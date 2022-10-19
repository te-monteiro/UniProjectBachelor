
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
