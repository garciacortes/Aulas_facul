package View;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VotacaoView extends JFrame {
	
	private JLabel lblCandidatos, lblListCandidatos, lblVoto, lblTotal, lblBrancos, lblVotoCand1, lblVotoCand2, lblAviso;
	private Container ctn;
	
	private static final long serialVersionUID = 1L;
	
	public VotacaoView() {
		setSize(400, 430);
		setTitle("Urna Eletronica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);;
		
		ctn = getContentPane();
		ctn.setLayout(null);
		
		lblCandidatos = new JLabel("Candidatos");
		lblCandidatos.setBounds(10, 10, 100, 25);
		
		lblVoto = new JLabel("Voto: ");
		lblVoto.setBounds(10, 80, 100, 25);
		
		lblAviso = new JLabel("Digite o seu voto!!!");
		lblAviso.setBounds(60, 180, 200, 25);
		lblAviso.setVisible(false);
		
		lblTotal = new JLabel("Total de votos");
		lblTotal.setBounds(10, 280, 250, 25);
		
		lblBrancos = new JLabel("Total em brancos");
		lblBrancos.setBounds(10, 300, 250, 25);
		
		lblVotoCand1 = new JLabel("Votos em candidato 1");
		lblVotoCand1.setBounds(10, 320, 250, 25);
		
		lblVotoCand2 = new JLabel("Votos em candidato 2");
		lblVotoCand2.setBounds(10, 340, 250 ,25);
		
		ctn.add(lblCandidatos);
		ctn.add(lblVoto);
		ctn.add(lblAviso);
		ctn.add(lblVoto);
		ctn.add(lblTotal);
		ctn.add(lblBrancos);
		ctn.add(lblVotoCand1);
		ctn.add(lblVotoCand2);
		
		setVisible(true);
	}

}
