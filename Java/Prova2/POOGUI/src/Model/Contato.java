package Model;

public class Contato extends Pessoa {
	
	private String telefone;
	private Endereco endereco;
	
	public Contato(String telefone, String nome, Endereco endereco) {
		super(nome);
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}
	
	public String getNome() {
		return super.getNome();
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
}
