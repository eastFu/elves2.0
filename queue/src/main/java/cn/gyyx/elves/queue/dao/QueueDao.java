package cn.gyyx.elves.queue.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QueueDao {
    int createQueue(String queue_id, String app);

    Map<String,Object> queryQueueById(String queue_id);

    Map<String,Object> queryTaskById(String taskId);

    int insertTask(Map<String,Object> map);

    int commitQueue(String queue_id);

    List<Map<String,Object>> queryTasksByQueueId(String queue_id);

    int stopQueue(String queue_id);

    int stopTask(String queue_id);

    List<Map<String,Object>> queryResultsByQueueId(String queue_id);

    int saveResult(Map<String,Object> map);

    List<Map<String,Object>> getNextTasks(String taskId);

    int updateTaskStatus(String taskId,String status);

}
