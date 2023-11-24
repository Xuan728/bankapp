package com.example.springaaaaaaaa.exception;

import com.example.springaaaaaaaa.pojo.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.example.springaaaaaaaa.exception
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.isNotBlank(e.getMessage())?e.getMessage():"操作失败");
    }
}
