package conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {

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
				+ "('TO','Palmas','Plano Diretor Sul','77002000','Quadra 204 Sul','Apartamento 2504'),"
				+ "('GO','Goiânia','Jardim Vila Boa','74360490','Avenida Pedro Ludovico Teixeira','Qd50 Lt10 Cs02')";

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

		String queryPopulate = "INSERT INTO `pessoa` VALUES (1,'11111111111','João','Silva',1,'5506898765430','1981-05-27'),(2,'22222222222','Maria','Santos',2,'5506898765433','2000-01-01'),(3,'33333333333','Pedro','Lima',3,'5506898765434','2005-12-30'),(4,'44444444444','Ana','Souza',4,'5508223456789','2000-01-01'),(5,'55555555555','Lucas','Oliveira',5,'5508223456790','2000-01-01'),(6,'66666666666','Carla','Rodrigues',6,'5508223456791','2000-01-01'),(7,'77777777777','Mariana','Ferreira',7,'5509223456792','2000-01-01'),(8,'88888888888','Rafael','Costa',8,'5509223456793','2000-01-01'),(9,'99999999999','Luiza','Gomes',9,'5509223456794','2000-01-01'),(10,'10101010101','Daniel','Sousa',10,'5509623456789','2000-01-01'),(11,'12121212121','Juliana','Barbosa',11,'5509623456790','2000-01-01'),(12,'13131313131','Gustavo','Pereira',12,'5509623456791','2000-01-01'),(13,'14141414141','Eduardo','Rocha',13,'5507123456789','2000-01-01'),(14,'15151515151','Amanda','Oliveira',14,'5507123456790','2000-01-01'),(15,'16161616161','Roberto','Santos',15,'5507123456791','2000-01-01'),(16,'17171717171','Patrícia','Silva',16,'5508523456789','2000-01-01'),(17,'18181818181','Fernando','Almeida',17,'5508523456790','2000-01-01'),(18,'19191919191','Camila','Pereira',18,'5508523456791','2000-01-01'),(19,'20202020202','Ricardo','Oliveira',19,'5506123456789','2000-01-01'),(20,'21212121212','Carolina','Sousa',20,'5506123456790','2000-01-01'),(21,'22222222220','Gabriel','Ferreira',21,'5506123456791','2000-01-01'),(22,'23232323232','Larissa','Costa',22,'5502723456789','2000-01-01'),(23,'24242424242','Maurício','Rodrigues',23,'5502723456790','2000-01-01'),(24,'25252525252','Fernanda','Silva',24,'5502723456791','2000-01-01'),(25,'26262626262','Alexandre','Gomes',25,'5506223456789','2000-01-01'),(26,'27272727272','Marina','Santos',26,'5506223456790','2000-01-01'),(27,'28282828282','Gustavo','Rocha',27,'5506223456791','2000-01-01'),(28,'29292929292','Fernanda','Silva',28,'5509823456789','2000-01-01'),(29,'30303030303','Rafael','Costa',29,'5509823456790','2000-01-01'),(30,'31313131313','Isabela','Oliveira',30,'5509823456791','2000-01-01'),(31,'32323232323','Guilherme','Almeida',31,'5506523456789','2000-01-01'),(32,'33333333330','Laura','Sousa',32,'5506523456790','2000-01-01'),(33,'34343434343','Ricardo','Ferreira',33,'5506523456791','2000-01-01'),(34,'35353535353','Beatriz','Pereira',34,'5506723456789','2000-01-01'),(35,'36363636363','Pedro','Rodrigues',35,'5506723456790','2000-01-01'),(36,'37373737373','Larissa','Silva',36,'5506723456791','2000-01-01'),(37,'38383838383','Lucas','Oliveira',37,'5503123456789','2000-01-01'),(38,'39393939393','Isabella','Silva',38,'5503123456790','2000-01-01'),(39,'40404040404','Matheus','Costa',39,'5503123456791','2000-01-01'),(40,'41414141414','Carolina','Sousa',40,'5509123456789','2000-01-01'),(41,'42424242424','Bruno','Rodrigues',41,'5509123456790','2000-01-01'),(42,'43434343434','Lara','Ferreira',42,'5509123456791','2000-01-01'),(43,'44444444440','Thiago','Pereira',43,'5508323456789','2000-01-01'),(44,'45454545454','Giovanna','Silva',44,'5508323456790','2000-01-01'),(45,'46464646464','Rafael','Santos',45,'5508323456791','2000-01-01'),(46,'47474747474','Amanda','Costa',46,'55041123456789','2000-01-01'),(47,'48484848484','Felipe','Oliveira',47,'55041123456790','2000-01-01'),(48,'49494949494','Laura','Silva',48,'55041123456791','2000-01-01'),(49,'50505050505','Ricardo','Santos',49,'5508123456789','2000-01-01'),(50,'51515151515','Marina','Oliveira',50,'5508123456790','2000-01-01'),(51,'52525252525','Gabriel','Silva',51,'5508123456791','2000-01-01'),(52,'53535353535','Isabela','Ferreira',52,'5508623456789','2000-01-01'),(53,'54545454545','Pedro','Costa',53,'5508623456790','2000-01-01'),(54,'55555555550','Amanda','Silva',54,'5508623456791','2000-01-01'),(55,'56565656565','Bruno','Sousa',55,'5502123456789','2000-01-01'),(56,'57575757575','Laura','Pereira',56,'5502123456790','2000-01-01'),(57,'58585858585','Gustavo','Silva',57,'5502123456791','2000-01-01'),(58,'59595959595','Lara','Oliveira',58,'5508423456789','2000-01-01'),(59,'60606060606','Matheus','Santos',59,'5508423456790','2000-01-01'),(60,'61616161616','Carolina','Silva',60,'5508423456791','2000-01-01'),(61,'62626262626','Felipe','Ferreira',61,'5505123456789','2000-01-01'),(62,'63636363636','Lara','Santos',62,'5505123456790','2000-01-01'),(63,'64646464646','Gustavo','Costa',63,'5505123456791','2000-01-01'),(64,'65656565656','Gabriela','Silva',64,'5506923456789','2000-01-01'),(65,'66666666660','Lucas','Oliveira',65,'5506923456790','2000-01-01'),(66,'67676767676','Isabella','Ferreira',66,'5506923456791','2000-01-01'),(67,'68686868686','Matheus','Santos',67,'5509523456789','2000-01-01'),(68,'69696969696','Carolina','Silva',68,'5509523456790','2000-01-01'),(69,'70707070707','Rafael','Oliveira',69,'5509523456791','2000-01-01'),(70,'71717171717','Amanda','Ferreira',70,'5504723456789','2000-01-01'),(71,'72727272727','Bruno','Sousa',71,'5504723456790','2000-01-01'),(72,'73737373737','Lara','Silva',72,'5504723456791','2000-01-01'),(73,'74747474747','Lucas','Oliveira',73,'5501123456789','2000-01-01'),(74,'75757575757','Isabella','Silva',74,'5501123456790','2000-01-01'),(75,'76767676767','Rafael','Costa',75,'5501123456791','2000-01-01'),(76,'77777777770','Carolina','Sousa',76,'5507923456789','2000-01-01'),(77,'78787878787','Matheus','Ferreira',77,'5507923456790','2000-01-01'),(78,'79797979797','Lara','Silva',78,'5507923456791','2000-01-01'),(79,'80808080808','Bruno','Oliveira',79,'5506323456789','2000-06-09'),(80,'81818181818','Gabriela','Santos',80,'5506323456790','2000-01-01'),(81,'82828282828','Gustavo','Costa',81,'5506323456791','2000-01-01'),(85,'07090936163','Henrique','Augusto Lobo',82,'5506892625495','2000-01-01'),(86,'00009308148','Marina','Lobo Pires',82,'5506281696169','1983-09-22'),(87,'91538232120','Ricardo','Augusto',82,'5506293338938','1980-06-03')";
		
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

		String queryPopulate = "INSERT INTO `endereco_centro` VALUES (1,'AC','Rio Branco','Estação Experimental','69903000','Alameda das Flores',''),(2,'AC','Rio Branco','Cadeia Velha','69904000','Praça da Liberdade',''),(3,'AL','Maceió','Jatiúca','57003000','Avenida Álvaro Otacílio',''),(4,'AL','Maceió','Centro','57004000','Rua do Comércio',''),(5,'AM','Manaus','Compensa','69003000','Travessa do Sol',''),(6,'AM','Manaus','Cidade Nova','69004000','Avenida Noel Nutels',''),(7,'AP','Macapá','Congós','68903000','Alameda dos Ipês',''),(8,'AP','Macapá','Pacoval','68904000','Praça da Independência',''),(9,'BA','Salvador','Barra','40003000','Travessa da Barra',''),(10,'BA','Salvador','Stiep','40004000','Avenida Tancredo Neves',''),(11,'CE','Fortaleza','Mucuripe','60003000','Travessa dos Navegantes',''),(12,'CE','Fortaleza','Cocó','60004000','Avenida Engenheiro Santana Júnior',''),(13,'DF','Brasília','Lago Norte','70003000','SHIN QL 10',''),(14,'DF','Brasília','Sudoeste','70004000','CLSW 300A',''),(15,'ES','Vitória','Jardim Camburi','29003000','Rua Aleixo Netto',''),(16,'ES','Vitória','Enseada do Suá','29004000','Avenida Nossa Senhora dos Navegantes',''),(17,'GO','Goiânia','Setor Campinas','70003000','Rua 24 de Outubro',''),(18,'GO','Goiânia','Setor Marista','70004000','Avenida Ricardo Paranhos',''),(19,'MA','São Luís','Renascença','65003000','Rua dos Maçaricos',''),(20,'MA','São Luís','Turu','65004000','Avenida dos Africanos',''),(21,'MT','Cuiabá','Santa Rosa','78003000','Rua dos Ipês',''),(22,'MT','Cuiabá','Quilombo','78004000','Avenida Historiador Rubens de Mendonça',''),(23,'MS','Campo Grande','Jardim Aeroporto','79003000','Rua da Paz',''),(24,'MS','Campo Grande','Amambaí','79004000','Avenida Ernesto Geisel',''),(25,'MG','Belo Horizonte','Funcionários','30003000','Rua Gonçalves Dias',''),(26,'MG','Belo Horizonte','Barro Preto','30004000','Avenida Augusto de Lima',''),(27,'PA','Belém','Cremação','66003000','Travessa Apinagés',''),(28,'PA','Belém','Marco','66004000','Avenida Pedro Miranda',''),(29,'PB','João Pessoa','Bessa','58003000','Rua Francisco Leocádio Ribeiro Coutinho',''),(30,'PB','João Pessoa','Cabo Branco','58004000','Avenida Olinda',''),(31,'PR','Curitiba','Champagnat','80003000','Rua Padre Anchieta',''),(32,'PR','Curitiba','Ecoville','80004000','Rua Monsenhor Ivo Zanlorenzi',''),(33,'PE','Recife','Madalena','50003000','Rua Real da Torre',''),(34,'PE','Recife','Espinheiro','50004000','Avenida Agamenon Magalhães',''),(35,'PI','Teresina','Dirceu','64003000','Rua das Orquídeas',''),(36,'PI','Teresina','Satélite','64004000','Avenida Zequinha Freire',''),(37,'RJ','Rio de Janeiro','Botafogo','20003000','Rua Voluntários da Pátria',''),(38,'RJ','Rio de Janeiro','Barra da Tijuca','20004000','Avenida das Américas',''),(39,'RN','Natal','Lagoa Nova','59003000','Rua São José',''),(40,'RN','Natal','Candelária','59004000','Avenida Prudente de Morais',''),(41,'RS','Porto Alegre','Cristal','90003000','Rua Diário de Notícias',''),(42,'RS','Porto Alegre','Petrópolis','90004000','Avenida Protásio Alves',''),(43,'RO','Porto Velho','Embratel','76803000','Rua Alexandre Guimarães',''),(44,'RO','Porto Velho','Nova Porto Velho','76804000','Avenida Rio de Janeiro',''),(45,'RR','Boa Vista','Caranã','69303000','Rua das Três Marias',''),(46,'RR','Boa Vista','Jóquei Clube','69304000','Avenida dos Imigrantes',''),(47,'SC','Florianópolis','Coqueiros','88003000','Avenida Engenheiro Max de Souza',''),(48,'SC','Florianópolis','Saco Grande','88004000','Rua Deputado Antônio Edu Vieira',''),(49,'SP','São Paulo','Pinheiros','01003000','Rua dos Pinheiros',''),(50,'SP','São Paulo','Vila Olímpia','01004000','Avenida Brigadeiro Faria Lima',''),(51,'SE','Aracaju','São Conrado','49003000','Avenida Desembargador Maynard',''),(52,'SE','Aracaju','Coroa do Meio','49004000','Avenida Beira Mar',''),(53,'TO','Palmas','Taquaralto','77003000','Avenida Tocantins',''),(54,'TO','Palmas','Jardim Aureny III','77004000','Rua 7',''),(55,'GO','Goiânia','Jardim Vila Boa','74360400','Avenida Duque de Caxias','Ao Lado do PitDog')";

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

		String queryPopulate = "INSERT INTO `centro` VALUES (1,'Clínica São Lucas','PRIVADO',1),(2,'Hospital Santa Maria','PUBLICO',2),(3,'Centro Médico Vida Nova','PRIVADO',3),(4,'Ambulatório São José','PUBLICO',4),(5,'Unidade de Saúde Esperança','PRIVADO',5),(6,'Centro de Atendimento Médico Integrado','PUBLICO',6),(7,'Clínica Popular Bem Estar','PRIVADO',7),(8,'Hospital Municipal Nossa Senhora da Paz','PUBLICO',8),(9,'Centro Médico da Família','PRIVADO',9),(10,'Unidade de Saúde Cuidar','PUBLICO',10),(11,'Hospital Regional São João','PRIVADO',11),(12,'Clínica Saúde Total','PUBLICO',12),(13,'Centro de Atendimento Médico Especializado','PRIVADO',13),(14,'Ambulatório Esperança Viva','PUBLICO',14),(15,'Hospital São Francisco de Assis','PRIVADO',15),(16,'Hospital São Francisco de Assis','PUBLICO',16),(17,'Unidade de Saúde Bem Estar','PRIVADO',17),(18,'Centro Médico Esperança Nova','PUBLICO',18),(19,'Clínica Popular Saúde em Dia','PRIVADO',19),(20,'Hospital Municipal São Pedro','PUBLICO',20),(21,'Unidade de Saúde Mais Saúde','PRIVADO',21),(22,'Hospital Regional São Lucas','PUBLICO',22),(23,'Clínica Saúde Total','PRIVADO',23),(24,'Centro de Atendimento Médico Integrado','PUBLICO',24),(25,'Ambulatório Bem Estar Viva','PRIVADO',25),(26,'Unidade de Saúde São João','PUBLICO',26),(27,'Centro Médico Cuidar Bem','PRIVADO',27),(28,'Clínica Popular Vida Nova','PUBLICO',28),(29,'Hospital Municipal Nossa Senhora da Paz','PRIVADO',29),(30,'Centro de Saúde da Família','PUBLICO',30),(31,'Unidade de Saúde Esperança Viva','PRIVADO',31),(32,'Hospital São Francisco de Assis','PUBLICO',32),(33,'Clínica Saúde Total','PRIVADO',33),(34,'Centro Médico da Família','PUBLICO',34),(35,'Ambulatório São José','PRIVADO',35),(36,'Unidade de Saúde Mais Saúde','PUBLICO',36),(37,'Centro de Atendimento Médico Integrado','PRIVADO',37),(38,'Hospital Regional São João','PUBLICO',38),(39,'Clínica Popular Saúde em Dia','PRIVADO',39),(40,'Centro Médico Esperança Nova','PUBLICO',40),(41,'Unidade de Saúde Cuidar','PRIVADO',41),(42,'Hospital Municipal São Pedro','PUBLICO',42),(43,'Centro de Saúde da Família','PRIVADO',43),(44,'Clínica Saúde Total','PUBLICO',44),(45,'Centro Médico Cuidar Bem','PRIVADO',45),(46,'Ambulatório Bem Estar Viva','PUBLICO',46),(47,'Unidade de Saúde São João','PRIVADO',47),(48,'Hospital Regional São Lucas','PUBLICO',48),(49,'Centro de Atendimento Médico Especializado','PRIVADO',49),(50,'Clínica Popular Vida Nova','PUBLICO',50),(51,'Hospital Municipal Nossa Senhora da Paz','PRIVADO',51),(52,'Unidade de Saúde Esperança Viva','PUBLICO',52),(53,'Hospital São Francisco de Assis','PRIVADO',53),(54,'Hospital Santa Genoveva','PUBLICO',54),(55,'Cais Vila Boa','PUBLICO',55)";
	
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

		String queryPopulate = "";
		
		/*statement.execute(queryPopulate);*/

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

		String queryPopulate = "INSERT INTO `vacina` (nome, fabricante, combate, doses_minimas, dias_proxima_dose) VALUES ('Pfizer-BioNTech','Pfizer Inc.','COVID-19',2,21),('Moderna','Moderna Therapeutics','COVID-19',2,28),('AstraZeneca','AstraZeneca PLC','COVID-19',2,84),('Johnson & Johnson','Johnson & Johnson','COVID-19',1,0),('CoronaVac','Sinovac Biotech','COVID-19',2,28),('Vacina V','Laboratório','COVID-19',3,60),('Vacina VV','Laboratório LL','COVID-19',1,0),('Vacina VVV','Laboratório LLL','COVID-19',2,40)";
		
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

		String queryPopulate = "";
		
		/*statement.execute(queryPopulate);*/

		System.out.println("TABELA VACINAÇÃO POPULADA");

		statement.close();
	}
}
