CREATE OR REPLACE procedure BIDDING.BILL_NO_GENERATOR(p_in_bill_pkid  in varchar2, -- 訂單表id
                                              p_in_pd_type_no in varchar2 --產品類型編號
                                              ) is
  v_bill_no        varchar2(50); -- 單號
  v_count          number; -- 計數器
  v_water_code     varchar2(50); -- 流水碼
  v_bill_no_prefix varchar2(50); -- 單號前綴
begin
  -- 查詢當天該類型是否存在 
  select count(*)
    into v_count
    from BILL_NO t
   where t.pd_type_no = p_in_pd_type_no
     and t.create_date = trunc(sysdate);

  if v_count = 0 then
    -- 當天該類型不存在單號,直接新增類型編號+年月日+00001的單號
    select p_in_pd_type_no || to_char(trunc(sysdate), 'yyyymmdd') ||
           '00001'
      into v_bill_no
      from dual;
    -- BILL_NO表中bill_no字段為唯一鍵，插入數據時會自動上鎖
    insert into BILL_NO
      (bill_no, pd_type_no)
    values
      (v_bill_no, p_in_pd_type_no);
  else
    -- 當天該類型存在單號，先查出單號，取出流水號轉換成數字再加1，再轉換成流水號，拼接成新的單號，再更新到單號表中
    select bill_no
      into v_bill_no
      from BILL_NO t
     where t.pd_type_no = p_in_pd_type_no
       and t.create_date = trunc(sysdate);
  
    -- 截取流水號轉換成數字並加1再處理成'0000x'格式
    select lpad((to_number(substr(v_bill_no, -5, 5)) + 1), 5, '0')
      into v_water_code
      from dual;
  
    -- 截取前綴
    select substr(v_bill_no, 0, length(v_bill_no) - 5)
      into v_bill_no_prefix
      from dual;
  
    -- 組裝前綴和流水號為單號
    select v_bill_no_prefix || v_water_code into v_bill_no from dual;
  
    -- 更新單號到單號表中
    update BILL_NO
       set bill_no = v_bill_no
     where pd_type_no = p_in_pd_type_no
       and create_date = trunc(sysdate);
  
  end if;

  -- 將生成的單號插入到訂單單號關聯表中
  insert into MAIN_BILL_NO_REL
    (bill_pkid, bill_no)
  values
    (p_in_bill_pkid, v_bill_no);

end BILL_NO_GENERATOR;
