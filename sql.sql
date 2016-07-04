drop table member3 CASCADE CONSTRAINTS PURGE;
drop table point CASCADE CONSTRAINTS PURGE;
drop table aniInfo CASCADE CONSTRAINTS PURGE;
drop table mapInfo CASCADE CONSTRAINTS PURGE;
drop table report CASCADE CONSTRAINTS PURGE;
drop table reportReason CASCADE CONSTRAINTS PURGE;
drop table itemInfo CASCADE CONSTRAINTS PURGE;
drop table aniOwn CASCADE CONSTRAINTS PURGE;
drop table farmInfo CASCADE CONSTRAINTS PURGE;
drop table mapItemInfo CASCADE CONSTRAINTS PURGE;
drop table itemDetail CASCADE CONSTRAINTS PURGE;
drop table board4 CASCADE CONSTRAINTS PURGE;
drop table reBoard CASCADE CONSTRAINTS PURGE;
drop table mapItemGetInfo CASCADE CONSTRAINTS PURGE;


select * from MapItemGetInfo where mapItemNum=1 and id='ds';
select * from MAPITEMGETINFO;
select * from aniInfo order by aniNum Desc;
select * from REBOARD where rDelChk='w';
select * from ANIINFO;
select * from board4;
select * from FARMINFO;
alter table  point modify pointGetReason  varchar(30);
select * from point;
insert into point values(97,'ds',sysdate,5000,'이벤트 아이템 판매');
select * from (select rowNum rn, a.* from (select * from board4 where aniNum=5 and (content like '%공지%' or subject like '%공지%') order by ref desc,re_step) a) where rn between 1 and 10;
select * from board4 where delChk='y';
select * from (select rowNum rn, a.* from (select * from board4 where aniNum=3 order by ref desc,re_step) a)  where rn between 1 and 10 and content like '%tt%' or subject like '%tt%';
select count(*) from board4 where content like '%d%' or subject like '%d%';
select * from board4 where content like '%d%' or subject like '%d%';
select count(*) from board4 where content like 'dd' or subject like 'dd';
select * from (select rowNum rn, a.* from (select * from board4 where content like '%dd%' or subject like '%dd%' order by ref desc,re_step) a)  where rn between 1 and 10;
select count(*) from board4 where delChk='n' and content like 'dd' or subject like 'dd';
select * from REPORTREASON;
update itemDetail set itemUseChk = 'n' where itemDetailNum=6;
select * from itemDetail;
select * from ITEMDETAIL where itemUseChk='y';
update aniOwn set nowStat1 = nowStat1+50 where ownNum=1;
update mapInfo set x = nowStat1+50, y= where id='ds';
select itemStat from itemInfo where itemNum=1;
update itemDetail set itemUseChk = 'n' where itemDetailNum=1;
select aniName1 from aniInfo a,aniOwn b where a.aniNum=b.aniNum; 
select * from aniOwn;
select * from (select rowNum rn, a.* from (select * from board4 order by ref desc,re_step) a) where aniNum=2 and rn between 1 and 10;
select * from board4 order by boardNum desc;
select * from ANIOWN;
select * from MAPINFO;
select * from BOARD4;
delete from ANIOWN;
select * from MEMBER3;
select * from point;
select * from ITEMINFO;
select * from ITEMDETAIL;
select * from ItemDetail where id='ds' order by itemDetailNum desc;
select itemNum from ITEMDETAIL where itemDetailNum=1;
select * from REBOARD;
select * from MAPINFO;
insert into MAPINFO values(12,'ds',33.451928,126.56598);
select * from MapInfo where id='ds3';
ALTER TABLE reportReason MODIFY reportReaVal varchar(50);
select * from REPORT;
select * from aniOwn;
update aniOwn set aniOwnChk='y' where aniOwnChk='t';
select * from AniOwn order by nowStat1+nowStat2 desc;
select * from MapItemInfo where mapItemNum=1;
select * from MAPITEMGETINFO;
select * from MapItemInfo where mapItemNum=1;
select count(*) from MapItemInfo where mapEventChk='y';
select * from MapItemInfo where mapEventChk='y';
ALTER TABLE member3 ADD (reg_date Date);

