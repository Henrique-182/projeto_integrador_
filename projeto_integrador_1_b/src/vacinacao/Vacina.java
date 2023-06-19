package vacinacao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexao.Conexao;
import tabelas.VacinaCRUD;

public class Vacina {

	private static Connection connection;
	
	public static void consultasDisponiveis() throws SQLException {
		boolean continuaMenuConsultaVacina = true;

		while (continuaMenuConsultaVacina) {
			String menuConsultaVacina = JOptionPane.showInputDialog(
					null, 
					"0- Voltar ao Menu Consultas\n" 
					+ "1- Todas as Vacinas\n" 
					+ "2- Por Doença\n\n"
					+ "Escolha: ",
					"Consultar Vacinas Por: ", 
					1
					);

			switch (menuConsultaVacina) {
			case "0":
				continuaMenuConsultaVacina = false;
				break;
			case "1":
				consultarPaginado();
				break;
			case "2":
				consultarPorDoenca();
				break;
			default:
				JOptionPane.showMessageDialog(null, "OPÇÃO DE CONSULTA POR VACINA INVÁLIDA");
			}
		}
	}
	
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
	
	public static void consultarPorDoenca() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			VacinaCRUD.selectPorDoenca(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE VACINA", "Vacinas por Doença", 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR VACINAS", "Erro", 0);
		} finally {
			connection.close();
		}
	}
	
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
