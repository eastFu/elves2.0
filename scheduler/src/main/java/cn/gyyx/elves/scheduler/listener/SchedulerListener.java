package cn.gyyx.elves.scheduler.listener;

import cn.gyyx.elves.core.config.ElvesConfig;
import cn.gyyx.elves.core.thrift.SchedulerService;
import cn.gyyx.elves.core.utils.ExceptionUtil;
import cn.gyyx.elves.scheduler.service.SchedulerServiceImpl;
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
 * @date 2019/4/29 18:01
 */
@Component
public class SchedulerListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = Logger.getLogger(SchedulerListener.class);

    @Resource
    private ElvesConfig elvesConfig;

    @Resource
    private SchedulerServiceImpl schedulerServiceImpl;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        new Thread(()->startSchedulerService()).start();
    }

    private void startSchedulerService(){
        try {
            LOG.info("start heartbeat service port :"+elvesConfig.getHeartbeatPort());
            TProcessor tprocessor = new SchedulerService.Processor<SchedulerService.Iface>(schedulerServiceImpl);
            TServerSocket serverTransport = new TServerSocket(elvesConfig.getSchedulerPort());
            TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
            ttpsArgs.processor(tprocessor);
            ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
            //线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
            TServer server = new TThreadPoolServer(ttpsArgs);
            server.serve();
        } catch (Exception e) {
            LOG.error("start scheduler thrift webservice error,msg:"+ ExceptionUtil.getStackTraceAsString(e));
        }
    }
}
