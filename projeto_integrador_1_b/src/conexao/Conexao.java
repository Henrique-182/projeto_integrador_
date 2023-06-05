package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	public static Connection createConnection() {
		Connection conexao = null;
		String url = "jdbc:mysql://localhost:3306/projeto_integrador_1_b?verifyServerCertificate=false&useSSL=true";
		String user = "root";
		String password = "12345678";

		try {
			 conexao = DriverManager.getConnection(url, user, password);
			 System.out.println("Conexão realizada com sucesso");
		} catch(Exception e) {
			System.out.println("Conexão não obteve sucesso");
		}

	return conexao;
	
	}

}
