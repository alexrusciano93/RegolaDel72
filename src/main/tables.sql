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