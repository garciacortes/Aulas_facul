package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class ComandoBreak implements RegrasSintaticas, SyntaxErrorException {

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.BREAK && LinhaAnalisada.vetorAnalise.get(1).token() == Token.PONTOVIRGULA) {
			if((!qtdeChaves.isEmpty() && qtdeChaves.lastKey().equals("SWITCH")) && (!switchCase.isEmpty() && switchCase.lastKey().equals("CASE"))) {
				switchCase.remove(switchCase.lastKey());
				System.out.println("COMANDOBREAK - ");
				System.out.println("Linha: " + LinhaAnalisada.linha + " ");
				LinhaAnalisada.vetorAnalise.forEach(c -> System.out.print(c.lexema()));
				System.out.println("");
				return true;
			} else if(!qtdeChaves.isEmpty() && qtdeChaves.lastKey().equals("SWITCH") && (!switchCase.isEmpty() && !switchCase.lastKey().equals("CASE"))) {
				logWarning("Encontrado break sem declaração case", LinhaAnalisada.linha);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
