package com.foxconn.bidstatistics.model;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Sat Dec 28 08:52:59 CST 2019.</p>
 * @author user 
 */

@Data
@TableName("SEND_EVAL")
public class SendEvalDO implements Serializable  {

	 private static final long serialVersionUID =  2635751788304681765L;
	 /** 主鍵id **/
	private String pkid;
	 /** 訂單表id **/
	private String billPkid;
	 /** 評價用戶id（發單方） **/
	private String sendUserPkid;
	 /** 被評價用戶id（接單方） **/
	private String recvUserPkid;
	 /** 綜合評價分數 **/
	private Integer summaryScore;
	 /** 出貨時效分數 **/
	private Integer outRateScore;
	 /** 出貨質量分數 **/
	private Integer outQualScore;
	 /** 服務態度分數 **/
	private Integer svcAtituScore;
	 /** 綜合評價文字 **/
	private String summaryText;
	 /** 匿名評價標誌（Y:是，N:否） **/
	private String fAnomus;
	 /** 創建時間 **/
	private Date createDate;


}
