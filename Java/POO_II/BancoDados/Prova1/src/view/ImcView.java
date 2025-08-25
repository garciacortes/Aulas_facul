package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.PrincipalController;
import Dto.PessoaDto;
import Entitys.Pessoa;

public class ImcView extends JFrame{
	
	private Container ctn;
	private JPanel pn;
	private JLabel lblNome, lblPeso, lblAltura, lblLog, lblId, lblTitle;
	private JTextField txtNome, txtPeso, txtAltura, txtId;
	private JButton btnCalcular, btnCadastrar, btnAlterar, btnRemover, btnListar, btnRelatorio;
	
	PrincipalController pController;
	ListagemView listagemView;
	RelatorioView relatorioView;
	
	private static final long serialVersionUID = 1L;

	public ImcView() throws IOException {
		this.pController = new PrincipalController();
		this.listagemView = new ListagemView();
		
		setTitle("Calculadora Imc");
		setSize(400,350);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ctn = getContentPane();
		
		pn = new JPanel();
		pn.setLayout(null);
		
		lblTitle = new JLabel("Calculadora de IMC");
		lblTitle.setBounds(130, 15, 200, 25);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 15));
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(40, 50, 50, 25);
		txtNome = new JTextField();
		txtNome.setBounds(80, 50, 200, 25);
		
		lblPeso = new JLabel("Peso");
		lblPeso.setBounds(40, 90, 50, 25);
		txtPeso = new JTextField();
		txtPeso.setBounds(80, 90, 200, 25);
		
		lblAltura = new JLabel("Altura");
		lblAltura.setBounds(40, 130, 50, 25);
		txtAltura = new JTextField();
		txtAltura.setBounds(80, 130, 200, 25);
		
		lblLog = new JLabel("Retorno Do Metodo");
		lblLog.setBounds(100, 165, 200, 25);
		lblLog.setVisible(false);
		
		lblId = new JLabel("ID");
		lblId.setBounds(317, 217, 50, 25);
		txtId = new JTextField();
		txtId.setBounds(330, 217, 25, 25);
		
		btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(25, 200, 85, 25);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(115, 200, 100, 25);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(223, 200, 85, 25);
		
		btnRemover = new JButton("Remover");
		btnRemover.setBounds(223, 235, 85, 25);
		
		btnListar = new JButton("Listagem");
		btnListar.setBounds(115, 235, 100, 25);
		
		btnRelatorio = new JButton("Relatorio");
		btnRelatorio.setBounds(25, 235, 85, 25);
		
		ctn.add(pn);
		
		pn.add(lblTitle);
		pn.add(lblNome);
		pn.add(txtNome);
		pn.add(lblPeso);
		pn.add(txtPeso);
		pn.add(lblAltura);
		pn.add(txtAltura);
		pn.add(lblLog);
		pn.add(lblId);
		pn.add(txtId);
		pn.add(btnCalcular);
		pn.add(btnCadastrar);
		pn.add(btnAlterar);
		pn.add(btnRemover);
		pn.add(btnListar);
		pn.add(btnRelatorio);
		
		setVisible(true);
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText();
				String peso = txtPeso.getText();
				String altura = txtAltura.getText();
				String msg = Validate(nome, peso, altura);
				if(msg == null) {
					msg = pController.Cadastrar(new PessoaDto(nome, peso, altura));
					setLog(msg);
				} else {
					setLog(msg);
				}
			}
		});
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText();
				String nome = txtNome.getText();
				String peso = txtPeso.getText();
				String altura = txtAltura.getText();
				String msg = Validate(nome, peso, altura);
				if(msg == null && (!(id.isEmpty()) && id.matches("[0-9]+"))) {
					msg = pController.ALterar(new PessoaDto(nome, peso, altura, id));
					setLog(msg);
				}  else if(!(msg == null)) {
					setLog(msg);
				} else {
					setLog("Digite o ID");
				}
			}
		});
		
		btnCalcular.addActionListener(new  ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String peso = txtPeso.getText();
				String altura = txtAltura.getText();
				Pattern p = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+$");
				Matcher m = p.matcher(peso);
				Matcher m1 = p.matcher(altura);
				String msg = (peso.isEmpty() || !m.matches()) ? "Digite o Peso" : (altura.isEmpty() || !m1.matches()) ? "Digite a Altura" : null;
				if(msg == null) {
					msg = pController.Calcular(new PessoaDto(peso, altura));
					setLog(msg);
				} else {
					setLog(msg);
				}
			}
		});
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText();
				if(!id.isEmpty() && id.matches("[0-9]+")) {
					String msg = pController.Remover(id);
					setLog(msg);
				} else {
					setLog("Digite o ID");
				}
			}
		});
		
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Pessoa> dados = new ArrayList<Pessoa>();
				dados = pController.Listar();
				listagemView.setVisible(true);
				DefaultTableModel model = listagemView.getModel();
				dados.forEach(i -> model.addRow(new Object[] {i.getId(), i.getNome(), i.getPeso(), i.getAltura(), i.getImc()}));
			}
		});
		
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Pessoa> dados = new ArrayList<Pessoa>();
				dados = pController.Listar();
				relatorioView = new RelatorioView(dados);
				relatorioView.setVisible(true);
			}
		});
	}
	
	public void setLog(String msg) {
		this.lblLog.setText(msg);
		this.lblLog.setVisible(true);
	}
	
	public String Validate(String nome, String peso, String altura) {
		Pattern p = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+$");
		Matcher m = p.matcher(peso);
		Matcher m1 = p.matcher(altura);
		if(nome.isEmpty()) {
			return "Digite um Nome";
		} else if(peso.isEmpty() || !m.matches()) {
			return "Digite o Peso";
		} else if(altura.isEmpty() || !m1.matches()) {
			return "Digite uma Altura";
		} else {
			return null;
		}
	}
}
