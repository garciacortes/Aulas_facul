package questao1;

import java.util.List;

public class SelectionSort {

    public int selectionSort(List<Integer> arr) {
        int n = arr.size();
        int operacoes = 0;

        for (int i = 0; i < n - 1; i++) {
            int menorIndex = i;

            // Encontra o menor elemento na parte não ordenada do vetor
            for (int j = i + 1; j < n; j++) {
                operacoes++;
                if (arr.get(j) < arr.get(menorIndex)) {
                    menorIndex = j;
                }
            }

            // Troca o menor elemento com o elemento na posição atual
            if (menorIndex != i) {
                int temp = arr.get(i);
                arr.set(i, arr.get(menorIndex));
                arr.set(menorIndex, temp);
            }
        }

        return operacoes;
    }

    public static void main(String[] args) {
        SelectionSort sorter = new SelectionSort();
        List<Integer> arr = List.of(64, 25, 12, 22, 11);

        // Ordena o vetor usando o Selection Sort e obtém o número de operações
        int operacoes = sorter.selectionSort(arr);

        // Imprime o vetor ordenado
        System.out.println("Vetor ordenado: " + arr);
        
        // Imprime o número de operações
        System.out.println("Número de operações: " + operacoes);
    }
}
