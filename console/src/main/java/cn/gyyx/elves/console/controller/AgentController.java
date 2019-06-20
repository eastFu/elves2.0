package cn.gyyx.elves.console.controller;


import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/agent")
@RestController
public class AgentController {

    @Autowired
    private AgentService agentService;


    @ResponseBody
    @RequestMapping("/queryListAll")
    public Result<Object> queryListAll()throws Exception{
        return agentService.queryListAll(  );
    }


    @ResponseBody
    @RequestMapping("/insert")
    public Result<Object> insert(@RequestBody List<Map<String,Object>> list)throws Exception{
        return agentService.insert( list );
    }

    @ResponseBody
    @RequestMapping("/remove")
    public Result<Object> remove(@RequestBody List<Map<String,Object>> list)throws Exception{
        return agentService.remove( list );
    }

}
