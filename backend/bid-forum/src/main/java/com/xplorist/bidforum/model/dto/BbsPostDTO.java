package com.xplorist.bidforum.model.dto;

import com.xplorist.bidforum.model.BbsPostDO;
import com.xplorist.bidforum.model.UserInfoDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BbsPostDTO extends BbsPostDO {
    private UserInfoDO creator;// 创建人信息
    private List<BbsReplyDTO> replyList;// parentId为空的根回复
    // 分页查询条件字段
    private Integer pageIndex;// 页码
    private Integer pageSize;// 每页数据条数
    // 分页查询结果字段
    private Integer rowNo;// 行号
    private Integer rowTotal;// 总行数

    // 构造方法
    public BbsPostDTO(BbsPostDO obj) {
        super(obj);
    }
}
