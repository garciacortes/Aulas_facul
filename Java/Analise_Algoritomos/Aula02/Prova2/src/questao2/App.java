package questao2;

import java.awt.Dimension;
import java.math.BigInteger;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
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
		TorreHanoi th = new TorreHanoi();
		
		XYSeries series = new XYSeries("Complexidade: ");	
		for(int tamanho = 3; tamanho <= 100; tamanho++) {
			BigInteger quantidade = th.Recursivo(tamanho);
			series.add(tamanho, quantidade);
			System.out.println("Quantidade de Operacoes da torre Hanoi para N = " + tamanho + " -> " + quantidade);
		}
		new App(series);
		System.out.println("Grafico gerado com Sucesso");
	}
}