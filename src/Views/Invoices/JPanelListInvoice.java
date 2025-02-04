package Views.Invoices;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;

import javax.swing.BoxLayout;

import Entities.Employees;
import Entities.Invoice;
import Entities.Invoice_detail;
import Models.Customer.CustomerModel;
import Models.Employees.EmployeeModel;
import Models.Invoice.InvoiceDetailModel;
import Models.Invoice.InvoiceModel;
import Views.Dashboard.JFrameDashBoard;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class JPanelListInvoice extends JPanel {
	private JComboBox<Object> comboBoxType;
	private JTable tableInvoiceList;
	private String keyword = "";
	private JPopupMenu popupMenu;
	private static JDialog dialog = new JDialog();
	/**
	 * Create the panel.
	 */
	public JPanelListInvoice() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel panelSearch = new JPanel();
		add(panelSearch);

		comboBoxType = new JComboBox<Object>();
		comboBoxType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxType_actionPerformed(e);
			}
		});
		comboBoxType.setModel(new DefaultComboBoxModel<Object>(new String[] { "All", "Supply", "Sales" }));
		panelSearch.add(comboBoxType);

		JPanel panelList = new JPanel();
		add(panelList);
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panelList.add(scrollPane);

		tableInvoiceList = new JTable();
		tableInvoiceList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showPopupMenu(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopupMenu(e);
			}
		});
		scrollPane.setViewportView(tableInvoiceList);

		popupMenu = new JPopupMenu();
		JMenuItem menuItemDetail = new JMenuItem("Detail"); // Add your menu items here
		menuItemDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuItemDetail_actionPerformed(e);
			}
		});

		JMenuItem menuItemCancel = new JMenuItem("Cancel");
		menuItemCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuItemCancel_actionPerformed(e);
			}
		});

		popupMenu.add(menuItemDetail);
		popupMenu.add(menuItemCancel);

		JPanel panelNothing = new JPanel();
		add(panelNothing);
		panelNothing.setLayout(new BorderLayout(0, 0));
		init();
	}
	
	private void init() {
		InvoiceModel invoiceModel = new InvoiceModel();
		fillDataToTable(invoiceModel.find(keyword));
	}

	public void comboBoxType_actionPerformed(ActionEvent e) {
		InvoiceModel invoiceModel = new InvoiceModel();
		int choice = comboBoxType.getSelectedIndex();

		switch (choice) {
		case 1:
			keyword = "supplier_id";
			break;
		case 2:
			keyword = "customer_id";
			break;
		default:
			keyword = "";
			break;
		}
		fillDataToTable(invoiceModel.find(keyword));
	}

	private void fillDataToTable(List<Invoice> invoiceList) {
		// Create a new DefaultTableModel and Add columns to the model
		CustomerModel customerModel = new CustomerModel();
		EmployeeModel employeeModel = new EmployeeModel();

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		model.addColumn("invoice_id");
		model.addColumn("delivery_date");
		model.addColumn("delivery_location");
		model.addColumn("note");
		model.addColumn("payment_method");
		model.addColumn("created_at");
		model.addColumn("supplier");
		model.addColumn("customer");
		model.addColumn("employee");
		model.addColumn("cancelled");
		model.addColumn("is_returned");

		for (Invoice invoice : invoiceList) {
			model.addRow(
					new Object[] { invoice.getInvoice_id(), invoice.getDelivery_date(), invoice.getDelivery_location(),
							invoice.getNote(), invoice.getPayment_method().toString(), invoice.getCreated_at(),
							invoice.getSupplier_id(), customerModel.findById(invoice.getCustomer_id()).getName(),
							employeeModel.FindByEmployeeID(invoice.getEmployee_id()).getUsername(),
							invoice.getIs_cancelled(),invoice.getIs_returned() });
		}
		// Set the model as the data source for the table
		tableInvoiceList.setModel(model);
		tableInvoiceList.getTableHeader().setReorderingAllowed(false);

		if (keyword == "customer_id") {
			hideColumn(tableInvoiceList, getColumnIndexByName(tableInvoiceList, "supplier"));
		}
		if (keyword == "supplier_id") {
			hideColumn(tableInvoiceList, getColumnIndexByName(tableInvoiceList, "customer"));
		}
		hideColumn(tableInvoiceList, getColumnIndexByName(tableInvoiceList, "cancelled"));
		hideColumn(tableInvoiceList, getColumnIndexByName(tableInvoiceList, "is_returned"));
		tableInvoiceList.setDefaultRenderer(Object.class, new Tablerenderer());
	}

	private void hideColumn(JTable table, int columnIndex) {
		table.getColumnModel().getColumn(columnIndex).setMinWidth(0);
		table.getColumnModel().getColumn(columnIndex).setMaxWidth(0);
		table.getColumnModel().getColumn(columnIndex).setWidth(0);
	}

	private int getColumnIndexByName(JTable table, String columnName) {
		for (int i = 0; i < table.getColumnCount(); i++) {
			if (table.getColumnName(i).equals(columnName)) {
				return i;// Return the column index when the column name matches
			}
		}
		return -1;// Return -1 if the column name is not found
	}

	private class Tablerenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			boolean isCancelled = (boolean) table.getValueAt(row, getColumnIndexByName(table, "cancelled"));
			boolean isReturned = (boolean) table.getValueAt(row, getColumnIndexByName(table, "is_returned"));
			if(isReturned) {
				component.setBackground(Color.YELLOW);
				component.setForeground(Color.BLACK);
				return component;
			}
			if (isCancelled) {
				component.setBackground(Color.RED);
			} else {
				component.setBackground(table.getBackground());
				component.setForeground(Color.BLACK);
			}
			return component;
		}

	}

	public void menuItemDetail_actionPerformed(ActionEvent e) {
		int selectedRowIndex = tableInvoiceList.getSelectedRow();
		String invoice_id = (String) tableInvoiceList.getValueAt(selectedRowIndex, 0);

		InvoiceModel invoiceModel = new InvoiceModel();
		Invoice invoice = invoiceModel.findByID(invoice_id);

		InvoiceDetailModel invoiceDetailModel = new InvoiceDetailModel();
		List<Invoice_detail> details = invoiceDetailModel.findByInvoiceId(invoice_id);

		dialog = new JDialog();
		dialog.setTitle("Invoice Detail");
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

		JFrameInvoiceDetail invoiceDetail = new JFrameInvoiceDetail(details, invoice);
		dialog.getContentPane().add(invoiceDetail.getContentPane());
		dialog.pack();
		dialog.setVisible(true);

	}

	public void menuItemCancel_actionPerformed(ActionEvent e) {
		int selectedRow = tableInvoiceList.getSelectedRow();
		String invoice_id = (String) tableInvoiceList.getValueAt(selectedRow, 0);

		InvoiceModel invoiceModel = new InvoiceModel();

		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		Date currentDate = new Date(currentTimestamp.getTime());

		if (invoiceModel.updateCancel((java.util.Date) currentDate, invoice_id)) {
			JOptionPane.showMessageDialog(null, "Done!");
			fillDataToTable(invoiceModel.find(""));
			
		} else {
			JOptionPane.showMessageDialog(null, "Failed!");
		}
	}

	private void showPopupMenu(MouseEvent e) {
		int selectedRow = tableInvoiceList.getSelectedRow();
		if (selectedRow != -1) {
			boolean isCancelled = (boolean) tableInvoiceList.getValueAt(selectedRow,
					getColumnIndexByName(tableInvoiceList, "cancelled"));
			if (!isCancelled) {
				if (e.isPopupTrigger()) {
					// Get the selected row index
					int row = tableInvoiceList.getSelectedRow();

					// Show the popup menu at the mouse position
					popupMenu.show(tableInvoiceList, e.getX(), e.getY());
				}
			}
		}
	}

	public static void dispose() {
		dialog.dispose();
	}
}
