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
'֪ͨ��'
/

comment on column BIDDING.BBS_NOTICE.ID is
'ID'
/

comment on column BIDDING.BBS_NOTICE.BBS_POST_ID is
'���ӱ�id'
/

comment on column BIDDING.BBS_NOTICE.PARENT_REPLY_ID is
'���ظ���id'
/

comment on column BIDDING.BBS_NOTICE.CHILD_REPLY_ID is
'�ӻظ���id'
/

comment on column BIDDING.BBS_NOTICE.RECEIVE_USER_ID is
'������id�����ظ�������id��'
/

comment on column BIDDING.BBS_NOTICE.SEND_USER_ID is
'������id���ӻظ�������id��'
/

comment on column BIDDING.BBS_NOTICE.CREATE_TIME is
'����ʱ��'
/

comment on column BIDDING.BBS_NOTICE.UPDATE_TIME is
'�޸�ʱ��'
/

comment on column BIDDING.BBS_NOTICE.DELETE_FLAG is
'ɾ����־��0��δɾ��1����ɾ��'
/

comment on column BIDDING.BBS_NOTICE.DELETE_TIME is
'ɾ��ʱ��'
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
'���ӱ�'
/

comment on column BIDDING.BBS_POST.ID is
'ID'
/

comment on column BIDDING.BBS_POST.TITLE is
'����'
/

comment on column BIDDING.BBS_POST.CONTENT is
'����'
/

comment on column BIDDING.BBS_POST.CREATOR_ID is
'������ID'
/

comment on column BIDDING.BBS_POST.CREATE_TIME is
'����ʱ��'
/

comment on column BIDDING.BBS_POST.UPDATE_TIME is
'�޸�ʱ��'
/

comment on column BIDDING.BBS_POST.DELETE_FLAG is
'ɾ����־��0��δɾ��1����ɾ��'
/

comment on column BIDDING.BBS_POST.DELETE_TIME is
'ɾ��ʱ��'
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
'�ظ���'
/

comment on column BIDDING.BBS_REPLY.ID is
'ID'
/

comment on column BIDDING.BBS_REPLY.BBS_POST_ID is
'���ӱ�ID'
/

comment on column BIDDING.BBS_REPLY.PARENT_ID is
'���ظ���ID'
/

comment on column BIDDING.BBS_REPLY.CONTENT is
'����'
/

comment on column BIDDING.BBS_REPLY.CREATOR_ID is
'������ID'
/

comment on column BIDDING.BBS_REPLY.CREATE_TIME is
'����ʱ��'
/

comment on column BIDDING.BBS_REPLY.UPDATE_TIME is
'�޸�ʱ��'
/

comment on column BIDDING.BBS_REPLY.DELETE_FLAG is
'ɾ����־��0��δɾ��1����ɾ��'
/

comment on column BIDDING.BBS_REPLY.DELETE_TIME is
'ɾ��ʱ��'
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
'�_�˹���'
/

comment on column BIDDING.BID_RANGE.PKID is
'���Iid'
/

comment on column BIDDING.BID_RANGE.NAME is
'�_�˹������Q'
/

comment on column BIDDING.BID_RANGE.LIST_ORDER is
'�б���̖'
/

comment on column BIDDING.BID_RANGE.CREATE_DATE is
'�����r�g'
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
'ӆ�α�'
/

comment on column BIDDING.BILL.PKID is
'���Iid(��java��̨����)'
/

comment on column BIDDING.BILL.BILL_NO is
'��̖'
/

comment on column BIDDING.BILL.BILL_STATUS is
'ӆ�Π�B��0�����l�ѡ�send�����᡿��1������r��send�l���᡿, 2�����l؛���И��᡿��3������؛��recv�l؛�᡿��4�������send��؛�᡿��5�����տsend�����ᣩ��6������ɣ�-1�����ˣ�-2�����ˣ�'
/

comment on column BIDDING.BILL.SEND_USER_PKID is
'�l���Ñ�id'
/

comment on column BIDDING.BILL.RECV_USER_PKID is
'�И˽ӆ��Ñ�id'
/

comment on column BIDDING.BILL.GIVE_PRICE_MSTR_PKID is
'�Иˈ�r��id'
/

