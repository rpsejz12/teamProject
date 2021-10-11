create table client(
	id varchar2(20) primary key,
	pw varchar2(30) not null,
	email varchar2(50) not null
);

select * from client;
drop table client;
select * from all_tables;
select from member;
insert into client values('asdf','a12345','abc1234@naver.com');
insert into client values('apple','a12345','apple321@gmail.com');
insert into client values('kiwi777','a12345','kiwi777@naver.com');
insert into client values('admin','1234','admin@naver.com');
CREATE table MOVIE(
	MPK VARCHAR2(20) PRIMARY KEY,
	TITLE VARCHAR2(100) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	MTYPE VARCHAR2(20) NOT NULL,
	MDATE VARCHAR2(100) NOT NULL,
	FILENAME VARCHAR2(100) NOT NULL,
	RATINGAVG INT DEFAULT 0
);

INSERT INTO MOVIE VALUES ('A1117','샹치','이러한 내용이다','ACTION','2002/09/01','file.jsp');

drop table movie;

INSERT INTO MOVIE VALUES('A1001', '샹치', '이러한 내용이다', 'ACTION', SYSDATE, 'FILE1');
INSERT INTO MOVIE VALUES('A1002', '샹치2', '이러한 내용이다2', 'ACTION', SYSDATE, 'FILE2');
INSERT INTO MOVIE VALUES('L1003', '영화1', '이러한 내용이다', 'ACTION', SYSDATE, 'FILE3');
INSERT INTO MOVIE VALUES('A1004', '영화2', '이러한 내용이다2', 'ACTION', SYSDATE, 'FILE4');
INSERT INTO MOVIE VALUES('L1005', '영화3', '이러한 내용이다', 'ACTION', SYSDATE, 'FILE5');
INSERT INTO MOVIE VALUES('D1006', '영화4', '이러한 내용이다2', 'ACTION', SYSDATE, 'FILE6');
INSERT INTO MOVIE VALUES('A1007', '영화5', '이러한 내용이다', 'ACTION', SYSDATE, 'FILE7');
INSERT INTO MOVIE VALUES('D1008', '영화6', '이러한 내용이다2', 'ACTION', SYSDATE, 'FILE8');

SELECT * FROM MOVIE; 

SELECT COUNT(*) FROM MOVIE WHERE MTYPE = '액션' AND TITLE LIKE '모가디슈';

2021/10/07

select rpk from review where mpk ='AC1001';
-- select avg(rpk) from review where mpk = 'AC1001'; 

select avg(rating) from review where mpk = ?;

SELECT * FROM MOVIE WHERE TITLE LIKE '%샹%';

create table review(
   rpk int primary key,
   cmt varchar2(4000) not null, -- comment
   id varchar2(20) not null,
   mpk varchar2(20) not null,
   rdate date not null,
   rating int default 3
);

select * from review;
drop table review;

insert into review values(1,'너무 재밌네요!!','apple','A1001',sysdate);
insert into review values(2,'감동적이네요 ㅜㅜ','kiwi777','A1004',sysdate);
insert into review values(3,'너무 재미없고 지루했어요 ...','asdf','D1006',sysdate);

CREATE SEQUENCE review_seq START WITH 1 INCREMENT BY 1 MAXVALUE 1000 CYCLE NOCACHE;
select * from client;