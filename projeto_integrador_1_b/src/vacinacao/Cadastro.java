package vacinacao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Cadastro {

	public static void cadastrar() throws SQLException {
		boolean continuaMenuCadastro = true;
		
		while(continuaMenuCadastro) {
			String menuCadastro = JOptionPane.showInputDialog(null, 
					"0- Voltar ao Menu Principal\n"
					+ "1- Pessoa\n"
					+ "2- Centro de Vacinação\n"
					+ "3- Vacina\n\n"
					+ "Escolha:", 
					"Menu Cadastro", 
					1
					);
		
			switch(menuCadastro) {
			case "0":
				continuaMenuCadastro = false;
				break;
			case "1":
				Pessoa.cadastrar();
				break;
			case "2":
				Centro.cadastrar();
				break;
			case "3":
				Vacina.cadastrar();
				break;
			default:
				JOptionPane.showMessageDialog(null, "MENU INVÁLIDO", "Inválido", 0);
				break;
			}
		}
	}
}

