import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		float num1, num2, num3;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite o numero 1: ");
		num1 = scan.nextFloat();
		System.out.println("Digite o numero 2: ");
		num2 = scan.nextFloat();
		System.out.println("Digite o numero 3: ");
		num3 = scan.nextFloat();
		
		OperacoesMatematicas Operacoes;
		Operacoes = new OperacoesMatematicas(num1, num2, num3);
		
		System.out.println("\n------ Operacoes com os 3 numeros ------\n");
		System.out.println("A Soma dos Numero é: " + Operacoes.Somar());
		System.out.println("A Subtracao dos Numero é: " + Operacoes.Subtrair());
		System.out.println("A Multiplicacao dos Numero é: " + Operacoes.Multiplcar());
		System.out.println("A Media dos Numero é: " + Operacoes.CalcularMedia());
		System.out.println("O Maior Numero é: " + Operacoes.MaiorNumero());
	}

}
