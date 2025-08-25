import java.util.ArrayList;
import java.util.Scanner;

public class Principal3 {

	public static void main(String[] args) {
		
		String nome, nomeFacul;
		int opcao, dia, mes, ano;
		
		Scanner scan = new Scanner(System.in);
		ArrayList<Pessoa> array_Inf = new ArrayList<Pessoa>();
		do {
			System.out.println("\n-------- MENU --------");
			System.out.println("1- Adicionar Pessoa");
			System.out.println("2- Relatorio Pessoa");
			System.out.println("0- sair");
			System.out.println("----------------------");
			System.out.println("Digite a Opcão: ");
			opcao = scan.nextInt();
			switch (opcao) {
			case 1:
				scan.nextLine();
				System.out.println("\n------- Adicionando Pessoa --------");
				System.out.println("Digite o Nome: ");
				nome = scan.nextLine();
				System.out.println("Digite o Nome da Facul: ");
				nomeFacul = scan.nextLine();
				System.out.println("Digite o Dia: ");
				dia = scan.nextInt();
				System.out.println("Digite o Mes: ");
				mes = scan.nextInt();
				System.out.println("Digite o Ano: ");
				ano = scan.nextInt();
				Pessoa inf = new Pessoa();
				inf.setNome(nome);
				inf.setFacul(nomeFacul);
				inf.setDia(dia);
				inf.setMes(mes);
				inf.setAno(ano);
				inf.setIdade(ano);
				array_Inf.add(inf);
				System.out.println("----------------------------------");
				break;
			case 2:
				System.out.println("\n--------- Relatorio ---------");
				for(Pessoa i : array_Inf) {
					String text = String.format("Nome: " + i.getNome() + " | Facul: " + i.getFacul() + 
							" | Idade: " + i.getIdade() + " | Dia: " + i.getDia() + 
							" | Mes: " + i.getMes() + " | Ano: " + i.getAno()); 
					System.out.println(text);
				}
				System.out.println("----------------------------");
				break;
			case 0:
				scan.close();
				System.exit(1);
				break;
			}
		}while(true);
		
		
	}

}
