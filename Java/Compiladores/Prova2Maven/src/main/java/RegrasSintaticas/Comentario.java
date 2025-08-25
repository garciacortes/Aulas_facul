package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class Comentario implements RegrasSintaticas {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.COMENTARIO) {
			System.out.println("COMENTARIO - ");
			System.out.println("Linha: " + LinhaAnalisada.linha + " ");
			LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
			return true;
		} else {
			return false;
		}
	}

}
