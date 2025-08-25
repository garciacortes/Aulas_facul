package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class ComandoAtribuicao implements RegrasSintaticas{
	
	
	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		
		if((VetorAnaliseLexica.get(LinhaAtual).vetorAnalise.get(0).token() == Token.TIPOVARIAVEL) &&
		   (VetorAnaliseLexica.get(LinhaAtual).vetorAnalise.get(1).token() == Token.VARIAVEL &&
		   (VetorAnaliseLexica.get(LinhaAtual).vetorAnalise.size() > 2 &&
		    VetorAnaliseLexica.get(LinhaAtual).vetorAnalise.get(2).token() != Token.ABRECOLCHETE))) {
			objControle.setIndiceAtual(2);
		} else if(VetorAnaliseLexica.get(LinhaAtual).vetorAnalise.get(0).token() == Token.VARIAVEL) {
			objControle.setIndiceAtual(1);
		} 
		
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		if(ComandoAuxAtribuicao(VetorAnaliseLexica, LinhaAnalisada)) {
			System.out.println("COMANDOATRIBUICAO - ");
			System.out.println("Linha: " + LinhaAnalisada.linha + " ");
			LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
			System.out.println("");
			return true;
		}
		return false;
	}
	
	boolean ComandoAuxAtribuicao(ArrayList<ControleSintatico> VetorAnaliseLexica, ControleSintatico LinhaAnalisada) {
		int parenteses = 0;
		for(int i = objControle.getIndiceAtual(); i < LinhaAnalisada.vetorAnalise.size(); i++) {
			if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ATRIBUICAO &&
			   LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL) {
				continue;
			} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ABREPARENTESE) {
				parenteses++;
				continue;
			} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.FECHAPARENTESE &&
					parenteses >= 1) {
				parenteses--;
				continue;
			}
			else if((LinhaAnalisada.vetorAnalise.get(i).token() == Token.NUMEROFLOAT ||
					  LinhaAnalisada.vetorAnalise.get(i).token() == Token.NUMEROINTEIRO ||
					  LinhaAnalisada.vetorAnalise.get(i).token() == Token.VARIAVEL) &&
					  (LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.NUMEROFLOAT &&
					  LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.NUMEROINTEIRO &&
					  LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.VARIAVEL)) {
				continue;
			} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.OPERADORESMATEMATICO &&
					  LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.ATRIBUICAO) {
				continue;
			} else if(parenteses == 0 &&
					  LinhaAnalisada.vetorAnalise.get(i).token() == Token.PONTOVIRGULA &&
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
