package questao1;

import java.util.List;

public class MergeSort {

    public int sort(List<Integer> arr) {
        if (arr.size() <= 1) {
            return 0; 
        }
        
        int operationCount = 0;
        
        int meio = arr.size() / 2;
        List<Integer> esquerda = arr.subList(0, meio);
        List<Integer> direita = arr.subList(meio, arr.size());
        
        operationCount += sort(esquerda);
        operationCount += sort(direita);
        
        operationCount += merge(arr, esquerda, direita);
        
        return operationCount;
    }

    private int merge(List<Integer> arr, List<Integer> esquerda, List<Integer> direita) {
        int i = 0, j = 0, k = 0;
        int operationCount = 0;
        
        while (i < esquerda.size() && j < direita.size()) {
            operationCount++;
            if (esquerda.get(i) <= direita.get(j)) {
                arr.set(k++, esquerda.get(i++));
            } else {
                arr.set(k++, direita.get(j++));
            }
        }
        
        while (i < esquerda.size()) {
            arr.set(k++, esquerda.get(i++));
        }
        
        while (j < direita.size()) {
            arr.set(k++, direita.get(j++));
        }
        
        return operationCount;
    }
}
