package View;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Exerc1 extends JFrame implements ActionListener{
	
	private JLabel lblNome, lblCPF, lblUser;
	private JTextField txtNome;
	private JFormattedTextField txtCPF;
	private JComboBox cmbUser;
	private JButton btnEnviar;
	private final String[] tipoUser = {"Administrador", "Geral"};
	private static final long serialVersionUID = 1L;
	private String Nome, Cargo, CPF;

	public Exerc1() {
		setSize(400, 300);
		setTitle("Tela Inicial");
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(0, 0, 100, 25);
		
		lblCPF = new JLabel("CPF");
		lblCPF.setBounds(0, 50, 100, 25);
		
		lblUser = new JLabel("Tipo de Usuario");
		lblUser.setBounds(0, 100, 100, 25);
		
		txtNome = new JTextField();
		txtNome.setBounds(120, 0, 200, 25);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(150, 150, 69, 30);
		
		try {
			txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCPF.setBounds(120, 50, 200, 25);
		
		cmbUser = new JComboBox(tipoUser);
		cmbUser.setBounds(120, 100, 200, 25);
		
		getContentPane().add(lblNome);
		getContentPane().add(txtNome);
		getContentPane().add(lblCPF);
		getContentPane().add(lblUser);
		getContentPane().add(txtCPF);
		getContentPane().add(cmbUser);
		getContentPane().add(btnEnviar);
		
		setVisible(false);
		
		btnEnviar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Enviar")) {
			Nome = txtNome.getText();
			CPF = txtCPF.getText();
			Cargo = cmbUser.getSelectedItem().toString();
			System.out.println("Nome: " + Nome + " | CPF: " + CPF + " | Cargo: " + Cargo);
		}
	}
}
