package cn.gyyx.elves.console.service;

import cn.gyyx.elves.console.domain.Result;

import java.util.Map;

public interface AppVersionService {

    Result<Object> queryListForPage(Map<String,Object> map)throws Exception;

}
