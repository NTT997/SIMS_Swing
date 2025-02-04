package Views.Invoices;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import Entities.Customers;
import Entities.Invoice;
import Entities.Invoice.Payment_method;
import Entities.Invoice_detail;
import Models.Customer.CustomerModel;
import Models.Invoice.InvoiceDetailModel;
import Models.Invoice.InvoiceModel;
import Models.Product.ProductModel;
import Views.Customer.JFrameCustomer;
import Views.Dashboard.JFrameDashBoard;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JFrameInvoiceDetail extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDeliveryLocation;
	private JTextField textFieldNote;
	private JTable tableBuyItem;
	private List<Invoice_detail> buyProducts = new ArrayList<>();
	private JTextField textFieldSubTotal;
	private JComboBox<Payment_method> comboBoxPaymentMethod;
	private JDateChooser dateChooserDeliveryDate;
	private JLabel lblCustomer;
	private JTextField textFieldCustomer;
	private UUID invoiceId;
	private JTextField textFieldEmail;
	private static JDialog dialog = new JDialog();
	private CustomerModel customerModel = new CustomerModel();
	private Invoice invoice;
	private JLabel lblNewCustomer;
	private static JButton btnPay;
	private String message;
	private ProductModel productModel = new ProductModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameInvoiceDetail frame = new JFrameInvoiceDetail();
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
	public JFrameInvoiceDetail() {
		setPreferredSize(new Dimension(400, 550));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 401, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

		JPanel panelBuyList = new JPanel();
		contentPane.add(panelBuyList);
		panelBuyList.setLayout(new BoxLayout(panelBuyList, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panelBuyList.add(scrollPane);

		tableBuyItem = new JTable();
		scrollPane.setViewportView(tableBuyItem);

		JPanel panelSubTotal = new JPanel();
		contentPane.add(panelSubTotal);
		panelSubTotal.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));

		JLabel lblNewLabel = new JLabel("Sub Total");
		panelSubTotal.add(lblNewLabel);

		textFieldSubTotal = new JTextField();
		textFieldSubTotal.setEditable(false);
		panelSubTotal.add(textFieldSubTotal);
		textFieldSubTotal.setColumns(10);

		JPanel panelInfo = new JPanel();
		contentPane.add(panelInfo);
		GridBagLayout gbl_panelInfo = new GridBagLayout();
		gbl_panelInfo.columnWidths = new int[] { 104, 73, 0, 75, 0, 0 };
		gbl_panelInfo.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelInfo.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelInfo.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelInfo.setLayout(gbl_panelInfo);

		JLabel lblNewLabel_1 = new JLabel("Email");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panelInfo.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textFieldEmail = new JTextField();
		textFieldEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textFieldEmail_focusLost(e);
			}
		});
		textFieldEmail.setText("");
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.gridwidth = 3;
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 0;
		panelInfo.add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);

		lblNewCustomer = new JLabel("");
		lblNewCustomer.setIcon(new ImageIcon(JFrameInvoiceDetail.class.getResource("/Resources/Icons/Add.png")));
		lblNewCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewCustomer_mouseClicked();
			}
		});
		GridBagConstraints gbc_lblNewCustomer = new GridBagConstraints();
		gbc_lblNewCustomer.anchor = GridBagConstraints.WEST;
		gbc_lblNewCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewCustomer.gridx = 4;
		gbc_lblNewCustomer.gridy = 0;
		panelInfo.add(lblNewCustomer, gbc_lblNewCustomer);

		lblCustomer = new JLabel("Customer");
		lblCustomer.setVisible(false);
		GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
		gbc_lblCustomer.anchor = GridBagConstraints.WEST;
		gbc_lblCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomer.gridx = 0;
		gbc_lblCustomer.gridy = 1;
		panelInfo.add(lblCustomer, gbc_lblCustomer);

		textFieldCustomer = new JTextField();
		textFieldCustomer.setFocusable(false);
		textFieldCustomer.setVisible(false);
		GridBagConstraints gbc_textFieldCustomer = new GridBagConstraints();
		gbc_textFieldCustomer.gridwidth = 4;
		gbc_textFieldCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCustomer.gridx = 1;
		gbc_textFieldCustomer.gridy = 1;
		panelInfo.add(textFieldCustomer, gbc_textFieldCustomer);
		textFieldCustomer.setColumns(10);

		JLabel lblDeliveryDate = new JLabel("Delivery Date");
		GridBagConstraints gbc_lblDeliveryDate = new GridBagConstraints();
		gbc_lblDeliveryDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeliveryDate.anchor = GridBagConstraints.WEST;
		gbc_lblDeliveryDate.gridx = 0;
		gbc_lblDeliveryDate.gridy = 2;
		panelInfo.add(lblDeliveryDate, gbc_lblDeliveryDate);

		dateChooserDeliveryDate = new JDateChooser();
		dateChooserDeliveryDate.setDateFormatString("dd-MM-yyyy");

		GridBagConstraints gbc_dateChooserDeliveryDate = new GridBagConstraints();
		gbc_dateChooserDeliveryDate.gridwidth = 4;
		gbc_dateChooserDeliveryDate.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooserDeliveryDate.fill = GridBagConstraints.BOTH;
		gbc_dateChooserDeliveryDate.gridx = 1;
		gbc_dateChooserDeliveryDate.gridy = 2;
		panelInfo.add(dateChooserDeliveryDate, gbc_dateChooserDeliveryDate);

		JLabel lblDeliveryLocation = new JLabel("Delivery Location");
		GridBagConstraints gbc_lblDeliveryLocation = new GridBagConstraints();
		gbc_lblDeliveryLocation.anchor = GridBagConstraints.WEST;
		gbc_lblDeliveryLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeliveryLocation.gridx = 0;
		gbc_lblDeliveryLocation.gridy = 3;
		panelInfo.add(lblDeliveryLocation, gbc_lblDeliveryLocation);

		textFieldDeliveryLocation = new JTextField();
		GridBagConstraints gbc_textFieldDeliveryLocation = new GridBagConstraints();
		gbc_textFieldDeliveryLocation.gridwidth = 4;
		gbc_textFieldDeliveryLocation.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDeliveryLocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDeliveryLocation.gridx = 1;
		gbc_textFieldDeliveryLocation.gridy = 3;
		panelInfo.add(textFieldDeliveryLocation, gbc_textFieldDeliveryLocation);
		textFieldDeliveryLocation.setColumns(10);

		JLabel lblNote = new JLabel("Note");
		GridBagConstraints gbc_lblNote = new GridBagConstraints();
		gbc_lblNote.anchor = GridBagConstraints.WEST;
		gbc_lblNote.insets = new Insets(0, 0, 5, 5);
		gbc_lblNote.gridx = 0;
		gbc_lblNote.gridy = 4;
		panelInfo.add(lblNote, gbc_lblNote);

		textFieldNote = new JTextField();
		GridBagConstraints gbc_textFieldNote = new GridBagConstraints();
		gbc_textFieldNote.gridwidth = 4;
		gbc_textFieldNote.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNote.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNote.gridx = 1;
		gbc_textFieldNote.gridy = 4;
		panelInfo.add(textFieldNote, gbc_textFieldNote);
		textFieldNote.setColumns(10);

		JLabel lblPaymentMethod = new JLabel("Payment Method");
		GridBagConstraints gbc_lblPaymentMethod = new GridBagConstraints();
		gbc_lblPaymentMethod.anchor = GridBagConstraints.WEST;
		gbc_lblPaymentMethod.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaymentMethod.gridx = 0;
		gbc_lblPaymentMethod.gridy = 5;
		panelInfo.add(lblPaymentMethod, gbc_lblPaymentMethod);

		comboBoxPaymentMethod = new JComboBox<Payment_method>();
		comboBoxPaymentMethod.setModel(new DefaultComboBoxModel<>(Payment_method.values()));
		comboBoxPaymentMethod.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxPaymentMethod = new GridBagConstraints();
		gbc_comboBoxPaymentMethod.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaymentMethod.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaymentMethod.gridx = 1;
		gbc_comboBoxPaymentMethod.gridy = 5;
		panelInfo.add(comboBoxPaymentMethod, gbc_comboBoxPaymentMethod);

		btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPay_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnPay = new GridBagConstraints();
		gbc_btnPay.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPay.insets = new Insets(0, 0, 0, 5);
		gbc_btnPay.gridx = 3;
		gbc_btnPay.gridy = 6;
		panelInfo.add(btnPay, gbc_btnPay);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 6;
		panelInfo.add(btnCancel, gbc_btnCancel);
	}

	public JFrameInvoiceDetail(List<Invoice_detail> buyProducts, Invoice invoice) {
		this();
		this.buyProducts = buyProducts;
		this.invoice = invoice;
		initJFrame();
	}

	private void initJFrame() {
		setReadable(invoice);
		fillDataToTable(buyProducts);
		calculateSubTotal();
	}

	// check if email already exist
	private void textFieldEmail_focusLost(FocusEvent e) {
		if (JFrameCustomer.isValidEmail(textFieldEmail.getText())) {
			String email = textFieldEmail.getText();
			Customers customer = customerModel.findByEmail(email);
			if (customer == null) {
				JOptionPane.showMessageDialog(null, "This email is not registed!");
				lblCustomer.setVisible(false);
				textFieldCustomer.setVisible(false);
				textFieldCustomer.setText(null);
			} else {
				lblCustomer.setVisible(true);
				textFieldCustomer.setVisible(true);
				textFieldCustomer.setText(customer.getName());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Email");
		}
	}

	// add new customer
	private void lblNewCustomer_mouseClicked() {
		JFrameCustomer customerJFrame = new JFrameCustomer(textFieldEmail.getText());
		textFieldEmail.setText("");

		dialog = new JDialog();
		dialog.setTitle("Customer Register");
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setSize(customerJFrame.getSize());
		dialog.setResizable(false);
		dialog.getContentPane().add(customerJFrame.getContentPane());

		// Calculate the center of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) (screenSize.getWidth() - dialog.getWidth()) / 2;
		int centerY = (int) (screenSize.getHeight() - dialog.getHeight()) / 2;

		// Set the dialog's location to the center of the screen
		dialog.setLocation(centerX, centerY);
		dialog.setVisible(true);
	}

	private void btnCancel_actionPerformed(ActionEvent e) {
		if (invoice != null) {
			JPanelListInvoice.dispose();
			return;
		}
		JPanelSalesInvoice.dispose();
	}

	private void btnPay_actionPerformed(ActionEvent e) {
		if (invoice != null && btnPay.getText() == "Proceed") {
			proceedToReturn();
			return;
		}
		if (btnPay.getText() == "Reload") {
			List<Invoice_detail> result = reload(JPanelReturnInvoice.getList());
			tableBuyItem.removeAll();
			tableBuyItem.repaint();
			tableBuyItem.revalidate();
			fillDataToTable(result);
			calculateSubTotal();
			if (checkIfReturnedAll()) {
				btnPay.setVisible(false);
				InvoiceModel invoicemodel = new InvoiceModel();
				if (invoicemodel.updateReturned(buyProducts.get(0).getInvoice_id(), true)) {
					btnPay.setVisible(false);
					return;
				}
			} else {
				btnPay.setText("Proceed");
			}
			return;
		}
		String email = textFieldEmail.getText().trim();
		if (email.isEmpty() || !JFrameCustomer.isValidEmail(email) || customerModel.findByEmail(email) == null) {
			JOptionPane.showMessageDialog(null, "Invalid or empty email address.");
			return; // Prevent further processing if email is invalid or empty
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date currentDate = new Date();
		String formattedDate = dateFormat.format(currentDate);
		
		try {
			if (dateChooserDeliveryDate.getDate().before(dateFormat.parse(formattedDate))) {
				JOptionPane.showMessageDialog(null, "Invalid Delivery Date!");
				return;
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		createInvoice();

		if (message.equalsIgnoreCase("Done!")) {
			JOptionPane.showMessageDialog(null, message);
			JPanelSalesInvoice.dispose();
		} else {
			JOptionPane.showMessageDialog(null, message);
		}
	}

	// Function to create a new Invoice
	private void createInvoice() {
		// Create a new Invoice object
		Invoice invoice = new Invoice();
		// Create an instance of the InvoiceModel class to work with invoices
		InvoiceModel invoiceModel = new InvoiceModel();
		invoiceId = UUID.randomUUID();
		try {
			if (!buyProducts.isEmpty()) {
				// Set various fields of the invoice object
				invoice.setInvoice_id(invoiceId.toString());
				invoice.setCreated_at(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
				invoice.setDelivery_date(dateChooserDeliveryDate.getDate());
				invoice.setDelivery_location(textFieldDeliveryLocation.getText());
				invoice.setNote(textFieldNote.getText());
				invoice.setPayment_method((Payment_method) comboBoxPaymentMethod.getSelectedItem());
				invoice.setIs_cancelled(false);
				invoice.setIs_imported(false);
				invoice.setSupplier_id(null);
				invoice.setCustomer_id(customerModel.findByEmail(textFieldEmail.getText()).getId());
				invoice.setEmployee_id(JFrameDashBoard.getEmployeeId());// set employeeid later
				// Create the invoice in the database

				if (invoiceModel.create(invoice)) {
					createInvoiceDetail();
				} else {
					JOptionPane.showMessageDialog(null, "Create Invoice Failed!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "NO ITEM!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Function to create a new Invoice Detail
	private void createInvoiceDetail() {
		// Create an instance of Invoice_detail and invoice_detail_model
		InvoiceDetailModel invoiceDetailModel = new InvoiceDetailModel();
		// set attribute of invoice detail per row
		for (int i = 0; i < tableBuyItem.getRowCount(); i++) {
			String product_code = (String) tableBuyItem.getValueAt(i,
					getColumnIndexByName(tableBuyItem, "productCode"));
			double product_price = (double) tableBuyItem.getValueAt(i,
					getColumnIndexByName(tableBuyItem, "Price (VND)"));
			double product_quantity = (double) tableBuyItem.getValueAt(i,
					getColumnIndexByName(tableBuyItem, "Quantity"));
			int product_Discount = (int) tableBuyItem.getValueAt(i, getColumnIndexByName(tableBuyItem, "Discount (%)"));
			Invoice_detail invoiceDetail = new Invoice_detail();

			invoiceDetail.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
			invoiceDetail.setProduct_price(product_price);
			invoiceDetail.setDiscount_percent(product_Discount);
			invoiceDetail.setProduct_quantity(product_quantity);
			invoiceDetail.setReturned(false);
			invoiceDetail.setIs_discount(product_Discount > 0 ? true : false);
			invoiceDetail.setProduct_code(product_code);
			invoiceDetail.setInvoice_id(invoiceId.toString());
			// add to database
			if (productModel.findById(product_code).getQuantity() - product_quantity < 0) {
				JOptionPane.showMessageDialog(null, "Not Enough Stock!");
				message = "Failed!";
				return;
			}
			if (invoiceDetailModel.create(invoiceDetail)) {
				productModel.updateQuantity(productModel.findById(product_code).getQuantity() - product_quantity,
						product_code);
				message = "Done!";
			}
		}
	}

	// Function to get the column index by column name in a JTable
	private int getColumnIndexByName(JTable table, String columnName) {
		for (int i = 0; i < table.getColumnCount(); i++) {
			if (table.getColumnName(i).equals(columnName)) {
				return i;// Return the column index when the column name matches
			}
		}
		return -1;// Return -1 if the column name is not found
	}

	// Function to populate data into the JTable
	private void fillDataToTable(List<Invoice_detail> List) {
		ProductModel productModel = new ProductModel();
		// Create a new DefaultTableModel and Add columns to the model
		if (invoice == null) {
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("productCode");
			model.addColumn("Name");
			model.addColumn("Price (VND)");
			model.addColumn("Quantity");
			model.addColumn("Discount (%)");
			model.addColumn("Total");
			for (Invoice_detail item : List) {
				double result;
				if (item.getDiscount_percent() > 0) {
					result = item.getProduct_quantity() * item.getProduct_price() * (100 - item.getDiscount_percent())
							/ 100;
				} else {
					result = item.getProduct_quantity() * item.getProduct_price();
				}
				model.addRow(new Object[] { item.getProduct_code(),
						productModel.findById(item.getProduct_code()).getName(), item.getProduct_price(),
						item.getProduct_quantity(), item.getDiscount_percent(), result });
			}
			tableBuyItem.setModel(model);
			tableBuyItem.getTableHeader().setReorderingAllowed(false);
			hideColumn(getColumnIndexByName(tableBuyItem, "productCode"));
			return;
		}
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// Return the appropriate column class, including Boolean for the "Option"
				// column
				if (columnIndex == getColumnIndexByName(tableBuyItem, "Return")) {
					return Boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				boolean isReturned = (boolean) getValueAt(row, getColumnIndexByName(tableBuyItem, "isReturn"));
				if (isReturned) {
					return !isReturned; // Return false for rows where isReturn is true
				}
				return column == getColumnIndexByName(tableBuyItem, "Return");
			}
		};
		model.addColumn("productCode");
		model.addColumn("Name");
		model.addColumn("Price (VND)");
		model.addColumn("Quantity");
		model.addColumn("Discount (%)");
		model.addColumn("Total");
		model.addColumn("id");
		model.addColumn("InvoiceId");
		model.addColumn("isDiscount");
		model.addColumn("isReturn");
		model.addColumn("Created");
		model.addColumn("Return");
		for (Invoice_detail item : List) {
			double result;
			if (item.getDiscount_percent() > 0) {
				result = item.getProduct_quantity() * item.getProduct_price() * (100 - item.getDiscount_percent())
						/ 100;
			} else {
				result = item.getProduct_quantity() * item.getProduct_price();
			}
			model.addRow(new Object[] { item.getProduct_code(), productModel.findById(item.getProduct_code()).getName(),
					item.getProduct_price(), item.getProduct_quantity(), item.getDiscount_percent(), result,
					item.getId(), item.getInvoice_id(), item.isIs_discount(), item.isReturned(), item.getCreated_at(),
					false });
		}
		// Set the model as the data source for the table
		tableBuyItem.setModel(model);
		tableBuyItem.getTableHeader().setReorderingAllowed(false);
		// Hide the product_Code column by setting its width to zero
		hideColumn(getColumnIndexByName(tableBuyItem, "productCode"));
		hideColumn(getColumnIndexByName(tableBuyItem, "id"));
		hideColumn(getColumnIndexByName(tableBuyItem, "InvoiceId"));
		hideColumn(getColumnIndexByName(tableBuyItem, "isReturn"));
		hideColumn(getColumnIndexByName(tableBuyItem, "isDiscount"));
		tableBuyItem.getColumnModel().getColumn(getColumnIndexByName(tableBuyItem, "Return"))
				.setCellRenderer(new CheckBoxRenderer());
		tableBuyItem.getColumnModel().getColumn(getColumnIndexByName(tableBuyItem, "Return"))
				.setCellEditor(new DefaultCellEditor(new JCheckBox()));
		tableBuyItem.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
	}

	private boolean checkIfReturnedAll() {
		int rowCount = tableBuyItem.getRowCount();
		int returnCount = 0;
		for (int i = 0; i < rowCount; i++) {
			if ((boolean) tableBuyItem.getValueAt(i, getColumnIndexByName(tableBuyItem, "isReturn"))) {
				returnCount++;
			}
		}
		if (returnCount == rowCount) {
			return true;
		}
		return false;
	}

	// Custom cell renderer for checkboxes
	private class CheckBoxRenderer implements TableCellRenderer {
		private final JCheckBox checkBox = new JCheckBox();

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (column == getColumnIndexByName(table, "Return")) {
				boolean isReturn = (boolean) table.getValueAt(row, getColumnIndexByName(table, "isReturn"));

				// Enable/disable the checkbox based on the "isReturn" value
				checkBox.setEnabled(!isReturn);
				checkBox.setSelected((boolean) value);

				return checkBox;
			} else {
				// For other columns, make the checkbox uneditable
				checkBox.setEnabled(false);
				checkBox.setSelected((boolean) value);
				return checkBox;
			}
		}
	}

	private class CustomTableCellRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			// Check if the "isReturn" column is true for the current row
			boolean isReturn = (boolean) table.getValueAt(row, getColumnIndexByName(table, "isReturn"));

			// Set the background color to red if isReturn is true
			if (isReturn) {
				component.setBackground(Color.RED);
			} else {
				component.setBackground(table.getBackground());
				component.setForeground(Color.BLACK);
			}

			return component;
		}
	}

	private void hideColumn(int columnIndex) {
		tableBuyItem.getColumnModel().getColumn(columnIndex).setMinWidth(0);
		tableBuyItem.getColumnModel().getColumn(columnIndex).setMaxWidth(0);
		tableBuyItem.getColumnModel().getColumn(columnIndex).setWidth(0);
	}

	// Function to calculate and display the subtotal of the items in the table
	private void calculateSubTotal() {
		if (invoice == null) {
			double subTotal = 0;
			int rowCount = tableBuyItem.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				subTotal += (double) tableBuyItem.getValueAt(i, getColumnIndexByName(tableBuyItem, "Total"));
			}
			textFieldSubTotal.setText(String.valueOf(subTotal));
			return;
		}
		double subTotal = 0;
		int rowCount = tableBuyItem.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			boolean result = (boolean) tableBuyItem.getValueAt(i, getColumnIndexByName(tableBuyItem, "isReturn"));
			if (!result) {
				subTotal += (double) tableBuyItem.getValueAt(i, getColumnIndexByName(tableBuyItem, "Total"));
			}
		}
		textFieldSubTotal.setText(String.valueOf(subTotal));
	}

	private void proceedToReturn() {
		List<Invoice_detail> returnLists = new ArrayList<Invoice_detail>();
		for (int i = 0; i < tableBuyItem.getRowCount(); i++) {
			boolean isReturned = (boolean) tableBuyItem.getValueAt(i, getColumnIndexByName(tableBuyItem, "Return"));
			if (isReturned) {
				Invoice_detail returnItem = new Invoice_detail();
				returnItem.setId((int) tableBuyItem.getValueAt(i, getColumnIndexByName(tableBuyItem, "id")));
				returnItem.setInvoice_id(
						(String) tableBuyItem.getValueAt(i, getColumnIndexByName(tableBuyItem, "InvoiceId")));
				returnItem.setProduct_code(
						(String) tableBuyItem.getValueAt(i, getColumnIndexByName(tableBuyItem, "productCode")));
				returnItem.setProduct_quantity(
						(double) tableBuyItem.getValueAt(i, getColumnIndexByName(tableBuyItem, "Quantity")));
				returnItem.setReturned(isReturned);
				returnLists.add(returnItem);
			}
		}

		// Create a modal dialog to display the invoice detail frame
		if (returnLists.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please select at least 1 product to return!");
			return;
		}

		JDialog dialogReturn = new JDialog(this, "Return Detail", true); // Pass 'this' as the parent component
		JPanelReturnInvoice returnInvoice = new JPanelReturnInvoice(returnLists);

		dialogReturn.getContentPane().add(returnInvoice);
		dialogReturn.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dialogReturn.pack();
		dialogReturn.setResizable(false);
		dialogReturn.setLocationRelativeTo(this);
		dialogReturn.getContentPane().setName("the name");
		dialogReturn.setVisible(true);
	}

	private void setReadable(Invoice invoice) {
		if (invoice != null) {
			textFieldEmail.setText(customerModel.findById(invoice.getCustomer_id()).getEmail());
			textFieldEmail.setEnabled(false);

			lblNewCustomer.setVisible(false);

			lblCustomer.setVisible(true);
			textFieldCustomer.setText(customerModel.findById(invoice.getCustomer_id()).getName());
			textFieldCustomer.setVisible(true);
			textFieldCustomer.setEnabled(false);

			dateChooserDeliveryDate.setDate(invoice.getDelivery_date());
			dateChooserDeliveryDate.setEnabled(false);
			JTextFieldDateEditor textFieldDateEditor = (JTextFieldDateEditor) dateChooserDeliveryDate.getDateEditor();
			textFieldDateEditor.setEnabled(false);

			textFieldDeliveryLocation.setText(invoice.getDelivery_location());
			textFieldDeliveryLocation.setEnabled(false);

			textFieldNote.setText(invoice.getNote());
			textFieldNote.setEnabled(false);

			comboBoxPaymentMethod.setSelectedItem(invoice.getPayment_method());
			comboBoxPaymentMethod.setBackground(Color.GREEN);
			comboBoxPaymentMethod.setEnabled(false);

			if (invoice.getIs_returned()) {
				btnPay.setVisible(false);
			}
			btnPay.setText("Proceed");
		}
	}

	public static void disposeCustomerRegister() {
		dialog.dispose();
	}

	public List<Invoice_detail> reload(List<Invoice_detail> List) {
		return buyProducts = List;
	}

	public static void setText(String text) {
		btnPay.setText(text);
	}
}
