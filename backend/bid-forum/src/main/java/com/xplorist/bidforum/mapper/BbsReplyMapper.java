package com.xplorist.bidforum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xplorist.bidforum.model.BbsReplyDO;
import com.xplorist.bidforum.model.dto.BbsReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BbsReplyMapper extends BaseMapper<BbsReplyDO> {
    // BbsReply【01】根据帖子ID查询根回复list
    List<BbsReplyDTO> listRootReplyByPostId(String postId);

    // BbsReply【02】 根据某个父回复Id查询子回复list
    List<BbsReplyDTO> listChildrenReplyByParentId(@Param("postId") String postId, @Param("parentId") String parentId);
}
