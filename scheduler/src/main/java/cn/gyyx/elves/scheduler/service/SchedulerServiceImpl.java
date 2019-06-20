package cn.gyyx.elves.scheduler.service;

import cn.gyyx.elves.core.config.ElvesAttribute;
import cn.gyyx.elves.core.config.ElvesConfig;
import cn.gyyx.elves.core.scheduler.AsyncProcessorService;
import cn.gyyx.elves.core.scheduler.SchedulerModuleService;
import cn.gyyx.elves.core.thrift.AgentService;
import cn.gyyx.elves.core.thrift.Instruct;
import cn.gyyx.elves.core.thrift.Reinstruct;
import cn.gyyx.elves.core.thrift.SchedulerService;
import cn.gyyx.elves.core.utils.ExceptionUtil;
import cn.gyyx.elves.core.utils.SpringContextUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SchedulerServiceImpl implements SchedulerService.Iface, SchedulerModuleService {

    private static final Logger LOG = Logger.getLogger(SchedulerServiceImpl.class);

    private AsyncProcessorService cronProcesserService;

    private AsyncProcessorService queueProcesserService;

    @Autowired
    private ElvesConfig elvesConfig;

    @Override
    public String dataTransport(Reinstruct reins) throws TException {
        //初始化service
        if(cronProcesserService==null||queueProcesserService==null){
            Map<String, AsyncProcessorService> classList = SpringContextUtil.getApplicationContext().getBeansOfType(AsyncProcessorService.class);
            if(classList==null||classList.size()==0){
                return null;
            }
            for(AsyncProcessorService service: classList.values()){
                if (service.getImplementClassType().equalsIgnoreCase(ElvesAttribute.JOB_TYPE_CRON)) {
                    cronProcesserService = service;
                }else if(service.getImplementClassType().equalsIgnoreCase(ElvesAttribute.JOB_TYPE_QUEUE)){
                    queueProcesserService=service;
                }else{
                    continue;
                }
            }
        }

        LOG.info("scheduler module reveive async message :"+ JSON.toJSONString(reins));

        String  type = reins.getIns().getType();
        if(type!=null&&type.equalsIgnoreCase(ElvesAttribute.JOB_TYPE_CRON)){
            //回调cron  存结果数据
            if(cronProcesserService!=null){
                cronProcesserService.agentAsyncMsgProcess(reins.getFlag(),reins.getCosttime(),reins.getIns(),reins.getResult());
            }
        }else if(type!=null&&type.equalsIgnoreCase(ElvesAttribute.JOB_TYPE_QUEUE)){
            //回调queue  存结果数据
            if(queueProcesserService!=null){
                queueProcesserService.agentAsyncMsgProcess(reins.getFlag(),reins.getCosttime(),reins.getIns(),reins.getResult());
            }
        }else{
            //不处理
        }
        return null;
    }

    @Override
    public List<Reinstruct> sendDataAsync(String ip, int timeout, List<Instruct> insList) {
        TSocket transport=null;
        try {
            transport = new TSocket(ip, elvesConfig.getClientPort());
            transport.setConnectTimeout(elvesConfig.getSchedulerConnectTimeout());
            transport.setSocketTimeout(timeout*1000);
            TProtocol protocol = new TBinaryProtocol(transport);
            AgentService.Client client = new AgentService.Client(protocol);
            transport.open();
            List<Reinstruct> resultList = client.instructionInvokeAsync(insList);
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

    @Override
    public Object sendDataSync(String ip, int timeout, Instruct ins) {
        TSocket transport=null;
        try {
            transport = new TSocket(ip, elvesConfig.getClientPort());
            transport.setConnectTimeout(elvesConfig.getSchedulerConnectTimeout());
            transport.setSocketTimeout(timeout*1000);
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
            LOG.error("sync send data to agent error : "+ ExceptionUtil.getStackTraceAsString(e));
            return null;
        } finally {
            if(null!=transport){
                transport.close();
            }
        }
    }


}
