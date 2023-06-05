package vacinacao.consultas;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import vacinacao.Centro;
import vacinacao.Vacina;

public class Consulta {

	public static void consultar() throws SQLException {
		boolean continuaMenuConsulta = true;
		
		while(continuaMenuConsulta) {
			String menuConsulta = JOptionPane.showInputDialog("0- Voltar ao Menu Principal\n"
					+ "1- Pessoa\n"
					+ "2- Centro de vacinação\n"
					+ "3- Vacinas disponíveis\n"
					+ "Menu: ");
			
			switch(menuConsulta) {
			case "0":
				continuaMenuConsulta = false;
				break;
			case "1":
				Pessoa.consultasDisponiveis();
				break;
			case "2":
				JOptionPane.showMessageDialog(null, "CONSULTAR CENTROS DE VACINAÇÃO");
				Centro.consultasDisponiveis();
				break;
			case "3":
				Vacina.consultarPaginado();
				break;
			default:
				JOptionPane.showMessageDialog(null, "MENU INVÁLIDO", "Inválido", 0);
			}
		}
	}
}
