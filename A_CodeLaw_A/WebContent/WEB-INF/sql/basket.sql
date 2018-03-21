--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-11-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BASKET
--------------------------------------------------------
DROP TABLE basket;
  CREATE TABLE BASKET 
   (	BASKETNO NUMBER, 
	USERID VARCHAR2(50 CHAR), 
    SHIPNO NUMBER,
	PRODUCTNAME VARCHAR2(20 CHAR), 
	PRICE VARCHAR2(20 CHAR), 
	BASKETFILE VARCHAR2(50 CHAR),
    REGDATE DATE,
	CONSTRAINT basket_no_pk PRIMARY KEY(basketNo)
   );

CREATE SEQUENCE basket_seq
  MINVALUE 1
  MAXVALUE 9999999
  INCREMENT BY 1
  START WITH 1;
  