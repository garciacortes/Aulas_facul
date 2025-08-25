package view;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListagemView extends JFrame {
	
	private Container ctn;
	private JPanel pn;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane sp;
	
	private String[] heards= {"Id", "Nome", "Peso", "Altura", "imc"};
	

	private static final long serialVersionUID = 1L;
	
	public ListagemView() {
		setTitle("Listagem");
		setSize(400,500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		ctn = getContentPane();
		
		pn = new JPanel();
		pn.setLayout(new GridLayout());
		//pn.setLayout();
		
		model = new DefaultTableModel();
		table = new JTable();
		
		model.setColumnIdentifiers(heards);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		ctn.add(pn);
		
		pn.add(sp);
		
		setVisible(false);
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
}
