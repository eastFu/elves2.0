package cn.gyyx.elves.core.queue;

import java.util.Map;

/**
 *  queue模块服务接口
 * @author East.F
 * @date 2019年5月31日 11:19:13
 */
public interface QueueModuleService {

    String createQueue(String app);

    String addTask(String queueId,String func,String ip,String mode,String param,String proxy,int timeout,String dependTaskId);

    boolean commitQueue(String queueId);

    boolean stopQueue(String queueId);

    Map<String,Object> queueResult(String queueId);

}
