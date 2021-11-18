package com.foxconn.bidstatistics.model;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Sat Dec 28 08:51:26 CST 2019.</p>
 * @author user 
 */

@Data
@TableName("GIVE_PRICE_MSTR")
public class GivePriceMstrDO implements Serializable  {

	 private static final long serialVersionUID =  3154805703697418648L;
	 /** 主鍵id **/
	private String pkid;
	 /** 訂單表id **/
	private String billPkid;
	 /** 接單用戶id **/
	private String recvUserPkid;
	 /** 報價從表關聯id **/
	private String givePriceSlavRelId;
	 /** 總報價 **/
	private Integer totalPrice;
	 /** 交貨日期 **/
	private Date deliverDate;
	 /** 理由說明 **/
	private String descp;
	 /** 是否中標標誌（Y:是，N:否） **/
	private String fWinBid;
	 /** 創建日期 **/
	private Date createDate;


}
