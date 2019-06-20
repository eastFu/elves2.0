package cn.gyyx.elves.api.webservice;

import cn.gyyx.elves.core.api.ApiModuleService;
import cn.gyyx.elves.core.utils.JsonFilter;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  elves cron api v2
 * @author East.F
 * @date 2019年5月31日 09:59:01
 */
@RestController
@RequestMapping("/api/v2/cron")
public class CronWebService {

    private static final Logger LOG = Logger.getLogger(CronWebService.class);

    @Autowired
    private ApiModuleService apiServiceImpl;

    @RequestMapping("/add")
    public String add(String ip, String app, String func,String param, String timeout, String proxy,String mode,String rule){
        LOG.debug("request elves cron add");
        String response = JSON.toJSONString(apiServiceImpl.createCronJob(ip, app, func, param, timeout, proxy,mode,rule), JsonFilter.filter);
        LOG.debug("cron add response:"+response);
        return response;
    }

    @RequestMapping("/start")
    public String start( String cron_id){
        LOG.debug("request elves cron start");
        String response = JSON.toJSONString(apiServiceImpl.startCronJob(cron_id), JsonFilter.filter);
        LOG.debug("cron start response:"+response);
        return response;
    }

    @RequestMapping("/stop")
    public String stop( String cron_id){
        LOG.debug("request elves cron stop");
        String response = JSON.toJSONString(apiServiceImpl.stopCronJob(cron_id), JsonFilter.filter);
        LOG.debug("cron stop response:"+response);
        return response;
    }

    @RequestMapping("/delete")
    public String delete( String cron_id){
        LOG.debug("request elves cron delete");
        String response = JSON.toJSONString(apiServiceImpl.delCronJob(cron_id), JsonFilter.filter);
        LOG.debug("cron delete response:"+response);
        return response;
    }

    @RequestMapping("/detail")
    public String detailCron( String cron_id){
        LOG.debug("detail elves cron detail");
        String response = JSON.toJSONString(apiServiceImpl.getCronJobDetail(cron_id), JsonFilter.filter);
        LOG.debug("cron detail response:"+response);
        return response;
    }

}
