package cn.gyyx.elves.core.api;

/**
 * interface api
 * @author East.F
 * @date 2019年5月30日 16:03:12
 */
public interface ApiModuleService {

    ElvesResponse rtJob(String ip, String app, String func, String param, String timeout, String proxy);



    ElvesResponse createCronJob(String ip, String app, String func, String param, String timeout, String proxy,String mode,String rule);

    ElvesResponse startCronJob(String cronId);

    ElvesResponse stopCronJob(String cronId);

    ElvesResponse delCronJob(String cronId);

    ElvesResponse getCronJobDetail(String cronId);



    ElvesResponse createQueue(String app);

    ElvesResponse addTaskQueue(String queueId,String ip,String func, String param, String timeout, String proxy,String mode,String dependTaskId);

    ElvesResponse commitQueue(String queueId);

    ElvesResponse stopQueue(String queueId);

    ElvesResponse resultQueue(String queueId);

    ElvesResponse createQksQueue(String app,String ip,String func, String param, String timeout, String proxy,String mode);


    /**
     * 获取此AuthID所对应的APP的基本信息，包含APP指令名称，APP名称、APP版本号以及APP的最后更新时间
     * @param authId  授权authId
     * @return ElvesResponse
     */
    ElvesResponse appInfo(String authId);

    /**
     * 获取此AuthID对应的APP可运行的AGENT的列表信息，包含AGENT别称，AGENT IP
     * @param authId  授权authId
     * @return ElvesResponse
     */
    ElvesResponse agentsInfo(String authId);

    /**
     * <p>
     * 将获取单个AGENT的详细信息
     * 包含其IP，别称，上线时间，计划任务列表，此接口依赖于Heartbeat，
     * 若未启用heartbeat则此接口不可用，计划任务列表部分信息依赖于cron组件，
     * 若未启用cron组件则计划任务列表不可用
     * </p>
     * @param ip  机器IP
     * @return ElvesResponse
     */
    ElvesResponse agentDetailInfo(String ip);

}
