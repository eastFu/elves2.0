package cn.gyyx.elves.scheduler.service;

import cn.gyyx.elves.scheduler.thrift.Reinstruct;
import cn.gyyx.elves.scheduler.thrift.SchedulerService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImpl implements SchedulerService.Iface{

    @Override
    public String dataTransport(Reinstruct reins) throws TException {
        return null;
    }
}
