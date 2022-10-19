
/* -------------- CREATE TABLES --------------- */

drop table pessoas cascade constraints;
drop table dadores cascade constraints;
drop table tiposDeSangue cascade constraints;
drop table compativel cascade constraints;
drop table pacientes cascade constraints;
drop table doacoes cascade constraints;
drop table historicoDeDoacoes cascade constraints;
drop table transfusoes cascade constraints;
drop table historicoDeTranfusoes cascade constraints;
drop table estabelecimentos cascade constraints;
drop table urgenciasDeHospital cascade constraints;
drop table centros cascade constraints;
drop table funcionarios cascade constraints;
drop table medicos cascade constraints;
drop table auxiliaresDeLimpeza cascade constraints;
drop table tecnicos cascade constraints;
drop table enfermeiros cascade constraints;
drop table equipamentosMedicos cascade constraints;
drop table stock cascade constraints;
drop table tipoEmStock cascade constraints;
drop table doamEm cascade constraints;
drop table recebemEm cascade constraints;
drop table usado cascade constraints;

create table pessoas (
NIF number(9) primary key,
codigoPostal varchar(8) not null,
nomePessoa varchar(100) not null,
nTelefone number(9) not null,
email varchar(100) not null,
dataNasc date not null
);

create table dadores (
NIF number(9) primary key,
peso number not null check (peso >= 50),
sexo char(1) not null check (upper(sexo) in('F','M')),
foreign key (NIF) references pessoas
);

create table tiposDeSangue(
sigla varchar(3) primary key
    check (upper(sigla) in ('A+', 'O+', 'B+', 'O-', 'A-', 'AB+', 'B-',  'AB-'))
);

create table compativel(
sigla varchar(3),
siglaCompativel varchar(3),
primary key (sigla,siglaCompativel),
foreign key (sigla) references tiposDeSangue,
foreign key (siglaCompativel) references tiposDeSangue
);

create table pacientes(
NIF number(9) primary key,
sigla varchar(3) not null, 
foreign key (NIF) references pessoas,
foreign key (sigla) references tiposDeSangue
);

create table doacoes (
dataDoacao date not null,
quantidadeRecolhida number not null check (quantidadeRecolhida > 0 and quantidadeRecolhida <= 450),
NIF number(9) not null,
sigla varchar(3) not null,
primary key(dataDoacao,NIF),
foreign key (NIF) references dadores,
foreign key (sigla) references tiposDeSangue
);

create table historicoDeDoacoes(
NIF number(9),
numeroDaDoacao number check (numeroDaDoacao>=0),
dataDoacao date not null,
primary key(numeroDaDoacao,NIF),
foreign key(NIF) references dadores
);

create table transfusoes(
dataTransfusao date,
quantidadeRecolhida number not null check (quantidadeRecolhida>0 and quantidadeRecolhida<=450),
NIF number(9),
sigla varchar(3) not null,
primary key(dataTransfusao,NIF),
foreign key (NIF) references pacientes,
foreign key (sigla) references tiposDeSangue
);

create table historicoDeTranfusoes(
NIF number(9),
numeroDeTransfusao number check (numeroDeTransfusao>=0),
dataTransfusao date not null,
primary key(numeroDeTransfusao,NIF),
foreign key(NIF) references pacientes
);

create table estabelecimentos(
nomeEstabelecimento varchar(100) primary key,
nTelefone number(9) not null,
codigoPostal varchar(8) not null
);

create table stock (
nrSerie number primary key,
quantidade number not null check (quantidade>=0)
);

create table urgenciasDeHospital(
nomeEstabelecimento varchar(100) primary key,
nrSerie number not null,
foreign key (nrSerie) references stock,
foreign key (nomeEstabelecimento) references estabelecimentos
);

create table centros(
nomeEstabelecimento varchar(100) primary key,
foreign key (nomeEstabelecimento) references estabelecimentos
);

create table funcionarios (
NIF number(9) primary key,
salario number(7,2) not null check(salario>0),
nomeEstabelecimento varchar(100),
foreign key (nomeEstabelecimento) references urgenciasDeHospital,
foreign key (NIF) references pessoas
);

