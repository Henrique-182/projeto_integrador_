package tabelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import painel.VacinacaoPainel;

public class VacinacaoCRUD {

	public static void selectAgendamentos(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		Integer i = 0;
		while(true) {
			if(i == 0) {				
				String querySetIdMaximo = "set @idMaximo = 0" ;
				statement.execute(querySetIdMaximo);
				
				i++;
				
			} else {
				String query = "SELECT id_vacinacao AS idVacinacao, dia_vacinacao as diaVacinacao, p.nome AS nomePessoa, p.sobrenome AS sobrenomePessoa, c.nome AS nomeCentro, v.nome as nomeVacina FROM vacinacao vacinacao"
				+ " INNER JOIN vacina v ON v.id_vacina = vacinacao.fk_id_vacina"
				+ " INNER JOIN pessoa p ON p.id_pessoa = vacinacao.fk_id_pessoa"
				+ " INNER JOIN centro c ON c.id_centro = vacinacao.fk_id_centro"
				+ " WHERE (dia_vacinacao > curdate()) AND (id_vacinacao > @idMaximo)"
				+ " LIMIT 5";
				
				ResultSet resultSet = statement.executeQuery(query);
				
				boolean retornouRegistros = false;
				
				StringBuilder sb = new StringBuilder();
				while(resultSet.next()) {
					retornouRegistros = true;
					
					Integer idVacinacao = resultSet.getInt("idVacinacao");
					String nomePessoa = resultSet.getString("nomePessoa");
					String sobrenomePessoa = resultSet.getString("sobrenomePessoa");
					String nomeVacina = resultSet.getString("nomeVacina");
					String nomeCentro = resultSet.getString("nomeCentro");
					String diaVacinacao = resultSet.getString("diaVacinacao");
					String separador = "=-=-=-=-=-=-=-=-=-=-=- id: " + idVacinacao + " -=-=-=-=-=-=-=-=-=-=-=-=\n";
					
					String s = nomePessoa + " " + sobrenomePessoa + "\n"
							+ nomeVacina + "\n"
							+ nomeCentro + "\n"
							+ diaVacinacao + "\n";
					
					sb.append(separador);
					sb.append(s);
				}
				
				if(!retornouRegistros) {
					break;
				}
				
				JOptionPane.showMessageDialog(null, sb, "Todas os Agendamentos: " + i +"ª página", 1);
				i++;
					
				resultSet.close();
				
				String querySetIdMaximo = "SET @idMaximo = ("
						+ " SELECT MAX(id_vacinacao) AS id_maximo FROM ("
						+ " SELECT id_vacinacao FROM VACINACAO vacinacao"
						+ " WHERE (vacinacao.dia_vacinacao > curdate()) AND (vacinacao.id_vacinacao > @idMaximo) LIMIT 5) AS subquery)";

				
				statement.execute(querySetIdMaximo);
			}
		}
		statement.close();
	}

	public static void selectVacinados(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		Integer i = 0;
		while(true) {
			if(i == 0) {				
				String querySetIdMaximo = "set @idMaximo = 0" ;
				statement.execute(querySetIdMaximo);
				
				i++;
				
			} else {
				String query = "SELECT vac.id_vacinacao AS idVacinacao, vac.dia_vacinacao AS diaVacinacao, p.nome AS nomePessoa, p.sobrenome as sobrenomePessoa, v.nome AS nomeVacina, c.nome AS nomeCentro FROM VACINACAO vac"
						+ " INNER JOIN pessoa p ON p.id_pessoa = vac.fk_id_pessoa"
						+ " INNER JOIN vacina v ON v.id_vacina = vac.fk_id_vacina"
						+ " INNER JOIN centro c ON c.id_centro = vac.fk_id_centro"
						+ "	WHERE (dia_vacinacao <= curdate()) AND (id_vacinacao > @idMaximo)"
						+ "	LIMIT 5;";
				
				ResultSet resultSet = statement.executeQuery(query);
				
				boolean retornouRegistros = false;
				
				StringBuilder sb = new StringBuilder();
				while(resultSet.next()) {
					retornouRegistros = true;
					
					Integer idVacinacao = resultSet.getInt("idVacinacao");
					String nomePessoa = resultSet.getString("nomePessoa");
					String sobrenomePessoa = resultSet.getString("sobrenomePessoa");
					String nomeVacina = resultSet.getString("nomeVacina");
					String nomeCentro = resultSet.getString("nomeCentro");
					String diaVacinacao = resultSet.getString("diaVacinacao");
					String separador = "=-=-=-=-=-=-=-=-=-=-=- id: " + idVacinacao + " -=-=-=-=-=-=-=-=-=-=-=-=\n";
					
					String s = nomePessoa + " " + sobrenomePessoa + "\n"
							+ nomeVacina + "\n"
							+ nomeCentro + "\n"
							+ diaVacinacao + "\n";
					
					sb.append(separador);
					sb.append(s);
				}
				
				if(!retornouRegistros) {
					break;
				}
				
				JOptionPane.showMessageDialog(null, sb, "Todas as Vacinações realizadas: " + i +"ª página", 1);
				i++;
					
				resultSet.close();
				
				String querySetIdMaximo = "SET @idMaximo = ("
						+ " SELECT MAX(id_vacinacao) AS id_maximo FROM ("
						+ " SELECT vac.id_vacinacao FROM VACINACAO vac"
						+ " WHERE (vac.dia_vacinacao <= curdate()) AND (vac.id_vacinacao > @idMaximo) LIMIT 5) AS subquery)";

				
				statement.execute(querySetIdMaximo);
			}
		}
		statement.close();
	}

