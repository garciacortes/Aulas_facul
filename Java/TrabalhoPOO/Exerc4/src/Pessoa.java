
public class Pessoa {
	
	private String nome; 
	private long cpf; 
	//private static int contador;
	
	public Pessoa(String nome, long cpf) {
		this.nome = nome;
		this.cpf = cpf;
		//contador = 0;
	}
	
	public String getNome() {
		return nome;
	}
	
	public long getCpf() {
		return cpf;
	}
}
