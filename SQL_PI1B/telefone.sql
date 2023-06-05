CREATE TABLE TELEFONE_CENTRO (
    id_telefone_centro int PRIMARY KEY,
    fk_id_centro int not null,
    numero varchar(14) not null
);

ALTER TABLE TELEFONE_CENTRO ADD CONSTRAINT fk_centro_telefone
    FOREIGN KEY (fk_id_centro)
    REFERENCES CENTRO (id_centro);