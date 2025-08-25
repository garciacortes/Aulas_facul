package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class EstruturaFor implements RegrasSintaticas {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {		
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		
		if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.ESTRUTURAFOR) {
			if(!posAberturaParenteses(LinhaAnalisada, VetorAnaliseLexica)) {
				return false;
			}
			
			int i = objControle.getIndiceAtual() + 1;
			if((i == LinhaAnalisada.vetorAnalise.size() - 1 && LinhaAnalisada.vetorAnalise.get(i).token() == Token.ABRECHAVE)) {
				System.out.println("ESTRUTURAFOR - ");
				System.out.println("Linha: " + LinhaAnalisada.linha + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.println(c.lexema()));
				if(!qtdeChaves.isEmpty()) {
					String nomeChave = qtdeChaves.lastKey();
					 String nome = nomeChave.substring(0, 1);
					 int valor = Integer.parseInt(nomeChave.substring(2));
					qtdeChaves.put(nome + " " + (valor+1), LinhaAnalisada.linha);
				} else {
					qtdeChaves.put("FOR 1", LinhaAnalisada.linha);
				}
				return true;
			} else if(verificaAbreChave(VetorAnaliseLexica, LinhaAtual)) {
				System.out.println("ESTRUTURAFOR - ");
				System.out.println("Linhas: " + LinhaAnalisada.linha + "|" + (LinhaAnalisada.linha+1) + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.println(c.lexema()));
				VetorAnaliseLexica.get(LinhaAtual+1).vetorAnalise.forEach(c -> System.out.println(c.lexema()));
				if(!qtdeChaves.isEmpty()) {
					String nomeChave = qtdeChaves.lastKey();
					 String nome = nomeChave.substring(0, 3);
					 int valor = Integer.parseInt(nomeChave.substring(4));
					qtdeChaves.put(nome + " " +(valor+1), LinhaAnalisada.linha);
				} else {
					qtdeChaves.put("FOR 1", LinhaAnalisada.linha);
				}
				return true;
			} else if((i == LinhaAnalisada.vetorAnalise.size() && LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.FECHAPARENTESE)) {
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
			objControle.setLinhaAtual(LinhaAtual+1);
			return true;
		} else {
			return false;
		}
	}
}
