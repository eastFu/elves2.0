package cn.gyyx.elves.console.controller;


import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/queryListForPage")
    public Result<Object> queryListForPage(@RequestBody Map<String,Object> map)throws Exception{
        return userService.queryListForPage( map );
    }

    @ResponseBody
    @RequestMapping("/insertOrUpdate")
    public Result<Object> insertOrUpdate(@RequestBody Map<String,Object> map)throws Exception{
        return userService.insertOrUpdate( map );
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Result<Object> delete(@RequestBody List<Map<String,Object>> list)throws Exception{
        return userService.delete( list );
    }
}
