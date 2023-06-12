package tabelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import painel.CentroPainel;
import painel.EnderecoPainel;

public class CentroCRUD {

	public static void selectPaginado(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		Integer i = 0;
		while (true) {
			if (i == 0) {
				i++;
				String querySetIdMinimo = "set @idMinimo = 0";
				String querySetIdMaximo = "set @idMaximo = 0";

				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);

			} else {
				String querySetIdMinimo = "set @idMinimo = (SELECT MIN(id_centro) FROM centro where id_centro > @idMaximo LIMIT 8)";
				String querySetIdMaximo = "set @idMaximo = (SELECT MAX(id_centro) as id_maximo FROM (SELECT id_centro FROM centro where id_centro > @idMaximo LIMIT 8) as subquery)";
				String query = "select * from centro where id_centro between @idMinimo and @idMaximo;";

				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);

				ResultSet resultSet = statement.executeQuery(query);

				boolean retornouRegistros = false;

				StringBuilder sb = new StringBuilder();
				while (resultSet.next()) {
					retornouRegistros = true;

					Integer id = resultSet.getInt("id_centro");
					String nome = resultSet.getString("nome");
					String tipo = resultSet.getString("tipo");
					String separador = "=-=-=-=-=-=-=-=-=-=-=- id: " + id + " -=-=-=-=-=-=-=-=-=-=-=-=\n";

					String s = "Nome: " + nome + "\n"
							+ "Tipo: " + tipo + "\n";

					sb.append(separador);
					sb.append(s);
				}

				if (!retornouRegistros) {
					break;
				}

				JOptionPane.showMessageDialog(null, sb, "Todas os centros: " + i + "ª página", 1);
				i++;

				resultSet.close();
			}
		}
		statement.close();
		connection.close();
	}
	
	public static void selectPorEstado(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		String estadoWhere = EnderecoPainel.getEstado();
		
		Integer i = 0;
		while(true) {
			if(i == 0) {
				i++;
				String querySetIdMinimo = "set @idMinimo = 0";
				String querySetIdMaximo = "set @idMaximo = 0" ;
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
			} else {
				String querySetIdMinimo = "set @idMinimo = (SELECT MIN(c.id_centro) FROM centro c LEFT JOIN endereco_centro e ON e.id_endereco_centro = c.fk_id_endereco_centro WHERE e.estado = '" + estadoWhere + "' AND c.id_centro > @idMaximo LIMIT 3)";
				String querySetIdMaximo = "set @idMaximo = (SELECT MAX(id_centro) as id_maximo FROM (SELECT c.id_centro FROM centro c LEFT JOIN endereco_centro e ON e.id_endereco_centro = c.fk_id_endereco_centro WHERE e.estado = '" + estadoWhere + "' AND c.id_centro > @idMaximo LIMIT 3) as subquery)";
				String query = "SELECT c.id_centro, c.nome, c.tipo, e.estado, e.cidade, e.bairro, e.cep, e.logradouro, e.complemento FROM centro c"
						+ " LEFT JOIN endereco_centro e ON e.id_endereco_centro = c.fk_id_endereco_centro"
						+ "	WHERE e.estado = '" + estadoWhere + "' AND "
						+ " c.id_centro BETWEEN @idMinimo and @idMaximo";
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
				
				ResultSet resultSet = statement.executeQuery(query);
				
				boolean retornouRegistros = false;
				
				StringBuilder sb = new StringBuilder();
				while(resultSet.next()) {
					retornouRegistros = true;
					
					Integer id = resultSet.getInt("id_centro");
					String nome = resultSet.getString("nome");
					String tipo = resultSet.getString("tipo");
					String estado = resultSet.getString("estado");
					String cidade = resultSet.getString("cidade");
					String bairro = resultSet.getString("bairro");
					String cep = resultSet.getString("cep");
					String logradouro = resultSet.getString("logradouro");
					String complemento = resultSet.getString("complemento");
					String separador = "=-=-=-=-=-=-=-=-=-=-=-=-= id: " + id + " =-=-=-=-=-=-=-=-=-=-=-=-=-=\n";
					
					String s = nome + "\n"
							+ tipo + "\n"
							+ cidade + " - " + estado + "\n"
							+ bairro + " - " + cep + "\n"
							+ logradouro + ", " + complemento + "\n";
					
					sb.append(separador);
					sb.append(s);
				}
				
				if(!retornouRegistros) {
					break;
				}
				
				JOptionPane.showMessageDialog(null, sb, "Centros por Estado:" + i + "ª página", 1);
				i++;
				
			}
		} 
		 
		statement.close();
	}

	public static void insert(Connection connection, Integer idEndereco) throws SQLException {
		String[] centro = CentroPainel.novo();
		String nome = centro[0];
		String tipo = centro[1];

		String query = "INSERT INTO" + " centro(nome, tipo, fk_id_endereco_centro)" + " VALUES(?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, nome);
		statement.setString(2, tipo);
		statement.setInt(3, idEndereco);

		statement.executeUpdate();

		statement.close();
	}
}
