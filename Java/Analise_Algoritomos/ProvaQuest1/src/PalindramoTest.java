import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PalindramoTest {

	@ParameterizedTest
	@MethodSource("inputOutputProvider")
	void test(String input, boolean excepetedOuput) {
		Palindramo p = new Palindramo();
		assertEquals(excepetedOuput, p.MeuPalindramo(input));
	}
	
	@ParameterizedTest
	@MethodSource("inputOutputProvider")
	void test1(String input, boolean excepetedOuput) {
		Palindramo p = new Palindramo();
		assertEquals(excepetedOuput, p.ExercPalindromo(input));
	}
	
	static Stream<Arguments> inputOutputProvider() {
		return Stream.of(
				Arguments.of("bbbbbb", true),
				Arguments.of("abba", true),
				Arguments.of("bb", true),
				Arguments.of("aa", true),
				Arguments.of("aba", true),
				Arguments.of("abbbab", false),
				Arguments.of("baa", false),
				Arguments.of("bababba", false),
				Arguments.of("baabba", false),
				Arguments.of("ab", false)
			);
	}
}
