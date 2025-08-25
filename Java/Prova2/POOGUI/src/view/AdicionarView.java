package view;

import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class AdicionarView {

	MenuView menu = new MenuView();
	
	JPanel adicionarPanel;
	JTextField nomeText, ruaText, numeroText, cidadeText, estadoText;
	JLabel nomelbl, telefonelbl, rualbl, numerolbl, cidadelbl, estadolbl;
	JFormattedTextField telefoneText;
	MaskFormatter fmt;
	JButton salvar, clear;

	public AdicionarView() {
		
		try {
			fmt = new MaskFormatter("## #########");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		adicionarPanel = new JPanel();
		nomeText = new JTextField();
		telefoneText = new JFormattedTextField(fmt);
		ruaText = new JTextField();
		numeroText = new JTextField();
		cidadeText = new JTextField();
		estadoText = new JTextField();
		nomelbl = new JLabel();
		telefonelbl = new JLabel();
		rualbl = new JLabel();
		numerolbl = new JLabel();
		cidadelbl = new JLabel();
		estadolbl = new JLabel();
		salvar = new JButton();
		clear = new JButton();
		
		
		adicionarPanel.setBounds(0, 0, 598, 416);
		adicionarPanel.setBackground(null);
		adicionarPanel.setVisible(false);
		adicionarPanel.setLayout(null);
		
		nomeText.setBounds(230, 60, 92, 20);
		nomelbl.setText("Nome:");
		nomelbl.setBounds(170, 60, 90, 20);
		
		telefoneText.setBounds(230, 90, 92, 20);
		telefonelbl.setText("Telefone:");
		telefonelbl.setBounds(170, 90, 90, 20);
		
		ruaText.setBounds(230, 125, 92, 20);
		rualbl.setText("Rua:");
		rualbl.setBounds(170, 125, 90, 20);
		
		numeroText.setBounds(230, 160, 92, 20);
		numerolbl.setText("Numero:");
		numerolbl.setBounds(170, 160, 90, 20);
		
		cidadeText.setBounds(230, 195, 92, 20);
		cidadelbl.setText("Cidade:");
		cidadelbl.setBounds(170, 195, 90, 20);
		
		estadoText.setBounds(230, 230, 92, 20);
		estadolbl.setText("Estado:");
		estadolbl.setBounds(170, 230, 90, 20);
		
		salvar.setBounds(165, 280, 70, 20);
		salvar.setText("Salvar");
		clear.setBounds(255, 280, 70, 20);
		clear.setText("Clear");
		
		
		adicionarPanel.add(menu.getMenuPanel());
		adicionarPanel.add(nomeText);
		adicionarPanel.add(nomelbl);
		adicionarPanel.add(telefonelbl);
		adicionarPanel.add(telefoneText);
		adicionarPanel.add(rualbl);
		adicionarPanel.add(ruaText);
		adicionarPanel.add(numerolbl);
		adicionarPanel.add(numeroText);
		adicionarPanel.add(cidadeText);
		adicionarPanel.add(cidadelbl);
		adicionarPanel.add(estadoText);
		adicionarPanel.add(estadolbl);
		adicionarPanel.add(salvar);
		adicionarPanel.add(clear);
	}
	
	public JPanel getAdicionarPanel() {
		return adicionarPanel;
	}

	public JTextField getNomeText() {
		return nomeText;
	}

	public void setNomeText(JTextField nomeText) {
		this.nomeText = nomeText;
	}

	public JTextField getRuaText() {
		return ruaText;
	}

	public void setRuaText(JTextField ruaText) {
		this.ruaText = ruaText;
	}

	public JTextField getNumeroText() {
		return numeroText;
	}

	public void setNumeroText(JTextField numeroText) {
		this.numeroText = numeroText;
	}

	public JTextField getCidadeText() {
		return cidadeText;
	}

	public void setCidadeText(JTextField cidadeText) {
		this.cidadeText = cidadeText;
	}

	public JTextField getEstadoText() {
		return estadoText;
	}

	public void setEstadoText(JTextField estadoText) {
		this.estadoText = estadoText;
	}

	public JFormattedTextField getTelefoneText() {
		return telefoneText;
	}

	public void setTelefoneText(JFormattedTextField telefoneText) {
		this.telefoneText = telefoneText;
	}

	public void Limpar(ActionListener Actions) {
		clear.addActionListener(Actions);
	}

	public void SalvarContato(ActionListener Actions) {
		salvar.addActionListener(Actions);
	}

}
