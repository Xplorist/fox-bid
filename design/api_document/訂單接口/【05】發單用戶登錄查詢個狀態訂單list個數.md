http://10.244.186.86:8081/api/bill/query_status_bill_num_send_user

### 發單用戶登錄查詢個狀態訂單list個數

### 接口文檔

***

請求參數JSON對象字段

| 字段 | 類型 | 描述 |
| ---- | ---- | ---- |
|      |      |      |

***

返回結果JSON對象字段

| 字段 | 類型   | 描述                       |
| ---- | ------ | -------------------------- |
| code | String | 結果標誌(0：失敗，1：成功) |
| msg  | String | 結果文字信息               |
| t    | Object | 返回對象                   |

***

t返回對象字段

| 字段             | 類型   | 描述                       |
| ---------------- | ------ | -------------------------- |
| num_status_all   | Number | 狀態為【全部】的訂單個數   |
| num_status_0     | Number | 狀態為【待發佈】的訂單個數 |
| num_status_1     | Number | 狀態為【待報價】的訂單個數 |
| num_status_1_5   | Number | 狀態為【待选标】的訂單個數 |
| num_status_2     | Number | 狀態為【待發貨】的訂單個數 |
| num_status_3     | Number | 狀態為【待收貨】的訂單個數 |
| num_status_4     | Number | 狀態為【待付款】的訂單個數 |
| num_status_5     | Number | 狀態為【待收款】的訂單個數 |
| num_status_6     | Number | 狀態為【已完成】的訂單個數 |
| num_no_send_eval | Number | 狀態為【待評價】的訂單個數 |

