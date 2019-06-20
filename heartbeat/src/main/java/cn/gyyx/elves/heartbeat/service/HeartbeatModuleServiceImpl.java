package cn.gyyx.elves.heartbeat.service;

import cn.gyyx.elves.core.heartbeat.HeartbeatModuleService;
import cn.gyyx.elves.core.thrift.AgentInfo;
import cn.gyyx.elves.core.thrift.HeartbeatService;
import cn.gyyx.elves.core.utils.DateUtils;
import cn.gyyx.elves.heartbeat.bo.HeartbeatPackage;
import cn.gyyx.elves.heartbeat.cache.Cache;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: HeartbeatModuleServiceImpl
 * @Description: heartbeat模块服务实现类
 * @author East.F
 * @date 2016年12月5日 下午3:47:06
 */
@Service
public class HeartbeatModuleServiceImpl implements HeartbeatModuleService, HeartbeatService.Iface{
	
	private static final Logger LOG = Logger.getLogger(HeartbeatModuleServiceImpl.class);

	@Override
	public String heartbeatPackage(AgentInfo info) throws TException {
		LOG.debug("heartbeat module reveived agent heartbeat package:"+info);
		Cache.put(info);
		JSONObject data =new JSONObject(){{
			put("data", Cache.getAgentAppVersion(info.getIp()));
		}};
		String appData = data.toJSONString();
		LOG.debug("heartbeat response to agent appData :"+appData);
		return appData;
	}
	
	@Override
	public Map<String, Object> agentInfo(String ip){
		HeartbeatPackage agent= Cache.getAgent(ip);
		if(null==agent){
			return new JSONObject();
		}
		JSONObject rs =new JSONObject(){{
			put("ip", agent.getAgent().getIp());
			put("asset",agent.getAgent().getId());
			put("last_hb_time", DateUtils.date2String(new Date(agent.getCheckTime()),DateUtils.DEFAULT_DATETIME_FORMAT));
			put("apps", JSON.parseObject(agent.getAgent().getApps(), new TypeReference<Map<String,Object>>(){}));
		}};
		return rs;
	}
	
	@Override
	public List<Map<String, Object>> agentList() {
		List<HeartbeatPackage> back= Cache.getAllAgent();
		List<Map<String, Object>> list = new ArrayList<>();
		for(HeartbeatPackage agent:back){
			JSONObject ele =new JSONObject(){{
                put("ip",agent.getAgent().getIp());
                put("asset",agent.getAgent().getId());
                put("last_hb_time",DateUtils.date2String(new Date(agent.getCheckTime()),DateUtils.DEFAULT_DATETIME_FORMAT));
                put("apps", JSON.parseObject(agent.getAgent().getApps(), new TypeReference<Map<String,Object>>(){}));
            }};
			list.add(ele);
		}
		return list;
	}
	
	@Override
	public boolean updateAppInfo(List<Map<String,Object>> appInfo){
		LOG.debug("updateAppInfo appInfo : "+JSON.toJSONString(appInfo));
		Cache.updateAppInfo(appInfo);
		return true;
	}

}