comment on column BIDDING.BILL.PD_TYPE is
'��;���aƷ��ͣ�'
/

comment on column BIDDING.BILL.DELIVER_DATE is
'��؛�r�g'
/

comment on column BIDDING.BILL.DELIVER_ADDRESS is
'��؛���c'
/

comment on column BIDDING.BILL.TEL is
'�M�Ԓ'
/

comment on column BIDDING.BILL.F_SHOW_TEL is
'�@ʾ�M�Ԓ���I��Y:�ǣ�N:��'
/

comment on column BIDDING.BILL.TOTAL_PRICE is
'���ܿ��r'
/

comment on column BIDDING.BILL.MONEY_TYPE is
'�ńe'
/

comment on column BIDDING.BILL.AMOUNT is
'������'
/

comment on column BIDDING.BILL.BID_RANGE is
'�_�˹�������ͣ�'
/

comment on column BIDDING.BILL.BID_START_DATE is
'���r�_ʼ�r�g'
/

comment on column BIDDING.BILL.BID_END_DATE is
'���r�Y���r�g'
/

comment on column BIDDING.BILL.DELIVER_WAY is
'��؛ģʽ�������M�ã�'
/

comment on column BIDDING.BILL.MAKE_REQU is
'�u��Ҫ��'
/

comment on column BIDDING.BILL.F_OPEN_HIDE is
'���_�[�ؘ��I��O:���_[open]��H:�[��[hide]��'
/

comment on column BIDDING.BILL.PART_DOC_FILE_REL_ID is
'����D�n�ļ����Pid'
/

comment on column BIDDING.BILL.CREATE_DATE is
'�����r�g'
/

comment on column BIDDING.BILL.F_SEND_EVAL is
'�l���u�r���I��Y���ǣ�N����'
/

comment on column BIDDING.BILL.F_RECV_EVAL is
'�ӆ��u�r���I��Y���ǣ�N����'
/

comment on column BIDDING.BILL.PUBLISH_DATE is
'�l�ѕr�g'
/

comment on column BIDDING.BILL.PD_TYPE_NO is
'��;��̖���aƷ��ͣ�'
/

comment on column BIDDING.BILL.BID_RANGE_VALUE is
'�_�˹������wֵ'
/

comment on column BIDDING.BILL.F_BILL_NO is
'��̖���I���l��ӆ�Εr���Ɇ�̖ǰ�ȸ��´��ֶΣ�'
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
'��̖��'
/

comment on column BIDDING.BILL_NO.PKID is
'���Iid'
/

comment on column BIDDING.BILL_NO.BILL_NO is
'��̖'
/

comment on column BIDDING.BILL_NO.PD_TYPE_NO is
'�aƷ��;�̖'
/

comment on column BIDDING.BILL_NO.CREATE_DATE is
'��������'
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
'ӆ�Π�B��'
/

comment on column BIDDING.BILL_STATUS.ID is
'��BID'
/

comment on column BIDDING.BILL_STATUS.NAME is
'��B���Q'
/

comment on column BIDDING.BILL_STATUS.LIST_ORDER is
'�б���̖'
/

comment on column BIDDING.BILL_STATUS.CREATE_TIME is
'�����r�g'
/

comment on column BIDDING.BILL_STATUS.UPDATE_TIME is
'�޸ĕr�g'
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
'����]���ַ'
/

comment on column BIDDING.BOSS_EMAIL_ADDRESS.PKID is
'���Iid'
/

comment on column BIDDING.BOSS_EMAIL_ADDRESS.EMAIL is
'�]���ַ'
/

comment on column BIDDING.BOSS_EMAIL_ADDRESS.LIST_ORDER is
'�б���̖'
/

comment on column BIDDING.BOSS_EMAIL_ADDRESS.CREATE_DATE is
'�����r�g'
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
'����'
/

comment on column BIDDING.BULLETIN.ID is
'�����id'
/

comment on column BIDDING.BULLETIN.CREATE_TIME is
'�����r�g'
/

comment on column BIDDING.BULLETIN.UPDATE_TIME is
'�޸ĕr�g'
/

comment on column BIDDING.BULLETIN.LIST_CONTENT is
'�б����'
/

