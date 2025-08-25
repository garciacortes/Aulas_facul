package Services;

import java.sql.SQLException;
import java.util.ArrayList;

import Dto.AlunoDto;
import Entitys.Aluno;
import Repositories.AlunoRepository;

public class PrincipalServices {
	
	AlunoRepository alunoRepository;
	private int id;
	
	public PrincipalServices() {
		this.alunoRepository = new AlunoRepository();
	}
	
	public String Cadastrar(String nome, String end, String tel, String cpf, String tipoSangue, String curso, String contEmerg, String telEmerg) { 
		cpf = cpf.replaceAll("\\.", "").replaceAll("-", "").replaceAll("", "").trim();
		tel = tel.replaceAll("", "").replaceAll("-", "").trim();
		telEmerg = telEmerg.replaceAll("", "").replaceAll("-", "").trim();
		return alunoRepository.Cadastrar(new Aluno(nome, end, tel, cpf, tipoSangue, curso, contEmerg, telEmerg));
	}
	
	public String Cadastrar(AlunoDto alunoDto) {
		String tipoSangue = alunoDto.getSangue() + alunoDto.getFatorRH();
		return this.Cadastrar(alunoDto.getNome(), alunoDto.getEnd(), alunoDto.getTel(), alunoDto.getCpf(), tipoSangue, alunoDto.getCurso(), alunoDto.getContEmerg(), alunoDto.getTelEmerg());
	}
	
	public String Update(String nome, String end, String tel, String cpf, String tipoSangue, String curso, String contEmerg, String telEmerg, String id) { 
		cpf = cpf.replaceAll("\\.", "").replaceAll("-", "").replaceAll("", "").trim();
		tel = tel.replaceAll("", "").replaceAll("-", "").trim();
		telEmerg = telEmerg.replaceAll("", "").replaceAll("-", "").trim();
		this.id = Integer.parseInt(id);
		return alunoRepository.Update(new Aluno(nome, end, tel, cpf, tipoSangue, curso, contEmerg, telEmerg, this.id));
	}
	
	
	public String Update(AlunoDto alunoDto) {
		String tipoSangue = alunoDto.getSangue() + alunoDto.getFatorRH();
		return this.Update(alunoDto.getNome(), alunoDto.getEnd(), alunoDto.getTel(), alunoDto.getCpf(), tipoSangue, alunoDto.getCurso(), alunoDto.getContEmerg(), alunoDto.getTelEmerg(), alunoDto.getId());
	}
	
	public String delete(String id) {
		return alunoRepository.delete(Integer.parseInt(id));
	}
	
	public ArrayList<Aluno> search() throws SQLException {
		return alunoRepository.search();
		
	}
}