package io.github.xxyopen.web.exception;

import io.github.xxyopen.model.resp.RestResult;
import io.github.xxyopen.model.resp.SysResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 通用的异常处理器
 * @author xiongxiaoyang
 * @version 1.0
 * @since 2020/5/23
 * */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * 处理后台数据校验异常
     * */
    @ExceptionHandler(BindException.class)
    public RestResult<Void> handlerBindException(BindException e){
        log.error(e.getMessage(),e);
        return RestResult.fail(SysResultCode.PARAM_ERROR);
    }

    /**
     * 处理业务异常
     * */
    @ExceptionHandler(BusinessException.class)
    public RestResult<Void> handlerBusinessException(BusinessException e){
        log.error(e.getMessage(),e);
        return RestResult.fail(e.getResultCode());
    }


    /**
     * 处理系统异常
     * */
    @ExceptionHandler(Exception.class)
    public RestResult<Void> handlerException(Exception e){
        log.error(e.getMessage(),e);
        return RestResult.error();
    }
}

