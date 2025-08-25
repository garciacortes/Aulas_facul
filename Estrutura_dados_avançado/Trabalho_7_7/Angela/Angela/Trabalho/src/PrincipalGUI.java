import java.awt.EventQueue;

import Controller.MenuController;
import Model.Arvore;
import View.MenuView;

public class PrincipalGUI{

	public static void main(String[] args) {
		
		Arvore model = new Arvore();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView frame = new MenuView();
					new MenuController(frame, model);
					model.setMenu(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
}
