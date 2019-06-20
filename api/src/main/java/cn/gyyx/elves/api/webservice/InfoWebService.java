package cn.gyyx.elves.api.webservice;

import cn.gyyx.elves.core.api.ApiModuleService;
import cn.gyyx.elves.core.utils.JsonFilter;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  elves info api v2
 * @author East.F
 * @date 2019年5月31日 09:59:01
 */
@RestController
@RequestMapping("/api/v2/info")
public class InfoWebService {

    private static final Logger LOG = Logger.getLogger(InfoWebService.class);

    @Autowired
    private ApiModuleService apiServiceImpl;

    @RequestMapping("/app")
    public String appInfo( String auth_id){
        LOG.debug("request elves app info");
        String response = JSON.toJSONString(apiServiceImpl.appInfo(auth_id), JsonFilter.filter);
        LOG.debug("app info response:"+response);
        return response;
    }

    @RequestMapping("/agents")
    public String agentsInfo( String auth_id){
        LOG.debug("request elves agents info");
        String response = JSON.toJSONString(apiServiceImpl.agentsInfo(auth_id), JsonFilter.filter);
        LOG.debug("agents info response:"+response);
        return response;
    }

    @RequestMapping("/agents/detail")
    public String agentsDetailInfo( String auth_id){
        LOG.debug("request elves agents info detail");
        String response = JSON.toJSONString(apiServiceImpl.agentDetailInfo(auth_id), JsonFilter.filter);
        LOG.debug("agents info detail response:"+response);
        return response;
    }

}
