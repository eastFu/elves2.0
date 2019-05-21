package cn.gyyx.elves.api.exception;

import cn.gyyx.elves.api.vo.ElvesResponse;
import cn.gyyx.elves.core.enums.Errorcode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author East.F
 * @ClassName: ExceptionHandler
 * @Description: The exception handler for elves
 * @date 2019/5/21 11:11
 */
@ControllerAdvice
public class ElvesExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ElvesExceptionHandler.class);

    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ElvesResponse handlerException(Exception e){
        logger.error(e.getMessage(),e);
        return ElvesResponse.newErrorInstance(Errorcode.ERR401);
    }
}
