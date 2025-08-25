package questao1;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort {

	public int bubbleSort(List<Integer> arr) {
		int n = arr.size();
		int count = 0;
		boolean troca;
		
		for(int i = 1; i < n; i++) {
			count++;
			troca = false;
			for(int j = 0; j < n-1; j++) {
				count++;
				if(arr.get(j) > arr.get(j+1)) {
					int aux = arr.get(j);
					arr.set(j, arr.get(j+1));
					arr.set(j+1, aux);
					troca = true;
				}
			}
			if(!troca) {
				break;
			}
		}
		return count;
	}
}
