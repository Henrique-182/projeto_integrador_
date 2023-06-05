package vacinacao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexao.Conexao;
import tabelas.VacinaCRUD;

public class Vacina {

	private static Connection connection;
	
	public static void consultarPaginado() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			VacinaCRUD.selectPaginado(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE VACINA", "Todas as Vacinas", 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR VACINAS", "Erro", 0);
		} finally {
			connection.close();
		}
	}
}
