package cn.gyyx.elves.core.scheduler;

import cn.gyyx.elves.core.thrift.Instruct;
import cn.gyyx.elves.core.thrift.Reinstruct;

import java.util.List;

public interface SchedulerModuleService {

    List<Reinstruct> sendDataAsync(String ip, int timeout, List<Instruct> insList);

    Object sendDataSync(String ip,int timeout,Instruct ins);

}
