-- 创建数据库
drop database if exists JavaEETest;
create database JavaEETest;

use JavaEETest;

-- 建立表
-- 创建用户表
drop table if exists userMsg;
create table userMsg
(
    username varchar(50) not null,
    password varchar(32) not null
);
-- 插入测试数据
insert into userMsg(username,password) values('admin','123');