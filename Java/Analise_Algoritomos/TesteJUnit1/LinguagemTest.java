import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LinguagemTest {
	
	private String[] palavras = {"abc", "aabbcc", "abcabc"};
	private boolean[] retornos = {true, true, false};
	
	@Test
	void teste01() {
		Linguagem objeto = new Linguagem();
		for(int i = 0; i < palavras.length; i++) {
			boolean retorno = objeto.VerificaPalavra(palavras[i]);
			assertEquals(retornos[i], retorno);
		}
	}

}
