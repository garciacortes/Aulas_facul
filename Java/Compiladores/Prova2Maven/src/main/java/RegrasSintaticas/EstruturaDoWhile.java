package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class EstruturaDoWhile implements RegrasSintaticas, SyntaxErrorException {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		int parenteses = 0;
		int comparacao = 0;
		int logico = 0;
		if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.PALAVRARESERVADADO) {
			if(LinhaAnalisada.vetorAnalise.size() > 1 && LinhaAnalisada.vetorAnalise.get(1).token() == Token.ABRECHAVE) {
				qtdeChaves.put("DO", LinhaAnalisada.linha);
				System.out.println("ESTRUTURADOWHILEINICIAL - ");
				System.out.println("Linha: " + LinhaAnalisada.linha + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
				System.out.println("");
				return true;
			} else if(VetorAnaliseLexica.get(LinhaAtual+1).vetorAnalise.get(0).token() == Token.ABRECHAVE) {
				System.out.println("ESTRUTURADOWHILEINICIAL - ");
				System.out.println("Linhas: " + LinhaAnalisada.linha + "|" + (LinhaAnalisada.linha+1) + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.println(c.lexema()));
				VetorAnaliseLexica.get(LinhaAtual+1).vetorAnalise.forEach(c -> System.out.print(c.lexema()));
				System.out.println("");
				qtdeChaves.put("DO", LinhaAnalisada.linha);
				objControle.setLinhaAtual(LinhaAtual+1);
				return true;
			}
		} else if((!qtdeChaves.isEmpty() &&
				  qtdeChaves.containsKey("DO")) &&
				  (LinhaAnalisada.vetorAnalise.size() > 1 &&
				   LinhaAnalisada.vetorAnalise.get(1).token() == Token.ESTRUTURAWHILE)) {
			if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.FECHACHAVE) {
				qtdeChaves.remove(qtdeChaves.lastKey());
				for(int i = 2; i < LinhaAnalisada.vetorAnalise.size(); i++) {
					objControle.setIndiceAtual(i);
					if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ABREPARENTESE) {
						parenteses++;
						continue;
					} else if(new OperacaoComparacao().verificar(VetorAnaliseLexica) &&
							  parenteses >= 1) {
						comparacao++;
						i = objControle.getIndiceAtual();
						logico = logico == 1 ? (--logico) : logico;
						if(LinhaAnalisada.vetorAnalise.get(i+1).token() == Token.FECHAPARENTESE &&
						   parenteses > 1) {
							parenteses--;
							i++;
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
						i++;
						if(LinhaAnalisada.vetorAnalise.size() != i  && LinhaAnalisada.vetorAnalise.get(i).token() == Token.PONTOVIRGULA) {
							objControle.setIndiceAtual(i);
							System.out.println("ESTRUTURADOWHILEFINAL - ");
							System.out.println("Linha: " + LinhaAnalisada.linha + " ");
							LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
							System.out.println("");
							return true;
						} else {
							logError("While Error: ; esperado no Final", LinhaAnalisada.linha);
							return true;
						}
						
					} else if(LinhaAnalisada.vetorAnalise.get(i).token() != Token.FECHAPARENTESE &&
							 parenteses == 1 && comparacao == 1) {
						logError("While Error: ) esperado no Final", LinhaAnalisada.linha);
						return true;
					} else {
						return false;
					}
				}
			} else {
				logError("While Error: ( esperado no Inico", LinhaAnalisada.linha);
				return true;
			}
		}
		return false;
	}

}
