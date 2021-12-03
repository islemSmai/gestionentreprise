package dao.entities;

import javax.swing.JOptionPane;

public class NotFound extends Exception {

	public NotFound() {
		super();
		JOptionPane.showMessageDialog(null, " element inconnu");
	}

}
