/* ENCONTRAR CENTRO A PARTIR DO ESTADO DA PESSOA */
select * from pessoa; -- 26

select 
	e.estado
from 
	pessoa p 
inner join 
	endereco_pessoa e on e.id_endereco_pessoa = p.fk_id_endereco_pessoa 
where 
	p.id_pessoa = 26;

select 
	c.id_centro, 
    c.nome, 
    c.tipo 
from 
	centro c 
inner join 
	endereco_centro e on e.id_endereco_centro = c.fk_id_endereco_centro 
where 
	e.estado = 'GO';
