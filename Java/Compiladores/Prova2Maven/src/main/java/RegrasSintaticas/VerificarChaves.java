package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class VerificarChaves implements RegrasSintaticas {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		if(LinhaAnalisada.vetorAnalise.size() == 1 && LinhaAnalisada.vetorAnalise.get(0).token() == Token.FECHACHAVE) {
			String ultimo = qtdeChaves.lastKey();
			System.out.println("FECHACHAVE - " + "ESTRUTURA -> " + ultimo + " Linha -> " + qtdeChaves.get(ultimo));
			System.out.println("Linha: " + LinhaAnalisada.linha + " ");
			qtdeChaves.remove(ultimo);
			LinhaAnalisada.vetorAnalise.forEach(c -> System.out.println(c.lexema()));
			return true;
		}
		return false;
	}
	
	
}
