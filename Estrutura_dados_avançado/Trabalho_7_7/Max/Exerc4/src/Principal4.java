import java.util.ArrayList;
import java.util.Scanner;

public class Principal4 {

	public static void main(String[] args) {
		
		int opcao; 
		Long cpf;
		String nome, curso;
		double salario;
		
		Scanner scan = new Scanner(System.in);
		Professor prof;
		Coordenador coord;
		ArrayList<Professor> prof_Array = new ArrayList<Professor>();
		ArrayList<Coordenador> coord_Array = new ArrayList<Coordenador>();
		
		do {
			System.out.println("\n-------- MENU --------");
			System.out.println("1- Adicionar Cadastro");
			System.out.println("2- Relatorio Cadastro");
			System.out.println("0- sair");
			System.out.println("----------------------");
			System.out.println("Digite a Opcão: ");
			opcao = scan.nextInt();
			switch (opcao) {
			case 1:
				scan.nextLine();
				System.out.println("\n------- Adicionando Cadastro Coordenador --------");
				System.out.println("Digite o Nome: ");
				nome = scan.nextLine();
				System.out.println("Digite o Nome do Curso: ");
				curso = scan.nextLine();
				System.out.println("Digite o Salario: ");
				salario = scan.nextInt();
				System.out.println("Digite o cpf: ");
				cpf = scan.nextLong();
				coord = new Coordenador(nome, curso, cpf, salario);
				coord_Array.add(coord);
				System.out.println("----------------------------------");
				
				scan.nextLine();
				System.out.println("\n------- Adicionando Cadastro Professor --------");
				System.out.println("Digite o Nome: ");
				nome = scan.nextLine();
				System.out.println("Digite o Salario: ");
				salario = scan.nextInt();
				System.out.println("Digite o cpf: ");
				cpf = scan.nextLong();
				prof = new Professor(nome, salario, cpf);
				prof_Array.add(prof);
				System.out.println("----------------------------------");
				
				break;
			case 2:
				System.out.println("\n--------- Relatorio Professor---------");
				for(Professor i : prof_Array) {
					i.Imprime();
				}
				System.out.println("----------------------------");
				
				System.out.println("\n--------- Relatorio Coordenador ---------");
				for(Coordenador i : coord_Array) {
					i.Imprime();
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
