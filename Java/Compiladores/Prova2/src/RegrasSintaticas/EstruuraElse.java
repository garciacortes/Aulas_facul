package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.RegrasSintatica;
import Analisador.Token;

public class EstruuraElse implements RegrasSintaticas{

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		RegrasSintatica objRegrasSintatica = new RegrasSintatica();
		if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.ESTRUTURAELSE &&
			LinhaAnalisada.vetorAnalise.get(1).token() != Token.ABRECHAVE) {
			objControle.setIndiceAtual(1);
			System.out.print("ELSE - ");
			if(objRegrasSintatica.Programa(VetorAnaliseLexica)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
