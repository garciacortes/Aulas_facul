package Controller;

import java.util.ArrayList;

import Dto.PessoaDto;
import Entitys.Pessoa;
import Services.PrincipalService;

public class PrincipalController {
	
	PrincipalService pService;
	
	public PrincipalController() {
		this.pService = new PrincipalService();
	}
	
	public String Cadastrar(PessoaDto pDto) {
		if(pDto.Validate()) {
			return pService.Cadastrar(pDto);
		} else {
			System.out.println("Atributos com informações/dados incorretas");
			return "Erro ao Cadastrar informações, entre em contato!";
		}
	}
	
	public String ALterar(PessoaDto pDto) {
		if(pDto.ValidateId()) {
			return pService.Alterar(pDto);
		} else {
			System.out.println("Atributos com informações/dados incorretas");
			return "Erro ao Cadastrar informações, entre em contato!";
		}
	}
	
	public String Calcular(PessoaDto pDto) {
		if(pDto.ValidateCalc()) {
			return pService.Calcular(pDto);
		} else {
			System.out.println("Atributos com informações/dados incorretas");
			return "Erro ao Cadastrar informações, entre em contato!";
		}
	}
	
	public String Remover(String id) {
		return pService.Remover(id);
	}
	
	public ArrayList<Pessoa> Listar() {
		return pService.Listar();
	}
}
