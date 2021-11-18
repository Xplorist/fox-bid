package com.foxconn.bidstatistics.model;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Thu Jan 02 10:55:10 CST 2020.</p>
 * @author user 
 */

@Data
@TableName("PD_TYPE")
public class PdTypeDO implements Serializable  {

	 private static final long serialVersionUID =  2480768886243529258L;
	 /** 主鍵id **/
	private String pkid;
	 /** 產品類型名稱 **/
	private String name;
	 /** 列表序號 **/
	private Integer listOrder;
	 /** 創建時間 **/
	private Date createDate;


}
