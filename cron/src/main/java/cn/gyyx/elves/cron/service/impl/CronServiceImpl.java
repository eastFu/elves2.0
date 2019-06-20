package cn.gyyx.elves.cron.service.impl;

import cn.gyyx.elves.core.cron.CronModuleService;
import cn.gyyx.elves.core.utils.ExceptionUtil;
import cn.gyyx.elves.core.utils.SecurityUtil;
import cn.gyyx.elves.cron.dao.QrtzJobDetailsDao;
import cn.gyyx.elves.cron.dao.TaskResultDao;
import cn.gyyx.elves.cron.enums.ResultEnum;
import cn.gyyx.elves.cron.job.ScheduledJob;
import cn.gyyx.elves.cron.service.SchedulerInter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CronServiceImpl implements CronModuleService {

    private static final Logger logger = Logger.getLogger(CronServiceImpl.class);

    @Autowired
    private SchedulerInter schedulerInter;

    @Autowired
    private QrtzJobDetailsDao qrtzJobDetailsDao;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private TaskResultDao taskResultDao;


    @Override
    public String createCronJob(String ip, String app, String func, String param, int timeout, String proxy, String rule) {
        String cronId = SecurityUtil.getUniqueKey();
        Map<String, Object> data = new HashMap<String, Object>() {{
            put("id", cronId);
            put("ip", ip);
            put("app", app);
            put("func", func);
            put("param", param);
            put("timeout", timeout);
            put("proxy", proxy);
            put("rule", rule);
        }};
        try {
            ResultEnum result = schedulerInter.createJob(data, ScheduledJob.class);
            if (result.getCode() == 0) {
                return cronId;
            }
            return null;
        } catch (SchedulerException e) {
            logger.error(ExceptionUtil.getStackTraceAsString(e));
            return null;
        }
    }

    @Override
    public boolean startCronJob(String cronId) {
        Map<String, Object> job = qrtzJobDetailsDao.queryByJobName(cronId);
        if (job == null) {
            return false;
        }
        try {
            schedulerInter.resumeJob(job);
            return true;
        } catch (Exception e) {
            logger.error(ExceptionUtil.getStackTraceAsString(e));
            return false;
        }
    }

    @Override
    public boolean stopCronJob(String cronId) {
        Map<String, Object> job = qrtzJobDetailsDao.queryByJobName(cronId);
        if (job == null) {
            return false;
        }
        try {
            schedulerInter.pauseJob(job);
            return true;
        } catch (Exception e) {
            logger.error(ExceptionUtil.getStackTraceAsString(e));
            return false;
        }
    }

    @Override
    public boolean delCronJob(String cronId) {
        Map<String, Object> job = qrtzJobDetailsDao.queryByJobName(cronId);
        if (job == null) {
            return false;
        }
        try {
            schedulerInter.deleteJob(job);
            return true;
        } catch (Exception e) {
            logger.error(ExceptionUtil.getStackTraceAsString(e));
            return false;
        }
    }

    //该方法有问题，需要重写！！！！
    @Override
    public Map<String, Object> cronDetail(String cronId) {
        Map<String, Object> job = qrtzJobDetailsDao.queryByJobName(cronId);
        if (job == null) {
            return null;
        }
        try {
            Map<String, Object> mapResult = new HashMap<>();
            Map<String, Object> cronDeObj = getJobDetailByJobKey(job);
            mapResult.putAll(cronDeObj);
            String jobName = job.get("JOB_NAME").toString();
            Map<String, Object> cronResult = taskResultDao.queryByCronId(jobName);
            if (null == cronResult) {
                mapResult.put("last_exec_time", "");
                mapResult.put("last_exec_result", Collections.emptyMap());
            } else {
                Map<String, Object> workerData = new HashMap<>();
                workerData.put("worker_flag", cronResult.get("worker_flag"));
                workerData.put("worker_message", cronResult.get("worker_message"));
                workerData.put("worker_costtime", cronResult.get("worker_costtime"));

                Map<String, Object> data = new HashMap<String, Object>();
                data.put("flag", cronResult.get("flag"));
                data.put("error", cronResult.get("error"));
                data.put("result", workerData);

                String endTime = cronResult.get("end_time") == null || StringUtils.isEmpty(cronResult.get("end_time").toString()) ? "" : cronResult.get("end_time").toString();
                String last_exec_time = StringUtils.isEmpty(endTime) ? "" : endTime.substring(0, endTime.length() - 2);

                mapResult.put("last_exec_time", last_exec_time);
                mapResult.put("last_exec_result", data);
            }
            return mapResult;
        } catch (Exception e) {
            logger.error(ExceptionUtil.getStackTraceAsString(e));
            return null;
        }
    }

    private Map<String, Object> getJobDetailByJobKey(Map<String, Object> map) throws Exception {
        String job_name = map.get("JOB_NAME").toString();
        String job_group = map.get("JOB_GROUP").toString();
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = new JobKey(job_name, job_group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        Map<String, Object> mapResult = (Map<String, Object>) jobDataMap.get("scheduleJob");
        return mapResult;
    }
}