comment on column BIDDING.BULLETIN.LIST_ORDER is
'�б���̖'
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
'��λ�����Y�ϱ�'
/

comment on column BIDDING.B_DEPT.PKID is
'���Iid'
/

comment on column BIDDING.B_DEPT.DEPT_NO is
'��λ���a'
/

comment on column BIDDING.B_DEPT.DEPT_NAME is
'��λ���Q'
/

comment on column BIDDING.B_DEPT.DEPT_LEVEL is
'��λ�Ӽ�'
/

comment on column BIDDING.B_DEPT.DEPT_UPNO is
'�ϼ���λ���a'
/

comment on column BIDDING.B_DEPT.DEPTTYPE is
'��λ���'
/

comment on column BIDDING.B_DEPT.SHOW is
'�@ʾ'
/

comment on column BIDDING.B_DEPT.ORDER_NO is
'��̖'
/

comment on column BIDDING.B_DEPT.USE_FLAG is
'ʹ�Ø��I'
/

comment on column BIDDING.B_DEPT.CREATER is
'������'
/

comment on column BIDDING.B_DEPT.CREATEDATE is
'��������'
/

comment on column BIDDING.B_DEPT.EDITMAN is
'��݋��'
/

comment on column BIDDING.B_DEPT.EDITDATE is
'��݋����'
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
'��ͬ��'
/

comment on column BIDDING.CONTRACT.PKID is
'���Iid'
/

comment on column BIDDING.CONTRACT.BILL_PKID is
'ӆ�α�id'
/

comment on column BIDDING.CONTRACT.PD_NAME is
'�aƷ���Q'
/

comment on column BIDDING.CONTRACT.CK_ADDRESS is
'��յ��c'
/

comment on column BIDDING.CONTRACT.DELIVER_ADDRESS is
'��؛���c'
/

comment on column BIDDING.CONTRACT.DELIVER_DAYS_LIMIT is
'��؛�r�g���ޣ��씵��'
/

comment on column BIDDING.CONTRACT.TOTAL_PRICE is
'�����~'
/

comment on column BIDDING.CONTRACT.TOTAL_AMUNT is
'���ה�'
/

comment on column BIDDING.CONTRACT.MONEY_COMPUT_WAY is
'؛��Ӌ��r�g������'
/

comment on column BIDDING.CONTRACT.CITY is
'����'
/

comment on column BIDDING.CONTRACT.A_BROKER is
'�׷�ίӚ������'
/

comment on column BIDDING.CONTRACT.A_ADDRESS is
'�׷���ַ'
/

comment on column BIDDING.CONTRACT.A_TEL is
'�׷��Ԓ'
/

comment on column BIDDING.CONTRACT.A_DATE is
'�׷��r�g'
/

comment on column BIDDING.CONTRACT.B_BROKER is
'�ҷ�ίӚ������'
/

comment on column BIDDING.CONTRACT.B_ADDRESS is
'�ҷ���ַ'
/

comment on column BIDDING.CONTRACT.B_TEL is
'�ҷ��Ԓ'
/

comment on column BIDDING.CONTRACT.B_DATE is
'�ҷ��r�g'
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
'��؛���c'
/

comment on column BIDDING.DELIVER_ADDRESS.PKID is
'���Iid'
/

comment on column BIDDING.DELIVER_ADDRESS.NAME is
'��؛���c���Q'
/

comment on column BIDDING.DELIVER_ADDRESS.LIST_ORDER is
'�б���̖'
/

comment on column BIDDING.DELIVER_ADDRESS.CREATE_DATE is
'�����r�g'
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
'�IȺ'
/

comment on column BIDDING.ENTRPS_GROUP.PKID is
'���Iid'
/

comment on column BIDDING.ENTRPS_GROUP.NAME is
'���Q'
/

comment on column BIDDING.ENTRPS_GROUP.SECN_CMPY_PKID is
'�μ��F���Iid'
/

comment on column BIDDING.ENTRPS_GROUP.ORDER_IN_CMPY is
'�μ��F����̖'
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
'�S�^'
/

comment on column BIDDING.FCTRY_ZONE.PKID is
'���Iid'
/

