package cn.gyyx.elves.console.controller;


import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.service.FtpService;
import cn.gyyx.elves.console.utils.ResultUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequestMapping("/ftp")
@RestController
public class FtpController {

    @Autowired
    private FtpService ftpService;

    @RequestMapping("fileUpload")
    public Result<Object> fileUpload2(@RequestParam("file") MultipartFile file,@RequestParam Map<String,Object> map) throws Exception {
        //boolean result = ftpService.uploadFile( file.getOriginalFilename() ,file.getInputStream());

        String paramStr = map.get("map").toString();
        Map<String,Object> param = JSON.parseObject( paramStr );

        boolean result = ftpService.uploadFile( file, param);
        return ResultUtil.success(result);
    }

}