create table medicos(
NIF number(9) primary key,
especialidade varchar(100) not null,
foreign key (NIF) references funcionarios
);

create table auxiliaresDeLimpeza(
NIF number(9) primary key,
foreign key (NIF) references funcionarios
);

create table tecnicos(
NIF number(9) primary key,
foreign key (NIF) references funcionarios
);

create table enfermeiros(
NIF number(9) primary key,
nomeEstabelecimento varchar(100),
foreign key (nomeEstabelecimento) references centros,
foreign key (NIF) references funcionarios
);

create table equipamentosMedicos(
nomeMaquina varchar(100) not null,
nrSerieEquip number primary key
);

create table tipoEmStock(
sigla varchar(3),
nrSerie number,
primary key (sigla, nrSerie),
foreign key (sigla) references tiposDeSangue,
foreign key (nrSerie) references stock
);

create table doamEm (
NIF number(9) primary key,
nomeEstabelecimento varchar(100) not null,
foreign key (NIF) references dadores,
foreign key (nomeEstabelecimento) references centros
);

create table recebemEm (
NIF number(9) primary key,
nomeEstabelecimento varchar(100) not null,
foreign key (NIF) references pacientes,
foreign key (nomeEstabelecimento) references urgenciasDeHospital
);

create table usado (
NIF number(9) not null,
numeroSerie number primary key,
foreign key (NIF) references funcionarios,
foreign key (numeroSerie) references equipamentosMedicos
);

/* ------------------------  TRIGGERS and SEQUENCES --------------------------- */

drop sequence seq_num_doacao;
create sequence seq_num_doacao increment by 1 start with 1;

drop sequence seq_num_transfusao;
create sequence seq_num_transfusao increment by 1 start with 1;

/* Trigger: ver se dador tem mais de 18 anos */

create or replace trigger mais_18
before insert on dadores
referencing new as nrow
for each row
declare exist number; age number;
begin
	select count(*) into exist
	from pessoas
	where pessoas.nif = :nrow.nif;

  select SYSDATE-dataNasc into age
	from pessoas
	where pessoas.nif = :nrow.nif;

	if(exist = 0)
	then Raise_Application_Error (-20100, 'Nao existe pessoa no sistema');
  end if;

	if(age/365 < 18)
	then Raise_Application_Error (-20100, 'Demasiado novo');
	end if;
end;
/

/* Trigger: ver se dador tem menos de 65 anos */

create or replace trigger menos_65
before insert on dadores
referencing new as nrow
for each row
declare exist number; age number;
begin
	select count(*) into exist
	from pessoas
	where pessoas.nif = :nrow.nif;

	select SYSDATE-dataNasc into age
	from pessoas
	where pessoas.nif = :nrow.nif;

	if(exist = 0)
	then Raise_Application_Error (-20100, 'Nao existe pessoa no sistema');
  end if;

	if(age/365 > 65)
	then Raise_Application_Error (-20100, 'Demasiado velho');
	end if;
end;
/

/* Trigger: tempo entre doacoes para mulheres */

create or replace trigger tempoEntreDoacoesMulheres
before insert on doacoes
referencing new as nrow
for each row
declare ultimaDoacao date;
begin
	select MIN(dataDoacao) into ultimaDoacao
	from historicoDeDoacoes, dadores
	where historicoDeDoacoes.nif = :nrow.nif and dadores.nif = :nrow.nif and dadores.sexo = 'F';
	
	if ((:nrow.dataDoacao - ultimaDoacao) < 120)
		then RAISE_APPLICATION_ERROR (-20100, 'Limite minimo de 120 dias nao cumprido');
	end if;
end;
/

/* Trigger: tempo entre doacoes para homens */