comment on column BIDDING.FCTRY_ZONE.NAME is
'���Q'
/

comment on column BIDDING.FCTRY_ZONE.LIST_ORDER is
'�б���̖'
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
'�ļ����dӛ䛱�'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.PKID is
'���Iid'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.FILE_ORIGIN_NAME is
'�ļ�ԭʼ���Q'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.FILE_SAVE_NAME is
'�ļ��������Q'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.FILE_SAVE_PATH is
'�ļ�����·��'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.CREATE_DATE is
'�����r�g'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.CREATE_USER_PKID is
'�����Ñ�pkid'
/

comment on column BIDDING.FILE_DOWNLOAD_RECORD.CREATE_USER_IP is
'�����Ñ�IP��ַ'
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
'��r�����'
/

comment on column BIDDING.GIVE_PRICE_LIST.PKID is
'���Iid'
/

comment on column BIDDING.GIVE_PRICE_LIST.GIVE_PRICE_MSTR_PKID is
'��r��id'
/

comment on column BIDDING.GIVE_PRICE_LIST.LIST_ORDER is
'�б���̖������Ҏ�t�M������ĽY����'
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
'��r����'
/

comment on column BIDDING.GIVE_PRICE_MSTR.PKID is
'���Iid'
/

comment on column BIDDING.GIVE_PRICE_MSTR.BILL_PKID is
'ӆ�α�id'
/

comment on column BIDDING.GIVE_PRICE_MSTR.RECV_USER_PKID is
'�ӆ��Ñ�id'
/

comment on column BIDDING.GIVE_PRICE_MSTR.GIVE_PRICE_SLAV_REL_ID is
'��r�ı��Pid'
/

comment on column BIDDING.GIVE_PRICE_MSTR.TOTAL_PRICE is
'����r'
/

comment on column BIDDING.GIVE_PRICE_MSTR.DELIVER_DATE is
'��؛����'
/

comment on column BIDDING.GIVE_PRICE_MSTR.DESCP is
'�����f��'
/

comment on column BIDDING.GIVE_PRICE_MSTR.F_WIN_BID is
'�Ƿ��И˘��I��Y:�ǣ�N:��'
/

comment on column BIDDING.GIVE_PRICE_MSTR.CREATE_DATE is
'��������'
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
'��r�ı����������'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PKID is
'���Iid'
/

comment on column BIDDING.GIVE_PRICE_SLAV.GIVE_PRICE_SLAV_REL_ID is
'��r�ı��Pid'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PART_NAME is
'������Q'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PART_AMUNT is
'�������'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PART_UNIT_PRICE is
'����΃r'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PART_PRICE_SUM is
'����r��СӋ'
/

comment on column BIDDING.GIVE_PRICE_SLAV.LIST_ORDER is
'�б���̖'
/

comment on column BIDDING.GIVE_PRICE_SLAV.CREATE_DATE is
'�����r�g'
/

comment on column BIDDING.GIVE_PRICE_SLAV.PART_DOC_FILE_PKID is
'����D�n�ļ�pkid'
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
'���˱�'
/

comment on column BIDDING.LEGAL_PERSON.PKID is
'���Iid'
/

comment on column BIDDING.LEGAL_PERSON.LEGAL_PERSON is
'�������Q'
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
'ӆ�������̖�P��'
/

comment on column BIDDING.MAIN_BILL_NO_REL.PKID is
'���Iid'
/

comment on column BIDDING.MAIN_BILL_NO_REL.BILL_PKID is
'ӆ�α�id'
/

comment on column BIDDING.MAIN_BILL_NO_REL.BILL_NO is
'��̖'
/

comment on column BIDDING.MAIN_BILL_NO_REL.CREATE_DATE is
'��������'
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
'����D�n�ļ���'
/

comment on column BIDDING.PART_DOC_FILE.PKID is
'���Iid'
/

comment on column BIDDING.PART_DOC_FILE.PART_DOC_FILE_REL_ID is
'����D�n�ļ����Pid'
/

comment on column BIDDING.PART_DOC_FILE.FILE_ORIGIN_NAME is
'�ļ�ԭʼ���Q'
/

comment on column BIDDING.PART_DOC_FILE.FILE_SAVE_NAME is
'�ļ��������Q'
/

