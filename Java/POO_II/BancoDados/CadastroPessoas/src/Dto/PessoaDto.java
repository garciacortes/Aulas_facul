package Dto;

public class PessoaDto {
	private String nome, end, tel;

	public PessoaDto(String nome, String end, String tel) {
		this.nome = nome;
		this.end = end;
		this.tel = tel;
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
	
	public boolean validate() {
		if(this.nome.isBlank() || this.nome == null) {
			return false;
		} else if(this.end.isBlank() || this.end == null) {
			return false;
		} else if(this.tel.isBlank() || this.tel == null) {
			return false;
		}
		
		return true;
	}
	
}
