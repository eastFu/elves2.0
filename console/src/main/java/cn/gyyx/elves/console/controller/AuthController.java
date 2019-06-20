package cn.gyyx.elves.console.controller;


import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.service.AuthService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Resource
    private AuthService authService;

    @ResponseBody
    @RequestMapping("/queryListForPage")
    public Result<Object> login(@RequestBody Map<String,Object> map)throws Exception{
        return authService.queryListForPage( map );
    }

    @ResponseBody
    @RequestMapping("/insertOrUpdate")
    public Result<Object> insertOrUpdate(@RequestBody Map<String,Object> map)throws Exception{
        return authService.insertOrUpdate( map );
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Result<Object> delete(@RequestBody List<Map<String,Object>> list)throws Exception{
        return authService.delete( list );
    }

}
