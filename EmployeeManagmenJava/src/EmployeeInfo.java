import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.source.tree.WhileLoopTree;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EmployeeInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Connection connection;
	private JTextField textEID;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textAge;
	private JTextField textSearch;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JComboBox comboBoxSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/***
	 * Extra Methods
	 */
	
	private void refreshTable() {
		// TODO Auto-generated method stub
		
		try {
			
			String query =  "select EID, Name, Surname, Age from EmployeeInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			pst.close();
			rs.close();
			
		} catch (Exception e2) {
			// TODO: handle exception
			
			System.out.println(e2.getMessage());
		}
		
	}
	
	

	/**
	 * Create the frame.
	 */
	public EmployeeInfo() {
		// Database connection
		connection = SqliteConnection.dbConnector();
		JOptionPane.showMessageDialog(null, "Connection Successfully Established ...");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 867, 573);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(244, 167, 561, 317);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					
					int row = table.getSelectedRow();
					String EID_ = (table.getModel().getValueAt(row, 0)).toString();
					
					String query =  "select * from EmployeeInfo where EID='" + EID_ + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					
					while(rs.next()) {
						
						textEID.setText(rs.getString("EID"));
						textName.setText(rs.getString("Name"));
						textSurname.setText(rs.getString("Surname"));
						textAge.setText(rs.getString("Age"));
						textUsername.setText(rs.getString("Username"));
						passwordField.setText(rs.getString("Password"));
						
						
					}
				
					
					
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					// TODO: handle exception
					
					System.out.println(e2.getMessage());
				}
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnLoadTable = new JButton("Load Table");
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				// This event helps me to load our Table with database content
				try {
					
					String query =  "select EID, Name, Surname, Age from EmployeeInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					// TODO: handle exception
					
					System.out.println(e2.getMessage());
				}
				
				
				
				
				
			}
		});
		btnLoadTable.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnLoadTable.setBounds(342, 46, 368, 32);
		btnLoadTable.setFocusable(false);
		contentPane.add(btnLoadTable);
		
		textEID = new JTextField();
		textEID.setFont(new Font("Tahoma", Font.BOLD, 16));
		textEID.setBounds(104, 60, 115, 32);
		contentPane.add(textEID);
		textEID.setEditable(false);
		textEID.setColumns(10);
		
		JLabel lblEID = new JLabel("EID:");
		lblEID.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblEID.setBounds(10, 71, 46, 14);
		contentPane.add(lblEID);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblName.setBounds(10, 112, 71, 14);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.BOLD, 16));
		textName.setColumns(10);
		textName.setBounds(104, 103, 115, 32);
		contentPane.add(textName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblSurname.setBounds(10, 155, 83, 14);
		contentPane.add(lblSurname);
		
		textSurname = new JTextField();
		textSurname.setFont(new Font("Tahoma", Font.BOLD, 16));
		textSurname.setColumns(10);
		textSurname.setBounds(104, 146, 115, 32);
		contentPane.add(textSurname);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblAge.setBounds(10, 208, 83, 23);
		contentPane.add(lblAge);
		
		textAge = new JTextField();
		textAge.setFont(new Font("Tahoma", Font.BOLD, 16));
		textAge.setColumns(10);
		textAge.setBounds(104, 199, 115, 32);
		contentPane.add(textAge);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "insert into EmployeeInfo (EID, Name, Surname,Username, Password, Age) values (?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					//ResultSet rs = pst.executeQuery();
					
					pst.setString(1, textEID.getText());
					pst.setString(2, textName.getText());
					pst.setString(3, textSurname.getText());
					pst.setString(4, textUsername.getText());
					pst.setString(5, passwordField.getText());
					pst.setString(6, textAge.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Saved ...");
					
					
				}catch(Exception e2) {
					
					System.out.println(e2.getMessage());
				}
				
				
				refreshTable();
				
			}

			
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnSave.setBounds(104, 367, 115, 32);
		contentPane.add(btnSave);
		btnSave.setFocusable(false);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
							String query = "update EmployeeInfo set EID='" +textEID.getText()+ "', Name='" + textName.getText()+ 
									"', Surname='" +textSurname.getText() + "', Username='"+ textUsername.getText() +
									"', Password='" + passwordField.getText() + "', Age='" +textAge.getText() + "' where EID='"+textEID.getText() +"'";
							PreparedStatement pst = connection.prepareStatement(query);
		
							pst.execute();
							
							JOptionPane.showMessageDialog(null, "Data Updated ...");
							pst.close();
					
				}catch(Exception e2) {
					
						System.out.println(e2.getMessage());
				}
				
				
					refreshTable();
				
				
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnUpdate.setBounds(104, 419, 115, 32);
		btnUpdate.setFocusable(false);
		contentPane.add(btnUpdate);
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
							String query = "delete from EmployeeInfo where EID='"+textEID.getText() +"'";
							PreparedStatement pst = connection.prepareStatement(query);
		
							pst.execute();
							
							JOptionPane.showMessageDialog(null, "Data Removed ...");
							pst.close();
					
					
				}catch(Exception e2) {
					
						System.out.println(e2.getMessage());
				}
				
				
					refreshTable();
				}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnDelete.setBounds(104, 473, 115, 32);
		btnDelete.setFocusable(false);
		contentPane.add(btnDelete);
		
		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				try {
					
					String selected = comboBoxSearch.getSelectedItem().toString();
					
					String query =  "select EID, Name, Surname, Age from EmployeeInfo where " + selected + "=?";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textSearch.getText());
					
					ResultSet rs = pst.executeQuery();
					
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					// TODO: handle exception
					
					System.out.println(e2.getMessage());
				}
				
				
				
				
			}
		});
		textSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		textSearch.setBounds(531, 122, 179, 34);
		contentPane.add(textSearch);
		textSearch.setColumns(10);
		
		comboBoxSearch = new JComboBox();
		comboBoxSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"EID", "Name", "Surname", "Age"}));
		comboBoxSearch.setBounds(387, 122, 104, 34);
		contentPane.add(comboBoxSearch);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblUsername.setBounds(10, 251, 97, 23);
		contentPane.add(lblUsername);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		textUsername.setColumns(10);
		textUsername.setBounds(104, 242, 115, 32);
		textUsername.setEditable(false);
		contentPane.add(textUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblPassword.setBounds(10, 297, 97, 23);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordField.setBounds(104, 294, 115, 32);
		passwordField.setEditable(false);
		contentPane.add(passwordField);
		
		refreshTable();
		
	}
	
	
}
