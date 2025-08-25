
public class Linguagem {
	
	int qtde, j; 
	int Verificacao = 0;;
	boolean resultado = false;
	
	//abba
	boolean VerificaPalavra(String palavra) {
		
		qtde = palavra.length() / 2;
		j = (palavra.length() - 1);
	
		for(int i = 0; i < qtde; i++) {
			if(j >= qtde) {
				if(palavra.charAt(i) == 'a' && palavra.charAt(j) == 'a') {
					System.out.println("Entrou a");
					Verificacao++;
				} else if(palavra.charAt(i) == 'b' && palavra.charAt(j) == 'b') {
					System.out.println("Entrou b");
					Verificacao++;
				}
			}
			j--;
		}
		if(Verificacao == qtde) {
			resultado = true;
		} else {
			resultado =  false;
		}
		
		return resultado;
	}
}