Create table member3 (
   id Varchar(20) NOT NULL,
   passwd Varchar(20) NOT NULL,
   nickname Varchar(20) NOT NULL,
   warnCount Int,
   dropChk Char(2),
   reg_date Date,
   pointSum Int,
 Primary Key (id));
select * from board4;
 ALTER TABLE member3
ADD (pointSum int);

 Create table point (
   pointNum Int NOT NULL,
   id Varchar(20) NOT NULL,
   pointGetReason Varchar(10) NOT NULL,
   pointGetTime Date,
   getPoint Int NOT NULL,
 Primary Key (pointNum));
 
 select * from point where id='ddd' order by pointNum desc;
  alter table point drop(pointSum);
 select * from point;
  ALTER TABLE point
ADD (pointGetReason Varchar(20));
 alter table point drop(pointGetReason);
 select * from MEMBER3;

 
 
Create table aniInfo (
   aniNum Int NOT NULL,
   aniName1 Varchar(15) NOT NULL,
   aniName2 Varchar(15) NOT NULL,
   aniPic1 Varchar(50) NOT NULL,
   aniPic2 Varchar(50) NOT NULL,
   aniHello1 Varchar(30) NOT NULL,
   aniHello2 Varchar(30) NOT NULL,
   aniHello3 Varchar(30) NOT NULL,
   aniMaxStat1 Int NOT NULL,
   aniMaxStat2 Int,
   aniPrice Int,
   speChk char,
 Primary Key (aniNum));
 
 select * from aniInfo;
 ALTER TABLE aniInfo
update aniInfo set aniPrice = 500;
update aniInfo set speChk = 'n';
ADD (aniPrice int);
ALTER TABLE aniInfo
ADD (speChk char);
 
Create table mapInfo (
   mapNum Int NOT NULL,
   id Varchar(20) NOT NULL,
   x Float NOT NULL,
   y Float NOT NULL,
 Primary Key (mapNum));

Create table report (
   repReaNum Int NOT NULL,
   reportReaNum Int NOT NULL,
   reporterId varchar(20) NOT NULL,
   reportedId varchar(20) NOT NULL,
   repDate Date NOT NULL,
   repHandChk Char(2) NOT NULL,   
   reBoardNum Int,
 Primary Key (repNum));
 
 ALTER TABLE report ADD (reBoardNum Int);
 select * from report;
 delete from report;
 alter table member3 drop(reporterId);
 alter table member3 drop(reportedId);
 ALTER TABLE report ADD (reporterId varchar(20));
 ALTER TABLE report ADD (reportedId varchar(20));
 ALTER TABLE report ADD (content varchar(500));
 select * from member3;
 
 alter table report drop(repNum);
Create table reportReason (
   reportReaNum Int NOT NULL,
   reportReaVal Varchar(20) NOT NULL,
 Primary Key (reportReaNum));

Create table itemInfo (
   itemNum Int NOT NULL,
   itemPic Varchar(50) NOT NULL,
   itemName Varchar(20) NOT NULL,
   itemStat Int NOT NULL,
   itemPrice Int,
 Primary Key (itemNum));

Create table aniOwn (
   ownNum Int NOT NULL,
   id Varchar(20) NOT NULL,
   aniNum Int NOT NULL,
   nowStat1 Int NOT NULL,
   nowStat2 Int NOT NULL,
   leadChk Char(2) NOT NULL,
   aniOwnChk Char(2) NOT NULL,
 Primary Key (ownNum));
 
 ALTER TABLE aniOwn
ADD (aniNaming Varchar(20));

Create table farmInfo (
   farmNum Int NOT NULL,
   id Varchar(20) NOT NULL,
   farmHello Varchar(100) NOT NULL,
   farmName Varchar(20) NOT NULL,
   farmCount Int NOT NULL,
   farmPop Int NOT NULL,
   farmBGCol Varchar(20) NOT NULL,
 Primary Key (farmNum));

