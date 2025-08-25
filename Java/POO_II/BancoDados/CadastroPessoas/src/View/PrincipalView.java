package View;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Controller.PrincipalController;
import Dto.PessoaDto;

public class PrincipalView extends JFrame {
	
	private JLabel lblNome, lblEnd, lblTel, lblId, lblLog;
	private JTextField txtNome, txtEnd;
	private JFormattedTextField txtfTel, txtfId;
	private JButton btnInsert, btnSearch, btnUpdate, btnRemove;
	private Container ctn;
	
	private static final long serialVersionUID = 1L;
	
	private PrincipalController pController;
	
	public PrincipalView() {
		this.pController = new PrincipalController();
		
		setSize(900, 390);
		setTitle("Ações CRUD com cadastro de Usuario");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		
		ctn = getContentPane();
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 40, 80, 20);
		txtNome = new JTextField();
		txtNome.setBounds(100, 40, 200, 25);
		
		lblEnd = new JLabel("Endereco");
		lblEnd.setBounds(10, 85, 100, 20);
		txtEnd = new JTextField();
		txtEnd.setBounds(100, 85, 200, 25);
		
		lblTel = new JLabel("Telefone");
		lblTel.setBounds(10, 130, 100, 20);
		try {
			txtfTel = new JFormattedTextField(new MaskFormatter("#########"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtfTel.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		txtfTel.setBounds(100 ,130, 200 ,25);
		
		lblLog = new JLabel("----");
		lblLog.setBounds(50, 170, 250 ,20);
		
		lblId = new JLabel("Id");
		lblId.setBounds(220, 285, 30 ,30);
		try {
			txtfId = new JFormattedTextField(new MaskFormatter("##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtfId.setBounds(240, 285, 30, 30);
		
		btnInsert = new JButton("Inserir");
		btnInsert.setBounds(10, 215, 90, 40);
		btnRemove = new JButton("Remover");
		btnRemove.setBounds(102, 280, 90, 40);
		btnSearch = new JButton("Procurar");
		btnSearch.setBounds(102, 215, 90 ,40);
		btnUpdate = new JButton("Alterar");
		btnUpdate.setBounds(10, 280, 90, 40);
		
		
		ctn.add(lblNome);
		ctn.add(txtNome);
		ctn.add(lblEnd);
		ctn.add(txtEnd);
		ctn.add(lblTel);
		ctn.add(txtfTel);
		ctn.add(lblLog);
		ctn.add(lblId);
		ctn.add(txtfId);
		ctn.add(btnInsert);
		ctn.add(btnRemove);
		ctn.add(btnSearch);
		ctn.add(btnUpdate);
		
		setVisible(true);
		
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pController = new PrincipalController();
				String nome = txtNome.getText();
				String end = txtEnd.getText(); 
				String tel = txtfTel.getText();
				lblLog.setText("");
				String validacao = validate(nome, end, tel);
				if(validacao == null) {
					String msg = pController.Cadastrar(new PessoaDto(nome, end, tel));
					lblLog.setText(msg);
					txtNome.setText(null);
					txtEnd.setText(null);
					txtfTel.setText(null);
				} else {
					lblLog.setText(validacao);
				}
			}
		});
	}
	
	public String validate(String nome, String end, String tel) {
		String msg = null;
		if(nome.isEmpty()) {
			msg = "Digite um Nome";
		} else if(end.isEmpty()) {
			msg = "Digite um Endereço";
		} else if(tel.replaceAll("", "").trim().isEmpty()) {
			msg = "Digite um Telefone";
		}
		
		return msg;
	}
}




