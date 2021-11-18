package com.foxconn.bidsso.global;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.foxconn.bidsso.model.ResultDTO;
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

        // 要求前端傳往後台的回調函數名必須是jsonpFunc,不然異常無法返回
        return new JSONPObject("jsonpFunc", ResultDTO.of("30001", e.getMessage() != null ? e.getMessage() : "服務器發生錯誤"));
    }
}
