package questao1;

import java.util.List;

public class ShellSort {
	
    public int sort(List<Integer> arr) {
        int n = arr.size();
        int operationCount = 0; 
        
        int gap = 1;
        while (gap < n / 3) {
            gap = gap * 3 + 1; 
            operationCount++; 
        }
        
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int temp = arr.get(i);
                operationCount++; 
                int j = i;
                
                while (j >= gap && arr.get(j - gap) > temp) {
                    operationCount++; 
                    arr.set(j, arr.get(j - gap));
                    operationCount++;
                    j -= gap;
                    operationCount++;
                }
                
                if (j >= gap) {
                    operationCount++;
                }

                arr.set(j, temp);
                operationCount++;
            }
            
            gap = (gap - 1) / 3;
            operationCount++;
        }
        
       return operationCount;
    }
    
}
