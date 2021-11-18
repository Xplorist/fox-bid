package com.xplorist.bidforum.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  帖子回复表DO
 * <p>Date: Thu Mar 19 15:49:27 CST 2020.</p>
 * @author user 
 */

@Data
@NoArgsConstructor
@TableName("BBS_REPLY")
public class BbsReplyDO implements Serializable  {

	 private static final long serialVersionUID =  3159922905171305957L;
	 /** ID **/
	private String id;
	 /** 帖子表ID **/
	 @NotNull(message = "【帖子表ID】不能为Null")
	 @NotEmpty(message = "【帖子表ID】不能为空字符串")
	private String bbsPostId;
	 /** 父回复表ID **/
	private String parentId;
	 /** 内容 **/
	 @NotNull(message = "【帖子回复内容】不能为Null")
	 @NotEmpty(message = "【不帖子回复内容】不能为空字符串")
	private String content;
	 /** 创建人ID **/
	private String creatorId;
	 /** 创建时间 **/
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	 /** 修改时间 **/
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateTime;
	 /** 删除标志（0：未删，1：已删） **/
	private Integer deleteFlag;
	 /** 删除时间 **/
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date deleteTime;

	// 构造方法
	public BbsReplyDO(BbsReplyDO obj) {
		this.id = obj.id;
		this.bbsPostId = obj.bbsPostId;
		this.parentId = obj.parentId;
		this.content = obj.content;
		this.creatorId = obj.creatorId;
		this.createTime = obj.createTime;
		this.updateTime = obj.updateTime;
		this.deleteFlag = obj.deleteFlag;
		this.deleteTime = obj.deleteTime;
	}
}
