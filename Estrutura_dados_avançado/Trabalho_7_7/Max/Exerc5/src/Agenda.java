import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {

	public static void main(String[] args) {
		
		int opcao;
		String nome, telefone, nomeBusca;
		
		ArrayList<Contato> contatos = new ArrayList<Contato>();
		Scanner scan = new Scanner(System.in);
		Contato ctn;
		
		do {
			System.out.println("\n-------- MENU --------");
			System.out.println("1- Cadastrar Contato");
			System.out.println("2- Buscar Contato");
			System.out.println("4- sair");
			System.out.println("----------------------");
			System.out.println("Digite a Opcão: ");
			opcao = scan.nextInt();
			switch (opcao) {
			case 1:
				scan.nextLine();
				System.out.println("------- Cadastrando Contato -------");
				System.out.println("Digit o nome do Contato: ");
				nome = scan.nextLine();
				System.out.println("Digit o telefone do Contato: ");
				telefone = scan.nextLine();
				ctn = new Contato(nome, telefone);
				contatos.add(ctn);
				System.out.println("-----------------------------------");
				break;
			case 2:
				scan.nextLine();
				System.out.println("------- Buscar Contato -------");
				System.out.println("Digit o nome do Contato:");
				nomeBusca = scan.nextLine();
				for(Contato i : contatos) {
					System.out.println("Teste " + i.getNome() + " | " + nomeBusca);
					if(i.getNome().equals(nomeBusca)) {
						System.out.println("O numero do contato buscado: " + i.getTelefone());
					}
					else {
						System.out.println("Contato Inexistente");
					}
				}
				System.out.println("-----------------------------------");
				break;
			case 3:
				System.out.println("------- Imprimir Agenda -------");
				for(Contato i : contatos) {
					System.out.println("Contato: " + i.getNome() + " | Telefone: " + i.getTelefone());
				}
				System.out.println("------------------------------");
				break;
			case 4:
				scan.close();
				System.exit(1);
				break;
			}
			
			
		}while(true);
	}

}
