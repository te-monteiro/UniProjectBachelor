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
    name_c varchar2(255) not null,
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

create table documents(

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

--Inserts
insert into Countries values (seq_countries.nextval, 'Portugal');
insert into Countries values (seq_countries.nextval, 'Espanha');
insert into Countries values (seq_countries.nextval, 'It�lia');

insert into Branches values (seq_branches.nextval,'TAF Set�bal', 'Rua Capitao Jose Pacheco n�8', 265235818,1 );
insert into Branches values (seq_branches.nextval,'TAF Lisboa', 'Avenida de Roma n�22', 265432788, 1 );
insert into Branches values (seq_branches.nextval,'TAF Madrid', 'Passeo del Prado n�5', 265876543,2 );
insert into Branches values (seq_branches.nextval,'TAF Rome', 'Via dei Fori Imperiali n�16', 265876543,3 );

insert into Categories values ('Div�rcio');
insert into Categories values ('Roubo Autom�vel');
insert into Categories values ('Burla Financeira');
insert into Categories values ('Crime Inform�tico');
insert into Categories values ('Tentativa de assassinato');

--Experiencias 
insert into Experiences
    values (seq_experiences.nextval, 'Participa��o em casos');
insert into Experiences
    values (seq_experiences.nextval, 'Participa��o em organiza��o da Empresa');
insert into Experiences
    values (seq_experiences.nextval, 'Participa��o em Tribunal');
insert into Experiences
    values (seq_experiences.nextval, 'Participa��o em Atividade de Escrit�rio');
insert into Experiences
    values (seq_experiences.nextval, 'Apoio ao Cliente');
    
--Tribunais
insert into Courts
  values (seq_courts.nextval, 285647388, 'Tribunal Grande Justi�a', 'Rua da Grande Justi�a n�20');
insert into Courts
  values (seq_courts.nextval, 219473554, 'Tribunal Jo�o Direito', 'Avenida Guerra Junqueiro n�31');
insert into Courts
  values (seq_courts.nextval, 218574665, 'Tribunal Mayor Juan', 'C/ Jos� mat�a, 22');
insert into Courts
  values (seq_courts.nextval, 219574456, 'Tribunal Adalrico Pisani', 'Via Nicola Mignogna, 18');


--Clientes Set�bal
insert into People
     values (868463547, 'Sara Ribeiro Almeida','sara.almeida@gmail.com', 918574536, 'Rua Engenheiro Duarte Pacheco n�61', to_date('06/03/1965','dd/mm/yyyy') , 1);
     insert into clients
        values (868463547);
insert into People
     values (875846389, 'David Pinto Ribeiro','david.pintorib@gmail.com', 927658768, 'Avenida Rep�blica n�52', to_date('04/02/1990','dd/mm/yyyy') , 1);
     insert into clients
        values (875846389);
insert into People
     values (995463758, 'Felipe Silva Rodrigues','feliperodrigues@gmail.com', 937584398, 'Rua da Alegria n�74', to_date('21/07/1985','dd/mm/yyyy') , 1);
     insert into clients
        values (995463758);
        
--Clientes Lisboa
insert into People
     values (979584758, 'Ana Silva Cotovio','ana.cotovio@gmail.com', 939275886, 'Rua Eleno Domingues n�2', to_date('08/01/1975','dd/mm/yyyy') , 2);
     insert into clients
        values (979584758);
insert into People
     values (916574856, 'Ant�nio Feliz Juli�o','antonio.juliao@gmail.com', 919234768, 'Avenida Fiel n�5', to_date('14/03/1993','dd/mm/yyyy') , 2);
     insert into clients
        values (916574856);
insert into People
     values (191748377, 'Jo�o Sousa Fel�cio','joaofelicio@gmail.com', 917584459, 'Rua Antonio Valmor n�70', to_date('22/09/1945','dd/mm/yyyy') , 2);
     insert into clients
        values (191748377);
insert into People
     values (707465883, 'Filipa Costa Gomes','filipagomes@gmail.com', 912758833, 'Avenida Liz Lopes n�51', to_date('15/10/1979','dd/mm/yyyy') , 2);
     insert into clients
        values (707465883);


-- Clientes Madrid
insert into People
     values (992657485, 'Nadal Medrano Valverde','nadalvalverde@gmail.com', 937584398, 'Rua da Alegria n�74', to_date('21/07/1985','dd/mm/yyyy') , 3);
     insert into clients
        values (992657485);
insert into People
     values (768836446, 'Eluney Montoya Ruiz','eluney.ruiz@gmail.com', 919375869, 'Plaza Col�n n�97', to_date('16/08/1959','dd/mm/yyyy') , 3);
     insert into clients
        values (768836446);
insert into People
     values (971453758, 'Gracia Tovar Canales','graciacanales@gmail.com', 937584398, 'C/ Rosa de los Vientos n�82', to_date('21/07/1985','dd/mm/yyyy') , 3);
     insert into clients
        values (971453758);
        
-- Clientes Roma
insert into People
     values (917594778, 'Fausto Onio','faustonio@gmail.com', 937584398, 'Corso Garibaldi n�79', to_date('01/02/1975','dd/mm/yyyy') , 4);
     insert into clients
        values (917594778);
insert into People
     values (911748355, 'Algiso Trevisano','algisotrevisano@gmail.com', 919375869, 'Via Stadera n�26', to_date('06/01/1968','dd/mm/yyyy') , 4);
     insert into clients
        values (911748355);
insert into People
     values (119234565, 'Vincenzo Beneventi','vinbeneventi@gmail.com', 937584398, 'Via Sergente Maggiore n�90', to_date('26/06/1984','dd/mm/yyyy') , 4);
     insert into clients
        values (119234565);

--Associados Setubal
insert into People
     values (369072589, 'Camila Azevedo Fernandes','camila.fernandes@gmail.com', 919375869, 'Avenida Visconde Valmor n�46', to_date('16/08/1959','dd/mm/yyyy') , 1);
     insert into workers
        values (369072589, 5200);
     insert into associates
       values (369072589);
insert into People
     values (457848969, 'Ant�nio Fernandes','antoniofernandes@gmail.com', 919375869, 'Rua Nuno Jo�o n�26', to_date('06/01/1968','dd/mm/yyyy') , 1);
     insert into workers
        values (457848969, 5200);
     insert into associates
       values (457848969);
insert into People
     values (697605771, 'Vincente Lizo','vicentelizo@gmail.com', 937584398, 'Rua Margarida Garcia n�90', to_date('26/06/1984','dd/mm/yyyy') , 1);
    insert into workers
        values (697605771, 5200);
     insert into associates
       values (697605771);
        
--Associados Lisboa
insert into People
     values (868100127, 'Jo�o Luis Felipe','joaofelipe@gmail.com', 938438857, 'Rua C�sar Moutinho n�79', to_date('01/02/1975','dd/mm/yyyy') , 2);
     insert into workers
        values (868100127, 5200);
     insert into associates
       values (868100127);
      
insert into People
     values (657106499, 'Madalena Gon�alves Lopes','madalenalopes@gmail.com', 917584566, 'Rua Luis Fel�cio n�26', to_date('06/01/1968','dd/mm/yyyy') , 2);
     insert into workers
        values (657106499, 5200);
     insert into associates
       values (657106499);
insert into People
     values (347655654, 'Joana Vicente','joanavicente@gmail.com', 928463374, 'Rua Ruiane Fernandes n�90', to_date('26/06/1984','dd/mm/yyyy') , 2);
     insert into workers
        values (347655654, 190);
     insert into interns
       values (347655654);
        
--Associados Madrid
insert into People
     values (267872914, 'Eleonora Arce Mojica','eleonoraarce@gmail.com', 919332869, 'Avenida Cervantes n�41', to_date('16/08/1959','dd/mm/yyyy') , 3);
     insert into workers
        values (267872914, 5200);
     insert into associates
       values (267872914);
insert into People
     values (834620863, 'Ecio Maya Corral','eciomaya@gmail.com', 919376569, 'Avda. Explanada Barnuevo, 41', to_date('06/01/1968','dd/mm/yyyy') , 3);
     insert into workers
        values (834620863, 5200);
     insert into associates
       values (834620863);
insert into People
     values (994157965, 'Huaman Caballero Ram�n','huamancaballero@gmail.com', 937143398, 'Puerta Nueva, 99', to_date('26/06/1984','dd/mm/yyyy') , 3);
     insert into workers
        values (994157965, 5200);
     insert into associates
       values (994157965);
        
--Associados Roma
insert into People
     values (397245994, 'Michele Siciliani','michelesici@gmail.com', 919312269, 'Via Zannoni n�114', to_date('19/02/1972','dd/mm/yyyy') , 4);
     insert into workers
        values (397245994, 5200);
     insert into associates
       values (397245994);
insert into People
     values (781899008, 'Fabrizia Beneventi','fabriziabeneventi@gmail.com', 919312869, 'Via Medina, 54', to_date('06/01/1968','dd/mm/yyyy') , 4);
     insert into workers
        values (781899008, 5200);
     insert into associates
       values (781899008);
insert into People
     values (182913680, 'Calogero Calabrese','calabrese@gmail.com', 937584398, 'Piazza Bovio, 126', to_date('26/06/1984','dd/mm/yyyy') , 4);
     insert into workers
        values (182913680, 200);
     insert into interns
       values (182913680);
       
--Juizes
insert into People
    values (184957746, 'Judy George', 'georgejudy@gmail.com', 916758631, 'Rua da Justi�a', to_date('14/04/1976','dd/mm/yyyy') , 1 );
     insert into judges values(184957746);
insert into People
    values (918856475, 'Manuel Lona', 'manelona@gmail.com', 916758631, 'Rua Luis Br�s', to_date('14/04/1976','dd/mm/yyyy') , 2 );
     insert into judges values(918856475);
insert into People
    values (917584665, 'Afonso Luis', 'afonsoluis@gmail.com', 916758631, 'Rua Fern�o Balso', to_date('14/04/1976','dd/mm/yyyy') , 4 );
     insert into judges values(917584665);


--Casos
insert into Cases 
  values(seq_cases.nextval,1300,'Burla Financeira',917594778,781899008);  
insert into Cases 
  values(seq_cases.nextval,1500,'Div�rcio',917594778,781899008);
insert into Cases 
  values(seq_cases.nextval,1200,'Crime Inform�tico',971453758,267872914);
insert into Cases 
  values(seq_cases.nextval,1600,'Crime Inform�tico',868463547,369072589);
insert into Cases
  values(seq_cases.nextval,3200,'Roubo Autom�vel',707465883, 657106499);
insert into Cases
  values(seq_cases.nextval,600,'Div�rcio',971453758, 994157965);
--Helps
insert into Helps
  values (1,182913680);
  
--Judged
insert into Judged
  values(1,1,917584665);
  
--Has Experience
insert into hasExperience
  values(1,182913680);
insert into hasExperience
  values(2,182913680);
insert into hasExperience
  values(3,182913680);
  
--Documents
insert into documents
  values(seq_doc.nextval, 'Relat�rio Cliente', 'Depoiamento do Cliente', 1);
insert into documents
  values(seq_doc.nextval, 'Relat�rio Advogado', 'Defesa do Advogado', 1);
insert into documents
  values(seq_doc.nextval, 'Relat�rio Cliente', 'Depoiamento do Cliente', 2);


--Criacao de tabelas

create or replace view viewPeopleAtBranch
    as select * from Branches natural join People;


create or replace view insertAssociates
    as select * from People natural join Workers natural join Associates;

create or replace trigger insertAssociatesTri
    instead of insert on insertAssociates
    begin    
        insert into People(nif, name_p, email_p, phone_p, address_p, birthdate, code_b)
        values (:new.nif, :new.name_p, :new.email_p, :new.phone_p, :new.address_p, :new.birthdate, :new.code_b);
        insert into Workers(nif, wage) values (:new.nif, :new.wage);
        insert into Associates(nif) values (:new.nif);
   
end;
/



create or replace view insertInterns
    as select * from People natural join Workers natural join Interns;

create or replace trigger insertInternsTri
    instead of insert on insertInterns
    begin
        insert into People(nif, name_p, email_p, phone_p, address_p, birthdate, code_b)
        values (:new.nif, :new.name_p, :new.email_p, :new.phone_p, :new.address_p, :new.birthdate, :new.code_b);
        insert into Workers(nif, wage) values (:new.nif, :new.wage);
        insert into Interns(nif) values (:new.nif);
  
end;
/

create or replace view insertJudges
    as select * from People natural join Judges ;

create or replace trigger insertJudgesTri
    instead of insert on insertJudges
    begin
        insert into People(nif, name_p, email_p, phone_p, address_p, birthdate, code_b)
        values (:new.nif, :new.name_p, :new.email_p, :new.phone_p, :new.address_p, :new.birthdate, :new.code_b);
        insert into Judges(nif) values (:new.nif);
    end;
/

create or replace view insertClients
    as select * from People natural join Clients ;

create or replace trigger insertClientsTri
    instead of insert on insertClients
    begin
        insert into People(nif, name_p, email_p, phone_p, address_p, birthdate, code_b)
        values (:new.nif, :new.name_p, :new.email_p, :new.phone_p, :new.address_p, :new.birthdate, :new.code_b);
        insert into Clients(nif) values (:new.nif);
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
    then raise_application_error(-20000, 'Associado e Cliente n�o pertencem ao mesmo escrit�rio!');
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
    raise_application_error(-20000, 'Estagi�rio e caso n�o pertencem ao mesmo escrit�rio!');
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


 commit;