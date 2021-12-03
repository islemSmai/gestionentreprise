package dao.entities;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public abstract class menu {


	public static void menuitems(JFrame frame, JPanel panel) {

		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		JButton btnAccueil = new JButton("Accueil");
		btnAccueil.setBackground(new Color(204, 204, 255));
		btnAccueil.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnAccueil.setBounds(10, 23, 141, 36);
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dunkirque ac = new dunkirque();
				frame.dispose();
			}
		});
		panel.setLayout(null);
		panel.add(btnAccueil);
		
		JButton btnArticle = new JButton("genral");
		btnArticle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				general art = new general();
				frame.dispose();
			}
		});
		btnArticle.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnArticle.setBackground(new Color(204, 204, 255));
		btnArticle.setBounds(10, 70, 141, 36);
		panel.add(btnArticle);
		
		JButton btnFournisseurs = new JButton("bateau");
		btnFournisseurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bateau fournisseur = new bateau();
				frame.dispose();
			}
		});
		btnFournisseurs.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnFournisseurs.setBackground(new Color(204, 204, 255));
		btnFournisseurs.setBounds(10, 117, 141, 36);
		panel.add(btnFournisseurs);
		
		
		frame.setBackground(Color.YELLOW);
		frame.setBounds(100, 100, 755, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setSize(1080, 560);
	}
	}
