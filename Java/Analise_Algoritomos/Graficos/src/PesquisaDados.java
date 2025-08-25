
public class PesquisaDados {
	
	public int pesquisaBinaria(int chave, int vetor[], int tamanhoVetor) {
		int inicio = 0;
		int meio;
		int fim = tamanhoVetor - 1;
		int quantidadeOperacoes = 0;
		
		while(inicio <= fim) {
			quantidadeOperacoes++;
			meio = (inicio + fim) / 2;
			if(chave < vetor[meio]) {
				fim = meio - 1;
			} else if(chave > vetor[meio]) {
				inicio = meio + 1;
			} else {
				return quantidadeOperacoes;
			}
		}
		
		return quantidadeOperacoes;
	}
}