create or replace trigger tempoEntreDoacoesHomens
before insert on doacoes
referencing new as nrow
for each row
declare ultimaDoacao date;
begin
	select MIN(dataDoacao) into ultimaDoacao
	from historicoDeDoacoes, dadores
	where historicoDeDoacoes.nif = :nrow.nif and dadores.nif = :nrow.nif and dadores.sexo = 'M';
	
	if ((:nrow.dataDoacao - ultimaDoacao) < 90)
		then RAISE_APPLICATION_ERROR (-20100, 'Limite minimo de 90 dias nao cumprido');
	end if;
end;
/

/* Trigger: ver se funcionario existe */

create or replace trigger funcionario_existe
before insert on funcionarios
referencing new as nrow
for each row
declare exist number;
begin
	select count(*) into exist
	from funcionarios
	where funcionarios.nif = :nrow.nif;
	if (exist != 0 )
		then Raise_Application_Error (-20100, 'Ja existe um funcionario com o NIF inserido!');
	end if;
end;
/

/* Trigger: ver se dador existe */

create or replace trigger dador_existe
before insert on dadores
referencing new as nrow
for each row
declare exist number;
begin
	select count(*) into exist
	from dadores
	where dadores.nif = :nrow.nif;
	if (exist != 0 )
		then Raise_Application_Error (-20100, 'Ja existe um dador com o NIF inserido!');
	end if;
end;
/

/* Trigger: ver se paciente existe */

create or replace trigger paciente_existe
before insert on pacientes
referencing new as nrow
for each row
declare exist number;
begin
	select count(*) into exist
	from pacientes
	where pacientes.nif = :nrow.nif;
	if (exist != 0 )
		then Raise_Application_Error (-20100, 'Ja existe um paciente com o NIF inserido!');
	end if;
end;
/

/* Trigger: apenas 1 emprego por NIF*/

create or replace trigger um_emprego
before insert on funcionarios
referencing new as nrow
for each row
declare exist number;
begin
	select count(*) into exist
	from funcionarios
	where funcionarios.nif = :nrow.nif;

	if (exist != 0)
		then Raise_Application_Error (-20100, 'Ja tem emprego!');
	end if;
end;
/

/* Trigger: Verificar se quem fez a doacao é dador */

create or replace trigger doacao_by_dador
before insert on doacoes
referencing new as nrow
for each row
declare exist number;
begin
	select count(*) into exist
	from dadores
	where dadores.nif = :nrow.nif;

	if (exist = 0)
		then Raise_Application_Error (-20100, 'Dador nao existente!');
	end if;
end;
/

/* Trigger: Verificar se quem recebe a transfusao é paciente */

create or replace trigger transfusao_by_paciente
before insert on transfusoes
referencing new as nrow
for each row
declare exist number;
begin
	select count(*) into exist
	from pacientes
	where pacientes.nif = :nrow.nif;

	if (exist = 0)
		then Raise_Application_Error (-20100, 'Paciente nao existente!');
	end if;
end;
/

/* Trigger: Remover Paciente */

create or replace trigger remover_paciente
before delete on pacientes
referencing new as nprow
for each row
declare existe_paciente number; tmb_funcionario number;
begin
	select count(*) into existe_paciente
	from pacientes
	where pacientes.nif = :nprow.nif;

	select count(*) into tmb_funcionario
	from funcionarios
	where funcionarios.nif = :nprow.nif;

	if (existe_paciente = 0)
		then Raise_Application_Error (-20100, 'Paciente nao existente!');
	end if;

	if (existe_paciente != 0 and tmb_funcionario = 0)
		then delete from pacientes where pacientes.nif = :nprow.nif;
	end if;

	if (existe_paciente != 0 and tmb_funcionario = 0)
		then delete from pessoas where pessoas.nif = :nprow.nif;
	end if;

	if (existe_paciente != 0 and tmb_funcionario != 0)
		then delete from pacientes where pacientes.nif = :nprow.nif;
	end if;
end;
/

/* Trigger: Remover Funcionario */

