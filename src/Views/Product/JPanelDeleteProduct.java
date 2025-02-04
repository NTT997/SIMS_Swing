package Views.Product;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Entities.Products;
import Models.Product.ProductModel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class JPanelDeleteProduct extends JPanel {
	private JTable tableList;
	private JPopupMenu popupMenu;
	private JMenuItem restore;
	private ProductModel productModel = new ProductModel();
	/**
	 * Create the panel.
	 */
	public JPanelDeleteProduct() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel panelList = new JPanel();
		add(panelList);
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panelList.add(scrollPane);

		tableList = new JTable();
		tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showPopupMenu(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopupMenu(e);
			}
		});
		scrollPane.setViewportView(tableList);

		JPanel panelbtn = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelbtn.getLayout();
		add(panelbtn);

		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init();
			}
		});
		panelbtn.add(btnReload);

		JPanel panel0 = new JPanel();
		add(panel0);
		panel0.setLayout(new BorderLayout(0, 0));
		
		popupMenu = new JPopupMenu();
		restore = new JMenuItem("Restore");
		restore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				restore_actionPerformed(e);
			}
		});
		popupMenu.add(restore);
		init();
	}
	
	private void init() {
		fillDataToTable(productModel.findDeleted());
	}
	
	public void restore_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			int selectedRow = tableList.getSelectedRow();
			DefaultTableModel model = (DefaultTableModel) tableList.getModel();
			String productCode = (String) tableList.getValueAt(selectedRow, model.findColumn("productCode"));
			if (productModel.delete(null, productCode, false)) {
				JOptionPane.showMessageDialog(null, "Done!");
			} else {
				JOptionPane.showMessageDialog(null, "Failed!");
			}
			return;
		}
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
		model.addColumn("Deleted At");

		for (int i = 0; i < productList.size(); i++) {
			Products product = productList.get(i);
			// Insert into column "Image"
			ImageIcon imageIcon = new ImageIcon(product.getImage());
			model.addRow(new Object[] { i + 1,
					product.getName(),
					product.getPrice(),
					product.getQuantity(),
					imageIcon,
					product.getProduct_code(),
					product.isDisable(),
					product.getDeleted_at() });
		}

		tableList.setModel(model);
		tableList.getTableHeader().setReorderingAllowed(false);
		hideColumn(model.findColumn("productCode"));
		hideColumn(model.findColumn("isDisabled"));
		int imageColumnIndex = model.findColumn("Image");
		int columnIndex = model.findColumn("Image");
		int desiredWidth = 100;

		tableList.getColumnModel().getColumn(columnIndex).setPreferredWidth(desiredWidth);
		int desiredHeight = 100;
		tableList.getColumnModel().getColumn(imageColumnIndex)
				.setCellRenderer(new ImageRenderer(desiredWidth, desiredHeight));
	}

	private void hideColumn(int columnIndex) {
		tableList.getColumnModel().getColumn(columnIndex).setMinWidth(0);
		tableList.getColumnModel().getColumn(columnIndex).setMaxWidth(0);
		tableList.getColumnModel().getColumn(columnIndex).setWidth(0);
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
	
	private void showPopupMenu(MouseEvent e) {
		int selectedRow = tableList.getSelectedRow();
		if (selectedRow != -1) {
			if (e.isPopupTrigger()) {
				popupMenu.show(tableList, e.getX(), e.getY());
			}
		}
	}
}
