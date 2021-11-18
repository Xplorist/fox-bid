package com.foxconn.bidding.service;

import com.foxconn.bidding.model.PART_DOC_FILE_bean;
import com.foxconn.bidding.model.ResultParam;

public interface FTPFileService {
    // 新增文件下載記錄
    ResultParam addFileDownloadRecord(PART_DOC_FILE_bean param);
}
