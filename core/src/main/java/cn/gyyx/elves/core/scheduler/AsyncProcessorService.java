package cn.gyyx.elves.core.scheduler;

import cn.gyyx.elves.core.thrift.Instruct;

/**
 *  异步数据处理服务接口
 */
public interface AsyncProcessorService {

    /**
     * 引用 ElvesAttribute.JOB_TYPE_CRON 或者 ElvesAttribute.JOB_TYPE_QUEUE
     * @return 类型 cron / queue
     */
    String getImplementClassType();

    /**
     *
     * @param flag  1 成功，0 失败
     * @param costtime  执行耗时 毫秒数
     * @param ins      通讯任务结构体
     * @param result   app执行结果json字符串
     */
    void agentAsyncMsgProcess(int flag, int costtime, Instruct ins,String result);
}
