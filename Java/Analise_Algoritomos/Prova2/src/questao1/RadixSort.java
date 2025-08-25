package questao1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RadixSort {
	
	private List<List<Integer>> output = new ArrayList<List<Integer>>();
	private List<Integer> outputOrdened = new ArrayList<Integer>();
	private int count; 
	
	public RadixSort() {
		IntStream.range(0, 10).forEach(i -> {
			output.add(new ArrayList<Integer>());
			count++;
		});
	}
	
	int radixSort(List<Integer> arr) {
		Integer max = arr.stream().max(Integer::compare).get();
		count++;
		for(int i = 1; (max/i) > 0; i*=10) {
			radixAux(arr, i);
			arr = this.outputOrdened;
		}
		
		return this.count;
	}
	
	void radixAux(List<Integer> arr, int exp) {
		
		output.forEach(List::clear);
		
		arr.stream().forEach(e -> {
			output.get((e/exp)%10).add(e);
			count++;
		});
		
		outputOrdened.clear();
		output.stream().flatMap(List::stream).forEach(c -> {
			outputOrdened.add(c);
			count++;
		});
	}
}
