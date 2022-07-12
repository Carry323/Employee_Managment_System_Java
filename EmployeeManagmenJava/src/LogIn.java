import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn {

	private JFrame frame;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private Connection connection;
	private ImageIcon btnIcon;
	private JLabel label;
	private ImageIcon lblIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
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
	public LogIn() {
		initialize();
		connection = SqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 204, 51));
		frame.setBounds(100, 100, 571, 368);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Login");
		
		userNameField = new JTextField();
		userNameField.setBounds(285, 92, 163, 32);
		frame.getContentPane().add(userNameField);
		userNameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblUsername.setBounds(156, 101, 127, 23);
		frame.getContentPane().add(lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(285, 152, 163, 32);
		frame.getContentPane().add(passwordField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblPassword.setBounds(156, 161, 113, 23);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogIn = new JButton("Login");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "select * from EmployeeInfo where Username=? and Password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, userNameField.getText());
					pst.setString(2, passwordField.getText());
					
					
					ResultSet rs = pst.executeQuery();
					
					int counter = 0;
					while (rs.next()) {
						counter++;
					}
					
					if (counter == 1) {
						JOptionPane.showMessageDialog(null, "Username and Password is Correct!");
						frame.dispose();
						EmployeeInfo empInfo = new EmployeeInfo();
						empInfo.setVisible(true);
						
						
					} else if(counter >= 2 ) {
						
						JOptionPane.showMessageDialog(null, "Duplicated Username and Password");

					}else if(counter == 0) {
						JOptionPane.showMessageDialog(null, "NO Username and Password!");
					}
					
					rs.close();
					pst.close();
					
				}catch(Exception e1) {
					
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnLogIn.setForeground(new Color(204, 204, 51));
		btnLogIn.setBackground(new Color(204, 153, 0));
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnLogIn.setBounds(238, 212, 113, 32);
		btnLogIn.setFocusable(false);
		
		btnIcon = new ImageIcon("check.png");
		btnLogIn.setIcon(btnIcon);
		
		label = new JLabel();
		label.setSize(100, 100);
		label.setBounds(10, 80, 130, 130);
		
		lblIcon = new ImageIcon("key.png");
		label.setIcon(lblIcon);
		
		frame.add(label);
		frame.getContentPane().add(btnLogIn);
	}
}
