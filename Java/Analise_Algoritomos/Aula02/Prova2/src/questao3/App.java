package questao3;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class App extends JFrame {
	
	public App(XYSeries series) {
		super("Complexidade Algoritimo");
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
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
		Alfabeto alfa = new Alfabeto();
		
		XYSeries series = new XYSeries("Complexidade: ");
		List<Character> arr = new ArrayList<Character>();
		int i;
		for(i = 1; i < 10; i++) {
			for(int j = 0; j < i*1; j++) {
				arr.add('A');
			}
			for(int j = 0; j < i*2; j++) {
				arr.add('B');
			}
			for(int j = 0; j < i*3; j++) {
				arr.add('C');
			}
		}
		int tamanho = i*1+i*2+i*3;
		long quantidade = alfa.Verificar(arr);
		series.add(tamanho, quantidade);
		System.out.println("Quantidade de Operacoes da torre Hanoi para N = " + tamanho + " -> " + quantidade);
		new App(series);
		System.out.println("Grafico gerado com Sucesso");
	}
}