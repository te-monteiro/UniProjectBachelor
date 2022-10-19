--Criacao das tabelas

drop table Branches cascade constraints;

create table Branches (
    
    code_b number(10) not null,
    name_b varchar2(255) not null,
    adress_b varchar2(255) not null,
    phone_b number(9) not null,
    
    primary key (code_b)
    
);

drop table People cascade constraints;

create table People(
    
    nif number (9) not null,
    name_p varchar2(255) not null,
    email_p varchar2(255) not null,
    phone_p number(9) not null,
    address_p varchar2(255) not null,
    birthdate date not null,
    code_b number(10) not null,
    
    primary key(nif),
    foreign key (code_b) references Branches(code_b) on delete cascade

);

drop table Workers cascade constraints;

create table Workers(
    nif number(9) not null,
    wage number(9) not null,
    
    primary key (nif),
    foreign key(nif) references People(nif) on delete cascade

);

drop table Clients cascade constraints;

create table Clients(
    nif number(9) not null,
    
    primary key (nif),
    foreign key(nif) references People(nif) on delete cascade
    
);

drop table Interns cascade constraints;

create table Interns(
    nif number(9) not null,
    
    primary key(nif),
    foreign key (nif) references Workers(nif) on delete cascade

);

drop table Associates cascade constraints;

create table Associates(
    nif number(9) not null,
    
    primary key(nif),
    foreign key (nif) references Workers(nif) on delete cascade

);


drop table Courts cascade constraints;

create table Courts(

    number_co number(10) not null,
    phone_co number(9) not null,
    name_co varchar2(255) not null,
    address_co varchar2(255) not null,
    
    primary key(number_co)

);

drop table Categories cascade constraints;

create table Categories(

   name_c varchar2(255),
   
   primary key(name_c)
);

drop table Judges cascade constraints;

create table Judges(

    nif number(9) not null,

    primary key(nif), 
    foreign key(nif) references People(nif) on delete cascade 
);

drop table Cases cascade constraints;

create table Cases(
    number_c number(10) not null,
    payment number(10) not null,
    name_c varchar2(10) not null,
    nif_cli number(10) not null,
    nif_ass number(10) not null,

    primary key (number_c),
    foreign key (name_c) references Categories(name_c) on delete cascade,
    foreign key(nif_cli)references Clients(nif) on delete cascade,
    foreign key(nif_ass) references Associates(nif) on delete cascade
);





drop table Helps cascade constraints;

create table Helps (

    number_c number(10) not null,
    nif number(9) not null,
    
    primary key(number_c, nif),
    foreign key (number_c) references Cases(number_c) on delete cascade,
    foreign key(nif) references Interns(nif) on delete cascade
    
);

drop table Judged cascade constraints;

create table Judged(
    
    number_c number(10) not null,
    number_co number(10) not null,
    nif number(10) not null,
    
    primary key(number_c, number_co),
    foreign key (number_c) references Cases(number_c) on delete cascade,
    foreign key(number_co) references Courts(number_co) on delete cascade,
    foreign key(nif) references Judges(nif) on delete cascade

);



--Sequencias
drop sequence seq_branches;

create sequence seq_branches
increment by 1
start with 1;

drop sequence seq_cases;

create sequence seq_cases
increment by 1
start with 1;

drop sequence seq_courts;
create sequence seq_courts
increment by 1
start with 1;

--Criacao de tabelas

create or replace view viewPeopleAtBranch
    as select * from Branches natural join People;

--Associados
create or replace view insertAssociates
    as select * from People natural join Workers natural join Associates;

create or replace trigger insertAssociatesTri
    instead of insert on insertAssociates
    for each row
    declare n number;
    begin
    select count(*) into n from Associates where Associates.nif = :new.nif;
    if n = 0 then
        insert into People(nif, name_p, email_p, phone_p, address_p, birthdate, code_b)
        values (:new.nif, :new.name_p, :new.email_p, :new.phone_p, :new.address_p, :new.birthdate, :new.code_b);
        insert into Workers(nif, wage) values (:new.nif, :new.wage);
        insert into Associates(nif) values (:new.nif);
    else
        raise_application_error(-20000, 'Associado já existe!');
        
    end if;
