package RegrasSintaticas;

import java.util.ArrayList;

import AnaliseLexica.ControleSintatico;
import AnaliseLexica.Token;

public class Incremento implements RegrasSintaticas{

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		
		for(int i = objControle.getIndiceAtual(); i < LinhaAnalisada.vetorAnalise.size(); i++) {
			if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.VARIAVEL) {
				continue;
			} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.INCREMENTO &&
					  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL) {
				objControle.setIndiceAtual(i);
				return true;
			}
		}
		return false;
	}

}
