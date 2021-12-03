package dao.entities;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dunkirque {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dunkirque window = new dunkirque();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public dunkirque() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 204, 255));
		frame.getContentPane().setLayout(null);
		frame.setSize(1080, 560);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 new general();
				frame.dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(dunkirque.class.getResource("/img/general.jpg")));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(73, 11, 118, 112);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("General");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(83, 132, 107, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 new bateau();
				frame.dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(dunkirque.class.getResource("/img/bateau.jpg")));
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(441, 11, 118, 112);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Bateau");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setBounds(469, 132, 64, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		//frame.setSize(780, 460);
		frame.setVisible(true);
		frame.setTitle("Dunkerque");
		
	}
}
