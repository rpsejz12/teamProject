select * all_tables;
CREATE TABLE HG_AIR 
(
  AIR_NUM VARCHAR2(10) PRIMARY KEY NOT NULL,
  AIR_NAME VARCHAR2(10) NOT NULL,
  DEPARTURE VARCHAR2(30) NOT NULL,
  ARRIVE VARCHAR2(30) NOT NULL,
  FLIGHT_DATE VARCHAR2(20) NOT NULL,
  PRICE NUMBER NOT NULL,
  S_CNT NUMBER DEFAULT 30  -- 좌석개수 기본값 30으로 설정
);

drop table HG_AIR;


-- 운항편번호, 항공기명, 출발지, 도착지, 날짜, 가격, 예약좌석개수
insert into hg_air values ('HG1001','H-1','서울','제주','2021-08-02 12:30',70000, 5);
insert into hg_air values ('HG1002','H-2','제주','서울','2021-08-02 14:00',50000, 30);
insert into hg_air values ('HG1003','H-3','서울','제주','2021-08-02 21:00',20000, 30);
insert into hg_air values ('HG1004','H-4','제주','서울','2021-08-03 08:30',30000, 30);
insert into hg_air values ('HG1005','H-5','서울','제주','2021-08-03 12:00',80000, 30);
insert into hg_air values ('HG1006','H-6','제주','서울','2021-08-03 15:00',40000, 30);
insert into hg_air values ('HG1007','H-7','서울','제주','2021-08-03 20:30',30000, 30);
insert into hg_air values ('HG1008','H-8','제주','서울','2021-08-04 10:00',40000, 25);
insert into hg_air values ('HG1009','H-9','서울','제주','2021-08-04 15:30',70000, 30);
insert into hg_air values ('HG1010','H-10','제주','서울','2021-08-04 19:00',50000, 30);
insert into hg_air values ('HG1011','H-11','서울','제주','2021-08-04 21:30',20000, 30);

select air_num 운항편번호, air_name 항공기명, departure 출발지, arrive 도착지, FLIGHT_DATE 날짜, price 가격, s_num 좌석 from hg_air order by air_num;

update HG_air set s_cnt = s_cnt - 10 where air_num = 'HG1009';
select * from HG_air;
update MEMBER set m_point = 0 where id = 'hong';
-- -------------------------------------------

CREATE TABLE MEMBER 
(
  ID VARCHAR(10) PRIMARY KEY NOT NULL, 
  PW VARCHAR(20) NOT NULL, 
  NAME VARCHAR(20) NOT NULL, 
  M_POINT NUMBER DEFAULT 0   -- 적립 마일리지 기본값 0으로 설정
);

drop table member;

-- id, pw, 이름, 마일리지
insert into member values ('hong', 'asdf', '홍길동', 10000);
insert into member values ('moncher', '1234', '몽셸', 500);
insert into member values ('leon', 'leon12', '레옹', 7000);

select id from member where id = 'hong' and pw = 'asdf';
select * from member where id ='hong';


-- -----------------------------------------------

create table ticket_info (
    t_num varchar2(20) primary key,
    id varchar2(20) not null,
    air_num varchar2(10) not null,
    payment int default 0,  -- 결제 금액 기본값 0으로 설정
    use_point int default 0,  -- 사용 마일리지 기본값 0으로 설정
    p_num int
 );
 
drop table ticket_info;

select * from ticket_info;
drop table ticket_info;

select * from HG_air where to_date(flight_date, 'YYYY-MM-DD HH24:MI' ) > sysdate order by air_num;
update HG_AIR set s_cnt = s_cnt - 3 where air_num= 'HG1001';
select * from HG_AIR;

-- HG_air의 air_num를 ticket_info의 참조키로 설정
ALTER TABLE ticket_info
	ADD
		CONSTRAINT FK_HG_air_TO_ticket_info
		FOREIGN KEY (
			air_num
		)
		REFERENCES HG_air (
			air_num
		);
      

-- member의 id를 ticket_info의 참조키로 설정	  
ALTER TABLE ticket_info
	ADD
		CONSTRAINT FK_member_TO_ticket_info
		FOREIGN KEY (
			id
		)
		REFERENCES member (
			id
		);

-- 티켓번호, id, 항공편번호, 결제금액, 사용마일리지
insert into ticket_info values (2, 'gang', 'HG1001', 50000, 20000, 5);
insert into ticket_info (t_num, id, air_num, payment) values (4, 'moncher', 'HG1010', 50000);  -- 마일리지 사용X -> 기본값 0으로 자동 설정됨