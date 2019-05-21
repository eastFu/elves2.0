package cn.gyyx.elves.api.service;

import cn.gyyx.elves.api.vo.ElvesResponse;

/**
 * @author East.F
 * @ClassName: ApiService
 * @Description: interface api
 * @date 2019/4/28 17:35
 */
public interface ApiService {

    ElvesResponse rtJob(String ip, String app, String func,String param, String timeout, String proxy);

    ElvesResponse createCronJob();

    ElvesResponse startCronJob();

    ElvesResponse stopCronJob();

    ElvesResponse delCronJob();

    ElvesResponse getCronJobDetail();
}
