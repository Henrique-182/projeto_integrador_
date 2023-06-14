package API;


public class Endereco {
	
    String logradouro;
    String bairro;
    String localidade;
    String complemento;
    String uf;
    boolean erro;

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }
    
    public boolean getErro() {
    	return this.erro;
    }
    
    public String getUf() {
    	return uf;
    }
    
    public String getComplemento() {
   return complemento; 	
   
    }

    @Override
    public String toString() {
        return "Endereco{" + "logradouro=" + logradouro + ", bairro=" + bairro + ", localidade=" + localidade + '}';
    }
}