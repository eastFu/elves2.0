package cn.gyyx.elves.cron.service.impl;

import cn.gyyx.elves.cron.dao.QrtzJobDetailsDao;
import cn.gyyx.elves.cron.enums.ResultEnum;
import cn.gyyx.elves.cron.service.SchedulerInter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Map;


public class SchedulerInterImpl implements SchedulerInter {

    private static final Logger logger = Logger.getLogger( SchedulerInterImpl.class);

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;



    @Autowired
    private QrtzJobDetailsDao qrtzJobDetailsDao;




    @Override
    public ResultEnum createJob(Map<String,Object> map, Class<? extends Job> jobClass) throws SchedulerException{
        logger.info("addJob,job:" + map);
        if (map == null ) {
            throw new NullPointerException();
        }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = new JobKey(map.get("id").toString(),map.get("ip").toString());
        TriggerKey triggerKey = TriggerKey.triggerKey(map.get("id").toString(),map.get("ip").toString());



        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 不存在，创建一个
        if (null == trigger) {
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).build();
            jobDetail.getJobDataMap().put("scheduleJob", map);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(map.get( "rule" ).toString()).withMisfireHandlingInstructionFireAndProceed();
            trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
            return  ResultEnum.SUCCESS;
        }
        return ResultEnum.ERROR_CRON_EXISTED;

    }

    @Override
    public boolean deleteJob(Map<String,Object> map) throws SchedulerException{
        logger.info("deleteJob,jobId:" + map);
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        String job_name = map.get("JOB_NAME").toString();
        String job_group = map.get("JOB_GROUP").toString();

        JobKey jobKey = JobKey.jobKey(job_name, job_group);
        return scheduler.deleteJob(jobKey);
    }

    @Override
    public void pauseJob(Map<String,Object> map) throws SchedulerException {
        logger.info("pauseJob,jobId:" + map);
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        String job_name = map.get("JOB_NAME").toString();
        String job_group = map.get("JOB_GROUP").toString();

        JobKey jobKey = new JobKey(job_name,job_group);
        scheduler.pauseJob(jobKey);
    }

    @Override
    public void resumeJob(Map<String,Object> map) throws SchedulerException {
        logger.info("resumeJob,jobId:" + map);


        String job_name = map.get("JOB_NAME").toString();
        String job_group = map.get("JOB_GROUP").toString();

       String rule = qrtzJobDetailsDao.queryRuleByTriggerName(job_name);
       if(StringUtils.isEmpty( rule )){
           throw new NullPointerException();
       }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();


        TriggerKey triggerKey = TriggerKey.triggerKey(job_name,job_group);
        CronTrigger trigger  = (CronTrigger) scheduler.getTrigger(triggerKey  );

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(rule).withMisfireHandlingInstructionFireAndProceed();

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob( triggerKey,trigger );

    }

    @Override
    public void clearAll() throws SchedulerException {
        logger.info("clearAll");
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.clear();
    }


}
