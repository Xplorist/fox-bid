package com.xplorist.bidforum.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Tue Mar 31 14:13:56 CST 2020.</p>
 * @author user 
 */

@Data
@NoArgsConstructor
@TableName("BBS_NOTICE")
public class BbsNoticeDO implements Serializable  {

	 private static final long serialVersionUID =  4026527665835485028L;
	 /** ID **/
	private String id;
	 /** 帖子表id **/
	private String bbsPostId;
	 /** 父回复表id **/
	private String parentReplyId;
	 /** 子回复表id **/
	private String childReplyId;
	 /** 接收人id（父回复创建人id） **/
	private String receiveUserId;
	 /** 发送人id（子回复创建人id） **/
	private String sendUserId;
	 /** 创建时间 **/
	private Date createTime;
	 /** 修改时间 **/
	private Date updateTime;
	 /** 删除标志（0：未删，1：已删） **/
	private Integer deleteFlag;
	 /** 删除时间 **/
	private Date deleteTime;

	public BbsNoticeDO(BbsNoticeDO obj) {
		this.id = obj.id;
		this.bbsPostId = obj.bbsPostId;
		this.parentReplyId = obj.parentReplyId;
		this.childReplyId = obj.childReplyId;
		this.receiveUserId = obj.receiveUserId;
		this.sendUserId = obj.sendUserId;
		this.createTime = obj.createTime;
		this.updateTime = obj.updateTime;
		this.deleteFlag = obj.deleteFlag;
		this.deleteTime = obj.deleteTime;
	}
}
