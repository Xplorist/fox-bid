package com.foxconn.bidding.service.impl;

import com.foxconn.bidding.mapper.BillMapper;
import com.foxconn.bidding.mapper.UserMapper;
import com.foxconn.bidding.model.BILL_bean;
import com.foxconn.bidding.model.GIVE_PRICE_MSTR_bean;
import com.foxconn.bidding.model.USER_INFO_bean;
import com.foxconn.bidding.service.ExcelService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BillMapper billMapper;

    @Override
    public Map<String, Object> generateExcel(String pkid, HttpServletRequest request) {
        GIVE_PRICE_MSTR_bean give_price = billMapper.query_give_price_by_pkid(pkid);
        String bill_pkid = give_price.getBill_pkid();
        BILL_bean bill_bean = billMapper.query_bill_by_pkid(bill_pkid);
        String bill_no = bill_bean.getBill_no();
        String recv_user_pkid = give_price.getRecv_user_pkid();
        USER_INFO_bean recv_user = userMapper.findUserById(recv_user_pkid);
        String dept_name = recv_user.getDept_name();
        String username = recv_user.getUsername();
        String title = "訂單【" + bill_no + "】接單方【" + dept_name + "-" + username + "】的報價單";

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        HSSFRow row_0 = sheet.createRow(0);
        HSSFCell cell_0_0 = row_0.createCell(0);
        cell_0_0.setCellValue(new HSSFRichTextString("test"));
        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                0, //last row  (0-based)
                0, //first column (0-based)
                13  //last column  (0-based)
        ));
        HSSFFont font_0 = workbook.createFont();
        font_0.setFontHeightInPoints((short) 20);
        font_0.setBold(true);
        HSSFCellStyle style_0 = workbook.createCellStyle();
        style_0.setFont(font_0);
//        style_0.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
//        style_0.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style_0.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style_0.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style_0.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cell_0_0.setCellStyle(style_0);

        HSSFCellStyle style_menu = workbook.createCellStyle();
        HSSFFont font_menu = workbook.createFont();
//        font_menu.setColor(HSSFColor.BLACK.index);
        font_menu.setBold(true);
        style_menu.setFont(font_menu);
//        style_menu.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style_menu.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style_menu.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style_menu.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //cell_0_0.setCellStyle(style_menu);

        HSSFRow row_1 = sheet.createRow(1);
        HSSFCell cell_1_0 = row_1.createCell(0);
        HSSFCell cell_1_1 = row_1.createCell(1);
        HSSFCell cell_1_2 = row_1.createCell(2);
        HSSFCell cell_1_3 = row_1.createCell(3);
        HSSFCell cell_1_4 = row_1.createCell(4);
        HSSFCell cell_1_5 = row_1.createCell(5);
        HSSFCell cell_1_6 = row_1.createCell(6);
        HSSFCell cell_1_7 = row_1.createCell(7);
        HSSFCell cell_1_8 = row_1.createCell(8);
        HSSFCell cell_1_9 = row_1.createCell(9);
        HSSFCell cell_1_10 = row_1.createCell(10);
        HSSFCell cell_1_11 = row_1.createCell(11);
        HSSFCell cell_1_12 = row_1.createCell(12);
        HSSFCell cell_1_13 = row_1.createCell(13);
        cell_1_0.setCellValue(new HSSFRichTextString("產品名稱    "));
//        cell_1_1.setCellValue(new HSSFRichTextString(product_name));
        cell_1_2.setCellValue(new HSSFRichTextString("客戶    "));
//        cell_1_3.setCellValue(new HSSFRichTextString(customer));
        cell_1_4.setCellValue(new HSSFRichTextString("鴻海料號    "));
//        cell_1_5.setCellValue(new HSSFRichTextString(hh_pn));
        cell_1_6.setCellValue(new HSSFRichTextString("鴻海版次    "));
//        cell_1_7.setCellValue(new HSSFRichTextString(hh_ver));
        cell_1_0.setCellStyle(style_menu);
        cell_1_1.setCellStyle(style_menu);
        cell_1_2.setCellStyle(style_menu);
        cell_1_3.setCellStyle(style_menu);
        cell_1_4.setCellStyle(style_menu);
        cell_1_5.setCellStyle(style_menu);
        cell_1_6.setCellStyle(style_menu);
        cell_1_7.setCellStyle(style_menu);
        cell_1_8.setCellStyle(style_menu);
        cell_1_9.setCellStyle(style_menu);
        cell_1_10.setCellStyle(style_menu);
        cell_1_11.setCellStyle(style_menu);
        cell_1_12.setCellStyle(style_menu);
        cell_1_13.setCellStyle(style_menu);

        HSSFRow row_2 = sheet.createRow(2);
        HSSFCell cell_2_0 = row_2.createCell(0);
        HSSFCell cell_2_1 = row_2.createCell(1);
        HSSFCell cell_2_2 = row_2.createCell(2);
        HSSFCell cell_2_3 = row_2.createCell(3);
        HSSFCell cell_2_4 = row_2.createCell(4);
        HSSFCell cell_2_5 = row_2.createCell(5);
        HSSFCell cell_2_6 = row_2.createCell(6);
        HSSFCell cell_2_7 = row_2.createCell(7);
        HSSFCell cell_2_8 = row_2.createCell(8);
        HSSFCell cell_2_9 = row_2.createCell(9);
        HSSFCell cell_2_10 = row_2.createCell(10);
        HSSFCell cell_2_11 = row_2.createCell(11);
        HSSFCell cell_2_12 = row_2.createCell(12);
        HSSFCell cell_2_13 = row_2.createCell(13);
        cell_2_0.setCellValue(new HSSFRichTextString("客戶料號    "));
