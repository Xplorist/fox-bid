http://10.244.186.86:8081/api/bill/query_give_price_list

### 根據訂單id查詢排序後報價list(分頁查詢)

### 接口文檔

***

| 是否需要token |
| ------------- |
| 是            |

***

請求參數JSON對象字段

| 字段      | 類型   | 描述                                                         |
| --------- | ------ | ------------------------------------------------------------ |
| bill_pkid | String | 訂單id                                                       |
| pageIndex | Number | 分頁頁碼                                                     |
| pageSize  | Number | 分頁每頁數據條數                                             |
| sort_way  | String | 排序方式（complex: 綜合排序，price: 報價排序，date: 交期排序） |

***

返回結果JSON對象字段

| 字段 | 類型   | 描述                       |
| ---- | ------ | -------------------------- |
| code | String | 結果標誌(0：失敗，1：成功) |
| msg  | String | 結果文字信息               |
| t    | Object | 返回對象                   |

***

t對象

| 字段            | 類型   | 描述           |
| --------------- | ------ | -------------- |
| give_price_list | Array  | 報價list       |
| row_total       | Number | 分頁數據總條數 |
| page_total      | Number | 分頁總頁數     |

give_price_list報價list數組中的對象字段

| 字段         | 類型   | 描述                       |
| ------------ | ------ | -------------------------- |
| total_price  | Number | 總報價                     |
| deliver_date | String | 交貨日期                   |
| descp        | String | 理由說明                   |
| f_win_bid    | String | 是否中標標誌（Y:是，N:否） |
| slav_list    | Array  | 報價明細list（從表list）   |
| recv_user    | Object | 接單用戶信息               |

slav_list報價明細list對象字段

| 字段               | 類型   | 描述           |
| ------------------ | ------ | -------------- |
| part_name          | String | 零件名稱       |
| part_amunt         | Number | 零件數量       |
| part_unit_price    | Number | 零件單價       |
| part_price_sum     | Number | 零件價格小計   |
| part_doc_file_pkid | String | 零件圖檔文件id |
| part_doc_file      | Object | 零件圖檔文件   |

part_doc_file零件圖檔文件對象字段

| 字段                 | 類型   | 描述                 |
| -------------------- | ------ | -------------------- |
| pkid                 | String | 主鍵id               |
| part_doc_file_rel_id | String | 零件圖檔文件表關聯id |
| file_origin_name     | String | 文件原始名稱         |
| file_save_name       | String | 文件保存名稱         |
| file_save_path       | String | 文件保存路徑         |
| part_amunt           | Number | 零件個數             |

recv_user接單用戶信息對象字段

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

