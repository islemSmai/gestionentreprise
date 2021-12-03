package dao.entities;

import javax.swing.JOptionPane;

public class AccessDenied extends Exception {
	public AccessDenied() {
		super();
		JOptionPane.showMessageDialog(null, " cin / mot de passe invalide");
	}
}
