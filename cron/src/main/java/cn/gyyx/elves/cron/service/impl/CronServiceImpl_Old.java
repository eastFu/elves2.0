package cn.gyyx.elves.cron.service.impl;

//@Service
public class CronServiceImpl_Old /*implements CronService*/ {

	/*private static final Logger logger = Logger.getLogger( CronServiceImpl_Old.class);

	@Autowired
	private SchedulerInter schedulerInter;

	@Autowired
	private QrtzJobDetailsDao qrtzJobDetailsDao;

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private TaskResultDao taskResultDao;*/

	/*@Override
	@Transactional
	public Result<Object> createJob(Map<String, Object> map)throws Exception{
		logger.info("cron module reveive createCron job ,params : "+map);
		String cronId = SecurityUtil.getUniqueKey();
		Map<String, Object> rs = new HashMap<String, Object>();
		try{

			// 内存中的 定时任务
			map.put( "id" , cronId);
			ResultEnum result = schedulerInter.createJob(map, ScheduledJob.class);

			if(result.getCode() == 0){
				rs.put("id", cronId);
				return ResultUtil.success(rs);
			}else{
				return ResultUtil.error( result );
			}

		}catch(Exception e){
			logger.error("create cron fail, error : " + e.getMessage());
			e.printStackTrace(  );
			return ResultUtil.exception(e);
		}

	}


	@Override
	public Result<Object> pauseJob(Map<String, Object> params)throws Exception{
		logger.info("cron module reveive stop cron job ,params:"+params);
		Result<Object> result = preDo(params);
		if(result.getCode() != 0){
			return result;
		}
		Map<String,Object> map = (Map<String, Object>) result.getData();
		try{
			schedulerInter.pauseJob(map);
			return successAfterDo(map);
		}catch(Exception e){
			logger.error("stop cron fail, error : "+ e.getMessage());
			e.printStackTrace(  );
			return ResultUtil.exception(e);
		}
	}

	@Override
	public Result<Object> resumeJob(Map<String, Object> params) throws Exception {
		logger.info("cron module reveive stop cron job ,params:"+params);
		Result<Object> result = preDo(params);
		if(result.getCode() != 0){
			return result;
		}
		Map<String,Object> map = (Map<String, Object>) result.getData();
		try{
			schedulerInter.resumeJob(map);
			return successAfterDo(map);
		}catch(Exception e){
			logger.error("stop cron fail, error : "+ e.getMessage());
			e.printStackTrace(  );
			return ResultUtil.exception(e);
		}
	}

	@Override
	public Result<Object> deleteJob(Map<String, Object> params)throws Exception{
		logger.info("cron module reveive delete cron job ,params:"+params);
		Result<Object> result = preDo(params);
		if(result.getCode() != 0){
			return result;
		}
		Map<String,Object> map = (Map<String, Object>) result.getData();
		try{
			schedulerInter.deleteJob(map);
			return successAfterDo(map);
		}catch(Exception e){
			logger.error("delete cron fail, error : " + e.getMessage());
			return ResultUtil.exception(e);
		}
	}
	
	@Override
	public Result<Object> cronList(Map<String, Object> map)throws Exception{
		logger.info("cron module reveive cronList job ,params:"+map);
		String ip = map.get("ip")==null?"":map.get("ip").toString().trim();

		if(StringUtils.isEmpty( ip )){
			return ResultUtil.error(ResultEnum.ERROR_CRON_PARAM_NO_IP);
		}
		List<Map<String,Object>> resultList = new LinkedList<>();

		try{
			List<Map<String,Object>> list = qrtzJobDetailsDao.queryByJobIp(map);

			for(Map<String,Object> mapFor : list){
				Map<String,Object> mapResult = getJobDetailByJobKey(mapFor);
				resultList.add( mapResult );
			}

			return ResultUtil.success(resultList);
		}catch(Exception e){
			logger.error("query cronList fail, error : " + e.getMessage());
			e.printStackTrace(  );
			return ResultUtil.exception(e);
		}
	}
	
	@Override
	public Result<Object> cronDetail(Map<String, Object> map)throws Exception {
		logger.info("cron module reveive cronDetail job ,params:" + map);
		String id = map.get("id")==null?"":map.get("id").toString().trim();

		Map<String,Object> mapResult = new HashMap<>(  );

		try {
			Result<Object> result = preDo(map);
			if(result.getCode() != 0){
				return result;
			}
			// 数据库中表的一条数据
			Map<String,Object> cronDetailDao = (Map<String, Object>) result.getData();

			//反序列化数据
			Map<String, Object> cronDeObj = getJobDetailByJobKey(cronDetailDao);
			mapResult.putAll( cronDeObj );

			String job_name = cronDetailDao.get("JOB_NAME").toString();
			// 该任务的结果
			Map<String, Object> cronResult = taskResultDao.queryByCronId(job_name);

			if(null == cronResult){
				mapResult.put("last_exec_time","");
				mapResult.put("last_exec_result",new HashMap<String,Object>());
			}else{
				Map<String,Object> workerData = new HashMap<>();
				workerData.put("worker_flag", cronResult.get("worker_flag"));
				workerData.put("worker_message", cronResult.get("worker_message"));
				workerData.put("worker_costtime", cronResult.get("worker_costtime"));

				Map<String,Object> data = new HashMap<String,Object>();
				data.put("flag", cronResult.get("flag"));
				data.put("error",  cronResult.get("error"));
				data.put("result", workerData);


				String endTime = cronResult.get("end_time") == null || StringUtils.isEmpty( cronResult.get("end_time").toString() ) ? "" : cronResult.get("end_time").toString();
				String last_exec_time = StringUtils.isEmpty( endTime ) ? "" :  endTime.substring(0,endTime.length()-2);

				mapResult.put("last_exec_time", last_exec_time);
				mapResult.put("last_exec_result",data);
			}

			return ResultUtil.success(mapResult);

		} catch (Exception e) {
			logger.error("query cronDetail fail, error : " + e.getMessage());
			return ResultUtil.exception( e );
		}
	}

	@Override
	public Result<Object> clearAll() throws Exception{
		try {
			schedulerInter.clearAll();
			return ResultUtil.success();
		}catch (Exception e){
			logger.error("clearAll fail, error : " + e.getMessage());
			return ResultUtil.exception( e );
		}

	}


	private Result<Object> preDo(Map<String,Object> map){
		String id = map.get("id")==null?"":map.get("id").toString().trim();

		if(StringUtils.isEmpty( id )){
			return ResultUtil.error(ResultEnum.ERROR_CRON_PARAM_NO_ID);
		}
		Map<String,Object> cron = qrtzJobDetailsDao.queryByJobName( map );
		if(null==cron){
			return ResultUtil.error( ResultEnum.ERROR_CRON_NOT_EXISTED );
		}

		return ResultUtil.success(cron);
	}

	private Result<Object> successAfterDo(Map<String,Object> map){
		Map<String,Object> result = new HashMap<>();
		result.put( "id",map.get("JOB_NAME") );
		return ResultUtil.success(result);
	}

	private Map<String,Object> getJobDetailByJobKey(Map<String,Object> map)throws Exception{

		if(map.get("JOB_NAME") == null || StringUtils.isEmpty( map.get("JOB_NAME").toString() )){
			throw new NullPointerException(  );
		}

		if(map.get("JOB_GROUP") == null || StringUtils.isEmpty( map.get("JOB_GROUP").toString() )){
			throw new NullPointerException(  );
		}

		String job_name = map.get("JOB_NAME").toString();
		String job_group = map.get("JOB_GROUP").toString();

		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		JobKey jobKey = new JobKey(job_name ,job_group );

		JobDetail jobDetail = scheduler.getJobDetail( jobKey);

		JobDataMap jobDataMap = jobDetail.getJobDataMap();

		Map<String,Object> mapResult = (Map<String, Object>) jobDataMap.get("scheduleJob");

		return mapResult;
	}*/

}
