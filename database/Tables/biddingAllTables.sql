/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2020/4/2 11:43:10                            */
/*==============================================================*/



-- Type package declaration
create or replace package PDTypes  
as
    TYPE ref_cursor IS REF CURSOR;
end;

-- Integrity package declaration
create or replace package IntegrityPackage AS
 procedure InitNestLevel;
 function GetNestLevel return number;
 procedure NextNestLevel;
 procedure PreviousNestLevel;
 end IntegrityPackage;
/

-- Integrity package definition
create or replace package body IntegrityPackage AS
 NestLevel number;

-- Procedure to initialize the trigger nest level
 procedure InitNestLevel is
 begin
 NestLevel := 0;
 end;


-- Function to return the trigger nest level
 function GetNestLevel return number is
 begin
 if NestLevel is null then
     NestLevel := 0;
 end if;
 return(NestLevel);
 end;

-- Procedure to increase the trigger nest level
 procedure NextNestLevel is
 begin
 if NestLevel is null then
     NestLevel := 0;
 end if;
 NestLevel := NestLevel + 1;
 end;

-- Procedure to decrease the trigger nest level
 procedure PreviousNestLevel is
 begin
 NestLevel := NestLevel - 1;
 end;

 end IntegrityPackage;
/


drop trigger BIDDING.BILL_UPDATE_TRG
/

drop table BIDDING.BBS_NOTICE cascade constraints
/

drop table BIDDING.BBS_POST cascade constraints
/

drop table BIDDING.BBS_REPLY cascade constraints
/

drop table BIDDING.BID_RANGE cascade constraints
/

drop table BIDDING.BILL cascade constraints
/

drop table BIDDING.BILL_NO cascade constraints
/

drop table BIDDING.BILL_STATUS cascade constraints
/

drop table BIDDING.BOSS_EMAIL_ADDRESS cascade constraints
/

drop table BIDDING.BULLETIN cascade constraints
/

drop index BIDDING.IDX_B_DEPT_LOOKUP
/

drop table BIDDING.B_DEPT cascade constraints
/

drop table BIDDING.CONTRACT cascade constraints
/

drop table BIDDING.DELIVER_ADDRESS cascade constraints
/

drop table BIDDING.ENTRPS_GROUP cascade constraints
/

drop table BIDDING.FCTRY_ZONE cascade constraints
/

drop table BIDDING.FILE_DOWNLOAD_RECORD cascade constraints
/

drop table BIDDING.GIVE_PRICE_LIST cascade constraints
/

drop table BIDDING.GIVE_PRICE_MSTR cascade constraints
/

drop table BIDDING.GIVE_PRICE_SLAV cascade constraints
/

drop table BIDDING.LEGAL_PERSON cascade constraints
/

drop table BIDDING.MAIN_BILL_NO_REL cascade constraints
/

drop table BIDDING.PART_DOC_FILE cascade constraints
/

drop table BIDDING.PD_OFFICE cascade constraints
/

drop table BIDDING.PD_TYPE cascade constraints
/

drop table BIDDING.RECV_EVAL cascade constraints
/

drop table BIDDING.RECV_MNUFC_RANGE cascade constraints
/

drop table BIDDING.SECN_CMPY cascade constraints
/

drop table BIDDING.SEND_EVAL cascade constraints
/

drop table BIDDING.TEST_TIME_PARSE cascade constraints
/

drop table BIDDING.USER_INFO cascade constraints
/

drop table BIDDING.USER_PIC_FILE cascade constraints
/

drop table BIDDING.USER_TEMP cascade constraints
/

drop table BIDDING.VERIFICATION_CODE cascade constraints
/

drop user BIDDING
/

/*==============================================================*/
/* User: BIDDING                                                */
/*==============================================================*/
create user BIDDING 
  identified by ""
/

