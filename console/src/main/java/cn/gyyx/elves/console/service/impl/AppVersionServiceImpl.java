package cn.gyyx.elves.console.service.impl;

import cn.gyyx.elves.console.dao.AppVersionDao;
import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.service.AppVersionService;
import cn.gyyx.elves.console.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppVersionServiceImpl implements AppVersionService {

    @Autowired
    private AppVersionDao appVersionDao;

    public Result<Object> queryListForPage(Map<String,Object> map)throws Exception{

        List<Map<String,Object>> list = appVersionDao.queryListForPage(map);

        return ResultUtil.successForPage(list,map);
    }

}
