package cn.gyyx.elves.api.service.impl;

import cn.gyyx.elves.api.service.ApiService;
import cn.gyyx.elves.api.vo.ElvesResponse;
import cn.gyyx.elves.core.config.ElvesAttribute;
import cn.gyyx.elves.core.config.ElvesConfig;
import cn.gyyx.elves.core.enums.Errorcode;
import cn.gyyx.elves.core.utils.SecurityUtil;
import cn.gyyx.elves.core.utils.ValidateUtil;
import cn.gyyx.elves.scheduler.thrift.Instruct;
import cn.gyyx.elves.scheduler.utils.SendThriftJobUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author East.F
 * @ClassName: ApiServiceImpl
 * @Description: The interface implement for ApiService
 * @date 2019/4/28 17:35
 */
@Service
public class ApiServiceImpl implements ApiService {

    @Resource
    private SendThriftJobUtil sendThriftJobUtil;

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
        Instruct ins = new Instruct(SecurityUtil.getUniqueKey(), ip, ElvesAttribute.JOB_TYPE_RT, ElvesAttribute.JOB_MODE_NP, app, func, param, readTimeout , proxy);
        Object data = sendThriftJobUtil.sendDataSync(ip,readTimeout, ins);
        return ElvesResponse.newSuccessInstance(data);
    }

    @Override
    public ElvesResponse createCronJob() {
        return null;
    }

    @Override
    public ElvesResponse startCronJob() {
        return null;
    }

    @Override
    public ElvesResponse stopCronJob() {
        return null;
    }

    @Override
    public ElvesResponse delCronJob() {
        return null;
    }

    @Override
    public ElvesResponse getCronJobDetail() {
        return null;
    }
}
