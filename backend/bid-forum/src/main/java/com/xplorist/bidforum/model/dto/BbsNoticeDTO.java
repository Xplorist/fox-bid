package com.xplorist.bidforum.model.dto;

import com.xplorist.bidforum.model.BbsNoticeDO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BbsNoticeDTO extends BbsNoticeDO {
    private BbsPostDTO post;// 帖子
    private BbsReplyDTO parentReply;// 父回复
    private BbsReplyDTO childReply;// 子回复
    private UserInfoDTO receiveUser;// 接收通知人（父回复创建人）
    private UserInfoDTO sendUser;// 发送通知人（子回复创建人）

    public BbsNoticeDTO(BbsNoticeDO obj) {
        super(obj);
    }
}
