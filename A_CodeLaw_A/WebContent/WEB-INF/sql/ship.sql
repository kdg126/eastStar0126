CREATE TABLE ship(
  shipno NUMBER PRIMARY KEY,
  province VARCHAR2(20 CHAR) NOT NULL,
  port1 VARCHAR2(20 CHAR) NOT NULL,
  shipname VARCHAR2(20 CHAR) NOT NULL,
  price VARCHAR2(20 CHAR) NOT NULL,
  maxpeople number NOT NULL,
  minpeople number NOT NULL,
  flatfish VARCHAR2(5 CHAR) ,
  squid VARCHAR2(5 CHAR) ,
  rockfish VARCHAR2(5 CHAR),
  octopus VARCHAR2(5 CHAR) ,
  whale VARCHAR2(5 CHAR) ,
  seabream VARCHAR2(5 CHAR),
  mackerel VARCHAR2(5 CHAR) ,
  mullet VARCHAR2(5 CHAR) ,
  shark VARCHAR2(5 CHAR) ,
  greenling VARCHAR2(5 CHAR) ,
  item1 VARCHAR2(10 CHAR) ,
  item2 VARCHAR2(10 CHAR) ,
  item3 VARCHAR2(10 CHAR) ,
  item4 VARCHAR2(10 CHAR) ,
  facility1 VARCHAR2(10 CHAR) ,
  facility2 VARCHAR2(10 CHAR) ,
  facility3 VARCHAR2(10 CHAR) ,
  facility4 VARCHAR2(10 CHAR) ,
  file1 VARCHAR2(1000 CHAR) ,
  file2 VARCHAR2(1000 CHAR) ,
  file3 VARCHAR2(1000 CHAR) ,
  file4 VARCHAR2(1000 CHAR),
  sunjuid varchar2(100 char),
  lat varchar2(100 char),
  lng varchar2(100 char)
  );

CREATE SEQUENCE ship_seq;

INSERT INTO SHIP(SHIPNO, province,PORT1,SHIPNAME,PRICE,MAXPEOPLE,MINPEOPLE,FLATFISH,ROCKFISH,WHALE,ITEM1,ITEM2,ITEM3,ITEM4,FACILITY1,FACILITY2,FACILITY3,FACILITY4,FILE1,SUNJUID, LAT, LNG) 
            values(ship_seq.nextval,'경기도','궁평항','명진1호','85000',18,1,'광어','우럭','고래','안내방송','레이더','어군탐지기','중식제공','여관','횟집','펜션','산책로','upload/myengjin1.jpeg','ggs451','37.116084', '126.681691');
INSERT INTO ship(shipno, province,port1,shipname,price,maxpeople,minpeople,flatfish,rockfish,greenling,item1,item2,item3,item4,facility1,facility2,facility3,facility4,file1,sunjuid, LAT, LNG)
            VALUES(SHIP_SEQ.NEXTVAL,'경기도','궁평항','바다2호','60000',17,12,'광어','우럭','노래미','안내방송','레이더','플로터','전동전기공급장치','모텔','횟집','펜션','산책로','upload/bada2.jpeg','zzzzz1','37.116084', '126.681691');
INSERT INTO SHIP(SHIPNO, province,PORT1,SHIPNAME,PRICE,MAXPEOPLE,MINPEOPLE,FLATFISH,ROCKFISH,ITEM1,ITEM2,ITEM3,ITEM4,FACILITY1,FACILITY2,FACILITY3,FACILITY4,FILE1,SUNJUID, LAT, LNG) 
            values(ship_seq.nextval,'경기도','궁평항','청운호','70000',11,11,'광어','우럭','안내방송','레이더','플로터','전동전기공급장치','이마트','횟집','펜션','바베큐장','upload/chengun1.jpeg','ggs451','37.116084', '126.681691');
