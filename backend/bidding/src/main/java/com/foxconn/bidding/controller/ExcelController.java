package com.foxconn.bidding.controller;

import com.foxconn.bidding.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
@RequestMapping("/excel")
public class ExcelController {
    @Autowired
    private ExcelService svc;

    // 下載excel
    @RequestMapping("/download")
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) {
        String pkid = request.getParameter("pkid");

        try {
            Map<String, Object> map = svc.generateExcel(pkid, request);
            HSSFWorkbook workbook = (HSSFWorkbook)map.get("workbook");
            String title = (String)map.get("title");

            response.setContentType("application/msexcel;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + new String((title + ".xls").getBytes("utf-8"), "ISO8859-1"));
            OutputStream out = response.getOutputStream();

            workbook.write(out);
            out.flush();
            out.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
