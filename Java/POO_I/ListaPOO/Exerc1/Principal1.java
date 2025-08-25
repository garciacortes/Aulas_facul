import java.util.Scanner;

public class Principal1 {

	public static void main(String[] args) {
		
		int count, opcao;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite o valor usado para contagem: ");
		count = scan.nextInt();
		
		Contador objCount = new Contador(count);
		
		do {
			System.out.println("\n-------- MENU -------");
			System.out.println("1- Zerar");
			System.out.println("2- Incrementar");
			System.out.println("3- Retornar Valordo contador");
			System.out.println("4- Sair");
			System.out.println("Digite a opcao escolhida: ");
			opcao = scan.nextInt();
			switch(opcao) {
			case 1:
				objCount.setCount(0);
				objCount.setNum(0);
				break;
			case 2:
				objCount.incrementar();
				break;
			case 3:
				if(objCount.getNum() == 0) {
					System.out.println("\nValor zerado ou sem Incremento.");
				}
				else {
					System.out.println("\nO valor do contador e: " + objCount.getNum());
				}
				break;
			case 4:
				System.exit(1);
				break;
			}
			
		}while(opcao != 4);
	}

}
