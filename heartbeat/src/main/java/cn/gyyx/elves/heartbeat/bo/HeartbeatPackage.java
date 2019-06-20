package cn.gyyx.elves.heartbeat.bo;


import cn.gyyx.elves.core.thrift.AgentInfo;

import java.io.Serializable;

/**
 * @ClassName: HeartbeatPackage
 * @Description: 自定义elves agent 心跳包,加入最后一次检测时间，用于标识在线状态，超时时间为1分钟
 * @author East.F
 * @date 2019年5月29日 17:34:22
 */
public class HeartbeatPackage implements Serializable {

	/**
     *  心跳检测时间戳
	 */
	private long checkTime;

	/**
     * 上报 elves agent信息
	 */
	private AgentInfo agent;

	public HeartbeatPackage(long checkTime, AgentInfo agent) {
		super();
		this.checkTime = checkTime;
		this.agent = agent;
	}
	
	public long getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(long checkTime) {
		this.checkTime = checkTime;
	}

	public AgentInfo getAgent() {
		return agent;
	}

	public void setAgent(AgentInfo agent) {
		this.agent = agent;
	}

	@Override
	public boolean equals(Object obj) {
		if(null==obj||!(obj instanceof AgentInfo)){
			return false;
		}
		AgentInfo other=((HeartbeatPackage)obj).getAgent();
		String ip =other.getIp();
		String id =other.getId();
		String version =other.getVersion();
		String apps =other.getApps();

		AgentInfo curr =getAgent();
		if(curr.getIp().equals(ip)&&curr.getId().equals(id)&&curr.getVersion().equals(version)&&curr.getApps().equals(apps)){
			return true;
		}
		return false;
	}
}
