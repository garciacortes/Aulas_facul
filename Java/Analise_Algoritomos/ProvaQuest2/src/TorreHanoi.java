import java.util.Stack;

public class TorreHanoi {
	
    public TorreHanoi() {
    }
    
    public void Iterativo(int n, char origem, char destino, char auxiliar) {
    	Stack<Integer> torre1 = new Stack<>();
        Stack<Integer> torre2 = new Stack<>();
        Stack<Integer> torre3 = new Stack<>();

        for (int i = n; i > 0; i--) {
            torre1.push(i);
        }

        char temp;

        if (n % 2 == 0) {
            temp = destino;
            destino = auxiliar;
            auxiliar = temp;
        }

        for (int i = 1; i <= Math.pow(2, n) - 1; i++) {
            if (i % 3 == 1) {
                moverDiscoI(torre1, torre3, origem, destino);
            } else if (i % 3 == 2) {
                moverDiscoI(torre1, torre2, origem, auxiliar);
            } else if (i % 3 == 0) {
                moverDiscoI(torre2, torre3, auxiliar, destino);
            }
        }
    }

    public static void moverDiscoI(Stack<Integer> origem, Stack<Integer> destino, char torreOrigem, char torreDestino) {
        if (origem.isEmpty() && destino.isEmpty()) {
            System.out.println("Movimento inválido!");
            return;
        }

        if (origem.isEmpty()) {
            origem.push(destino.pop());
            System.out.println(torreDestino + " -> " + torreOrigem);
        } else if (destino.isEmpty()) {
            destino.push(origem.pop());
            System.out.println(torreOrigem + " -> " + torreDestino);
        } else if (origem.peek() > destino.peek()) {
            origem.push(destino.pop());
            System.out.println(torreDestino + " -> " + torreOrigem);
        } else {
            destino.push(origem.pop());
            System.out.println(torreOrigem + " -> " + torreDestino);
        }
    }
    
    public void Recursivo(int n, char origem, char destino, char auxiliar) {
    	if(n == 1) {
    		MoveDiscoR(origem, destino);
    	} else {
    		Recursivo(n - 1, origem, auxiliar, destino);
    		MoveDiscoR(origem, destino);
    		Recursivo(n - 1, auxiliar, destino, origem);
    	}
    }
    
    public void MoveDiscoR(char origem, char destino) {
    	System.out.println(origem + " -> " + destino);
    }
}
