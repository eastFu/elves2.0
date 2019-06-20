package cn.gyyx.elves.heartbeat.listener;

import cn.gyyx.elves.core.config.ElvesConfig;
import cn.gyyx.elves.core.thrift.HeartbeatService;
import cn.gyyx.elves.core.utils.ExceptionUtil;
import cn.gyyx.elves.heartbeat.service.HeartbeatModuleServiceImpl;
import org.apache.log4j.Logger;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author East.F
 * @ClassName: ElvesAuthFilter
 * @Description: start scheduler service
 * @date 2019年5月29日 17:39:14
 */
@Component
public class HeartbeatListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final org.apache.log4j.Logger LOG = Logger.getLogger(HeartbeatListener.class);

    @Resource
    private ElvesConfig elvesConfig;

    @Resource
    private HeartbeatModuleServiceImpl heartbeatModuleServiceImpl;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        new Thread(()->startSchedulerService()).start();
    }

    private void startSchedulerService(){
        try {
            LOG.debug("start heartbeat service port :"+elvesConfig.getHeartbeatPort());
            TProcessor tprocessor = new HeartbeatService.Processor<HeartbeatService.Iface>(heartbeatModuleServiceImpl);
            TServerSocket serverTransport = new TServerSocket(elvesConfig.getHeartbeatPort());
            TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
            ttpsArgs.processor(tprocessor);
            ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
            //线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
            TServer server = new TThreadPoolServer(ttpsArgs);
            server.serve();
        } catch (Exception e) {
            LOG.error("start heartbeat thrift service error,msg:"+ ExceptionUtil.getStackTraceAsString(e));
        }
    }

}
