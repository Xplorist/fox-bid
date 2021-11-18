http://10.244.186.86:8081/api/bill/query_status_num_recv_user

### 接單用戶登錄查詢個狀態訂單list個數

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

t對象字段

| 字段             | 類型   | 描述       |
| ---------------- | ------ | ---------- |
| num_status_all   | Number | 所有個數   |
| num_status_1     | Number | 待報價個數 |
| num_status_2     | Number | 待發貨個數 |
| num_status_3     | Number | 待收貨個數 |
| num_status_4     | Number | 待付款個數 |
| num_status_5     | Number | 待收款個數 |
| num_status_6     | Number | 已完成個數 |
| num_no_recv_eval | Number | 待評價個數 |



