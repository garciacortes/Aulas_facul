public class Questao01 {

    private static long count = 0;
    
    public long Coppersmith_Winograd(int[][] A, int[][] B, int n) {
    	
    	int[][] C = new int[n][n];
    	
    	int[][] A11 = new int[n/2][n/2];
    	int[][] A12 = new int[n/2][n/2];
    	int[][] A21 = new int[n/2][n/2];
    	int[][] A22 = new int[n/2][n/2];
    	int[][] B11 = new int[n/2][n/2];
    	int[][] B12 = new int[n/2][n/2];
    	int[][] B21 = new int[n/2][n/2];
    	int[][] B22 = new int[n/2][n/2];
    	
    	for(int i = 0; i < n/2; i++) {
    		for(int j = 0; j < n/2; j++) {
    			A11[i][j] = A[i][j];
    			A12[i][j] = A[i][j + n/2];
    			A21[i][j] = A[i + n/2][j];
    			A22[i][j] = A[i + n/2][j + n/2];
    			B11[i][j] = B[i][j];
    			B12[i][j] = B[i][j + n/2];
    			B21[i][j] = B[i + n/2][j];
    			B22[i][j] = B[i + n/2][j + n/2];
    			count++;
    		}
    	}
    	
    	int[][] C11 = new int[n/2][n/2];
    	int[][] C12 = new int[n/2][n/2];
    	int[][] C21 = new int[n/2][n/2];
    	int[][] C22 = new int[n/2][n/2];
    	
    	int[][] M1 = MultMatrizes(SomaMatrizes(A11, A22), SomaMatrizes(B11, B22));
    	int[][] M2 = MultMatrizes(SomaMatrizes(A21, A22), B11);
    	int[][] M3 = MultMatrizes(A11, SubMatrizes(B12, B22));
    	int[][] M4 = MultMatrizes(A22, SubMatrizes(B21, B11));
    	int[][] M5 = MultMatrizes(SomaMatrizes(A11, A12), B22);
    	int[][] M6 = MultMatrizes(SubMatrizes(A21, A11), SomaMatrizes(B11, B12));
    	int[][] M7 = MultMatrizes(SubMatrizes(A12, A22), SomaMatrizes(B21, B22));
    	
    	
    	for(int i = 0; i < n/2; i++) {
    		for(int j = 0; j < n/2; j++) {
    			C11[i][j] = M1[i][j] + M4[i][j] - M5[i][j] + M7[i][j];
    			C12[i][j] = M3[i][j] + M5[i][j];
    			C21[i][j] = M2[i][j] + M4[i][j];
    			C22[i][j] = M1[i][j] - M2[i][j] + M3[i][j] + M6[i][j];
    			count++;
    		}
    	}
    	for(int i = 0; i < n/2; i++) {
    		for(int j = 0; j < n/2; j++) {
    			C[i][j] = C11[i][j];
    			C[i][j+n/2] = C12[i][j];
    			C[i + n/2][j] = C21[i][j];
    			C[i + n/2][j + n/2] = C22[i][j];
    			count++;
    		}
    	}
    	
    	return count++;
    }
    
    public int[][] SomaMatrizes(int[][] A, int[][] B) {
    	int n = A.length;
    	int[][] result = new int[n][n];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			result[i][j] = A[i][j] + B[i][j];
    			count++;
    		}
    	}
    	return result;
    }
    
    public int[][] SubMatrizes(int[][] A, int[][] B) {
    	int n = A.length;
    	int[][] result = new int[n][n];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			result[i][j] = A[i][j] - B[i][j];
    			count++;
    		}
    	}
    	return result;
    }
    
    
    public int[][] MultMatrizes(int[][] A, int[][] B) {
    	int n = A.length;
    	int[][] result = new int[n][n];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			for(int k = 0; k < n; k++) {
    				result[i][j] += A[i][k] * B[k][j];
    				count++;
    			}
    		}
    	}
    	return result;
    }
    
    public long multPadrao(int[][] A, int[][] B) {
    	int n = A.length;
    	int[][] result = new int[n][n];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			for(int k = 0; k < n; k++) {
    				result[i][j] += A[i][k] * B[k][j];
    				count++;
    			}
    		}
    	}
    	return count;
    }
    
    public static void main(String[] args) {
    	
    	/*System.out.println("Matriz Resultado:\n");
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			System.out.print("[" + C[i][j] + "]"); 
    		}
    		System.out.println();
    	}*/
    }
}