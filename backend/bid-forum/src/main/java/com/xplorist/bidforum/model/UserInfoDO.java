package com.xplorist.bidforum.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Mon Mar 30 09:05:33 CST 2020.</p>
 * @author user 
 */

@Data
@NoArgsConstructor
@TableName("USER_INFO")
public class UserInfoDO implements Serializable  {

	 private static final long serialVersionUID =  6334874876458814925L;
	 /** 主鍵id **/
	private String pkid;
	 /** 賬號名稱 **/
	private String username;
	 /** 賬號密碼 **/
	private String password;
	 /** 公司內外類別(in：公司內，out：公司外) **/
	private String inOutType;
	 /** 發單接單類別(send：發單方，recv：接單方) **/
	private String sendRecvType;
	 /** 所屬廠區 **/
	private String fctryZone;
	 /** 所屬次集團 **/
	private String secnCmpy;
	 /** 所屬事業群 **/
	private String entrpsGroup;
	 /** 所屬產品處 **/
	private String pdOffice;
	 /** 單位名稱 **/
	private String deptName;
	 /** 交易法人 **/
	private String legalPerson;
	 /** 費用代碼 **/
	private String costCode;
	 /** 接單方加工範圍關聯id **/
	private String recvMnufcRangeRelId;
	 /** 收/付款銀行名稱 **/
	private String bankName;
	 /** 收/付款銀行賬號 **/
	private String bankAcunt;
	 /** 業務經理 **/
	private String busisMngr;
	 /** 聯繫電話（固定電話） **/
	private String tel;
	 /** Email **/
	private String email;
	 /** 簡介 **/
	private String summary;
	 /** 頭像文件pkid **/
	private String userPicFilePkid;
	 /** 創建時間 **/
	private Date createDate;
	 /** 聯繫電話（移動電話） **/
	private String phone;
	 /** 單位代碼 **/
	private String deptCode;
	 /** 賬號有效標誌（Y：有效，N：無效） **/
	private String fValid;
	 /** 賬號最近登錄時間 **/
	private Date latestLoginTime;

	public UserInfoDO(UserInfoDO obj) {
		this.pkid = obj.pkid;
		this.username = obj.username;
		this.password = obj.password;
		this.inOutType = obj.inOutType;
		this.sendRecvType = obj.sendRecvType;
		this.fctryZone = obj.fctryZone;
		this.secnCmpy = obj.secnCmpy;
		this.entrpsGroup = obj.entrpsGroup;
		this.pdOffice = obj.pdOffice;
		this.deptName = obj.deptName;
		this.legalPerson = obj.legalPerson;
		this.costCode = obj.costCode;
		this.recvMnufcRangeRelId = obj.recvMnufcRangeRelId;
		this.bankName = obj.bankName;
		this.bankAcunt = obj.bankAcunt;
		this.busisMngr = obj.busisMngr;
		this.tel = obj.tel;
		this.email = obj.email;
		this.summary = obj.summary;
		this.userPicFilePkid = obj.userPicFilePkid;
		this.createDate = obj.createDate;
		this.phone = obj.phone;
		this.deptCode = obj.deptCode;
		this.fValid = obj.fValid;
		this.latestLoginTime = obj.latestLoginTime;
	}
}
