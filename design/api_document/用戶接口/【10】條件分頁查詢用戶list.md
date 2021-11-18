http://10.244.186.86:8081/api/user/queryUserList

### 【10】條件分頁查詢用戶list

### 接口文檔

***

| 是否需要token |
| --------- |
| 是         |

***

請求參數JSON對象字段

| 字段             | 類型      | 描述     |
| -------------- | ------- | ------ |
| send_recv_type | String  | 發單接單類別 |
| fctry_zone     | String  | 所屬廠區   |
| secn_cmpy      | String  | 所屬次集團  |
| entrps_group   | String  | 所屬事業群  |
| pd_office      | String  | 所屬產品處  |
| pageIndex      | Integer | 分頁頁碼   |
| pageSize       | Integer | 每頁數據條數 |

***

返回結果JSON對象字段

| 字段   | 類型     | 描述              |
| ---- | ------ | --------------- |
| code | String | 結果標誌(0：失敗，1：成功) |
| msg  | String | 結果文字信息          |
| t    | Object | 返回對象t           |

***

t對象字段

| 字段         | 類型      | 描述     |
| ---------- | ------- | ------ |
| row_total  | Integer | 總行數    |
| page_total | Integer | 總頁數    |
| list       | Array   | 用戶list |

****

用戶list中對象字段

| 字段                      | 類型     | 描述                        |
| ----------------------- | ------ | ------------------------- |
| pkid                    | String | 主鍵id                      |
| username                | String | 賬號名稱                      |
| password                | String | 賬號密碼                      |
| in_out_type             | String | 公司內外類別(in：公司內，out：公司外)    |
| send_recv_type          | String | 發單接單類別(send：發單方，recv：接單方) |
| fctry_zone              | String | 所屬廠區                      |
| secn_cmpy               | String | 所屬次集團                     |
| entrps_group            | String | 所屬事業群                     |
| pd_office               | String | 所屬產品處                     |
| dept_name               | String | 單位名稱                      |
| legal_person            | String | 交易法人                      |
| cost_code               | String | 費用代碼                      |
| recv_mnufc_range_rel_id | String | 接單方加工範圍關聯id               |
| busis_mngr              | String | 業務經理                      |
| tel                     | String | 聯繫電話（固定電話）                |
| email                   | String | email                     |
| summary                 | String | 簡介                        |
| create_date             | String | 創建時間                      |
| phone                   | String | 聯繫電話（移動電話）                |
| dept_code               | String | 單位代碼                      |
| f_valid                 | String | 賬號有效標誌（Y：有效，N：無效）         |
| f_active                | String | 活躍標誌, Y:活躍，N:不活躍          |

****

.
