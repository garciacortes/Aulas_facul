
public class Linguagem {
	
	boolean VerificaPalavra(String palavra) {
		int contA = 0;
		int	contB = 0;
		int	contC = 0;
		boolean pertence  = false;
		
		for(int i = 0; i < palavra.length(); i++) {
			if(palavra.charAt(i) == 'a') {
				contA++;
			}
		}
		
		for(int i = (contA - 1); i < palavra.length(); i++) {
			if(palavra.charAt(i) == 'b') {
				contB++;
			}
		}
		
		for(int i = (contA + contB - 1); i < palavra.length(); i++) {
			if(palavra.charAt(i) == 'c') {
				contC++;
			}
		}
		if(contA >= 1) {
			if((contA == contB) && (contA == contC)) {
				pertence = true;
			} else {
				pertence = false;
			}
		} else {
			pertence = false;
		}
			
		return pertence;
	}
}
