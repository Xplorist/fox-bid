package com.foxconn.bidsso.model;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Thu Dec 19 11:30:37 CST 2019.</p>
 * @author user 
 */

@Data
@TableName("USER_INFO")
public class UserInfoDO implements Serializable  {

	 private static final long serialVersionUID =  1503778251459846180L;
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


}
