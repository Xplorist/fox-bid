package com.foxconn.bidstatistics.exception;

import com.foxconn.bidstatistics.model.dto.ResultDTO;
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

        return ResultDTO.of("500", e.getMessage() != null ? e.getMessage() : "服務器發生錯誤");
    }
}
