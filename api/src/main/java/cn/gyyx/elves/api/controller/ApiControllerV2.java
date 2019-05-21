package cn.gyyx.elves.api.controller;

import cn.gyyx.elves.api.service.ApiService;
import cn.gyyx.elves.core.utils.JsonFilter;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class ApiControllerV2 {

    private static final Logger LOG = Logger.getLogger(ApiControllerV2.class);

    @Autowired
    private ApiService apiServiceImpl;

    @RequestMapping("/rt/exec")
    public String execRt(String ip, String app, String func,String param, String timeout, String proxy){
        LOG.info("request elves rt api");
        String response = JSON.toJSONString(apiServiceImpl.rtJob(ip, app, func, param, timeout, proxy), JsonFilter.filter);
        LOG.info("rt api response:"+response);
        return response;
    }


}
