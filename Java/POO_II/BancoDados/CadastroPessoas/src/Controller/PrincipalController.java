package Controller;

import Dto.PessoaDto;
import services.PrincipalServices;

public class PrincipalController {
	
	PrincipalServices pServices;
	
	public PrincipalController() {
		this.pServices = new PrincipalServices();
	}
	
	public String Cadastrar(PessoaDto pDto) {
		String msg = null;
		if(pDto.validate()) {
			msg = pServices.Cadastrar(pDto);
		} else {
			System.out.println("Atributos com informações/dados incorretas");
		}
		
		return msg;
	}
}
