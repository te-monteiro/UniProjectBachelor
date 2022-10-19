.cd /users
.cd teres
.cd Desktop
.open projecto.db
drop table a;
create table a (uuid_t number);
insert into a values (1);
insert into a values (2);
insert into a values (3);
insert into a values (4);
insert into a values (5);
					   drop table b;
create table b(id number, price number, uuid_t number);
insert into b values(10,500, 1);
insert into b values(20,500,2);
insert into b values(30,500,3);

.header on

select uuid_t,id from a inner join b using(uuid_t) where b.id <30;

select sum(price),id from a inner join b using(uuid_t) where b.id <30 group by id, uuid_t;

drop view abc;
create view abc as select sum(a) soma, id from a inner join b using(a) where b.id <30 group by id;
select * from abc;

insert into b values(20,500,1);
select * from abc;

create trigger total_price
instead of update of price on abc 
begin
update b set price= new.price
where a=new.a;
end;

update abc set price = 