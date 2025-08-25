package services;

import Dto.PessoaDto;
import Entitys.Pessoa;
import repositories.PessoaRepository;

public class PrincipalServices {
	
	PessoaRepository pRepository;
	
	public PrincipalServices() {
		this.pRepository = new PessoaRepository();
	}
	
	public String Cadastrar(String nome, String end, String tel) {
		return pRepository.Cadastrar(new Pessoa(nome, end, tel));
	}
	
	public String Cadastrar(PessoaDto pDto) {
		return this.Cadastrar(pDto.getNome(), pDto.getEnd(), pDto.getTel());
	}
}
