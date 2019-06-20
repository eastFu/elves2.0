package cn.gyyx.elves.queue.service.impl;

import cn.gyyx.elves.core.config.ElvesAttribute;
import cn.gyyx.elves.core.scheduler.AsyncProcessorService;
import cn.gyyx.elves.core.scheduler.SchedulerModuleService;
import cn.gyyx.elves.core.thrift.Instruct;
import cn.gyyx.elves.queue.dao.QueueDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AsyncProcessorServiceQueueImpl implements AsyncProcessorService {

    private static final Logger logger = Logger.getLogger(AsyncProcessorServiceQueueImpl.class);


    @Autowired
    private QueueDao queueDao;

    @Autowired
    private SchedulerModuleService schedulerModuleService;

    @Override
    public String getImplementClassType() {
        return ElvesAttribute.JOB_TYPE_QUEUE;
    }

    @Override
    public void agentAsyncMsgProcess(int flagInt, int costtime, Instruct ins, String result) {
        Map<String, Object> map = new HashMap<>();
        String task_id = ins.getId();
        String flag = flagInt == 1 ? "true" : "false";
        String error = "";
        int worker_flag = flagInt;
        String worker_message = result;
        int worker_costtime = costtime;
        map.put("task_id", task_id);
        map.put("flag", flag);
        map.put("error", error);
        map.put("worker_flag", worker_flag);
        map.put("worker_message", worker_message);
        map.put("worker_costtime", worker_costtime);
        map.put("status", "finish");
        queueDao.saveResult(map);


        List<Map<String, Object>> taskList = queueDao.getNextTasks(task_id);
        for (Map<String, Object> mapFor : taskList) {
            String id = mapFor.get("task_id").toString();
            String ip = mapFor.get("ip").toString();
            String app = mapFor.get("app").toString();
            String func = mapFor.get("func").toString();
            String param = mapFor.get("param").toString();
            int timeout = Integer.parseInt(mapFor.get("timeout").toString());
            String proxy = mapFor.get("proxy") == null || StringUtils.isEmpty(mapFor.get("proxy").toString()) ? null : mapFor.get("proxy").toString();
            String mode = mapFor.get("mode").toString();

            Instruct insNext = new Instruct(id, ip, ElvesAttribute.JOB_TYPE_QUEUE, mode, app, func, param, timeout, proxy);
            List<Instruct> list = new ArrayList<>();
            list.add(insNext);

            // 异步任务，不管结果
            schedulerModuleService.sendDataAsync(ip, timeout, list);
            queueDao.updateTaskStatus(id, "running");
        }
    }

}
