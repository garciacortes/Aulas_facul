package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Cadastro extends JPanel {
	
	private JLabel lblTitle, lblUser, lblSenha;
	private JPanel pnLogin;
	private JTextField txtUser;
	private JPasswordField txtSenha;
	private JButton btnCadastrar, btnVoltar;
	private String User, Senha;
	
	private static final long serialVersionUID = 1L;

	public Cadastro() {
		setLayout(null);
		
		lblTitle = new JLabel("Cadastro");
		lblTitle.setBounds(135, 15, 100, 25);
		
		lblUser = new JLabel("Usuario");
		lblUser.setBounds(60, 70, 100, 25);
		txtUser = new JTextField();
		txtUser.setBounds(110, 70, 125, 25);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(60, 110, 100, 25);
		txtSenha = new JPasswordField();
		txtSenha.setBounds(110, 110, 125, 25);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(75, 150, 91, 25);
		
		btnVoltar= new JButton("Voltar");
		btnVoltar.setBounds(175, 150, 70, 25);
		
		add(lblTitle);
		add(lblUser);
		add(lblSenha);
		add(txtUser);
		add(txtSenha);
		add(btnCadastrar);
		add(btnVoltar);
		
		setVisible(false);
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				pnLogin.setVisible(true);
			}
		});
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	public void setPnLogin(JPanel pnLogin) {
		this.pnLogin = pnLogin;
	}
}