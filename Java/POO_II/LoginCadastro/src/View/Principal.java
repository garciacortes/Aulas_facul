package View;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Principal extends JFrame {
	
	private JLabel lblUser, lblSenha, lblDialog;
	private JTextField txtUser;
	private JPasswordField txtSenha;
	private JButton btnLogin, btnCadastro;
	private JPanel pnLogin;
	private Container ctn;
	private String User, Senha;
	private JDialog dlgAviso;
	private Usuario pnUser;
	private Dados dados;
	private Timer timer;
	
	private static final long serialVersionUID = 1L;
	
	public Principal(Cadastro pnCadastro, Dados dados, Usuario pnUser) {
		this.pnUser = pnUser;
		this.dados = dados;
		
		setTitle("Principal");
		setSize(320,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ctn = getContentPane();
				
		pnLogin = new JPanel();
		pnLogin.setLayout(null);
		
		lblUser = new JLabel("Usuario");
		lblUser.setBounds(60, 70, 100, 25);
		txtUser = new JTextField();
		txtUser.setBounds(110, 70, 125, 25);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(60, 110, 100, 25);
		txtSenha = new JPasswordField();
		txtSenha.setBounds(110, 110, 125, 25);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(70 ,150, 65, 25);
		
		btnCadastro = new JButton("Cadastro");
		btnCadastro.setBounds(140 ,150, 90, 25);
		
		dlgAviso = new JDialog();
		dlgAviso.setSize(250 ,100);
		dlgAviso.setLocationRelativeTo(pnLogin);
		dlgAviso.setLayout(null);
		
		lblDialog = new JLabel();
		lblDialog.setBounds(25, 15, 250, 25);
		dlgAviso.add(lblDialog);
		
		ctn.add(pnLogin);
		
		pnLogin.add(lblUser);
		pnLogin.add(lblSenha);
		pnLogin.add(txtUser);
		pnLogin.add(txtSenha);
		pnLogin.add(btnLogin);
		pnLogin.add(btnCadastro);
		
		setVisible(true);
		
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctn.add(pnCadastro);
				pnLogin.setVisible(false);
				pnCadastro.setVisible(true);
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User = txtUser.getText();
				Senha = String.copyValueOf(txtSenha.getPassword());
				
				if(dados.getUser().equals(User) && dados.getSenha().equals(Senha)) {
					timer = new Timer(1000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dlgAviso.setVisible(false);
							ctn.add(pnUser);
							pnLogin.setVisible(false);
							pnUser.setVisible(true);
						}
					});
					dlgAviso.setVisible(true);
					lblDialog.setText("Login Efetuado com sucesso");
					timer.start();
					dadosUser();
				} else {
					dlgAviso.setVisible(true);
					lblDialog.setText("Usuario ou senha Incorretos");
					timer = new Timer(1000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dlgAviso.setVisible(false);
						}
					});
					timer.start();
				}
			}
		});
	}
	
	public void dadosUser() {
		pnUser.getLblNomeShow().setText(dados.getNome());
		pnUser.getLblSexoShow().setText(dados.getSexo());
	}

	public JPanel getPnLogin() {
		return pnLogin;
	}
}
