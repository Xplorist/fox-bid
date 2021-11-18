package com.foxconn.bidding.util;

import com.foxconn.bidding.model.FTP_File_Param;
import com.foxconn.bidding.model.ResultParam;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FTP_File_Util {
    @Autowired
    private FTP_Properties_Util util;

    /**
     * 文件上傳
     * @param type_base_path 類型根路徑（例如： user_pic為用戶頭像圖片類型的文件的根路徑）
     * @param file 文件
     * @return 響應結果
     */
    public ResultParam fileUpload(String type_base_path, MultipartFile file) {
        String file_origin_name = file.getOriginalFilename();
        InputStream input = null;
        try {
            input = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultParam("0", "上傳文件失敗", null);
        }

        // 將DWG文件轉換成PDF文件
        OutputStream output = null;
        int lastIndexOfDot = file_origin_name.lastIndexOf(".");
        String suffix = "";
        if(lastIndexOfDot != -1) {
            suffix = file_origin_name.substring(lastIndexOfDot);
            /*if(".dwg".equals(suffix)) {
                output = new ByteArrayOutputStream();
                try {
                    input = Out_to_In_stream_Util.convert_out_to_in(DWG_to_PDF_Util.convert_DWG_to_PDF(input, output));
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(file_origin_name + "上傳失敗，此DWG文件無法轉換成PDF文件，請檢查此DWG文件是否損壞");
                }
                String prefix = file_origin_name.substring(0, lastIndexOfDot);
                file_origin_name = prefix + ".pdf";
            }*/
        } else {
            throw new RuntimeException("上傳文件失敗,文件無後綴名");
        }

        String FTP_Address = util.getAddress();
        Integer FTP_Port = util.getPort();
        String FTP_Username = util.getUsername();
        String FTP_Password = util.getPassword();

        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");

        Date date = new Date();
        String year = new SimpleDateFormat("yyyy").format(date);
        String month = new SimpleDateFormat("MM").format(date);
        String day = new SimpleDateFormat("dd").format(date);
        String file_save_path = type_base_path + File.separator + year + File.separator + month + File.separator + day;// 取得配置文件路徑
        //String file_save_name = "(" + new SimpleDateFormat("HHmmssSSS").format(new Date()).toString() + ")" + file_origin_name;
        String file_save_name = UUID_Util.getUUID32() + suffix;

        try {
            int replay;
            ftp.connect(FTP_Address, FTP_Port);
            ftp.login(FTP_Username, FTP_Password);
            replay = ftp.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replay)) {
                ftp.disconnect();
                return new ResultParam("0", "上傳文件失敗", null);
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            String workingDirectory = ftp.printWorkingDirectory();
            String[] paths = file_save_path.split("\\\\");
            for(int i = 0; i < paths.length; i++) {
                ftp.makeDirectory(paths[i]);
                boolean changeWorkingDirectory_flag = ftp.changeWorkingDirectory(paths[i]);
                if(!changeWorkingDirectory_flag) {
                    return new ResultParam("0", "上傳文件失敗", workingDirectory);
                }
            }

            ftp.storeFile(file_save_name, input);
            input.close();
            ftp.logout();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultParam("0", "上傳文件失敗", null);
        } finally {
            try {
                if(ftp.isConnected()) {
                    ftp.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return new ResultParam("0", "上傳文件失敗", null);
            }
        }
        FTP_File_Param ftp_file_param = new FTP_File_Param();
        ftp_file_param.setFile_save_path(file_save_path);
        ftp_file_param.setFile_save_name(file_save_name);
        ftp_file_param.setFile_origin_name(file_origin_name);

        return new ResultParam("1", "上傳文件成功", ftp_file_param);
    }

    /**
     * 文件下載
     * @param file_save_path 文件保存路徑
     * @param file_save_name 文件保存名稱
     * @return 響應結果
     */
    public ResultParam fileDownload(String file_save_path, String file_save_name) {
        InputStream is = null;

        String FTP_Address = util.getAddress();
        Integer FTP_Port = util.getPort();
        String FTP_Username = util.getUsername();
        String FTP_Password = util.getPassword();

        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");

        try {
            int replay;
            ftp.connect(FTP_Address, FTP_Port);
            ftp.login(FTP_Username, FTP_Password);
            replay = ftp.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replay)) {
                ftp.disconnect();
                return new ResultParam("0", "下載文件失敗", null);
            }

            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            String[] paths = file_save_path.split("\\\\");
            for(int i = 0; i < paths.length; i++) {
                //ftp.makeDirectory(paths[i]);
                boolean changeWorkingDirectory_flag = ftp.changeWorkingDirectory(paths[i]);
                if(!changeWorkingDirectory_flag) {
                    return new ResultParam("0", "下載文件失敗", null);
                }
            }

            is = ftp.retrieveFileStream(file_save_name);
            //ftp.logout();// 必須把登出的代碼注釋掉，不然會導致大文件無法下載
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultParam("0", "下載文件失敗", null);
        } finally {
            try {
                if(ftp.isConnected()) {
                    ftp.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return new ResultParam("0", "下載文件失敗", null);
            }
        }

        return new ResultParam("1", "下載文件成功", is);
    }

    /**
     * 文件刪除
     * @param param param中必須包含 file_save_path, file_save_name
     * @return
     */
    public ResultParam fileDelete(FTP_File_Param param) {
        String file_save_path = null;
        String file_save_name = null;
        try {
            file_save_path = URLDecoder.decode(param.getFile_save_path(), "UTF-8");
            file_save_name = URLDecoder.decode(param.getFile_save_name(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String FTP_Address = util.getAddress();
        Integer FTP_Port = util.getPort();
        String FTP_Username = util.getUsername();
        String FTP_Password = util.getPassword();

        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");

        try {
            int replay;
            ftp.connect(FTP_Address, FTP_Port);
            ftp.login(FTP_Username, FTP_Password);
            replay = ftp.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replay)) {
                ftp.disconnect();
                return new ResultParam("0", "刪除文件失敗", null);
            }

            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            String file_url = file_save_path + File.separator + file_save_name;

            boolean deleteFile_flag = ftp.deleteFile(file_url);
            if(!deleteFile_flag) {
                return new ResultParam("0", "刪除文件失敗", null);
            }
            //ftp.logout();// 必須把登出的代碼注釋掉，不然會導致大文件無法下載
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultParam("0", "刪除文件失敗", null);
        } finally {
            try {
                if(ftp.isConnected()) {
                    ftp.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return new ResultParam("0", "刪除文件失敗", null);
            }
        }

        return new ResultParam("1", "刪除文件成功", null);
    }
}
