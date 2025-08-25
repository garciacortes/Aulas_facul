package questao3;

import java.util.List;

public class Alfabeto {

	public long Verificar(List<Character> arr) {
		long quantidadeA = arr.stream().filter(i -> i.equals('A')).count();
		long quantidadeB = arr.stream().filter(i -> i.equals('B')).count();
		long quantidadeC = arr.stream().filter(i -> i.equals('C')).count();
		
		if(quantidadeB == 2 * quantidadeA && quantidadeC == 3 * quantidadeA) {
			return quantidadeA + quantidadeB + quantidadeC;
		} else {
			return 0;
		}
	}
}
