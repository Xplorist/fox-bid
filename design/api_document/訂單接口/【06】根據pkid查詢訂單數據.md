1http://10.244.186.86:8081/api/bill/query_bill_by_pkid

### 根據pkid查詢訂單數據

### 接口文檔

***

請求參數JSON對象字段

| 字段 | 類型   | 描述       |
| ---- | ------ | ---------- |
| pkid | String | 訂單主鍵id |

***

返回結果JSON對象字段

| 字段 | 類型   | 描述                       |
| ---- | ------ | -------------------------- |
| code | String | 結果標誌(0：失敗，1：成功) |
| msg  | String | 結果文字信息               |
| t    | Object | 返回對象（訂單對象）       |

***

t對象（訂單對象）字段

| 字段                 | 類型   | 描述                                       |
| -------------------- | ------ | ------------------------------------------ |
| pkid                 | String | 主鍵id                                     |
| bill_no              | String | 單號                                       |
| bill_status          | String | 訂單狀態                                   |
| send_user_pkid       | String | 發單用戶id                                 |
| recv_user_pkid       | String | 中標接單用戶id                             |
| give_price_mstr_pkid | String | 中標報價表id                               |
| pd_type              | String | 用途（產品類型）                           |
| deliver_date         | String | 交貨時間                                   |
| deliver_address      | String | 交貨地點                                   |
| tel                  | String | 聯繫電話                                   |
| f_show_tel           | String | 顯示聯繫電話標誌（Y:是，N:否）             |
| total_price          | String | 接受總價                                   |
| money_type           | String | 幣別                                       |
| amount               | String | 需求數量                                   |
| bid_range            | String | 開標範圍                                   |
| bid_start_date       | String | 競價開始時間                               |
| bid_end_date         | String | 競價結束時間                               |
| deliver_way          | String | 交貨模式（物流費用）                       |
| make_requ            | String | 製作要求                                   |
| f_open_hide          | String | 公開隱藏標誌（o:公開[open]，h:隱藏[hide]） |
| part_doc_file_rel_id | String | 零件圖檔文件表關聯id                       |
| create_date          | String | 創建時間                                   |
| f_send_eval          | String | 發單評價標誌（Y：是，N：否）               |
| f_recv_eval          | String | 接單評價標誌（Y：是，N：否）               |
| publish_date         | String | 發佈時間                                   |
| recv_user_num        | Number | 參與投標的接單用戶個數（參與單位個數）     |
| recv_user            | Object | 中標接單用戶（對象字段中包含中標單位）     |
| send_user            | Object | 訂單創建用戶（發單用戶）                   |
| file_list            | Array  | 文件list                                   |
| give_recv_user_list  | Array  | 參與報價的接單用戶list                     |
| send_eval            | Object | 發單方評價                                 |
| recv_eval            | Object | 接單方評價                                 |

file_list數組中對象Object字段說明

| 字段                 | 類型   | 描述                 |
| -------------------- | ------ | -------------------- |
| pkid                 | String | 主鍵id               |
| part_doc_file_rel_id | String | 零件圖檔文件表關聯id |
| file_origin_name     | String | 文件原始名稱         |
| file_save_name       | String | 文件保存名稱         |
| file_save_path       | String | 文件保存路徑         |
| part_amunt           | Number | 零件個數             |
| list_order           | Number | 列表序號             |
| create_date          | String | 創建時間             |

send_user訂單創建用戶對象/give_recv_user_list中的對象字段

| 字段               | 類型   | 描述                                     |
| ------------------ | ------ | ---------------------------------------- |
| pkid               | String | 主鍵id                                   |
| username           | String | 賬號名稱                                 |
| in_out_type        | String | 公司內外類別(in：公司內，out：公司外)    |
| send_recv_type     | String | 發單接單類別(send：發單方，recv：接單方) |
| fctry_zone         | String | 所屬廠區                                 |
| secn_cmpy          | String | 所屬次集團                               |
| entrps_group       | String | 所屬事業群                               |
| pd_office          | String | 所屬產品處                               |
| dept_name          | String | 單位名稱                                 |
| legal_person       | String | 交易法人                                 |
| cost_code          | String | 費用代碼                                 |
| recv_mnufc_range   | String | 加工範圍(接單）                          |
| busis_mngr         | String | 業務經理                                 |
| tel                | String | 聯繫電話                                 |
| email              | String | email                                    |
| summary            | String | 簡介                                     |
| create_date        | String | 創建時間                                 |
| user_pic_file_pkid | String | 頭像文件pkid                             |
| f_win_bid          | String | 是否中標標誌（Y:是，N:否）               |

send_eval發單方評價字段

| 字段            | 類型   | 描述                       |
| --------------- | ------ | -------------------------- |
| pkid            | String | 主鍵id                     |
| bill_pkid       | String | 訂單表id                   |
| send_user_pkid  | String | 評價用戶id（發單方）       |
| recv_user_pkid  | String | 被評價用戶id（接單方）     |
| summary_score   | Number | 綜合評價分數               |
| out_rate_score  | Number | 出貨時效分數               |
| out_qual_score  | Number | 出貨質量分數               |
| svc_atitu_score | Number | 服務態度分數               |
| summary_text    | String | 綜合評價文字               |
| f_anomus        | String | 匿名評價標誌（Y:是，N:否） |
| create_date     | String | 創建時間                   |

recv_eval接單方評價字段

| 字段            | 類型   | 描述                       |
| --------------- | ------ | -------------------------- |
| pkid            | String | 主鍵id                     |
| bill_pkid       | String | 訂單表id                   |
| recv_user_pkid  | String | 評價用戶id（接單方）       |
| send_user_pkid  | String | 被評價用戶id（發單方）     |
| summary_score   | Number | 綜合評價分數               |
| pay_rate_score  | Number | 付款時效分數               |
| svc_atitu_score | Number | 服務態度分數               |
| summary_text    | String | 綜合評價文字               |
| f_anomus        | String | 匿名評價標誌（Y:是，N:否） |
| create_date     | String | 創建時間                   |

