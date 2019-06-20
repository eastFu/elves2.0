package cn.gyyx.elves.console.service;

import cn.gyyx.elves.console.domain.Result;

import java.util.List;
import java.util.Map;

public interface AppService extends BaseService{

    Result<Object> queryList(Map<String, Object> map)throws Exception;

    Result<Object> startAppVersion(Map<String, Object> map)throws Exception;

    List<Map<String,Object>> queryAppInfoToHeartbeat()throws Exception;

    Result<Object> queryAuthAppList(Map<String,Object> map)throws Exception;


}
