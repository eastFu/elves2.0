package cn.gyyx.elves.heartbeat.cache;

import cn.gyyx.elves.core.config.ElvesConfig;
import cn.gyyx.elves.core.thrift.AgentInfo;
import cn.gyyx.elves.core.utils.SpringContextUtil;
import cn.gyyx.elves.heartbeat.bo.HeartbeatPackage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @ClassName: Cache
 * @Description:  heartbeat module 缓存类
 * @author East.F
 * @date 2019年5月29日 17:31:22
 */
@Component
public class Cache {

	private static final Logger LOG = Logger.getLogger(Cache.class);

	@Autowired
	private static ElvesConfig elvesConfig;

	private static Map<String, HeartbeatPackage> cache = new Hashtable<>();

	/**
	 * 存放supervisor 管理的app信息（instruct,version）,定时同步+被动接收更新通知
	 * (格式 [{'app':'testApp','version':'1.0','agentList':['192.168.1.1','192.168.1.2']})
	 */
	private static List<Map<String,Object>> cacheAppInfo = new ArrayList<>();

	/**
	 *  心跳时间为 60s ,超时时长设置为10分钟 = 10 * 60 * 1000 = 600000ms
	 */
	private static final long TIME_OUT = 600000L;

	/**
	 * @Title: updateAppInfo
	 * @Description: 更新内存中 app 数据
	 * @param appInfo 设定文件
	 * @return void   返回类型
	 */
	public static void updateAppInfo(List<Map<String,Object>> appInfo){
		cacheAppInfo.clear();
		cacheAppInfo.addAll(appInfo);
	}
	
	/**
	 * @Title: getAgentAppVersion
	 * @Description: 根据ip获取该机器上可以运行app列表
	 * @param ip  机器IP唯一标识
	 * @return Map<String,String>   返回该机器可以下载的app列表
	 */
	public static Map<String,String> getAgentAppVersion(String ip){
		Map<String,String> appVersion=new HashMap<String,String>();
		if(SpringContextUtil.getBean(ElvesConfig.class).isSupervisorEnable()){
			if(StringUtils.isNotBlank(ip)){
				for(Map<String,Object> app: cacheAppInfo){
					String instruct=app.get("app").toString();
					String version=app.get("version").toString();
					List<String> agentList = JSON.parseObject(app.get("agentList").toString(), new TypeReference<List<String>>(){});
					if(agentList.contains(ip)){
						appVersion.put(instruct, version);
					}
				}
			}
			return appVersion;
		}else{
			return appVersion;
		}
	}
	
	/**
	 * @Title: put
	 * @Description: 存储
	 * @param info 设定文件
	 * @return void    返回类型
	 */
	public static void put(AgentInfo info){
		if(StringUtils.isNotBlank(info.getIp())){
			Cache.cache.put(info.getIp(),new HeartbeatPackage(System.currentTimeMillis(),info));
		}else{
			LOG.error("heartbeat module reveive elves-agent ip is empty" + info.toString());
		}
	}

	/**
	 * @Title: getAllAgent
	 * @Description: cache获取所有在线的agent
	 * @return List<HeartbeatPackage>    返回类型
	 */
	public static List<HeartbeatPackage> getAllAgent(){
		List<HeartbeatPackage> result = new ArrayList<>();
		for(HeartbeatPackage custom: Cache.cache.values()){
			if(agentIsOnLine(custom)){
				result.add(custom);
			}
		}
		return result;
	}

	/**
	 * @Title: getAgent
	 * @Description: 根据IP从cache获取在线的agent
	 * @param ip
	 * @return List<HeartbeatPackage>    返回类型
	 */
	public static HeartbeatPackage getAgent(String ip){
		if(StringUtils.isEmpty(ip)){
			return null;
		}
		HeartbeatPackage agent = Cache.cache.get(ip);
		if(agentIsOnLine(agent)){
			return agent;
		}
		return null;
	}
	
	
	/**
	 * @Title: agentIsOnLine
	 * @Description: 检测agent是否已经在线
	 * @param heartbeatPackage
	 * @return boolean    返回类型
	 */
	private static boolean agentIsOnLine(HeartbeatPackage heartbeatPackage){
		if(null==heartbeatPackage){
			return false;
		}
		long date=System.currentTimeMillis() - heartbeatPackage.getCheckTime();
		if(date<= Cache.TIME_OUT){
			return true;
		}
		return false;
	}

	/**
	 * @Title: getCacheAppInfo
	 * @Description: 获取缓存中的App列表
	 * @return List<Map<String, Object>>    返回类型
	 */
	public static List<Map<String, Object>> getCacheAppInfo() {
		return cacheAppInfo;
	}
}
