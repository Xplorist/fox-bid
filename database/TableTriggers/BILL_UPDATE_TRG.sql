trigger BILL_UPDATE_TRG
  after update of f_bill_no
  on bill 
  for each row
declare
  -- local variables here
begin
  BILL_NO_GENERATOR(:new.pkid, :new.pd_type_no);
end BILL_UPDATE_TRG;
