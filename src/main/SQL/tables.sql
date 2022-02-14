drop database regola_72;
create database regola_72;
GRANT ALL PRIVILEGES ON regola_72.* TO 'root'@'localhost';
use regola_72;

create table calciatore
(
    nome            varchar(40) not null,
    ruolo           varchar(15) not null,
    squadra         varchar(40) not null,
    quotazione      int         not null,
    scelto          boolean     not null,
    media           float       not null,
    cod             int         not null
        primary key
);

create table voto
(
    idVoto           int         not null AUTO_INCREMENT
        primary key,
    n_giornata         int         not null,
    voto               float       not null,
    cal_fk             int         null,
    constraint voto_cal_fk
        foreign key (cal_fk) references calciatore (cod)
            on update cascade on delete cascade
);

create table storico
(
    idStorico          int         not null AUTO_INCREMENT
        primary key,
    n_giornata         int         not null,
    totale             float       not null
);


create table calSto
(
    idCalSto           int         not null AUTO_INCREMENT
        primary key,
    cal_fk   int not null,
    sto_fk   int not null,
    constraint calSto_cal_fk
        foreign key (cal_fk) references calciatore (cod)
            on update cascade on delete cascade,
    constraint calSto_sto_fk
        foreign key (sto_fk) references storico (idStorico)
            on update cascade on delete cascade
);