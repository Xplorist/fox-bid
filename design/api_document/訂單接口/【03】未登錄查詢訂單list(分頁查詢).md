http://10.244.186.86:8081/api/bill/query_bill_list_not_login

### 未登錄查詢訂單list(分頁查詢)

### 接口文檔

***

請求參數JSON對象字段

| 字段              | 類型     | 描述               |
| --------------- | ------ | ---------------- |
| bid_range       | String | 開標範圍（'集團':'集團'）  |
| bill_status     | String | 訂單狀態（all: 全部)    |
| deliver_address | String | 交貨地點(all:全部)     |
| deliver_date    | String | 交貨日期（all:全部）     |
| pd_type         | String | 用途（產品類型）（all:全部） |
| bill_no         | String | 單號（all:全部）       |
| send_user_name  | String | 發單用戶名            |
| recv_user_name  | String | 接單用戶名            |
| pageIndex       | Number | 頁碼               |
| pageSize        | Number | 每頁數據條數           |

***

返回結果JSON對象字段

| 字段   | 類型     | 描述              |
| ---- | ------ | --------------- |
| code | String | 結果標誌(0：失敗，1：成功) |
| msg  | String | 結果文字信息          |
| t    | Object | 返回對象            |

***

t對象的字段

| 字段         | 類型     | 描述      |
| ---------- | ------ | ------- |
| bill_list  | Array  | 訂單list  |
| page_total | Number | 總頁數     |
| row_total  | Number | 訂單數據總條數 |

***

  訂單list中的訂單對象字段

t對象（訂單對象）字段

| 字段                   | 類型     | 描述                            |
| -------------------- | ------ | ----------------------------- |
| pkid                 | String | 主鍵id                          |
| bill_no              | String | 單號                            |
| bill_status          | String | 訂單狀態                          |
| send_user_pkid       | String | 發單用戶id                        |
| recv_user_pkid       | String | 中標接單用戶id                      |
| give_price_mstr_pkid | String | 中標報價表id                       |
| pd_type              | String | 用途（產品類型）                      |
| deliver_date         | String | 交貨時間                          |
| deliver_address      | String | 交貨地點                          |
| tel                  | String | 聯繫電話                          |
| f_show_tel           | String | 顯示聯繫電話標誌（Y:是，N:否）             |
| total_price          | String | 接受總價                          |
| money_type           | String | 幣別                            |
| amount               | String | 需求數量                          |
| bid_range            | String | 開標範圍                          |
| bid_start_date       | String | 競價開始時間                        |
| bid_end_date         | String | 競價結束時間                        |
| deliver_way          | String | 交貨模式（物流費用）                    |
| make_requ            | String | 製作要求                          |
| f_open_hide          | String | 公開隱藏標誌（o:公開[open]，h:隱藏[hide]） |
| part_doc_file_rel_id | String | 零件圖檔文件表關聯id                   |
| create_date          | String | 創建時間                          |
| f_send_eval          | String | 發單評價標誌（Y：是，N：否）               |
| f_recv_eval          | String | 接單評價標誌（Y：是，N：否）               |
| publish_date         | String | 發佈時間                          |
| recv_user_num        | Number | 參與投標的接單用戶個數（參與單位個數）           |
| recv_user            | Object | 中標接單用戶（對象字段中包含中標單位）           |
| send_user            | Object | 發單用戶（需求單位）                    |
| give_price           | Object | 中標用戶的報價信息                     |

send_user發單用戶對象字段

| 字段        | 類型     | 描述   |
| --------- | ------ | ---- |
| dept_name | String | 單位名稱 |

give_price中標用戶的報價信息對象字段

| 字段           | 類型        | 描述               |
| ------------ | --------- | ---------------- |
| total_price  | Integer   | 總報價              |
| deliver_date | String    | 交貨日期             |
| slav_list    | ArrayList | 報價明細list（從表list） |

slav_list對象字段

| 字段              | 類型      | 描述     |
| --------------- | ------- | ------ |
| part_name       | String  | 零件名稱   |
| part_amunt      | Integer | 零件數量   |
| part_unit_price | Integer | 零件單價   |
| part_price_sum  | Integer | 零件價格小計 |
| list_order      | Integer | 列表序號   |

****

.
