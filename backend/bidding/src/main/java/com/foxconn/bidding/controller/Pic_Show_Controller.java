package com.foxconn.bidding.controller;

import com.foxconn.bidding.mapper.UserMapper;
import com.foxconn.bidding.model.PART_DOC_FILE_bean;
import com.foxconn.bidding.model.ResultParam;
import com.foxconn.bidding.model.USER_INFO_bean;
import com.foxconn.bidding.service.FTPFileService;
import com.foxconn.bidding.util.Client_Real_IP_Util;
import com.foxconn.bidding.util.FTP_File_Util;
import com.foxconn.bidding.util.PDF_WaterMark_Util;
import com.foxconn.bidding.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@Controller
@RequestMapping("/pic_show")
public class Pic_Show_Controller {
    @Autowired
    private FTP_File_Util util;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FTPFileService svc;

    // 加載圖片(param中必須傳遞 file_save_path, file_save_name, file_origin_name)
    // html中用<a href="/api/pic_show/load?file_save_path=xxx&file_save_name=yyy&file_origin_name=zzz"></a>來實現下載
    // html中用<img src="/api/pic_show/load?file_save_path=xxx&file_save_name=yyy&file_origin_name=zzz"></img>來實現顯示
    @RequestMapping("/load")
    public void load(HttpServletRequest request, HttpServletResponse response) {
        String file_save_path = request.getParameter("file_save_path");
        String file_save_name = request.getParameter("file_save_name");
        String file_origin_name = request.getParameter("file_origin_name");

        int lastIndexOfDot = file_save_name.lastIndexOf(".");
        if(lastIndexOfDot == -1) {
            throw new RuntimeException("文件無格式後綴");
        } else {
            String suffix = file_save_name.substring(lastIndexOfDot);
            if(!".bmp".equals(suffix) && !".jpg".equals(suffix) && !".jpeg".equals(suffix)
                    && !".png".equals(suffix) && !".gif".equals(suffix)
                    && !".zip".equals(suffix)) {
                throw new RuntimeException("格式有誤，只能加載.bmp .jpg .jpeg .png .gif 格式的圖片和下載.zip 格式的文件");
            }
        }

        OutputStream os = null;
        InputStream is = null;
        try {
            ResultParam resultParam = util.fileDownload(file_save_path, file_save_name);
            if(resultParam.getT() != null) {
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file_origin_name, "UTF-8"));

                is = (InputStream) resultParam.getT();
                BufferedInputStream bis = new BufferedInputStream(is);
                os = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int length;

                while((length = bis.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                }
                is.close();
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 【04】文件下載
    @VerifyToken
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        Boolean flag = true;
        String file_save_path = request.getParameter("file_save_path");
        String file_save_name = request.getParameter("file_save_name");
        String file_origin_name = request.getParameter("file_origin_name");

        String user_pkid = (String) request.getAttribute("user_pkid");
        String ip = Client_Real_IP_Util.getRealIP(request);
        USER_INFO_bean user = userMapper.findUserById(user_pkid);
        String username = user.getUsername();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String waterMarkText = "下載用戶:" + username
                + "\n下載時間:" + dateStr
                + "\n下載IP:" + ip
                + "\n公司內部文件，注意保密！";

        PART_DOC_FILE_bean file_bean = new PART_DOC_FILE_bean();
        file_bean.setFile_origin_name(file_origin_name);
        file_bean.setFile_save_name(file_save_name);
        file_bean.setFile_save_path(file_save_path);
        file_bean.setCreate_user_pkid(user_pkid);
        file_bean.setCreate_user_ip(ip);

        OutputStream os = null;
        InputStream is = null;
        try {
            ResultParam resultParam = util.fileDownload(file_save_path, file_save_name);
            if(resultParam.getT() != null) {
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file_origin_name, "UTF-8"));

                is = (InputStream) resultParam.getT();
                BufferedInputStream bis = new BufferedInputStream(is);
                os = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int length;

                if(file_save_name.length() > 4) {
                    String suffix = file_save_name.substring(file_save_name.length() - 4);
                    if(".pdf".equals(suffix)) {
                        try {
                            os = PDF_WaterMark_Util.addWaterMark(is, os, waterMarkText);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RuntimeException("此pdf文件未添加水印,文件下載失敗");
                        }
                    }
                }

                while((length = bis.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                }
                is.close();
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        }

        if(flag) {
            ResultParam resultParam = svc.addFileDownloadRecord(file_bean);
            if(!"1".equals(resultParam.getCode())) {
                throw new RuntimeException("新增文件下載記錄失敗");
            }
        } else {
            throw new RuntimeException("下載文件失敗");
        }
    }
}
