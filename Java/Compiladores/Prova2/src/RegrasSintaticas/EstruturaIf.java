package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class EstruturaIf implements RegrasSintaticas {
	
	
	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		int parenteses = 0;
		int comparacao = 0;
		int logico = 0;
		if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.ESTRUTURAIF) {
			for(int i = 1; i < LinhaAnalisada.vetorAnalise.size(); i++) {
				objControle.setIndiceAtual(i);
				if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ABREPARENTESE) {
					parenteses++;
					continue;
				} else if(new OperacaoComparacao().verificar(VetorAnaliseLexica) &&
						  parenteses >= 1) {
					comparacao++;
					i = objControle.getIndiceAtual();
					if(LinhaAnalisada.vetorAnalise.get(i+1).token() == Token.FECHAPARENTESE &&
					   parenteses > 1) {
						parenteses--;
						logico = logico == 1 ? (logico--) : logico;
						continue;
					} else if(parenteses > 1) {
						return false;
					}
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.OPERADORESLOGICOS &&
						  comparacao == 1) {
					logico++;
					comparacao--;
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.FECHAPARENTESE &&
						 --parenteses == 0 && comparacao == 1) {
					System.out.println("ESTRUTURAIF -");
					System.out.println("Linha: " + LinhaAnalisada.linha + " ");
					LinhaAnalisada.vetorAnalise.forEach(c -> System.out.println(c.lexema()));
					return true;
					
				}
			}
		}
		return false;
	}

}
