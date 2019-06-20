package cn.gyyx.elves.console.service;

import cn.gyyx.elves.console.domain.Result;

import java.util.List;
import java.util.Map;

public interface BaseService {

    Result<Object> queryListForPage(Map<String, Object> map)throws Exception;

    Result<Object> insertOrUpdate(Map<String, Object> map)throws Exception;


    Result<Object> delete(List<Map<String, Object>> list)throws Exception;
}
