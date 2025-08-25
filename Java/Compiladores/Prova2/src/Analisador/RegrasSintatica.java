package Analisador;
import java.util.ArrayList;

import RegrasSintaticas.ComandoAtribuicao;
import RegrasSintaticas.ComandoPrintf;
import RegrasSintaticas.ComandoScanf;
import RegrasSintaticas.DeclaracaoVariavel;
import RegrasSintaticas.EstruturaFor;
import RegrasSintaticas.EstruturaIf;
import RegrasSintaticas.EstruuraElse;

public class RegrasSintatica {
	
	public boolean Programa(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		
		return  new DeclaracaoVariavel().verificar(VetorAnaliseLexica) || 
				new ComandoAtribuicao().verificar(VetorAnaliseLexica) ||
				new EstruturaFor().verificar(VetorAnaliseLexica) || 
				new ComandoPrintf().verificar(VetorAnaliseLexica) ||
				new ComandoScanf().verificar(VetorAnaliseLexica) ||
				new EstruturaIf().verificar(VetorAnaliseLexica) ||
				new EstruuraElse().verificar(VetorAnaliseLexica);
			
	}
}
