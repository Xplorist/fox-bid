http://10.244.186.86:8081/api/bill/query_send_make_eval_list

### 查詢發單方做出的評價list(分頁查詢)

### 接口文檔

***

| 是否需要token |
| ------------- |
| 是            |

***

請求參數JSON對象字段

| 字段      | 類型   | 描述         |
| --------- | ------ | ------------ |
| pageIndex | Number | 分頁頁碼     |
| pageSize  | Number | 分頁每頁條數 |

***

返回結果JSON對象字段

| 字段 | 類型   | 描述                       |
| ---- | ------ | -------------------------- |
| code | String | 結果標誌(0：失敗，1：成功) |
| msg  | String | 結果文字信息               |
| t    | Object | 返回對象                   |

***

t對象字段

| 字段                | 類型   | 描述                 |
| ------------------- | ------ | -------------------- |
| page_total          | Number | 分頁總頁數           |
| row_total           | Number | 分頁數據總條數       |
| send_make_eval_list | Array  | 發單方作出的評價list |

send_make_eval_list單方作出的評價list對象字段

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
| recv_user       | Object | 接單用戶信息               |

recv_user接單用戶信息字段

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

