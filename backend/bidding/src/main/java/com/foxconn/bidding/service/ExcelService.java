package com.foxconn.bidding.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ExcelService {
    // 生成excel
    Map<String, Object> generateExcel(String pkid, HttpServletRequest request);
}
