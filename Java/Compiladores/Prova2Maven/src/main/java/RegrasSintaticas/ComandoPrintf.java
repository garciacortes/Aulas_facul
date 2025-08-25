package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class ComandoPrintf implements RegrasSintaticas, SyntaxErrorException {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		int indiceAtualAnterior = objControle.getIndiceAtual();
		int indiceAtual = objControle.getIndiceAtual() != 0 ? objControle.getIndiceAtual() : 0;
		int parenteses = 0, colchete = 0;
		if(LinhaAnalisada.vetorAnalise.get(indiceAtual).token() == Token.IMPRIMIR) {
			if(LinhaAnalisada.vetorAnalise.get(indiceAtual+1).token() == Token.ABREPARENTESE) {
				parenteses++;
				for(int i = indiceAtual+2; i < LinhaAnalisada.vetorAnalise.size(); i++) {
					objControle.setIndiceAtual(i);
					if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.STRING &&
							LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.ABREPARENTESE) {
						continue;
					} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.STRINGFORMATACAO &&
							LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.ABREPARENTESE) {
						continue;
					} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.VIRGULA &&
							LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.STRINGFORMATACAO) {
						continue;
					}else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.VARIAVEL &&
							LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VIRGULA) {
						continue; 
					} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ABRECOLCHETE &&
							  new Matriz().verificar(VetorAnaliseLexica)) {
						i = objControle.getIndiceAtual()-1;
						colchete++;
						continue;
					} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.VIRGULA &&
							LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL) {
						continue;
					}
					else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.FECHAPARENTESE &&
							--parenteses == 0 &&
							(LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.STRING ||
							LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL ||
							(LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.FECHACOLCHETE &&
							colchete == 1))) {
						colchete--;
						continue;
					} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.PONTOVIRGULA &&
							  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.FECHAPARENTESE) {
						System.out.println("COMANDOPRINTF - ");
						System.out.println("Linha: " + LinhaAnalisada.linha + " ");
						LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
						System.out.println("");
						return true;
					} else if(LinhaAnalisada.vetorAnalise.get(i).token() != Token.FECHAPARENTESE &&
							parenteses == 1 &&
							(LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.STRING ||
							LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL)) {
						logError("printf Error: ) esperado no Final", LinhaAnalisada.linha);
						return true;
					} else if(LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.FECHAPARENTESE &&
                              LinhaAnalisada.vetorAnalise.get(i).token() != Token.PONTOVIRGULA) {
						logError("printf Error: ; esperado no Final", LinhaAnalisada.linha);
						return true;
					} else {
						return false;
					}
				}
			} else {
				logError("printf Error: ( esperado no Inico", LinhaAnalisada.linha);
				return true;
			}
		}
		return false;
	}
}
