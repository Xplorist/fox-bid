http://10.244.186.86:8081/api/baseInfo/listQueryConditions

### 【10】查詢搜索條件list

### 接口文檔

***

| 是否需要token |
| --------- |
| 否         |

***

請求參數JSON對象字段

| 字段  | 類型  | 描述  |
| --- | --- | --- |
| 無   |     |     |

***

返回結果JSON對象字段

| 字段   | 類型     | 描述              |
| ---- | ------ | --------------- |
| code | String | 結果標誌(0：失敗，1：成功) |
| msg  | String | 結果文字信息          |
| t    | Array  | 返回數組            |

***

t數組中對象字段

| 字段          | 類型      | 描述     |
| ----------- | ------- | ------ |
| scope       | String  | 名稱     |
| val         | String  | 值      |
| id          | String  | id     |
| active      | Boolean | 是否有效   |
| checkedItem | Object  | 被選中的子項 |
| sub         | Array   | 子項     |

checkedItem,sub數組對象中的字段

| 字段  | 類型      | 描述  |
| --- | ------- | --- |
| txt | String  | 說明  |
| val | String  | 值   |
| id  | Integer | id  |

****

.
