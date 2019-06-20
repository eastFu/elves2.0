package cn.gyyx.elves.api.webservice;

import cn.gyyx.elves.core.api.ApiModuleService;
import cn.gyyx.elves.core.utils.JsonFilter;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  elves rt api v2
 * @author East.F
 * @date 2019年5月31日 09:59:01
 */
@RestController
@RequestMapping("/api/v2/rt")
public class RtWebService {

    private static final Logger LOG = Logger.getLogger(RtWebService.class);

    @Autowired
    private ApiModuleService apiServiceImpl;

    @RequestMapping("/exec")
    public String add(String ip, String app, String func,String param, String timeout, String proxy){
        LOG.debug("request elves rt exec");
        String response = JSON.toJSONString(apiServiceImpl.rtJob(ip, app, func, param, timeout, proxy), JsonFilter.filter);
        LOG.debug("rt exec response:"+response);
        return response;
    }

}
