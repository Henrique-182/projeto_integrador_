package vacinacao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Agenda {

	public static void agendar() throws SQLException {
		boolean continuaMenuAgendar = true;
		
		while(continuaMenuAgendar) {
			String menuAgendar = JOptionPane.showInputDialog(null, 
					"0- Voltar ao Menu Principal\n"
					+ "1- Pessoa\n"
					+ "2- Centro de Vacinação\n"
					+ "3- Vacina\n\n"
					+ "Escolha:", 
					"Menu Cadastro", 
					1
					);
		
			switch(menuAgendar) {
			case "0":
				continuaMenuAgendar = false;
				break;
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			default:
				JOptionPane.showMessageDialog(null, "MENU INVÁLIDO", "Inválido", 0);
				break;
			}
		}
	}
}
