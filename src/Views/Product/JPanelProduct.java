package Views.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Entities.Categories;
import Entities.Employees;
import Entities.Product_category;
import Entities.Products;
import Models.Categories.CategoriesModel;
import Models.Employees.EmployeeModel;
import Models.Product.ProductModel;
import Views.Dashboard.JFrameDashBoard;
import Views.Invoices.JPanelImportInvoicesProduct;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JPanelProduct extends JPanel {
	private JTable jtable1;
	private JScrollPane scrollPane;
	private JPopupMenu popupMenu;
	private JTextField jtextFieldkeyword;
	private JComboBox jcomboBoxCategory;
	private ProductModel productModel = new ProductModel();
	private EmployeeModel employeeModel = new EmployeeModel();
	private Employees employee = employeeModel.FindByEmployeeID(JFrameDashBoard.getEmployeeId());
	private JMenuItem menuItemEdit;
	private JMenuItem menuItemDelete;
	private JButton btnAdd;
	private JButton btnImport;

	public JPanelProduct() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		btnAdd = new JButton("Add");
		btnAdd.setVisible(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdd_actionPerformed(e);
			}
		});
		panel.add(btnAdd);
		
		btnImport = new JButton("Import");
		btnImport.setVisible(false);
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnImport_actionPerformed(e);
			}
		});
		panel.add(btnImport);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Product List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		panel_1.add(lblNewLabel);

		JPanel jpanelTop = new JPanel();
		panel_1.add(jpanelTop);
		jpanelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel jlblSearch = new JLabel("Search");
		jpanelTop.add(jlblSearch);

		jtextFieldkeyword = new JTextField();
		jtextFieldkeyword.setColumns(10);
		jpanelTop.add(jtextFieldkeyword);

		JButton jbtnSearch = new JButton("Search");
		jbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSearch_actionPerformed(e);
			}
		});
		jpanelTop.add(jbtnSearch);

		jcomboBoxCategory = new JComboBox();
		jcomboBoxCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcomboBoxCategory_actionPerformed(e);
			}
		});
		jpanelTop.add(jcomboBoxCategory);

		JButton btnNewButton_2 = new JButton("Home");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(JPanelProduct.class.getResource("/Resources/Icons/Back.png")));
		panel_1.add(btnNewButton_2);

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		jtable1 = new JTable();
		jtable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showPopupMenu(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopupMenu(e);
			}
		});
		scrollPane.setViewportView(jtable1);

		popupMenu = new JPopupMenu();
		menuItemEdit = new JMenuItem("Edit");
		menuItemEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuItemEdit_actionPerformed(e);
			}
		});

		menuItemDelete = new JMenuItem("Delete");
		menuItemDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuItemDelete_actionPerformed(e);
			}
		});
		popupMenu.add(menuItemEdit);
		popupMenu.add(menuItemDelete);
		popupMenu.setVisible(false);
		menuItemEdit.setVisible(false);
		menuItemDelete.setVisible(false);
		init();
	}

	private void init() {
		fillDataToTable(productModel.findAll());
		showcombodata();
		employeeIsAdmin();
	}

	private void fillDataToTable(List<Products> productList) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("STT");
		model.addColumn("Name");
		model.addColumn("Price (VND)");
		model.addColumn("Quantity");
		model.addColumn("Image");
		model.addColumn("productCode");
		model.addColumn("isDisabled");

		for (int i = 0; i < productList.size(); i++) {
			Products product = productList.get(i);
			// Insert into column "Image"
			ImageIcon imageIcon = new ImageIcon(product.getImage());
			model.addRow(new Object[] { i + 1, product.getName(), product.getPrice(), product.getQuantity(), imageIcon,
					product.getProduct_code(), product.isDisable() });
		}

		jtable1.setModel(model);
		jtable1.getTableHeader().setReorderingAllowed(false);
		hideColumn(model.findColumn("productCode"));
		hideColumn(model.findColumn("isDisabled"));
		int imageColumnIndex = model.findColumn("Image");
		int columnIndex = model.findColumn("Image");
		int desiredWidth = 100;

		jtable1.getColumnModel().getColumn(columnIndex).setPreferredWidth(desiredWidth);
		int desiredHeight = 100;
		jtable1.getColumnModel().getColumn(imageColumnIndex)
				.setCellRenderer(new ImageRenderer(desiredWidth, desiredHeight));
	}

	private void hideColumn(int columnIndex) {
		jtable1.getColumnModel().getColumn(columnIndex).setMinWidth(0);
		jtable1.getColumnModel().getColumn(columnIndex).setMaxWidth(0);
		jtable1.getColumnModel().getColumn(columnIndex).setWidth(0);
	}

	public void jbtnSearch_actionPerformed(ActionEvent e) {
		String keyword = jtextFieldkeyword.getText().trim();
		fillDataToTable(productModel.findbyname(keyword));
	}

	public class ImageRenderer extends DefaultTableCellRenderer {
		private int width;
		private int height;

		public ImageRenderer(int width, int height) {
			this.width = width;
			this.height = height;
		}

		@Override
		protected void setValue(Object value) {
			if (value instanceof ImageIcon) {
				ImageIcon imageIcon = (ImageIcon) value;
				Image image = imageIcon.getImage();
				Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				setIcon(new ImageIcon(scaledImage));
			} else {
				super.setValue(value);
			}
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			table.setRowHeight(row, height);
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}
	
	public void btnAdd_actionPerformed(ActionEvent e) {
		this.removeAll();
		this.revalidate();
		this.repaint();
		AddProduct addProduct = new AddProduct();
		this.add(addProduct);	
		this.setVisible(true);
	}

	public void menuItemEdit_actionPerformed(ActionEvent e) {
		int selectedRow = jtable1.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel) jtable1.getModel();

		if (selectedRow != -1) {
			String productCode = (String) jtable1.getValueAt(selectedRow, model.findColumn("productCode"));
			String productName = (String) jtable1.getValueAt(selectedRow, model.findColumn("Name"));
			double productPrice = (double) jtable1.getValueAt(selectedRow, model.findColumn("Price (VND)"));
//			double productPrice = Double.parseDouble(productPriceString);

			Products editProduct = new Products();
			editProduct.setProduct_code(productCode);
			editProduct.setName(productName);
			editProduct.setPrice(productPrice);
			JPanel EditProductPrice = new EditProductPrice(editProduct);
			createAndShowDialog("Edit Product", EditProductPrice);
		}
		return;
	}

	public void menuItemDelete_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			int selectedRow = jtable1.getSelectedRow();
			DefaultTableModel model = (DefaultTableModel) jtable1.getModel();
			String productCode = (String) jtable1.getValueAt(selectedRow, model.findColumn("productCode"));
			if (productModel.delete(new Date(), productCode, true)) {
				JOptionPane.showMessageDialog(null, "Done!");
			} else {
				JOptionPane.showMessageDialog(null, "Failed!");
			}
			fillDataToTable(productModel.findAll());
			return;
		}
		return;
	}

	private void showcombodata() {
		List<Categories> categories = new CategoriesModel().findAllCategories();
		jcomboBoxCategory.addItem("ALL");
		for (Categories categorie : categories) {
			jcomboBoxCategory.addItem(categorie.getName());
		}
	}

	public void jcomboBoxCategory_actionPerformed(ActionEvent e) {
		CategoriesModel categoriesModel = new CategoriesModel();
		String namecate = jcomboBoxCategory.getSelectedItem().toString();

		if (namecate == "ALL") {
			fillDataToTable(productModel.findAll());
			return;
		}

		int categoryID = categoriesModel.findCategoryID(namecate);
		List<Products> products = new ArrayList<>();

		for (Product_category productCategory : categoriesModel.findAllProdcutcodebycategoryID(categoryID)) {
			String productCode = productCategory.getProduct_code();
			products.add(productModel.findById(productCode));
		}

		fillDataToTable(products);
	}

	private void showPopupMenu(MouseEvent e) {
		int selectedRow = jtable1.getSelectedRow();
		if (selectedRow != -1) {
			if (e.isPopupTrigger()) {
				popupMenu.show(jtable1, e.getX(), e.getY());
			}
		}
	}

	private void employeeIsAdmin() {
		if (employee.getEmployee_id() == 1) {
			popupMenu.setVisible(true);
			menuItemEdit.setVisible(true);
			menuItemDelete.setVisible(true);
			btnAdd.setVisible(true);
			btnImport.setVisible(true);
		}
	}
	
	public void jbtnImport_actionPerformed(ActionEvent e) {
		JPanelImportInvoicesProduct jPanelImportInvoicesProduct = new JPanelImportInvoicesProduct();
		createAndShowDialog("Import Product", jPanelImportInvoicesProduct);
	}

	private void createAndShowDialog(String title, JPanel panel) {
		JDialog dialog = new JDialog();
		dialog.setTitle(title);
		dialog.getContentPane().add(panel);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.pack();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}
}