create or replace trigger remover_funcionario
before delete on funcionarios
referencing new as nfrow
for each row
declare existe_funcionario number; tmb_dador number; tmb_paciente number;
begin
	select count(*) into existe_funcionario
	from funcionarios
	where funcionarios.nif = :nfrow.nif;

	select count(*) into tmb_dador
	from dadores
	where dadores.nif = :nfrow.nif;

	select count(*) into tmb_paciente
	from pacientes
	where pacientes.nif = :nfrow.nif;

	if (existe_funcionario = 0)
		then Raise_Application_Error (-20100, 'Funcionario nao existente!');
	end if;

	if (existe_funcionario != 0 and tmb_dador = 0 and tmb_paciente = 0)
		then delete from funcionarios where funcionarios.nif = :nfrow.nif;
	end if;

 	if (existe_funcionario != 0 and tmb_dador = 0 and tmb_paciente = 0)
		then delete from pessoas where pessoas.nif = :nfrow.nif;
	end if;

 	if ((existe_funcionario != 0 and tmb_dador != 0) or (existe_funcionario != 0 and tmb_paciente != 0))
		then delete from funcionarios where funcionarios.nif = :nfrow.nif;
	end if;
end;
/


/* Trigger: Remover Dador */

create or replace trigger remover_dador
before delete on dadores
referencing new as ndrow
for each row
declare existe_dador number; tmb_funcionario number;
begin
	select count(*) into existe_dador
	from dadores
	where dadores.nif = :ndrow.nif;

	select count(*) into tmb_funcionario
	from funcionarios
	where funcionarios.nif = :ndrow.nif;

	if (existe_dador = 0)
		then Raise_Application_Error (-20100, 'Dador nao existente!');
	end if;

	if (existe_dador != 0 and tmb_funcionario = 0)
		then delete from dadores where dadores.nif = :ndrow.nif;
	end if;

	if (existe_dador != 0 and tmb_funcionario != 0)
		then delete from dadores where dadores.nif = :ndrow.nif;
	end if;
end;
/

/* Trigger: Dador passa a ser Paciente */

create or replace trigger dador_agora_paciente
before insert on pacientes
referencing new as nrow
for each row
declare exist_dador number;
begin
	select count(*) into exist_dador
	from dadores
	where dadores.nif = :nrow.nif;

	if (exist_dador != 0)
		then delete from dadores where dadores.nif = :nrow.nif;
	end if;
end;
/

/* Trigger: Ao adicionar doacao tambem adiciona ao historico */

create or replace trigger adicionarHistoricoDoacoes
after insert on doacoes
referencing new as nrow
for each row
declare exist_dador number;
begin
		select count(*) into exist_dador
		from dadores
		where dadores.nif = :nrow.nif;

		if(exist_dador != 0)
				then insert into historicoDeDoacoes values (:nrow.nif, seq_num_doacao.nextval, :nrow.dataDoacao);
		end if;
end;
/

/* Trigger: Ao adicionar transfusao tambem adiciona ao historico */

create or replace trigger adicionarHistoricoTransfusoes
after insert on transfusoes
referencing new as nrow
for each row
declare exist_paciente number;
begin
		select count(*) into exist_paciente
		from pacientes
		where pacientes.nif = :nrow.nif;

		if(exist_paciente != 0)
				then insert into historicoDeTranfusoes values (:nrow.nif, seq_num_transfusao.nextval, :nrow.dataTransfusao);
		end if;
end;
/

/* Trigger: dador não existir para doam em*/

create or replace trigger doamEm_verificacao
before insert on doamEm
referencing new as nrow
for each row
declare exist number;
begin

	select count(*) into exist
	from dadores
	where dadores.nif = :nrow.nif;
	
	if (exist = 0)
		then RAISE_APPLICATION_ERROR (-20100, 'Utilizador nao e dador.');
	end if;
end;
/ 

/* Trigger: dador não existir para recebem em*/

create or replace trigger recebemEm_verificacao
before insert on recebemEm
referencing new as nrow
for each row
declare exist number;
begin

	select count(*) into exist
	from pacientes
	where pacientes.nif = :nrow.nif;
	
	if (exist = 0)
		then RAISE_APPLICATION_ERROR (-20100, 'Utilizador nao e paciente.');
	end if;
