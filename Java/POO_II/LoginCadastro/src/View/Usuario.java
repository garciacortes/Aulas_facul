package View;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Usuario extends JPanel {
	
	private JLabel lblNome, lblSexo, lblNomeShow, lblSexoShow;
	
	private static final long serialVersionUID = 1L;

	public Usuario(Dados dados) {
		setLayout(null);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(35, 40, 100 ,25);
		lblNomeShow = new JLabel();
		lblNomeShow.setBounds(85, 40, 150, 25);
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(35, 70, 100, 25);
		lblSexoShow = new JLabel();
		lblSexoShow.setBounds(85, 70, 150, 25);
		
		add(lblNome);
		add(lblSexo);
		add(lblNomeShow);
		add(lblSexoShow);
		
		setVisible(false);
	}

	public JLabel getLblSexoShow() {
		return lblSexoShow;
	}

	public JLabel getLblNomeShow() {
		return lblNomeShow;
	}
}
