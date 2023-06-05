package tabelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import conexao.Conexao;
import painel.EnderecoPainel;
import painel.PessoaPainel;
import view.View;

public class PessoaCRUD {
	
	private static Connection connection;
	
	public PessoaCRUD() {}
	
	public StringBuilder select() throws SQLException {
		connection = Conexao.createConnection();
		Statement statement = connection.createStatement();

		String query = "SELECT * FROM pessoa";
		
		ResultSet resultSet = statement.executeQuery(query);
		
		StringBuilder sb = new StringBuilder();
		
		while(resultSet.next()) {
			Integer id = resultSet.getInt("id_pessoa");
			String cpf = resultSet.getString("cpf");
			String nome = resultSet.getString("nome");
			String sobrenome = resultSet.getString("sobrenome");
			String telefone = resultSet.getString("telefone");
			String separador = "=-=-=-=-=-=-=-=-=-=-=-=-=-=\n";
			
			String s = id + "ª: (" + cpf + ") " + nome + " " + sobrenome + " (" + telefone + ")\n";
			
			sb.append(s);
			sb.append(separador);
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		return sb;
	}
	
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
				String query = "select * from pessoa where id_pessoa between @idMinimo and @idMaximo;";
				
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
					String telefone = resultSet.getString("telefone");
					String separador = "=-=-=-=-=-=-=-=-=-=-=- id: " + id + " -=-=-=-=-=-=-=-=-=-=-=-=\n";
					
					String s = "CPF: " + cpf + "\n"
							+ "Nome: " + nome + " " + sobrenome + "\n"
							+ "Telefone: " + telefone + "\n";
					
					sb.append(separador);
					sb.append(s);
				}
				
				if(!retornouRegistros) {
					JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS REGISTROS", "Todas as Pessoas", 1);
					break;
				}
				
				JOptionPane.showMessageDialog(null, sb, "Todas as pessoas: " + i +"ª página", 1);
				i++;
					
				resultSet.close();
			}
		}
		statement.close();
		connection.close();
	}
	
	public static void selectPorEstado(Connection connection) throws SQLException {
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
				String estadoWhere = EnderecoPainel.getEstado();
				
				String querySetIdMinimo = "set @idMinimo = (SELECT min(id_pessoa) FROM pessoa where id_pessoa > @idMaximo LIMIT 3)";
				String querySetIdMaximo = "set @idMaximo = (SELECT MAX(id_pessoa) as id_maximo FROM (SELECT id_pessoa FROM pessoa where id_pessoa > @idMaximo LIMIT 3) as subquery)";
				String query = "SELECT * FROM pessoa p"
						+ " LEFT JOIN endereco_pessoa e ON e.id_endereco_pessoa = p.fk_id_endereco_pessoa"
						+ "	WHERE e.estado = ?"
						+ " AND id_pessoa BETWEEN @idMinimo and @idMaximo";
				
				statement.execute(querySetIdMinimo);
				statement.execute(querySetIdMaximo);
				
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, estadoWhere);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
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
				
				preparedStatement.close();
			}
		} 
		 
		statement.close();
	}
	
	public static void selectComEndereco() throws SQLException {
		Statement statement = connection.createStatement();
		
		String query = "SELECT * FROM pessoa p"
				+ " LEFT JOIN endereco_pessoa e ON e.id_endereco_pessoa = p.fk_id_endereco_pessoa";
		
		ResultSet resultSet = statement.executeQuery(query);
		
		while(resultSet.next()) {
			Integer id = resultSet.getInt("id_pessoa");
			String cpf = resultSet.getString("cpf");
			String nome = resultSet.getString("nome");
			String sobrenome = resultSet.getString("sobrenome");
			String telefone = resultSet.getString("telefone");
			String estado = resultSet.getString("estado");
			String cidade = resultSet.getString("cidade");
			String bairro = resultSet.getString("bairro");
			String cep = resultSet.getString("cep");
			String logradouro = resultSet.getString("logradouro");
			String complemento = resultSet.getString("complemento");
			View.formataPessoaComEndereco(cpf, nome, sobrenome, telefone, estado, cidade, bairro, cep, logradouro, complemento);
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		}
		
		resultSet.close();
		statement.close();
	}
	
	public static void update(String coluna, String novoValor, Integer id) throws SQLException {
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
				+ " pessoa(cpf, nome, sobrenome, fk_id_endereco_pessoa, telefone)"
				+ " VALUES(?, ?, ?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, pessoa[0]);
		statement.setString(2, pessoa[1]);
		statement.setString(3, pessoa[2]);
		statement.setInt(4, idEndereco);
		statement.setString(5, pessoa[3]);
		
		statement.executeUpdate();
		
		statement.close();
	}
}
