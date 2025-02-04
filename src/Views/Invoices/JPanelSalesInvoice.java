package Views.Invoices;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import Entities.Categories;
import Entities.Invoice_detail;
import Entities.Product_category;
import Entities.Products;
import Models.Categories.CategoriesModel;
import Models.Product.ProductModel;
import Views.Product.Product_Detail;

import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JComboBox;

public class JPanelSalesInvoice extends JPanel {
	private ProductModel productModel = new ProductModel();
	private JPanel panelList;
	private static JPanel jpanelInformation;
	private static Products selectedProduct = new Products();
	private static List<JPanel> informationPanels = new ArrayList<>();
	private static JDialog dialog = new JDialog();
	private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
	private JTextField textFieldSearch;
	private JComboBox jcomboBoxCategory;

	/**
	 * Create the panel.
	 */
	public JPanelSalesInvoice() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JPanel panelSearch = new JPanel();
		panel.add(panelSearch);

		JLabel jlblSearch = new JLabel("Search");
		panelSearch.add(jlblSearch);

		textFieldSearch = new JTextField();
		textFieldSearch.setColumns(10);
		panelSearch.add(textFieldSearch);

		JButton jbtnSearch = new JButton("Search");
		jbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSearch_actionPerformed(e);
			}
		});
		panelSearch.add(jbtnSearch);

		jcomboBoxCategory = new JComboBox();
		jcomboBoxCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcomboBoxCategory_actionPerformed(e);
			}
		});
		panelSearch.add(jcomboBoxCategory);

		JPanel panelShowList = new JPanel();
		panelShowList.setBorder(new TitledBorder(null, "List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelShowList);
		panelShowList.setLayout(new BoxLayout(panelShowList, BoxLayout.X_AXIS));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelShowList.add(scrollPane_1);

		panelList = new JPanel();
		panelList.setPreferredSize(new Dimension(1100, 1600));
		scrollPane_1.setViewportView(panelList);
		panelList.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		jpanelInformation = new JPanel();
		jpanelInformation
				.setBorder(new TitledBorder(null, "information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(jpanelInformation);
		jpanelInformation.setLayout(new BoxLayout(jpanelInformation, BoxLayout.PAGE_AXIS));

		JPanel panel_4 = new JPanel();
		panel_4.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel.add(panel_4);

		JButton jbtnProceed = new JButton("Proceed");
		jbtnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnProceed_actionPerformed(e);
			}
		});
		panel_4.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));

		panel_4.add(jbtnProceed);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		showcombodata();
		initProductCard();
	}

	private void initProductCard() {
		panelList.removeAll();
		for (Products product : productModel.findAll()) {
			Product_Detail productDetail = new Product_Detail(product);
			panelList.add(productDetail);
		}
		panelList.revalidate();
		panelList.repaint();
	}

	public void jbtnProceed_actionPerformed(ActionEvent e) {
		// Create a list to store BuyProductInfo objects
		List<Invoice_detail> buyProducts = new ArrayList<Invoice_detail>();
		// Iterate through the information panels
		for (JPanel infoPanel : informationPanels) {
			// Extract code, quantity, and product ID from the info panel
			JLabel productID = (JLabel) infoPanel.getComponent(4);
			JTextField quantityField = (JTextField) infoPanel.getComponent(3);
			JLabel priceLabel = (JLabel) infoPanel.getComponent(5);
			JLabel discountLabel = (JLabel) infoPanel.getComponent(6);
			JLabel stockedProductQuantity = (JLabel) infoPanel.getComponent(7);
			// Get the values
			String id = productID.getText();
			double stocked = Double.parseDouble(stockedProductQuantity.getText());
			double price = Double.parseDouble(priceLabel.getText());
			int discount = (int) Double.parseDouble(discountLabel.getText());
			// Check if the input in quantityField is not numeric
			if (!isNumeric(quantityField.getText())) {
				JOptionPane.showMessageDialog(null, "Invalid Quantity");
				quantityField.requestFocus();
				return;
			}
			// Parse the quantity as a double, rounding it to two decimal places
			double quantity = Double.parseDouble(decimalFormat.format(Double.parseDouble(quantityField.getText())));
			// Check if the quantity is greater than the stocked quantity or less than or
			// equal to 0
			if (quantity > stocked || quantity <= 0) {
				JOptionPane.showMessageDialog(null, "Invalid Quantity");
				quantityField.requestFocus();
				return;
			}
			// Create a BuyProductInfo object and add it to the list
			Invoice_detail invoiceDetail = new Invoice_detail();
			invoiceDetail.setId(0);
			invoiceDetail.setProduct_code(id);
			invoiceDetail.setInvoice_id("");
			invoiceDetail.setProduct_price(price);
			invoiceDetail.setProduct_quantity(quantity);
			invoiceDetail.setIs_discount(discount > 0 ? true : false);
			invoiceDetail.setDiscount_percent(discount);
			invoiceDetail.setReturned(false);
			invoiceDetail.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));

			buyProducts.add(invoiceDetail);
		}

		if (buyProducts.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No Product");
			return;
		} else {
			// Create a modal dialog to display the invoice detail frame
			dialog = new JDialog();
			dialog.setTitle("Invoice Detail");
			dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

			JFrameInvoiceDetail invoiceDetail = new JFrameInvoiceDetail(buyProducts, null);
			dialog.getContentPane().add(invoiceDetail.getContentPane());
			dialog.pack();
			dialog.setVisible(true);
		}
	}

	public static void dispose() {
		dialog.dispose();
	}

	public void jcomboBoxCategory_actionPerformed(ActionEvent e) {
		panelList.removeAll();
		CategoriesModel categoriesModel = new CategoriesModel();
		String categoryName = (String) jcomboBoxCategory.getSelectedItem();
		if(categoryName == "ALL") {
			initProductCard();
			return;
		}
		int categoryId = categoriesModel.findCategoryID((String) jcomboBoxCategory.getSelectedItem());
		List<Product_category> categoryList = categoriesModel.findAllProdcutcodebycategoryID(categoryId);
		List<Products> searchByCateList = new ArrayList<Products>();
		for (Product_category product_category : categoryList) {
			searchByCateList.add(productModel.findById(product_category.getProduct_code()));
		}
		if (searchByCateList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No Result!");
			return;
		}
		for (Products product : searchByCateList) {
			Product_Detail productDetail = new Product_Detail(product);
			panelList.add(productDetail);
		}
		panelList.revalidate();
		panelList.repaint();
	}

	public void jbtnSearch_actionPerformed(ActionEvent e) {
		panelList.removeAll();
		List<Products> searchByKeywordList = productModel.findbyname(textFieldSearch.getText());
		if (searchByKeywordList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No Result!");
			return;
		}
		for (Products product : searchByKeywordList) {
			Product_Detail productDetail = new Product_Detail(product);
			panelList.add(productDetail);
		}
		panelList.revalidate();
		panelList.repaint();
	}

	private void showcombodata() {
		List<Categories> categories = new CategoriesModel().findAllCategories();
		jcomboBoxCategory.addItem("ALL");
		for (Categories categorie : categories) {
			jcomboBoxCategory.addItem(categorie.getName());
		}
	}

	public static void getBuyProductInformation(Products buyProduct) {
		selectedProduct = buyProduct;
		/*
		 * Iterate through existing information panels to check if the product is
		 * already selected
		 */
		for (JPanel infoPanel : informationPanels) {
			// Extract the JLabel containing the product ID from the info panel
			JLabel buyProductId = (JLabel) infoPanel.getComponent(4);
			// Check if the product code matches the selected product's code
			if (selectedProduct.getProduct_code().equals(buyProductId.getText())) {
				// If found, increase the quantity by 1
				JTextField quantityField = (JTextField) infoPanel.getComponent(3);
				double quantity = Double.parseDouble(quantityField.getText());
				quantity++;
				quantityField.setText(Double.toString(quantity));
				return; // Exit the method
			}
		}
		// Create a new panel for the selected product
		JPanel panelBuyProductItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelBuyProductItem.setPreferredSize(new Dimension(400, 50));
		// Create labels, text fields, buttons, and a hidden label for product ID
		JLabel buyProductNameLabel = new JLabel("Name:");
		JTextField buyProductNameTextField = new JTextField(selectedProduct.getName());
		buyProductNameTextField.setColumns(15);
		JLabel quantityLabel = new JLabel("quantity");
		JTextField quantityTextField = new JTextField("1");
		quantityTextField.setColumns(5);
		JButton button1 = new JButton("+");
		JButton button2 = new JButton("-");
		JButton button3 = new JButton("x");
		JLabel buyProductId = new JLabel(selectedProduct.getProduct_code());
		JLabel buyProductPrice = new JLabel(String.valueOf(selectedProduct.getPrice()));
		JLabel buyProductDiscount = new JLabel(String.valueOf(selectedProduct.getDiscount_percent()));
		JLabel buyProductQuantity = new JLabel(String.valueOf(selectedProduct.getQuantity()));
		buyProductId.setVisible(false);
		buyProductPrice.setVisible(false);
		buyProductDiscount.setVisible(false);
		buyProductQuantity.setVisible(false);
		// Add components to the panel
		panelBuyProductItem.add(buyProductNameLabel);
		panelBuyProductItem.add(buyProductNameTextField);
		panelBuyProductItem.add(quantityLabel);
		panelBuyProductItem.add(quantityTextField);
		panelBuyProductItem.add(buyProductId);
		panelBuyProductItem.add(buyProductPrice);
		panelBuyProductItem.add(buyProductDiscount);
		panelBuyProductItem.add(buyProductQuantity);
		panelBuyProductItem.add(button1);
		panelBuyProductItem.add(button2);
		panelBuyProductItem.add(button3);
		// Add action listeners for the +, -, and x buttons
		button1.addActionListener(e -> {
			if (!isNumeric(quantityTextField.getText())) {
				JOptionPane.showMessageDialog(null, "Invalid Quantity");
				quantityTextField.requestFocusInWindow();
				return;
			}
			double value = Double.parseDouble(quantityTextField.getText());
			value++;
			quantityTextField.setText(decimalFormat.format(value));
		});

		button2.addActionListener(e -> {
			if (!isNumeric(quantityTextField.getText())) {
				JOptionPane.showMessageDialog(null, "Invalid Quantity");
				quantityTextField.requestFocusInWindow();
				return;
			}
			double value = Double.parseDouble(quantityTextField.getText());
			if (value > 1) {
				value--;
				quantityTextField.setText(decimalFormat.format(value));
			}
		});

		button3.addActionListener(e -> {
			// Remove the panel from jpanelInformation
			jpanelInformation.remove(panelBuyProductItem);
			informationPanels.remove(panelBuyProductItem);
			// Revalidate and repaint jpanelInformation to update the UI
			jpanelInformation.revalidate();
			jpanelInformation.repaint();
		});
		// Add the new panel to the list of information panels
		informationPanels.add(panelBuyProductItem);
		// Add the new panel to the jpanelInformation
		jpanelInformation.add(panelBuyProductItem);
		// Revalidate and repaint jpanelInformation to update the UI
		jpanelInformation.revalidate();
		jpanelInformation.repaint();
	}

	public static void clearInformationPanel() {
		for (JPanel infoPanel : informationPanels) {
			jpanelInformation.remove(infoPanel);
		}
		informationPanels.clear(); // Clear the list of information panels
		jpanelInformation.revalidate();
		jpanelInformation.repaint();
	}

	// Checks if a given string represents a numeric value.
	private static boolean isNumeric(String string) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		try {
			double d = Double.parseDouble(string);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
