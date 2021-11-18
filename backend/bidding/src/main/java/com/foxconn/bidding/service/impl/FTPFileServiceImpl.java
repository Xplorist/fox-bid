package com.foxconn.bidding.service.impl;

import com.foxconn.bidding.mapper.FTPFileMapper;
import com.foxconn.bidding.model.PART_DOC_FILE_bean;
import com.foxconn.bidding.model.ResultParam;
import com.foxconn.bidding.service.FTPFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FTPFileServiceImpl implements FTPFileService {
    @Autowired
    private FTPFileMapper mapper;

    // 新增文件下載記錄
    @Override
    public ResultParam addFileDownloadRecord(PART_DOC_FILE_bean param) {
        Integer f_addFileDownloadRecord = mapper.addFileDownloadRecord(param);
        if(f_addFileDownloadRecord <= 0) {
            throw new RuntimeException("新增文件下載記錄失敗");
        }

        return new ResultParam("1","新增文件下載記錄成功", null);
    }
}
