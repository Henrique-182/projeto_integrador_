package tabelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import painel.CentroPainel;

public class CentroCRUD {
	
	public CentroCRUD() {}

	public static void insert(Connection connection, Integer idEndereco) throws SQLException {
		String[] centro = CentroPainel.novo();
		String nome = centro[0];
		String tipo = centro[1];
		
		String query = "INSERT INTO"
				+ " centro(nome, tipo, fk_id_endereco_centro)"
				+ " VALUES(?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, nome);
		statement.setString(2, tipo);
		statement.setInt(3, idEndereco);
		
		statement.executeUpdate();
		
		statement.close();
	}
}
