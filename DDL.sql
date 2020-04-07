--���̺� �ۼ�

CREATE TABLE test1(
    id VARCHAR2(30) PRIMARY KEY,
    name VARCHAR2(30) NOT NULL
);

CREATE TABLE test2(
    id VARCHAR2(30) PRIMARY KEY,
    birth VARCHAR2(30) NOT NULL,
    FOREIGN KEY(id) REFERENCES test1(id)
);

CREATE TABLE test3(
    id VARCHAR2(30) PRIMARY KEY,
    tel VARCHAR2(30) NOT NULL,
    FOREIGN KEY(id) REFERENCES test1(id)
);

select * from sale;
CREATE TABLE sale(
	sNum NUMBER PRIMARY KEY,
	pNum NUMBER NOT NULL,
	sDate DATE NOT NULL,
	sQty NUMBER NOT NULL,
	cNum NUMBER NOT NULL,
	FOREIGN KEY(cNum) REFERENCES customer(cNum),
FOREIGN KEY(pNum) REFERENCES product(pNum)	
);

CREATE TABLE product_keyword(
    pnum NUMBER,
    keyword VARCHAR(30) NOT NULL,
    CONSTRAINT PK_PRODUCT_KEYWORD PRIMARY KEY (pnum, keyword) 
);

-------------------------------------------------

SELECT id, name, birth, tel
FROM test1
LEFT OUTER JOIN test2 USING(id)
LEFT OUTER JOIN test3 USING(id);

desc member1;
desc member2;

select * from member1
LEFT OUTER JOIN member2 using(id);

select * from score;

select * from tab;


DECLARE
X NUMBER;
BEGIN
DBMS_JOB.SUBMIT
(
X -- ���� ID
,'PHARMACY_INPUT_TEST;' -- ������ ���ν�����
,to_date('03-04-2020 15:31:00','dd/mm/yyyy hh24:mi:ss') --�����ų �ð� ����
,'SYSDATE+2/(1440)' --�ݺ��Ⱓ ����
,FALSE 
);
END;
/

SELECT * FROM USER_SCHEDULER_JOBS; --��ϵ� job
SELECT * FROM USER_SCHEDULER_JOB_ARGS; --job�� arguments
SELECT * FROM USER_SCHEDULER_RUNNING_JOBS; --���� running���� job��������
SELECT * FROM USER_SCHEDULER_JOB_LOG; --job�� log
SELECT * FROM USER_SCHEDULER_JOB_RUN_DETAILS; --job�Ǽ����������Error ����
SELECT * FROM USER_SCHEDULER_PROGRAMS; -- ��ϵ� Program
SELECT * FROM USER_SCHEDULER_PROGRAM_ARGS; -- ���α׷��� �ŰԺ���
SELECT * FROM USER_SCHEDULER_SCHEDULES; --��ϵ� �����췯


drop table test purge;
create table test(
    test date
);

DECLARE
BEGIN
    DBMS_JOB.REMOVE(61);
END;
/


CREATE OR REPLACE PROCEDURE PHARMACY_INPUT_TEST
IS
BEGIN
    INSERT INTO test(test) VALUES(SYSDATE);
    COMMIT;
END;
/

exec PHARMACY_INPUT_TEST();
delete from test;
commit;
select TO_CHAR(test, 'HH24:MI:SS') from test order by test desc;

commit;

--������

select * from seq;

 create sequence customer_seq
        start with 1
        increment by 1
        nomaxvalue
        nocycle
        nocache;

create sequence sale_seq
        start with 1
        increment by 1
        nomaxvalue
        nocycle
        nocache;

create sequence input_seq
        start with 1
        increment by 1
        nomaxvalue
        nocycle
        nocache;

drop sequence product_seq;
create sequence product_seq
        start with 1
        increment by 1
        nomaxvalue
        nocycle
        nocache;
--
        
--
desc product;
--�̸�    ��?       ����           
------- -------- ------------ 
--PNUM  NOT NULL NUMBER(8)    
--PNAME NOT NULL VARCHAR2(30) 
--PRICE NOT NULL VARCHAR2(20) 
--STOCK NOT NULL NUMBER(8)  

desc sale;
--�̸�    ��?       ����     
------- -------- ------ 
--SNUM  NOT NULL NUMBER 
--SDATE          DATE   
--SQTY  NOT NULL NUMBER 
--CNUM  NOT NULL NUMBER 

alter table sale modify cnum null;

desc input;
--�̸�    ��?       ����        
------- -------- --------- 
--INUM  NOT NULL NUMBER(8) 
--PNUM  NOT NULL NUMBER(8) 
--IDATE NOT NULL DATE      
--IQTY  NOT NULL NUMBER(8) 