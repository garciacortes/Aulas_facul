package Entitys;

public class Pessoa {
	
	private String nome, end, tel;
	
	public Pessoa(String nome, String end, String tel) {
		this.nome = nome;
		this.end = end;
		this.tel = tel;
	}
	
	public Pessoa(Pessoa pessoa) {
		this.nome = getNome();
		this.end = getEnd();
		this.tel = getTel();
	}

	public String getNome() {
		return nome;
	}

	public String getEnd() {
		return end;
	}

	public String getTel() {
		return tel;
	}
}
