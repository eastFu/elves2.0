package cn.gyyx.elves.core.cron;


import java.util.Map;

/**
 *  cron 计划任务模块服务接口
 * @author East.F
 * @date 2019年5月30日 15:58:09
 */
public interface CronModuleService {

	/**
	 * 根据获取agent实时数据（返回单条数据）
	 * @param ip	机器IP
	 * @param app  APP名
	 * @param func  方法名
	 * @param param  参数json字符串
	 * @param timeout 超时时间毫秒数
	 * @param proxy   代理器
	 * @param rule	   quartz 定时任务表达式
	 * @return String 返回cronId
	 */
	String createCronJob(String ip, String app, String func, String param, int timeout, String proxy,String rule);

	/**
	 *  开启计划任务
	 * @param cronId   cron任务ID
	 * @return  boolean
	 */
	boolean startCronJob(String cronId);

	/**
	 *  停止计划任务
	 * @param cronId	cron任务ID
	 * @return  boolean
	 */
	boolean stopCronJob(String cronId);

	/**
	 *  删除计划任务
	 * @param cronId	cron任务ID
	 * @return  boolean
	 */
	boolean delCronJob(String cronId);

	/**
	 *  获取计划任务最近一次的执行结果
	 * @param cronId	cron任务ID
	 * @return	 cron任务的执行结果
	 */
	Map<String,Object> cronDetail(String cronId);
	
}
