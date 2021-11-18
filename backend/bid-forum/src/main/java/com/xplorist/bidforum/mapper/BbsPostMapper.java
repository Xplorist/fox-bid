package com.xplorist.bidforum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xplorist.bidforum.model.BbsPostDO;
import com.xplorist.bidforum.model.dto.BbsPostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BbsPostMapper extends BaseMapper<BbsPostDO> {
    // bbsPost【01】分页查询所有帖子list
    List<BbsPostDTO> listBbsPostByPage(BbsPostDTO param);
}
