package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.RegrasSintatica;
import Analisador.Token;

public class EstruturaElse implements RegrasSintaticas, SyntaxErrorException {

	@Override
 	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		RegrasSintatica objRegrasSintatica = new RegrasSintatica();
		if(!qtdeChaves.isEmpty() &&(qtdeChaves.lastKey().equals("ELSE IF") || qtdeChaves.lastKey().equals("IF")) &&
		    (LinhaAnalisada.vetorAnalise.get(0).token() == Token.FECHACHAVE &&
		    LinhaAnalisada.vetorAnalise.get(1).token() == Token.ESTRUTURAELSE)) {
			qtdeChaves.remove(qtdeChaves.lastKey());
			if(LinhaAnalisada.vetorAnalise.get(2).token() == Token.ABRECHAVE) {
				qtdeChaves.put("ELSE", LinhaAnalisada.linha);
				System.out.println("ESTRUTURAELSE - ");
				System.out.println("Linha: " + LinhaAnalisada.linha + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
				System.out.println("");
				return true;
			} else if(LinhaAnalisada.vetorAnalise.size() > 2 && LinhaAnalisada.vetorAnalise.get(2).token() != Token.ABRECHAVE) {
				objControle.setIndiceAtual(2);
				System.out.print("ELSE - ");
				return objRegrasSintatica.Programa(VetorAnaliseLexica);
			} else if(VetorAnaliseLexica.get(LinhaAtual+1).vetorAnalise.get(0).token() == Token.ABRECHAVE) {
				System.out.println("ESTRUTURAELSE - ");
				System.out.println("Linhas: " + LinhaAnalisada.linha + "|" + (LinhaAnalisada.linha+1) + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
				VetorAnaliseLexica.get(LinhaAtual+1).vetorAnalise.forEach(c -> System.out.print(c.lexema()));
				System.out.println("");
				qtdeChaves.put("ELSE", LinhaAnalisada.linha+1);
				objControle.setLinhaAtual(LinhaAtual+1);
				return true;
			}
		} else if(LinhaAnalisada.vetorAnalise.size() > 1 &&
				  LinhaAnalisada.vetorAnalise.get(0).token() == Token.FECHACHAVE &&
		          LinhaAnalisada.vetorAnalise.get(1).token() == Token.ESTRUTURAELSE) {
			logError("Else Error: inicio ilegal de declaração", LinhaAnalisada.linha);
			return true;
			
		}
		return VerificaMetodos(VetorAnaliseLexica, LinhaAnalisada, LinhaAtual);
	}

	private boolean VerificaMetodos(ArrayList<ControleSintatico> VetorAnaliseLexica, ControleSintatico LinhaAnalisada, int LinhaAtual) {
		RegrasSintatica objRegrasSintatica = new RegrasSintatica();
	    if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.ESTRUTURAELSE &&
				LinhaAnalisada.vetorAnalise.get(1).token() != Token.ABRECHAVE) {
			objControle.setIndiceAtual(1);
			System.out.print("ELSE - ");
			return objRegrasSintatica.Programa(VetorAnaliseLexica);
		} else if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.ESTRUTURAELSE &&
				  LinhaAnalisada.vetorAnalise.get(1).token() == Token.ABRECHAVE) {
			qtdeChaves.put("ELSE", LinhaAnalisada.linha);
			System.out.println("ESTRUTURAELSE - ");
			System.out.println("Linha: " + LinhaAnalisada.linha + " ");
			LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
			System.out.println("");
			return true;
		} else if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.ESTRUTURAELSE &&
				  VetorAnaliseLexica.get(LinhaAtual+1).vetorAnalise.get(0).token() == Token.ABRECHAVE) {
			System.out.println("ESTRUTURAELSE - ");
			System.out.println("Linhas: " + LinhaAnalisada.linha + "|" + (LinhaAnalisada.linha+1) + " ");
			LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
			VetorAnaliseLexica.get(LinhaAtual+1).vetorAnalise.forEach(c -> System.out.print(c.lexema()));
			System.out.println("");
			qtdeChaves.put("ELSE", LinhaAnalisada.linha+1);
			objControle.setLinhaAtual(LinhaAtual+1);
			return true;
		} else {
			return false;
		}
	}
}
