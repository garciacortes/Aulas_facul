package RegrasSintaticas;

import java.util.ArrayList;

import Analisador.ControleSintatico;
import Analisador.Token;

public class ComandoPrintf implements RegrasSintaticas{

	@Override
	public boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		int LinhaAtual = objControle.getLinhaAtual();
		ControleSintatico LinhaAnalisada = VetorAnaliseLexica.get(LinhaAtual);
		
		if(LinhaAnalisada.vetorAnalise.get(0).token() == Token.IMPRIMIR) {
			for(int i = 1; i < LinhaAnalisada.vetorAnalise.size(); i++) {
				if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.ABREPARENTESE) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.STRING &&
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
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.FECHAPARENTESE &&
						  (LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.STRING ||
						   LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.VARIAVEL)) {
					continue;
				} else if(LinhaAnalisada.vetorAnalise.get(i).token() == Token.PONTOVIRGULA &&
						  LinhaAnalisada.vetorAnalise.get(i-1).token() == Token.FECHAPARENTESE) {
					System.out.println("COMANDOPRINTF - ");
					System.out.println("Linha: " + LinhaAnalisada.linha + " ");
					LinhaAnalisada.vetorAnalise.forEach(c -> System.out.println(c.lexema()));
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