end;
/

create or replace trigger insertAssociatesUpdate
    instead of update on insertAssociates
    for each row
    begin
    if :new.nif <> :old.nif then
      raise_application_error(-20000, 'Para mudar o NIF tem de criar um novo registo, primeiro apagando este!');
    else
        update People
        set name_p=:new.name_p, email_p=:new.email_p, phone_p= :new.phone_p, address_p=:new.address_p, birthdate=:new.birthdate, code_b=:new.code_b
        where nif = :new.nif;
        update Workers
        set wage=:new.wage
        where nif = :new.nif;
    end if;
end;
/


-- Interns
create or replace view insertInterns
    as select * from People natural join Workers natural join Interns;

create or replace trigger insertInternsTri
    instead of insert on insertInterns
    for each row
    declare n number;
    begin
    select count(*) into n from People where People.nif = :new.nif;
    if n = 0 then
        insert into People(nif, name_p, email_p, phone_p, address_p, birthdate, code_b)
        values (:new.nif, :new.name_p, :new.email_p, :new.phone_p, :new.address_p, :new.birthdate, :new.code_b);
        insert into Workers(nif, wage) values (:new.nif, :new.wage);
        insert into Interns(nif) values (:new.nif);
    else
        raise_application_error(-20000, 'Estagiário ja existe!');
        
    end if;
end;
/

create or replace trigger insertInternsUpdate
    instead of update on insertInterns
    for each row
    begin
    if :new.nif <> :old.nif then
      raise_application_error(-20000, 'Para mudar o NIF tem de criar um novo registo, primeiro apagando este!');
    else
        update People
        set name_p=:new.name_p, email_p=:new.email_p, phone_p= :new.phone_p, address_p=:new.address_p, birthdate=:new.birthdate, code_b=:new.code_b
        where nif = :new.nif;
        update Workers
        set wage=:new.wage
        where nif = :new.nif;
    end if;
end;
/


--Judges 
create or replace view insertJudges
    as select * from People natural join Judges ;

create or replace trigger insertJudgesTri
    instead of insert on insertJudges
    for each row
    declare n number;
    begin
    select count(*) into n from People where People.nif = :new.nif;
    if n = 0 then
        insert into People(nif, name_p, email_p, phone_p, address_p, birthdate, code_b)
        values (:new.nif, :new.name_p, :new.email_p, :new.phone_p, :new.address_p, :new.birthdate, :new.code_b);
        insert into Judges(nif) values (:new.nif);
    else
        raise_application_error(-20000, 'Juíz já existe!');
        
    end if;
end;
/

create or replace trigger insertJudgesUpdate
    instead of update on insertJudges
    for each row
    begin
    if :new.nif <> :old.nif then
      raise_application_error(-20000, 'Para mudar o NIF tem de criar um novo registo, primeiro apagando este!');
    else
        update People
        set name_p=:new.name_p, email_p=:new.email_p, phone_p= :new.phone_p, address_p=:new.address_p, birthdate=:new.birthdate, code_b=:new.code_b
        where nif = :new.nif;
    end if;
end;
/


--Clients
create or replace view insertClients
    as select * from People natural join Clients;

create or replace trigger insertClientsTri
    instead of insert on insertClients
    for each row
    declare n number;
    begin
    select count(*) into n from People where People.nif = :new.nif;
    if n = 0 then
        insert into People(nif, name_p, email_p, phone_p, address_p, birthdate, code_b)
        values (:new.nif, :new.name_p, :new.email_p, :new.phone_p, :new.address_p, :new.birthdate, :new.code_b);
        insert into Clients(nif) values (:new.nif);
    else
        raise_application_error(-20000, 'Cliente já existe!');
        
    end if;
