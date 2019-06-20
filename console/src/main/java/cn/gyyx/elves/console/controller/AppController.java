package cn.gyyx.elves.console.controller;

import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.service.AppService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/app")
@RestController
public class AppController {

    @Resource
    private AppService appService;

    @ResponseBody
    @RequestMapping("/queryListForPage")
    public Result<Object> queryListForPage(@RequestBody Map<String,Object> map)throws Exception{
        return appService.queryListForPage( map );
    }

    @ResponseBody
    @RequestMapping("/insertOrUpdate")
    public Result<Object> insertOrUpdate(@RequestBody Map<String,Object> map)throws Exception{
        return appService.insertOrUpdate( map );
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Result<Object> delete(@RequestBody List<Map<String,Object>> list)throws Exception{
        return appService.delete( list );
    }

    @ResponseBody
    @RequestMapping("/queryList")
    public Result<Object> queryList(@RequestBody Map<String,Object> map)throws Exception{
        return appService.queryList( map );
    }


    @ResponseBody
    @RequestMapping("/startAppVersion")
    public Result<Object> startAppVersion(@RequestBody Map<String,Object> map)throws Exception{
        return appService.startAppVersion( map );
    }


    @ResponseBody
    @RequestMapping("/queryAuthAppList")
    public Result<Object> queryAuthAppList(@RequestBody Map<String,Object> map)throws Exception{
        return appService.queryAuthAppList( map );
    }

}