end;
/ 

/* Trigger: ver se sangue é compativel */

create or replace trigger verCompatibilidade
before insert on transfusoes
referencing new as nrow
for each row
declare exist number; pessoa_sigla varchar(3);
begin	
	
	select pacientes.sigla into pessoa_sigla
	from pacientes
	where pacientes.nif = :nrow.nif;
	
	select count(*) into exist
	from compativel
	where compativel.sigla = pessoa_sigla and compativel.siglaCompativel = :nrow.sigla;
	
	if (exist = 0)
		then RAISE_APPLICATION_ERROR (-20100, 'Tipos de sangue incompatíveis');
	end if;
end;
/


/* ------------------------  VIEWS AND FUNCTIONS --------------------------- */

/* View: Todos os funcionarios tem acesso aos equipamentos Medicos */
create or replace view funcionarios_view as
    select *
    from equipamentosMedicos
;

/* View: os centros tem acesso aos funcionarios que lá trabalham */
create or replace view centros_view as
    select *
    from enfermeiros
;

/* View: os enfermeiros tem acesso a todas as doacoes feitas nos centros */
create or replace view enfermeiros_view as
    select *
    from historicoDeDoacoes natural inner join dadores
;

/* View: os hospitais tem acesso a todos os funcionarios que lá trabalham */
create or replace view hospitais_view as
    select *
    from funcionarios
;

/* View: os funcionarios tem acesso a todas as transfusões feitas nos hospitais */
create or replace view funcionarios_view as
    select *
    from historicoDeTranfusoes natural inner join pacientes
;


/*-----------FUNCTIONS-------------*/

/* Function: Calcula o número total de doações inseridas na base de dados */
create or replace FUNCTION calc_doacoes (nif NUMBER) RETURN NUMBER
IS
     d_count NUMBER;
BEGIN
    SELECT count(*) 
    INTO d_count
    FROM doacoes d
    WHERE d.nif = nif;
    RETURN (d_count);
end calc_doacoes ;


/* Function: Calcula o número total de dadores que fizeram uma doação em cada
centro de doação inserido no sistema*/

create or replace FUNCTION calc_nr_dadores (nome varchar) RETURN NUMBER
IS
    d_count NUMBER;
begin
    select count(*) 
    into d_count
    from doamEm d
    where nome = d.nomeEstabelecimento;
    return (d_count);
end calc_nr_dadores;


/* Function: Calcula o número total de pacientes que fizeram uma transfusão em cada
urgência de hospital inserida no sistema*/

create or replace FUNCTION calc_nr_pacientes (nome varchar) RETURN NUMBER
IS
    d_count NUMBER;
begin
    select count(*) 
    into d_count
    from recebemEm r
    where nome = r.nomeEstabelecimento;
    return (d_count);
end calc_nr_pacientes;


/* Function: Calcula o número total de transfusões inseridas na base de dados */

create or replace FUNCTION calc_transfusoes (nif NUMBER) RETURN NUMBER
IS
     d_count NUMBER;
BEGIN
    SELECT count(*) 
    INTO d_count
    FROM transfusoes t
    WHERE nif = t.nif;
    RETURN (d_count);
end calc_transfusoes ;



/* ------------------------  INSERTS --------------------------- */


