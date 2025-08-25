package RegrasSintaticas;

import java.util.ArrayList;

import AnaliseLexica.ClassificacaoLexica;
import AnaliseLexica.ControleSintatico;
import AnaliseLexica.Token;

public class EstruturaFor implements RegrasSintaticas {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		
		//for (i=0; i<10; i++)
		
		if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.ESTRUTURAREPETICAO) {
			if(!posAberturaParenteses(LinhaAnalisada, VetorAnaliseLexica)) {
				return false;
			}
			
			int i = objControle.getIndiceAtual();
			if((i == LinhaAnalisada.vetorAnalise.size() - 1 && LinhaAnalisada.vetorAnalise.get(i).token() == Token.ABRECHAVE)) {
				System.out.println("ESTRUTURAFOR - ");
				System.out.println("Linha: " + LinhaAtual+1 + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.println(c.lexema()));
				return true;
			} else if(verificaAbreChave(VetorAnaliseLexica, LinhaAtual)) {
				System.out.println("ESTRUTURAFOR - ");
				System.out.println("Linha: " + LinhaAtual + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.println(c.lexema()));
				System.out.println("Linha: " + (LinhaAtual+1) + " ");
				VetorAnaliseLexica.get(LinhaAtual+1).vetorAnalise.forEach(c -> System.out.println(c.lexema()));
				return true;
			}
		}
		
		return false;
	}
	
	boolean posAberturaParenteses(ControleSintatico LinhaAnalisada, ArrayList<ControleSintatico> VetorAnaliseLexica) {
		for(int i = 1; i < LinhaAnalisada.vetorAnalise.size() - 1; i++) {
			objControle.setIndiceAtual(i);
			if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ABREPARENTESE) {
				continue;
			} else if(LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.ABREPARENTESE &&
					  new ComandoAtribuicao().ComandoAuxAtribuicao(VetorAnaliseLexica, LinhaAnalisada)) {
				i = objControle.getIndiceAtual();
				continue;
			} else if(new OperacaoComparacao().verificar(VetorAnaliseLexica)) {
				i = objControle.getIndiceAtual() + 1;
				if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.PONTOVIRGULA) {
					continue;
				} else {
					return false;
				}
			} else if(new Incremento().verificar(VetorAnaliseLexica)) {
				i = objControle.getIndiceAtual() + 1;
				if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.FECHAPARENTESE) {
					objControle.setIndiceAtual(i);
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}
	
	boolean verificaAbreChave(ArrayList<ControleSintatico> VetorAnaliseLexica, int LinhaAtual) {
		if(VetorAnaliseLexica.get(LinhaAtual+1).vetorAnalise.get(0).token() == Token.ABRECHAVE) {
			return true;
		} else {
			return false;
		}
	}
}
