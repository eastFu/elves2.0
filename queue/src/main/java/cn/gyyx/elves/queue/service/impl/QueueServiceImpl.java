package cn.gyyx.elves.queue.service.impl;

import cn.gyyx.elves.core.config.ElvesAttribute;
import cn.gyyx.elves.core.queue.QueueModuleService;
import cn.gyyx.elves.core.scheduler.SchedulerModuleService;
import cn.gyyx.elves.core.thrift.Instruct;
import cn.gyyx.elves.queue.dao.QueueDao;
import cn.gyyx.elves.queue.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueueServiceImpl implements QueueModuleService {

    private static final Logger logger = Logger.getLogger(QueueServiceImpl.class);

    @Autowired
    private QueueDao queueDao;

    @Autowired
    private SchedulerModuleService schedulerModuleService;

    @Override
    public String createQueue(String app) {
        String id = SecurityUtil.getUniqueKey();
        int flag = queueDao.createQueue(id, app);
        if (flag != 1) {
            return null;
        }
        return id;
    }

    @Override
    public String addTask(String queueId, String func, String ip, String mode, String param, String proxy, int timeout, String dependTaskId) {
        Map<String, Object> queue = queueDao.queryQueueById(queueId);
        if (queue == null) {
            return null;
        }

        if (StringUtils.isNotEmpty(dependTaskId)) {
            Map<String, Object> dependTask = queueDao.queryTaskById(dependTaskId);
            if (dependTask == null) {
                return null;
            }
        }

        String taskId = SecurityUtil.getUniqueKey();
        Map<String, Object> task = new HashMap<>();
        task.put("queue_id", queueId);
        task.put("task_id", taskId);

        task.put("ip", ip);
        task.put("mode", mode);
        task.put("app", queue.get("app").toString());
        task.put("func", func);
        task.put("param", param);
        task.put("timeout", timeout);
        task.put("proxy", proxy);
        task.put("depend_task_id", dependTaskId);

        int flag = queueDao.insertTask(task);
        if (flag != 1) {
            return null;
        }
        return taskId;
    }

    @Override
    public boolean commitQueue(String queueId){
        Map<String, Object> queue = queueDao.queryQueueById(queueId);
        if (queue == null||!"pendding".equals(queue.get("status"))) {
            return false;
        }
        queueDao.commitQueue(queueId);
        List<Map<String, Object>> listTask = queueDao.queryTasksByQueueId(queueId);
        if (listTask != null){
            for (Map<String, Object> mapFor : listTask) {
                String id = mapFor.get("task_id").toString();
                String ip = mapFor.get("ip").toString();
                String app = mapFor.get("app").toString();
                String func = mapFor.get("func").toString();
                String param = mapFor.get("param").toString();
                int timeout = Integer.parseInt(mapFor.get("timeout").toString());
                String proxy = mapFor.get("proxy") == null || StringUtils.isEmpty(mapFor.get("proxy").toString()) ? null : mapFor.get("proxy").toString();
                String mode = mapFor.get("mode").toString();
                Instruct ins = new Instruct(id, ip, ElvesAttribute.JOB_TYPE_QUEUE, mode, app, func, param, timeout, proxy);
                List<Instruct> list = new ArrayList<>();
                list.add(ins);
                // 异步任务，不管结果
                schedulerModuleService.sendDataAsync(ip, timeout, list);
            }
        }
        return true;
    }

    @Override
    public boolean stopQueue(String queueId){
        Map<String, Object> queue = queueDao.queryQueueById(queueId);
        if (queue == null) {
            return false;
        }
        int f = queueDao.stopQueue(queueId);
        logger.info("stopQueue flag: " + f);
        int tf = queueDao.stopTask(queueId);
        logger.info("stopTask flag:" + tf);
        return true;
    }

    @Override
    public Map<String, Object> queueResult(String queueId){
        Map<String, Object> queue = queueDao.queryQueueById(queueId);
        if (queue == null) {
            return null;
        }
        List<Map<String, Object>> rList = queueDao.queryResultsByQueueId(queueId);
        Map<String, Object> queueResult = new HashMap<>();

        if (rList != null) {
            for (Map<String, Object> mapFor : rList) {
                queueResult.put(mapFor.get("id").toString(), mapFor);
            }
        }
        return queueResult;
    }
}