//        cell_2_1.setCellValue(new HSSFRichTextString(customer_pn));
        cell_2_2.setCellValue(new HSSFRichTextString("客戶版次    "));
//        cell_2_3.setCellValue(new HSSFRichTextString(customer_ver));
        cell_2_4.setCellValue(new HSSFRichTextString("制表日期    "));
//        cell_2_5.setCellValue(new HSSFRichTextString(excel_date));
        cell_2_6.setCellValue(new HSSFRichTextString("備註    "));
//        cell_2_7.setCellValue(new HSSFRichTextString(memo));
        cell_2_0.setCellStyle(style_menu);
        cell_2_1.setCellStyle(style_menu);
        cell_2_2.setCellStyle(style_menu);
        cell_2_3.setCellStyle(style_menu);
        cell_2_4.setCellStyle(style_menu);
        cell_2_5.setCellStyle(style_menu);
        cell_2_6.setCellStyle(style_menu);
        cell_2_7.setCellStyle(style_menu);
        cell_2_8.setCellStyle(style_menu);
        cell_2_9.setCellStyle(style_menu);
        cell_2_10.setCellStyle(style_menu);
        cell_2_11.setCellStyle(style_menu);
        cell_2_12.setCellStyle(style_menu);
        cell_2_13.setCellStyle(style_menu);

        HSSFRow row0 = sheet.createRow(3);
        HSSFCell cell0 = row0.createCell(0);
        HSSFCell cell1 = row0.createCell(1);
        HSSFCell cell2 = row0.createCell(2);
        HSSFCell cell3 = row0.createCell(3);
        HSSFCell cell4 = row0.createCell(4);
        HSSFCell cell5 = row0.createCell(5);
        HSSFCell cell6 = row0.createCell(6);
        HSSFCell cell7 = row0.createCell(7);
        HSSFCell cell8 = row0.createCell(8);
        HSSFCell cell9 = row0.createCell(9);
        HSSFCell cell10 = row0.createCell(10);
        HSSFCell cell11 = row0.createCell(11);
        HSSFCell cell12 = row0.createCell(12);
        HSSFCell cell13 = row0.createCell(13);
        cell0.setCellValue(new HSSFRichTextString("項次    "));
        cell1.setCellValue(new HSSFRichTextString("組立階次    "));
        cell2.setCellValue(new HSSFRichTextString("鴻海料號    "));
        cell3.setCellValue(new HSSFRichTextString("鴻海版次    "));
        cell4.setCellValue(new HSSFRichTextString("客戶料號    "));
        cell5.setCellValue(new HSSFRichTextString("客戶版次    "));
        cell6.setCellValue(new HSSFRichTextString("用量    "));
        cell7.setCellValue(new HSSFRichTextString("單位    "));
        cell8.setCellValue(new HSSFRichTextString("單重    "));
        cell9.setCellValue(new HSSFRichTextString("類別    "));
        cell10.setCellValue(new HSSFRichTextString("英文名稱    "));
        cell11.setCellValue(new HSSFRichTextString("中文名稱    "));
        cell12.setCellValue(new HSSFRichTextString("製程DRI    "));
        cell13.setCellValue(new HSSFRichTextString("備註    "));

        HSSFCellStyle style = workbook.createCellStyle();
        //style.setFillForegroundColor(HSSFColor.LIME.index);
//        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//        style.setFillPattern((short)1);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        HSSFFont font = workbook.createFont();
//        font.setColor(HSSFColor.BLUE.index);
        font.setBold(true);
        style.setFont(font);

        cell0.setCellStyle(style);
        cell1.setCellStyle(style);
        cell2.setCellStyle(style);
        cell3.setCellStyle(style);
        cell4.setCellStyle(style);
        cell5.setCellStyle(style);
        cell6.setCellStyle(style);
        cell7.setCellStyle(style);
        cell8.setCellStyle(style);
        cell9.setCellStyle(style);
        cell10.setCellStyle(style);
        cell11.setCellStyle(style);
        cell12.setCellStyle(style);
        cell13.setCellStyle(style);


        Map<String, Object> map = new HashMap<>();
        map.put("workbook", workbook);
        map.put("title", title);

        return map;
    }
}
