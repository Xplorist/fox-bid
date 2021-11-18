package com.foxconn.bidstatistics.model;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Sat Dec 28 08:48:57 CST 2019.</p>
 * @author user 
 */

@Data
@TableName("BILL")
public class BillDO implements Serializable  {

	 private static final long serialVersionUID =  3922986332031073345L;
	 /** 主鍵id(由java後台生成) **/
	private String pkid;
	 /** 單號 **/
	private String billNo;
	 /** 訂單狀態（0：待發佈【send保存後】，1：待報價【send發佈後】, 2：待發貨【中標後】，3：待收貨【recv發貨後】，4：待付款【send收貨後】，5：待收款（send付款後），6：已完成，-1：流標，-2：棄標） **/
	private String billStatus;
	 /** 發單用戶id **/
	private String sendUserPkid;
	 /** 中標接單用戶id **/
	private String recvUserPkid;
	 /** 中標報價表id **/
	private String givePriceMstrPkid;
	 /** 用途（產品類型） **/
	private String pdType;
	 /** 交貨時間 **/
	private Date deliverDate;
	 /** 交貨地點 **/
	private String deliverAddress;
	 /** 聯繫電話 **/
	private String tel;
	 /** 顯示聯繫電話標誌（Y:是，N:否） **/
	private String fShowTel;
	 /** 接受總價 **/
	private Integer totalPrice;
	 /** 幣別 **/
	private String moneyType;
	 /** 需求數量 **/
	private Integer amount;
	 /** 開標範圍（類型） **/
	private String bidRange;
	 /** 競價開始時間 **/
	private Date bidStartDate;
	 /** 競價結束時間 **/
	private Date bidEndDate;
	 /** 交貨模式（物流費用） **/
	private String deliverWay;
	 /** 製作要求 **/
	private String makeRequ;
	 /** 公開隱藏標誌（O:公開[open]，H:隱藏[hide]） **/
	private String fOpenHide;
	 /** 零件圖檔文件表關聯id **/
	private String partDocFileRelId;
	 /** 創建時間 **/
	private Date createDate;
	 /** 發單評價標誌（Y：是，N：否） **/
	private String fSendEval;
	 /** 接單評價標誌（Y：是，N：否） **/
	private String fRecvEval;
	 /** 發佈時間 **/
	private Date publishDate;
	 /** 用途編號（產品類型） **/
	private String pdTypeNo;
	 /** 開標範圍具體值 **/
	private String bidRangeValue;
	 /** 單號標誌（發佈訂單時生成單號前先更新此字段） **/
	private String fBillNo;


}
