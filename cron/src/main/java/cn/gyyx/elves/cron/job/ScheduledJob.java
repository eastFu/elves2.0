package cn.gyyx.elves.cron.job;

import cn.gyyx.elves.core.config.ElvesAttribute;
import cn.gyyx.elves.core.scheduler.SchedulerModuleService;
import cn.gyyx.elves.core.thrift.Instruct;
import cn.gyyx.elves.core.thrift.Reinstruct;
import cn.gyyx.elves.core.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ScheduledJob implements Job {

    private static final Logger logger = Logger.getLogger( ScheduledJob.class);

    @Resource
    private SchedulerModuleService schedulerModuleService;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("执行自定义定时任务");
        Map<String,Object> map = (Map) context.getMergedJobDataMap().get("scheduleJob");

        String id = map.get( "id" ).toString();
        String ip =  map.get("ip").toString();
        String app = map.get("app").toString();
        String func = map.get("func").toString();
        String param = map.get("param").toString();
        int timeout =  Integer.parseInt( map.get("timeout").toString() );
        String proxy =  map.get("proxy") == null || StringUtils.isEmpty( map.get("proxy").toString() ) ? null : map.get("proxy").toString();
        String mode = map.get("mode").toString();

        Instruct ins = new Instruct( id , ip, ElvesAttribute.JOB_TYPE_CRON, mode, app, func, param, timeout , proxy);
        List<Instruct> list = new ArrayList<>(  );
        list.add( ins );

        // 异步任务，不管结果
        schedulerModuleService.sendDataAsync(ip,timeout, list);
    }
}