INSERT INTO SHIP(SHIPNO, province,PORT1,SHIPNAME,PRICE,MAXPEOPLE,MINPEOPLE,FLATFISH,ROCKFISH,GREENLING,ITEM1,ITEM2,ITEM3,ITEM4,FACILITY1,FACILITY2,FACILITY3,FACILITY4,FILE1,SUNJUID, LAT, LNG)
            VALUES(SHIP_SEQ.NEXTVAL,'경기도','궁평항','청해2호','60000',12,2,'광어','우럭','노래미','안내방송','레이더','플로터','중식제공','모텔','횟집','펜션','여관','upload/chenghea1.jpeg','qwert1','37.116084', '126.681691');
INSERT INTO SHIP(SHIPNO, province,PORT1,SHIPNAME,PRICE,MAXPEOPLE,MINPEOPLE,OCTOPUS,ROCKFISH,GREENLING,SHARK,ITEM1,ITEM2,ITEM3,ITEM4,FACILITY1,FACILITY2,FACILITY3,FACILITY4,FILE1,SUNJUID, LAT, LNG) 
            values(ship_seq.nextval,'경기도','궁평항','대성호','70000',11,2,'광어','문어','노래미','상어','안내방송','레이더','어군탐지기','전동전기공급장치','모텔','횟집','펜션','캠핑장','upload/deasung1.jpeg','aaaad','37.116084', '126.681691');
INSERT INTO SHIP(SHIPNO, province,PORT1,SHIPNAME,PRICE,MAXPEOPLE,MINPEOPLE,MULLET,ROCKFISH,GREENLING,SHARK,ITEM1,ITEM2,ITEM3,ITEM4,FACILITY1,FACILITY2,FACILITY3,FACILITY4,FILE1,SUNJUID, LAT, LNG) 
            VALUES(SHIP_SEQ.NEXTVAL,'경기도','궁평항','광진호','50000',16,10,'숭어','우럭','노래미','상어','안내방송','레이더','어군탐지기','전동전기공급장치','모텔','횟집','펜션','캠핑장','upload/guangjin1.jpeg','kakao','37.116084', '126.681691');
INSERT INTO SHIP(SHIPNO, province,PORT1,SHIPNAME,PRICE,MAXPEOPLE,MINPEOPLE,FLATFISH,ROCKFISH,GREENLING,ITEM1,ITEM2,ITEM3,ITEM4,FACILITY1,FACILITY2,FACILITY3,FACILITY4,FILE1,SUNJUID, LAT, LNG)
            values(ship_seq.nextval,'경기도','궁평항','코리아호','70000',20,18,'광어','우럭','노래미','해수쿨러','레이더','플로터','중식제공','모텔','횟집','펜션','여관','upload/korea1.jpeg','aaaad','37.116084', '126.681691');
INSERT INTO SHIP(SHIPNO, province,PORT1,SHIPNAME,PRICE,MAXPEOPLE,MINPEOPLE,FLATFISH,SEABREAM, WHALE,SHARK,GREENLING,ITEM1,ITEM2,ITEM3,ITEM4,FACILITY1,FACILITY2,FACILITY3,FACILITY4,FILE1,FILE2,FILE3,SUNJUID, LAT, LNG) 
            VALUES(SHIP_SEQ.NEXTVAL,'경기도','궁평항','마도로스호','80000',22,6,'광어','참돔','고래','상어','노래미','해수쿨러','레이더','플로터','중식제공','모텔','횟집','펜션','산책로','upload/madoros1.jpeg','upload/madoros2.jpeg','upload/madoros3.jpeg','kakao','37.116084', '126.681691');   
INSERT INTO SHIP(SHIPNO, province,PORT1,SHIPNAME,PRICE,MAXPEOPLE,MINPEOPLE,FLATFISH,SEABREAM, WHALE,GREENLING,ITEM1,ITEM2,ITEM3,ITEM4,FACILITY1,FACILITY2,FACILITY3,FACILITY4,FILE1,SUNJUID, LAT, LNG) 
            VALUES(SHIP_SEQ.NEXTVAL,'경기도','평택항','파라호','80000',9,3,'광어','참돔','고래','노래미','안내방송','레이더','플로터','중식제공','여관','횟집','펜션','산책로','upload/para1.jpeg','kakao','36.959456', '126.843401'); 
commit;
            

            commit;
            select * from ship order by shipno desc;




