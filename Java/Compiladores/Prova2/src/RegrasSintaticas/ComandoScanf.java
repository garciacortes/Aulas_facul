package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class ComandoScanf implements RegrasSintaticas {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.LEITURADADOS) {
			for(int i = 1; i < LinhaAnalisada.vetorAnalise.size(); i++) {
				if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ABREPARENTESE) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ESPECIFICADORFORMATACAO &&
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.ABREPARENTESE) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.VIRGULA &&
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.ESPECIFICADORFORMATACAO) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ENDERECOMEMORIA &&
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VIRGULA) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.VARIAVEL &&
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.ENDERECOMEMORIA) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.FECHAPARENTESE &&
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.PONTOVIRGULA &&
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.FECHAPARENTESE) {
					System.out.println("COMANDOSCANF - ");
					System.out.println("Linha: " + LinhaAnalisada.linha + " ");
					LinhaAnalisada.vetorAnalise.forEach(c -> System.out.println(c.lexema()));
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

}
