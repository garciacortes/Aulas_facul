import java.util.Scanner;

public class Principal2 {

	public static void main(String[] args) {
		
		int opcao, qtdPaginas, paginasLidas;
		String titulo = null;
		
		Livro lv = new Livro();
		Scanner scan = new Scanner(System.in);
		
		do {
			System.out.println("\n-------- MENU --------");
			System.out.println("1- Cadastrar Livro");
			System.out.println("2- Livro Cadastrado e Prgresso de Leitura");
			System.out.println("3- Alterar informações");
			System.out.println("4- Sair");
			System.out.println("-----------------------");
			System.out.println("Digite a opção: ");
			opcao = scan.nextInt();
			switch(opcao) {
			case 1:
				scan.nextLine();
				System.out.println("\n------- Cadastro do Livro -------");
				System.out.println("Digite o Titulo do Livro: ");
				titulo = scan.nextLine();
				lv.setTitulo(titulo);
				System.out.println("Digite quantas Paginas tem o Livro: ");
				qtdPaginas = scan.nextInt();
				lv.setQtdPaginas(qtdPaginas);
				System.out.println("Digite a quantidade de Paginas lidas: ");
				paginasLidas = scan.nextInt();
				lv.setPaginasLidas(paginasLidas);;
				System.out.println("------------------------------------");
				break;
			case 2:
				System.out.println("\n------ Livro Cadastrado ------");
				System.out.println("Titulo: " + lv.getTitulo());
				System.out.println("Quantidade de Paginas Total: " + lv.getQtdPaginas());
				System.out.println("Quantidade de Paginas Livros: " + lv.getPaginasLidas());
				System.out.println("-----------------------------------");
				System.out.println("\n------ Progresso de Leitura ------");
				lv.setPorcentLido();;
				System.out.println("Voce Ja leu " + lv.getPorcentLido() + "% do livro");
				System.out.println("-----------------------------------");
				break;
			case 3:
				scan.nextLine();
				System.out.println("\n------ Alterar informações do Livro ------");
				System.out.println("Digite o Novo titulo do livro: ");
				titulo = scan.nextLine();
				lv.setTitulo(titulo);
				System.out.println("Digite a nova quantidade de paginas lidas: ");
				paginasLidas = scan.nextInt();
				lv.setPaginasLidas(paginasLidas);
				System.out.println("------------------------------------------");
				break;
			case 4:
				scan.close();
				System.exit(1);
				break;
			}
			
		} while(true);
	}

}
