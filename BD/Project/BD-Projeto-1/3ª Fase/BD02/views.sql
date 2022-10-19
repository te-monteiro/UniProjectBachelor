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


