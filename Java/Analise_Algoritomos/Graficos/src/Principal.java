import java.util.Scanner;

import org.jfree.data.xy.XYSeries;

public class Principal extends Thread {

	PesquisaDados objPesquisaDados = new PesquisaDados();
	Grafico objGrafico;
	
	public static void main(String[] args) {
		new Principal().start();
	}
	
	public void run() {
		while(true) {
			Scanner teclado = new Scanner(System.in);
			int NumeroProcurado;
			System.out.println("Digite o numero para procurar no vetor: ");
			NumeroProcurado = teclado.nextInt();
			
			XYSeries series = new XYSeries("Complexidade algoritimo Busca Binaria");
			
			for(int tamanho = 100; tamanho < 1000; tamanho += 100) {
				int vetor[] = new int[tamanho];
				
				for(int i = 0; i < tamanho; i++) {
					vetor[i] = i;
				}
				
				int quantidade = objPesquisaDados.pesquisaBinaria(NumeroProcurado, vetor, tamanho);
				series.add(tamanho, quantidade);
				System.out.println("Quantidade de busca: " + quantidade);
	 		}
			
			objGrafico = new Grafico(series);
			System.out.println("Grafico gerado com sucesso!");
		}
	}

}
