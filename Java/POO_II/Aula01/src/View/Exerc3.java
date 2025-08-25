package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

public class Exerc3 extends JFrame{
	
	private JLabel lblCep, lblCnpj, lblCod;
	private JFormattedTextField txtCep, txtCnpj, txtCod;
	private JButton btnEnviar;
	
	private static final long serialVersionUID = 1L;
	
	public Exerc3() {
		setTitle("Exercicio 3");
		setSize(350, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		lblCep = new JLabel("CEP");
		lblCep.setBounds(0, 0, 125, 25);
		try {
			txtCep = new JFormattedTextField(new MaskFormatter("#####-###"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCep.setBounds(150, 0, 150, 25);
		
		lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(0, 40, 125, 25);
		try {
			txtCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCnpj.setBounds(150, 40, 150, 25);
		
		lblCod = new JLabel("Codigo do Fornecedor");
		lblCod.setBounds(0, 80, 125, 25);
		try {
			txtCod = new JFormattedTextField(new MaskFormatter("UU-#####L"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtCod.setBounds(150, 80, 150, 25);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(125, 120, 75, 25);
		
		getContentPane().add(lblCep);
		getContentPane().add(lblCnpj);
		getContentPane().add(lblCod);
		getContentPane().add(txtCep);
		getContentPane().add(txtCnpj);
		getContentPane().add(txtCod);
		getContentPane().add(btnEnviar);
		
		setVisible(true);
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("CEP: " + txtCep.getText());				
				System.out.println("CNPJ: " + txtCnpj.getText());				
				System.out.println("Codigo do Fornecedor " + txtCod.getText());				
			}
		});
	}
}
