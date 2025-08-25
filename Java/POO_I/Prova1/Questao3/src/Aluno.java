
public class Aluno {
	
	private int totalAlunos, reprovados, count, countNotas;
	private double[][] notas;
	private double media, somaTotal, somaAluno, mediaTotal, nota;
	double []mediaAcima;
	
	public Aluno(int qtde) {
		totalAlunos = qtde;
		notas = new double[totalAlunos][];
	}
	
	public void InsereNotas(double nota, int index) {
		if(notas[index] == null) {
			notas[index] = new double[1];
		}
		else {
			double[] temp = new double[notas[index].length + 1];
			System.arraycopy(notas[index], 0, temp, 0, notas[index].length);
			notas[index] = temp;
		}
		
		notas[index][notas[index].length - 1] = nota;
	}
	
	public double Media() {
		
		count = 0;
		somaTotal = 0;
		
		for (int i = 0; i < totalAlunos; i++) {
		    if (notas[i] != null) {
		        for (int j = 0; j < notas[i].length; j++) {
		            nota = notas[i][j];
		            somaTotal += nota;
		            count++;
		        }
		    }
		}
		
		mediaTotal = somaTotal / count;
		return mediaTotal;
	}
	
	public double[] MediaAcima(double valor) {
		
		somaAluno = 0;
		countNotas = 0;
		reprovados = 0;
		
		mediaAcima = new double[totalAlunos];
		
		for (int i = 0; i < totalAlunos; i++) {
		    if (notas[i] != null) {
		        for (int j = 0; j < notas[i].length; j++) {
		            double nota = notas[i][j];
		            somaAluno += nota;
		            countNotas++;
		        }
		    }
		    mediaAcima[i] = somaAluno / countNotas;
		    if((somaAluno / countNotas) < 4) {
		    	reprovados++;
		    }
		}
		
		return mediaAcima;
	}
	
	public int Reprovados() {
		return reprovados;
	}
}


