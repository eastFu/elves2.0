package cn.gyyx.elves.api.service.impl;

import cn.gyyx.elves.core.api.ApiModuleService;
import cn.gyyx.elves.core.api.ElvesResponse;
import cn.gyyx.elves.core.config.ElvesAttribute;
import cn.gyyx.elves.core.config.ElvesConfig;
import cn.gyyx.elves.core.cron.CronModuleService;
import cn.gyyx.elves.core.enums.Errorcode;
import cn.gyyx.elves.core.queue.QueueModuleService;
import cn.gyyx.elves.core.scheduler.SchedulerModuleService;
import cn.gyyx.elves.core.thrift.Instruct;
import cn.gyyx.elves.core.utils.SecurityUtil;
import cn.gyyx.elves.core.utils.ValidateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author East.F
 * @ClassName: ApiServiceImpl
 * @Description: The interface implement for ApiModuleService
 * @date 2019/4/28 17:35
 */
@Service
public class ApiServiceImpl implements ApiModuleService {

    @Autowired
    private SchedulerModuleService schedulerModuleServiceImpl;

//    @Resource
    private CronModuleService cronModuleServiceImpl;

    @Autowired
    private QueueModuleService queueModuleServiceImpl;

    @Resource
    private ElvesConfig elvesConfig;

