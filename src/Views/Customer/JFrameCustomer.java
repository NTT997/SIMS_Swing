package Views.Customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.Customers;
import Models.Customer.CustomerModel;
import Views.Invoices.JFrameInvoiceDetail;
import Views.Invoices.JPanelSalesInvoice;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class JFrameCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JTextField textFieldName;
	private JTextField textFieldTel;
	private JTextField textFieldAddress;
	private JComboBox comboBoxGender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameCustomer frame = new JFrameCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameCustomer() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 113, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 0;
		contentPane.add(lblEmail, gbc_lblEmail);

		textFieldEmail = new JTextField();
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.gridwidth = 2;
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 0;
		contentPane.add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		contentPane.add(lblName, gbc_lblName);

		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.gridwidth = 2;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 1;
		contentPane.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.EAST;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 2;
		contentPane.add(lblAddress, gbc_lblAddress);

		textFieldAddress = new JTextField();
		GridBagConstraints gbc_textFieldAddress = new GridBagConstraints();
		gbc_textFieldAddress.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAddress.gridwidth = 2;
		gbc_textFieldAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAddress.gridx = 1;
		gbc_textFieldAddress.gridy = 2;
		contentPane.add(textFieldAddress, gbc_textFieldAddress);
		textFieldAddress.setColumns(10);

		JLabel lblTel = new JLabel("Tel");
		GridBagConstraints gbc_lblTel = new GridBagConstraints();
		gbc_lblTel.anchor = GridBagConstraints.EAST;
		gbc_lblTel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTel.gridx = 0;
		gbc_lblTel.gridy = 3;
		contentPane.add(lblTel, gbc_lblTel);

		textFieldTel = new JTextField();
		GridBagConstraints gbc_textFieldTel = new GridBagConstraints();
		gbc_textFieldTel.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTel.gridx = 1;
		gbc_textFieldTel.gridy = 3;
		contentPane.add(textFieldTel, gbc_textFieldTel);
		textFieldTel.setColumns(10);

		JLabel lblGender = new JLabel("Gender");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.EAST;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 0;
		gbc_lblGender.gridy = 4;
		contentPane.add(lblGender, gbc_lblGender);

		comboBoxGender = new JComboBox();
		comboBoxGender.setModel(new DefaultComboBoxModel(new String[] { "Male", "Female" }));
		GridBagConstraints gbc_comboBoxGender = new GridBagConstraints();
		gbc_comboBoxGender.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxGender.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxGender.gridx = 1;
		gbc_comboBoxGender.gridy = 4;
		contentPane.add(comboBoxGender, gbc_comboBoxGender);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreate_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.gridx = 3;
		gbc_btnCreate.gridy = 5;
		contentPane.add(btnCreate, gbc_btnCreate);
	}

	public JFrameCustomer(String email) {
		this();
		textFieldEmail.setText(email);
	}

	public void btnCreate_actionPerformed(ActionEvent e) {
		CustomerModel customerModel = new CustomerModel();
		String email = textFieldEmail.getText().trim();
		String name = textFieldName.getText().trim();
		String address = textFieldAddress.getText().trim();
		String tel = textFieldTel.getText().trim();
		String gender = comboBoxGender.getSelectedItem().toString();

		if (email.isEmpty() || !isValidEmail(email) || name.isEmpty() || address.isEmpty() || tel.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill in all required fields with valid information.");
		} else {
			if (customerModel.findByEmail(email) != null) {
				JOptionPane.showMessageDialog(null, "Email is already registed!");
				return;
			}
			Customers customer = new Customers();
			customer.setEmail(email);
			customer.setName(name);
			customer.setAddress(address);
			customer.setTel(tel);
			customer.setGender("Male".equals(gender));
			customer.setCreated_at(new Date());

			if (customerModel.create(customer)) {
				JOptionPane.showMessageDialog(null, "Done!");
				JFrameInvoiceDetail.disposeCustomerRegister();
			} else {
				JOptionPane.showMessageDialog(null, "Failed!");
			}
		}
	}

	public static boolean isValidEmail(String email) {
		// Define a regular expression pattern for a valid email address
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

		// Compile the pattern
		Pattern pattern = Pattern.compile(emailRegex);

		// Create a Matcher object
		Matcher matcher = pattern.matcher(email);

		// Return true if the email matches the pattern
		return matcher.matches();
	}
}
