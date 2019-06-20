package cn.gyyx.elves.console.service.impl;

import cn.gyyx.elves.console.dao.AuthDao;
import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.exception.AppException;
import cn.gyyx.elves.console.service.AuthService;
import cn.gyyx.elves.console.service.ThreadInter;
import cn.gyyx.elves.console.utils.DateUtil;
import cn.gyyx.elves.console.utils.ResultUtil;
import cn.gyyx.elves.core.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthDao authDao;

    @Resource
    private ThreadInter threadInter;

    @Override
    public Result<Object> queryListForPage(Map<String, Object> map) throws Exception {
        return ResultUtil.successForPage( authDao.queryListForPage( map ),map );
    }

    @Override
    public Result<Object> insertOrUpdate(Map<String, Object> map) throws Exception {
        // 断言
        assertion(map);

        int result = 0;
        String id = map.get("auth_id") == null || StringUtils.isEmpty(map.get("auth_id").toString())  || "-1".equals(map.get("auth_id").toString()) ? "" : map.get("auth_id").toString();
        if(StringUtils.isEmpty(id)){
            // 新增
            map.put("auth_id", SecurityUtil.getUniqueKey());
            map.put("auth_key", SecurityUtil.getUniqueKey());
            map.put("create_time", DateUtil.getNowDate());
            result = authDao.insert(map);
        }else{
            //编辑
            result =  authDao.update(map);
        }
        return ResultUtil.success(result);
    }

    @Transactional
    @Override
    public Result<Object> delete(List<Map<String, Object>> list) throws Exception {
        int result = 0;

        for(Map<String,Object> mapFor : list){
            result += authDao.delete(mapFor);
        }
        return ResultUtil.success(result);
    }


    private void assertion(Map<String,Object> map)throws Exception{
        int result = authDao.queryTotalByField(map);

        if(result >= 1){
            throw new AppException("该App已经被绑定，请重新选择App！");
        }
    }
}
