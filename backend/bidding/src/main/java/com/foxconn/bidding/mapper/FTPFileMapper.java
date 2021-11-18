package com.foxconn.bidding.mapper;

import com.foxconn.bidding.model.PART_DOC_FILE_bean;
import org.springframework.stereotype.Repository;

@Repository
public interface FTPFileMapper {
    // 新增文件下載記錄
    Integer addFileDownloadRecord(PART_DOC_FILE_bean param);
}
