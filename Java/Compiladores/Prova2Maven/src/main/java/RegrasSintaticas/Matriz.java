package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class Matriz implements RegrasSintaticas, SyntaxErrorException {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		int indiceAtual = objControle.getIndiceAtual();
		if(LinhaAnalisada.vetorAnalise.get(indiceAtual).token() == Token.TIPOVARIAVEL) {
			objControle.setIndiceAtual(indiceAtual+1);
			if(MatrizAuxiliar(LinhaAnalisada)) {
				if(LinhaAnalisada.vetorAnalise.get(objControle.getIndiceAtual()).token() == Token.PONTOVIRGULA) {
					System.out.println("ATRIBUICAOMATRIZ - ");
					System.out.println("Linha: " + LinhaAnalisada.linha + " ");
					LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
					System.out.println("");
					return true;
				} else {
					logError("declaracao Error: ; esperado no Final", LinhaAnalisada.linha);
				}
			}
		} else if(LinhaAnalisada.vetorAnalise.get(indiceAtual).token() == Token.ABRECOLCHETE) {
			objControle.setIndiceAtual(indiceAtual);
			return MatrizAuxiliar(LinhaAnalisada);
		} else {
			return false;
		}
		
		return false;
	}
	
	private boolean MatrizAuxiliar(ControleSintatico LinhaAnalisada) {
		int colchete = 0, dimensoes = 0;
		for(int i = objControle.getIndiceAtual(); i < LinhaAnalisada.vetorAnalise.size(); i++) {
			objControle.setIndiceAtual(i);
			if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.VARIAVEL &&
					LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.TIPOVARIAVEL) {
				continue;
			} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ABRECOLCHETE &&
					colchete == 0) {
				colchete++;
				continue;
			} else if((LinhaAnalisada.vetorAnalise.get(i).token() == Token.VARIAVEL ||
					LinhaAnalisada.vetorAnalise.get(i).token() == Token.NUMEROINTEIRO) &&
					LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.ABRECOLCHETE) {
				continue;
			} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.FECHACOLCHETE &&
					colchete == 1) {
				colchete--;
				dimensoes++;
				continue;
			} else if(LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.FECHACOLCHETE &&
					colchete == 0 && (dimensoes == 1 || dimensoes == 2)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
