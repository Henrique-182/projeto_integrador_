package vacinacao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexao.Conexao;
import tabelas.CentroCRUD;
import tabelas.EnderecoCentroCRUD;
import tabelas.TelefoneCentroCRUD;

public class Centro {
	
	private static Connection connection;

	public static void consultasDisponiveis() throws SQLException {
		boolean continuaMenuConsultaCentro = true;

		while (continuaMenuConsultaCentro) {
			String menuConsultaPessoa = JOptionPane.showInputDialog(
					null, 
					"0- Voltar ao Menu Consultas\n" 
					+ "1- Todas os Centros\n" 
					+ "2- Por Estado\n\n"
					+ "Escolha: ",
					"Consultar Centros de Vacinação Por: ", 
					1
					);

			switch (menuConsultaPessoa) {
			case "0":
				continuaMenuConsultaCentro = false;
				break;
			case "1":
				consultarPaginado();
				break;
			case "2":
				consultarPorEstado();
				break;
			default:
				JOptionPane.showMessageDialog(null, "OPÇÃO DE CONSULTA POR CENTRO INVÁLIDA");
			}
		}
	}


	private static void consultarPaginado() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			CentroCRUD.selectPaginado(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE CENTRO", "Todos os Centros", 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR CENTROS", "Erro", 0);
		} finally {
			connection.close();
		}
	}
	
	private static void consultarPorEstado() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			CentroCRUD.selectPorEstado(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE CENTRO", "Todos os Centros", 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR CENTROS", "Erro", 0);
		} finally {
			connection.close();
		}
	}
	
	public static void cadastrar() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			Integer idEndereco = EnderecoCentroCRUD.createEGetIdEndereco(connection);
			
			Integer idCentro = CentroCRUD.insert(connection, idEndereco);

			TelefoneCentroCRUD.createTelefones(connection, idCentro);
			
			JOptionPane.showMessageDialog(null, "CENTRO CRIADO COM SUCESSO", "Sucesso", 1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO AO CRIAR CENTRO", "Erro", 0);
		} finally {
			connection.close();
		}
	} 
}
