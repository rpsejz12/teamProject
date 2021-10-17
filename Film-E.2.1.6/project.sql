create table client(
	id varchar(20) primary key,
	pw varchar(30) not null,
	email varchar(50) not null
);

create table movie(
	mpk varchar(20) primary key,
	title varchar(100) not null,
	content varchar(4000) not null,
	mtype varchar(20) not null,
	mdate varchar(100) not null,
	filename varchar(100) not null,
	ratingavg double default 0
);

create table review(
   rpk int primary key auto_increment,
   cmt varchar(4000) not null, -- comment
   id varchar(20) not null,
   mpk varchar(20) not null,
   rdate datetime not null,
   rating double default 3
);

insert into client values('asdf','1234','abc1234@naver.com');
insert into client values('admin','1234','admin@naver.com');

drop table review;
drop table client;
drop table movie;

select * from review;
select * from client;
select * from movie;