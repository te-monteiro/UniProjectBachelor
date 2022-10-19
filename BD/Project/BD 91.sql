--Criacao das tabelas
drop table Countries cascade constraints;

create table Countries(
    
    number_ctry number(10),
    name_ctry varchar2(255) not null,
    
    primary key (number_ctry)  
    
    );
drop table Branches cascade constraints;

create table Branches (
    
    code_b number(10) not null,
    name_b varchar2(255) not null unique,
    adress_b varchar2(255) not null,
    phone_b number(9) not null,
    number_ctry number(10) not null,
    
    primary key (code_b),
    foreign key (number_ctry) references Countries
    
);

drop table People cascade constraints;

create table People(
    
    nif number (9) not null,
    name_p varchar2(255) not null,
    email_p varchar2(255) not null unique,
    phone_p number(9) not null,
    address_p varchar2(255) not null,
    birthdate date not null,
    code_b number(10) not null,
    
    primary key(nif),
    foreign key (code_b) references Branches(code_b)

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
    foreign key (name_c) references Categories(name_c),
    foreign key(nif_cli)references Clients(nif),
    foreign key(nif_ass) references Associates(nif) 
);

drop table Helps cascade constraints;

create table Helps (

    number_c number(10) not null,
    nif number(9) not null,
    
    primary key(number_c, nif),
    foreign key (number_c) references Cases(number_c),
    foreign key(nif) references Interns(nif)
    
);

drop table Judged cascade constraints;

create table Judged(
    
    number_c number(10) not null,
    number_co number(10) not null,
    nif number(10) not null,
    
    primary key(number_c, number_co),
    foreign key (number_c) references Cases(number_c),
    foreign key(number_co) references Courts(number_co),
    foreign key(nif) references Judges(nif)

);

drop table Experiences cascade constraints;

create table Experiences(
    
    number_exp number(10),
    type_exp varchar2 (255) not null,
    
    primary key (number_exp)

);

drop table hasExperience cascade constraints;

create table hasExperience(

    number_exp number(10),
    nif number(9),
    
    foreign key (number_exp) references Experiences(number_exp),
    foreign key (nif) references Interns (nif),
    primary key (number_exp, nif)
    
);

drop table documents cascade constraints;

create table Documents(

    number_doc number (10),
    name_doc varchar2 (255) not null,
    descr_text varchar2(1000) not null,
    number_c number(10) not null,
    
    primary key (number_doc),
    foreign key (number_c) references Cases(number_c)

);

--Sequencias

drop sequence seq_doc;

create sequence seq_doc
increment by 1
start with 1;

drop sequence seq_experiences;

create sequence seq_experiences
increment by 1
start with 1;

drop sequence seq_countries;

create sequence seq_countries
increment by 1
start with 1;

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


create or replace view insertAssociates
    as select * from People natural join Workers natural join Associates;

create or replace trigger insertAssociatesTri
    instead of insert on insertAssociates
    for each row
    declare n number;
    begin
    select count(*) into n from People where People.nif = :new.nif;
    if n = 0 then
        insert into People(nif, name_p, email_p, phone_p, address_p, birthdate, code_b)
        values (:new.nif, :new.name_p, :new.email_p, :new.phone_p, :new.address_p, :new.birthdate, :new.code_b);
        insert into Workers(nif, wage) values (:new.nif, :new.wage);
        insert into Associates(nif) values (:new.nif);
    else
        raise_application_error(-20000, 'Associate already exists!');
        
    end if;
end;
/



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
        raise_application_error(-20000, 'Associate already exists!');
        
    end if;
end;
/

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

create or replace view insertClients
    as select * from People natural join Clients ;

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
        raise_application_error(-20000, 'Client already exists!');
        
    end if;
end;
/

create or replace trigger insertCases
    before insert on Cases
    for each row
    declare 
    code_a number;
    code_c number;
    begin
    select code_b into code_a from people where nif = :new.nif_ass;
    select code_b into code_c from people where nif = :new.nif_cli;
    if code_a <> code_c
    then raise_application_error(-20000, 'Associado e Cliente não pertencem ao mesmo escritório!');
    end if;
    end;
    /
    
