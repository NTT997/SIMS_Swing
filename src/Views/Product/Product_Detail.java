package Views.Product;

import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import Entities.Products;
import Views.Invoices.JPanelImportInvoicesProduct;
import Views.Invoices.JPanelSalesInvoice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

public class Product_Detail extends JPanel {
	private Products buyProduct;
	private JLabel JpanelDiscount;
	private JLabel lblImage;
	private JLabel jlabelProductname;
	private JLabel jlabelCost;
	private JLabel jlabeltag;
	private JPanel jpanelquantyti;
	private JLabel jlabelQuantyti;
	private JLabel lblNewLabel;
	private JLabel jlabelVND2;
	private String keyword;
	private JPanel jpanelPriceDiscount;

	/**
	 * Create the panel.
	 */
	public Product_Detail() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setAutoscrolls(true);
		setMinimumSize(new Dimension(200, 250));
		setAlignmentY(0.0f);
		setAlignmentX(0.0f);
		setMaximumSize(new Dimension(200, 250));
		setPreferredSize(new Dimension(200, 260));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel jpanelImage = new JPanel();
		add(jpanelImage);
		jpanelImage.setLayout(new BoxLayout(jpanelImage, BoxLayout.X_AXIS));

		lblImage = new JLabel("");
		lblImage.setPreferredSize(new Dimension(200, 150));
		lblImage.setMinimumSize(new Dimension(200, 150));
		lblImage.setMaximumSize(new Dimension(200, 150));
		lblImage.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblImage.setAlignmentY(0.0f);
		jpanelImage.add(lblImage);

		JPanel jpanelName = new JPanel();
		add(jpanelName);
		jpanelName.setLayout(new BoxLayout(jpanelName, BoxLayout.X_AXIS));

		jlabelProductname = new JLabel("Product Name");
		jlabelProductname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jpanelName.add(jlabelProductname);

		JPanel jpanelPrice = new JPanel();
		add(jpanelPrice);
		jpanelPrice.setLayout(new BoxLayout(jpanelPrice, BoxLayout.PAGE_AXIS));

		jpanelquantyti = new JPanel();
		jpanelPrice.add(jpanelquantyti);

		jlabelQuantyti = new JLabel("New label");
		jpanelquantyti.add(jlabelQuantyti);

		JPanel jpanelPriceDefault = new JPanel();
		jpanelPrice.add(jpanelPriceDefault);

		jlabelCost = new JLabel("20.000");
		jpanelPriceDefault.add(jlabelCost);

		lblNewLabel = new JLabel("VNĐ");
		jpanelPriceDefault.add(lblNewLabel);

		jlabeltag = new JLabel("");
		jlabeltag.setIcon(new ImageIcon(Product_Detail.class.getResource("/Resources/sale_791968.png")));
		jlabeltag.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jlabeltag.setForeground(Color.RED);
		jpanelPriceDefault.add(jlabeltag);

		jpanelPriceDiscount = new JPanel();
		jpanelPrice.add(jpanelPriceDiscount);

		JpanelDiscount = new JLabel("");

		jpanelPriceDiscount.add(JpanelDiscount);

		jlabelVND2 = new JLabel("VNĐ");
		jpanelPriceDiscount.add(jlabelVND2);

	}

	public Product_Detail(Products products) {
		this();
		this.buyProduct = products;
		ImageIcon originalImageIcon = new ImageIcon(buyProduct.getImage());

		int desiredWidth = 200;
		int desiredHeight = 150;
		Image scaledImage = originalImageIcon.getImage().getScaledInstance(desiredWidth, desiredHeight,
				Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

		lblImage.setIcon(scaledImageIcon);
		jlabelProductname.setText(buyProduct.getName());
		jlabelCost.setText(Double.toString(buyProduct.getPrice()));
		if (buyProduct.getQuantity() <= 0) {
			jlabelQuantyti.setText("Out of stock");
		} else {
			jlabelQuantyti.setText(Double.toString(buyProduct.getQuantity()));
		}

		if (String.valueOf(buyProduct.getDiscount_percent()).equals("") || buyProduct.getDiscount_percent() == 0) {
			jlabeltag.setVisible(false);
			jlabelVND2.setVisible(false);
		} else {
			jlabeltag.setVisible(true);
			jlabelVND2.setVisible(true);
			jlabeltag.setText(String.valueOf(buyProduct.getDiscount_percent()) + "%");
			double resultDouble = (buyProduct.getPrice() * buyProduct.getDiscount_percent()) / 100;
			Double totalprice = buyProduct.getPrice() - resultDouble;
			JpanelDiscount.setText(Double.toString(totalprice));
		}

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (jlabelQuantyti.getText().equalsIgnoreCase("Out of stock")) {
					return;
				}
				JPanelSalesInvoice.getBuyProductInformation(buyProduct);
			}
		});
	}

	public Product_Detail(Products products, String keyword) {
		this();
		this.buyProduct = products;
		this.keyword = keyword;
		ImageIcon originalImageIcon = new ImageIcon(buyProduct.getImage());

		int desiredWidth = 200;
		int desiredHeight = 150;
		Image scaledImage = originalImageIcon.getImage().getScaledInstance(desiredWidth, desiredHeight,
				Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

		lblImage.setIcon(scaledImageIcon);
		jlabelProductname.setText(buyProduct.getName());
		jlabelCost.setText(Double.toString(buyProduct.getPrice()));
		if (buyProduct.getQuantity() <= 0) {
			jlabelQuantyti.setText("Out of stock");
		} else {
			jlabelQuantyti.setText(Double.toString(buyProduct.getQuantity()));
		}

		jlabeltag.setVisible(false);
		jlabelVND2.setVisible(false);
		jpanelPriceDiscount.setVisible(false);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanelImportInvoicesProduct.getBuyProductInformation(buyProduct);
			}
		});
	}

}
