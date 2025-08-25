package view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Entitys.Pessoa;

public class RelatorioView extends JFrame {
	
	private Container ctn;
	private JPanel pnRelatorio, pnButton, pnRelatorioNovo, pnAux;
	private GridBagConstraints gbc, gdc;
	private JComboBox<String> cmb;
	private JButton btnFilter, btnResetar;
	private boolean pnAuxBol;
	
	private String[] opcoes = {"Maior Peso", "Menor Peso", "Maior Altura", "Menor Altura", "Maior Imc", "Menor Imc", "Media Pesos", "Media Altura", "Media Imc"};
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioView(ArrayList<Pessoa> dados) {
		setTitle("Relatorio");
		setSize(270,350);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		ctn = getContentPane();
		
		pnButton = new JPanel();
		pnButton.setBounds(0, 0, 250, 40);
		pnButton.setLayout(null);
		
		cmb = new JComboBox<String>(opcoes);
		cmb.setBounds(3, 3, 100, 20);
		btnFilter = new JButton("Filtrar");
		btnFilter.setBounds(110, 6, 67, 15);
		//btnResetar = new JButton("Reset");
		//btnResetar.setBounds(180, 6, 67, 15);
		
		GridBagLayout layout = new GridBagLayout();
		gbc =  new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(7, 0, 7, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		pnRelatorio = new JPanel();
		pnRelatorio.setLayout(layout);
		pnRelatorio.setBounds(5, 50, 200, 300);
		
		
		Map<String, String> label = new HashMap<String, String>();
		label.put("Maior Peso", MaiorPeso(dados.stream().max(Comparator.comparing(i -> i.getPeso())).orElseThrow()));
		label.put("Menor Peso", MenorPeso(dados.stream().min(Comparator.comparing(i -> i.getPeso())).orElseThrow()));
		label.put("Maior Altura", MaiorAltura(dados.stream().max(Comparator.comparing(i -> i.getAltura())).orElseThrow()));
		label.put("Menor Altura", MenorAltura(dados.stream().min(Comparator.comparing(i -> i.getAltura())).orElseThrow()));
		label.put("Maior Imc", MaiorImc(dados.stream().max(Comparator.comparing(i -> i.getImc())).orElseThrow()));
		label.put("Menor Imc", MenorImc(dados.stream().min(Comparator.comparing(i -> i.getImc())).orElseThrow()));
		label.put("MediaPeso", MediaPeso(dados.stream().mapToDouble(i -> i.getPeso()).average().orElseThrow()));
		label.put("Media Altura", MediaAltura(dados.stream().mapToDouble(i -> i.getAltura()).average().orElseThrow()));
		label.put("Media Imc", MediaImc(dados.stream().mapToDouble(i -> i.getImc()).average().orElseThrow()));
		
		label.forEach((i, k) -> {pnRelatorio.add(new JLabel(k), gbc); gbc.gridy++;});
		
		
		ctn.add(pnButton);
		ctn.add(pnRelatorio);
		
		pnButton.add(cmb);
		pnButton.add(btnFilter);
		//pnButton.add(btnResetar);
		
		setVisible(false);
		
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opFilter = cmb.getSelectedItem().toString();
				label.forEach((i, k) -> {
					if(i == opFilter) {
						if(!pnAuxBol) {
							ctn.remove(pnRelatorio);
						} else {
							ctn.remove(pnAux);
						}
						gdc =  new GridBagConstraints();
						gdc.fill = GridBagConstraints.HORIZONTAL;
						gdc.insets = new Insets(7, 0, 7, 0);
						gdc.gridx = 0;
						gdc.gridy = 0;
						
						pnRelatorioNovo = new JPanel();
						pnRelatorioNovo.setLayout(new GridBagLayout());
						pnRelatorioNovo.setBounds(5, 50, 200, 300);
						
						
						ctn.add(pnRelatorioNovo);
						pnRelatorioNovo.add(new JLabel(k), gdc);
						pnAux = pnRelatorioNovo;
						pnAuxBol = true;
						
					}
				});
				validate();
			}
		});
	}

	
	public String MaiorPeso(Pessoa p) {
		return "Maior Peso: " + p.getPeso() + " Nome: " + p.getNome();
	}
	
	public String MenorPeso(Pessoa p) {
		return "Menor Peso: " + p.getPeso() + " Nome: " + p.getNome();
	}
	
	public String MaiorAltura(Pessoa p) {
		return "Maior Altura: " + p.getAltura() + " Nome: " + p.getNome();
	}
	
	public String MenorAltura(Pessoa p) {
		return "Menor Altura: " + p.getAltura() + " Nome: " + p.getNome();
	}
	
	public String MaiorImc(Pessoa p) {
		return "Maior Imc: " + p.getImc() + " Nome: " + p.getNome();
	}
	
	public String MenorImc(Pessoa p) {
		return "Menor Imc: " + p.getImc() + " Nome: " + p.getNome();
	}
	
	public String MediaPeso(Double d) {
		return "A Media dos Pesos: " + d;
	}
	
	public String MediaAltura(Double d) {
		return "A Media das Alturas: " + d;
	}
	
	public String MediaImc(Double d) {
		return "A Media dos Imc: " + d;
	}
}
