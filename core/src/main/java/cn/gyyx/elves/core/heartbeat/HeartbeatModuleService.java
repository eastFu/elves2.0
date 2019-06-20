package cn.gyyx.elves.core.heartbeat;

import java.util.List;
import java.util.Map;

/**
 * heartbeat模块服务接口
 * @author East.F
 * @date 2016年12月5日 下午3:50:43
 */
public interface HeartbeatModuleService {

	/**
	 * @Title: agentInfo
	 * @Description: 根据获取agent实时数据（返回单条数据）
	 * @param ip
	 * @return Map<String,Object>    返回类型
	 */
	Map<String, Object> agentInfo(String ip);
	
	/**
	 * @Title: agentList
	 * @Description: 搜索获取agent数据，可分页
	 * @return Map<String,Object>    返回类型
	 */
	List<Map<String, Object>> agentList();
	
	
	/**
	 * @Title: noticeAppInfo
	 * @Description: <p>接受supervisor的通知， 更新内存中app版本信息发生变化，
	 * 				格式：[{'app':'testApp','version':'1.0','agentList':['192.168.1.1','192.168.1.2']}]
	 * 				</p>
	 * @param appInfo
	 * @return boolean   返回类型
	 */
	boolean updateAppInfo(List<Map<String,Object>> appInfo);
}
