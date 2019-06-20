package cn.gyyx.elves.cron.service.impl;

import cn.gyyx.elves.core.config.ElvesAttribute;
import cn.gyyx.elves.core.scheduler.AsyncProcessorService;
import cn.gyyx.elves.core.thrift.Instruct;
import cn.gyyx.elves.core.utils.DateUtils;
import cn.gyyx.elves.cron.dao.TaskResultDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AsyncProcessorServiceJobImpl implements AsyncProcessorService {

    private static final Logger logger = Logger.getLogger( AsyncProcessorServiceJobImpl.class);

    @Autowired
    private TaskResultDao taskResultDao;

    @Override
    public String getImplementClassType() {
        return  ElvesAttribute.JOB_TYPE_CRON;
    }

    @Override
    public void agentAsyncMsgProcess(int flagInt, int costtime, Instruct ins, String result) {
        Map<String,Object> map = new HashMap<>(  );

        String cron_id = ins.getId();
        String flag = "true";
        String error = "";
        int worker_flag = flagInt;
        String worker_message = result;
        int worker_costtime = costtime;
        String end_time = DateUtils.currentTimestamp2String( DateUtils.DEFAULT_DATETIME_FORMAT) ;

        map.put( "cron_id", cron_id);
        map.put( "flag", flag);
        map.put( "error", error);
        map.put( "worker_flag", worker_flag);
        map.put( "worker_message", worker_message);
        map.put( "worker_costtime", worker_costtime);
        map.put( "end_time", end_time);

        taskResultDao.delete(map);
        taskResultDao.insert( map );
    }
}
