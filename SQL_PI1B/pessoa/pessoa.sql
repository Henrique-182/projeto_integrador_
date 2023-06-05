CREATE TABLE PESSOA (
    id_pessoa int auto_increment PRIMARY KEY,
    cpf varchar(11) unique,
    nome varchar(50) not null,
    sobrenome varchar(100) not null,
    fk_id_endereco_pessoa int not null,
    telefone varchar(14) not null
);

ALTER TABLE PESSOA ADD CONSTRAINT fk_endereco_pessoa
    FOREIGN KEY (fk_id_endereco_pessoa)
    REFERENCES ENDERECO_PESSOA (id_endereco_pessoa);