package cn.gyyx.elves.api.webservice;

import cn.gyyx.elves.core.api.ApiModuleService;
import cn.gyyx.elves.core.utils.JsonFilter;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  elves queue api v2
 * @author East.F
 * @date 2019年5月31日 09:59:01
 */
@RestController
@RequestMapping("/api/v2/queue")
public class QueueWebService {

    private static final Logger LOG = Logger.getLogger(QueueWebService.class);

    @Autowired
    private ApiModuleService apiServiceImpl;

    @RequestMapping("/create")
    public String create(String app){
        LOG.debug("request elves queue create");
        String response = JSON.toJSONString(apiServiceImpl.createQueue(app), JsonFilter.filter);
        LOG.debug("queue create response:" + response);
        return response;
    }

    @RequestMapping("/addtask")
    public String addtask(String queueId,String ip, String func, String param, String timeout, String proxy,String mode,String depend_task_id){
        LOG.debug("request elves queue addTask");
        String response = JSON.toJSONString(apiServiceImpl.addTaskQueue(queueId,ip, func, param, timeout, proxy,mode,depend_task_id), JsonFilter.filter);
        LOG.debug("queue addTask response:"+response);
        return response;
    }

    @RequestMapping("/commit")
    public String commit( String queue_id){
        LOG.debug("request elves queue commit");
        String response = JSON.toJSONString(apiServiceImpl.commitQueue(queue_id), JsonFilter.filter);
        LOG.debug("queue commit response:"+response);
        return response;
    }

    @RequestMapping("/stop")
    public String stop( String queue_id){
        LOG.debug("request elves queue stop");
        String response = JSON.toJSONString(apiServiceImpl.stopQueue(queue_id), JsonFilter.filter);
        LOG.debug("queue stop response:"+response);
        return response;
    }

    @RequestMapping("/result")
    public String result( String queue_id){
        LOG.debug("request elves queue result");
        String response = JSON.toJSONString(apiServiceImpl.resultQueue(queue_id), JsonFilter.filter);
        LOG.debug("queue result response:"+response);
        return response;
    }

    @RequestMapping("/qksqueue")
    public String qksqueue(String app,String ip , String func, String param, String timeout, String proxy,String mode){
        LOG.debug("request elves queue qksqueue");
        String response = JSON.toJSONString(apiServiceImpl.createQksQueue(app,ip,func,param,timeout,proxy,mode), JsonFilter.filter);
        LOG.debug("queue qksqueue response:"+response);
        return response;
    }

}
