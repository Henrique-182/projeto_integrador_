CREATE TABLE VACINACAO (
    id_vacinacao int auto_increment PRIMARY KEY,
    fk_id_pessoa int not null,
    fk_id_centro int not null,
    fk_id_vacina int not null,
    dia_vacinacao date not null,
    dia_proxima_dose date not null
);

ALTER TABLE VACINACAO ADD CONSTRAINT fk_id_pessoa_vacinacao
    FOREIGN KEY (fk_id_pessoa)
    REFERENCES PESSOA (id_pessoa);
 ALTER TABLE VACINACAO ADD CONSTRAINT fk_id_centro_vacinacao
    FOREIGN KEY (fk_id_centro)
    REFERENCES CENTRO (id_centro);
 
ALTER TABLE VACINACAO ADD CONSTRAINT fk_id_vacina_vacinacao
    FOREIGN KEY (fk_id_vacina)
    REFERENCES VACINA (id_vacina);