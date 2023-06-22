package conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	public static void createTables() throws SQLException {
		Connection connection = Conexao.createConnection();

		createEnderecoPessoa(connection);
		createPessoa(connection);
		createEnderecoCentro(connection);
		createCentro(connection);
		createTelefoneCentro(connection);
		createVacina(connection);
		createVacinacao(connection);

		connection.close();
	}

	public static void populateTables() throws SQLException {
		Connection connection = Conexao.createConnection();

		populateEnderecoPessoa(connection);
		populatePessoa(connection);
		populateEnderecoCentro(connection);
		populateCentro(connection);
		populateTelefoneCentro(connection);
		populateVacina(connection);
		populateVacinacao(connection);

		connection.close();
	}
	
	public static void createProcedure() throws SQLException {
		Connection connection = Conexao.createConnection();

		Statement statement = connection.createStatement();

		String queryCreateProcedureAgendarVacinacao = "CREATE PROCEDURE proc_agendar_vacinacao(IN p_id_vacina INT, IN p_id_pessoa INT, IN p_id_centro INT, IN p_dia_primeira_dose DATE) "
				+ " BEGIN "
				+ "	DECLARE v_doses INT; "
				+ "    DECLARE v_dia_dose DATE; "
				+ "    DECLARE v_dias_proxima_dose INT; "
				+ "    DECLARE v_dia_proxima_dose DATE; "
				+ "     "
				+ "    SET v_doses = (SELECT doses_minimas FROM vacina WHERE id_vacina = p_id_vacina); "
				+ "    SET v_dia_dose = p_dia_primeira_dose; "
				+ "    SET v_dias_proxima_dose = (SELECT dias_proxima_dose FROM vacina WHERE id_vacina = p_id_vacina); "
				+ "    SET v_dia_proxima_dose = v_dia_dose; "
				+ "     "
				+ "    WHILE v_doses != 0 DO "
				+ "		IF v_doses = 1 THEN "
				+ "			INSERT INTO vacinacao (fk_id_vacina, fk_id_pessoa, fk_id_centro, dia_vacinacao) "
				+ "			VALUES (p_id_vacina, p_id_pessoa, p_id_centro, v_dia_dose); "
				+ "             "
				+ "        ELSE "
				+ "			SET v_dia_proxima_dose = date_add(v_dia_proxima_dose, INTERVAL v_dias_proxima_dose DAY); "
				+ " "
				+ "			INSERT INTO vacinacao (fk_id_vacina, fk_id_pessoa, fk_id_centro, dia_vacinacao, dia_proxima_dose) "
				+ "			VALUES (p_id_vacina, p_id_pessoa, p_id_centro, v_dia_dose, v_dia_proxima_dose); "
				+ "        END IF; "
				+ " "
				+ "		SET v_dia_dose = date_add(v_dia_dose, INTERVAL v_dias_proxima_dose DAY); "
				+ "		SET v_doses = v_doses - 1; "
				+ "	END WHILE; "
				+ "END ";

		statement.execute(queryCreateProcedureAgendarVacinacao);

		System.out.println("PROCEDURE CRIADA");

		statement.close();

		connection.close();
	}

	private static void createEnderecoPessoa(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryTableEnderecoPessoa = "CREATE TABLE IF NOT EXISTS `endereco_pessoa` ("
				+ "  `id_endereco_pessoa` int NOT NULL PRIMARY KEY AUTO_INCREMENT," + "  `estado` varchar(2) NOT NULL,"
				+ "  `cidade` varchar(50) NOT NULL," + "  `bairro` varchar(50) NOT NULL,"
				+ "  `cep` varchar(8) NOT NULL," + "  `logradouro` varchar(50) NOT NULL,"
				+ "  `complemento` varchar(100) DEFAULT NULL" + ")";

		statement.execute(queryTableEnderecoPessoa);

		System.out.println("TABELA ENDEREÇO PESSOA CRIADA");

		statement.close();
	}

	private static void populateEnderecoPessoa(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryPopulate = "INSERT INTO `endereco_pessoa`(estado, cidade, bairro, cep, logradouro, complemento) VALUES "
				+ "('AC','Rio Branco','Centro','69900000','Rua Acre','Prédio 1'),"
				+ "('AC','Rio Branco','Bosque','69901000','Avenida Amazônia','Apartamento 101'),"
				+ "('AC','Rio Branco','Floresta','69902000','Travessa dos Ipês','Casa 5'),"
				+ "('AL','Maceió','Ponta Verde','57000000','Rua da Praia','Apartamento 501'),"
				+ "('AL','Maceió','Farol','57001000','Avenida Fernandes Lima','Loja 10'),"
				+ "('AL','Maceió','Pajuçara','57002000','Rua Senador Mendonça','Sobrado 2'),"
				+ "('AM','Manaus','Centro','69000000','Avenida Eduardo Ribeiro','Loja 1'),"
				+ "('AM','Manaus','Adrianópolis','69001000','Rua Salvador','Sala 202'),"
				+ "('AM','Manaus','Parque Dez','69002000','Avenida das Torres','Apartamento 301'),"
				+ "('AP','Macapá','Central','68900000','Rua do Canal','Casa 1'),"
				+ "('AP','Macapá','Santa Rita','68901000','Avenida FAB','Apartamento 102'),"
				+ "('AP','Macapá','Buritizal','68902000','Travessa das Flores','Casa 8'),"
				+ "('BA','Salvador','Centro','40000000','Avenida Sete de Setembro','Loja 2'),"
				+ "('BA','Salvador','Itaigara','40001000','Rua das Acácias','Sala 203'),"
				+ "('BA','Salvador','Pituba','40002000','Avenida Manoel Dias','Apartamento 402'),"
				+ "('CE','Fortaleza','Centro','60000000','Avenida Dom Manuel','Loja 3'),"
				+ "('CE','Fortaleza','Aldeota','60001000','Rua Torres Câmara','Sala 204'),"
				+ "('CE','Fortaleza','Meireles','60002000','Avenida Beira Mar','Apartamento 503'),"
				+ "('DF','Brasília','Asa Sul','70000000','SQS 102','Apartamento 201'),"
				+ "('DF','Brasília','Asa Norte','70001000','SCLRN 304','Loja 5'),"
				+ "('DF','Brasília','Lago Sul','70002000','SHIS QI 9','Casa 15'),"
				+ "('ES','Vitória','Centro','29000000','Avenida Jerônimo Monteiro','Loja 6'),"
				+ "('ES','Vitória','Jardim da Penha','29001000','Rua Belmiro Rodrigues da Silva','Sala 205'),"
				+ "('ES','Vitória','Praia do Canto','29002000','Avenida Rio Branco','Apartamento 601'),"
				+ "('GO','Goiânia','Setor Central','70000000','Avenida Anhanguera','Loja 7'),"
				+ "('GO','Goiânia','Setor Bueno','70001000','Rua T10','Sala 206'),"
				+ "('GO','Goiânia','Setor Oeste','70002000','Avenida Assis Chateaubriand','Apartamento 701'),"
				+ "('MA','São Luís','Centro','65000000','Rua Grande','Loja 8'),"
				+ "('MA','São Luís','Cohama','65001000','Avenida Daniel de La Touche','Sala 207'),"
				+ "('MA','São Luís','Calhau','65002000','Avenida dos Holandeses','Apartamento 801'),"
				+ "('MT','Cuiabá','Centro Norte','78000000','Avenida Isaac Póvoas','Loja 9'),"
				+ "('MT','Cuiabá','Bosque da Saúde','78001000','Rua São Sebastião','Sala 208'),"
				+ "('MT','Cuiabá','Jardim das Américas','78002000','Avenida das Flores','Apartamento 901'),"
				+ "('MS','Campo Grande','Centro','79000000','Avenida Afonso Pena','Loja 10'),"
				+ "('MS','Campo Grande','Jardim dos Estados','79001000','Rua Antônio Maria Coelho','Sala 209'),"
				+ "('MS','Campo Grande','Bairro Tiradentes','79002000','Avenida Bandeirantes','Apartamento 1002'),"
				+ "('MG','Belo Horizonte','Centro','30000000','Avenida Afonso Pena','Loja 11'),"
				+ "('MG','Belo Horizonte','Savassi','30001000','Rua Pernambuco','Sala 210'),"
				+ "('MG','Belo Horizonte','Lourdes','30002000','Avenida Bias Fortes','Apartamento 1102'),"
				+ "('PA','Belém','Batista Campos','66000000','Travessa Padre Eutíquio','Loja 12'),"
				+ "('PA','Belém','Nazaré','66001000','Avenida Almirante Barroso','Sala 211'),"
				+ "('PA','Belém','Umarizal','66002000','Rua dos Pariquis','Apartamento 1203'),"
				+ "('PB','João Pessoa','Centro','58000000','Avenida Epitácio Pessoa','Loja 13'),"
				+ "('PB','João Pessoa','Manaíra','58001000','Rua Maria Caetano Fernandes de Lima','Sala 212'),"
				+ "('PB','João Pessoa','Tambaú','58002000','Avenida Presidente Epitácio Pessoa','Apartamento 1304'),"
				+ "('PR','Curitiba','Centro','80000000','Rua XV de Novembro','Loja 14'),"
				+ "('PR','Curitiba','Batel','80001000','Alameda Dr. Carlos de Carvalho','Sala 213'),"
				+ "('PR','Curitiba','Água Verde','80002000','Avenida República Argentina','Apartamento 1405'),"
				+ "('PE','Recife','Boa Viagem','50000000','Avenida Boa Viagem','Loja 15'),"
				+ "('PE','Recife','Graças','50001000','Rua Joaquim Nabuco','Sala 214'),"
				+ "('PE','Recife','Pina','50002000','Avenida Herculano Bandeira','Apartamento 1506'),"
				+ "('PI','Teresina','Centro','64000000','Avenida Frei Serafim','Loja 16'),"
				+ "('PI','Teresina','Jóquei','64001000','Rua Goiás','Sala 215'),"
				+ "('PI','Teresina','Ininga','64002000','Avenida Nossa Senhora de Fátima','Apartamento 1607'),"
				+ "('RJ','Rio de Janeiro','Centro','20000000','Rua do Rosário','Loja 17'),"
				+ "('RJ','Rio de Janeiro','Copacabana','20001000','Avenida Nossa Senhora de Copacabana','Sala 216'),"
				+ "('RJ','Rio de Janeiro','Ipanema','20002000','Rua Visconde de Pirajá','Apartamento 1708'),"
				+ "('RN','Natal','Centro','59000000','Avenida Rio Branco','Loja 18'),"
				+ "('RN','Natal','Ponta Negra','59001000','Rua Erivan França','Sala 217'),"
				+ "('RN','Natal','Tirol','59002000','Avenida Hermes da Fonseca','Apartamento 1809'),"
				+ "('RS','Porto Alegre','Centro','90000000','Avenida Borges de Medeiros','Loja 19'),"
				+ "('RS','Porto Alegre','Moinhos de Vento','90001000','Rua Padre Chagas','Sala 218'),"
				+ "('RS','Porto Alegre','Belém Novo','90002000','Avenida Juca Batista','Apartamento 1909'),"
				+ "('RO','Porto Velho','Centro','76800000','Avenida Sete de Setembro','Loja 20'),"
				+ "('RO','Porto Velho','Caiari','76801000','Rua Dom Pedro II','Sala 219'),"
				+ "('RO','Porto Velho','Olaria','76802000','Avenida Calama','Apartamento 2009'),"
				+ "('RR','Boa Vista','Centro','69300000','Avenida Ville Roy','Loja 21'),"
				+ "('RR','Boa Vista','São Francisco','69301000','Rua Agnelo Bittencourt','Sala 220'),"
				+ "('RR','Boa Vista','Liberdade','69302000','Avenida Brigadeiro Eduardo Gomes','Apartamento 2102'),"
				+ "('SC','Florianópolis','Centro','88000000','Rua Felipe Schmidt','Loja 22'),"
				+ "('SC','Florianópolis','Trindade','88001000','Avenida Madre Benvenuta','Sala 221'),"
				+ "('SC','Florianópolis','Itacorubi','88002000','Rua Lauro Linhares','Apartamento 2202'),"
				+ "('SP','São Paulo','Centro','01000000','Rua Direita','Loja 23'),"
				+ "('SP','São Paulo','Jardins','01001000','Alameda Santos','Sala 222'),"
				+ "('SP','São Paulo','Moema','01002000','Avenida Bemtevi','Apartamento 2302'),"
				+ "('SE','Aracaju','Centro','49000000','Rua João Pessoa','Loja 24'),"
				+ "('SE','Aracaju','Atalaia','49001000','Avenida Santos Dumont','Sala 223'),"
				+ "('SE','Aracaju','Grageru','49002000','Rua Laranjeiras','Apartamento 2403'),"
				+ "('TO','Palmas','Centro','77000000','Avenida JK','Loja 25'),"
				+ "('TO','Palmas','Plano Diretor Norte','77001000','Quadra 104 Norte','Sala 224'),"
				+ "('TO','Palmas','Plano Diretor Sul','77002000','Quadra 204 Sul','Apartamento 2504')";

		statement.execute(queryPopulate);

		System.out.println("TABELA ENDEREÇO_PESSOA POPULADA");

		statement.close();
	}

	private static void createPessoa(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryTablePessoa = "CREATE TABLE IF NOT EXISTS `pessoa` (" + " `id_pessoa` int NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "	`cpf` varchar(11) DEFAULT NULL," + "	`nome` varchar(50) NOT NULL,"
				+ "	`sobrenome` varchar(100) NOT NULL," + "	`fk_id_endereco_pessoa` int NOT NULL,"
				+ "	`telefone` varchar(14) NOT NULL," + "	`data_nascimento` date NOT NULL,"
				+ "	UNIQUE KEY `cpf` (`cpf`)" + "	)";

		String queryAddConstraintTablePessoa = "ALTER TABLE `pessoa` ADD CONSTRAINT fk_endereco_pessoa"
				+ " FOREIGN KEY (`fk_id_endereco_pessoa`)" + " REFERENCES `endereco_pessoa` (`id_endereco_pessoa`)";

		statement.execute(queryTablePessoa);
		statement.execute(queryAddConstraintTablePessoa);

		System.out.println("TABELA PESSOA CRIADA");

		statement.close();
	}

	private static void populatePessoa(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryPopulate = "INSERT INTO `pessoa` VALUES (1,'11111111111','João','Silva',1,'5506898765430','1981-05-27'),(2,'22222222222','Maria','Santos',2,'5506898765433','1960-12-09'),(3,'33333333333','Pedro','Lima',3,'5506898765434','2005-12-30'),(4,'44444444444','Ana','Souza',4,'5508223456789','2001-09-27'),(5,'55555555555','Lucas','Oliveira',5,'5508223456790','1976-05-30'),(6,'66666666666','Carla','Rodrigues',6,'5508223456791','2009-11-08'),(7,'77777777777','Mariana','Ferreira',7,'5509223456792','2020-08-15'),(8,'88888888888','Rafael','Costa',8,'5509223456793','1988-04-04'),(9,'99999999999','Luiza','Gomes',9,'5509223456794','1984-06-19'),(10,'10101010101','Daniel','Sousa',10,'5509623456789','1973-05-05'),(11,'12121212121','Juliana','Barbosa',11,'5509623456790','2001-02-22'),(12,'13131313131','Gustavo','Pereira',12,'5509623456791','2000-01-01'),(13,'14141414141','Eduardo','Rocha',13,'5507123456789','2007-08-30'),(14,'15151515151','Amanda','Oliveira',14,'5507123456790','2010-09-22'),(15,'16161616161','Roberto','Santos',15,'5507123456791','2001-06-11'),(16,'17171717171','Patrícia','Silva',16,'5508523456789','2003-12-12'),(17,'18181818181','Fernando','Almeida',17,'5508523456790','1999-03-17'),(18,'19191919191','Camila','Pereira',18,'5508523456791','2000-11-29'),(19,'20202020202','Ricardo','Oliveira',19,'5506123456789','2000-10-16'),(20,'21212121212','Carolina','Sousa',20,'5506123456790','1964-09-07'),(21,'22222222220','Gabriel','Ferreira',21,'5506123456791','1968-05-29'),(22,'23232323232','Larissa','Costa',22,'5502723456789','1970-10-10'),(23,'24242424242','Maurício','Rodrigues',23,'5502723456790','1988-07-06'),(24,'25252525252','Fernanda','Silva',24,'5502723456791','1990-03-03'),(25,'26262626262','Alexandre','Gomes',25,'5506223456789','2004-06-22'),(26,'27272727272','Marina','Santos',26,'5506223456790','2006-07-22'),(27,'28282828282','Gustavo','Rocha',27,'5506223456791','1994-02-28'),(28,'29292929292','Fernanda','Silva',28,'5509823456789','1995-06-30'),(29,'30303030303','Rafael','Costa',29,'5509823456790','2009-04-05'),(30,'31313131313','Isabela','Oliveira',30,'5509823456791','2001-03-21'),(31,'32323232323','Guilherme','Almeida',31,'5506523456789','1985-04-23'),(32,'33333333330','Laura','Sousa',32,'5506523456790','1994-06-23'),(33,'34343434343','Ricardo','Ferreira',33,'5506523456791','2000-09-14'),(34,'35353535353','Beatriz','Pereira',34,'5506723456789','2002-05-18'),(35,'36363636363','Pedro','Rodrigues',35,'5506723456790','1969-01-19'),(36,'37373737373','Larissa','Silva',36,'5506723456791','1970-07-22'),(37,'38383838383','Lucas','Oliveira',37,'5503123456789','1980-04-05'),(38,'39393939393','Isabella','Silva',38,'5503123456790','1990-11-22'),(39,'40404040404','Matheus','Costa',39,'5503123456791','1995-11-05'),(40,'41414141414','Carolina','Sousa',40,'5509123456789','2004-08-17'),(41,'42424242424','Bruno','Rodrigues',41,'5509123456790','2020-06-04'),(42,'43434343434','Lara','Ferreira',42,'5509123456791','2018-09-16'),(43,'44444444440','Thiago','Pereira',43,'5508323456789','2015-01-30'),(44,'45454545454','Giovanna','Silva',44,'5508323456790','2010-08-14'),(45,'46464646464','Rafael','Santos',45,'5508323456791','2001-05-20'),(46,'47474747474','Amanda','Costa',46,'55041123456789','1986-09-27'),(47,'48484848484','Felipe','Oliveira',47,'55041123456790','1981-04-30'),(48,'49494949494','Laura','Silva',48,'55041123456791','1988-09-20'),(49,'50505050505','Ricardo','Santos',49,'5508123456789','2004-09-22'),(50,'51515151515','Marina','Oliveira',50,'5508123456790','1968-05-24'),(51,'52525252525','Gabriel','Silva',51,'5508123456791','1963-02-10'),(52,'53535353535','Isabela','Ferreira',52,'5508623456789','1973-11-28'),(53,'54545454545','Pedro','Costa',53,'5508623456790','1984-03-10'),(54,'55555555550','Amanda','Silva',54,'5508623456791','1987-10-06'),(55,'56565656565','Bruno','Sousa',55,'5502123456789','1992-11-23'),(56,'57575757575','Laura','Pereira',56,'5502123456790','1999-01-15'),(57,'58585858585','Gustavo','Silva',57,'5502123456791','2006-05-15'),(58,'59595959595','Lara','Oliveira',58,'5508423456789','2008-12-09'),(59,'60606060606','Matheus','Santos',59,'5508423456790','2015-09-30'),(60,'61616161616','Carolina','Silva',60,'5508423456791','1965-11-30'),(61,'62626262626','Felipe','Ferreira',61,'5505123456789','1975-12-09'),(62,'63636363636','Lara','Santos',62,'5505123456790','1989-12-20'),(63,'64646464646','Gustavo','Costa',63,'5505123456791','1993-12-05'),(64,'65656565656','Gabriela','Silva',64,'5506923456789','2004-12-27'),(65,'66666666660','Lucas','Oliveira',65,'5506923456790','2001-04-06'),(66,'67676767676','Isabella','Ferreira',66,'5506923456791','1990-03-03'),(67,'68686868686','Matheus','Santos',67,'5509523456789','1998-04-01'),(68,'69696969696','Carolina','Silva',68,'5509523456790','2002-09-06'),(69,'70707070707','Rafael','Oliveira',69,'5509523456791','2010-07-22'),(70,'71717171717','Amanda','Ferreira',70,'5504723456789','2000-01-01'),(71,'72727272727','Bruno','Sousa',71,'5504723456790','2003-07-16'),(72,'73737373737','Lara','Silva',72,'5504723456791','2008-10-10'),(73,'74747474747','Lucas','Oliveira',73,'5501123456789','1995-05-05'),(74,'75757575757','Isabella','Silva',74,'5501123456790','1996-04-18'),(75,'76767676767','Rafael','Costa',75,'5501123456791','1995-08-10'),(76,'77777777770','Carolina','Sousa',76,'5507923456789','2003-07-14'),(77,'78787878787','Matheus','Ferreira',77,'5507923456790','2010-12-25'),(78,'79797979797','Lara','Silva',78,'5507923456791','1984-12-30'),(79,'80808080808','Bruno','Oliveira',79,'5506323456789','2000-06-09'),(80,'81818181818','Gabriela','Santos',80,'5506323456790','1983-08-08'),(81,'82828282828','Gustavo','Costa',81,'5506323456791','1982-11-10')";
		
		statement.execute(queryPopulate);
		
		System.out.println("TABELA PESSOA POPULADA");

		statement.close();
	}

	private static void createEnderecoCentro(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryCreateTableEnderecoCentro = "CREATE TABLE IF NOT EXISTS `endereco_centro` ("
				+ "  `id_endereco_centro` int NOT NULL PRIMARY KEY AUTO_INCREMENT," + "  `estado` varchar(2) NOT NULL,"
				+ "  `cidade` varchar(50) NOT NULL," + "  `bairro` varchar(50) NOT NULL,"
				+ "  `cep` varchar(8) NOT NULL," + "  `logradouro` varchar(50) NOT NULL,"
				+ "  `complemento` varchar(100) DEFAULT NULL" + ")";

		statement.execute(queryCreateTableEnderecoCentro);

		System.out.println("TABELA ENDEREÇO CENTRO CRIADA");

		statement.close();
	}

	private static void populateEnderecoCentro(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryPopulate = "INSERT INTO `endereco_centro` VALUES (1,'AC','Rio Branco','Estação Experimental','69903000','Alameda das Flores',''),(2,'AC','Rio Branco','Cadeia Velha','69904000','Praça da Liberdade',''),(3,'AL','Maceió','Jatiúca','57003000','Avenida Álvaro Otacílio',''),(4,'AL','Maceió','Centro','57004000','Rua do Comércio',''),(5,'AM','Manaus','Compensa','69003000','Travessa do Sol',''),(6,'AM','Manaus','Cidade Nova','69004000','Avenida Noel Nutels',''),(7,'AP','Macapá','Congós','68903000','Alameda dos Ipês',''),(8,'AP','Macapá','Pacoval','68904000','Praça da Independência',''),(9,'BA','Salvador','Barra','40003000','Travessa da Barra',''),(10,'BA','Salvador','Stiep','40004000','Avenida Tancredo Neves',''),(11,'CE','Fortaleza','Mucuripe','60003000','Travessa dos Navegantes',''),(12,'CE','Fortaleza','Cocó','60004000','Avenida Engenheiro Santana Júnior',''),(13,'DF','Brasília','Lago Norte','70003000','SHIN QL 10',''),(14,'DF','Brasília','Sudoeste','70004000','CLSW 300A',''),(15,'ES','Vitória','Jardim Camburi','29003000','Rua Aleixo Netto',''),(16,'ES','Vitória','Enseada do Suá','29004000','Avenida Nossa Senhora dos Navegantes',''),(17,'GO','Goiânia','Setor Campinas','70003000','Rua 24 de Outubro',''),(18,'GO','Goiânia','Setor Marista','70004000','Avenida Ricardo Paranhos',''),(19,'MA','São Luís','Renascença','65003000','Rua dos Maçaricos',''),(20,'MA','São Luís','Turu','65004000','Avenida dos Africanos',''),(21,'MT','Cuiabá','Santa Rosa','78003000','Rua dos Ipês',''),(22,'MT','Cuiabá','Quilombo','78004000','Avenida Historiador Rubens de Mendonça',''),(23,'MS','Campo Grande','Jardim Aeroporto','79003000','Rua da Paz',''),(24,'MS','Campo Grande','Amambaí','79004000','Avenida Ernesto Geisel',''),(25,'MG','Belo Horizonte','Funcionários','30003000','Rua Gonçalves Dias',''),(26,'MG','Belo Horizonte','Barro Preto','30004000','Avenida Augusto de Lima',''),(27,'PA','Belém','Cremação','66003000','Travessa Apinagés',''),(28,'PA','Belém','Marco','66004000','Avenida Pedro Miranda',''),(29,'PB','João Pessoa','Bessa','58003000','Rua Francisco Leocádio Ribeiro Coutinho',''),(30,'PB','João Pessoa','Cabo Branco','58004000','Avenida Olinda',''),(31,'PR','Curitiba','Champagnat','80003000','Rua Padre Anchieta',''),(32,'PR','Curitiba','Ecoville','80004000','Rua Monsenhor Ivo Zanlorenzi',''),(33,'PE','Recife','Madalena','50003000','Rua Real da Torre',''),(34,'PE','Recife','Espinheiro','50004000','Avenida Agamenon Magalhães',''),(35,'PI','Teresina','Dirceu','64003000','Rua das Orquídeas',''),(36,'PI','Teresina','Satélite','64004000','Avenida Zequinha Freire',''),(37,'RJ','Rio de Janeiro','Botafogo','20003000','Rua Voluntários da Pátria',''),(38,'RJ','Rio de Janeiro','Barra da Tijuca','20004000','Avenida das Américas',''),(39,'RN','Natal','Lagoa Nova','59003000','Rua São José',''),(40,'RN','Natal','Candelária','59004000','Avenida Prudente de Morais',''),(41,'RS','Porto Alegre','Cristal','90003000','Rua Diário de Notícias',''),(42,'RS','Porto Alegre','Petrópolis','90004000','Avenida Protásio Alves',''),(43,'RO','Porto Velho','Embratel','76803000','Rua Alexandre Guimarães',''),(44,'RO','Porto Velho','Nova Porto Velho','76804000','Avenida Rio de Janeiro',''),(45,'RR','Boa Vista','Caranã','69303000','Rua das Três Marias',''),(46,'RR','Boa Vista','Jóquei Clube','69304000','Avenida dos Imigrantes',''),(47,'SC','Florianópolis','Coqueiros','88003000','Avenida Engenheiro Max de Souza',''),(48,'SC','Florianópolis','Saco Grande','88004000','Rua Deputado Antônio Edu Vieira',''),(49,'SP','São Paulo','Pinheiros','01003000','Rua dos Pinheiros',''),(50,'SP','São Paulo','Vila Olímpia','01004000','Avenida Brigadeiro Faria Lima',''),(51,'SE','Aracaju','São Conrado','49003000','Avenida Desembargador Maynard',''),(52,'SE','Aracaju','Coroa do Meio','49004000','Avenida Beira Mar',''),(53,'TO','Palmas','Taquaralto','77003000','Avenida Tocantins',''),(54,'TO','Palmas','Jardim Aureny III','77004000','Rua 7','')";

		statement.execute(queryPopulate);

		System.out.println("TABELA ENDEREÇO_CENTRO POPULADA");

		statement.close();
	}

	private static void createCentro(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryCreateTableCentro = "CREATE TABLE IF NOT EXISTS `centro` ("
				+ "  `id_centro` int NOT NULL PRIMARY KEY AUTO_INCREMENT," + "  `nome` varchar(100) NOT NULL,"
				+ "  `tipo` enum('PUBLICO','PRIVADO') NOT NULL," + "  `fk_id_endereco_centro` int NOT NULL" + ")";

		String queryAddConstraintTableCentro = "ALTER TABLE `centro` ADD CONSTRAINT fk_id_endereco_centro "
				+ " FOREIGN KEY (`fk_id_endereco_centro`)" + " REFERENCES `endereco_centro` (`id_endereco_centro`)";

		statement.execute(queryCreateTableCentro);
		statement.execute(queryAddConstraintTableCentro);

		System.out.println("TABELA CENTRO CRIADA");

		statement.close();
	}

	private static void populateCentro(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryPopulate = "INSERT INTO `centro` VALUES (1,'Clínica São Lucas','PRIVADO',1),(2,'Hospital Santa Maria','PUBLICO',2),(3,'Centro Médico Vida Nova','PRIVADO',3),(4,'Ambulatório São José','PUBLICO',4),(5,'Unidade de Saúde Esperança','PRIVADO',5),(6,'Centro de Atendimento Médico Integrado','PUBLICO',6),(7,'Clínica Popular Bem Estar','PRIVADO',7),(8,'Hospital Municipal Nossa Senhora da Paz','PUBLICO',8),(9,'Centro Médico da Família','PRIVADO',9),(10,'Unidade de Saúde Cuidar','PUBLICO',10),(11,'Hospital Regional São João','PRIVADO',11),(12,'Clínica Saúde Total','PUBLICO',12),(13,'Centro de Atendimento Médico Especializado','PRIVADO',13),(14,'Ambulatório Esperança Viva','PUBLICO',14),(15,'Hospital São Francisco de Assis','PRIVADO',15),(16,'Hospital São Francisco de Assis','PUBLICO',16),(17,'Unidade de Saúde Bem Estar','PRIVADO',17),(18,'Centro Médico Esperança Nova','PUBLICO',18),(19,'Clínica Popular Saúde em Dia','PRIVADO',19),(20,'Hospital Municipal São Pedro','PUBLICO',20),(21,'Unidade de Saúde Mais Saúde','PRIVADO',21),(22,'Hospital Regional São Lucas','PUBLICO',22),(23,'Clínica Saúde Total','PRIVADO',23),(24,'Centro de Atendimento Médico Integrado','PUBLICO',24),(25,'Ambulatório Bem Estar Viva','PRIVADO',25),(26,'Unidade de Saúde São João','PUBLICO',26),(27,'Centro Médico Cuidar Bem','PRIVADO',27),(28,'Clínica Popular Vida Nova','PUBLICO',28),(29,'Hospital Municipal Nossa Senhora da Paz','PRIVADO',29),(30,'Centro de Saúde da Família','PUBLICO',30),(31,'Unidade de Saúde Esperança Viva','PRIVADO',31),(32,'Hospital São Francisco de Assis','PUBLICO',32),(33,'Clínica Saúde Total','PRIVADO',33),(34,'Centro Médico da Família','PUBLICO',34),(35,'Ambulatório São José','PRIVADO',35),(36,'Unidade de Saúde Mais Saúde','PUBLICO',36),(37,'Centro de Atendimento Médico Integrado','PRIVADO',37),(38,'Hospital Regional São João','PUBLICO',38),(39,'Clínica Popular Saúde em Dia','PRIVADO',39),(40,'Centro Médico Esperança Nova','PUBLICO',40),(41,'Unidade de Saúde Cuidar','PRIVADO',41),(42,'Hospital Municipal São Pedro','PUBLICO',42),(43,'Centro de Saúde da Família','PRIVADO',43),(44,'Clínica Saúde Total','PUBLICO',44),(45,'Centro Médico Cuidar Bem','PRIVADO',45),(46,'Ambulatório Bem Estar Viva','PUBLICO',46),(47,'Unidade de Saúde São João','PRIVADO',47),(48,'Hospital Regional São Lucas','PUBLICO',48),(49,'Centro de Atendimento Médico Especializado','PRIVADO',49),(50,'Clínica Popular Vida Nova','PUBLICO',50),(51,'Hospital Municipal Nossa Senhora da Paz','PRIVADO',51),(52,'Unidade de Saúde Esperança Viva','PUBLICO',52),(53,'Hospital São Francisco de Assis','PRIVADO',53),(54,'Hospital Santa Genoveva','PUBLICO',54)";
	
		statement.execute(queryPopulate);

		System.out.println("TABELA CENTRO POPULADA");

		statement.close();
	}

	private static void createTelefoneCentro(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryCreateTableTelefoneCentro = "CREATE TABLE IF NOT EXISTS `telefone_centro` ("
				+ "  `id_telefone_centro` int NOT NULL PRIMARY KEY AUTO_INCREMENT," + "  `fk_id_centro` int NOT NULL,"
				+ "  `numero` varchar(14) NOT NULL" + ")";

		String queryAddConstraintTelefoneCentro = "ALTER TABLE `telefone_centro` ADD CONSTRAINT fk_id_centro_telefone"
				+ " FOREIGN KEY (`fk_id_centro`)" + " REFERENCES `centro` (`id_centro`)";

		statement.execute(queryCreateTableTelefoneCentro);
		statement.execute(queryAddConstraintTelefoneCentro);

		System.out.println("TABELA TELEFONE CENTRO CRIADA");

		statement.close();
	}

	private static void populateTelefoneCentro(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryPopulate = "INSERT INTO `telefone_centro` VALUES (1,1,'068999999999'),(2,1,'068988888888'),(3,2,'068977777777'),(4,2,'068966666666'),(5,3,'082955555555'),(6,3,'082944444444'),(7,4,'082933333333'),(8,4,'082922222222'),(9,5,'092911111111'),(10,5,'092999999999'),(11,6,'092988888888'),(12,6,'092977777777'),(13,7,'096966666666'),(14,7,'096955555555'),(15,8,'096944444444'),(16,8,'096933333333'),(17,9,'071922222222'),(18,9,'071911111111'),(19,10,'071999999999'),(20,10,'071988888888'),(21,11,'085977777777'),(22,11,'085966666666'),(23,12,'085955555555'),(24,12,'085944444444'),(25,13,'061933333333'),(26,13,'061922222222'),(27,14,'061911111111'),(28,14,'061999999999'),(29,15,'027988888888'),(30,15,'027977777777'),(31,16,'027966666666'),(32,16,'027955555555'),(33,17,'062944444444'),(34,17,'062933333333'),(35,18,'062922222222'),(36,18,'062911111111'),(37,19,'098999999999'),(38,19,'098988888888'),(39,20,'098977777777'),(40,20,'098966666666'),(41,21,'065955555555'),(42,21,'065944444444'),(43,22,'065933333333'),(44,22,'065922222222'),(45,23,'067911111111'),(46,23,'067999999999'),(47,24,'067988888888'),(48,24,'067977777777'),(49,25,'031966666666'),(50,25,'031955555555'),(51,26,'031944444444'),(52,26,'031933333333'),(53,27,'091922222222'),(54,27,'091911111111'),(55,28,'091999999999'),(56,28,'091988888888'),(57,29,'083977777777'),(58,29,'083966666666'),(59,30,'083955555555'),(60,30,'083944444444'),(61,31,'041933333333'),(62,31,'041922222222'),(63,32,'041911111111'),(64,32,'041999999999'),(65,33,'081988888888'),(66,33,'081977777777'),(67,34,'081966666666'),(68,34,'081955555555'),(69,35,'086944444444'),(70,35,'086933333333'),(71,36,'086922222222'),(72,36,'086911111111'),(73,37,'021999999999'),(74,37,'021988888888'),(75,38,'021977777777'),(76,38,'021966666666'),(77,39,'084955555555'),(78,39,'084944444444'),(79,40,'084933333333'),(80,40,'084922222222'),(81,41,'051911111111'),(82,41,'051999999999'),(83,42,'051988888888'),(84,42,'051977777777'),(85,43,'069966666666'),(86,43,'069955555555'),(87,44,'069944444444'),(88,44,'069933333333'),(89,45,'095922222222'),(90,45,'095911111111'),(91,46,'095999999999'),(92,46,'095988888888'),(93,47,'047977777777'),(94,47,'047966666666'),(95,48,'047955555555'),(96,48,'047944444444'),(97,49,'011933333333'),(98,49,'011922222222'),(99,50,'011911111111'),(100,50,'011999999999'),(101,51,'079988888888'),(102,51,'079977777777'),(103,52,'079966666666'),(104,52,'079955555555'),(105,53,'063944444444'),(106,53,'063933333333'),(107,54,'063922222222'),(108,54,'063911111111')";
		
		statement.execute(queryPopulate);

		System.out.println("TABELA TELEFONE_CENTRO POPULADA");

		statement.close();
	}

	private static void createVacina(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryCreateTableVacina = "CREATE TABLE IF NOT EXISTS `vacina` ("
				+ "  `id_vacina` int NOT NULL PRIMARY KEY AUTO_INCREMENT," + "  `nome` varchar(50) NOT NULL,"
				+ "  `fabricante` varchar(100) NOT NULL," + "  `combate` varchar(50) NOT NULL,"
				+ "  `doses_minimas` int NOT NULL DEFAULT 1," + "  `dias_proxima_dose` int DEFAULT 0" + ")";

		statement.execute(queryCreateTableVacina);

		System.out.println("TABELA VACINA CRIADA");

		statement.close();
	}

	private static void populateVacina(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryPopulate = "INSERT INTO `vacina` (nome, fabricante, combate, doses_minimas, dias_proxima_dose) VALUES ('Pfizer-BioNTech','Pfizer Inc.','COVID-19',2,21),('Moderna','Moderna Therapeutics','COVID-19',2,28),('AstraZeneca','AstraZeneca PLC','COVID-19',2,84),('Johnson & Johnson','Johnson & Johnson','COVID-19',1,0),('CoronaVac','Sinovac Biotech','COVID-19',2,28)";
		
		statement.execute(queryPopulate);

		System.out.println("TABELA VACINA POPULADA");

		statement.close();
	}

	private static void createVacinacao(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryCreateTableVacinacao = "CREATE TABLE IF NOT EXISTS `vacinacao` ("
				+ "  `id_vacinacao` int NOT NULL PRIMARY KEY AUTO_INCREMENT," + "  `fk_id_pessoa` int NOT NULL,"
				+ "  `fk_id_centro` int NOT NULL," + "  `fk_id_vacina` int NOT NULL,"
				+ "  `dia_vacinacao` date NOT NULL," + "  `dia_proxima_dose` date DEFAULT NULL" + ")";

		String queryAddConstraintTableVacinacao = "ALTER TABLE `vacinacao` ADD CONSTRAINT fk_id_pessoa_vacinacao"
				+ " FOREIGN KEY (`fk_id_pessoa`)" + " REFERENCES `pessoa` (`id_pessoa`)";

		String queryAddConstraint2TableVacinacao = "ALTER TABLE `vacinacao` ADD CONSTRAINT fk_id_centro_vacinacao"
				+ " FOREIGN KEY (`fk_id_centro`)" + " REFERENCES `centro` (`id_centro`)";

		String queryAddConstraint3TableVacinacao = "ALTER TABLE `vacinacao` ADD CONSTRAINT fk_id_vacina_vacinacao"
				+ " FOREIGN KEY (`fk_id_vacina`)" + " REFERENCES `vacina` (`id_vacina`)";

		statement.execute(queryCreateTableVacinacao);
		statement.execute(queryAddConstraintTableVacinacao);
		statement.execute(queryAddConstraint2TableVacinacao);
		statement.execute(queryAddConstraint3TableVacinacao);

		System.out.println("TABELA VACINAÇÃO CRIADA");

		statement.close();
	}

	private static void populateVacinacao(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String queryPopulate = "INSERT INTO `vacinacao` VALUES (1,1,1,5,'2023-01-15','2023-02-12'),(2,1,1,5,'2023-02-12',NULL),(3,2,2,2,'2023-05-15','2023-06-12'),(4,2,2,2,'2023-06-12',NULL),(5,4,3,1,'2023-02-27','2023-03-20'),(6,4,3,1,'2023-03-20',NULL),(7,5,3,4,'2023-03-01',NULL),(8,6,4,3,'2023-06-08','2023-08-31'),(9,6,4,3,'2023-08-31',NULL),(10,7,5,2,'2023-01-03','2023-01-31'),(11,7,5,2,'2023-01-31',NULL),(12,9,6,2,'2023-03-30','2023-04-27'),(13,9,6,2,'2023-04-27',NULL),(14,10,8,1,'2023-04-15','2023-05-06'),(15,10,8,1,'2023-05-06',NULL),(16,11,8,2,'2023-04-10','2023-05-08'),(17,11,8,2,'2023-05-08',NULL),(18,12,8,3,'2023-01-30','2023-04-24'),(19,12,8,3,'2023-04-24',NULL),(20,13,10,4,'2023-06-10',NULL),(21,16,11,5,'2023-04-01','2023-04-29'),(22,16,11,5,'2023-04-29',NULL),(23,19,14,1,'2023-05-30','2023-06-20'),(24,19,14,1,'2023-06-20',NULL),(25,20,14,2,'2023-02-09','2023-03-09'),(26,20,14,2,'2023-03-09',NULL),(27,21,13,3,'2023-01-29','2023-04-23'),(28,21,13,3,'2023-04-23',NULL),(29,22,15,4,'2023-06-01',NULL),(30,23,16,5,'2023-04-15','2023-05-13'),(31,23,16,5,'2023-05-13',NULL),(32,25,17,1,'2023-05-28','2023-06-18'),(33,25,17,1,'2023-06-18',NULL),(34,31,21,2,'2023-01-16','2023-02-13'),(35,31,21,2,'2023-02-13',NULL),(36,32,22,3,'2023-03-28','2023-06-20'),(37,32,22,3,'2023-06-20',NULL),(38,33,21,4,'2023-04-05',NULL),(39,34,24,5,'2023-04-05','2023-05-03'),(40,34,24,5,'2023-05-03',NULL),(41,35,24,1,'2023-04-05','2023-04-26'),(42,35,24,1,'2023-04-26',NULL),(43,37,26,2,'2023-05-21','2023-06-18'),(44,37,26,2,'2023-06-18',NULL),(45,38,26,3,'2023-03-08','2023-05-31'),(46,38,26,3,'2023-05-31',NULL),(47,39,26,4,'2023-06-21',NULL),(48,46,32,5,'2023-02-14','2023-03-14'),(49,46,32,5,'2023-03-14',NULL),(50,47,31,1,'2023-05-26','2023-06-16'),(51,47,31,1,'2023-06-16',NULL),(52,48,31,2,'2023-03-04','2023-04-01'),(53,48,31,2,'2023-04-01',NULL),(54,51,34,3,'2023-04-04','2023-06-27'),(55,51,34,3,'2023-06-27',NULL),(56,50,33,4,'2023-06-04',NULL),(57,53,35,5,'2023-06-20','2023-07-18'),(58,53,35,5,'2023-07-18',NULL),(59,55,37,1,'2023-01-14','2023-02-04'),(60,55,37,1,'2023-02-04',NULL),(61,56,37,2,'2023-03-19','2023-04-16'),(62,56,37,2,'2023-04-16',NULL),(63,57,37,3,'2023-04-23','2023-07-16'),(64,57,37,3,'2023-07-16',NULL),(65,58,39,4,'2023-06-01',NULL),(66,61,41,5,'2023-02-22','2023-03-22'),(67,61,41,5,'2023-03-22',NULL),(68,62,41,1,'2023-05-10','2023-05-31'),(69,62,41,1,'2023-05-31',NULL),(70,63,42,2,'2023-01-17','2023-02-14'),(71,63,42,2,'2023-02-14',NULL),(72,66,44,3,'2023-06-17','2023-09-09'),(73,66,44,3,'2023-09-09',NULL),(74,68,45,4,'2023-05-18',NULL),(75,69,46,5,'2023-03-18','2023-04-15'),(76,69,46,5,'2023-04-15',NULL),(77,70,47,1,'2023-06-21','2023-07-12'),(78,70,47,1,'2023-07-12',NULL),(79,71,48,2,'2023-01-06','2023-02-03'),(80,71,48,2,'2023-02-03',NULL),(81,72,48,3,'2023-04-27','2023-07-20'),(82,72,48,3,'2023-07-20',NULL),(83,73,50,4,'2023-04-01',NULL),(84,74,50,5,'2023-04-13','2023-05-11'),(85,74,50,5,'2023-05-11',NULL),(86,75,50,1,'2023-05-07','2023-05-28'),(87,75,50,1,'2023-05-28',NULL),(88,79,53,2,'2023-01-23','2023-02-20'),(89,79,53,2,'2023-02-20',NULL),(90,81,54,3,'2023-02-07','2023-05-02'),(91,81,54,3,'2023-05-02',NULL)";
		
		statement.execute(queryPopulate);

		System.out.println("TABELA VACINAÇÃO POPULADA");

		statement.close();
	}
}
