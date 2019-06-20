package cn.gyyx.elves.cron.service;

import cn.gyyx.elves.cron.enums.ResultEnum;
import org.quartz.Job;
import org.quartz.SchedulerException;

import java.util.Map;

public interface SchedulerInter {

    /**
     * 添加 定时任务
     * @param map
     * @return
     * @throws SchedulerException
     */
    ResultEnum createJob(Map<String,Object> map, Class<? extends Job> jobClass) throws SchedulerException;

    /**
     * 删除定时任务
     * @param map
     * @return
     * @throws SchedulerException
     */
    boolean deleteJob(Map<String,Object> map) throws SchedulerException;


    /**
     * 暂停定时任务
     * @param map
     * @throws SchedulerException
     */
    void pauseJob(Map<String,Object> map) throws SchedulerException;


    /**
     * 恢复定时任务
     * @param map
     * @throws SchedulerException
     */
    void resumeJob(Map<String,Object> map) throws SchedulerException;

    /**
     * 清空定时任务
     * @throws SchedulerException
     */
     void clearAll() throws SchedulerException;
}
