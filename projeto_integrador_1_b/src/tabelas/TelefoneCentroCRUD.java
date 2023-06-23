package tabelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import painel.TelefoneCentroPainel;

public class TelefoneCentroCRUD {
	
	public static StringBuilder selectTelefone(Connection connection, Integer idCentro) throws SQLException {
		Statement statement = connection.createStatement();
		
		String queryTelefone = "SELECT * FROM telefone_centro WHERE fk_id_centro = "+ idCentro;
		
		ResultSet resultSet = statement.executeQuery(queryTelefone);
		
		StringBuilder sb = new StringBuilder();
		
		Integer i = 1;
		
		while(resultSet.next()) {
			String numeroTelefone = resultSet.getString("numero");
			
			if(i == 1) {
				sb.append(numeroTelefone + "\n");
				i++;
			} else {
				sb.append(numeroTelefone);
			}
			
		}
		
		resultSet.close();
		
		statement.close();
		
		return sb;
	}

	public static void createTelefones(Connection connection, Integer idCentro) throws SQLException {
		String[][] telefones = TelefoneCentroPainel.novo();
		
		String primeiroNumero = "55" + telefones[0][0] + telefones[0][1];
		String segundoNumero = "55" + telefones[1][0] + telefones[1][1];
		
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
