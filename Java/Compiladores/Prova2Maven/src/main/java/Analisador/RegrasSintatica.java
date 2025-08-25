package Analisador;
import java.util.ArrayList;

import RegrasSintaticas.ComandoAtribuicao;
import RegrasSintaticas.ComandoBreak;
import RegrasSintaticas.ComandoPrintf;
import RegrasSintaticas.ComandoScanf;
import RegrasSintaticas.ComandoSystem;
import RegrasSintaticas.Comentario;
import RegrasSintaticas.DeclaracaoVariavel;
import RegrasSintaticas.EstruturaDoWhile;
import RegrasSintaticas.EstruturaElse;
import RegrasSintaticas.EstruturaElseIf;
import RegrasSintaticas.EstruturaFor;
import RegrasSintaticas.EstruturaIf;
import RegrasSintaticas.EstruturaSwitch;
import RegrasSintaticas.Matriz;
import RegrasSintaticas.VerificarChaves;

public class RegrasSintatica {
	
	public boolean Programa(ArrayList<ControleSintatico> VetorAnaliseLexica) {
		
		return  new DeclaracaoVariavel().verificar(VetorAnaliseLexica) || 
				new ComandoAtribuicao().verificar(VetorAnaliseLexica) ||
				new EstruturaFor().verificar(VetorAnaliseLexica) || 
				new ComandoPrintf().verificar(VetorAnaliseLexica) ||
				new ComandoScanf().verificar(VetorAnaliseLexica) ||
				new EstruturaIf().verificar(VetorAnaliseLexica) ||
				new EstruturaElse().verificar(VetorAnaliseLexica) ||
				new VerificarChaves().verificar(VetorAnaliseLexica) ||
				new EstruturaElseIf().verificar(VetorAnaliseLexica) ||
				new EstruturaDoWhile().verificar(VetorAnaliseLexica) ||
				new EstruturaSwitch().verificar(VetorAnaliseLexica) || 
				new ComandoBreak().verificar(VetorAnaliseLexica) ||
				new ComandoSystem().verificar(VetorAnaliseLexica) ||
				new Matriz().verificar(VetorAnaliseLexica) ||
				new Comentario().verificar(VetorAnaliseLexica);
	}
}
