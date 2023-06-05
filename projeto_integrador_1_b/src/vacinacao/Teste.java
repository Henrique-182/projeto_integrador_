package vacinacao;

import java.sql.Connection;
import java.sql.SQLException;

import conexao.Conexao;
import tabelas.PessoaCRUD;

public class Teste {

	public static void main(String[] args) throws SQLException {
		Connection connection = Conexao.createConnection();
		PessoaCRUD.selectPorEstado(connection);
	}

}
