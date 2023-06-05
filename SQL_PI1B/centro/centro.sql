CREATE TABLE CENTRO (
    id_centro int auto_increment PRIMARY KEY,
    nome varchar(100) not null,
    tipo enum('PUBLICO', 'PRIVADO') not null,
    fk_id_endereco_centro int not null
);

ALTER TABLE CENTRO ADD CONSTRAINT fk_endereco_centro
    FOREIGN KEY (fk_id_endereco_centro)
    REFERENCES ENDERECO_CENTRO (id_endereco_centro);