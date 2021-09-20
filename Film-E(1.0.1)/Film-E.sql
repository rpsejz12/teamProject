CREATE table MOVIE(
MPK VARCHAR(20) PRIMARY KEY,
TITLE VARCHAR(100) UNIQUE NOT NULL,
CONTENT VARCHAR(2000) NOT NULL,
MTYPE VARCHAR(20) NOT NULL,
MDATE DATE NOT NULL,
FILENAME VARCHAR(50) NOT NULL
);

drop table movie;

INSERT INTO MOVIE VALUES('A1001', '샹치', '이러한 내용이다', 'ACTION', SYSDATE, 'FILE1');
INSERT INTO MOVIE VALUES('A1002', '샹치2', '이러한 내용이다2', 'ACTION', SYSDATE, 'FILE2');
INSERT INTO MOVIE VALUES('L1003', '영화1', '이러한 내용이다', 'ACTION', SYSDATE, 'FILE3');
INSERT INTO MOVIE VALUES('A1004', '영화2', '이러한 내용이다2', 'ACTION', SYSDATE, 'FILE4');
INSERT INTO MOVIE VALUES('L1005', '영화3', '이러한 내용이다', 'ACTION', SYSDATE, 'FILE5');
INSERT INTO MOVIE VALUES('D1006', '영화4', '이러한 내용이다2', 'ACTION', SYSDATE, 'FILE6');
INSERT INTO MOVIE VALUES('A1007', '영화5', '이러한 내용이다', 'ACTION', SYSDATE, 'FILE7');
INSERT INTO MOVIE VALUES('D1008', '영화6', '이러한 내용이다2', 'ACTION', SYSDATE, 'FILE8');

SELECT MPK FROM MOVIE; 



SELECT * FROM MOVIE WHERE TITLE LIKE '%샹%';

