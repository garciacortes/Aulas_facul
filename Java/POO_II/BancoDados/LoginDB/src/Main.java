import view.Cadastro;
import view.Login;

public class Main {

	public static void main(String[] args) {
		
		Cadastro cadastro = new Cadastro();
		Login frame = new Login(cadastro);
		
		cadastro.setPnLogin(frame.getPnLogin());
	}

}