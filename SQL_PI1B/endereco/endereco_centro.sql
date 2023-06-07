CREATE TABLE ENDERECO_CENTRO (
    id_endereco_centro int auto_increment PRIMARY KEY,
    estado varchar(2) not null,
    cidade varchar(50) not null,
    bairro varchar(50) not null,
    cep varchar(8) not null,
    logradouro varchar(50) not null,
    complemento varchar(100)
);