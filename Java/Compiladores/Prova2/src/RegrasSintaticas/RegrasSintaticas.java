package RegrasSintaticas;

import java.util.ArrayList;

import AnaliseLexica.AnalisadorSintatico;
import AnaliseLexica.ControleSintatico;

public interface RegrasSintaticas {
	
	ControleSintatico objControle = new ControleSintatico();
	
	boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica);
}
