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

drop view total_price;
create view total_price as select sum(booking.price) price_t, uuid_t from travel inner join booking using(uuid_t) group by uuid_t;

insert into booking values(20,500,1);
select * from total_price;

create or replace trigger calc_total_price
  instead of update on total_price 
  for each row
begin
  update travel  
    set price_t= (select max(price_t)+ :new.price_t
                    from total_price 
                    where  uuid_t=:new.uuid_t)
       where uuid_t = :new.uuid_t;
end;
/

select * from travel;
update total_price set price_t = 95 where uuid_t=1;
select * from travel;


