package vacinacao.cadastros;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexao.Conexao;
import tabelas.CentroCRUD;
import tabelas.EnderecoCentroCRUD;

public class CentroCadastro {
	
	private static Connection connection;

	public static void cadastrar() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			EnderecoCentroCRUD endereco = new EnderecoCentroCRUD(connection);
			Integer id = endereco.createAndGetIdEndereco();
			
			CentroCRUD.insert(connection, id);
			
			JOptionPane.showMessageDialog(null, "CENTRO CRIADO COM SUCESSO", "Sucesso", 1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO AO CRIAR CENTRO", "Erro", 0);
		} finally {
			connection.close();
		}
	} 
}
