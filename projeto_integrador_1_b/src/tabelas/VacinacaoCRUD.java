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
				
				JOptionPane.showMessageDialog(null, sb, "Todas as pessoas: " + i +"ª página", 1);
				i++;
					
				resultSet.close();
				
				String querySetIdMaximo = "SET @idMaximo = ("
						+ " SELECT MAX(id_vacinacao) AS id_maximo FROM ("
						+ " SELECT id_vacinacao FROM VACINACAO vacinacao"
						+ " INNER JOIN vacina v ON v.id_vacina = vacinacao.fk_id_vacina"
						+ " INNER JOIN pessoa p ON p.id_pessoa = vacinacao.fk_id_pessoa"
						+ " INNER JOIN centro c ON c.id_centro = vacinacao.fk_id_centro"
						+ " WHERE (vacinacao.dia_vacinacao > curdate()) AND (vacinacao.id_vacinacao > @idMaximo) LIMIT 5) AS subquery)";

				
				statement.execute(querySetIdMaximo);
			}
		}
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