comment on column BIDDING.PART_DOC_FILE.FILE_SAVE_PATH is
'�ļ�����·��'
/

comment on column BIDDING.PART_DOC_FILE.PART_AMUNT is
'�������'
/

comment on column BIDDING.PART_DOC_FILE.LIST_ORDER is
'�б���̖'
/

comment on column BIDDING.PART_DOC_FILE.CREATE_DATE is
'�����r�g'
/

comment on column BIDDING.PART_DOC_FILE.CREATE_USER_PKID is
'�����Ñ�pkid'
/

comment on column BIDDING.PART_DOC_FILE.CREATE_USER_IP is
'�����Ñ�IP��ַ'
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
'�aƷ̎'
/

comment on column BIDDING.PD_OFFICE.PKID is
'���Iid'
/

comment on column BIDDING.PD_OFFICE.NAME is
'���Q'
/

comment on column BIDDING.PD_OFFICE.ENTRPS_GROUP_PKID is
'�IȺ���Iid'
/

comment on column BIDDING.PD_OFFICE.ORDER_IN_GROUP is
'�IȺ����̖'
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
'�aƷ���'
/

comment on column BIDDING.PD_TYPE.PKID is
'���Iid'
/

comment on column BIDDING.PD_TYPE.NAME is
'�aƷ������Q'
/

comment on column BIDDING.PD_TYPE.LIST_ORDER is
'�б���̖'
/

comment on column BIDDING.PD_TYPE.CREATE_DATE is
'�����r�g'
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
'�ӆ��u�r���ӆ��u�r�l�Σ�'
/

comment on column BIDDING.RECV_EVAL.PKID is
'���Iid'
/

comment on column BIDDING.RECV_EVAL.BILL_PKID is
'ӆ�α�id'
/

comment on column BIDDING.RECV_EVAL.RECV_USER_PKID is
'�u�r�Ñ�id���ӆη���'
/

comment on column BIDDING.RECV_EVAL.SEND_USER_PKID is
'���u�r�Ñ�id���l�η���'
/

comment on column BIDDING.RECV_EVAL.SUMMARY_SCORE is
'�C���u�r�֔�'
/

comment on column BIDDING.RECV_EVAL.PAY_RATE_SCORE is
'����rЧ�֔�'
/

comment on column BIDDING.RECV_EVAL.SVC_ATITU_SCORE is
'���ՑB�ȷ֔�'
/

comment on column BIDDING.RECV_EVAL.SUMMARY_TEXT is
'�C���u�r����'
/

comment on column BIDDING.RECV_EVAL.F_ANOMUS is
'�����u�r���I��Y:�ǣ�N:��'
/

comment on column BIDDING.RECV_EVAL.CREATE_DATE is
'�����r�g'
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
'�ӆη��ӹ�������'
/

comment on column BIDDING.RECV_MNUFC_RANGE.PKID is
'���Iid'
/

comment on column BIDDING.RECV_MNUFC_RANGE.NAME is
'�ӆη��ӹ��������Q'
/

comment on column BIDDING.RECV_MNUFC_RANGE.RECV_MNUFC_RANGE_REL_ID is
'�ӆη��ӹ������Pid'
/

comment on column BIDDING.RECV_MNUFC_RANGE.CREATE_DATE is
'�����r�g'
/

comment on column BIDDING.RECV_MNUFC_RANGE.LIST_ORDER is
'�б���̖'
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
'�μ��F'
/

comment on column BIDDING.SECN_CMPY.PKID is
'���Iid'
/

comment on column BIDDING.SECN_CMPY.NAME is
'���Q'
/

comment on column BIDDING.SECN_CMPY.FCTRY_ZONE_PKID is
'�S�^���Iid'
/

comment on column BIDDING.SECN_CMPY.ORDER_IN_FCTRY is
'�S�^����̖'
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
'�l���u�r���l���u�ӆΣ�'
/

comment on column BIDDING.SEND_EVAL.PKID is
'���Iid'
/

comment on column BIDDING.SEND_EVAL.BILL_PKID is
'ӆ�α�id'
/

comment on column BIDDING.SEND_EVAL.SEND_USER_PKID is
'�u�r�Ñ�id���l�η���'
/

