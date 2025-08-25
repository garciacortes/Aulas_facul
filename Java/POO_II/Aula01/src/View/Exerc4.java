package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Exerc4 extends JFrame {
	
	private JLabel lblNum1, lblNum2, lblPeso1, lblPeso2, lblMediaPond;
	private JTextField txtNum1, txtNum2, txtPeso1, txtPeso2;
	private JButton btnCalcular;
	private double num1, peso1, num2, peso2, mediaPond;
	

	private static final long serialVersionUID = 1L;
	
	public Exerc4() {
		setTitle("Exercico 4");
		setSize(400,300);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblNum1 = new JLabel("Numero 1");
		lblNum1.setBounds(10, 25, 100, 25);
		txtNum1 = new JTextField();
		txtNum1.setBounds(75, 25, 100, 25);
		
		lblNum2 = new JLabel("Numero 2");
		lblNum2.setBounds(10, 65, 100, 25);
		txtNum2 = new JTextField();
		txtNum2.setBounds(75, 65, 100, 25);
		
		lblPeso1 = new JLabel("Peso 1");
		lblPeso1.setBounds(210, 25, 100, 25);
		txtPeso1 = new JTextField();
		txtPeso1.setBounds(260, 25, 100, 25);
		
		lblPeso2 = new JLabel("Peso 2");
		lblPeso2.setBounds(210, 65, 100, 25);
		txtPeso2 = new JTextField();
		txtPeso2.setBounds(260, 65, 100, 25);
		
		btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(150, 125, 90, 25);
		
		lblMediaPond = new JLabel("A Media Ponderada é: --");
		lblMediaPond.setBounds(10, 180, 150, 25);
		
		getContentPane().add(lblNum1);
		getContentPane().add(txtNum1);
		getContentPane().add(lblNum2);
		getContentPane().add(txtNum2);
		getContentPane().add(lblPeso1);
		getContentPane().add(lblPeso2);
		getContentPane().add(txtPeso1);
		getContentPane().add(txtPeso2);
		getContentPane().add(btnCalcular);
		getContentPane().add(lblMediaPond);
		
		setVisible(true);
		
		
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean verficaCampos = VerificarCampos();
				if(verficaCampos == true) {
					num1 = Double.parseDouble(txtNum1.getText());
					num2 = Double.parseDouble(txtNum2.getText());
					peso1 = Double.parseDouble(txtPeso1.getText());
					peso2 = Double.parseDouble(txtPeso1.getText());
					
					mediaPond = ((num1 * peso1) + (num2 * peso2)) / (peso1 + peso2);
					
					lblMediaPond.setText("A Media Ponderada é: " + Double.toString(mediaPond));
				}
			}
		});
	}
	
	public boolean VerificarCampos() {
		if(txtNum1.getText().equals("") || txtNum2.getText().equals("") || txtPeso1.getText().equals("") || txtPeso2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite todos os Campos", "Alerta", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

}
