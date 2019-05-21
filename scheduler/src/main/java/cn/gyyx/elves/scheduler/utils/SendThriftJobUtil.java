package cn.gyyx.elves.scheduler.utils;

import cn.gyyx.elves.core.config.ElvesConfig;
import cn.gyyx.elves.core.utils.ExceptionUtil;
import cn.gyyx.elves.scheduler.thrift.AgentService;
import cn.gyyx.elves.scheduler.thrift.Instruct;
import cn.gyyx.elves.scheduler.thrift.Reinstruct;
import com.alibaba.fastjson.JSONObject;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
public class SendThriftJobUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SendThriftJobUtil.class);

    @Autowired
    private ElvesConfig elvesConfig;

    public List<Reinstruct> sendDataAsync(String ip,int timeout,List<Instruct> insList){
        TSocket transport=null;
        List<Reinstruct> resultList=null;
        try {
            transport = new TSocket(ip, elvesConfig.getClientPort());
            transport.setConnectTimeout(elvesConfig.getSchedulerConnectTimeout());
            transport.setSocketTimeout(timeout);
            TProtocol protocol = new TBinaryProtocol(transport);
            AgentService.Client client = new AgentService.Client(protocol);
            transport.open();
            resultList=client.instructionInvokeAsync(insList);
            LOG.info("异步指令转发到agent,返回结果："+resultList);
            return resultList;
        } catch (Exception e) {
            LOG.error("sync send data to agent error : "+ ExceptionUtil.getStackTraceAsString(e));
            return null;
        } finally {
            if(null!=transport){
                transport.close();
            }
        }
    }

    public Object sendDataSync(String ip,int timeout,Instruct ins){
        TSocket transport=null;
        try {
            transport = new TSocket(ip, elvesConfig.getClientPort());
            transport.setConnectTimeout(elvesConfig.getSchedulerConnectTimeout());
            transport.setSocketTimeout(timeout);
            TProtocol protocol = new TBinaryProtocol(transport);
            AgentService.Client client = new AgentService.Client(protocol);
            transport.open();
            Reinstruct back=client.instructionInvokeSync(ins);
            if(back!=null){
                JSONObject data = new JSONObject();
                data.put("id",ins.getId());
                data.put("worker_flag",back.getFlag());
                data.put("worker_message",  back.getResult()==null?"":back.getResult());
                data.put("worker_costtime", back.getCosttime());
                return data;
            }else{
                return null;
            }
        } catch (Exception e) {
            LOG.error("sync send data to agent error : "+ExceptionUtil.getStackTraceAsString(e));
            return null;
        } finally {
            if(null!=transport){
                transport.close();
            }
        }
    }
}
