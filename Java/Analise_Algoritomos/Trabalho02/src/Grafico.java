import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafico extends JFrame {
	
	public Grafico(XYSeries series, XYSeries series2) {
		super("Complexidade Algoritimo");
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
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
		Questao01 q1 = new Questao01();
		
		Grafico objGrafico;
		
		XYSeries series = new XYSeries("Complexidade: ");
		XYSeries series2 = new XYSeries("ComplexidadeReduzida: ");
		for(int tamanho1 = 128; tamanho1 < 2048; tamanho1 *= 2) {
			int[][] matriz = new int[tamanho1][tamanho1];
			
			for(int i = 0; i < tamanho1; i++) {
				for(int j = 0; j < tamanho1; j++) {
					matriz[i][j] = 1;
				}
			}
			
			long quantidade1 = q1.multPadrao(matriz, matriz);
			series.add(tamanho1, quantidade1);
			System.out.println("Quantidade de Operacoes do algoritimo Padrão: " + quantidade1);
			
		}
		
		for(int tamanho2 = 128; tamanho2 <= 2048; tamanho2 *= 2) {
			int[][] matriz = new int[tamanho2][tamanho2];
			
			for(int i = 0; i < tamanho2; i++) {
				for(int j = 0; j < tamanho2; j++) {
					matriz[i][j] = 1;
				}
			}
			
			long quantidade2 = q1.Coppersmith_Winograd(matriz, matriz, tamanho2);
			series2.add(tamanho2, quantidade2);
			System.out.println("Quantidade de Operacoes do algoritimo Coppersmith_Winograd: " + quantidade2);
			
		}
		objGrafico = new Grafico(series, series2);
		System.out.println("Grafico gerado com Sucesso");
	}
}