end;
/


create or replace trigger insertClientsUpdate
    instead of update on insertClients
    for each row
    begin
    if :new.nif <> :old.nif then
      raise_application_error(-20000, 'Para mudar o NIF tem de criar um novo registo, primeiro apagando este!');
    else
        update People
        set name_p=:new.name_p, email_p=:new.email_p, phone_p= :new.phone_p, address_p=:new.address_p, birthdate=:new.birthdate, code_b=:new.code_b
        where nif = :new.nif;
    end if;
end;
/

--Cases
--por caso em cliente

create or replace view insertCases
    as select * from Cases;
  
--falta ver se ja ha um caso igual
create or replace trigger insertCasesTri
    instead of insert on insertCases
    for each row
    declare
    code number;
    nif_c number;
    nif_a number;
    begin
    select count(*) into nif_c from people where People.nif = :new.nif_cli;
    select count(*) into nif_a from People where People.nif =:new.nif_ass;
    if nif_c <> 0 and nif_a <>0
    then
    code := seq_cases.nextval;
    insert into Cases values
    (code, :new.payment, :new.name_c, :new.nif_cli, :new.nif_ass);
    else
            raise_application_error(-20000, 'Associado/Cliente não existe!');
end if;
end;
/

create or replace view insertCourts
    as select * from Courts;
    
create or replace trigger insertCourtTrigger
    instead of insert on insertCourts
    begin
    insert into Courts values (seq_courts.nextval, :new.phone_co, :new.name_co, :new.address_co);
    end;
/

create or replace view insertInternAtCase
    as select * from Helps natural join Interns;


create or replace trigger insertInternAtCaseTri
    instead of insert on insertInternAtCase
    for each row
    declare n number;
    begin
    select count (*) into n from People where People.nif = :new.nif;
    if n <>0
    then
    insert into Helps values (:new.number_c, :new.nif);
    else
    raise_application_error(-20000, 'Estagiário não existe!');
    end if;
end;
/
    
create or replace view insertCourtAtCase
    as select * from Judged;
    
create or replace trigger insertCourtAtCaseTri
    instead of insert on insertCourtAtCase
    begin
    insert into Judged values (:new.number_c, :new.number_co, :new.nif);
    end;
    /
    


   
--Inserts

insert into Branches values (seq_branches.nextval,'TAF Setubal', 'Rua Capitao Jose Pacheco nº8 Setubal', 265235818 );
insert into Branches values (seq_branches.nextval,'TAF Lisboa', 'Av. 5 de Outubro', 265432788 );
insert into Branches values (seq_branches.nextval,'TAF Oeiras', 'Avenida', 265876543 );

insert into Categories values ('Homicidio');
insert into Categories values ('Divorcio');
insert into Categories values ('Roubo');
insert into Categories values ('Burla Fiscal');

insert into People
     values (848123456, 'Client1','Client1@gmail.com', 916758831, 'Avenida estados unidos', to_date('14/04/2018','dd/mm/yyyy') , 1);
     insert into clients
    values (848123456);

insert into People
     values (123456788, 'Associate1','Associate1@gmail.com', 916758831, 'Avenida estados unidos', to_date('14/04/2018','dd/mm/yyyy') , 2);
     insert into workers values (123456788, 1000);
     insert into associates values(123456788);
     
insert into People
     values (123465788, 'Intern1','Intern1@gmail.com', 916758831, 'Avenida estados unidos', to_date('14/04/2018','dd/mm/yyyy') , 3);
     insert into workers values (123465788, 100);
     insert into interns values(123465788);
     
insert into People
     values (129865788, 'Judge1','Judge1@gmail.com', 916758831, 'Avenida estados unidos', to_date('14/04/2018','dd/mm/yyyy') , 3);
     insert into judges values (129865788);
     
commit;