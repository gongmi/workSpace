#���ݿ�����ȫ��Сд
create database shopping;

use shopping;

#user.java
create table user 
(
id int primary key auto_increment,
username varchar(40),
password varchar(16),
phone varchar(40),
addr varchar(255),
rdate datetime
);

 insert into user values (null,'gongmi','295783','12345','����ʡ�Ͼ���',now());

#�ݹ�
#category.java
create table category
(
id int primary key auto_increment,
pid int, #pidΪ0�������ڵ�
name varchar(255), 
descr varchar(255),
cno int, #�������, ÿ��ռ��λ, ���99���ӽڵ�
grade int #������, ��1��ʼ
);

#product.java
create table product
(
id int primary key auto_increment,
name varchar(255),
descr varchar(255),
normalprice double,
memberprice double,
pdate datetime,
categoryid int references category(id) #ֻ����categoryid��Ҷ�ӽڵ�
);

#salesorder.java
create table salesorder
(
id int primary key auto_increment,
userid int,
addr varchar(255),
odate datetime,
status int
);

#salesitem.java
create table salesitem 
(
id int primary key auto_increment,
productid int, 
unitprice double, 
pcount int,
orderid int
);

#select pid, sum(pcount) sumCount from salesitem group by pid order by sumCount desc limit 0, 10 


