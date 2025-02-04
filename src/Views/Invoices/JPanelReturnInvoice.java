package Views.Invoices;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;

import Entities.Invoice_detail;
import Entities.Products;
import Entities.Return_product;
import Models.Invoice.InvoiceDetailModel;
import Models.Invoice.ReturnProductModel;
import Models.Product.ProductModel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;

public class JPanelReturnInvoice extends JPanel {

	private JPanel panelContent;
	private JPanel panelMain;
	private static List<Invoice_detail> returnItemList = new ArrayList<Invoice_detail>();
	private List<JPanel> returnItemPanelList = new ArrayList<>();
	private ProductModel productModel = new ProductModel();
	private JTextField invoiceDetailIDtextField;
	private JTextField reasonTextField;
	private ReturnProductModel returnProductModel = new ReturnProductModel();

	/**
	 * Create the panel.
	 */
	public JPanelReturnInvoice() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		panelMain = new JPanel();
		add(panelMain);
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panelMain.add(scrollPane);

		panelContent = new JPanel();
		scrollPane.setViewportView(panelContent);

	}

	public JPanelReturnInvoice(List<Invoice_detail> returnList) {
		this();
		this.returnItemList = returnList;
		init();
	}

	private void init() {
		createReturnList(returnItemList);
		createButtons();
		JPanel panel = new JPanel();
		panelMain.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
	}

	public void createReturnList(List<Invoice_detail> returnList) {
		for (Invoice_detail invoice_detail : returnList) {
			JPanel panelReturnItem = new JPanel();
			panelReturnItem
					.setBorder(new TitledBorder(null, "Item", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelContent.add(panelReturnItem);
			GridBagLayout gbl_panelReturnItem = new GridBagLayout();
			gbl_panelReturnItem.columnWidths = new int[] { 100, 0, 0, 0 };
			gbl_panelReturnItem.rowHeights = new int[] { 0, 0, 0, 0, 0 };
			gbl_panelReturnItem.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
			gbl_panelReturnItem.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panelReturnItem.setLayout(gbl_panelReturnItem);

			JLabel invoiceDetailIdLabel = new JLabel("Detail Id");
			GridBagConstraints gbc_invoiceIdLabel = new GridBagConstraints();
			gbc_invoiceIdLabel.anchor = GridBagConstraints.WEST;
			gbc_invoiceIdLabel.insets = new Insets(0, 0, 5, 5);
			gbc_invoiceIdLabel.gridx = 0;
			gbc_invoiceIdLabel.gridy = 0;

			invoiceDetailIDtextField = new JTextField();
			invoiceDetailIDtextField.setText(String.valueOf(invoice_detail.getId()));
			invoiceDetailIDtextField.setEditable(false);
			GridBagConstraints gbc_invoiceIDtextField = new GridBagConstraints();
			gbc_invoiceIDtextField.insets = new Insets(0, 0, 5, 5);
			gbc_invoiceIDtextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_invoiceIDtextField.gridx = 1;
			gbc_invoiceIDtextField.gridy = 0;

			invoiceDetailIDtextField.setColumns(10);

			JLabel nameLabel = new JLabel("Returned Product");
			GridBagConstraints gbc_nameLabel = new GridBagConstraints();
			gbc_nameLabel.anchor = GridBagConstraints.WEST;
			gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nameLabel.gridx = 0;
			gbc_nameLabel.gridy = 1;

			JTextField nameTextField = new JTextField();
			nameTextField.setText(productModel.findById(invoice_detail.getProduct_code()).getName());
			nameTextField.setEditable(false);
			GridBagConstraints gbc_nameTextField = new GridBagConstraints();
			gbc_nameTextField.gridwidth = 2;
			gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameTextField.gridx = 1;
			gbc_nameTextField.gridy = 1;

			nameTextField.setColumns(10);

			JLabel quantityLabel = new JLabel("Returned Quantity");
			GridBagConstraints gbc_quantityLabel = new GridBagConstraints();
			gbc_quantityLabel.anchor = GridBagConstraints.WEST;
			gbc_quantityLabel.insets = new Insets(0, 0, 5, 5);
			gbc_quantityLabel.gridx = 0;
			gbc_quantityLabel.gridy = 2;

			NumberFormat numberFormat = new DecimalFormat("#,###.##");
			NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
			numberFormatter.setValueClass(Double.class);

			JFormattedTextField quantityTextField = new JFormattedTextField(numberFormatter);
			quantityTextField.setValue(invoice_detail.getProduct_quantity());
			quantityTextField.setEditable(false);
			GridBagConstraints gbc_quantityTextField = new GridBagConstraints();
			gbc_quantityTextField.gridwidth = 2;
			gbc_quantityTextField.insets = new Insets(0, 0, 5, 5);
			gbc_quantityTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_quantityTextField.gridx = 1;
			gbc_quantityTextField.gridy = 2;

			quantityTextField.setColumns(10);

			JLabel reasonLabel = new JLabel("Reason");
			GridBagConstraints gbc_reasonLabel = new GridBagConstraints();
			gbc_reasonLabel.anchor = GridBagConstraints.WEST;
			gbc_reasonLabel.insets = new Insets(0, 0, 0, 5);
			gbc_reasonLabel.gridx = 0;
			gbc_reasonLabel.gridy = 3;

			reasonTextField = new JTextField();
			reasonTextField.setText("");
			GridBagConstraints gbc_reasonTextField = new GridBagConstraints();
			gbc_reasonTextField.gridwidth = 2;
			gbc_reasonTextField.insets = new Insets(0, 0, 0, 5);
			gbc_reasonTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_reasonTextField.gridx = 1;
			gbc_reasonTextField.gridy = 3;

			reasonTextField.setColumns(10);

			panelReturnItem.add(invoiceDetailIdLabel, gbc_invoiceIdLabel);
			panelReturnItem.add(invoiceDetailIDtextField, gbc_invoiceIDtextField);
			panelReturnItem.add(nameLabel, gbc_nameLabel);
			panelReturnItem.add(nameTextField, gbc_nameTextField);
			panelReturnItem.add(quantityLabel, gbc_quantityLabel);
			panelReturnItem.add(quantityTextField, gbc_quantityTextField);
			panelReturnItem.add(reasonLabel, gbc_reasonLabel);
			panelReturnItem.add(reasonTextField, gbc_reasonTextField);

			returnItemPanelList.add(panelReturnItem);
			panelContent.add(panelReturnItem);
			panelContent.revalidate();
			panelContent.repaint();
		}
		;
	}

	private void createButtons() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");
		// Adjust the width (75) and height (30)
		Dimension buttonSize = new Dimension(75, 30);
		saveButton.setPreferredSize(buttonSize);
		cancelButton.setPreferredSize(buttonSize);

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveToDatabase();
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});

		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		panelMain.add(buttonPanel);
	}

	private void saveToDatabase() {
		String message = "";
		List<Return_product> returnedList = new ArrayList<Return_product>();
		for (JPanel jPanel : returnItemPanelList) {
			JTextField invoiceDetailID = (JTextField) jPanel.getComponent(1); // Invoice ID text field
			JTextField reasonTextField = (JTextField) jPanel.getComponent(7); // Reason text field

			// Access the values
			int invoiceDetailIDValue = Integer.parseInt(invoiceDetailID.getText());
			String reasonValue = reasonTextField.getText();

			Return_product returnProduct = new Return_product();
			returnProduct.setInvoice_detail_id(invoiceDetailIDValue);
			returnProduct.setReason(reasonValue);
			returnProduct.setReturned_at(new Date());
			returnedList.add(returnProduct);
		}

		int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Save", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			for (Return_product return_product : returnedList) {
				if (returnProductModel.create(return_product)) {
					InvoiceDetailModel invoiceDetailModel = new InvoiceDetailModel();

					if (invoiceDetailModel.updateIsReturned(return_product.getInvoice_detail_id())) {
						// get the invoice detail which have returned product
						Invoice_detail returndedProductAtInvoiceDetail = invoiceDetailModel
								.findById(return_product.getInvoice_detail_id());
						// get the product from invoice detail
						Products returnedProduct = productModel
								.findById(returndedProductAtInvoiceDetail.getProduct_code());
						// get the quantity before return
						double oldQuantity = returnedProduct.getQuantity();
						// update the quantity after return
						double quantityAfterReturn = oldQuantity
								+ returndedProductAtInvoiceDetail.getProduct_quantity();

						productModel.updateQuantity(quantityAfterReturn, returnedProduct.getProduct_code());
						message = "Done!";
					}
				} else {
					message = "Failed!";
					return;
				}
			}
			JFrameInvoiceDetail.setText("Reload");
		}
		JOptionPane.showMessageDialog(null, message);
		closeDialog();
	}

	private void closeDialog() {
		java.awt.Window thisWindow = SwingUtilities.getWindowAncestor(this);
		if (thisWindow instanceof JDialog) {
			JDialog dialog = (JDialog) thisWindow;
			dialog.dispose();
		}
	}

	public static List<Invoice_detail> getList() {
		InvoiceDetailModel invoiceDetailModel = new InvoiceDetailModel();
		List<Invoice_detail> returnList = invoiceDetailModel.findByInvoiceId(returnItemList.get(0).getInvoice_id());
		int count = 0;
		int listSize = returnList.size();
		for (Invoice_detail invoice_detail : returnList) {
			if (invoice_detail.isReturned()) {
				count++;
			}
		}
		if (count == listSize) {
			JFrameInvoiceDetail.setText("Reload");
		} else {
			JFrameInvoiceDetail.setText("Proceed");
		}

		return returnList;
	}
}
