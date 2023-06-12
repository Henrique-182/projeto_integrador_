package vacinacao;
import java.sql.SQLException;

import painel.TelefoneCentroPainel;

public class Teste {

	public static void main(String[] args) throws SQLException {
		
		String[][] telefones = TelefoneCentroPainel.novo();
		
		for(String[] telefone : telefones) {
			System.out.println("(" + telefone[0] + ")" + telefone[1]);
		}
	}

}
