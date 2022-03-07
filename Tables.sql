Skip to content
Search or jump to…
Pulls
Issues
Marketplace
Explore
 
@bmadison7998 
bmadison7998
/
RevatureBank
Public
Code
Issues
Pull requests
Actions
Projects
Wiki
More
RevatureBank/CreateTables.sql
@bmadison7998
bmadison7998 first commit
Latest commit 2d8dbbc 14 days ago
 History
 1 contributor
46 lines (40 sloc)  1.06 KB
   
use revaturebank;	
CREATE TABLE transaction (
    userID int NOT NULL,
    targetacct int NOT NULL,
    amount double NOT NULL,
    Approved boolean NOT NULL,
    PRIMARY KEY (userID)
);
CREATE TABLE account (
	accountID int NOT NULL auto_increment,
    userID int NOT NULL,
    enabled boolean NOT NULL,
    balance double NOT NULL,
    account varchar(255) NOT NULL,
    PRIMARY KEY (accountID)
);
CREATE TABLE user (
	userid int NOT NULL auto_increment,
    password varchar(255) not null,
    firstname varchar(255) not null,
    middlename varchar(255) not null,
    lastname varchar(255) not null,
    email varchar(255) not null,
    defaultacct int not null,
    PRIMARY KEY (userid)
);
CREATE TABLE logs (
    userid int NOT NULL,
    actioninfo varchar(255) NOT NULL,
    PRIMARY KEY (userid)
);	

select * from user;
select * from transaction;
select * from logs;
select * from account;
show tables;
insert into account values (2,2,true,50.00,"Checking");



INSERT INTO logs VALUES (2," Transferred money from 3, to 2.");


delete from user where userid = 3;
select * from user;
© 2022 GitHub, Inc.
Terms
Privacy
Security
Status
Docs
Contact GitHub
Pricing
API
Training
Blog
About
Loading complete