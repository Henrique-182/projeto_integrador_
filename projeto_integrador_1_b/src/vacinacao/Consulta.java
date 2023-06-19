package vacinacao;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Consulta {

	public static void consultar() throws SQLException {
		boolean continuaMenuConsulta = true;
		
		while(continuaMenuConsulta) {
			String menuConsulta = JOptionPane.showInputDialog(
					null,
					"0- Voltar ao Menu Principal\n"
					+ "1- Pessoa\n"
					+ "2- Centro de vacinação\n"
					+ "3- Vacinas disponíveis\n\n"
					+ "Menu: ",
					"Consultas", 
					3
					);
			
			switch(menuConsulta) {
			case "0":
				continuaMenuConsulta = false;
				break;
			case "1":
				Pessoa.consultasDisponiveis();
				break;
			case "2":
				Centro.consultasDisponiveis();
				break;
			case "3":
				Vacina.consultasDisponiveis();
				break;
			default:
				JOptionPane.showMessageDialog(null, "MENU INVÁLIDO", "Inválido", 0);
			}
		}
	}
}
