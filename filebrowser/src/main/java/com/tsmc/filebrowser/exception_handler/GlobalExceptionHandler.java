package com.tsmc.filebrowser.exception_handler;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsmc.filebrowser.domain.ResultBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * Null Value Exception
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("Null point exception!, error: ",e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }
    
    /**
     * File cannot be found
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =FileNotFoundException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, FileNotFoundException e){
        logger.error("Resource not found!, error: ",e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }
    
    /**
     * IO Exception
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =IOException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, IOException e){
        logger.error("IOException!, error: ",e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }


    /**
        * Unknown Exception
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("Unknown Exception!, error: ",e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }
}