comment on column BIDDING.SEND_EVAL.RECV_USER_PKID is
'���u�r�Ñ�id���ӆη���'
/

comment on column BIDDING.SEND_EVAL.SUMMARY_SCORE is
'�C���u�r�֔�'
/

comment on column BIDDING.SEND_EVAL.OUT_RATE_SCORE is
'��؛�rЧ�֔�'
/

comment on column BIDDING.SEND_EVAL.OUT_QUAL_SCORE is
'��؛�|���֔�'
/

comment on column BIDDING.SEND_EVAL.SVC_ATITU_SCORE is
'���ՑB�ȷ֔�'
/

comment on column BIDDING.SEND_EVAL.SUMMARY_TEXT is
'�C���u�r����'
/

comment on column BIDDING.SEND_EVAL.F_ANOMUS is
'�����u�r���I��Y:�ǣ�N:��'
/

comment on column BIDDING.SEND_EVAL.CREATE_DATE is
'�����r�g'
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
'�yԇ�r�g��ʽ��'
/

comment on column BIDDING.TEST_TIME_PARSE.PKID is
'���Iid'
/

comment on column BIDDING.TEST_TIME_PARSE.TEST_TIME is
'�yԇ�r�g'
/

comment on column BIDDING.TEST_TIME_PARSE.CREATE_DATE is
'�����r�g'
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
'�Ñ��]����Ϣ��'
/

comment on column BIDDING.USER_INFO.PKID is
'���Iid'
/

comment on column BIDDING.USER_INFO.USERNAME is
'�~̖���Q'
/

comment on column BIDDING.USER_INFO.PASSWORD is
'�~̖�ܴa'
/

comment on column BIDDING.USER_INFO.IN_OUT_TYPE is
'��˾����e(in����˾�ȣ�out����˾��)'
/

comment on column BIDDING.USER_INFO.SEND_RECV_TYPE is
'�l�νӆ�e(send���l�η���recv���ӆη�)'
/

comment on column BIDDING.USER_INFO.FCTRY_ZONE is
'���ُS�^'
/

comment on column BIDDING.USER_INFO.SECN_CMPY is
'���ٴμ��F'
/

comment on column BIDDING.USER_INFO.ENTRPS_GROUP is
'�����IȺ'
/

comment on column BIDDING.USER_INFO.PD_OFFICE is
'���ٮaƷ̎'
/

comment on column BIDDING.USER_INFO.DEPT_NAME is
'��λ���Q'
/

comment on column BIDDING.USER_INFO.LEGAL_PERSON is
'���׷���'
/

comment on column BIDDING.USER_INFO.COST_CODE is
'�M�ô��a'
/

comment on column BIDDING.USER_INFO.RECV_MNUFC_RANGE_REL_ID is
'�ӆη��ӹ������Pid'
/

comment on column BIDDING.USER_INFO.BANK_NAME is
'��/�����y�����Q'
/

comment on column BIDDING.USER_INFO.BANK_ACUNT is
'��/�����y���~̖'
/

comment on column BIDDING.USER_INFO.BUSIS_MNGR is
'�I�ս���'
/

comment on column BIDDING.USER_INFO.TEL is
'�M�Ԓ���̶��Ԓ��'
/

comment on column BIDDING.USER_INFO.EMAIL is
'Email'
/

comment on column BIDDING.USER_INFO.SUMMARY is
'����'
/

comment on column BIDDING.USER_INFO.USER_PIC_FILE_PKID is
'�^���ļ�pkid'
/

comment on column BIDDING.USER_INFO.CREATE_DATE is
'�����r�g'
/

comment on column BIDDING.USER_INFO.PHONE is
'�M�Ԓ���Ƅ��Ԓ��'
/

comment on column BIDDING.USER_INFO.DEPT_CODE is
'��λ���a'
/

comment on column BIDDING.USER_INFO.F_VALID is
'�~̖��Ч���I��Y����Ч��N���oЧ��'
/

comment on column BIDDING.USER_INFO.LATEST_LOGIN_TIME is
'�~̖�����䛕r�g'
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
'�Ñ��^���ļ���'
/

