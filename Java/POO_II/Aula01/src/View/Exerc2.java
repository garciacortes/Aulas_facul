package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Exerc2 extends JFrame implements ActionListener {
	
	private JLabel lblNome, lblEnd, lblTel, lblCPF, lblSangue, lblFatorRH, lblCurso, lblContEmerg, lblTelEmerg;
	private JTextField txtNome, txtEnd, txtContEmerg;
	private JFormattedTextField txtTel, txtCPF, txtTelEmerg;
	private JButton btnInserir, btnCancel;
	private JComboBox cmbSangue, cmbFatorRH, cmbCurso;
	private final String[] TiposCurso = {"Ciência da Computação", "Engenharia Civil", "Medicina", "Administração", "Direito", "Psicologia", "Economia", "Engenharia Elétrica", "Arquitetura", "Ciências Contábeis"};
	private final String[] TiposFatorRH = {"+", "-"};
	private final String[] TiposSangue = {"A", "B", "AB", "O"};
	private String Nome, End, Tel, CPF, Sangue, FatorRH, Curso, ContEmerg, TelEmerg;
	
	private static final long serialVersionUID = 1L;
	
	public Exerc2() {
		setTitle("Exercico 2");
		setSize(500, 450);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(0, 0, 100, 25);
		txtNome = new JTextField();
		txtNome.setBounds(190, 0, 200, 25);

		lblEnd = new JLabel("Endereço");
		lblEnd.setBounds(0, 40, 100, 25);
		txtEnd = new JTextField();
		txtEnd.setBounds(190, 40, 200, 25);
		
		lblTel = new JLabel("Telefone"); 
		lblTel.setBounds(0, 80, 100, 25);
		try {
			txtTel = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtTel.setBounds(190, 80, 200, 25);
		
		lblCPF = new JLabel("CPF");
		lblCPF.setBounds(0, 120, 100, 25);
		try {
			txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCPF.setBounds(190, 120, 200, 25);
		
		lblSangue = new JLabel("Tipo de Sangue");
		lblSangue.setBounds(0, 160, 100, 25);
		cmbSangue = new JComboBox(TiposSangue);
		cmbSangue.setBounds(190, 160, 50, 25);
		
		lblFatorRH = new JLabel("Fator RH");
		lblFatorRH.setBounds(270, 160, 100, 25);
		cmbFatorRH = new JComboBox(TiposFatorRH);
		cmbFatorRH.setBounds(330, 160, 40, 25);
		
		lblCurso = new JLabel("Curso");
		lblCurso.setBounds(0, 200, 100, 25);
		cmbCurso = new JComboBox(TiposCurso);
		cmbCurso.setBounds(190, 200, 200, 25);
		
		lblContEmerg = new JLabel("Contato de Emergência");
		lblContEmerg.setBounds(0, 240, 135, 25);
		txtContEmerg = new JTextField();
		txtContEmerg.setBounds(190, 240, 200, 25);
		
		lblTelEmerg = new JLabel("Telefone Emergencia");
		lblTelEmerg.setBounds(0, 280, 130, 25);
		try {
			txtTelEmerg = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtTelEmerg.setBounds(190, 280, 200, 25);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(285, 325, 85, 30);
		
		btnInserir = new JButton("Inserir");
		btnInserir.setBounds(185, 325, 85, 30);
		
		btnCancel.addActionListener(this);
		btnInserir.addActionListener(this);
		
		getContentPane().add(lblNome);
		getContentPane().add(lblEnd);
		getContentPane().add(lblTel);
		getContentPane().add(lblCPF);
		getContentPane().add(lblSangue);
		getContentPane().add(lblFatorRH);
		getContentPane().add(lblCurso);
		getContentPane().add(lblContEmerg);
		getContentPane().add(lblTelEmerg);
		getContentPane().add(txtNome);
		getContentPane().add(txtEnd);
		getContentPane().add(txtTel);
		getContentPane().add(txtContEmerg);
		getContentPane().add(txtTelEmerg);
		getContentPane().add(txtCPF);
		getContentPane().add(cmbSangue);
		getContentPane().add(cmbFatorRH);
		getContentPane().add(cmbCurso);
		getContentPane().add(btnCancel);
		getContentPane().add(btnInserir);
		
		setVisible(true);
	}
	
	public void LimparDados() {
		txtNome.setText(null);
		txtEnd.setText(null);
		txtTel.setText(null);
		txtCPF.setText(null);
		txtContEmerg.setText(null);
		txtTelEmerg.setText(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancelar")) {
			System.exit(0);
			
		} else if(e.getActionCommand().equals("Inserir")) {
			Nome = txtNome.getText();
			End = txtEnd.getText();
			Tel = txtTel.getText();
			CPF = txtCPF.getText();
			Sangue = cmbSangue.getSelectedItem().toString();
			FatorRH = cmbFatorRH.getSelectedItem().toString();
			Curso = cmbCurso.getSelectedItem().toString();
			ContEmerg = txtContEmerg.getText();
			TelEmerg = txtTelEmerg.getText();
			
			System.out.println("------------Dados que seram Inseridos------------");
			System.out.println("Nome: " + Nome);
			System.out.println("Endereço: " + End);
			System.out.println("Telefone: " + Tel);
			System.out.println("CPF: " + CPF);
			System.out.println("Tipo de sangue com Fator RH: " + Sangue + FatorRH);
			System.out.println("Curso: " + Curso);
			System.out.println("Contato de Emergencia: " + ContEmerg);
			System.out.println("Telefone de Emergencia: "  + TelEmerg);
			System.out.println("-------------------------------------------------");
			
			LimparDados();
		}
		
	}

}