	public static void selectTopVacinas(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		String query = "SELECT v.nome AS nomeVacina, COUNT(DISTINCT CONCAT(vac.fk_id_pessoa, '_', vac.fk_id_vacina)) AS totalAplicacoes FROM vacina v"
				+ " LEFT JOIN vacinacao vac ON v.id_vacina = vac.fk_id_vacina"
				+ " GROUP BY v.id_vacina"
				+ " ORDER BY COUNT(DISTINCT CONCAT(vac.fk_id_pessoa, '_', vac.fk_id_vacina)) DESC LIMIT 10";
		
		ResultSet resultSet = statement.executeQuery(query);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Nome -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= Aplicações\n");
		
		while(resultSet.next()) {
			String nomeVacina = resultSet.getString("nomeVacina");
			Integer totalAplicacoes = resultSet.getInt("totalAplicacoes");
			
			int tamanho = nomeVacina.length();
			int qnt = 40;
			if(tamanho % 2 == 0) {
				qnt -= tamanho;
			} else {
				qnt -= tamanho;
		}
			
			
		String s = String.format("%-40s %d aplicações\n", nomeVacina, totalAplicacoes);
		
		sb.append(s);
		}
		
		JOptionPane.showMessageDialog(null, sb, "Vacinas mais aplicadas:", 1);
			
		resultSet.close();
		statement.close();
	}

	public static void selectVacinacaoPorEstado(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		String query = "SELECT e.estado AS estado, count(DISTINCT v.fk_id_pessoa) AS totalAplicacoes FROM endereco_pessoa e"
				+ " INNER JOIN pessoa p ON p.fk_id_endereco_pessoa = e.id_endereco_pessoa"
				+ " LEFT JOIN vacinacao v ON v.fk_id_pessoa = p.id_pessoa"
				+ " GROUP BY e.estado"
				+ " ORDER BY 2 DESC";
		
		ResultSet resultSet = statement.executeQuery(query);
		
		StringBuilder sb = new StringBuilder();
		
		while(resultSet.next()) {
			String estado = resultSet.getString("estado");
			Integer totalAplicacoes = resultSet.getInt("totalAplicacoes");
			
		String s = estado + ".................................................." + totalAplicacoes + " Aplicações\n";
		
		sb.append(s);
		}
		
		JOptionPane.showMessageDialog(null, sb, "Estados com mais aplicações:", 1);
			
		resultSet.close();
		statement.close();
	}


	
	public static void insert(Connection connection) throws SQLException {
		String[] dados = VacinacaoPainel.novo();
		Integer idVacina = Integer.parseInt(dados[0]);
		Integer idPessoa = Integer.parseInt(dados[1]);
		Integer idCentro = Integer.parseInt(dados[2]);
		String data = dados[3];
		
		String query = "CALL proc_agendar_vacinacao(?, ?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idVacina);
		statement.setInt(2, idPessoa);
		statement.setInt(3, idCentro);
		statement.setString(4, data);
		
		statement.executeUpdate();
		
		statement.close();
	}
}
