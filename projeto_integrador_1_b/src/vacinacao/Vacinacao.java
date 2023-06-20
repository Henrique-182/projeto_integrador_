package vacinacao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexao.Conexao;
import tabelas.VacinacaoCRUD;

public class Vacinacao {
	
	private static Connection connection;

	public static void vacinar() throws SQLException {
		boolean continuaMenuVacinar = true;
		
		while(continuaMenuVacinar) {
			String menuVacinar = JOptionPane.showInputDialog(null, 
					"0- Voltar ao Menu Principal\n"
					+ "1- Consultar Agendamentos\n"
					+ "2- Consultar Vacinação\n"
					+ "3- Consultar Esquema Vacinal\n"
					+ "4- Agendar Vacinação \n\n"
					+ "Escolha:", 
					"Menu Vacinação", 
					1
					);
		
			switch(menuVacinar) {
			case "0":
				continuaMenuVacinar = false;
				break;
			case "1":
				agendamentos();
				break;
			case "2":
				realizadas();
				break;
			case "3":
				esquemaVacinal();
				break;
			case "4":
				agendar();
				break;
			default:
				JOptionPane.showMessageDialog(null, "MENU INVÁLIDO", "Inválido", 0);
				break;
			}
		}
	}

	private static void agendamentos() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			VacinacaoCRUD.selectAgendamentos(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE AGENDAMENTO", "Todos os Agendamentos", 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR AGENDAMENTOS", "Erro", 0);
		} finally {
			connection.close();
		}
	}

	private static void realizadas() throws SQLException  {
		try {
			connection = Conexao.createConnection();
			
			VacinacaoCRUD.selectVacinados(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE VACINAÇÃO", "Todas as Vacinações", 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR VACINAÇÃO", "Erro", 0);
		} finally {
			connection.close();
		}
	}

	private static void esquemaVacinal() throws SQLException  {
		try {
			connection = Conexao.createConnection();
			
			VacinacaoCRUD.selectTopVacinas(connection);
			VacinacaoCRUD.selectVacinacaoPorEstado(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE ESQUEMA VACINAL", "Esquema Vacinal", 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR ESQUEMA VACINAL", "Erro", 0);
		} finally {
			connection.close();
		}
	}
	
	private static void agendar() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			Pessoa.consultarPorCPF();
			Centro.consultaPorEstado();
			Vacina.consultarPorDoenca();
			
			VacinacaoCRUD.insert(connection);
			
			JOptionPane.showMessageDialog(null, "VACINAÇÃO CRIADA COM SUCESSO", "Sucesso", 1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO AO CRIAR VACINAÇÃO", "Erro", 0);
		} finally {
			connection.close();
		}
	}
}
