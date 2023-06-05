package tabelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import painel.EnderecoPainel;

public class EnderecoCentroCRUD {
	
	private static Connection connection;
	
	public EnderecoCentroCRUD(Connection connection) {
		EnderecoCentroCRUD.connection = connection;
	}

	public Integer createAndGetIdEndereco() throws SQLException {
		String[] endereco = EnderecoPainel.novo();
		String estado = endereco[0];
		String cidade = endereco[1];
		String bairro = endereco[2];
		String cep = endereco[3];
		String logradouro = endereco[4];
		String complemento = endereco[5];
		
		String query = "INSERT INTO"
				+ " ENDERECO_CENTRO(estado, cidade, bairro, cep, logradouro, complemento)"
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
