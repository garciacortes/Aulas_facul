package view;

import javax.swing.JFrame;

import controller.AdicionarController;
import controller.ContatoController;
import controller.MenuController;

public class Agenda extends JFrame {

	private static final long serialVersionUID = 1L;

	MenuView menu = new MenuView();
	AdicionarView adicionar = new AdicionarView();
	ListaContatos contatos = new ListaContatos();
	MenuController controller = new MenuController(menu, adicionar, contatos);
	AdicionarController AddController = new AdicionarController(adicionar);
	ContatoController contatoController = new ContatoController(contatos, AddController);
	
	public Agenda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 416);
		setLocationRelativeTo(null);
		
		getContentPane().add(menu.getMenuPanel());
		getContentPane().add(adicionar.getAdicionarPanel());
		getContentPane().add(contatos.getContatosPanel());
	}
}
