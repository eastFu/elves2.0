package cn.gyyx.elves.console.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {

    List<Map<String,Object>> queryListForPage(Map<String, Object> map);

    int insert(Map<String, Object> map);

    int update(Map<String, Object> map);

    int delete(Map<String, Object> map);

    int queryTotalByField(Map<String, Object> map);
}
