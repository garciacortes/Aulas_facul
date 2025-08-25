package questao1;

import java.util.List;

public class QuickSort {

    public int sort(List<Integer> arr) {
        return quickSort(arr, 0, arr.size() - 1);
    }

    private int quickSort(List<Integer> arr, int inicio, int fim) {
    	int operationCount = 0;
        if (inicio < fim) {
            int pivoIndex = partition(arr, inicio, fim);
            operationCount += fim - inicio + 1;
            operationCount += quickSort(arr, inicio, pivoIndex - 1);
            operationCount += quickSort(arr, pivoIndex + 1, fim);
            operationCount++;
        }
        return operationCount;
    }

    private int partition(List<Integer> arr, int inicio, int fim) {
        int pivoIndex = (inicio + fim) / 2;
        int pivo = arr.get(pivoIndex);
        int i = inicio;
        int j = fim;

        while (i <= j) {
            while (arr.get(i) < pivo) {
                i++;
            }
            while (arr.get(j) > pivo) {
                j--;
            }
            if (i <= j) {
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
                i++;
                j--;
            }
        }
        return i;
    }
}
