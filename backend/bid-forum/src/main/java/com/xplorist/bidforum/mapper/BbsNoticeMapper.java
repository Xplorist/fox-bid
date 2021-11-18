package com.xplorist.bidforum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xplorist.bidforum.model.BbsNoticeDO;
import com.xplorist.bidforum.model.dto.BbsNoticeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BbsNoticeMapper extends BaseMapper<BbsNoticeDO> {
    // BbsNotice【01】根据接收人id查询通知list
    List<BbsNoticeDTO> listNoticeByReceiveUserId(String receiveUserId);
}
