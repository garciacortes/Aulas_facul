package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class OperacaoComparacao implements RegrasSintaticas {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		for(int i = objControle.getIndiceAtual(); i < LinhaAnalisada.vetorAnalise.size(); i++) {
			objControle.setIndiceAtual(i);
			if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.VARIAVEL &&
			   LinhaAnalisada.vetorAnalise.get(i-1).token() != Token.OPERADORESRELACIONAIS) {
				continue;
			} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.OPERADORESRELACIONAIS &&
					  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL) {
				continue;
			} else if((LinhaAnalisada.vetorAnalise.get(i).token() == Token.VARIAVEL ||
					  LinhaAnalisada.vetorAnalise.get(i).token() == Token.NUMEROFLOAT ||
					  LinhaAnalisada.vetorAnalise.get(i).token() == Token.NUMEROINTEIRO) && 
					  (LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.OPERADORESRELACIONAIS)) {
				objControle.setIndiceAtual(i);
				return true;
			} else if(new OperacoesMatematicas().verificar(VetorAnaliseLexica) &&
					  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.OPERADORESRELACIONAIS) {
				i = objControle.getIndiceAtual();
				objControle.setIndiceAtual(i);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
