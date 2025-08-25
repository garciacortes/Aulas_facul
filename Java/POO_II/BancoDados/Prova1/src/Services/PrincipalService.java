package Services;

import java.util.ArrayList;

import Dto.PessoaDto;
import Entitys.Pessoa;
import Repositories.PessoaRepository;

public class PrincipalService {
	
	private double peso, altura;
	private int id;
	PessoaRepository pRepository;
	
	public PrincipalService() {
		this.pRepository = new PessoaRepository();
	}
	
	public String Cadastrar(String nome, String peso, String altura) {
		Converter(peso, altura);
		double imc = CalcularImc();
		return pRepository.Cadastrar(new Pessoa(nome, this.peso, this.altura, imc));
	}
	
	public String Cadastrar(PessoaDto pDto) {
		return this.Cadastrar(pDto.getNome(), pDto.getPeso(), pDto.getAltura());
	}
	
	public String Alterar(String id, String nome, String peso, String altura) {
		Converter(peso, altura);
		double imc = CalcularImc();
		this.id = Integer.parseInt(id);
		return pRepository.Alterar(new Pessoa(nome, this.peso, this.altura, imc, this.id));
	}
	
	public String Alterar(PessoaDto pDto) {
		return this.Alterar(pDto.getId(), pDto.getNome(), pDto.getPeso(), pDto.getAltura());
	}
	
	public String Calcular(String peso, String altura) {
		Converter(peso, altura);
		double imc = CalcularImc();
		if(imc < 18.5) {
			return "Voce esta abaixo do peso ideal";
		} else if(imc >= 18.5 && imc < 25) {
			return "Peso Ideal";
		} else {
			return "Voce esta acima do peso ideal";
		}
	}
	
	public String Calcular(PessoaDto pDto) {
		return this.Calcular(pDto.getPeso(), pDto.getAltura());
	}
	
	public String Remover(String id) {
		return pRepository.Remover(Integer.parseInt(id));
	}
	
	public ArrayList<Pessoa> Listar() {
		return pRepository.Listar();
	}
	
	public void Converter(String peso, String altura) {
		 this.peso = Double.valueOf(peso).doubleValue();
		 this.altura = Double.valueOf(altura).doubleValue();
	}
	
	public double CalcularImc() {
		return (double) ((long) ((this.peso/(this.altura * this.altura)) * Math.pow(10, 2))) / Math.pow(10, 2);
				
	}
}
