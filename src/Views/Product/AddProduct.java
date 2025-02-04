package Views.Product;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import Entities.Categories;
import Entities.Products;
import Entities.Units;
import Models.Categories.CategoriesModel;
import Models.Product.ProductModel;
import Models.Unit.UnitModel;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import java.awt.FlowLayout;

public class AddProduct extends JPanel {
	private JTextField textField;
	private JLabel jlabelPhoto;
	private JComboBox<Units> comboBoxUnit;
	private JFormattedTextField formattedTextFieldPrice;
	private JFormattedTextField formattedTextFieldQuantity;
	private JFormattedTextField formattedTextFieldDiscount;
	private JButton btnBrowse;
	private String imagePath;
	private JComboBox<Categories> comboBoxCate;
	private ProductModel productModel = new ProductModel();
	private File selectedFile;

	/**
	 * Create the panel.
	 */
	public AddProduct() {
		setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("ADD PRODUCTS");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);

		JPanel panelContent = new JPanel();
		add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panelContent.add(scrollPane);

		JPanel jpanelADD = new JPanel();
		jpanelADD.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(jpanelADD);
		GridBagLayout gbl_jpanelADD = new GridBagLayout();
		gbl_jpanelADD.columnWidths = new int[] { 0, 104, 111, 70, 0 };
		gbl_jpanelADD.rowHeights = new int[] { 0, 0, 0, 0, 0, 32, 0, 0 };
		gbl_jpanelADD.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_jpanelADD.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		jpanelADD.setLayout(gbl_jpanelADD);

		JLabel lblName = new JLabel("Product Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		jpanelADD.add(lblName, gbc_lblName);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		jpanelADD.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 1;
		jpanelADD.add(lblPrice, gbc_lblPrice);

		formattedTextFieldPrice = new JFormattedTextField();
		GridBagConstraints gbc_formattedTextFieldPrice = new GridBagConstraints();
		gbc_formattedTextFieldPrice.gridwidth = 2;
		gbc_formattedTextFieldPrice.insets = new Insets(0, 0, 5, 5);
		gbc_formattedTextFieldPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldPrice.gridx = 1;
		gbc_formattedTextFieldPrice.gridy = 1;
		jpanelADD.add(formattedTextFieldPrice, gbc_formattedTextFieldPrice);

		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 2;
		jpanelADD.add(lblQuantity, gbc_lblQuantity);

		formattedTextFieldQuantity = new JFormattedTextField();
		GridBagConstraints gbc_formattedTextFieldQuantity = new GridBagConstraints();
		gbc_formattedTextFieldQuantity.gridwidth = 2;
		gbc_formattedTextFieldQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_formattedTextFieldQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldQuantity.gridx = 1;
		gbc_formattedTextFieldQuantity.gridy = 2;
		jpanelADD.add(formattedTextFieldQuantity, gbc_formattedTextFieldQuantity);

		JLabel lblImage = new JLabel("Image");
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.anchor = GridBagConstraints.WEST;
		gbc_lblImage.insets = new Insets(0, 0, 5, 5);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 3;
		jpanelADD.add(lblImage, gbc_lblImage);

		jlabelPhoto = new JLabel("");
		jlabelPhoto.setMinimumSize(new Dimension(100, 120));
		jlabelPhoto.setPreferredSize(new Dimension(100, 120));
		jlabelPhoto.setBorder(new TitledBorder(null, "Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_jlabelPhoto = new GridBagConstraints();
		gbc_jlabelPhoto.fill = GridBagConstraints.HORIZONTAL;
		gbc_jlabelPhoto.insets = new Insets(0, 0, 5, 5);
		gbc_jlabelPhoto.gridx = 1;
		gbc_jlabelPhoto.gridy = 3;
		jpanelADD.add(jlabelPhoto, gbc_jlabelPhoto);

		btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBrowse_actionPerformed(e);
			}
		});

		GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
		gbc_btnBrowse.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBrowse.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowse.gridx = 3;
		gbc_btnBrowse.gridy = 3;
		jpanelADD.add(btnBrowse, gbc_btnBrowse);

		JLabel lblDiscount = new JLabel("Discount");
		GridBagConstraints gbc_lblDiscount = new GridBagConstraints();
		gbc_lblDiscount.anchor = GridBagConstraints.WEST;
		gbc_lblDiscount.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiscount.gridx = 0;
		gbc_lblDiscount.gridy = 4;
		jpanelADD.add(lblDiscount, gbc_lblDiscount);

		formattedTextFieldDiscount = new JFormattedTextField();
		GridBagConstraints gbc_formattedTextFieldDiscount = new GridBagConstraints();
		gbc_formattedTextFieldDiscount.gridwidth = 2;
		gbc_formattedTextFieldDiscount.insets = new Insets(0, 0, 5, 5);
		gbc_formattedTextFieldDiscount.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextFieldDiscount.gridx = 1;
		gbc_formattedTextFieldDiscount.gridy = 4;
		jpanelADD.add(formattedTextFieldDiscount, gbc_formattedTextFieldDiscount);

		JLabel lblUnit = new JLabel("Unit");
		GridBagConstraints gbc_lblUnit = new GridBagConstraints();
		gbc_lblUnit.anchor = GridBagConstraints.WEST;
		gbc_lblUnit.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnit.gridx = 0;
		gbc_lblUnit.gridy = 5;
		jpanelADD.add(lblUnit, gbc_lblUnit);

		comboBoxUnit = new JComboBox<Units>();

