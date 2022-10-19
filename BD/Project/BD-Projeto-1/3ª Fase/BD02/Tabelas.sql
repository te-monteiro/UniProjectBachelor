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