package cn.gyyx.elves.heartbeat.timer;

import cn.gyyx.elves.core.config.ElvesConfig;
import cn.gyyx.elves.core.utils.ExceptionUtil;
import cn.gyyx.elves.heartbeat.util.Storage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName: AppInfoTasker
 * @Description: 定时器：定时同步校验内存中app版本数据和supervisor是否一直，进行更新
 * @author East.F
 * @date 2017年5月23日 下午3:35:44
 */
@Component
public class AppInfoTasker {
	
	private static final org.apache.log4j.Logger LOG = Logger.getLogger(AppInfoTasker.class);

	@Resource
	private ElvesConfig elvesConfig;

	public void excute(){
		try {
			LOG.info("Get app info tasker running...");
			/*if(elvesConfig.isSupervisorEnable()){
				Map<String, Object> back =null;
				if(null!=back&&null!=back.get("result")){
					List<Map<String, Object>> appInfo =  JSON.parseObject(back.get("result").toString(),new TypeReference<List<Map<String, Object>>>(){});
					Storage.updateAppInfo(appInfo);
				}
			}*/
			LOG.info("storage app info :"+ Storage.getCacheAppInfo());
		} catch (Exception e) {
			LOG.error("update app info error:"+ ExceptionUtil.getStackTraceAsString(e));
		}
	}
}
