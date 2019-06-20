package cn.gyyx.elves.console.controller;


import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.service.AppService;
import cn.gyyx.elves.console.service.AppVersionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/appversion")
@RestController
public class AppVersionController {


    @Resource
    private AppVersionService appVersionService;

    @ResponseBody
    @RequestMapping("/queryListForPage")
    public Result<Object> queryListForPage(@RequestBody Map<String,Object> map)throws Exception{
        return appVersionService.queryListForPage( map );
    }
}
