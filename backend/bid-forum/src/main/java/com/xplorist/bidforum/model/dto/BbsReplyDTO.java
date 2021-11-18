package com.xplorist.bidforum.model.dto;

import com.xplorist.bidforum.model.BbsReplyDO;
import com.xplorist.bidforum.model.UserInfoDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class BbsReplyDTO extends BbsReplyDO {
    @NotNull(message = "回复类型不能为Null")
    @NotEmpty(message = "回复类型不能为空字符串")
    private String replyType;// 回复类型（toPost:回复帖子; toReply:给回复添加子回复）
    private List<BbsReplyDTO> replyList;// parentId不为空的子回复
    private UserInfoDO creator;// 创建人信息

    // 构造方法
    public BbsReplyDTO (BbsReplyDO obj) {
        super(obj);
    }
}
