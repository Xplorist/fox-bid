package com.xplorist.bidforum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xplorist.bidforum.model.BbsPostDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BidForumMapper extends BaseMapper<BbsPostDO> {
}