create or replace trigger insertInternHelp
    before insert on Helps
    for each row
    declare
    code_i number;
    code number;
    begin
    select code_b into code_i from people where nif =:new.nif;
    select code_b into code from Cases natural join Clients natural join People where number_c = :new.number_c;
    if code_i <> code
    then
    raise_application_error(-20000, 'Estagiário e caso não pertencem ao mesmo escritório!');
    end if;
    end;
    /

    
--por caso em cliente


 
create or replace trigger cases_key
    before insert on Cases
    for each row
    declare
    case_n number;
    begin
    select seq_cases.nextval into case_n
    from dual;
    :new.number_c := case_n;
end;
/

create or replace trigger doc_key
    before insert on documents
    for each row
    declare
code number;
    begin
    select seq_doc.nextval into code
    from dual;
    :new.number_doc := code;
end;
/
  

create or replace trigger courts_key
    before insert on Courts
    for each row
    declare
    court_n number;
    begin
    select seq_courts.nextval into court_n
    from dual;
    :new.number_co := court_n;
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


---aqui
--tirar ccascata nao sei
create or replace trigger deleteClient
    after delete on Clients
    for each row
    begin
    delete from Cases where nif_cli = :old.nif;
    end;
/

create or replace trigger deleteCases
    after delete on Cases
    for each row
    begin
    delete from Documents where number_c = :old.number_c;
    delete from Helps where number_c = :old.number_c;
    delete from Judged where number_c = :old.number_c;
    end;
/

create or replace trigger deleteIntern
    after delete on Interns
    for each row
    begin
    delete from hasExperience where nif = :old.nif;
    delete from Helps where nif = :old.nif;
    end;
/

create or replace trigger verificaIdade
  before insert on People
  for each row
  declare age number;
  begin
   select idade(:new.birthdate)
   into age from dual;
   -- Check whether employee age is greater than 18 or not
    if (age < 18) THEN
      raise_application_error(-20000, 'Pessoa tem de ter mais de 18 anos!');
    end if;
  end;
/

create or replace function idade(dataNasc date)
  return number
  is
  age number;
  begin 
  select (sysdate - dataNasc)/365
  into age from dual;
  return age;
  end;
/



--Inserts
insert into Countries values (seq_countries.nextval, 'Portugal');
insert into Countries values (seq_countries.nextval, 'Espanha');
insert into Countries values (seq_countries.nextval, 'Itália');

insert into Branches values (seq_branches.nextval,'TAF Setubal', 'Rua Capitao Jose Pacheco nº8 Setubal', 265235818,1 );
insert into Branches values (seq_branches.nextval,'TAF Lisboa', 'Av. 5 de Outubro', 265432788, 2 );
insert into Branches values (seq_branches.nextval,'TAF Oeiras', 'Avenida', 265876543,3 );

insert into Categories values ('Homicídio');
insert into Categories values ('Divórcio');
insert into Categories values ('Roubo');
insert into Categories values ('Burla');

insert into People
     values (100000000, 'Cliente 1','cliente1@gmail.com', 916758831, 'Avenida estados unidos', to_date('14/04/1978','dd/mm/yyyy') , 1);
     insert into clients
    values (100000000);

insert into People
     values (200000000, 'Associado 1','associate1@gmail.com', 916758831, 'Rua Luisa Fernão', to_date('14/04/1997','dd/mm/yyyy') , 1);
     insert into workers values (200000000, 8000);
     insert into associates values(200000000);

insert into People
    values (300000000, 'Juiz 1', 'juiz1@gmail.com', 916758631,'Rua da Justiça', to_date('14/04/1976','dd/mm/yyyy') , 1   );
     insert into judges values(300000000);

insert into People
     values (400000000, 'Estagário 1','estagiario1@gmail.com', 916358831, 'Rua do Estudante', to_date('14/04/2000','dd/mm/yyyy') , 1);
     insert into workers values (400000000, 120);
     insert into interns values(400000000);
     
insert into Cases 
  values(1,1300,'Burla',100000000,200000000);

insert into Helps
  values (1,400000000);
  
insert into Courts
  values (1, 219675846, 'Tribunal 1', 'Rua da Grande Justiça');

insert into judged
  values (1, 1, 300000000);
  
insert into Experiences
    values (seq_experiences.nextval, 'Participação em casos');

insert into Experiences
    values (seq_experiences.nextval, 'Participação em organização da Empresa');
 
 commit;