insert into pessoas values(1, '1000-001', 'Ana', 961111111, 'Ana@gmail.com', to_date('1991-05-17','YYYY-MM-DD'));
insert into pessoas values(2, '1000-002', 'Beatriz', 961111112, 'Beatriz@gmail.com', to_date('1992-05-18','YYYY-MM-DD'));
insert into pessoas values(3, '1000-003', 'Carlos', 961111113, 'Carlos@gmail.com', to_date('1993-05-19','YYYY-MM-DD'));
insert into pessoas values(4, '1000-004', 'Duarte', 961111114, 'Duarte@gmail.com', to_date('1774-05-20','YYYY-MM-DD'));
insert into pessoas values(5, '1000-005', 'Eduarda', 961111115, 'Eduarda@gmail.com', to_date('1995-05-21','YYYY-MM-DD'));
insert into pessoas values(6, '1000-006', 'Francisca', 961111116, 'Francisca@gmail.com', to_date('1996-05-22','YYYY-MM-DD'));
insert into pessoas values(7, '1000-007', 'Gabriel', 961111117, 'Gabriel@gmail.com', to_date('1997-05-23','YYYY-MM-DD'));
insert into pessoas values(8, '1000-008', 'Herve', 961111118, 'Herve@gmail.com', to_date('1998-05-24','YYYY-MM-DD'));
insert into pessoas values(9, '1000-009', 'Ivo', 961111119, 'Ivo@gmail.com', to_date('1999-05-25','YYYY-MM-DD'));
insert into pessoas values(10, '1000-010', 'Joana', 961111120, 'Joana@gmail.com', to_date('2000-06-26','YYYY-MM-DD'));
insert into pessoas values(11, '1000-010', 'Karim', 961111120, 'Karim@gmail.com', to_date('1980-07-04','YYYY-MM-DD'));
insert into pessoas values(12, '1000-010', 'Lucas', 961111120, 'Lucas@gmail.com', to_date('1981-08-25','YYYY-MM-DD'));
insert into pessoas values(13, '1000-010', 'Mauro', 961111120, 'Mauro@gmail.com', to_date('1982-09-13','YYYY-MM-DD'));
insert into pessoas values(14, '1000-010', 'Nadia', 961111120, 'Nadia@gmail.com', to_date('1983-10-10','YYYY-MM-DD'));
insert into pessoas values(15, '1000-010', 'Olivia', 961111120, 'Olivia@gmail.com', to_date('1984-11-18','YYYY-MM-DD'));

insert into dadores values(1, 50, 'F');
insert into dadores values(2, 60, 'F');
insert into dadores values(3, 70, 'M');
insert into dadores values(4, 80, 'M');
insert into dadores values(5, 90, 'F');
insert into dadores values(10, 100, 'M');
insert into dadores values(1, 50, 'F');

insert into tiposDeSangue values ('A+');
insert into tiposDeSangue values ('O+');
insert into tiposDeSangue values ('B+');
insert into tiposDeSangue values ('A-');
insert into tiposDeSangue values ('O-');
insert into tiposDeSangue values ('B-');
insert into tiposDeSangue values ('AB+');
insert into tiposDeSangue values ('AB-');

insert into compativel values ('A+', 'A+');
insert into compativel values ('A+', 'O+');
insert into compativel values ('A-', 'O-');
insert into compativel values ('B+', 'O+');
insert into compativel values ('AB+', 'B+');
insert into compativel values ('O+', 'O+');
insert into compativel values ('A-', 'A-');
insert into compativel values ('AB+', 'AB+');

insert into pacientes values(6, 'A+');
insert into pacientes values(7, 'AB+');
insert into pacientes values(8, 'O+');
insert into pacientes values(9, 'A-');
insert into pacientes values(6, 'B-');

insert into doacoes values (to_date('2018-05-17','YYYY-MM-DD'), 300, 1, 'A+');
insert into doacoes values (to_date('2018-05-18','YYYY-MM-DD'), 320, 1, 'A+');
insert into doacoes values (to_date('2018-05-19','YYYY-MM-DD'), 340, 2, 'B+');
insert into doacoes values (to_date('2019-05-20','YYYY-MM-DD'), 360, 2, 'B+');
insert into doacoes values (to_date('2018-05-22','YYYY-MM-DD'), 400, 3, 'O-');
insert into doacoes values (to_date('2018-05-23','YYYY-MM-DD'), 420, 3, 'O-');
insert into doacoes values (to_date('2018-05-25','YYYY-MM-DD'), 450, 5, 'O+');
insert into doacoes values (to_date('2019-05-26','YYYY-MM-DD'), 450, 5, 'O+');
insert into doacoes values (to_date('2018-05-26','YYYY-MM-DD'), 450, 15, 'O+');