/*==============================================================*/
/* Table: BBS_NOTICE                                            */
/*==============================================================*/
create table BIDDING.BBS_NOTICE 
(
   ID                   VARCHAR2(50)         default SYS_GUID() not null,
   BBS_POST_ID          VARCHAR2(50)         not null,
   PARENT_REPLY_ID      VARCHAR2(50),
   CHILD_REPLY_ID       VARCHAR2(50)         not null,
   RECEIVE_USER_ID      VARCHAR2(50)         not null,
   SEND_USER_ID         VARCHAR2(50)         not null,
   CREATE_TIME          DATE                 default SYSDATE not null,
   UPDATE_TIME          DATE,
   DELETE_FLAG          NUMBER               default 0 not null,
   DELETE_TIME          DATE,
   constraint PK_BBS_NOTICE primary key (ID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
       maxextents unlimited
   )
   logging
   tablespace BIDDING
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.BBS_NOTICE is
'通知表'
/

comment on column BIDDING.BBS_NOTICE.ID is
'ID'
/

comment on column BIDDING.BBS_NOTICE.BBS_POST_ID is
'帖子表id'
/

comment on column BIDDING.BBS_NOTICE.PARENT_REPLY_ID is
'父回复表id'
/

comment on column BIDDING.BBS_NOTICE.CHILD_REPLY_ID is
'子回复表id'
/

comment on column BIDDING.BBS_NOTICE.RECEIVE_USER_ID is
'接收人id（父回复创建人id）'
/

comment on column BIDDING.BBS_NOTICE.SEND_USER_ID is
'发送人id（子回复创建人id）'
/

comment on column BIDDING.BBS_NOTICE.CREATE_TIME is
'创建时间'
/

comment on column BIDDING.BBS_NOTICE.UPDATE_TIME is
'修改时间'
/

comment on column BIDDING.BBS_NOTICE.DELETE_FLAG is
'删除标志（0：未删，1：已删）'
/

comment on column BIDDING.BBS_NOTICE.DELETE_TIME is
'删除时间'
/

/*==============================================================*/
/* Table: BBS_POST                                              */
/*==============================================================*/
create table BIDDING.BBS_POST 
(
   ID                   VARCHAR2(50)         default sys_guid() not null,
   TITLE                VARCHAR2(100)        not null,
   CONTENT              CLOB,
   CREATOR_ID           VARCHAR2(50)         not null,
   CREATE_TIME          DATE                 default SYSDATE not null,
   UPDATE_TIME          DATE,
   DELETE_FLAG          NUMBER               default 0 not null,
   DELETE_TIME          DATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 lob
 (CONTENT)
    store as
         basicfile
 (tablespace BIDDING
        chunk 8192
 retention nocache)
/

comment on table BIDDING.BBS_POST is
'帖子表'
/

comment on column BIDDING.BBS_POST.ID is
'ID'
/

comment on column BIDDING.BBS_POST.TITLE is
'标题'
/

comment on column BIDDING.BBS_POST.CONTENT is
'内容'
/

comment on column BIDDING.BBS_POST.CREATOR_ID is
'创建人ID'
/

comment on column BIDDING.BBS_POST.CREATE_TIME is
'创建时间'
/

comment on column BIDDING.BBS_POST.UPDATE_TIME is
'修改时间'
/

comment on column BIDDING.BBS_POST.DELETE_FLAG is
'删除标志（0：未删，1：已删）'
/

comment on column BIDDING.BBS_POST.DELETE_TIME is
'删除时间'
/

/*==============================================================*/
/* Table: BBS_REPLY                                             */
/*==============================================================*/
create table BIDDING.BBS_REPLY 
(
   ID                   VARCHAR2(50)         default sys_guid() not null,
   BBS_POST_ID          VARCHAR2(50)         not null,
   PARENT_ID            VARCHAR2(50),
   CONTENT              VARCHAR2(500)        not null,
   CREATOR_ID           VARCHAR2(50)         not null,
   CREATE_TIME          DATE                 default SYSDATE not null,
   UPDATE_TIME          DATE,
   DELETE_FLAG          NUMBER               default 0 not null,
   DELETE_TIME          DATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.BBS_REPLY is
'回复表'
/

comment on column BIDDING.BBS_REPLY.ID is
'ID'
/

comment on column BIDDING.BBS_REPLY.BBS_POST_ID is
'帖子表ID'
/

comment on column BIDDING.BBS_REPLY.PARENT_ID is
'父回复表ID'
/

comment on column BIDDING.BBS_REPLY.CONTENT is
'内容'
/

comment on column BIDDING.BBS_REPLY.CREATOR_ID is
'创建人ID'
/

comment on column BIDDING.BBS_REPLY.CREATE_TIME is
'创建时间'
/

comment on column BIDDING.BBS_REPLY.UPDATE_TIME is
'修改时间'
/

comment on column BIDDING.BBS_REPLY.DELETE_FLAG is
'删除标志（0：未删，1：已删）'
/

comment on column BIDDING.BBS_REPLY.DELETE_TIME is
'删除时间'
/

/*==============================================================*/
/* Table: BID_RANGE                                             */
/*==============================================================*/
create table BIDDING.BID_RANGE 
(
   PKID                 VARCHAR2(50)         not null,
   NAME                 VARCHAR2(50),
   LIST_ORDER           NUMBER,
   CREATE_DATE          DATE                 default SYSDATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.BID_RANGE is
'開標範圍'
/

comment on column BIDDING.BID_RANGE.PKID is
'主鍵id'
/

comment on column BIDDING.BID_RANGE.NAME is
'開標範圍名稱'
/

comment on column BIDDING.BID_RANGE.LIST_ORDER is
'列表序號'
/

comment on column BIDDING.BID_RANGE.CREATE_DATE is
'創建時間'
/

/*==============================================================*/
/* Table: BILL                                                  */
/*==============================================================*/
create table BIDDING.BILL 
(
   PKID                 VARCHAR2(50)         default NULL not null,
   BILL_NO              VARCHAR2(50),
   BILL_STATUS          VARCHAR2(50),
   SEND_USER_PKID       VARCHAR2(50),
   RECV_USER_PKID       VARCHAR2(50),
   GIVE_PRICE_MSTR_PKID VARCHAR2(50),
   PD_TYPE              VARCHAR2(50),
   DELIVER_DATE         DATE,
   DELIVER_ADDRESS      VARCHAR2(50),
   TEL                  VARCHAR2(50),
   F_SHOW_TEL           VARCHAR2(50),
   TOTAL_PRICE          NUMBER,
   MONEY_TYPE           VARCHAR2(50),
   AMOUNT               NUMBER,
   BID_RANGE            VARCHAR2(50),
   BID_START_DATE       DATE,
   BID_END_DATE         DATE,
   DELIVER_WAY          VARCHAR2(50),
   MAKE_REQU            VARCHAR2(500),
   F_OPEN_HIDE          VARCHAR2(1),
   PART_DOC_FILE_REL_ID VARCHAR2(50),
   CREATE_DATE          DATE                 default SYSDATE,
   F_SEND_EVAL          VARCHAR2(50)         default 'N',
   F_RECV_EVAL          VARCHAR2(50)         default 'N',
   PUBLISH_DATE         DATE,
   PD_TYPE_NO           VARCHAR2(5),
   BID_RANGE_VALUE      VARCHAR2(50),
   F_BILL_NO            VARCHAR2(50)
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.BILL is
'訂單表'
/

comment on column BIDDING.BILL.PKID is
'主鍵id(由java後台生成)'
/

comment on column BIDDING.BILL.BILL_NO is
'單號'
/

comment on column BIDDING.BILL.BILL_STATUS is
'訂單狀態（0：待發佈【send保存後】，1：待報價【send發佈後】, 2：待發貨【中標後】，3：待收貨【recv發貨後】，4：待付款【send收貨後】，5：待收款（send付款後），6：已完成，-1：流標，-2：棄標）'
/

comment on column BIDDING.BILL.SEND_USER_PKID is
'發單用戶id'
/

comment on column BIDDING.BILL.RECV_USER_PKID is
'中標接單用戶id'
/

comment on column BIDDING.BILL.GIVE_PRICE_MSTR_PKID is
'中標報價表id'
/

comment on column BIDDING.BILL.PD_TYPE is
'用途（產品類型）'
/

comment on column BIDDING.BILL.DELIVER_DATE is
'交貨時間'
/

comment on column BIDDING.BILL.DELIVER_ADDRESS is
'交貨地點'
/

comment on column BIDDING.BILL.TEL is
'聯繫電話'
/

comment on column BIDDING.BILL.F_SHOW_TEL is
'顯示聯繫電話標誌（Y:是，N:否）'
/

comment on column BIDDING.BILL.TOTAL_PRICE is
'接受總價'
/

comment on column BIDDING.BILL.MONEY_TYPE is
'幣別'
/

comment on column BIDDING.BILL.AMOUNT is
'需求數量'
/

comment on column BIDDING.BILL.BID_RANGE is
'開標範圍（類型）'
/

comment on column BIDDING.BILL.BID_START_DATE is
'競價開始時間'
/

comment on column BIDDING.BILL.BID_END_DATE is
'競價結束時間'
/

comment on column BIDDING.BILL.DELIVER_WAY is
'交貨模式（物流費用）'
/

comment on column BIDDING.BILL.MAKE_REQU is
'製作要求'
/

comment on column BIDDING.BILL.F_OPEN_HIDE is
'公開隱藏標誌（O:公開[open]，H:隱藏[hide]）'
/

comment on column BIDDING.BILL.PART_DOC_FILE_REL_ID is
'零件圖檔文件表關聯id'
/

comment on column BIDDING.BILL.CREATE_DATE is
'創建時間'
/

comment on column BIDDING.BILL.F_SEND_EVAL is
'發單評價標誌（Y：是，N：否）'
/

comment on column BIDDING.BILL.F_RECV_EVAL is
'接單評價標誌（Y：是，N：否）'
/

comment on column BIDDING.BILL.PUBLISH_DATE is
'發佈時間'
/

comment on column BIDDING.BILL.PD_TYPE_NO is
'用途編號（產品類型）'
/

comment on column BIDDING.BILL.BID_RANGE_VALUE is
'開標範圍具體值'
/

comment on column BIDDING.BILL.F_BILL_NO is
'單號標誌（發佈訂單時生成單號前先更新此字段）'
/

/*==============================================================*/
/* Table: BILL_NO                                               */
/*==============================================================*/
create table BIDDING.BILL_NO 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   BILL_NO              VARCHAR2(50)         not null,
   PD_TYPE_NO           VARCHAR2(50),
   CREATE_DATE          DATE                 default trunc(sysdate),
   constraint BILL_NO_PKID primary key (PKID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
       maxextents unlimited
   )
   logging
   tablespace BIDDING,
   constraint BILL_NO_UKID unique (BILL_NO)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
       maxextents unlimited
   )
   logging
   tablespace BIDDING
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.BILL_NO is
'單號表'
/

comment on column BIDDING.BILL_NO.PKID is
'主鍵id'
/

comment on column BIDDING.BILL_NO.BILL_NO is
'單號'
/

comment on column BIDDING.BILL_NO.PD_TYPE_NO is
'產品類型編號'
/

comment on column BIDDING.BILL_NO.CREATE_DATE is
'創建日期'
/

/*==============================================================*/
/* Table: BILL_STATUS                                           */
/*==============================================================*/
create table BIDDING.BILL_STATUS 
(
   ID                   VARCHAR2(50)         not null,
   NAME                 VARCHAR2(50),
   LIST_ORDER           NUMBER,
   CREATE_TIME          DATE                 default SYSDATE,
   UPDATE_TIME          DATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.BILL_STATUS is
'訂單狀態表'
/

comment on column BIDDING.BILL_STATUS.ID is
'狀態ID'
/

comment on column BIDDING.BILL_STATUS.NAME is
'狀態名稱'
/

comment on column BIDDING.BILL_STATUS.LIST_ORDER is
'列表序號'
/

comment on column BIDDING.BILL_STATUS.CREATE_TIME is
'創建時間'
/

comment on column BIDDING.BILL_STATUS.UPDATE_TIME is
'修改時間'
/

/*==============================================================*/
/* Table: BOSS_EMAIL_ADDRESS                                    */
/*==============================================================*/
create table BIDDING.BOSS_EMAIL_ADDRESS 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   EMAIL                VARCHAR2(250),
   LIST_ORDER           NUMBER,
   CREATE_DATE          DATE                 default SYSDATE,
   constraint BOSS_EMAIL_ADDRESS_PKID primary key (PKID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
       maxextents unlimited
   )
   logging
   tablespace BIDDING,
   constraint BOSS_EMAIL_ADDRESS_UK unique (EMAIL)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
       maxextents unlimited
   )
   logging
   tablespace BIDDING
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.BOSS_EMAIL_ADDRESS is
'老闆郵箱地址'
/

comment on column BIDDING.BOSS_EMAIL_ADDRESS.PKID is
'主鍵id'
/

comment on column BIDDING.BOSS_EMAIL_ADDRESS.EMAIL is
'郵箱地址'
/

comment on column BIDDING.BOSS_EMAIL_ADDRESS.LIST_ORDER is
'列表序號'
/

comment on column BIDDING.BOSS_EMAIL_ADDRESS.CREATE_DATE is
'創建時間'
/

/*==============================================================*/
/* Table: BULLETIN                                              */
/*==============================================================*/
create table BIDDING.BULLETIN 
(
   ID                   VARCHAR2(50)         default sys_guid() not null,
   CREATE_TIME          DATE                 default SYSDATE,
   UPDATE_TIME          DATE,
   LIST_CONTENT         VARCHAR2(500),
   LIST_ORDER           NUMBER
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.BULLETIN is
'公告'
/

comment on column BIDDING.BULLETIN.ID is
'公告表id'
/

comment on column BIDDING.BULLETIN.CREATE_TIME is
'創建時間'
/

comment on column BIDDING.BULLETIN.UPDATE_TIME is
'修改時間'
/

comment on column BIDDING.BULLETIN.LIST_CONTENT is
'列表內容'
/

comment on column BIDDING.BULLETIN.LIST_ORDER is
'列表序號'
/

/*==============================================================*/
/* Table: B_DEPT                                                */
/*==============================================================*/
create table BIDDING.B_DEPT 
(
   PKID                 VARCHAR2(40)         not null,
   DEPT_NO              VARCHAR2(20),
   DEPT_NAME            VARCHAR2(500),
   DEPT_LEVEL           VARCHAR2(2),
   DEPT_UPNO            VARCHAR2(20),
   DEPTTYPE             VARCHAR2(50),
   SHOW                 VARCHAR2(20),
   ORDER_NO             NUMBER,
   USE_FLAG             VARCHAR2(5),
   CREATER              VARCHAR2(20),
   CREATEDATE           TIMESTAMP,
   EDITMAN              VARCHAR2(20),
   EDITDATE             TIMESTAMP
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.B_DEPT is
'單位基本資料表'
/

comment on column BIDDING.B_DEPT.PKID is
'主鍵id'
/

comment on column BIDDING.B_DEPT.DEPT_NO is
'單位代碼'
/

comment on column BIDDING.B_DEPT.DEPT_NAME is
'單位名稱'
/

comment on column BIDDING.B_DEPT.DEPT_LEVEL is
'單位層級'
/

comment on column BIDDING.B_DEPT.DEPT_UPNO is
'上級單位代碼'
/

comment on column BIDDING.B_DEPT.DEPTTYPE is
'單位類型'
/

comment on column BIDDING.B_DEPT.SHOW is
'顯示'
/

comment on column BIDDING.B_DEPT.ORDER_NO is
'序號'
/

comment on column BIDDING.B_DEPT.USE_FLAG is
'使用標誌'
/

comment on column BIDDING.B_DEPT.CREATER is
'創建人'
/

comment on column BIDDING.B_DEPT.CREATEDATE is
'創建日期'
/

comment on column BIDDING.B_DEPT.EDITMAN is
'編輯人'
/

comment on column BIDDING.B_DEPT.EDITDATE is
'編輯日期'
/

/*==============================================================*/
/* Index: IDX_B_DEPT_LOOKUP                                     */
/*==============================================================*/
create index BIDDING.IDX_B_DEPT_LOOKUP on BIDDING.B_DEPT (
   PKID ASC
)
pctfree 10
initrans 2
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
    buffer_pool default
)
logging
tablespace BIDDING
/

/*==============================================================*/
/* Table: CONTRACT                                              */
/*==============================================================*/
create table BIDDING.CONTRACT 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   BILL_PKID            VARCHAR2(50),
   PD_NAME              VARCHAR2(50),
   CK_ADDRESS           VARCHAR2(50),
   DELIVER_ADDRESS      VARCHAR2(50),
   DELIVER_DAYS_LIMIT   NUMBER,
   TOTAL_PRICE          NUMBER,
   TOTAL_AMUNT          NUMBER,
   MONEY_COMPUT_WAY     VARCHAR2(50),
   CITY                 VARCHAR2(50),
   A_BROKER             VARCHAR2(50),
   A_ADDRESS            VARCHAR2(50),
   A_TEL                VARCHAR2(50),
   A_DATE               VARCHAR2(50),
   B_BROKER             VARCHAR2(50),
   B_ADDRESS            VARCHAR2(50),
   B_TEL                VARCHAR2(50),
   B_DATE               VARCHAR2(50)
)
pctfree 10
initrans 1
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.CONTRACT is
'合同表'
/

comment on column BIDDING.CONTRACT.PKID is
'主鍵id'
/

comment on column BIDDING.CONTRACT.BILL_PKID is
'訂單表id'
/

comment on column BIDDING.CONTRACT.PD_NAME is
'產品名稱'
/

comment on column BIDDING.CONTRACT.CK_ADDRESS is
'驗收地點'
/

comment on column BIDDING.CONTRACT.DELIVER_ADDRESS is
'交貨地點'
/

comment on column BIDDING.CONTRACT.DELIVER_DAYS_LIMIT is
'交貨時間期限（天數）'
/

comment on column BIDDING.CONTRACT.TOTAL_PRICE is
'總金額'
/

comment on column BIDDING.CONTRACT.TOTAL_AMUNT is
'總套數'
/

comment on column BIDDING.CONTRACT.MONEY_COMPUT_WAY is
'貨款計算時間及方法'
/

comment on column BIDDING.CONTRACT.CITY is
'城市'
/

comment on column BIDDING.CONTRACT.A_BROKER is
'甲方委託代理人'
/

comment on column BIDDING.CONTRACT.A_ADDRESS is
'甲方地址'
/

comment on column BIDDING.CONTRACT.A_TEL is
'甲方電話'
/

comment on column BIDDING.CONTRACT.A_DATE is
'甲方時間'
/

comment on column BIDDING.CONTRACT.B_BROKER is
'乙方委託代理人'
/

comment on column BIDDING.CONTRACT.B_ADDRESS is
'乙方地址'
/

comment on column BIDDING.CONTRACT.B_TEL is
'乙方電話'
/

comment on column BIDDING.CONTRACT.B_DATE is
'乙方時間'
/

/*==============================================================*/
/* Table: DELIVER_ADDRESS                                       */
/*==============================================================*/
create table BIDDING.DELIVER_ADDRESS 
(
   PKID                 VARCHAR2(50)         not null,
   NAME                 VARCHAR2(50),
   LIST_ORDER           NUMBER,
   CREATE_DATE          DATE                 default SYSDATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.DELIVER_ADDRESS is
'交貨地點'
/

comment on column BIDDING.DELIVER_ADDRESS.PKID is
'主鍵id'
/

comment on column BIDDING.DELIVER_ADDRESS.NAME is
'交貨地點名稱'
/

comment on column BIDDING.DELIVER_ADDRESS.LIST_ORDER is
'列表序號'
/

comment on column BIDDING.DELIVER_ADDRESS.CREATE_DATE is
'創建時間'
/

/*==============================================================*/
/* Table: ENTRPS_GROUP                                          */
/*==============================================================*/
create table BIDDING.ENTRPS_GROUP 
(
   PKID                 VARCHAR2(50)         not null,
   NAME                 VARCHAR2(50),
   SECN_CMPY_PKID       VARCHAR2(50),
   ORDER_IN_CMPY        NUMBER
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.ENTRPS_GROUP is
'事業群'
/

comment on column BIDDING.ENTRPS_GROUP.PKID is
'主鍵id'
/

comment on column BIDDING.ENTRPS_GROUP.NAME is
'名稱'
/

comment on column BIDDING.ENTRPS_GROUP.SECN_CMPY_PKID is
'次集團主鍵id'
/

comment on column BIDDING.ENTRPS_GROUP.ORDER_IN_CMPY is
'次集團內序號'
/

/*==============================================================*/
/* Table: FCTRY_ZONE                                            */
/*==============================================================*/
create table BIDDING.FCTRY_ZONE 
(
   PKID                 VARCHAR2(50)         not null,
   NAME                 VARCHAR2(50),
   LIST_ORDER           NUMBER
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.FCTRY_ZONE is
'廠區'
/

comment on column BIDDING.FCTRY_ZONE.PKID is
'主鍵id'
/

comment on column BIDDING.FCTRY_ZONE.NAME is
'名稱'
/

comment on column BIDDING.FCTRY_ZONE.LIST_ORDER is
'列表序號'
/

/*==============================================================*/
/* Table: FILE_DOWNLOAD_RECORD                                  */
/*==============================================================*/
create table BIDDING.FILE_DOWNLOAD_RECORD 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   FILE_ORIGIN_NAME     VARCHAR2(250),
   FILE_SAVE_NAME       VARCHAR2(300),
   FILE_SAVE_PATH       VARCHAR2(250),
   CREATE_DATE          DATE                 default SYSDATE,
   CREATE_USER_PKID     VARCHAR2(50),
   CREATE_USER_IP       VARCHAR2(50)
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.FILE_DOWNLOAD_RECORD is
'文件下載記錄表'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.PKID is
'主鍵id'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.FILE_ORIGIN_NAME is
'文件原始名稱'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.FILE_SAVE_NAME is
'文件保存名稱'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.FILE_SAVE_PATH is
'文件保存路徑'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.CREATE_DATE is
'創建時間'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.CREATE_USER_PKID is
'創建用戶pkid'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.CREATE_USER_IP is
'創建用戶IP地址'
/

/*==============================================================*/
/* Table: GIVE_PRICE_LIST                                       */
/*==============================================================*/
create table BIDDING.GIVE_PRICE_LIST 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   GIVE_PRICE_MSTR_PKID VARCHAR2(50),
   LIST_ORDER           NUMBER
)
pctfree 10
initrans 1
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.GIVE_PRICE_LIST is
'報價排序表'
/

comment on column BIDDING.GIVE_PRICE_LIST.PKID is
'主鍵id'
/

comment on column BIDDING.GIVE_PRICE_LIST.GIVE_PRICE_MSTR_PKID is
'報價表id'
/

comment on column BIDDING.GIVE_PRICE_LIST.LIST_ORDER is
'列表序號（根據規則進行排序的結果）'
/

/*==============================================================*/
/* Table: GIVE_PRICE_MSTR                                       */
/*==============================================================*/
create table BIDDING.GIVE_PRICE_MSTR 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   BILL_PKID            VARCHAR2(50),
   RECV_USER_PKID       VARCHAR2(50),
   GIVE_PRICE_SLAV_REL_ID VARCHAR2(50),
   TOTAL_PRICE          NUMBER,
   DELIVER_DATE         DATE,
   DESCP                VARCHAR2(500),
   F_WIN_BID            VARCHAR2(1)          default 'N',
   CREATE_DATE          DATE                 default SYSDATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.GIVE_PRICE_MSTR is
'報價主表'
/

comment on column BIDDING.GIVE_PRICE_MSTR.PKID is
'主鍵id'
/

comment on column BIDDING.GIVE_PRICE_MSTR.BILL_PKID is
'訂單表id'
/

comment on column BIDDING.GIVE_PRICE_MSTR.RECV_USER_PKID is
'接單用戶id'
/

comment on column BIDDING.GIVE_PRICE_MSTR.GIVE_PRICE_SLAV_REL_ID is
'報價從表關聯id'
/

comment on column BIDDING.GIVE_PRICE_MSTR.TOTAL_PRICE is
'總報價'
/

comment on column BIDDING.GIVE_PRICE_MSTR.DELIVER_DATE is
'交貨日期'
/

comment on column BIDDING.GIVE_PRICE_MSTR.DESCP is
'理由說明'
/

comment on column BIDDING.GIVE_PRICE_MSTR.F_WIN_BID is
'是否中標標誌（Y:是，N:否）'
/

comment on column BIDDING.GIVE_PRICE_MSTR.CREATE_DATE is
'創建日期'
/

/*==============================================================*/
/* Table: GIVE_PRICE_SLAV                                       */
/*==============================================================*/
create table BIDDING.GIVE_PRICE_SLAV 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   GIVE_PRICE_SLAV_REL_ID VARCHAR2(50),
   PART_NAME            VARCHAR2(50),
   PART_AMUNT           NUMBER,
   PART_UNIT_PRICE      NUMBER,
   PART_PRICE_SUM       NUMBER,
   LIST_ORDER           NUMBER,
   CREATE_DATE          DATE                 default SYSDATE,
   PART_DOC_FILE_PKID   VARCHAR2(50)
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.GIVE_PRICE_SLAV is
'報價從表（清單明細）'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PKID is
'主鍵id'
/

comment on column BIDDING.GIVE_PRICE_SLAV.GIVE_PRICE_SLAV_REL_ID is
'報價從表關聯id'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PART_NAME is
'零件名稱'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PART_AMUNT is
'零件數量'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PART_UNIT_PRICE is
'零件單價'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PART_PRICE_SUM is
'零件價格小計'
/

comment on column BIDDING.GIVE_PRICE_SLAV.LIST_ORDER is
'列表序號'
/

comment on column BIDDING.GIVE_PRICE_SLAV.CREATE_DATE is
'創建時間'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PART_DOC_FILE_PKID is
'零件圖檔文件pkid'
/

/*==============================================================*/
/* Table: LEGAL_PERSON                                          */
/*==============================================================*/
create table BIDDING.LEGAL_PERSON 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   LEGAL_PERSON         VARCHAR2(350)
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.LEGAL_PERSON is
'法人表'
/

comment on column BIDDING.LEGAL_PERSON.PKID is
'主鍵id'
/

comment on column BIDDING.LEGAL_PERSON.LEGAL_PERSON is
'法人名稱'
/

/*==============================================================*/
/* Table: MAIN_BILL_NO_REL                                      */
/*==============================================================*/
create table BIDDING.MAIN_BILL_NO_REL 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   BILL_PKID            VARCHAR2(50),
   BILL_NO              VARCHAR2(50),
   CREATE_DATE          DATE                 default SYSDATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.MAIN_BILL_NO_REL is
'訂單主表單號關聯表'
/

comment on column BIDDING.MAIN_BILL_NO_REL.PKID is
'主鍵id'
/

comment on column BIDDING.MAIN_BILL_NO_REL.BILL_PKID is
'訂單表id'
/

comment on column BIDDING.MAIN_BILL_NO_REL.BILL_NO is
'單號'
/

comment on column BIDDING.MAIN_BILL_NO_REL.CREATE_DATE is
'創建日期'
/

/*==============================================================*/
/* Table: PART_DOC_FILE                                         */
/*==============================================================*/
create table BIDDING.PART_DOC_FILE 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   PART_DOC_FILE_REL_ID VARCHAR2(50),
   FILE_ORIGIN_NAME     VARCHAR2(200),
   FILE_SAVE_NAME       VARCHAR2(300),
   FILE_SAVE_PATH       VARCHAR2(200),
   PART_AMUNT           NUMBER,
   LIST_ORDER           NUMBER,
   CREATE_DATE          DATE                 default SYSDATE,
   CREATE_USER_PKID     VARCHAR2(50),
   CREATE_USER_IP       VARCHAR2(50)
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.PART_DOC_FILE is
'零件圖檔文件表'
/

comment on column BIDDING.PART_DOC_FILE.PKID is
'主鍵id'
/

comment on column BIDDING.PART_DOC_FILE.PART_DOC_FILE_REL_ID is
'零件圖檔文件表關聯id'
/

comment on column BIDDING.PART_DOC_FILE.FILE_ORIGIN_NAME is
'文件原始名稱'
/

comment on column BIDDING.PART_DOC_FILE.FILE_SAVE_NAME is
'文件保存名稱'
/

comment on column BIDDING.PART_DOC_FILE.FILE_SAVE_PATH is
'文件保存路徑'
/

comment on column BIDDING.PART_DOC_FILE.PART_AMUNT is
'零件個數'
/

comment on column BIDDING.PART_DOC_FILE.LIST_ORDER is
'列表序號'
/

comment on column BIDDING.PART_DOC_FILE.CREATE_DATE is
'創建時間'
/

comment on column BIDDING.PART_DOC_FILE.CREATE_USER_PKID is
'創建用戶pkid'
/

comment on column BIDDING.PART_DOC_FILE.CREATE_USER_IP is
'創建用戶IP地址'
/

/*==============================================================*/
/* Table: PD_OFFICE                                             */
/*==============================================================*/
create table BIDDING.PD_OFFICE 
(
   PKID                 VARCHAR2(50)         not null,
   NAME                 VARCHAR2(50),
   ENTRPS_GROUP_PKID    VARCHAR2(50),
   ORDER_IN_GROUP       NUMBER
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.PD_OFFICE is
'產品處'
/

comment on column BIDDING.PD_OFFICE.PKID is
'主鍵id'
/

comment on column BIDDING.PD_OFFICE.NAME is
'名稱'
/

comment on column BIDDING.PD_OFFICE.ENTRPS_GROUP_PKID is
'事業群主鍵id'
/

comment on column BIDDING.PD_OFFICE.ORDER_IN_GROUP is
'事業群內序號'
/

/*==============================================================*/
/* Table: PD_TYPE                                               */
/*==============================================================*/
create table BIDDING.PD_TYPE 
(
   PKID                 VARCHAR2(50)         not null,
   NAME                 VARCHAR2(50),
   LIST_ORDER           NUMBER,
   CREATE_DATE          DATE                 default SYSDATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.PD_TYPE is
'產品類型'
/

comment on column BIDDING.PD_TYPE.PKID is
'主鍵id'
/

comment on column BIDDING.PD_TYPE.NAME is
'產品類型名稱'
/

comment on column BIDDING.PD_TYPE.LIST_ORDER is
'列表序號'
/

comment on column BIDDING.PD_TYPE.CREATE_DATE is
'創建時間'
/

/*==============================================================*/
/* Table: RECV_EVAL                                             */
/*==============================================================*/
create table BIDDING.RECV_EVAL 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   BILL_PKID            VARCHAR2(50),
   RECV_USER_PKID       VARCHAR2(50),
   SEND_USER_PKID       VARCHAR2(50),
   SUMMARY_SCORE        NUMBER,
   PAY_RATE_SCORE       NUMBER,
   SVC_ATITU_SCORE      NUMBER,
   SUMMARY_TEXT         VARCHAR2(500),
   F_ANOMUS             VARCHAR2(1),
   CREATE_DATE          DATE                 default SYSDATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.RECV_EVAL is
'接單評價表（接單評價發單）'
/

comment on column BIDDING.RECV_EVAL.PKID is
'主鍵id'
/

comment on column BIDDING.RECV_EVAL.BILL_PKID is
'訂單表id'
/

comment on column BIDDING.RECV_EVAL.RECV_USER_PKID is
'評價用戶id（接單方）'
/

comment on column BIDDING.RECV_EVAL.SEND_USER_PKID is
'被評價用戶id（發單方）'
/

comment on column BIDDING.RECV_EVAL.SUMMARY_SCORE is
'綜合評價分數'
/

comment on column BIDDING.RECV_EVAL.PAY_RATE_SCORE is
'付款時效分數'
/

comment on column BIDDING.RECV_EVAL.SVC_ATITU_SCORE is
'服務態度分數'
/

comment on column BIDDING.RECV_EVAL.SUMMARY_TEXT is
'綜合評價文字'
/

comment on column BIDDING.RECV_EVAL.F_ANOMUS is
'匿名評價標誌（Y:是，N:否）'
/

comment on column BIDDING.RECV_EVAL.CREATE_DATE is
'創建時間'
/

/*==============================================================*/
/* Table: RECV_MNUFC_RANGE                                      */
/*==============================================================*/
create table BIDDING.RECV_MNUFC_RANGE 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   NAME                 VARCHAR2(50),
   RECV_MNUFC_RANGE_REL_ID VARCHAR2(50),
   CREATE_DATE          DATE                 default SYSDATE,
   LIST_ORDER           NUMBER
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.RECV_MNUFC_RANGE is
'接單方加工範圍表'
/

comment on column BIDDING.RECV_MNUFC_RANGE.PKID is
'主鍵id'
/

comment on column BIDDING.RECV_MNUFC_RANGE.NAME is
'接單方加工範圍名稱'
/

comment on column BIDDING.RECV_MNUFC_RANGE.RECV_MNUFC_RANGE_REL_ID is
'接單方加工範圍關聯id'
/

comment on column BIDDING.RECV_MNUFC_RANGE.CREATE_DATE is
'創建時間'
/

comment on column BIDDING.RECV_MNUFC_RANGE.LIST_ORDER is
'列表序號'
/

/*==============================================================*/
/* Table: SECN_CMPY                                             */
/*==============================================================*/
create table BIDDING.SECN_CMPY 
(
   PKID                 VARCHAR2(50)         not null,
   NAME                 VARCHAR2(50),
   FCTRY_ZONE_PKID      VARCHAR2(50),
   ORDER_IN_FCTRY       NUMBER
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.SECN_CMPY is
'次集團'
/

comment on column BIDDING.SECN_CMPY.PKID is
'主鍵id'
/

comment on column BIDDING.SECN_CMPY.NAME is
'名稱'
/

comment on column BIDDING.SECN_CMPY.FCTRY_ZONE_PKID is
'廠區主鍵id'
/

comment on column BIDDING.SECN_CMPY.ORDER_IN_FCTRY is
'廠區內序號'
/

/*==============================================================*/
/* Table: SEND_EVAL                                             */
/*==============================================================*/
create table BIDDING.SEND_EVAL 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   BILL_PKID            VARCHAR2(50),
   SEND_USER_PKID       VARCHAR2(50),
   RECV_USER_PKID       VARCHAR2(50),
   SUMMARY_SCORE        NUMBER,
   OUT_RATE_SCORE       NUMBER,
   OUT_QUAL_SCORE       NUMBER,
   SVC_ATITU_SCORE      NUMBER,
   SUMMARY_TEXT         VARCHAR2(500),
   F_ANOMUS             VARCHAR2(1),
   CREATE_DATE          DATE                 default SYSDATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.SEND_EVAL is
'發單評價表（發單評接單）'
/

comment on column BIDDING.SEND_EVAL.PKID is
'主鍵id'
/

comment on column BIDDING.SEND_EVAL.BILL_PKID is
'訂單表id'
/

comment on column BIDDING.SEND_EVAL.SEND_USER_PKID is
'評價用戶id（發單方）'
/

comment on column BIDDING.SEND_EVAL.RECV_USER_PKID is
'被評價用戶id（接單方）'
/

comment on column BIDDING.SEND_EVAL.SUMMARY_SCORE is
'綜合評價分數'
/

comment on column BIDDING.SEND_EVAL.OUT_RATE_SCORE is
'出貨時效分數'
/

comment on column BIDDING.SEND_EVAL.OUT_QUAL_SCORE is
'出貨質量分數'
/

comment on column BIDDING.SEND_EVAL.SVC_ATITU_SCORE is
'服務態度分數'
/

comment on column BIDDING.SEND_EVAL.SUMMARY_TEXT is
'綜合評價文字'
/

comment on column BIDDING.SEND_EVAL.F_ANOMUS is
'匿名評價標誌（Y:是，N:否）'
/

comment on column BIDDING.SEND_EVAL.CREATE_DATE is
'創建時間'
/

/*==============================================================*/
/* Table: TEST_TIME_PARSE                                       */
/*==============================================================*/
create table BIDDING.TEST_TIME_PARSE 
(
   PKID                 VARCHAR2(50)         default NULL not null,
   TEST_TIME            DATE,
   CREATE_DATE          DATE                 default SYSDATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.TEST_TIME_PARSE is
'測試時間格式表'
/

comment on column BIDDING.TEST_TIME_PARSE.PKID is
'主鍵id'
/

comment on column BIDDING.TEST_TIME_PARSE.TEST_TIME is
'測試時間'
/

comment on column BIDDING.TEST_TIME_PARSE.CREATE_DATE is
'創建時間'
/

/*==============================================================*/
/* Table: USER_INFO                                             */
/*==============================================================*/
create table BIDDING.USER_INFO 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   USERNAME             VARCHAR2(50)         not null,
   PASSWORD             VARCHAR2(250),
   IN_OUT_TYPE          VARCHAR2(50),
   SEND_RECV_TYPE       VARCHAR2(50),
   FCTRY_ZONE           VARCHAR2(50),
   SECN_CMPY            VARCHAR2(50),
   ENTRPS_GROUP         VARCHAR2(50),
   PD_OFFICE            VARCHAR2(50),
   DEPT_NAME            VARCHAR2(200),
   LEGAL_PERSON         VARCHAR2(50),
   COST_CODE            VARCHAR2(50),
   RECV_MNUFC_RANGE_REL_ID VARCHAR2(50),
   BANK_NAME            VARCHAR2(50),
   BANK_ACUNT           VARCHAR2(50),
   BUSIS_MNGR           VARCHAR2(50),
   TEL                  VARCHAR2(50),
   EMAIL                VARCHAR2(250),
   SUMMARY              VARCHAR2(500),
   USER_PIC_FILE_PKID   VARCHAR2(50),
   CREATE_DATE          DATE                 default SYSDATE,
   PHONE                VARCHAR2(50),
   DEPT_CODE            VARCHAR2(50),
   F_VALID              VARCHAR2(5)          default 'Y',
   LATEST_LOGIN_TIME    DATE,
   constraint USER_INFO_PK primary key (PKID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
       maxextents unlimited
   )
   logging
   tablespace BIDDING
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.USER_INFO is
'用戶註冊信息表'
/

comment on column BIDDING.USER_INFO.PKID is
'主鍵id'
/

comment on column BIDDING.USER_INFO.USERNAME is
'賬號名稱'
/

comment on column BIDDING.USER_INFO.PASSWORD is
'賬號密碼'
/

comment on column BIDDING.USER_INFO.IN_OUT_TYPE is
'公司內外類別(in：公司內，out：公司外)'
/

comment on column BIDDING.USER_INFO.SEND_RECV_TYPE is
'發單接單類別(send：發單方，recv：接單方)'
/

comment on column BIDDING.USER_INFO.FCTRY_ZONE is
'所屬廠區'
/

comment on column BIDDING.USER_INFO.SECN_CMPY is
'所屬次集團'
/

comment on column BIDDING.USER_INFO.ENTRPS_GROUP is
'所屬事業群'
/

comment on column BIDDING.USER_INFO.PD_OFFICE is
'所屬產品處'
/

comment on column BIDDING.USER_INFO.DEPT_NAME is
'單位名稱'
/

comment on column BIDDING.USER_INFO.LEGAL_PERSON is
'交易法人'
/

comment on column BIDDING.USER_INFO.COST_CODE is
'費用代碼'
/

comment on column BIDDING.USER_INFO.RECV_MNUFC_RANGE_REL_ID is
'接單方加工範圍關聯id'
/

comment on column BIDDING.USER_INFO.BANK_NAME is
'收/付款銀行名稱'
/

comment on column BIDDING.USER_INFO.BANK_ACUNT is
'收/付款銀行賬號'
/

comment on column BIDDING.USER_INFO.BUSIS_MNGR is
'業務經理'
/

comment on column BIDDING.USER_INFO.TEL is
'聯繫電話（固定電話）'
/

comment on column BIDDING.USER_INFO.EMAIL is
'Email'
/

comment on column BIDDING.USER_INFO.SUMMARY is
'簡介'
/

comment on column BIDDING.USER_INFO.USER_PIC_FILE_PKID is
'頭像文件pkid'
/

comment on column BIDDING.USER_INFO.CREATE_DATE is
'創建時間'
/

comment on column BIDDING.USER_INFO.PHONE is
'聯繫電話（移動電話）'
/

comment on column BIDDING.USER_INFO.DEPT_CODE is
'單位代碼'
/

comment on column BIDDING.USER_INFO.F_VALID is
'賬號有效標誌（Y：有效，N：無效）'
/

comment on column BIDDING.USER_INFO.LATEST_LOGIN_TIME is
'賬號最近登錄時間'
/

/*==============================================================*/
/* Table: USER_PIC_FILE                                         */
/*==============================================================*/
create table BIDDING.USER_PIC_FILE 
(
   PKID                 VARCHAR2(50)         default NULL not null,
   FILE_ORIGIN_NAME     VARCHAR2(200),
   FILE_SAVE_NAME       VARCHAR2(250),
   FILE_SAVE_PATH       VARCHAR2(250),
   CREATE_DATE          DATE                 default SYSDATE
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.USER_PIC_FILE is
'用戶頭像文件表'
/

comment on column BIDDING.USER_PIC_FILE.PKID is
'主鍵id(由java後台生成)'
/

comment on column BIDDING.USER_PIC_FILE.FILE_ORIGIN_NAME is
'文件原始名稱'
/

comment on column BIDDING.USER_PIC_FILE.FILE_SAVE_NAME is
'文件保存名稱'
/

comment on column BIDDING.USER_PIC_FILE.FILE_SAVE_PATH is
'文件保存路徑'
/

comment on column BIDDING.USER_PIC_FILE.CREATE_DATE is
'創建日期'
/

/*==============================================================*/
/* Table: USER_TEMP                                             */
/*==============================================================*/
create table BIDDING.USER_TEMP 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   AU_STATUS            VARCHAR2(50)         default '0',
   USERNAME             VARCHAR2(50),
   PASSWORD             VARCHAR2(250),
   IN_OUT_TYPE          VARCHAR2(50),
   SEND_RECV_TYPE       VARCHAR2(50),
   FCTRY_ZONE           VARCHAR2(50),
   SECN_CMPY            VARCHAR2(50),
   ENTRPS_GROUP         VARCHAR2(50),
   PD_OFFICE            VARCHAR2(50),
   DEPT_NAME            VARCHAR2(50),
   LEGAL_PERSON         VARCHAR2(50),
   COST_CODE            VARCHAR2(50),
   RECV_MNUFC_RANGE_REL_ID VARCHAR2(50),
   BANK_NAME            VARCHAR2(50),
   BANK_ACUNT           VARCHAR2(50),
   BUSIS_MNGR           VARCHAR2(50),
   TEL                  VARCHAR2(50),
   EMAIL                VARCHAR2(50),
   SUMMARY              VARCHAR2(500),
   USER_PIC_FILE_PKID   VARCHAR2(50),
   CREATE_DATE          DATE                 default SYSDATE,
   PHONE                VARCHAR2(50),
   DEPT_CODE            VARCHAR2(50),
   REJ_MSG              VARCHAR2(500),
   constraint USER_TEMP_PK primary key (PKID)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
       maxextents unlimited
   )
   logging
   tablespace BIDDING,
   constraint USER_TEMP_UK unique (USERNAME)
         using index pctfree 10
   initrans 2
   storage
   (
       initial 64K
       next 1024K
       minextents 1
       maxextents unlimited
   )
   logging
   tablespace BIDDING
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.USER_TEMP is
'用戶臨時表'
/

comment on column BIDDING.USER_TEMP.PKID is
'主鍵id'
/

comment on column BIDDING.USER_TEMP.AU_STATUS is
'賬號狀態(0：未審核，1：通過，2：駁回)'
/

comment on column BIDDING.USER_TEMP.USERNAME is
'賬號名稱'
/

comment on column BIDDING.USER_TEMP.PASSWORD is
'賬號密碼'
/

comment on column BIDDING.USER_TEMP.IN_OUT_TYPE is
'公司內外類別(in：公司內，out：公司外)'
/

comment on column BIDDING.USER_TEMP.SEND_RECV_TYPE is
'發單接單類別(send：發單方，recv：接單方)'
/

comment on column BIDDING.USER_TEMP.FCTRY_ZONE is
'所屬廠區'
/

comment on column BIDDING.USER_TEMP.SECN_CMPY is
'所屬次集團'
/

comment on column BIDDING.USER_TEMP.ENTRPS_GROUP is
'所屬事業群'
/

comment on column BIDDING.USER_TEMP.PD_OFFICE is
'所屬產品處'
/

comment on column BIDDING.USER_TEMP.DEPT_NAME is
'單位名稱'
/

comment on column BIDDING.USER_TEMP.LEGAL_PERSON is
'交易法人'
/

comment on column BIDDING.USER_TEMP.COST_CODE is
'費用代碼'
/

comment on column BIDDING.USER_TEMP.RECV_MNUFC_RANGE_REL_ID is
'接單方加工範圍關聯id'
/

comment on column BIDDING.USER_TEMP.BANK_NAME is
'收/付款銀行名稱'
/

comment on column BIDDING.USER_TEMP.BANK_ACUNT is
'收/付款銀行賬號'
/

comment on column BIDDING.USER_TEMP.BUSIS_MNGR is
'業務經理'
/

comment on column BIDDING.USER_TEMP.TEL is
'聯繫電話（固定電話）'
/

comment on column BIDDING.USER_TEMP.EMAIL is
'Email'
/

comment on column BIDDING.USER_TEMP.SUMMARY is
'簡介'
/

comment on column BIDDING.USER_TEMP.USER_PIC_FILE_PKID is
'頭像文件pkid'
/

comment on column BIDDING.USER_TEMP.CREATE_DATE is
'創建時間'
/

comment on column BIDDING.USER_TEMP.PHONE is
'聯繫電話（移動電話）'
/

comment on column BIDDING.USER_TEMP.DEPT_CODE is
'單位代碼'
/

comment on column BIDDING.USER_TEMP.REJ_MSG is
'駁回信息'
/

/*==============================================================*/
/* Table: VERIFICATION_CODE                                     */
/*==============================================================*/
create table BIDDING.VERIFICATION_CODE 
(
   PKID                 VARCHAR2(50)         default sys_guid() not null,
   USER_PKID            VARCHAR2(50),
   CODE                 VARCHAR2(50),
   CREATE_DATE          DATE                 default SYSDATE,
   F_NEWEST             VARCHAR2(1)          default 'Y'
)
pctfree 10
initrans 1
storage
(
    initial 64K
    next 1024K
    minextents 1
    maxextents unlimited
)
tablespace BIDDING
logging
 nocompress
 monitoring
 noparallel
/

comment on table BIDDING.VERIFICATION_CODE is
'驗證碼表'
/

comment on column BIDDING.VERIFICATION_CODE.PKID is
'主鍵id'
/

comment on column BIDDING.VERIFICATION_CODE.USER_PKID is
'用戶pkid'
/

comment on column BIDDING.VERIFICATION_CODE.CODE is
'驗證碼'
/

comment on column BIDDING.VERIFICATION_CODE.CREATE_DATE is
'創建時間'
/

comment on column BIDDING.VERIFICATION_CODE.F_NEWEST is
'是否最新標誌（Y:是，N：否）'
/


create trigger BIDDING.BILL_UPDATE_TRG   after  update on BIDDING.BILL REFERENCING NEW AS NEW OLD AS OLD
declare
  -- local variables here
begin
  BILL_NO_GENERATOR(:new.pkid, :new.pd_type_no);
end BILL_UPDATE_TRG;
/

