package com.foxconn.bidding.interceptor;

import com.foxconn.bidding.model.ResultParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        e.printStackTrace();
        log.error("【系統異常:】", e);

        return ResultParam.of("30001", e.getMessage() != null ? e.getMessage() : "服務器發生錯誤");
    }
}
