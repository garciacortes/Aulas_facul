package View;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;

public class MenuView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JMenuItem menuItem2, menuItem;
	private JTextField textField, textField2;
	private JButton btnAdicionar, Desenhar, btnAdicionar_1;
	private JTextArea areaLog;
	private JPanel panel_1;
	private JButton ArvoreCrescente, SomaElemento, ContarElemento, MaiorElemento, ProcurarValor, RemocaoNo;
	private JButton MenorElemento, ImprimeFolha, ImprimeNaoFolha, Profundidade, Altura, ArvoreDecrescente;
	
	public JTextArea getAreaLog() {
		return areaLog;
	}

	public MenuView() {
		this.setBounds(235, 20, 950, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Opções"); 
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menuItem = new JMenuItem("Nova Arvore");
		menuItem2 = new JMenuItem("Sair");
		menu.add(menuItem);
		menu.add(menuItem2);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 5, 920, 623);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Adicionar Valor");
		label.setBounds(20, 15, 90, 26);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(130, 19, 86, 20);
		panel.add(textField);
		textField.setColumns(0);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(230, 17, 88, 22);
		panel.add(btnAdicionar);
		
		btnAdicionar_1 = new JButton("Adicionar Profundidade");
		btnAdicionar_1.setBounds(793, 25, 125, 22);
		panel.add(btnAdicionar_1);
		
	    areaLog = new JTextArea();
	    areaLog.setLineWrap(true);
	    areaLog.setDisabledTextColor(new Color(64, 0, 64));
	    areaLog.setBounds(500, 178, 410, 205);
		panel.add(areaLog);
		
		panel_1 = new JPanel();
		panel_1.setBounds(20, 52, 410, 331);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		Desenhar = new JButton("Desenhar");
		Desenhar.setBounds(330, 17, 100, 22);
		panel.add(Desenhar);
		
		ArvoreCrescente = new JButton("ArvoreCrescente");
		ArvoreCrescente.setBounds(500, 55, 132, 22);
		panel.add(ArvoreCrescente);
		ArvoreDecrescente = new JButton("ArvoreDecrescente");
		ArvoreDecrescente.setBounds(640, 55, 145, 22);
		panel.add(ArvoreDecrescente);
		ContarElemento = new JButton("ContarElemento");
		ContarElemento.setBounds(793, 55, 125, 22);
		panel.add(ContarElemento);
		SomaElemento = new JButton("SomarElemento");
		SomaElemento.setBounds(793, 85, 125, 22);
		panel.add(SomaElemento);
		MaiorElemento = new JButton("MaiorElemento");
		MaiorElemento.setBounds(640, 85, 145, 22);
		panel.add(MaiorElemento);
		MenorElemento = new JButton("MenorElemento");
		MenorElemento.setBounds(500, 85, 132, 22);
		panel.add(MenorElemento);
		ImprimeFolha = new JButton("ImprimeFolha");
		ImprimeFolha.setBounds(500, 115, 132, 22);
		panel.add(ImprimeFolha);
		ImprimeNaoFolha = new JButton("ImprimeNãoFolha");
		ImprimeNaoFolha.setBounds(640, 115, 145, 22);
		panel.add(ImprimeNaoFolha);
		Profundidade = new JButton("Profundidade");
		Profundidade.setBounds(793, 115, 125, 22);
		panel.add(Profundidade);
		Altura = new JButton("Altura");
		Altura.setBounds(500, 145, 132, 22);
		panel.add(Altura);
		RemocaoNo = new JButton("RemoçãoNo");
		RemocaoNo.setBounds(640, 145, 145, 22);
		panel.add(RemocaoNo);
		ProcurarValor = new JButton("ProcurarValor");
		ProcurarValor.setBounds(793, 145, 125, 22);
		panel.add(ProcurarValor);
		
		JLabel label2 = new JLabel("Insira o Valor");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setHorizontalTextPosition(SwingConstants.CENTER);
		label2.setBounds(505, 17, 100, 18);
		panel.add(label2);
		
		textField2 = new JTextField();
		textField2.setBounds(610, 17, 86, 20);
		panel.add(textField2);
		textField2.setColumns(0);
		
	}
	
	public JTextField getTextField2() {
		return textField2;
	}

	public void createLabel() {
	}
	
	public JPanel getPanel_1() {
		return panel_1;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void MenuSair(ActionListener actions) {
		menuItem2.addActionListener(actions);
	}
	
	public void Adicionar(ActionListener actions) {
		btnAdicionar.addActionListener(actions);
	}
	
	public void AdicionarProfundidade(ActionListener actions) {
		btnAdicionar_1.addActionListener(actions);
	}
	
	public void Desenhar(ActionListener actions) {
		Desenhar.addActionListener(actions);
	}
	
	public void ArvoreCrescente(ActionListener actions) {
		ArvoreCrescente.addActionListener(actions);
	}
	public void ArvoreDecrescente(ActionListener actions) {
		ArvoreDecrescente.addActionListener(actions);
	}
	public void ContarElemento(ActionListener actions) {
		ContarElemento.addActionListener(actions);
	}
	public void MenorElemento(ActionListener actions) {
		MenorElemento.addActionListener(actions);
	}
	public void MaiorElemento(ActionListener actions) {
		MaiorElemento.addActionListener(actions);
	}
	public void SomarElemento(ActionListener actions) {
		SomaElemento.addActionListener(actions);
	}
	public void ImprimeFolha(ActionListener actions) {
		ImprimeFolha.addActionListener(actions);
	}
	public void ImprimeNaoFolha(ActionListener actions) {
		ImprimeNaoFolha.addActionListener(actions);
	}
	public void Profundidade(ActionListener actions) {
		Profundidade.addActionListener(actions);
	}
	public void Altura(ActionListener actions) {
		Altura.addActionListener(actions);
	}
	public void RemocaoNo(ActionListener actions) {
		RemocaoNo.addActionListener(actions);
	}
	public void ProcurarValor(ActionListener actions) {
		ProcurarValor.addActionListener(actions);
	}
}
