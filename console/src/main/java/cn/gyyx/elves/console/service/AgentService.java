package cn.gyyx.elves.console.service;

import cn.gyyx.elves.console.domain.Result;

import java.util.List;
import java.util.Map;

public interface AgentService {

    Result<Object> queryListAll();

    Result<Object> insert(List<Map<String,Object>> list)throws Exception;

    Result<Object> remove(List<Map<String,Object>> list)throws Exception;
}
