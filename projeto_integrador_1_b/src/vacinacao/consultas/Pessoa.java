package vacinacao.consultas;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexao.Conexao;
import tabelas.EnderecoPessoaCRUD;
import tabelas.PessoaCRUD;

public class Pessoa {
	
	private static Connection connection;

	public static void consultasDisponiveis() throws SQLException {
		boolean continuaMenuConsultaPessoa = true;
		
		while(continuaMenuConsultaPessoa) {
			String  menuConsultaPessoa = JOptionPane.showInputDialog(
					null, 
					"0- Voltar ao Menu Consultas\n"
					+ "1- Todas as Pessoas\n"
					+ "2- Por Estado\n"
					+ "3- Por Idade\n"
					+ "4- Por Nome\n"
					+ "5- Por CPF\n\n"
					+ "Escolha: ",
					"Consultar Pessoa Por: ",
					1
					);
			
			switch(menuConsultaPessoa) {
			case "0":
				JOptionPane.showMessageDialog(null, "VOLTANDO AO MENU CONSULTAS");
				connection.close();
				continuaMenuConsultaPessoa = false;
				break;
			case "1":
				consultarPaginado();
				break;
			case "2":
				consultarPorEstado();
				break;
			case "3":
				consultarPorIdade();
				break;
			case "4":
				consultarPorNome();
				break;
			case "5":
				consultarPorCPF();
				break;
			default:
				JOptionPane.showMessageDialog(null, "OPÇÃO DE CONSULTA POR PESSOA INVÁLIDA");
			}
		}
	}

	public static void consultarPaginado() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			PessoaCRUD.selectPaginado(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE PESSOA", "Todas as Pessoas", 2);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR PESSOAS", "Erro", 0);
		} finally {
			connection.close();
		}
	}
	
	public static void consultarPorEstado() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			PessoaCRUD.selectPorEstado(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE PESSOA", "Pessoas por Estado", 2);
		} catch (Exception e) {
			
		} finally {
			connection.close();
		}
	}
	
	public static void consultarPorIdade() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			PessoaCRUD.selectPorIdade(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE PESSOA", "Pessoas por Estado", 2);
		} catch (Exception e) {
			
		} finally {
			connection.close();
		}
	}
	public static void consultarPorNome() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			PessoaCRUD.selectPorNome(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE PESSOA", "Pessoas por Estado", 2);
		} catch (Exception e) {
			
		} finally {
			connection.close();
		}
	}
	public static void consultarPorCPF() throws SQLException {
		try {
			connection = Conexao.createConnection();
			
			PessoaCRUD.selectPorCPF(connection);
			
			JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS NENHUM REGISTRO DE PESSOA", "Pessoas por Estado", 2);
		} catch (Exception e) {
			
		} finally {
			connection.close();
		}
	}
	
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
