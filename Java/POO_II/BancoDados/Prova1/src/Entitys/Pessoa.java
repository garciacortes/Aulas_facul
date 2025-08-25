package Entitys;

import Dto.PessoaDto;

public class Pessoa {
	
	private String nome;
	private double peso, altura, imc;
	private int id;
	
	public Pessoa(String nome, double peso, double altura, double imc) {
		this.nome = nome;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
	}
	
	public Pessoa(String nome, double peso, double altura, double imc, int id) {
		this.id = id;
		this.nome = nome;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getPeso() {
		return peso;
	}

	public double getAltura() {
		return altura;
	}

	public double getImc() {
		return imc;
	}
}
