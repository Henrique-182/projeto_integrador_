package tabelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import painel.VacinaPainel;

public class VacinaCRUD {
	
	public static void selectPaginado(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		Integer i = 0;
		while(true) {
			if(i == 0) {
				i++;
				String querySetIdMinimo = "set @idMinimo = 0";
				String querySetIdMaximo = "set @idMaximo = 0" ;
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
				
			} else {
				String querySetIdMinimo = "set @idMinimo = (SELECT min(id_vacina) FROM vacina where id_vacina > @idMaximo LIMIT 3)";
				String querySetIdMaximo = "set @idMaximo = (SELECT MAX(id_vacina) as id_maximo FROM (SELECT id_vacina FROM vacina where id_vacina > @idMaximo LIMIT 3) as subquery)";
				String query = "select * from vacina where id_vacina between @idMinimo and @idMaximo;";
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
				
				ResultSet resultSet = statement.executeQuery(query);
				
				boolean retornouRegistros = false;
				
				StringBuilder sb = new StringBuilder();
				while(resultSet.next()) {
					retornouRegistros = true;
					
					Integer id = resultSet.getInt("id_vacina");
					String nome = resultSet.getString("nome");
					String fabricante = resultSet.getString("fabricante");
					String combate = resultSet.getString("combate");
					String dosesMinimas = resultSet.getString("doses_minimas");
					String diasProximaDose = resultSet.getString("dias_proxima_dose");
					String separador = "=-=-=-=-=-=-=-=-=-=-=- id: " + id + " -=-=-=-=-=-=-=-=-=-=-=-=\n";
					
					String s = "Nome: " + nome + "\n"
							+ "Fabricante: " + fabricante + "\n"
							+ "Combate: " + combate + "\n"
							+ "Doses mínimas: " + dosesMinimas + "\n"
							+ "Dias próxima dose: " + diasProximaDose + "\n";
					
					sb.append(separador);
					sb.append(s);
				}
				
				if(!retornouRegistros) {
					break;
				}
				
				JOptionPane.showMessageDialog(null, sb, "Todas as pessoas: " + i +"ª página", 1);
				i++;
					
				resultSet.close();
			}
		}
		statement.close();
	}

	public static void insert(Connection connection) throws SQLException {
		String[] vacina = VacinaPainel.novo();
		
		String query = "INSERT INTO "
				+ " vacina(nome, fabricante, combate, doses_minimas, dias_proxima_dose)"
				+ " VALUES(?, ?, ?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, vacina[0]);
		statement.setString(2, vacina[1]);
		statement.setString(3, vacina[2]);
		statement.setInt(4, Integer.parseInt(vacina[3]));
		statement.setInt(5, Integer.parseInt(vacina[4]));
		
		statement.executeUpdate();
		
		statement.close();
	}
}
