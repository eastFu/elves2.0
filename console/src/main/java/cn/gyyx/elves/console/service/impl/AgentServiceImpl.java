package cn.gyyx.elves.console.service.impl;

import cn.gyyx.elves.console.dao.AppAgentDao;
import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.service.AgentService;
import cn.gyyx.elves.console.service.AppService;
import cn.gyyx.elves.console.utils.ResultUtil;
import cn.gyyx.elves.core.heartbeat.HeartbeatModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AgentServiceImpl implements AgentService{

    @Autowired
    private HeartbeatModuleService heartbeatModuleService;

    @Autowired
    private AppAgentDao appAgentDao;


    @Autowired
    private AppService appService;

    @Override
    public Result<Object> queryListAll() {
        return ResultUtil.success(heartbeatModuleService.agentList());
    }

    @Override
    public Result<Object> insert(List<Map<String,Object>> list) throws Exception {
        int result = 0;
        for(Map<String,Object> mapFor : list){
            result += appAgentDao.insert( mapFor );
        }

        List<Map<String,Object>> heartbearList = appService.queryAppInfoToHeartbeat();
        heartbeatModuleService.updateAppInfo( heartbearList );

        return ResultUtil.success(result);
    }

    @Override
    public Result<Object> remove(List<Map<String, Object>> list) throws Exception {
        int result = 0;
        for(Map<String,Object> mapFor : list){
            result += appAgentDao.delete( mapFor );
        }

        List<Map<String,Object>> heartbearList = appService.queryAppInfoToHeartbeat();
        heartbeatModuleService.updateAppInfo( heartbearList );
        return ResultUtil.success(result);
    }
}
