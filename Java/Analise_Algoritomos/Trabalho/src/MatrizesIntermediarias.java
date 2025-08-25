
public class MatrizesIntermediarias implements Runnable{
	
	private int[][] A11, A12, A21, A22, B11, B12, B21, B22;
	private Questao2 q2 = new Questao2();

	private int[][] M1, M2, M3, M4, M5, M6, M7;
	
	public MatrizesIntermediarias(int[][] A11, int[][] A12, int[][] A21, int[][] A22,
								  int[][] B11, int[][] B12, int[][] B21, int[][] B22) {
		this.A11 = A11;
		this.A12 = A12;
		this.A21 = A21;
		this.A22 = A22;
		this.B11 = B11;
		this.B12 = B12;
		this.B21 = B21;
		this.B22 = B22;
		
	}
	
	public void run() {
	}
	
	public void M1() {
		int[][] M1 = q2.MultMatrizes(q2.SomaMatrizes(A11, A22), q2.SomaMatrizes(B11, B22));
	}
	
	public void M2() {
		int[][] M2 = q2.MultMatrizes(q2.SomaMatrizes(A21, A22), B11);
	}
	
	public void M3() {
		int[][] M3 = q2.MultMatrizes(A11, q2.SubMatrizes(B12, B22));
	}
	
	public void M4() {
		int[][] M4 = q2.MultMatrizes(A22, q2.SubMatrizes(B21, B11));
	}
	
	public void M5() {
		int[][] M5 = q2.MultMatrizes(q2.SomaMatrizes(A11, A12), B22);
	}
	
	public void M6() {
		int[][] M6 = q2.MultMatrizes(q2.SubMatrizes(A21, A11), q2.SomaMatrizes(B11, B12));
	}
	
	public void M7() {
		int[][] M7 = q2.MultMatrizes(q2.SubMatrizes(A12, A22), q2.SomaMatrizes(B21, B22));
	}
	
	public int[][] getM1() {
		return M1;
	}
	
	public int[][] getM2() {
		return M2;
	}
	
	public int[][] getM3() {
		return M3;
	}
	
	public int[][] getM4() {
		return M4;
	}
	
	public int[][] getM5() {
		return M5;
	}
	
	public int[][] getM6() {
		return M6;
	}
	
	public int[][] getM7() {
		return M7;
	}
}
