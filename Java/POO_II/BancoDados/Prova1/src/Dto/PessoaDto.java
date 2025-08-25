package Dto;

public class PessoaDto {
	
	private String nome, peso, altura, id;
	
	public PessoaDto(String nome, String peso, String altura) {
		this.nome = nome;
		this.peso = peso;
		this.altura = altura;
	}
	
	public PessoaDto(String nome, String peso, String altura, String id) {
		this.nome = nome;
		this.peso = peso;
		this.altura = altura;
		this.id = id;
	}
	
	public PessoaDto(String peso, String altura) {
		this.peso = peso;
		this.altura = altura;
	}

	public String getNome() {
		return nome;
	}

	public String getPeso() {
		return peso;
	}

	public String getAltura() {
		return altura;
	}

	public String getId() {
		return id;
	}
	
	public boolean ValidateCalc() {
		if(peso.isBlank() || peso == null) {
			return false;
		} else if(altura.isBlank() || altura == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean Validate() {
		if(nome.isBlank() || nome == null) {
			return false;
		} else if(peso.isBlank() || peso == null) {
			return false;
		} else if(altura.isBlank() || altura == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean ValidateId() {
		if(!this.Validate() || id.isBlank() || id == null) {
			return false;
		} else {
			return true;
		}
	}
}
