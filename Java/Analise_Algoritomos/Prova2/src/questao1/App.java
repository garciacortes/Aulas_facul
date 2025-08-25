package questao1;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class App extends JFrame {
	
	public App(XYSeries series1, XYSeries series2) {
		super("Complexidade Algoritimo");
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Grafico de Linha de Exemplo",
				"x",
				"Y",
				dataset
		);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 600));
		setContentPane(chartPanel);
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		BubbleSort bubble = new BubbleSort();
		SelectionSort selection = new SelectionSort();
		InsertionSort insertion = new InsertionSort();
		QuickSort quick = new QuickSort();
		MergeSort merge = new MergeSort();
		RadixSort radix = new RadixSort();	
		HeapSort heap = new HeapSort();
		ShellSort shell = new ShellSort();
		List<Integer> arr = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha o Algoritimo de ordenacao: \n"
				+ "1 - BubbleSort\n2 - SelectionSort\n3 - InsertionSort\n4 - QuickSort\n5 - MergeSort\n6 - RadixSort\n7 - HeapSort\n8 - ShellSort");
		System.out.println("Digite a Opcao: ");
		int opcao = sc.nextInt();
		int Quantidade = 0;
		XYSeries series1 = new XYSeries("Complexidade Ordenada: ");
		XYSeries series2 = new XYSeries("Complexidade Decrescente: ");
		int n = 10000000;
		for(int i = 1; i < n; i++) {
			arr.clear();
			for(int j = 0; j < i; j++) {
				arr.add(j);
			}
			switch (opcao) {
			case 1:
				Quantidade = bubble.bubbleSort(arr);
				break;
			case 2:
				Quantidade = selection.selectionSort(arr);
				break;
			case 3:
				Quantidade = insertion.insertionSort(arr);
				break;
			case 4:
				Quantidade = quick.sort(arr);
				break;
			case 5:
				Quantidade = merge.sort(arr);
				break;
			case 6:
				Quantidade = radix.radixSort(arr);
				break;
			case 7:
				Quantidade = heap.heapSort(arr);
				break;
			case 8:
				Quantidade = shell.sort(arr);
				break;
			default:
				break;
			}
			series1.add(i, Quantidade);
			System.out.println("Quantidade de operacoes para N = " + i + " -> " + Quantidade);
		}
		
		Quantidade = 0;
		for(int i = 1; i < n; i++) {
			arr.clear();
			for(int j = i; j > 0; j--) {
				arr.add(j);
			}
			switch (opcao) {
			case 1:
				Quantidade = bubble.bubbleSort(arr);
				break;
			case 2:
				Quantidade = selection.selectionSort(arr);
				break;
			case 3:
				Quantidade = insertion.insertionSort(arr);
				break;
			case 4:
				Quantidade = quick.sort(arr);
				break;
			case 5:
				Quantidade = merge.sort(arr);
				break;
			case 6:
				Quantidade = radix.radixSort(arr);
				break;
			case 7:
				Quantidade = heap.heapSort(arr);
				break;
			case 8:
				Quantidade = shell.sort(arr);
				break;
			default:
				break;
			}
			series2.add(i, Quantidade);
			System.out.println("Quantidade de operacoes para N = " + i + " -> " + Quantidade);
		}
		System.out.println("Grafico gerado com Sucesso");
		new App(series1, series2);
	}
	
}
