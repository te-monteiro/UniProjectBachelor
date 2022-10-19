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