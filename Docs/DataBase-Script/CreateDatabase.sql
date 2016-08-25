create database EBTest;
use EBTest;

create table Accounts
(
_id bigint primary key AUTO_INCREMENT,
Acc_ID varchar(20) not null,
Acc_name varchar(100) not null,
Acc_pwd varchar(20) not null,
Acc_sex char(4),
Acc_loc varchar(200) not null,
Acc_lvl int not null,
Acc_ryb int not null,
Acc_shop bool not null,
Acc_atn int not null,
Acc_atnd int not null,
Acc_pub int not null,
Acc_no varchar(20),
Acc_name2 varchar(100),
Acc_tel varchar(20),
Acc_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table Shops
(
_id bigint primary key AUTO_INCREMENT,
Shop_ID varchar(20) not null,
Shop_name varchar(100) not null,
Shop_info varchar(500) not null,
Shop_licen varchar(500) not null,
Shop_lvl int not null,
Shop_ryb int not null,
Shop_favo int not null,
Acc_ID varchar(20),
Shop_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table Category
(
_id bigint primary key AUTO_INCREMENT,
Cat_ID varchar(20) not null,
Cat_name varchar(100) not null,
Cat_desc varchar(2000),
Cat_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table Products
(
_id bigint primary key AUTO_INCREMENT,
Prod_ID varchar(20) not null,
Cat_ID varchar(20) not null,
Shop_ID varchar(20) not null,
Prod_name varchar(100) not null,
Prod_desc varchar(2000),
Prod_info varchar(500) not null,
Prod_price float(8, 2) not null,
Prod_num int not null,
Prod_sold int not null,
Prod_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table Attention
(
_id bigint primary key AUTO_INCREMENT,
Acc_ID varchar(20) not null,
Att_ID varchar(20) not null,
Att_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table Courses
(
_id bigint primary key AUTO_INCREMENT,
Crs_ID varchar(20) not null,
Crs_name varchar(100) not null,
Crs_route varchar(200) not null,
Acc_ID varchar(20) not null,
Author_ID varchar(20) not null,
Crs_rep int not null,
Crs_comm int not null,
Crs_like int not null,
Crs_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table Posts
(
_id bigint primary key AUTO_INCREMENT,
Post_ID varchar(20) not null,
Post_name varchar(100) not null,
Post_route varchar(200) not null,
Acc_ID varchar(20) not null,
Author_ID varchar(20) not null,
Post_rep int not null,
Post_comm int not null,
Post_like int not null,
Post_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table RepostCrs
(
_id bigint primary key AUTO_INCREMENT,
Acc_ID varchar(20) not null,
Rep_ID varchar(20) not null,
Crs_ID varchar(20) not null,
Rep_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table RepostPost
(
_id bigint primary key AUTO_INCREMENT,
Acc_ID varchar(20) not null,
Rep_ID varchar(20) not null,
Post_ID varchar(20) not null,
Rep_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table CommentCrs
(
_id bigint primary key AUTO_INCREMENT,
Comm_ID varchar(20) not null,
Comm_desc varchar(2000) not null,
Acc_ID varchar(20) not null,
Rep_ID varchar(20) not null,
Crs_ID varchar(20) not null,
Comm_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table CommentPost
(
_id bigint primary key AUTO_INCREMENT,
Comm_ID varchar(20) not null,
Comm_desc varchar(2000) not null,
Acc_ID varchar(20) not null,
Rep_ID varchar(20) not null,
Post_ID varchar(20) not null,
Comm_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table LikeCrs
(
_id bigint primary key AUTO_INCREMENT,
Like_ID varchar(20) not null,
Acc_ID varchar(20) not null,
Rep_ID varchar(20) not null,
Crs_ID varchar(20) not null,
Like_ryb int not null,
Like_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table LikePost
(
_id bigint primary key AUTO_INCREMENT,
Like_ID varchar(20) not null,
Acc_ID varchar(20) not null,
Rep_ID varchar(20) not null,
Post_ID varchar(20) not null,
Like_ryb int not null,
Like_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table Favourite
(
_id bigint primary key AUTO_INCREMENT,
Acc_ID varchar(20) not null,
Shop_ID varchar(20) not null,
Favo_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table Want
(
_id bigint primary key AUTO_INCREMENT,
Acc_ID varchar(20) not null,
Prod_ID varchar(20) not null,
Want_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table Address
(
_id bigint primary key AUTO_INCREMENT,
Add_ID varchar(20) not null,
Add_info varchar(500) not null,
Acc_ID varchar(20) not null,
Consign varchar(100) not null,
Con_tel varchar(20) not null,
Add_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table Cart
(
_id bigint primary key AUTO_INCREMENT,
Cart_ID varchar(20) not null,
Prod_ID varchar(20) not null,
Acc_ID varchar(20) not null,
Prod_price float(8,2) not null,
Pro_num int not null,
Cart_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table Orders
(
_id bigint primary key AUTO_INCREMENT,
Ord_ID varchar(20) not null,
Ord_date varchar(20) not null,
Acc_ID varchar(20) not null,
Prod_ID varchar(20) not null,
Prod_price float(8,2) not null,
Pro_num int not null,
Ord_paid bool not null,
Ord_sent bool not null,
Ord_received bool not null,
Ord_comment bool not null,
Add_ID varchar(20),
Ord_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

create table CommentProduct
(
_id bigint primary key AUTO_INCREMENT,
Comm_ID varchar(20) not null,
Comm_desc varchar(2000) not null,
Acc_ID varchar(20) not null,
Prod_ID varchar(20) not null,
Ord_ID varchar(20) not null,
Comm_addTime varchar(20)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=UTF8;

