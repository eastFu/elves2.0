package cn.gyyx.elves.cron.dao;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QrtzJobDetailsDao {

    Map<String,Object> queryByJobName(String cronId);

    List<Map<String,Object>> queryByJobIp(String ip);

    String queryRuleByTriggerName(String triggerName);
}
