package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Controller.PrincipalController;
import Dto.AlunoDto;
import Entitys.Aluno;

public class PrincipalView extends JFrame {
	
	private JLabel lblNome, lblEnd, lblTel, lblCPF, lblSangue, lblFatorRH, lblCurso, lblContEmerg, lblTelEmerg, lblId;
	private JTextField txtNome, txtEnd, txtContEmerg, txtId;
	private JFormattedTextField txtTel, txtCPF, txtTelEmerg;
	private JButton btnInserir, btnUpdate, btnDelete, btnSearch;
	private JComboBox cmbSangue, cmbFatorRH, cmbCurso;
	private final String[] TiposCurso = {"Ciência da Computação", "Engenharia Civil", "Medicina", "Administração", "Direito", "Psicologia", "Economia", "Engenharia Elétrica", "Arquitetura", "Ciências Contábeis"};
	private final String[] TiposFatorRH = {"+", "-"};
	private final String[] TiposSangue = {"A", "B", "AB", "O"};
	private String Nome, End, Tel, CPF, Sangue, FatorRH, Curso, ContEmerg, TelEmerg, Id, msg;
	private JScrollPane sp;
	private JTextArea txtArea;
	
	private String heards[]= {"id" ,"Nome","End", "tel", "cpf", "tipoSangue", "curso", "contEmerg", "telEmerg"};
	
	private PrincipalController pController;
	
	private static final long serialVersionUID = 1L;
	
	public PrincipalView() {
		this.pController = new PrincipalController();
		
		setTitle("Exercico 2");
		setSize(900, 450);
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
			txtTel = new JFormattedTextField(new MaskFormatter("#######-####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtTel.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		txtTel.setBounds(190, 80, 200, 25);
		
		lblCPF = new JLabel("CPF");
		lblCPF.setBounds(0, 120, 100, 25);
		try {
			txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCPF.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
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
			txtTelEmerg = new JFormattedTextField(new MaskFormatter("#######-####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtTelEmerg.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		txtTelEmerg.setBounds(190, 280, 200, 25);
		
		lblId = new JLabel("ID");
		lblId.setBounds(385, 327, 200, 25);
		txtId = new JTextField();
		txtId.setBounds(400, 327, 27, 25);
	
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable();
		
		model.setColumnIdentifiers(heards);
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(480, 15, 393, 380);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(285, 325, 85, 30);
		
		btnInserir = new JButton("Insert");
		btnInserir.setBounds(185, 325, 85, 30);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(185, 365, 85, 30);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(285, 365, 85, 30);
		
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
		getContentPane().add(lblId);
		getContentPane().add(txtId);
		getContentPane().add(sp);
		getContentPane().add(btnUpdate);
		getContentPane().add(btnInserir);
		getContentPane().add(btnDelete);
		getContentPane().add(btnSearch);
		
		setVisible(true);
		
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Nome = txtNome.getText();
				End = txtEnd.getText();
				Tel = txtTel.getText();
				CPF = txtCPF.getText();
				Sangue = cmbSangue.getSelectedItem().toString();
				FatorRH = cmbFatorRH.getSelectedItem().toString();
				Curso = cmbCurso.getSelectedItem().toString();
				ContEmerg = txtContEmerg.getText();
				TelEmerg = txtTelEmerg.getText();
				msg = validate(Nome, End, Tel, CPF, Sangue, FatorRH, Curso, ContEmerg, TelEmerg);
				boxConfirm(msg);
				if(msg == null) {
					msg = pController.Cadastrar(new AlunoDto(Nome, End, Tel, CPF, Sangue, FatorRH, Curso, ContEmerg, TelEmerg));
					boxConfirm(msg);
					LimparDados();
				} else {
					System.out.println(msg);
				}
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Nome = txtNome.getText();
				End = txtEnd.getText();
				Tel = txtTel.getText();
				CPF = txtCPF.getText();
				Sangue = cmbSangue.getSelectedItem().toString();
				FatorRH = cmbFatorRH.getSelectedItem().toString();
				Curso = cmbCurso.getSelectedItem().toString();
				ContEmerg = txtContEmerg.getText();
				TelEmerg = txtTelEmerg.getText();
				Id = txtId.getText();
				msg = validate(Nome, End, Tel, CPF, Sangue, FatorRH, Curso, ContEmerg, TelEmerg);
				boxConfirm(msg);
				if(msg == null || !(Id.isEmpty())) {
					msg = pController.Update(new AlunoDto(Nome, End, Tel, CPF, Sangue, FatorRH, Curso, ContEmerg, TelEmerg, Id));
					boxConfirm(msg);
					LimparDados();
				} else {
					boxConfirm("Digite o ID para Continuar");
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Id = txtId.getText();
				if(!(Id.isEmpty())) {
					msg = pController.delete(Id);
					boxConfirm(msg);
				} else {
					boxConfirm("Digite o ID para Continuar");
				}
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Aluno> dados;
				try {
					dados = pController.search();
					dados.forEach(i -> model.addRow(new Object[] { String.valueOf(i.getId()), String.valueOf(i.getNome()), String.valueOf(i.getEnd()), String.valueOf(i.getTel()), String.valueOf(i.getCpf()), String.valueOf(i.getTipoSangue()), String.valueOf(i.getCurso()), String.valueOf(i.getContEmerg()), String.valueOf(i.getTelEmerg())}));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void LimparDados() {
		txtNome.setText(null);
		txtEnd.setText(null);
		txtTel.setText(null);
		txtCPF.setText(null);
		txtContEmerg.setText(null);
		txtTelEmerg.setText(null);
	}
	
	public String validate(String nome, String end, String tel, String cpf, String sangue, String fatorRH, String curso, String contEmerg, String telEmerg) {
		String msg = null;
		if(nome.isEmpty()) {
			msg = "Digite um Nome";
		} else if(end.isEmpty()) {
			msg = "Digite um Endereço";
		} else if(tel.replaceAll("", "").replaceAll("-", "").trim().isEmpty()) {
			msg = "Digite um Telefone";
		} else if(cpf.replaceAll("\\.", "").replaceAll("-", "").replaceAll("", "").trim().isEmpty()) {
			msg = "Digite um CPF";
		} else if(ContEmerg.isEmpty()) {
			msg = "Digite um Contato de emergencia";
		} else if(telEmerg.replaceAll("", "").replaceAll("-", "").trim().isEmpty()) {
			msg = "Digite um Telefone de Emergencia";
		}
		
		return msg;
	}
	
	public void boxConfirm(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
}