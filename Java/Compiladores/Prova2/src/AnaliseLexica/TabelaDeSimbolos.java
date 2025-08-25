package AnaliseLexica;
import java.util.HashMap;

public class TabelaDeSimbolos {
	
	HashMap<Token, String> tabela;
	
	public TabelaDeSimbolos() {
		this.tabela = new HashMap<Token, String>();
	}
	
	public void AdicionarSimbolo(Token void1, String simbolo) {
		tabela.put(void1, simbolo);
	}
	
	public String BuscarValor(Token token) {
		return tabela.get(token);
	}
	
	public boolean ContemToken(Token token) {
		return tabela.containsKey(token);
	}
	
	public boolean ContemSimbolo(String simbolo) {
		return tabela.containsValue(simbolo);
	}
}
