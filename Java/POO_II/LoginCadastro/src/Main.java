import View.Cadastro;
import View.Dados;
import View.Principal;
import View.Usuario;

public class Main {

	public static void main(String[] args) {
		
		Dados dados = new Dados();
		Usuario user = new Usuario(dados);
		Cadastro cadastro = new Cadastro(dados);
		Principal frame = new Principal(cadastro, dados, user);
		
		cadastro.setPnLogin(frame.getPnLogin());
	}

}
