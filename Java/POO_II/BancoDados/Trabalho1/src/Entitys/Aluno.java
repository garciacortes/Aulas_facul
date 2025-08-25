package Entitys;

import java.util.ArrayList;

public class Aluno {
	
	private String nome, end, tel, cpf, tipoSangue, curso, contEmerg, telEmerg;
	ArrayList<String> dados = new ArrayList<String>();
	private int id;
	
	public Aluno() {
	}
	
	public Aluno(String nome, String end, String tel, String cpf, String tipoSangue, String curso, String contEmerg, String telEmerg) {
		this.nome = nome;
		this.end = end;
		this.tel = tel;
		this.cpf = cpf;
		this.tipoSangue = tipoSangue;
		this.curso = curso;
		this.contEmerg = contEmerg;
		this.telEmerg = telEmerg;
	}
	
	public Aluno(String nome, String end, String tel, String cpf, String tipoSangue, String curso, String contEmerg, String telEmerg, int id) {
		this.nome = nome;
		this.end = end;
		this.tel = tel;
		this.cpf = cpf;
		this.tipoSangue = tipoSangue;
		this.curso = curso;
		this.contEmerg = contEmerg;
		this.telEmerg = telEmerg;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public ArrayList<String> listDados() {
		dados.add(nome);
		dados.add(end);
		dados.add(tel);
		dados.add(cpf);
		dados.add(tipoSangue);
		dados.add(curso);
		dados.add(contEmerg);
		dados.add(telEmerg);
		
		return dados;
		
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

	public String getTipoSangue() {
		return tipoSangue;
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
