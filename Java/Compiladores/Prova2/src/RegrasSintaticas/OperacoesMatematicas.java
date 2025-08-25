package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class OperacoesMatematicas implements RegrasSintaticas {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		int indiceAtual = objControle.getIndiceAtual();
		int parenteses = 0;
		if(LinhaAnalisada.vetorAnalise.get(indiceAtual).token() == Token.ABREPARENTESE) {
			parenteses++;
			indiceAtual++;
		}
		if(LinhaAnalisada.vetorAnalise.get(indiceAtual).token() == Token.VARIAVEL) {
			for(int i = indiceAtual+1; i < LinhaAnalisada.vetorAnalise.size(); i++) {
				if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.OPERADORESMATEMATICO &&
						LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.VARIAVEL && 
						LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.OPERADORESMATEMATICO) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() != Token.VARIAVEL && 
						LinhaAnalisada.vetorAnalise.get(i).token() != Token.OPERADORESMATEMATICO &&
						LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL &&
						parenteses == 0) {
					objControle.setIndiceAtual(i);
					return true;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.FECHAPARENTESE &&
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL) {
					objControle.setIndiceAtual(indiceAtual);
					return true;
				}
			}
		}
		return false;
	}
	
}
