package com.foxconn.bidstatistics.model;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Sat Dec 28 08:53:09 CST 2019.</p>
 * @author user 
 */

@Data
@TableName("RECV_EVAL")
public class RecvEvalDO implements Serializable  {

	 private static final long serialVersionUID =  235386340390121280L;
	 /** 主鍵id **/
	private String pkid;
	 /** 訂單表id **/
	private String billPkid;
	 /** 評價用戶id（接單方） **/
	private String recvUserPkid;
	 /** 被評價用戶id（發單方） **/
	private String sendUserPkid;
	 /** 綜合評價分數 **/
	private Integer summaryScore;
	 /** 付款時效分數 **/
	private Integer payRateScore;
	 /** 服務態度分數 **/
	private Integer svcAtituScore;
	 /** 綜合評價文字 **/
	private String summaryText;
	 /** 匿名評價標誌（Y:是，N:否） **/
	private String fAnomus;
	 /** 創建時間 **/
	private Date createDate;


}
