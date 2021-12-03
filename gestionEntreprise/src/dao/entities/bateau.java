package dao.entities;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import dao.entities.allException;
import dao.entities.testVideException;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class bateau{

	private JFrame frame;
	private JTable table = new JTable();
	JButton btnAjouter;
	JButton btnEdit;
	JButton btndelete;
	private JTextField chercher;
	private JButton btnchercher;
	private JTextField noml;
	private JTextField marque;
	private JTextField pays;
	private JLabel label;
	private JButton btnNewButton;
           private JTextField idBateau;
	 static  Connection cx= SingletonConnection.getConnection();
		 static PreparedStatement ps;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bateau window = new bateau();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public bateau() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 204, 255));
		frame.getContentPane().setLayout(null);
		frame.setSize(1030, 460);
		frame.setVisible(true);
		frame.setTitle("Gestion Bateau");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 177, 443);
		frame.getContentPane().add(panel);
		
		menu.menuitems(frame,panel);
	
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(187, 314, 767, 141);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		String[] columnNames = {"id",
                "Nom",
                "marque",
               
               };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				idBateau.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
				noml.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				marque.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
				
				
				btnAjouter.setEnabled(false);
				btnEdit.setEnabled(true);
				btndelete.setEnabled(true);
				
				
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setModel(model); 
		
		table.setBounds(187, 50, 487, 306);
		JButton supprimer = new JButton("supprimer");
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				general art = new general();
				frame.dispose();
			}
		});
		
		
		ResultSet rs;
		
		
		try {
			
		ps = cx.prepareStatement("select * from "+ testype());
		
		rs=ps.executeQuery();
		
		while (rs.next()) {
			model.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3)});			
		}
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportBorder(null);
		panel_1.add(scrollPane);
		table.setForeground(new Color(0, 0, 0));
		
		
		
		
		
		 btnAjouter = new JButton("Ajouter");
		 btnAjouter.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		try {
						
		if(select()==1) throw new allException();
		
		
		else if(select()==-1) {
			Connection cx= SingletonConnection.getConnection();
			PreparedStatement ps;
			
			testVide();
			try {
			ps = cx.prepareStatement("INSERT INTO "+testype()+" (`idBateau`, `nom`, `pays`, `marque`) VALUES(?,?,?,?)");
			
			ps.setInt(1,Integer.parseInt(idBateau.getText()));
			ps.setString(2,noml.getText());
			ps.setString(3,pays.getText());
			ps.setInt(4,Integer.parseInt(marque.getText()));
			
			ps.executeUpdate();
		
getAll();
			
			}catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			}
				} catch (allException e1) {
					
					e1.printStackTrace();
				}catch (testVideException e1) {
					
					e1.printStackTrace();
				}
		 	}
                        
		 });
		btnAjouter.setBounds(187, 187, 89, 23);
		frame.getContentPane().add(btnAjouter);
		
		 btnEdit = new JButton("Modifier");
		 btnEdit.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		try {
		
		ps = cx.prepareStatement("update "+testype()+" set  `idBateau`= ? , `nom`= ? , `pays` = ?, `marque` = ? where idBateau= ?");
	
		ps.setInt(1,Integer.parseInt(idBateau.getText()));
		ps.setString(2,noml.getText());
		ps.setString(3,pays.getText());
		ps.setInt(4,Integer.parseInt(marque.getText()));
		ps.setInt(7, Integer.parseInt(idBateau.getText()));
		ps.executeUpdate();

		
		}catch (SQLException e5) {
			
			e5.printStackTrace();
		}
		
		 	}
		 });
		btnEdit.setBounds(302, 187, 89, 23);
		frame.getContentPane().add(btnEdit);
		btnEdit.setEnabled(false);
		
		btndelete = new JButton("Supprimer");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		ps = cx.prepareStatement("DELETE from "+testype()+" where idBateau=?");
		
		
		ps.setInt(1, Integer.parseInt(idBateau.getText()));
		ps.executeUpdate();
		

		
		}catch (SQLException es) {
			
			JOptionPane.showMessageDialog(null,"Vous ne pouvez pas supprimer cet element !!");
		}
		
				
			}
		});
		btndelete.setBounds(593, 187, 124, 23);
		frame.getContentPane().add(btndelete);
		btndelete.setEnabled(false);
		
		chercher = new JTextField();
		chercher.setBounds(753, 269, 86, 20);
		frame.getContentPane().add(chercher);
		chercher.setColumns(10);
		
		btnchercher = new JButton("Recherche");
		btnchercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"idBateau",
                "Nom",
                "Pays",
                "Marque",
               };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				idBateau.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
				noml.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				pays.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
				marque.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
				
				
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
		ps = cx.prepareStatement("select * from "+testype()+" where idBateau LIKE ? '%'   or nom LIKE ? '%' or pays LIKE ? '%' ");
		
		ps.setString(1, chercher.getText());
		ps.setString(2, chercher.getText());
		ps.setString(3, chercher.getText());
		rs=ps.executeQuery();
		
		while (rs.next()) {
			model.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)});			
					
		}
		
		}catch (SQLException er) {
			
			er.printStackTrace();
		}
		
	
	
			}
		});
		btnchercher.setBounds(849, 268, 105, 23);
		frame.getContentPane().add(btnchercher);
		
		JLabel lblGestionCatgories = new JLabel("Gestion Bateau");
		lblGestionCatgories.setFont(new Font("Arial", Font.BOLD, 16));
		lblGestionCatgories.setBounds(463, 11, 166, 31);
		frame.getContentPane().add(lblGestionCatgories);
		
                JLabel lblId = new JLabel("id :");
		lblId.setBounds(187, 70, 100, 10);
		frame.getContentPane().add(lblId);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(187, 104, 46, 14);
		frame.getContentPane().add(lblNom);
		
		JLabel lblPays = new JLabel("pays :");
		lblPays.setBounds(187, 143, 67, 14);
		frame.getContentPane().add(lblPays);
		
		idBateau = new JTextField();
		idBateau.addMouseListener(new MouseAdapter() {
			
			public void mouseExited(MouseEvent e) {
				label.setText("");
				}
		});
		idBateau.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent ke) {
				String value = idBateau.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
	            	idBateau.setEditable(true);
	               label.setText("");
	            } else {
	            	idBateau.setEditable(false);
	            	
	              label.setText("Seulement des num�ros sont autoris�s");
	            }
			}
		});
		idBateau.setColumns(10);
		idBateau.setBounds(264, 67, 124, 20);
		frame.getContentPane().add(idBateau);
		
		noml = new JTextField();
		noml.setColumns(10);
		noml.setBounds(264, 104, 124, 20);
		frame.getContentPane().add(noml);
		
		pays = new JTextField();
		pays.setColumns(10);
		pays.setBounds(264, 143, 124, 20);
		frame.getContentPane().add(pays);
		
		
		
		JLabel lblLibell_2_1 = new JLabel("Marque :");
		lblLibell_2_1.setBounds(593, 146, 91, 14);
		frame.getContentPane().add(lblLibell_2_1);
		marque = new JTextField();
		marque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				String value = marque.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
	            	marque.setEditable(true);
	               label.setText("");
	            } else {
	            	marque.setEditable(false);
	               label.setText("Seulement des num�ros sont autoris�s");
	            }
			}
		});
		marque.setColumns(10);
		marque.setBounds(694, 143, 124, 20);
		frame.getContentPane().add(marque);
		
		label = new JLabel("");
		label.setFont(new Font("Arial", Font.PLAIN, 15));
		label.setForeground(Color.RED);
		label.setBounds(302, 40, 327, 14);
		frame.getContentPane().add(label);
		
		btnNewButton = new JButton("Actualiser");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAll();
		idBateau.setText("");
		noml.setText("");
		marque.setText("");
		pays.setText("");
		
		btnAjouter.setEnabled(true);
		btnEdit.setEnabled(false);
		btndelete.setEnabled(false);
			}
		});
		btnNewButton.setBounds(732, 187, 133, 23);
		frame.getContentPane().add(btnNewButton);
		
	}

	

	

	

	



	
	public int select() {
		Connection cx= SingletonConnection.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
		ps = cx.prepareStatement("select * from "+ testype() +" where cin LIKE  ? ");
		ps.setString(1, idBateau.getText());
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
	public String testype() {
		String user="";
		if(this.getClass().getName().equals("bateau")) {
			user="bateau";
		}else if(this.getClass().getName().equals("general")) {
			user="general";
		}
		String user1="bateau";
		return user1;
	}
	public void testVide() throws testVideException {
		if(idBateau.getText().length()==0 || noml.getText().length()==0 || pays.getText().length()==0 || marque.getText().length()==0  ) throw new testVideException();
	}
	public void getAll() {
		String[] columnNames = {"idBateau",
                "Nom",
                "marque",
               
               };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				idBateau.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
				noml.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
				pays.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
				
				btnAjouter.setEnabled(false);
				btnEdit.setEnabled(true);
				btndelete.setEnabled(true);
				
				
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setModel(model); 
		
		table.setBounds(187, 50, 487, 306);
		JButton supprimer = new JButton("supprimer");
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				general art = new general();
				frame.dispose();
			}
		});
		
		Connection cx= SingletonConnection.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		
		try {
			
		ps = cx.prepareStatement("select * from "+ testype());
		
		rs=ps.executeQuery();
		
		while (rs.next()) {
			model.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3)});			
		}
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
