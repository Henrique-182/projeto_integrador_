package tabelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import painel.EnderecoPainel;

public class EnderecoPessoaCRUD {
	
	private static Connection connection;
	
	public EnderecoPessoaCRUD(Connection connection) {
		EnderecoPessoaCRUD.connection = connection;
	}


	public Integer getIdEndereco() throws SQLException {
		String[] endereco = EnderecoPainel.novo();
		String estado = endereco[0];
		String cidade = endereco[1];
		String bairro = endereco[2];
		String cep = endereco[3];
		String logradouro = endereco[4];
		String complemento = endereco[5];
		
		Integer id = getIdEnderecoExistente(estado, cidade, bairro, cep, logradouro, complemento);

		if(id != 0) {
			return id;
		} else {
			String queryEndereco = "INSERT INTO"
					+ " ENDERECO_PESSOA(estado, cidade, bairro, cep, logradouro, complemento)"
					+ " VALUES(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(queryEndereco, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, estado);
			statement.setString(2, cidade);
			statement.setString(3, bairro);
			statement.setString(4, cep);
			statement.setString(5, logradouro);
			statement.setString(6, complemento);
			
			statement.executeUpdate();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			
			resultSet.next();

			return resultSet.getInt(1);
		}
		
	}
	
	private static Integer getIdEnderecoExistente(
			String estado,
			String cidade,
			String bairro,
			String cep,
			String logradouro,
			String complemento
			) throws SQLException {
		
		String query = "SELECT id_endereco_pessoa"
				+ "	FROM endereco_pessoa"
				+ " WHERE estado = ?"
				+ " AND cidade = ?"
				+ " AND bairro = ?"
				+ " AND cep = ?"
				+ " AND logradouro = ?"
				+ " AND complemento = ?";
		
		PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, estado);
		statement.setString(2, cidade);
		statement.setString(3, bairro);
		statement.setString(4, cep);
		statement.setString(5, logradouro);
		statement.setString(6, complemento);
		
		ResultSet resultSet = statement.executeQuery();
		
		try {
			resultSet.next();
			return resultSet.getInt("id_endereco_pessoa");
		} catch (Exception e) {
			return 0;
		} finally {
			statement.close();
		}

	}

}
