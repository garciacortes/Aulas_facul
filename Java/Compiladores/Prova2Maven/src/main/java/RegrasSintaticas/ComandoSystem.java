package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class ComandoSystem implements RegrasSintaticas {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		if(LinhaAnalisada.vetorAnalise.get(objControle.getIndiceAtual()).token() == Token.SYSTEM) {
			for(int i = objControle.getIndiceAtual()+1; i < LinhaAnalisada.vetorAnalise.size(); i++) {
				objControle.setIndiceAtual(i);
				if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ABREPARENTESE &&
						 LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.SYSTEM) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.STRING &&
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.ABREPARENTESE) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.FECHAPARENTESE &&
						LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.STRING) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.PONTOVIRGULA &&
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.FECHAPARENTESE) {
					System.out.println("COMANDOSYSTEM - ");
					System.out.println("Linha: " + LinhaAnalisada.linha + " ");
					LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
					System.out.println("");
					return true;
				}
			}
		}
		return false;
	}

}
