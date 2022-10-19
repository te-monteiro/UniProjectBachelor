
create table Branches
(
    code_b number(10),
    address_b varchar(255) not null,
    name_b varchar(255) not null,
    phone_b number(10) not null,

    primary key(code_b)
);

create table People
(
    nif number(10),
    email_p varchar(254) not null,
    phone_p number(10) not null,
    address_p varchar(255) not null,
    birthdate date not null,
    name_p varchar(255) not null,
    code_b number(10) not null,

    primary key (nif),
    foreign key (code_b) references Branches(code_b)
);

create table Courts(
    number_co number(10),
    phone_co number(10) not null,
    name_co varchar(255) not null,
    address_co varchar(255) not null,

    primary key (number_co)
);

create table Workers (
     nif number(10),
     wage number(7) not null,

     primary key(nif),
     foreign key (nif) references People(nif)
);

create table Clients(
    nif number(10)  not null,
    primary key(nif),
    foreign key (nif) references People(nif)
);

create table Associates(
    nif number(10),
    primary key (nif),
    foreign key (nif) references People(nif)
);

create table Cases(
    number_c number(10),
    payment number(10)  not null,
    code_t number(3) not null, 

    primary key(number_c),
    foreign key (code_t) references Types(code_t)
);

create table Clients_C(
    nif number(10),
    number_c number(10),

    primary key(nif,number_c),
    foreign key (nif) references Clients(nif),
    foreign key (number_c) references Cases(number_c)
);

create table Works(
    number_c number(10),
    nif number(10),

    primary key(number_c,nif),
    foreign key (number_c) references Cases(number_c),
    foreign key (nif) references Associates(nif)
);

create table Judges(
    nif number(10),
    primary key (nif),
    foreign key (nif) references People(nif)
);

create table Interns(
    nif number(10),
    primary key (nif),
    foreign key (nif) references Workers(nif)
);

create table Helps(
    number_c number(10),
    nif_w number(10),
    nif_i number(10),
    primary key(number_c,nif_w,nif_i),
    foreign key (number_c) references Works(number_c),
    foreign key (nif_w) references Works(nif),
    foreign key (nif_i) references Works(nif)
);


create table Judges_C(
    number_c number(10),
    nif number(10), 
    primary key(number_c,nif),
    foreign key (number_c) references Judged(number_c),
    foreign key (nif) references Judges(nif)
);

create table Types(
    code_t number(3),
    name_t varchar(255),

    primary key(code_t)
);

create table Judged (
    number_c number(10),
    number_co number(10),

    primary key (number_c,number_co),
    foreign key (number_c) references Cases(number_c),
    foreign key (number_co) references Courts(number_co)
)