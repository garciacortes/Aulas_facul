package questao1;

import java.util.List;

public class InsertionSort {

	public int insertionSort(List<Integer> arr) {
		int n = arr.size();
		int count = 0;
		
		for(int i = 1; i < n; i++) {
			int atual = arr.get(i);
			int j;
			count++;
			for(j = i-1; j >= 0 && arr.get(j) > atual; j--) {
				arr.set(j+1, arr.get(j));
				count+=2;
			}
			arr.set(j+1, atual);
			count++;
		}
		
		return count;
	}
}
