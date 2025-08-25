package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.RegrasSintatica;
import Analisador.Token;

public class EstruturaSwitch implements RegrasSintaticas, SyntaxErrorException {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.SWITCH &&
		   LinhaAnalisada.vetorAnalise.get(1).token() == Token.ABREPARENTESE &&
		   LinhaAnalisada.vetorAnalise.get(2).token() == Token.VARIAVEL &&
		   LinhaAnalisada.vetorAnalise.get(3).token() == Token.FECHAPARENTESE) {
			if(LinhaAnalisada.vetorAnalise.size() > 4 &&
			   LinhaAnalisada.vetorAnalise.get(1).token() == Token.ABRECHAVE) {
				qtdeChaves.put("SWITCH", LinhaAnalisada.linha);
				System.out.println("ESTRUTURASWITCH - ");
				System.out.println("Linha: " + LinhaAnalisada.linha + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
				System.out.println("");
				return true;
			} else if(VetorAnaliseLexica.get(LinhaAtual+1).vetorAnalise.get(0).token() == Token.ABRECHAVE) {
				qtdeChaves.put("SWITCH", LinhaAnalisada.linha+1);
				System.out.println("ESTRUTURASWITCH - ");
				System.out.println("Linhas: " + LinhaAnalisada.linha + "|" + (LinhaAnalisada.linha+1) + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
				VetorAnaliseLexica.get(LinhaAtual+1).vetorAnalise.forEach(c -> System.out.print(c.lexema()));
				System.out.println("");
				objControle.setLinhaAtual(LinhaAtual+1);
				return true;
			} else {
				logError("Switch { esperado ", LinhaAnalisada.linha);
				return true;
			}
		}
		
		if(!qtdeChaves.isEmpty() && qtdeChaves.lastKey().equals("SWITCH")) {
			if(switchCase.isEmpty() && 
			  (LinhaAnalisada.vetorAnalise.size() < 4 && 
			   CaseSintaxe(LinhaAnalisada))) {
				switchCase.put("CASE", LinhaAnalisada.linha);
				System.out.println("ESTRUTURACASE - ");
				System.out.println("Linha: " + LinhaAnalisada.linha + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
				System.out.println("");
				return true;
			} else if(switchCase.isEmpty() && 
					 (LinhaAnalisada.vetorAnalise.size() > 3 && 
					  CaseSintaxe(LinhaAnalisada) && objControle.getIndiceAtual() == 0)) {
				switchCase.put("CASE", LinhaAnalisada.linha);
				objControle.setIndiceAtual(3);
				objControle.setLinhaAtual(LinhaAtual);
				return new RegrasSintatica().Programa(VetorAnaliseLexica);
			} else if(switchCase.isEmpty() && 
					  LinhaAnalisada.vetorAnalise.get(0).token() == Token.DEFAULT &&
					  LinhaAnalisada.vetorAnalise.get(1).token() == Token.DOISPONTOS) {
				objControle.setIndiceAtual(2);
				objControle.setLinhaAtual(LinhaAtual);
				return new RegrasSintatica().Programa(VetorAnaliseLexica);
			} else if(switchCase.isEmpty() &&
 					  LinhaAnalisada.vetorAnalise.get(0).token() != Token.CASE &&
					  LinhaAnalisada.vetorAnalise.get(0).token() != Token.DEFAULT){
				logError("Switch Error: Estrutura ou comando invalida dentro do escopo", LinhaAnalisada.linha);
				return true;
			}
		} else if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.CASE) {
			logError("Case Error: Case fora de uma estrutura Switch", LinhaAnalisada.linha);
			return true;
		} else {
			return false;
		}
		return false;
	}
	
	private boolean CaseSintaxe(ControleSintatico LinhaAnalisada) {
		if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.CASE &&
				LinhaAnalisada.vetorAnalise.get(1).token() == Token.NUMEROINTEIRO &&
				LinhaAnalisada.vetorAnalise.get(2).token() == Token.DOISPONTOS) {
			return true;
		} else if(LinhaAnalisada.vetorAnalise.get(0).token() != Token.CASE &&
				LinhaAnalisada.vetorAnalise.get(1).token() == Token.NUMEROINTEIRO &&
				LinhaAnalisada.vetorAnalise.get(2).token() == Token.DOISPONTOS) {
			logError("Error: Case, default ou } esperado", LinhaAnalisada.linha);
			return false;
		} else if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.CASE &&
				LinhaAnalisada.vetorAnalise.get(1).token() != Token.NUMEROINTEIRO &&
				LinhaAnalisada.vetorAnalise.get(2).token() == Token.DOISPONTOS) {
			logError("Case Error: Espressão com Inicio incorreto", LinhaAnalisada.linha);
			return false;
		} else if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.CASE &&
				LinhaAnalisada.vetorAnalise.get(1).token() == Token.NUMEROINTEIRO &&
				LinhaAnalisada.vetorAnalise.get(2).token() != Token.DOISPONTOS) {
			logError("Case Error: ; Esperado", LinhaAnalisada.linha);
			return false;
		} 
		return false;
	}
	
}
