package cn.gyyx.elves.cron.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface TaskResultDao {

    int insert(Map<String,Object> map);

    int delete(Map<String,Object> map);

    Map<String,Object> queryByCronId(String cronId);
}
