package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class DeclaracaoVariavel implements RegrasSintaticas {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		if(VetorAnaliseLexica.get(LinhaAtual).vetorAnalise.get(0).token() == Token.TIPOVARIAVEL) {
			for(int i = 1; i < LinhaAnalisada.vetorAnalise.size(); i++) {
				if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.VARIAVEL && 
				   LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.VARIAVEL) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.VIRGULA && 
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.PONTOVIRGULA && 
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL && 
						  LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.VIRGULA) {
					System.out.println("DECLARACAOVARIAVEL -");
					System.out.println("Linha: " + LinhaAnalisada.linha + " ");
					LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
					System.out.println("");
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
}
