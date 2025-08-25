import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Arvore MinhaArvore = new Arvore();

		System.out.println("******** ARVORE BINARIA ********");
		System.out.println("Obs.: para terminar a inserção de dados, digite -999 ");

		int i, opcao, QtdElemento, SomaElemento, MaiorElemento, MenorElemento, altura, remover;
		
		System.out.println("Digite a opcao: ");
		opcao = scan.nextInt();
		System.out.println("1- ");
		System.out.println("2- ");
		System.out.println("3- ");
		System.out.println("4- ");
		System.out.println("5- ");
		System.out.println("6- ");
		System.out.println("7- ");
		System.out.println("8- ");
		System.out.println("9- ");
		System.out.println("10- ");
		System.out.println("11- ");
		System.out.println("12- ");
		System.out.println("13- ");
		System.out.println("14- ");
		System.out.println("15- ");
		
		
		
		
		switch(opcao) {
		case 1:
			System.out.println("\n***** Busca na árovre binaria *****");
		    System.out.println("Digite o valor da Busca: ");
		    i = scan.nextInt();
		    if(MinhaArvore.ProcuraValor(i)) {
		    	System.out.println("Valor Encontrado\n");
		    }
		    else {
		    	System.out.println("Valor não Encontrado\n");
		    }
			break;
		case 2:
			System.out.println("Digite um Numero: ");
			i = scan.nextInt();
			while (i != -999) {
				MinhaArvore.InsereNovoNo(i);
				System.out.println("Digite um Numero: ");
				i = scan.nextInt();
			}
			break;
		case 3:
			QtdElemento = MinhaArvore.ContaElemento(MinhaArvore.Raiz);
			System.out.println("\nA quantidade de numeros inseridos: " + QtdElemento);
			break;
		case 4:	
			SomaElemento = MinhaArvore.SomaElemento(MinhaArvore.Raiz);
			System.out.println("\nA Soma dos numeros inseridos: " + SomaElemento);
			break;
		case 5:
			MaiorElemento = MinhaArvore.MaiorElemento(MinhaArvore.Raiz);
			System.out.println("\nO Maior Elemento da Arvore: " + MaiorElemento);
			break;
		case 6:
			MenorElemento = MinhaArvore.MenorElemento(MinhaArvore.Raiz);
			System.out.println("\nO Menor Elemento da Arvore: " + MenorElemento);
			break;
		case 7:
			System.out.println("\n********** Impressão de Folha **********");
			MinhaArvore.ImprimeFolha(MinhaArvore.Raiz);
			break;
		case 8:
			System.out.println("\n\n********** Impressão de Não Folha **********");
			MinhaArvore.ImprimeNaoFolha(MinhaArvore.Raiz);
			break;
		case 9:
			System.out.println("\n\n********** Impressão de Profundidade **********");
			MinhaArvore.ImprimeProfundidade(MinhaArvore.Raiz);
			break;
		case 10:
			altura = MinhaArvore.Altura(MinhaArvore.Raiz);
			System.out.println("\nA altura da arvore: " + altura);
			break;
		case 11:
			System.out.println("***** Impressão da Arvore em ordem crescente *****");
		    MinhaArvore.ImprimeArvoreCrescente(MinhaArvore.Raiz, 3);
			break;
		case 12:
			System.out.println("\n\n********** Remoção de No **********");
			System.out.println("Digite o valor que deseja remover: ");
			remover = scan.nextInt();
			MinhaArvore.RemoveNo(remover);
			break;
		case 13:
		    System.out.println("***** Impressão da Arvore em ordem crescente Apos Remoção *****");
		    MinhaArvore.ImprimeArvore(MinhaArvore.Raiz, 3);
			break;
		case 14:
			break;
		case 15:
			scan.close();
			System.out.println("\n********** PROGRAMA ENCERRADO *********");
			System.exit(1);
			break;
		}	
	}

}
