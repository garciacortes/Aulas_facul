package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Cadastro extends JPanel {
	
	private JLabel lblNome, lblTitle, lblSexo, lblUser, lblSenha;
	private JPanel pnLogin;
	private JTextField txtNome, txtUser;
	private JPasswordField txtSenha;
	private JComboBox cmbSexo;
	private JButton btnCadastrar, btnVoltar;
	private final String[] Sexos = {"Masculino", "Feminino", "Outro"};
	private String Nome, Sexo, User, Senha;
	
	private static final long serialVersionUID = 1L;

	public Cadastro(Dados dados) {
		setLayout(null);
		
		lblTitle = new JLabel("Cadastro");
		lblTitle.setBounds(120, 5, 100, 25);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(25, 40, 100 ,25);
		txtNome = new JTextField();
		txtNome.setBounds(85, 40, 150, 25);
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(25, 70, 100 ,25);
		cmbSexo = new JComboBox(Sexos);
		cmbSexo.setBounds(85, 70, 150, 25);
		
		lblUser = new JLabel("Usuario");
		lblUser.setBounds(25, 100, 100 ,25);
		txtUser = new JTextField();
		txtUser.setBounds(85, 100, 150, 25);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(25, 130, 100 ,25);
		txtSenha = new JPasswordField();
		txtSenha.setBounds(85, 130, 150, 25);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(75, 165, 91, 25);
		
		btnVoltar= new JButton("Voltar");
		btnVoltar.setBounds(175, 165, 70, 25);
		
		add(lblTitle);
		add(lblNome);
		add(lblSexo);
		add(lblUser);
		add(lblSenha);
		add(txtNome);
		add(txtSenha);
		add(txtUser);
		add(cmbSexo);
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
				Nome = txtNome.getText();
				Sexo = cmbSexo.getSelectedItem().toString();
				User = txtUser.getText();
				Senha = String.copyValueOf(txtSenha.getPassword());
				dados.setNome(Nome);
				dados.setSenha(Senha);
				dados.setSexo(Sexo);
				dados.setUser(User);
				System.out.println(dados.getUser());
			}
		});
	}

	public void setPnLogin(JPanel pnLogin) {
		this.pnLogin = pnLogin;
	}
}
