package Model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ContatoModel {

	private ImageIcon iconReload, iconSearch;

	private Image image_1, image_2;
	
	public ContatoModel() {
		iconReload = new ImageIcon("src/Images/recarregar.png");
		image_1 = iconReload.getImage().getScaledInstance(17, 17, Image.SCALE_SMOOTH);
		iconReload = new ImageIcon(image_1);
		
		iconSearch = new ImageIcon("src/Images/procurar.png");
		image_2 = iconSearch.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		iconSearch = new ImageIcon(image_2);
	}

	public ImageIcon getIconReload() {
		return iconReload;
	}
	
	public ImageIcon getIconSearch() {
		return iconSearch;
	}
}