Create table mapItemInfo (
   mapItemNum Int NOT NULL,
   mapItemName varchar(20) NOT NULL,
   mapEventChk char(2),
   mapItemPrice int,
   mapItemPic varchar(50),
 Primary Key (mapItemNum));
 
   mapItemOwner varchar(20),
    char(2),
 alter table mapItemInfo drop(mapItemOwner);
 alter table mapItemInfo drop(mapItemOwnChk);
 
 ALTER TABLE mapItemInfo ADD (mapItemName varchar(20));
 
 ALTER TABLE mapItemInfo ADD (mapItemPrice int);
 ALTER TABLE mapItemInfo ADD (mapItemPic varchar(50));
 ALTER TABLE mapItemInfo ADD (mapEventChk char(2));
 ALTER TABLE mapItemInfo ADD (mapItemOwner varchar(20));
 ALTER TABLE mapItemInfo ADD (mapItemOwnChk char(2));
 select * from MAPITEMINFO;
 desc MAPITEMINFO;
 select * from MapItemGetInfo where mapItemNum=1 and id='ds' and mapItemGetChk='y';
 select count(*) from MapItemInfo where mapEventChk='y';
 
Create table itemDetail (
   itemDetailNum Int NOT NULL,
   itemNum Int NOT NULL,
   id Varchar(20) NOT NULL,
   itemUseChk Char(2) NOT NULL,
 Primary Key (itemDetailNum));

Create table board4 (
   boardNum Int NOT NULL,
   aniNum Int NOT NULL,
   id Varchar(20) NOT NULL,
   subject Varchar(50) NOT NULL,
   content Varchar(500) NOT NULL,
   readCount Int NOT NULL,
   ref Int NOT NULL,
   re_step Int NOT NULL,
   re_level Int NOT NULL,
   writeDate Date NOT NULL,
   delChk Char(2) NOT NULL,
   noticeChk Char(2) NOT NULL,
 Primary Key (boardNum));
 
 Create table reBoard (
 	reBoardNum Int NOT NULL,
   boardNum Int NOT NULL,
   id Varchar(20) NOT NULL,
   content Varchar(500) NOT NULL,
   Rref Int NOT NULL,
   Rre_step Int NOT NULL,
   Rre_level Int NOT NULL,
   RwriteDate Date NOT NULL,
   RdelChk Char(2) NOT NULL,
 Primary Key (reBoardNum));
 
 ALTER TABLE reBoard
ADD (reBoardNum int);
alter table reBoard add constraint reBoardNum primary key;
 select * from reBoard;
select * from ReBoard;



Create table mapItemGetInfo (
   id Varchar(20) NOT NULL,
   mapItemNum Int NOT NULL,
   mapItemGetChk Char(2) NOT NULL,
 Primary Key (id,mapItemNum));
select * from tab;


 Create table msg(
   msgNum Int NOT NULL,
   sendId Varchar(20) NOT NULL,
   receiveId Varchar(10) NOT NULL,
   sendTime Date,
   sendDelChk char(2),
   receiveDelChk char(2),
   receiveChk char(2),
   receiveTime Date,
   msgSubject varchar(30),
   msgContent varchar(100),
 Primary Key (msgNum));
  ALTER TABLE msg ADD (msgSubject varchar(30));
  ALTER TABLE msg ADD (msgContent varchar(100));
 select * from msg;
 select count(*) from msg where receiveId='ds';

ALTER TABLE point
ADD FOREIGN KEY (id)
REFERENCES member3(id);
Alter table mapInfo add Foreign Key (id) references member3 (id);
Alter table aniOwn add Foreign Key (id) references member3 (id);
Alter table farmInfo add Foreign Key (id) references member3 (id);
Alter table itemDetail add Foreign Key (id) references member3 (id);
Alter table board4 add Foreign Key (id) references member3 (id);
Alter table reBoard add Foreign Key (id) references member3 (id);
Alter table reBoard add Foreign Key (boardNum) references board4 (boardNum);
Alter table report add Foreign Key (reBoardNum) references reBoard (reBoardNum);
Alter table mapItemGetInfo add Foreign Key (id) references member3 (id);
Alter table aniOwn add Foreign Key (aniNum) references aniInfo (aniNum);
Alter table board4 add Foreign Key (aniNum) references aniInfo (aniNum);
Alter table report add Foreign Key (reportReaNum) references reportReason (reportReaNum);
Alter table itemDetail add Foreign Key (itemNum) references itemInfo (itemNum);
Alter table mapItemGetInfo add Foreign Key (mapItemNum) references mapItemInfo (mapItemNum);