comment on column BIDDING.USER_PIC_FILE.PKID is
'���Iid(��java��̨����)'
/

comment on column BIDDING.USER_PIC_FILE.FILE_ORIGIN_NAME is
'�ļ�ԭʼ���Q'
/

comment on column BIDDING.USER_PIC_FILE.FILE_SAVE_NAME is
'�ļ��������Q'
/

comment on column BIDDING.USER_PIC_FILE.FILE_SAVE_PATH is
'�ļ�����·��'
/

comment on column BIDDING.USER_PIC_FILE.CREATE_DATE is
'��������'
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
'�Ñ��R�r��'
/

comment on column BIDDING.USER_TEMP.PKID is
'���Iid'
/

comment on column BIDDING.USER_TEMP.AU_STATUS is
'�~̖��B(0��δ���ˣ�1��ͨ�^��2���g��)'
/

comment on column BIDDING.USER_TEMP.USERNAME is
'�~̖���Q'
/

comment on column BIDDING.USER_TEMP.PASSWORD is
'�~̖�ܴa'
/

comment on column BIDDING.USER_TEMP.IN_OUT_TYPE is
'��˾����e(in����˾�ȣ�out����˾��)'
/

comment on column BIDDING.USER_TEMP.SEND_RECV_TYPE is
'�l�νӆ�e(send���l�η���recv���ӆη�)'
/

comment on column BIDDING.USER_TEMP.FCTRY_ZONE is
'���ُS�^'
/

comment on column BIDDING.USER_TEMP.SECN_CMPY is
'���ٴμ��F'
/

comment on column BIDDING.USER_TEMP.ENTRPS_GROUP is
'�����IȺ'
/

comment on column BIDDING.USER_TEMP.PD_OFFICE is
'���ٮaƷ̎'
/

comment on column BIDDING.USER_TEMP.DEPT_NAME is
'��λ���Q'
/

comment on column BIDDING.USER_TEMP.LEGAL_PERSON is
'���׷���'
/

comment on column BIDDING.USER_TEMP.COST_CODE is
'�M�ô��a'
/

comment on column BIDDING.USER_TEMP.RECV_MNUFC_RANGE_REL_ID is
'�ӆη��ӹ������Pid'
/

comment on column BIDDING.USER_TEMP.BANK_NAME is
'��/�����y�����Q'
/

comment on column BIDDING.USER_TEMP.BANK_ACUNT is
'��/�����y���~̖'
/

comment on column BIDDING.USER_TEMP.BUSIS_MNGR is
'�I�ս���'
/

comment on column BIDDING.USER_TEMP.TEL is
'�M�Ԓ���̶��Ԓ��'
/

comment on column BIDDING.USER_TEMP.EMAIL is
'Email'
/

comment on column BIDDING.USER_TEMP.SUMMARY is
'����'
/

comment on column BIDDING.USER_TEMP.USER_PIC_FILE_PKID is
'�^���ļ�pkid'
/

comment on column BIDDING.USER_TEMP.CREATE_DATE is
'�����r�g'
/

comment on column BIDDING.USER_TEMP.PHONE is
'�M�Ԓ���Ƅ��Ԓ��'
/

comment on column BIDDING.USER_TEMP.DEPT_CODE is
'��λ���a'
/

comment on column BIDDING.USER_TEMP.REJ_MSG is
'�g����Ϣ'
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
'��C�a��'
/

comment on column BIDDING.VERIFICATION_CODE.PKID is
'���Iid'
/

comment on column BIDDING.VERIFICATION_CODE.USER_PKID is
'�Ñ�pkid'
/

comment on column BIDDING.VERIFICATION_CODE.CODE is
'��C�a'
/

comment on column BIDDING.VERIFICATION_CODE.CREATE_DATE is
'�����r�g'
/

comment on column BIDDING.VERIFICATION_CODE.F_NEWEST is
'�Ƿ������I��Y:�ǣ�N����'
/


create trigger BIDDING.BILL_UPDATE_TRG   after  update on BIDDING.BILL REFERENCING NEW AS NEW OLD AS OLD
declare
  -- local variables here
begin
  BILL_NO_GENERATOR(:new.pkid, :new.pd_type_no);
end BILL_UPDATE_TRG;
/

