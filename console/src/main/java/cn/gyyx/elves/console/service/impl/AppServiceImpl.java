package cn.gyyx.elves.console.service.impl;

import cn.gyyx.elves.console.dao.AppDao;
import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.domain.User;
import cn.gyyx.elves.console.exception.AppException;
import cn.gyyx.elves.console.service.AppService;
import cn.gyyx.elves.console.service.ThreadInter;
import cn.gyyx.elves.console.utils.DateUtil;
import cn.gyyx.elves.console.utils.MapUtil;
import cn.gyyx.elves.console.utils.ResultUtil;
import cn.gyyx.elves.core.heartbeat.HeartbeatModuleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AppServiceImpl implements AppService{

    @Resource
    private AppDao appDao;

    @Resource
    private ThreadInter threadInter;

    @Autowired
    private HeartbeatModuleService heartbeatModuleService;


    @Override
    public Result<Object> queryListForPage(Map<String, Object> map) throws Exception {
        List<Map<String,Object>> list = appDao.queryListForPage( map );
        return ResultUtil.successForPage(list, map);
    }

    @Override
    public Result<Object> insertOrUpdate(Map<String, Object> map) throws Exception {

        // 断言
        assertion(map);

        int result = 0;
        String id = map.get("app_id") == null || StringUtils.isEmpty(map.get("app_id").toString())  || "-1".equals(map.get("app_id").toString()) ? "" : map.get("app_id").toString();
        if(StringUtils.isEmpty(id)){
            // 新增
            String now = DateUtil.getNowDate();
            map.put("create_time", now);
            map.put("update_time", now);

            Map<String,Object> userMap = threadInter.get();
            User user = (User) userMap.get("currentUser");
            map.put( "founder", user.getFounder());
            result = appDao.insert(map);
        }else{
            //编辑
            result =  appDao.update(map);
        }
        return ResultUtil.success(result);
    }

    @Transactional
    @Override
    public Result<Object> delete(List<Map<String,Object>> list) throws Exception {
        int result = 0;
        for(Map<String,Object> mapFor : list){
            result += appDao.delete( mapFor );
        }
        return ResultUtil.success(result);
    }



    private void assertion(Map<String,Object> map)throws Exception{
        int result = appDao.queryTotalByField(map);

        if(result >= 1){
            throw new AppException("指令已存在");
        }
    }

    @Override
    public Result<Object> queryList(Map<String, Object> map) throws Exception {
        return ResultUtil.success( appDao.queryList(map) );
    }

    @Override
    public Result<Object> startAppVersion(Map<String, Object> map) throws Exception {
        return ResultUtil.success( appDao.startAppVersion(map) );
    }

    @Override
    public List<Map<String, Object>> queryAppInfoToHeartbeat()throws Exception {
        return appDao.queryAppInfoToHeartbeat();
    }


    @Override
    public Result<Object> queryAuthAppList(Map<String, Object> map)throws Exception {

        //全部机器
        List<Map<String,Object>> listAll = heartbeatModuleService.agentList();

        // 已授权机器
        List<Map<String,Object>> listAuth = appDao.queryAuthAppList(map);

        //未授权机器  求差集  遍历移除
        Map<String,Object> mapAll = MapUtil.listToMapByKey( listAll,"ip" );
        Map<String,Object> mapAuth = MapUtil.listToMapByKey( listAuth,"ip" );



        Iterator<Map.Entry<String,Object>> itAll = mapAll.entrySet().iterator();
        while (itAll.hasNext()){
            Map.Entry<String,Object> entryAll = itAll.next();
            String keyAll = entryAll.getKey();

            Map<String,Object> mapAuthFor = mapAuth.get( keyAll ) == null ? null : (Map<String,Object>)mapAuth.get( keyAll );

            if(mapAuthFor != null){
                mapAll.remove( keyAll );

                //合并 心跳时间
                Map<String,Object> objAll = (Map<String, Object>) entryAll.getValue();
                String last_hb_time = objAll.get("last_hb_time").toString();
                mapAuthFor.put( "last_hb_time",last_hb_time );
            }
        }

        listAll = MapUtil.mapToList( mapAll );
        listAuth = MapUtil.mapToList( mapAuth );

        Map<String,Object> mapResult = new HashMap<>(  );
        mapResult.put( "noAuth", listAll);
        mapResult.put( "Auth", listAuth);
        return ResultUtil.success( mapResult);
    }



}
