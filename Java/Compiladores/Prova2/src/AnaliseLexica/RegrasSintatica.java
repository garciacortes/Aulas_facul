package AnaliseLexica;
import java.util.ArrayList;

import RegrasSintaticas.ComandoAtribuicao;
import RegrasSintaticas.DeclaracaoVariavel;
import RegrasSintaticas.EstruturaFor;

public class RegrasSintatica {
	
	boolean ComandoEspecifico(ArrayList<ClassificacaoLexica> VetorAnaliseLexica) {
		
		int tamanho = VetorAnaliseLexica.size();
		
		if(tamanho < 5) {
			return false;
		}
		
		if((VetorAnaliseLexica.get(0).token() == Token.IMPRIMIR) && (VetorAnaliseLexica.get(1).token() == Token.ABREPARENTESE) &&
		   (VetorAnaliseLexica.get(2).token() == Token.VARIAVEL) && (VetorAnaliseLexica.get(3).token() == Token.FECHAPARENTESE) &&
		   (VetorAnaliseLexica.get(0).token() == Token.PONTOVIRGULA)) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean Programa(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		
		return new DeclaracaoVariavel().verificar(VetorAnaliseLexica) || new ComandoAtribuicao().verificar(VetorAnaliseLexica) ||
				new EstruturaFor().verificar(VetorAnaliseLexica);
			
	}
}
