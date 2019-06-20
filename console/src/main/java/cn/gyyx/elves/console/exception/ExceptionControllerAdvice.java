package cn.gyyx.elves.console.exception;

import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


//@ControllerAdvice
public class ExceptionControllerAdvice {

	private final static Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	

	 @ExceptionHandler({Exception.class})
	 @ResponseBody
     public Result<Object> errorHandler(Exception ex) {
		 logger.error("ExceptionControllerAdvice" + ex.getMessage());
		 if(ex instanceof  AppException){
		 	AppException appEx = (AppException)ex;
		 	return ResultUtil.error(appEx.getCode(),ex.getMessage());
		 }
         return ResultUtil.error(ex.getMessage());
	  }
	
}
