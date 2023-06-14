package vacinacao;
import java.sql.SQLException;

import painel.EnderecoPainel;

public class Teste {

	public static void main(String[] args) throws SQLException {
		
		String[] endereco = EnderecoPainel.novo();
		
		for(String x : endereco) {
			System.out.println(x);
		}
	}

}
