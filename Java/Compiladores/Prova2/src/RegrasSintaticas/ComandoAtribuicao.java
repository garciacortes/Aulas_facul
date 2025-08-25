package RegrasSintaticas;

import java.util.ArrayList;

import AnaliseLexica.ControleSintatico;
import AnaliseLexica.Token;

public class ComandoAtribuicao implements RegrasSintaticas{
	
	
	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		
		if((VetorAnaliseLexica.get(LinhaAtual).vetorAnalise.get(0).token() == Token.TIPOVARIAVEL) &&
		   (VetorAnaliseLexica.get(LinhaAtual).vetorAnalise.get(1).token() == Token.VARIAVEL)) {
			objControle.setIndiceAtual(2);
		} else if(VetorAnaliseLexica.get(LinhaAtual).vetorAnalise.get(0).token() == Token.VARIAVEL) {
			objControle.setIndiceAtual(1);
		} 
		
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		if(ComandoAuxAtribuicao(VetorAnaliseLexica, LinhaAnalisada)) {
			System.out.println("COMANDOATRIBUICAO - ");
			System.out.println("Linha: " + (LinhaAtual+1) + " ");
			LinhaAnalisada.vetorAnalise.forEach(c -> System.out.println(c.lexema()));
			return true;
		}
		return false;
	}
	
	boolean ComandoAuxAtribuicao(ArrayList<ControleSintatico> VetorAnaliseLexica, ControleSintatico LinhaAnalisada) {
		for(int i = objControle.getIndiceAtual(); i < LinhaAnalisada.vetorAnalise.size(); i++) {
			if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ATRIBUICAO &&
			   LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL) {
				continue;
			} else if((LinhaAnalisada.vetorAnalise.get(i).token() == Token.NUMEROFLOAT ||
					  LinhaAnalisada.vetorAnalise.get(i).token() == Token.NUMEROINTEIRO ||
					  LinhaAnalisada.vetorAnalise.get(i).token() == Token.VARIAVEL) &&
					  (LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.NUMEROFLOAT ||
					  LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.NUMEROINTEIRO ||
					  LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.VARIAVEL)) {
				continue;
			} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.OPERADORESMATEMATICO &&
					  LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.ATRIBUICAO) {
				continue;
			} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.PONTOVIRGULA &&
					  LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.ATRIBUICAO && 
					  LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.OPERADORESMATEMATICO) {
				objControle.setIndiceAtual(i);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
