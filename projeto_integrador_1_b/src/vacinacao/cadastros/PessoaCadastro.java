package vacinacao.cadastros;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexao.Conexao;
import tabelas.EnderecoPessoaCRUD;
import tabelas.PessoaCRUD;

public class PessoaCadastro {
	
	private static Connection connection;

	public static void cadastrar() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			EnderecoPessoaCRUD endereco = new EnderecoPessoaCRUD(connection);
			Integer idEndereco = endereco.getIdEndereco();
			
			PessoaCRUD.insert(connection, idEndereco);
			
			JOptionPane.showMessageDialog(null, "PESSOA CRIADA COM SUCESSO", "Sucesso", 1);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO AO CRIAR PESSOA", "Erro", 0);
		} finally {
			connection.close();
		}
		
	}
}
