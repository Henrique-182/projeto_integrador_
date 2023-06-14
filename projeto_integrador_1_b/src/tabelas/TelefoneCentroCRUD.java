package tabelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import painel.TelefoneCentroPainel;

public class TelefoneCentroCRUD {

	public static void createTelefones(Connection connection, Integer idCentro) throws SQLException {
		String[][] telefones = TelefoneCentroPainel.novo();
		
		String primeiroNumero = telefones[0][0] + telefones[0][1];
		String segundoNumero = telefones[1][0] + telefones[1][1];
		
		String query = "INSERT INTO "
				+ " TELEFONE_CENTRO(fk_id_centro, numero)"
				+ " VALUES(?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idCentro);
		statement.setString(2, primeiroNumero);
		
		statement.executeUpdate();
		
		String query2 = "INSERT INTO "
				+ " TELEFONE_CENTRO(fk_id_centro, numero)"
				+ " VALUES(?, ?)"; 
		
		statement = connection.prepareStatement(query2);
		statement.setInt(1, idCentro);
		statement.setString(2, segundoNumero);
		
		statement.executeUpdate();
		
		statement.close();
	}
}
