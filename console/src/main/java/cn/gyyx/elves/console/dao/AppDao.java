package cn.gyyx.elves.console.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AppDao extends BaseDao {

    List<Map<String,Object>> queryList(Map<String, Object> map);

    int updateAppVersion(Map<String,Object> map);

    int insertAppVersion(Map<String,Object> map);

    int startAppVersion(Map<String,Object> map);

    List<Map<String,Object>> queryAppInfoToHeartbeat();

    List<Map<String,Object>> queryAuthAppList(Map<String,Object> map);

    List<Map<String,Object>> queryAgentAll(Map<String,Object> map);

}
