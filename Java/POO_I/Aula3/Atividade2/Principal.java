import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		double Valor1, Valor2, Peso1, Peso2;
		int Opcao;
		
		Scanner scan = new Scanner(System.in);
		
		OperacaoMatematica Operacoes = new OperacaoMatematica();
		
		System.out.println("Digite o Valor 1: ");
		Valor1 = scan.nextDouble();
		System.out.println("Digite o Valor 2: ");
		Valor2 = scan.nextDouble();
		
		do {
			System.out.println("\n-------- MENU --------");
			System.out.println("1- Soma");
			System.out.println("2- Subtracacao");
			System.out.println("3- Divisao");
			System.out.println("4- Multiplicacao");
			System.out.println("5- Media");
			System.out.println("6- Media Ponderada");
			System.out.println("7- Numeros Novos");
			System.out.println("8- Sair");
			System.out.println("Digite a opcao Escolhida:");
			Opcao = scan.nextInt();
			switch(Opcao) {
				case 1:
					System.out.println("A Soma dos Dois Numeros e: " + Operacoes.Somar(Valor1, Valor2));
					break;
				case 2:
					System.out.println("A Subtracacao dos Dois Numeros e: " + Operacoes.Subtracao(Valor1, Valor2));
					break;
				case 3:
					System.out.println("A Divisao dos Dois Numeros e: " + Operacoes.Divisao(Valor1, Valor2));
					break;
				case 4:
					System.out.println("A Multiplicacao dos Dois Numeros e: " + Operacoes.Multiplicacao(Valor1, Valor2));
					break;
				case 5:
					System.out.println("A Media dos Dois Numeros e: " + Operacoes.Media(Valor1, Valor2));
					break;
				case 6:
					System.out.println("Digite o Peso1: ");
					Peso1 = scan.nextDouble();
					System.out.println("Digite o Peso2: ");
					Peso2 = scan.nextDouble();
					System.out.println("A Media Ponderada dos Dois Numeros e: " + Operacoes.MediaPonderada(Valor1, Valor2, Peso1, Peso2));
					break;
				case 7:
					System.out.println("Digite o Valor 1: ");
					Valor1 = scan.nextDouble(); 
					System.out.println("Digite o Valor 2: ");
					Valor2 = scan.nextDouble();
					break;
				default:
					System.exit(0);
			}
		}while(true);
	}

}
