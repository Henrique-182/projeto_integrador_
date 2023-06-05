CREATE TABLE VACINA (
    id_vacina int auto_increment PRIMARY KEY,
    nome varchar(50) not null,
    fabricante varchar(100) not null,
    combate varchar(50) not null,
    doses_minimas int not null,
    dias_proxima_dose int not null
);