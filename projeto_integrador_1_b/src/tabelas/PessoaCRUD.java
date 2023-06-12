package tabelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import painel.EnderecoPainel;
import painel.PessoaPainel;

public class PessoaCRUD {
	
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
				String querySetIdMinimo = "set @idMinimo = (SELECT min(id_pessoa) FROM pessoa where id_pessoa > @idMaximo LIMIT 8)";
				String querySetIdMaximo = "set @idMaximo = (SELECT MAX(id_pessoa) as id_maximo FROM (SELECT id_pessoa FROM pessoa where id_pessoa > @idMaximo LIMIT 8) as subquery)";
				String query = "select id_pessoa, cpf, nome, sobrenome from pessoa where id_pessoa between @idMinimo and @idMaximo;";
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
				
				ResultSet resultSet = statement.executeQuery(query);
				
				boolean retornouRegistros = false;
				
				StringBuilder sb = new StringBuilder();
				while(resultSet.next()) {
					retornouRegistros = true;
					
					Integer id = resultSet.getInt("id_pessoa");
					String cpf = resultSet.getString("cpf");
					String nome = resultSet.getString("nome");
					String sobrenome = resultSet.getString("sobrenome");
					String separador = "=-=-=-=-=-=-=-=-=-=-=- id: " + id + " -=-=-=-=-=-=-=-=-=-=-=-=\n";
					
					String s = "CPF: " + cpf + "\n"
							+ "Nome: " + nome + " " + sobrenome + "\n";
					
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
				String querySetIdMinimo = "set @idMinimo = (SELECT MIN(p.id_pessoa) FROM pessoa p LEFT JOIN endereco_pessoa e ON e.id_endereco_pessoa = p.fk_id_endereco_pessoa WHERE e.estado = '" + estadoWhere + "' AND p.id_pessoa > @idMaximo LIMIT 3)";
				String querySetIdMaximo = "set @idMaximo = (SELECT MAX(id_pessoa) as id_maximo FROM (SELECT p.id_pessoa FROM pessoa p LEFT JOIN endereco_pessoa e ON e.id_endereco_pessoa = p.fk_id_endereco_pessoa WHERE e.estado = '" + estadoWhere + "' AND p.id_pessoa > @idMaximo LIMIT 3) as subquery)";
				String query = "SELECT p.id_pessoa, p.nome, p.sobrenome, e.estado, e.cidade, e.bairro, e.cep, e.logradouro, e.complemento FROM pessoa p"
						+ " LEFT JOIN endereco_pessoa e ON e.id_endereco_pessoa = p.fk_id_endereco_pessoa"
						+ "	WHERE e.estado = '" + estadoWhere + "' AND "
						+ " p.id_pessoa BETWEEN @idMinimo and @idMaximo";
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
				
				ResultSet resultSet = statement.executeQuery(query);
				
				boolean retornouRegistros = false;
				
				StringBuilder sb = new StringBuilder();
				while(resultSet.next()) {
					retornouRegistros = true;
					
					Integer id = resultSet.getInt("id_pessoa");
					String nome = resultSet.getString("nome");
					String sobrenome = resultSet.getString("sobrenome");
					String estado = resultSet.getString("estado");
					String cidade = resultSet.getString("cidade");
					String bairro = resultSet.getString("bairro");
					String cep = resultSet.getString("cep");
					String logradouro = resultSet.getString("logradouro");
					String complemento = resultSet.getString("complemento");
					String separador = "=-=-=-=-=-=-=-=-=-=-=-=-= id: " + id + " =-=-=-=-=-=-=-=-=-=-=-=-=-=\n";
					
					String s = nome + " " + sobrenome + "\n"
							+ cidade + " - " + estado + "\n"
							+ bairro + " - " + cep + "\n"
							+ logradouro + ", " + complemento + "\n";
					
					sb.append(separador);
					sb.append(s);
				}
				
				if(!retornouRegistros) {
					break;
				}
				
				JOptionPane.showMessageDialog(null, sb, "Pessoas por Estado:" + i + "ª página", 1);
				i++;
				
			}
		} 
		 
		statement.close();
	}

	public static void selectPorCPF(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		String cpfWhere = PessoaPainel.getCPF();
		cpfWhere = "%" + cpfWhere + "%";
		
		Integer i = 0;
		while(true) {
			if(i == 0) {
				i++;
				String querySetIdMinimo = "set @idMinimo = 0";
				String querySetIdMaximo = "set @idMaximo = 0" ;
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
			} else {
				String querySetIdMinimo = "set @idMinimo = (SELECT min(id_pessoa) FROM pessoa WHERE cpf LIKE '" + cpfWhere + "' AND id_pessoa > @idMaximo LIMIT 3)";
				String querySetIdMaximo = "set @idMaximo = (SELECT MAX(id_pessoa) as id_maximo FROM (SELECT id_pessoa FROM pessoa WHERE cpf LIKE '" + cpfWhere + "' AND id_pessoa > @idMaximo LIMIT 3) as subquery)";
				String query = "SELECT id_pessoa, nome, sobrenome, cpf FROM pessoa"
						+ " WHERE cpf LIKE '" + cpfWhere + "' AND "
						+ " id_pessoa BETWEEN @idMinimo and @idMaximo";
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
				
				ResultSet resultSet = statement.executeQuery(query);
				
				boolean retornouRegistros = false;
				
				StringBuilder sb = new StringBuilder();
				while(resultSet.next()) {
					retornouRegistros = true;
					
					Integer id = resultSet.getInt("id_pessoa");
					String nome = resultSet.getString("nome");
					String sobrenome = resultSet.getString("sobrenome");
					String cpf = resultSet.getString("cpf");
					String separador = "=-=-=-=-=-=-=-=-=-=-=-=-= id: " + id + " =-=-=-=-=-=-=-=-=-=-=-=-=-=\n";
					
					String s = "Nome: " + nome + " " + sobrenome + "\n"
							+ "CPF: " + cpf + "\n";
					
					sb.append(separador);
					sb.append(s);
				}
				
				if(!retornouRegistros) {
					break;
				}
				
				JOptionPane.showMessageDialog(null, sb, "Pessoas por CPF:" + i + "ª página", 1);
				i++; 
				
			}
		} 
		 
		statement.close();
	}

	public static void selectPorIdade(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		String[] idadeWhere = PessoaPainel.getIdade();
		int menorIdade = Integer.parseInt(idadeWhere[0]);
		int maiorIdade = Integer.parseInt(idadeWhere[1]);
		
		Integer i = 0;
		while(true) {
			if(i == 0) {
				i++;
				String querySetIdMinimo = "set @idMinimo = 0";
				String querySetIdMaximo = "set @idMaximo = 0" ;
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
			} else {
				String querySetIdMinimo = "set @idMinimo = (SELECT MIN(id_pessoa) FROM pessoa WHERE TIMESTAMPDIFF(YEAR, data_nascimento, curdate()) >= " + menorIdade + " AND TIMESTAMPDIFF(YEAR, data_nascimento, curdate()) <= " + maiorIdade + " AND id_pessoa > @idMaximo LIMIT 5)";
				String querySetIdMaximo = "set @idMaximo = (SELECT MAX(id_pessoa) as id_maximo FROM (SELECT id_pessoa FROM pessoa WHERE TIMESTAMPDIFF(YEAR, data_nascimento, curdate()) >= " + menorIdade + " AND TIMESTAMPDIFF(YEAR, data_nascimento, curdate()) <= " + maiorIdade + " AND id_pessoa > @idMaximo LIMIT 5) as subquery)";
				String query = "SELECT id_pessoa, nome, sobrenome, TIMESTAMPDIFF(YEAR, data_nascimento, curdate()) AS idade FROM pessoa"
						+ "	WHERE TIMESTAMPDIFF(YEAR, data_nascimento, curdate()) >= " + menorIdade
						+ " AND TIMESTAMPDIFF(YEAR, data_nascimento, curdate()) <= " + maiorIdade
						+ " AND id_pessoa BETWEEN @idMinimo and @idMaximo";
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
				
				ResultSet resultSet = statement.executeQuery(query);
				
				boolean retornouRegistros = false;
				
				StringBuilder sb = new StringBuilder();
				while(resultSet.next()) {
					retornouRegistros = true;
					
					Integer id = resultSet.getInt("id_pessoa");
					String nome = resultSet.getString("nome");
					String sobrenome = resultSet.getString("sobrenome");
					Integer idade = resultSet.getInt("idade");
					String separador = "=-=-=-=-=-=-=-=-=-=-=-=-= id: " + id + " =-=-=-=-=-=-=-=-=-=-=-=-=-=\n";
					
					String s = "Nome: " + nome + " " + sobrenome + "\n"
							+ "Idade: " + idade + "\n";
					
					sb.append(separador);
					sb.append(s);
				}
				
				if(!retornouRegistros) {
					break;
				}
				
				JOptionPane.showMessageDialog(null, sb, "Pessoas por Nome:" + i + "ª página", 1);
				i++;
			}
		} 
		 
		statement.close();
	}

	public static void selectPorNome(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		String[] nomeWhere = PessoaPainel.getNome();
		String primeiroNome = "%" + nomeWhere[0] + "%";
		String sobrenome = "%" + nomeWhere[1] + "%";
		
		Integer i = 0;
		while(true) {
			if(i == 0) {
				i++;
				String querySetIdMinimo = "set @idMinimo = 0";
				String querySetIdMaximo = "set @idMaximo = 0" ;
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
			} else {
				String querySetIdMinimo = "set @idMinimo = (SELECT MIN(id_pessoa) FROM pessoa WHERE (nome LIKE '" + primeiroNome + "' AND sobrenome LIKE '" + sobrenome + "') AND id_pessoa > @idMaximo LIMIT 3)";
				String querySetIdMaximo = "set @idMaximo = (SELECT MAX(id_pessoa) as id_maximo FROM (SELECT id_pessoa FROM pessoa WHERE (nome LIKE '" + primeiroNome + "' AND sobrenome LIKE '" + sobrenome + "') AND id_pessoa > @idMaximo LIMIT 3) as subquery)";
				String query = "SELECT id_pessoa, nome, sobrenome, cpf FROM pessoa"
						+ "	WHERE (nome LIKE '" + primeiroNome + "' AND sobrenome LIKE '" + sobrenome + "')"
						+ " AND id_pessoa BETWEEN @idMinimo and @idMaximo";
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
				
				ResultSet resultSet = statement.executeQuery(query);
				
				boolean retornouRegistros = false;
				
				StringBuilder sb = new StringBuilder();
				while(resultSet.next()) {
					retornouRegistros = true;
					
					Integer id = resultSet.getInt("id_pessoa");
					String nomeCompleto = resultSet.getString("nome") + " " + resultSet.getString("sobrenome");
					String cpf = resultSet.getString("cpf");
					String separador = "=-=-=-=-=-=-=-=-=-=-=-=-= id: " + id + " =-=-=-=-=-=-=-=-=-=-=-=-=-=\n";
					
					String s = "Nome: " + nomeCompleto + "\n"
							+ "CPF: " + cpf + "\n";
					
					sb.append(separador);
					sb.append(s);
				}
				
				if(!retornouRegistros) {
					break;
				}
				
				JOptionPane.showMessageDialog(null, sb, "Pessoas por Nome:" + i + "ª página", 1);
				i++;
			}
		} 
		 
		statement.close();
	}

	public static void update(Connection connection, String coluna, String novoValor, Integer id) throws SQLException {
			String query = "UPDATE pessoa "
					+ " SET " + coluna + " = ?"
					+ " WHERE id_pessoa = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, novoValor);
			statement.setInt(2, id);
			
			Integer afetados = statement.executeUpdate();
			
			System.out.println("Registros atualizados: " + afetados);
			
			statement.close();
		
	}
	
	public static void insert(Connection connection, Integer idEndereco) throws SQLException {
		String[] pessoa = PessoaPainel.novo();
		String query = "INSERT INTO "
				+ " pessoa(cpf, nome, sobrenome, fk_id_endereco_pessoa, telefone, data_nascimento)"
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, pessoa[0]);
		statement.setString(2, pessoa[1]);
		statement.setString(3, pessoa[2]);
		statement.setInt(4, idEndereco);
		statement.setString(5, pessoa[3]);
		statement.setString(6, pessoa[4]);
		
		statement.executeUpdate();
		
		statement.close();
	}
}
