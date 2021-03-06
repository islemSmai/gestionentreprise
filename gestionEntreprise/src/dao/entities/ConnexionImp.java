package dao.entities;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.AccessDeniedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.entities.AccessDenied;
import dao.entities.allException;
import dao.entities.testVideException;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class ConnexionImp  {

	private JFrame frame;
	JButton btnLogin;
	private JTextField cinl;
	private JLabel label;
	private JPasswordField password;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnexionImp window = new ConnexionImp();
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
	public ConnexionImp() {
		
		initialize();

		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 204, 255));
		frame.getContentPane().setLayout(null);
		frame.setSize(485, 308);
		frame.setVisible(true);
		frame.setTitle("Gestion Users");
		
		JLabel lbMotPasse = new JLabel("Mot de Passe");
		lbMotPasse.setBounds(24, 116, 109, 14);
		frame.getContentPane().add(lbMotPasse);
		
		
		
		
		 btnLogin = new JButton("Login");
		 btnLogin.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
					try {
						login();
					} catch (testVideException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (AccessDenied e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
		 	}

			
		 });
		btnLogin.setBounds(187, 156, 124, 23);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblconnexion = new JLabel("Connexion");
		lblconnexion.setFont(new Font("Arial", Font.BOLD, 16));
		lblconnexion.setBounds(187, 11, 166, 31);
		frame.getContentPane().add(lblconnexion);
		
		JLabel lblCin = new JLabel("cin:");
		lblCin.setBounds(24, 61, 80, 14);
		frame.getContentPane().add(lblCin);
		
		cinl = new JTextField();
		cinl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				label.setText("");
			}
		});
		cinl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				String value = cinl.getText();
	            int l = value.length();
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getExtendedKeyCode() == KeyEvent.VK_DECIMAL ) {
	            	cinl.setEditable(true);
	               label.setText("");
	            } else {
	            	cinl.setEditable(false);
	               label.setText("Seulement des num???ros sont autoris???s");
	            }
			}
		});
		cinl.setColumns(10);
		cinl.setBounds(182, 58, 124, 20);
		frame.getContentPane().add(cinl);
		
		label = new JLabel("");
		label.setFont(new Font("Arial", Font.PLAIN, 15));
		label.setForeground(Color.RED);
		label.setBounds(302, 40, 327, 14);
		frame.getContentPane().add(label);
		
		password = new JPasswordField();
		password.setBounds(182, 112, 124, 18);
		frame.getContentPane().add(password);
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void login() throws testVideException  , AccessDenied{
		Connection cx= SingletonConnection.getConnection();
		PreparedStatement ps;
		ResultSet rs;
       String motPasse1=null;
       int cin = 0 ;
       
       testVide();
		try {
		ps = cx.prepareStatement("SELECT cin , motPasse from admin  ");
       rs=ps.executeQuery();
      
       while (rs.next()) {
			cin=rs.getInt(1);	
			motPasse1 = rs.getString(2);
			 System.out.println(motPasse1.equals(new String(password.getPassword())));
		}		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Integer.parseInt(cinl.getText())==0 || password.getPassword() .equals(null))throw new testVideException();
		else{
		if(Integer.parseInt(cinl.getText())==cin && motPasse1.equals( new String (password.getPassword()))) {
			dunkirque a = new dunkirque();
			frame.dispose();
		}
		else throw new AccessDenied ();
	}}
	
	public void testVide() throws testVideException {
		// TODO Auto-generated method stub
		if(cinl.getText().length()==0 || password.getText().length()==0) throw new testVideException();
		
	}
}
