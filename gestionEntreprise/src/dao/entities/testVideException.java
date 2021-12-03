package dao.entities;

import javax.swing.JOptionPane;

public class testVideException extends Exception{
	
	public testVideException() {
		super();
		JOptionPane.showMessageDialog(null, "Verifier vos donn�es !!");
	}

}
