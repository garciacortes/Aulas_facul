package Model;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MenuModel {
	
	private Color menuColor;

	private ImageIcon iconShow, iconExit, iconContato, iconAdd;

	private Image image_1, image_2, image_3, image_4;
	
	public MenuModel() {
		menuColor = Color.decode("#1e88e5");
		iconShow = new ImageIcon("src/Images/menu.png");
		image_1 = iconShow.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		iconShow = new ImageIcon(image_1);
		
		iconExit = new ImageIcon("src/Images/exit.png");
		image_2 = iconExit.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		iconExit = new ImageIcon(image_2);
		
		iconContato = new ImageIcon("src/Images/contato.png");
		image_3 = iconContato.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
		iconContato = new ImageIcon(image_3);
		
		iconAdd = new ImageIcon("src/Images/adicionar.png");
		image_4 = iconAdd.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
		iconAdd = new ImageIcon(image_4);
	}
	
	public ImageIcon getIconExit() {
		return iconExit;
	}
	
	public Color getMenuColor() {
		return menuColor;
	}
	
	public ImageIcon getIconShow() {
		return iconShow;
	}
	
	public ImageIcon getIconContato() {
		return iconContato;
	}
	
	public ImageIcon getIconAdd() {
		return iconAdd;
	}
}
