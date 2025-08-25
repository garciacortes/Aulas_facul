package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dto.AlunoDto;
import Entitys.Aluno;
import Services.PrincipalServices;

public class PrincipalController {
	
	private PrincipalServices pServices;
	
	public PrincipalController() {
		this.pServices = new PrincipalServices();
	}
	
	public String Cadastrar(AlunoDto alunoDto) {
		String msg = null;
		if(alunoDto.validate()) {
			return pServices.Cadastrar(alunoDto);
		} else {
			msg = "Erro ao Cadastrar informações, entre em contato!";
			System.out.println("Atributos com informações/dados incorretas");
		}
		
		return msg;
	}
	
	public String Update(AlunoDto alunoDto) {
		String msg = null;
		if(alunoDto.validate()) {
			return pServices.Update(alunoDto);
		} else {
			msg = "Erro ao Cadastrar informações, entre em contato!";
			System.out.println("Atributos com informações/dados incorretas");
		}
		
		return msg;
	}
	
	public String delete(String id) {
		return pServices.delete(id);
	}
	
	public ArrayList<Aluno> search() throws SQLException {
		return pServices.search();
	}
}
