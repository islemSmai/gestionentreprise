package dao.entities;

import java.awt.EventQueue;

import javax.swing.JFrame;

import dao.entities.allException;
import dao.entities.testVideException;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class general{

	private JFrame frame;
	private JTable table = new JTable();;
	private JTextField libelle_cat;
	JButton btnAjouter;
	JButton btnEdit;
	JButton btndelete;
	private JTextField chercher;
	private JButton btnchercher;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					general window = new general();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public general() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 204, 255));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setTitle("Gestion General");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 177, 443);
		frame.getContentPane().add(panel);
		
		menu.menuitems(frame,panel);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(187, 284, 767, 141);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblGestionCatgories = new JLabel("Gestion General");
		lblGestionCatgories.setFont(new Font("Arial", Font.BOLD, 16));
		lblGestionCatgories.setBounds(428, 11, 166, 31);
		frame.getContentPane().add(lblGestionCatgories);
		
		
		String[] columnNames = {"#",
                "Libell�"
               };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				libelle_cat.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				btnAjouter.setEnabled(false);
				btnEdit.setEnabled(true);
				btndelete.setEnabled(true);
				
				
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setModel(model); 
		
		table.setBounds(187, 50, 487, 306);
		
		
		Connection cx= SingletonConnection.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		
		try {
		ps = cx.prepareStatement("select * from general");
		rs=ps.executeQuery();
		
		while (rs.next()) {
			
			
			
			model.addRow(new Object[]{rs.getInt(1),rs.getString(2)});
			
						
		}
		
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportBorder(null);
		panel_1.add(scrollPane);
		table.setForeground(new Color(0, 0, 0));
		
		JLabel lblLibell = new JLabel("nom :");
		lblLibell.setBounds(187, 67, 46, 14);
		frame.getContentPane().add(lblLibell);
		libelle_cat = new JTextField();
		libelle_cat.setBounds(251, 64, 195, 20);
		frame.getContentPane().add(libelle_cat);
		libelle_cat.setColumns(10);
		
		
		 btnAjouter = new JButton("Ajouter un general");
		 btnAjouter.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		try {
					ajouter();
				} catch (allException e1) {
					
					e1.printStackTrace();
				} catch (testVideException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 	}
		 });
		btnAjouter.setBounds(187, 136, 89, 23);
		frame.getContentPane().add(btnAjouter);
		
		 btnEdit = new JButton("Modifier");
		 btnEdit.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		modifier((int)table.getValueAt(table.getSelectedRow(), 0));
		 	}
		 });
		btnEdit.setBounds(302, 136, 89, 23);
		frame.getContentPane().add(btnEdit);
		btnEdit.setEnabled(false);
		
		btndelete = new JButton("Supprimer");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimer((int)table.getValueAt(table.getSelectedRow(), 0));
				
			}
		});
		btndelete.setBounds(516, 136, 124, 23);
		frame.getContentPane().add(btndelete);
		btndelete.setEnabled(false);
		
		chercher = new JTextField();
		chercher.setBounds(574, 218, 133, 20);
		frame.getContentPane().add(chercher);
		chercher.setColumns(10);
		
		btnchercher = new JButton("Recherche");
		btnchercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rechercher();
			}
		});
		btnchercher.setBounds(717, 217, 105, 23);
		frame.getContentPane().add(btnchercher);
		
		JButton btnNewButton = new JButton("Actualiser");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualiser();
			}
		});
		btnNewButton.setBounds(671, 136, 133, 23);
		frame.getContentPane().add(btnNewButton);
	}

	
	
	
	

	
	
	
	public void actualiser() {
		String[] columnNames = {"#",
                "Libell�"
               };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				libelle_cat.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				btnAjouter.setEnabled(false);
				btnEdit.setEnabled(true);
				btndelete.setEnabled(true);
				
				
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setModel(model); 
		
		table.setBounds(187, 50, 487, 306);
		
		
		Connection cx= SingletonConnection.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		
		try {
		ps = cx.prepareStatement("select * from general");
		rs=ps.executeQuery();
		
		while (rs.next()) {
			
			
			
			model.addRow(new Object[]{rs.getInt(1),rs.getString(2)});
			
						
		}
		
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		libelle_cat.setText("");
		
		btnAjouter.setEnabled(true);
		btnEdit.setEnabled(false);
		btndelete.setEnabled(false);
	}
	
	
	
	public void ajouter() throws allException, testVideException {
		
		
		if(select()==1) throw new allException();
		
		
		else if(select()==-1) {
			Connection cx= SingletonConnection.getConnection();
			PreparedStatement ps;
			testVide();
			try {
			ps = cx.prepareStatement("INSERT INTO `general`(`nom`) VALUES(?)");
			
			ps.setString(1, libelle_cat.getText());
			ps.executeUpdate();
			String[] columnNames = {"#",
                "Libell�"
               };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				libelle_cat.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				btnAjouter.setEnabled(false);
				btnEdit.setEnabled(true);
				btndelete.setEnabled(true);
				
				
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setModel(model); 
		
		table.setBounds(187, 50, 487, 306);
		
		
                    Connection cx2= SingletonConnection.getConnection();
		PreparedStatement ps2;
		ResultSet rs;
		
		
		try {
		ps = cx.prepareStatement("select * from general");
		rs=ps.executeQuery();
		
		while (rs.next()) {
			
			
			
			model.addRow(new Object[]{rs.getInt(1),rs.getString(2)});
			
						
		}
		
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		libelle_cat.setText("");
		
		btnAjouter.setEnabled(true);
		btnEdit.setEnabled(false);
		btndelete.setEnabled(false);

			
			}catch (SQLException e) {
				
				e.printStackTrace();
			}
			}
		
		
	}

	
	public void modifier(int id) {
		Connection cx= SingletonConnection.getConnection();
		PreparedStatement ps;
		
		try {
		ps = cx.prepareStatement("update general set libelle=? where idGeneral=?");
		ps.setString(1, libelle_cat.getText());
		ps.setInt(2, id);
		ps.executeUpdate();
		actualiser();

		
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	
	public void supprimer(int id) {
		
		Connection cx= SingletonConnection.getConnection();
		PreparedStatement ps;
		
		try {
		ps = cx.prepareStatement("DELETE from general where idGeneral=?");
		
		ps.setInt(1, id);
		ps.executeUpdate();
		actualiser();

		
		}catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null,"Vous ne pouvez pas supprimer cet element !!");
		}
		
	}

	
	public void rechercher() {
		
			String[] columnNames = {"#",
	                "Libell�"
	               };
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);
			
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					libelle_cat.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
					btnAjouter.setEnabled(false);
					btnEdit.setEnabled(true);
					btndelete.setEnabled(true);
					
					
				}
			});
			table.setFont(new Font("Arial", Font.PLAIN, 14));
			table.setModel(model); 
			
			table.setBounds(187, 50, 487, 306);
			
			Connection cx= SingletonConnection.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			
			
			try {
			ps = cx.prepareStatement("select * from general where nom LIKE ? '%' ");
			ps.setString(1, chercher.getText());
			rs=ps.executeQuery();
			
			while (rs.next()) {
				model.addRow(new Object[]{rs.getInt(1),rs.getString(2)});
						
			}
			
			}catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		
		
		
	}

	
	

	
	public int select() {
		Connection cx= SingletonConnection.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
		ps = cx.prepareStatement("select * from general where nom LIKE  ? ");
		ps.setString(1, libelle_cat.getText());
		rs=ps.executeQuery();
		
		if(rs.next()) {
			return 1;
		}
		else return -1 ;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	
	public void testVide() throws testVideException {
		
		if(libelle_cat.getText().length()==0) throw new testVideException();
		
	}
        
}
