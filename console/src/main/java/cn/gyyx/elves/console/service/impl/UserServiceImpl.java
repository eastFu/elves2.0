package cn.gyyx.elves.console.service.impl;

import cn.gyyx.elves.console.dao.UserDao;
import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.domain.User;
import cn.gyyx.elves.console.exception.AppException;
import cn.gyyx.elves.console.service.ThreadInter;
import cn.gyyx.elves.console.service.UserService;
import cn.gyyx.elves.console.utils.DateUtil;
import cn.gyyx.elves.console.utils.ResultUtil;
import cn.gyyx.elves.core.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Resource
    private ThreadInter threadInter;

    @Override
    public Result<Object> queryListForPage(Map<String, Object> map) throws Exception {

        List<Map<String,Object>> list = userDao.queryListForPage( map );

        return ResultUtil.successForPage( list,map );
    }

    @Override
    public Result<Object> insertOrUpdate(Map<String, Object> map) throws Exception {
        // 断言
        assertion(map);
        int result = 0;
        String id = map.get("id") == null || StringUtils.isEmpty(map.get("id").toString())  || "-1".equals(map.get("id").toString()) ? "" : map.get("id").toString();
        if(StringUtils.isEmpty(id)){
            // 新增
            String now = DateUtil.getNowDate();
            map.put("create_time", now);
            map.put("update_time", now);
            Map<String,Object> userMap = threadInter.get();
            User user = (User) userMap.get("currentUser");
            map.put( "founder", user.getFounder());
            map.put( "password", MD5Utils.MD5("123456"));
            result = userDao.insert(map);
        }else{
            //编辑
            String now = DateUtil.getNowDate();
            map.remove( "create_time" );
            map.remove( "password" );
            map.remove( "last_login_time" );
            map.put("update_time", now);
            result =  userDao.update(map);
        }
        return ResultUtil.success(result);
    }

    @Override
    public Result<Object> delete(List<Map<String, Object>> list) throws Exception {
        int result = 0;
        for(Map<String,Object> mapFor : list){
            result += userDao.delete(mapFor);
        }
        return ResultUtil.success(result);
    }

    private void assertion(Map<String,Object> map)throws Exception{
        int result = userDao.queryTotalByField( map );
        if(result >= 1){
            throw new AppException("该邮箱已被使用，请输入其他邮箱");
        }
    }
}
