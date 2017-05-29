/**
*   环境：mysql
*/

DROP DATABASE IF EXISTS stallserver;

CREATE DATABASE stallserver;

USE stallserver;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
    telephone varchar(11) primary key COMMENT '用户手机号',
    password varchar(16) not null COMMENT '密码',
    username varchar(15) not null COMMENT '用户姓名',
    palte varchar(15) not null COMMENT '车牌号'
  )  ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS parkinfo;
CREATE TABLE parkinfo (
    parkid INT(10) primary key COMMENT '停车场ID',
    password varchar(16) not null COMMENT '密码',
    name varchar(20) not null COMMENT '停车场名',
    telephone varchar(11) not null COMMENT '停车场联系电话',
    address VARCHAR(50) NOT NULL COMMENT '停车场地址',
    remark VARCHAR(100)  NOT NULL COMMENT '停车场备注'
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE orderinfo (
    id int primary key auto_increment COMMENT '自增ID',
    parkid int not null COMMENT '停车场ID',
    phone varchar(11) not null COMMENT '用户手机号'
    
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user value('11111111111','123','我是谁','ESP1234');
insert into parkinfo value('1111111111','123','天虹','12345678910');