		GridBagConstraints gbc_comboBoxUnit = new GridBagConstraints();
		gbc_comboBoxUnit.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxUnit.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUnit.gridx = 1;
		gbc_comboBoxUnit.gridy = 5;
		jpanelADD.add(comboBoxUnit, gbc_comboBoxUnit);

		JLabel lblCategories = new JLabel("Categories");
		GridBagConstraints gbc_lblCategories = new GridBagConstraints();
		gbc_lblCategories.anchor = GridBagConstraints.WEST;
		gbc_lblCategories.insets = new Insets(0, 0, 0, 5);
		gbc_lblCategories.gridx = 0;
		gbc_lblCategories.gridy = 6;
		jpanelADD.add(lblCategories, gbc_lblCategories);

		comboBoxCate = new JComboBox<Categories>();
		GridBagConstraints gbc_comboBoxCate = new GridBagConstraints();
		gbc_comboBoxCate.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxCate.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCate.gridx = 1;
		gbc_comboBoxCate.gridy = 6;
		jpanelADD.add(comboBoxCate, gbc_comboBoxCate);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Save(e);
			}
		});
		panel.add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancel(e);
			}
		});
		panel.add(btnCancel);

		init();
	}

	private void init() {
		comboUnitData();
		comboCateData();
		formatNumber(formattedTextFieldDiscount, Integer.class);
		formatNumber(formattedTextFieldQuantity, Double.class);
		formatNumber(formattedTextFieldPrice, Double.class);
	}

	private void comboCateData() {
		CategoriesModel catesModel = new CategoriesModel();
		List<Categories> catesList = catesModel.findAllCategories();
		if (!catesList.isEmpty()) {
			DefaultComboBoxModel<Categories> model = new DefaultComboBoxModel<>();

			for (Categories category : catesList) {
				model.addElement(category);
			}
			comboBoxCate.setModel(model);
			comboBoxCate.setRenderer(new comboBoxCateRenderer());
		}
	}

	private void comboUnitData() {
		UnitModel unitModel = new UnitModel();
		List<Units> unitList = unitModel.findAll();
		DefaultComboBoxModel<Units> model = new DefaultComboBoxModel<>();
		for (Units units : unitList) {
			model.addElement(units);
		}
		comboBoxUnit.setModel(model);
		comboBoxUnit.setRenderer(new comboBoxUnitRenderer());
	}

	private class comboBoxUnitRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			// TODO Auto-generated method stub
			Units unit = (Units) value;
			return super.getListCellRendererComponent(list, unit.getUnit_name(), index, isSelected, cellHasFocus);
		}

	}

	private class comboBoxCateRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			// TODO Auto-generated method stub
			Categories cate = (Categories) value;
			return super.getListCellRendererComponent(list, cate.getName(), index, isSelected, cellHasFocus);
		}

	}

	public void btnBrowse_actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		// Set the file chooser to accept only image files
		fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));
		int returnValue = fileChooser.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
			// You can now use the selectedFile to get the chosen image file
			imagePath = selectedFile.getAbsolutePath();
			int width = jlabelPhoto.getWidth();
			int height = jlabelPhoto.getHeight();
			// You can also display the selected image in your JLabel (jlabelPhoto)
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage()
					.getScaledInstance(width, height, Image.SCALE_DEFAULT));
			jlabelPhoto.setIcon(imageIcon);
			// Display the selected image path or do whatever you need with it
		}
	}

	public void Save(ActionEvent e) {
		try {
			String productName = textField.getText();
			Double price = (Double) formattedTextFieldPrice.getValue();
			Double quantity = (Double) formattedTextFieldQuantity.getValue();
			Integer discount = (Integer) formattedTextFieldDiscount.getValue();
			Units selectedUnit = (Units) comboBoxUnit.getSelectedItem();

			if (productName == null || price == null || quantity == null || selectedFile == null) {
				JOptionPane.showMessageDialog(null, "All Fields Required!");
				return;
			}

			byte[] photo = Files.readAllBytes(Paths.get(imagePath));
			Products product = new Products();
			product.setProduct_code(UUID.randomUUID().toString());
			product.setName(productName);
			product.setPrice(price);
			product.setQuantity(quantity);
			product.setImage(photo);
			product.setDiscount_percent(discount);
			product.setDisable(false);
			product.setCreated_at(new Date());
			product.setUnit_id(selectedUnit.getUnit_id());

			if (productModel.create(product)) {
				JOptionPane.showMessageDialog(null, "Done!");
				clearForm();
				return;
			} else {
				JOptionPane.showMessageDialog(null, "Failed!");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void Cancel(ActionEvent e) {
		this.removeAll();
		this.revalidate();
		this.repaint();
		JPanelProduct jPanelProduct = new JPanelProduct();
		this.setVisible(true);
		this.add(jPanelProduct);
	}

	private void clearForm() {
		textField.setText("");
		formattedTextFieldPrice.setValue(null);
		formattedTextFieldQuantity.setValue(null);
		jlabelPhoto.setIcon(null);
		formattedTextFieldDiscount.setValue(null);
		comboBoxUnit.setSelectedIndex(0);
		comboBoxCate.setSelectedIndex(0);
	}

	private void formatNumber(JFormattedTextField textField, Class<?> valueClass) {
		NumberFormat numberFormat;
		if (valueClass == Double.class) {
			numberFormat = new DecimalFormat("#,###.##");
		} else if (valueClass == Integer.class) {
			numberFormat = new DecimalFormat("#,###");
		} else {
			// Handle other numeric types if needed
			numberFormat = new DecimalFormat(); // Default format
		}

		NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
		numberFormatter.setValueClass(valueClass);

		textField.setFormatterFactory(new DefaultFormatterFactory(numberFormatter));
	}

}
