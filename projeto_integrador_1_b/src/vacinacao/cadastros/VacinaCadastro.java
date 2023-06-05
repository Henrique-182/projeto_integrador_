package vacinacao.cadastros;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexao.Conexao;
import tabelas.VacinaCRUD;

public class VacinaCadastro {
	
	private static Connection connection;

	public static void cadastrar() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			VacinaCRUD.insert(connection);
			
			JOptionPane.showMessageDialog(null, "VACINA CRIADA COM SUCESSO", "Sucesso", 1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO AO CRIAR VACINA", "Erro", 0);
		} finally {
			connection.close();
		}
	}
}
