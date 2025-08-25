import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LinguagemTest {

	@Test
	void teste1() {
		Linguagem obj = new Linguagem();
		boolean resultado = obj.VerificaPalavra("abba");
		assertEquals(true, resultado);
	}
	@Test
	void teste2() {
		Linguagem obj = new Linguagem();
		boolean resultado = obj.VerificaPalavra("abab");
		assertEquals(false, resultado);
	}
	@Test
	void teste3() {
		Linguagem obj = new Linguagem();
		boolean resultado = obj.VerificaPalavra("bba");
		assertEquals(false, resultado);
	}
	@Test
	void teste4() {
		Linguagem obj = new Linguagem();
		boolean resultado = obj.VerificaPalavra("ababa");
		assertEquals(true, resultado);
	}

}
