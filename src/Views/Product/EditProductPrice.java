package Views.Product;

import javax.swing.JPanel;

import Entities.Products;
import Models.Product.ProductModel;

import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditProductPrice extends JPanel {
	private JTextField textFieldName;
	private Products product = new Products();
	private JFormattedTextField formattedTextFieldNewPrice;
	private JFormattedTextField formattedTextFieldOldPrice;

	/**
	 * Create the panel.
	 */
	public EditProductPrice() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panelContent = new JPanel();
		add(panelContent);
		GridBagLayout gbl_panelContent = new GridBagLayout();
		gbl_panelContent.columnWidths = new int[] { 0, 0, 80, 75, 75, 0 };
		gbl_panelContent.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelContent.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelContent.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelContent.setLayout(gbl_panelContent);

		JLabel lblNewLabel = new JLabel("Product Name");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelContent.add(lblNewLabel, gbc_lblNewLabel);

		textFieldName = new JTextField();
		textFieldName.setEditable(false);
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.gridwidth = 4;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		panelContent.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Old Price");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panelContent.add(lblNewLabel_1, gbc_lblNewLabel_1);

		NumberFormat numberFormat = new DecimalFormat("#,###.##");
		NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
		numberFormatter.setValueClass(Double.class);

		formattedTextFieldOldPrice = new JFormattedTextField(numberFormatter);
		formattedTextFieldOldPrice.setEditable(false);
		GridBagConstraints gbc_formattedTextFieldOldPrice = new GridBagConstraints();
		gbc_formattedTextFieldOldPrice.gridwidth = 4;
		gbc_formattedTextFieldOldPrice.insets = new Insets(0, 0, 5, 5);
		gbc_formattedTextFieldOldPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldOldPrice.gridx = 1;
		gbc_formattedTextFieldOldPrice.gridy = 1;
		panelContent.add(formattedTextFieldOldPrice, gbc_formattedTextFieldOldPrice);

		JLabel lblNewLabel_2 = new JLabel("New Price");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panelContent.add(lblNewLabel_2, gbc_lblNewLabel_2);

		formattedTextFieldNewPrice = new JFormattedTextField(numberFormatter);
		GridBagConstraints gbc_formattedTextFieldNewPrice = new GridBagConstraints();
		gbc_formattedTextFieldNewPrice.gridwidth = 4;
		gbc_formattedTextFieldNewPrice.insets = new Insets(0, 0, 5, 5);
		gbc_formattedTextFieldNewPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldNewPrice.gridx = 1;
		gbc_formattedTextFieldNewPrice.gridy = 2;
		panelContent.add(formattedTextFieldNewPrice, gbc_formattedTextFieldNewPrice);

		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 3;
		panelContent.add(btnSave, gbc_btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 3;
		panelContent.add(btnCancel, gbc_btnCancel);

	}

	public EditProductPrice(Products product) {
		this();
		this.product = product;
		init();
	}

	private void init() {
		textFieldName.setText(product.getName());
		formattedTextFieldOldPrice.setValue(product.getPrice());
	}
	
	public void btnCancel_actionPerformed(ActionEvent e) {
		closeDialog();
	}

	public void btnSave_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Save", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			if(formattedTextFieldOldPrice.getValue() == formattedTextFieldNewPrice.getValue()) {
				JOptionPane.showMessageDialog(null, "New price cannot = old price!");
				return;
			}
			ProductModel productModel = new ProductModel();
			if (productModel.updatePrice((double) formattedTextFieldNewPrice.getValue(), product.getProduct_code())) {
				JOptionPane.showMessageDialog(null, "Done!");
				closeDialog();
			} else {
				JOptionPane.showMessageDialog(null, "Failed!");
			}
		}
		return;
	}
	
	private void closeDialog() {
		java.awt.Window thisWindow = SwingUtilities.getWindowAncestor(this);	
		if (thisWindow instanceof JDialog) {
			JDialog dialog = (JDialog) thisWindow;			
			dialog.dispose();
		}
	}
}
