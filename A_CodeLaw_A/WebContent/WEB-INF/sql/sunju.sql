CREATE TABLE sunju(
  no NUMBER,
  id VARCHAR2(15),
  name VARCHAR2(10 CHAR),
  sunju_code VARCHAR2(20),
  pass VARCHAR2(15),
  phone VARCHAR2(13),
  email VARCHAR2(100),
  CONSTRAINT sunju_no_pk PRIMARY KEY(no)
);
CREATE SEQUENCE sunju_seq
  MINVALUE 1
  MAXVALUE 9999999
  INCREMENT BY 1
  START WITH 1;
  COMMIT;
  select * from sunju;