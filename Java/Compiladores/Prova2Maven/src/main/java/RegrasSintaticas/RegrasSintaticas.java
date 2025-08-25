package RegrasSintaticas;

import java.util.ArrayList;

import org.apache.commons.collections4.map.ListOrderedMap;

import Analisador.ControleSintatico;

public interface RegrasSintaticas {
	
	ControleSintatico objControle = new ControleSintatico();
	ListOrderedMap<String, Integer> qtdeChaves = new ListOrderedMap<String, Integer>();
	ListOrderedMap<String, Integer> switchCase = new ListOrderedMap<String, Integer>();
	
	boolean verificar(ArrayList<ControleSintatico> VetorAnaliseLexica);
}