    @Override
    public ElvesResponse rtJob(String ip, String app, String func, String param, String timeout, String proxy) {

        if(StringUtils.isBlank(app)) {
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_3);
        }
        if(StringUtils.isBlank(func)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_7);
        }
        if(!ValidateUtil.validateIpAddress(ip)) {
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_1);
        }
        if(StringUtils.isNotBlank(param) && !ValidateUtil.validateJson(param)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_2);
        }
        if(StringUtils.isNotBlank(timeout) && !ValidateUtil.validateNumber(timeout)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_8);
        }

        int readTimeout = timeout =="" || timeout==null ? elvesConfig.getSchedulerSocketTimeout(): Integer.parseInt(timeout);

        Instruct ins = new Instruct(SecurityUtil.getUniqueKey(), ip, ElvesAttribute.JOB_TYPE_RT, ElvesAttribute.JOB_MODE_NP, app, func,
                param, readTimeout , proxy);

        Object data = schedulerModuleServiceImpl.sendDataSync(ip,readTimeout, ins);
        if(data==null){
            return ElvesResponse.newErrorInstance(Errorcode.ERR500);
        }
        return ElvesResponse.newSuccessInstance(data);
    }

    @Override
    public ElvesResponse createCronJob(String ip, String app, String func, String param, String timeout, String proxy,String mode,String rule) {
        if(StringUtils.isBlank(app)) {
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_3);
        }
        if(StringUtils.isBlank(func)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_7);
        }
        if(!ValidateUtil.validateIpAddress(ip)) {
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_1);
        }
        if(StringUtils.isNotBlank(param) && !ValidateUtil.validateJson(param)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_2);
        }
        if(StringUtils.isNotBlank(timeout) && !ValidateUtil.validateNumber(timeout)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_8);
        }

        boolean modeFlag = StringUtils.isBlank(mode) || (!ElvesAttribute.JOB_MODE_P.equalsIgnoreCase(mode)&&!ElvesAttribute.JOB_MODE_NP.equalsIgnoreCase(mode));
        if(modeFlag){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_4);
        }

        if(StringUtils.isNotBlank(rule) && !ValidateUtil.validataQuartzRule(rule)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_5);
        }

        int readTimeout = timeout =="" || timeout==null ? elvesConfig.getSchedulerSocketTimeout(): Integer.parseInt(timeout);

        String cronId = cronModuleServiceImpl.createCronJob(ip, app,func, param, readTimeout, proxy, rule);
        if(StringUtils.isBlank(cronId)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR500);
        }
        JSONObject data = new JSONObject(){{
            put("id",cronId);
        }};
        return ElvesResponse.newSuccessInstance(data);
    }

    @Override
    public ElvesResponse startCronJob(String cronId) {
        int length = 16;
        if(StringUtils.isBlank(cronId)||cronId.length()!= length ){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_9);
        }
        boolean flag = cronModuleServiceImpl.startCronJob(cronId);
        if(!flag){
            return ElvesResponse.newErrorInstance(Errorcode.ERR500);
        }
        return ElvesResponse.newSuccessInstance(null);
    }

    @Override
    public ElvesResponse stopCronJob(String cronId) {
        int length = 16;
        if(StringUtils.isBlank(cronId)||cronId.length()!= length ){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_9);
        }
        boolean flag = cronModuleServiceImpl.stopCronJob(cronId);
        if(!flag){
            return ElvesResponse.newErrorInstance(Errorcode.ERR500);
        }
        return ElvesResponse.newSuccessInstance(null);
    }

    @Override
    public ElvesResponse delCronJob(String cronId) {
        int length = 16;
        if(StringUtils.isBlank(cronId)||cronId.length()!= length ){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_9);
        }
        boolean flag = cronModuleServiceImpl.delCronJob(cronId);
        if(!flag){
            return ElvesResponse.newErrorInstance(Errorcode.ERR500);
        }
        return ElvesResponse.newSuccessInstance(null);
    }

    @Override
    public ElvesResponse getCronJobDetail(String cronId) {
        int length = 16;
        if(StringUtils.isBlank(cronId)||cronId.length()!= length ){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_9);
        }
        Map<String,Object> detail =  cronModuleServiceImpl.cronDetail(cronId);
        if(detail == null){
            return ElvesResponse.newErrorInstance(Errorcode.ERR500);
        }
        return ElvesResponse.newSuccessInstance(detail);
    }

    @Override
    public ElvesResponse createQueue(String app) {
        if(StringUtils.isBlank(app)) {
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_3);
        }
        String queueId = queueModuleServiceImpl.createQueue(app);
        if(StringUtils.isBlank(queueId)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR500);
        }
        JSONObject data = new JSONObject(){{
            put("id",queueId);
        }};
        return ElvesResponse.newSuccessInstance(data);
    }

    @Override
    public ElvesResponse addTaskQueue(String queueId, String ip, String func, String param, String timeout, String proxy, String mode, String dependTaskId) {
        int length = 16;
        if(StringUtils.isBlank(queueId)||queueId.length()!= length ){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_6);
        }
        if(StringUtils.isBlank(func)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_7);
        }
        if(!ValidateUtil.validateIpAddress(ip)) {
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_1);
        }
        if(StringUtils.isNotBlank(param) && !ValidateUtil.validateJson(param)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_2);
        }
        if(StringUtils.isNotBlank(timeout) && !ValidateUtil.validateNumber(timeout)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_8);
        }

        boolean modeFlag = StringUtils.isBlank(mode) || (!ElvesAttribute.JOB_MODE_P.equalsIgnoreCase(mode)&&!ElvesAttribute.JOB_MODE_NP.equalsIgnoreCase(mode));
        if(modeFlag){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_4);
        }

        int readTimeout = timeout =="" || timeout==null ? elvesConfig.getSchedulerSocketTimeout(): Integer.parseInt(timeout);

        String taskId = queueModuleServiceImpl.addTask(queueId,func,ip,mode,param,proxy,readTimeout,dependTaskId);
        if(StringUtils.isBlank(taskId)){
            return ElvesResponse.newErrorInstance(Errorcode.ERR500);
        }
        JSONObject data = new JSONObject(){{
            put("id",queueId);
        }};
        return ElvesResponse.newSuccessInstance(data);
    }

    @Override
    public ElvesResponse commitQueue(String queueId) {
        int length = 16;
        if(StringUtils.isBlank(queueId)||queueId.length()!= length ){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_6);
        }
        boolean flag = queueModuleServiceImpl.commitQueue(queueId);
        if(!flag){
            return ElvesResponse.newErrorInstance(Errorcode.ERR500);
        }
        return ElvesResponse.newSuccessInstance(null);
    }

    @Override
    public ElvesResponse stopQueue(String queueId) {
        int length = 16;
        if(StringUtils.isBlank(queueId)||queueId.length()!= length ){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_6);
        }
        boolean flag = queueModuleServiceImpl.stopQueue(queueId);
        if(!flag){
            return ElvesResponse.newErrorInstance(Errorcode.ERR500);
        }
        return ElvesResponse.newSuccessInstance(null);
    }

    @Override
    public ElvesResponse resultQueue(String queueId) {
        int length = 16;
        if(StringUtils.isBlank(queueId)||queueId.length()!= length ){
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_6);
        }
        Map<String,Object> result = queueModuleServiceImpl.queueResult(queueId);
        if(result==null){
            return ElvesResponse.newErrorInstance(Errorcode.ERR500);
        }
        return ElvesResponse.newSuccessInstance(result);
    }

    @Override
    public ElvesResponse createQksQueue(String app,String ip,String func, String param, String timeout, String proxy,String mode){
        //先创建queue
        ElvesResponse createQueueFlag = createQueue(app);
        if(ElvesResponse.SUCCESS_FLAG.equals(createQueueFlag.getFlag())){
            //创建队列任务
            Map<String,String> queueData = JSON.parseObject(createQueueFlag.getResult().toString(),new TypeReference<Map<String, String>>(){});
            String queueId = queueData==null ? null:queueData.get("id");
            if(queueId==null){
                return ElvesResponse.newErrorInstance(Errorcode.ERR500);
            }
            ElvesResponse addTaskFlag =addTaskQueue(queueId,ip,func,param,timeout,proxy,mode,"");
            if(ElvesResponse.SUCCESS_FLAG.equals(addTaskFlag.getFlag())){
                //提交队列
                Map<String,String> taskData = JSON.parseObject(addTaskFlag.getResult().toString(),new TypeReference<Map<String, String>>(){});
                String taskId = taskData==null ? null:taskData.get("id");
                if(taskId==null){
                    return ElvesResponse.newErrorInstance(Errorcode.ERR500);
                }
                ElvesResponse commitFlag = commitQueue(queueId);
                if(ElvesResponse.SUCCESS_FLAG.equals(commitFlag.getFlag())){
                    //成功返回
                    JSONObject back = new JSONObject(){{
                        put("id",queueId);
                        put("task_id",taskId);
                    }};
                    return ElvesResponse.newSuccessInstance(back);
                }
            }
        }
        return ElvesResponse.newErrorInstance(Errorcode.ERR500);
    }


    @Override
    public ElvesResponse appInfo(String authId) {
        int length = 16;
        if(StringUtils.isBlank(authId)||authId.length()!= length ){
            return ElvesResponse.newErrorInstance(Errorcode.ERR401_2);
        }
        Map<String,Object> appInfo = null;
        return ElvesResponse.newSuccessInstance(appInfo);
    }

    @Override
    public ElvesResponse agentsInfo(String authId) {
        int length = 16;
        if(StringUtils.isBlank(authId)||authId.length()!= length ){
            return ElvesResponse.newErrorInstance(Errorcode.ERR401_2);
        }
        String[] ips = null;
        return ElvesResponse.newSuccessInstance(ips);
    }

    @Override
    public ElvesResponse agentDetailInfo(String ip) {
        if(!ValidateUtil.validateIpAddress(ip)) {
            return ElvesResponse.newErrorInstance(Errorcode.ERR403_1);
        }
        Map<String,Object> detail = null;
        return ElvesResponse.newSuccessInstance(detail);
    }
}
