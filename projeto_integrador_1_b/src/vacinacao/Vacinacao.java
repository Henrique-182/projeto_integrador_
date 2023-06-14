package vacinacao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Vacinacao {

	public static void main(String[] args) throws SQLException {
		
		JOptionPane.showMessageDialog(null, "PROJETO INTEGRADOR 1-B", "Vacinação", 1);
		
		boolean continuaMenuPrincipal = true;
		
		while(continuaMenuPrincipal ) {
			String menuPrincipal = JOptionPane.showInputDialog(
					null, 
					"0- Sair\n"
					+ "1- Consultar\n"
					+ "2- Cadastrar\n"
					+ "3- Agendar Vacinação\n\n"
					+ "Digite: ", 
					"Menu Principal", 
					3
					);
			
			switch(menuPrincipal) {
			case "0":
				JOptionPane.showMessageDialog(null, "SAINDO");
				continuaMenuPrincipal = false;
				break;
			case "1":
				Consulta.consultar();
				break;
			case "2":
				Cadastro.cadastrar();
				break;
			case "3":
				Agenda.agendar();
				break;
			default:
				JOptionPane.showMessageDialog(null, "MENU INVÁLIDO", "Inválido", 0);
			}
		}
		
	}

}