insert into transfusoes values (to_date('2018-05-17','YYYY-MM-DD'), 300, 6, 'A+');
insert into transfusoes values (to_date('2018-05-18','YYYY-MM-DD'), 320, 6, 'B+');
insert into transfusoes values (to_date('2018-05-19','YYYY-MM-DD'), 340, 7, 'B+');
insert into transfusoes values (to_date('2018-05-20','YYYY-MM-DD'), 360, 7, 'AB+');
insert into transfusoes values (to_date('2018-05-21','YYYY-MM-DD'), 380, 7, 'B+');
insert into transfusoes values (to_date('2018-05-22','YYYY-MM-DD'), 400, 8, 'O+');
insert into transfusoes values (to_date('2018-05-23','YYYY-MM-DD'), 420, 8, 'O+');
insert into transfusoes values (to_date('2018-05-24','YYYY-MM-DD'), 440, 9, 'A-');
insert into transfusoes values (to_date('2018-05-25','YYYY-MM-DD'), 450, 10, 'O+');
insert into transfusoes values (to_date('2018-05-26','YYYY-MM-DD'), 450, 10, 'O+');

insert into estabelecimentos values ('Almada', 210000000, '2000-001');
insert into estabelecimentos values ('Benfica', 210000001, '2000-002');
insert into estabelecimentos values ('Porto', 210000002, '2000-003');
insert into estabelecimentos values ('Coimbra', 210000003, '2000-004');

insert into stock values (1, 200000);
insert into stock values (2, 300000);

insert into urgenciasDeHospital values ('Almada',1);
insert into urgenciasDeHospital values ('Porto',2);

insert into centros values ('Benfica');
insert into centros values ('Coimbra');

insert into funcionarios values (11, 1000, 'Almada');
insert into funcionarios values (12, 1100, 'Almada');
insert into funcionarios values (13, 1200, 'Porto');
insert into funcionarios values (14, 1300, 'Almada');
insert into funcionarios values (15, 1400, 'Porto');
insert into funcionarios values (11, 1000, 'Almada');

insert into medicos values (15, 'Pediatra');

insert into auxiliaresDeLimpeza values (11);

insert into tecnicos values (12);
insert into tecnicos values (11);

insert into enfermeiros values (13, 'Benfica');
insert into enfermeiros values (14, 'Coimbra');

insert into equipamentosMedicos values ('Maquina Raio-X', 111);
insert into equipamentosMedicos values ('Agulha', 222);
insert into equipamentosMedicos values ('Estetoscopio', 333);
insert into equipamentosMedicos values ('Microscopio', 444);

insert into tipoEmStock values ('A+', 1);
insert into tipoEmStock values ('A-', 1);
insert into tipoEmStock values ('B+', 1);
insert into tipoEmStock values ('B-', 1);
insert into tipoEmStock values ('O+', 1);
insert into tipoEmStock values ('O-', 1);
insert into tipoEmStock values ('AB+', 1);
insert into tipoEmStock values ('AB-', 1);

insert into tipoEmStock values ('A+', 2);
insert into tipoEmStock values ('A-', 2);
insert into tipoEmStock values ('B+', 2);
insert into tipoEmStock values ('B-', 2);
insert into tipoEmStock values ('O+', 2);
insert into tipoEmStock values ('O-', 2);
insert into tipoEmStock values ('AB+', 2);
insert into tipoEmStock values ('AB-', 2);

insert into doamEm values (1, 'Benfica');
insert into doamEm values (2, 'Coimbra');
insert into doamEm values (3, 'Benfica');
insert into doamEm values (4, 'Coimbra');
insert into doamEm values (5, 'Benfica');

insert into recebemEm values (6, 'Almada');
insert into recebemEm values (7, 'Almada');
insert into recebemEm values (8, 'Almada');
insert into recebemEm values (9, 'Almada');
insert into recebemEm values (10, 'Almada');

insert into usado values (11, 111);
insert into usado values (12, 222);
insert into usado values (14, 333);
insert into usado values (15, 444);

