package cn.gyyx.elves.api.controller;

import cn.gyyx.elves.api.service.ApiService;
import cn.gyyx.elves.core.utils.JsonFilter;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author East.F
 * @ClassName: RtJobServiceV3
 * @Description: The api for elves version.3
 * @date 2019/4/28 17:16
 */
@RestController
@RequestMapping("/api/v3")
public class ApiControllerV3 {

    private final static Logger LOG = LoggerFactory.getLogger(ApiControllerV3.class);

    @Autowired
    private ApiService apiServiceImpl;

    @RequestMapping("/rt/exec")
    @ResponseBody
    public String execRt(String ip, String app, String func,String param, String timeout, String proxy, String auth_id){
        LOG.info("request elves rt api");
        String response = JSON.toJSONString(apiServiceImpl.rtJob(ip, app, func, param, timeout, proxy), JsonFilter.filter);
        LOG.info("rt api response: {}",response);
        return response;
    }

}
