create table a (a number);
insert into a values (1);
insert into a values (2);
insert into a values (3);
insert into a values (4);
insert into a values (5);

create table b(id number, a number);
insert into b values(10,1);
insert into b values(20,2);
insert into b values(30,3);

.header on

select a,id from a inner join b using(a) where b.id <30;
-- sum(a)|id
-- 1|10
-- 2|20

select sum(a),id from a inner join b using(a) where b.id <30 group by id;
-- sum(a)|id
-- 1|10
-- 2|20
create view abc as select sum(a) soma, id from a inner join b using(a) where b.id <30 group by id;
select * from abc;
-- soma|id
-- 1|10
-- 2|20
insert into b values(20,1);
select * from abc;
-- soma|id
-- 1|10
-- 3|20
