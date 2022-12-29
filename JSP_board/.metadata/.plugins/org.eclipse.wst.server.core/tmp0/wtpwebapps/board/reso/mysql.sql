
게시판 테이블

create table board(
bno int auto_increment,
title varchar(100),
writer varchar(50),
content text,
cnt int default 0, 
recommend int default 0,
regdate datetime default now(),
primary key(bno));

멤버 테이블

create table member(
email varchar(50),
pwd varchar(50),
nick varchar(50),
reg_at datetime default now(),
last_login datetime,
primary key(email));
