package dao.entities;

import javax.swing.JOptionPane;

public class allException extends Exception {

	public allException() {
		super();
		JOptionPane.showMessageDialog(null, "existe d�ja");
	}
	
}
