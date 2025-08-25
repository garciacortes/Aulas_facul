package questao1;

import java.util.List;

public class HeapSort {
	
	private int count;
	
	public HeapSort() {
		this.count = 0;
	}
	
	public int heapSort(List<Integer> arr) {
		int n = arr.size();
		
		for(int i = n / 2 -1; i >= 0; i--) {
			heapify(arr, n, i);
		}
		
		for(int i = n -1; i > 0; i--) {
			troca(arr, 0, i);
			count++;
			heapify(arr, i, 0);
		}
		
		return count;
	}

	private void heapify(List<Integer> arr, int n, int i) {
		int maior = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		
		if(left < n && arr.get(left) > arr.get(maior)) {
			count++;
			maior = left;
		}
		if(right < n && arr.get(right) > arr.get(maior)) {
			count++;
			maior = right;
		}
		if(maior != i) {
			troca(arr, i, maior);
			count++;
			heapify(arr, n, maior);
		}
		
	}

	private void troca(List<Integer> arr, int i, int maior) {
		int temp = arr.get(i);
		arr.set(i, arr.get(maior));
		arr.set(maior, temp);
		
	}
}
