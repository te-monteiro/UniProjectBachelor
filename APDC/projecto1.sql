.cd /users
.cd teres
.cd Desktop
.open projecto.db
drop table travel;
create table travel (uuid_t number primary key, price_t number);
insert into travel values (1,1);
insert into travel values (2,1);
insert into travel values (3,1);
insert into travel values (4,1);
insert into travel values (5,1);
					   drop table booking;
create table booking(uuid_b number, price number, uuid_t number);
insert into booking values(10,500,1);
insert into booking values(20,500,2);
insert into booking values(30,500,3);

.header on

select uuid_t,uuid_b from travel inner join booking using(uuid_t);

select sum(price),uuid_b from travel inner join booking using(uuid_t) group by uuid_b, uuid_t;
.print total_price
drop view total_price;
create view total_price as select sum(booking.price) price_t, uuid_t from travel inner join booking using(uuid_t) group by uuid_t;
select * from total_price;

.print inserir novo booking
insert into booking values(20,500,1);
select * from total_price;

.print criar_trigger

--drop trigger calc_total_price;
drop table new_price;
create table new_price (price_t number, uuid_t number primary key);

create trigger calc_total_price
before update of price_t on new_price 
begin
--update travel set price= (select price_t + new.price_t from total_price where uuid_t=new.uuid_t) 
update travel set price_t= 55
where uuid_t=new.uuid_t;
end;
select* from travel;

.print Fim2

update new_price set price_t = 99 where uuid_t=1;
select* from travel;
