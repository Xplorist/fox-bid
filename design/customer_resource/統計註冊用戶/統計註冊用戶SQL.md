### 統計用戶SQL

****

```
select t.username 用戶名,
       t.busis_mngr 業務窗口,
       decode(t.send_recv_type, 'send', '發單方', 'recv', '接單方') 用戶類型,
       t.fctry_zone 廠區,
       t.entrps_group 事業群,
       t.pd_office 產品處,
       t.dept_name 部門名稱,
       t.dept_code 部門代碼,
       t.legal_person 法人,
       t.cost_code 費用代碼,
       t.tel 座機,
       t.phone 手機,
       t.email 郵箱,
       t.create_date 創建時間
  from USER_INFO t
 where t.username not in ('admin', 'send', 'recv')
   and t.send_recv_type = 'send'
 order by t.create_date desc
```

****

切換發單方和接單方時，將查詢條件send_recv_type的字段的值修改即可
