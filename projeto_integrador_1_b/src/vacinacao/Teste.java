package vacinacao;

import java.sql.Connection;
import java.sql.SQLException;

import conexao.Conexao;
import tabelas.VacinacaoCRUD;

public class Teste {

	public static void main(String[] args) throws SQLException {
		Connection connection = Conexao.createConnection();
		VacinacaoCRUD.insert(connection);
	}

}
