package Dto;

public class AlunoDto {
	
	private String nome, end, tel, cpf, sangue, fatorRH, curso, contEmerg, telEmerg, id;
	
	public AlunoDto(String nome, String end, String tel, String cpf, String sangue, String fatorRH, String curso, String contEmerg, String telEmerg) {
		this.nome = nome;
		this.end = end;
		this.tel = tel;
		this.cpf = cpf;
		this.sangue = sangue;
		this.fatorRH = fatorRH;
		this.curso = curso;
		this.contEmerg = contEmerg;
		this.telEmerg = telEmerg;
	}
	
	public AlunoDto(String nome, String end, String tel, String cpf, String sangue, String fatorRH, String curso, String contEmerg, String telEmerg, String id) {
		this.nome = nome;
		this.end = end;
		this.tel = tel;
		this.cpf = cpf;
		this.sangue = sangue;
		this.fatorRH = fatorRH;
		this.curso = curso;
		this.contEmerg = contEmerg;
		this.telEmerg = telEmerg;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public boolean validate() {
		if(this.nome.isBlank() || this.nome == null) {
			return false;
		} else if(this.end.isBlank() || this.end == null) {
			return false;
		} else if(this.tel.isBlank() || this.tel == null) {
			return false;
		} else if(this.cpf.isBlank() || this.cpf == null) {
			return false;
		} else if(this.sangue.isBlank() || this.sangue == null) {
			return false;
		} else if(this.fatorRH.isBlank() || this.fatorRH == null) {
			return false;
		} else if(this.curso.isBlank() || this.curso== null) {
			return false;
		} else if(this.contEmerg.isBlank() || this.contEmerg == null) {
			return false;
		} else if(this.telEmerg.isBlank() || this.telEmerg == null) {
			return false;
		}
		
		return true;
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

	public String getCpf() {
		return cpf;
	}

	public String getSangue() {
		return sangue;
	}

	public String getFatorRH() {
		return fatorRH;
	}

	public String getCurso() {
		return curso;
	}

	public String getContEmerg() {
		return contEmerg;
	}

	public String getTelEmerg() {
		return telEmerg;
	}
}
