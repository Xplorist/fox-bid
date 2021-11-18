package com.foxconn.bidding.controller;

import com.foxconn.bidding.model.FTP_File_Param;
import com.foxconn.bidding.model.ResultParam;
import com.foxconn.bidding.util.FTP_File_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ftp_file")
public class FTP_File_Controller {
    @Autowired
    private FTP_File_Util util;

    // 上傳文件
    @RequestMapping("/upload")
    public ResultParam upload(@RequestParam("file") MultipartFile file, @RequestParam("file_type") String file_type, HttpServletRequest request) {
        return util.fileUpload(file_type, file);
    }

    // 刪除文件
    @RequestMapping("/delete")
    public ResultParam delete(@RequestBody FTP_File_Param param, HttpServletRequest request) {
        return util.fileDelete(param);
    }
}
