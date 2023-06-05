package view;

public class View {

	public static void formataPessoaComEndereco(
			String cpf,
			String nome,
			String sobrenome,
			String telefone,
			String estado,
			String cidade,
			String bairro,
			String cep,
			String logradouro,
			String complemento
			) {
		
		System.out.println("CPF: " + cpf);
		System.out.println("Nome: " + nome + " " + sobrenome);
		System.out.println("Telefone: " + telefone);
		System.out.println("CEP: " + cep); 
		System.out.println("Bairro: " + bairro); 
		System.out.println("Logradouro: " + logradouro);
		System.out.println("Complemento: " + complemento);
		System.out.println("Cidade: " + cidade); 
		System.out.println("Estado: " + estado);
	}
}
