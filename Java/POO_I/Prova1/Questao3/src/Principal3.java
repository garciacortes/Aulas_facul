import java.nio.file.FileSystemNotFoundException;
import java.util.Scanner;

public class Principal3 {

	public static void main(String[] args) {
		
		int qtdeAlunos, qtdeNotas;
		double nota, ValorAcima;
		double AcimaMedia[];
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite a quantidade de Alunos: ");
		qtdeAlunos = scan.nextInt();
		
		Aluno objAluno;
		objAluno = new Aluno(qtdeAlunos);
		
		System.out.println("-------- Dados dos Aunos --------");
		for(int i = 0; i < qtdeAlunos; i++) {
			System.out.println("Deseja inserir quantas Notas para o Aluno " + (i + 1) + ": ");
			qtdeNotas = scan.nextInt();
			for(int j = 0; j < qtdeNotas; j++) {
				System.out.println("Digite a nota " + (j + 1) + " para o Aluno " + (i + 1) + ": ");
				nota = scan.nextDouble();
				objAluno.InsereNotas(nota, i);
			}
		}
		
		System.out.println("Digite o Valor para calcular o valor acima: ");
		ValorAcima = scan.nextDouble();	
		AcimaMedia = objAluno.MediaAcima(ValorAcima);
		System.out.println("A Media de todas as Notas são: " + objAluno.Media());
			System.out.println("Nenhuma media esta acima de " + ValorAcima);
			for(double i : AcimaMedia) {
				if(i > ValorAcima)
					System.out.println("A media que estão acima da Media " + ValorAcima + " são: " + i);
			}
		System.out.println("A quantidade de Alunos Que foram reprovados na media 4 foram: " + objAluno.Reprovados());
	}

}
