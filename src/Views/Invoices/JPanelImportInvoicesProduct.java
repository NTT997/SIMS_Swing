package Views.Invoices;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Entities.Invoice_detail;
import Entities.Products;
import Models.Product.ProductModel;
import Views.Product.Product_Detail;

public class JPanelImportInvoicesProduct extends JPanel {
    private ProductModel productModel = new ProductModel();
    private JPanel panelList;
    private static JPanel jpanelInformation;
    private static List<JPanel> informationPanels = new ArrayList<>();
    private static JDialog dialog = new JDialog();

    public JPanelImportInvoicesProduct() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

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
        jpanelInformation.setBorder(new TitledBorder(null, "information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

        initProductCard();
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
            // Get the values
            String id = productID.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceLabel.getText());
            int discount = Integer.parseInt(discountLabel.getText());

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
            JOptionPane.showMessageDialog(null, "NO PRODUCT");
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

        // Clear the information panels and update the product table
        clearInformationPanel();
        updateProductTable();
    }

    private void updateProductTable() {
        // Clear the existing product list
        panelList.removeAll();

        // Add products with updated quantities
        for (Products product : productModel.findAll()) {
            Product_Detail productDetail = new Product_Detail(product);
            panelList.add(productDetail);
        }

        // Revalidate and repaint panelList to update the UI
        panelList.revalidate();
        panelList.repaint();
    }

    public static void getBuyProductInformation(Products buyProduct) {
        // Check if the product is already selected
        for (JPanel infoPanel : informationPanels) {
            JLabel buyProductId = findComponentByClass(infoPanel, JLabel.class);
            if (buyProductId != null && buyProduct.getProduct_code().equals(buyProductId.getText())) {
                JTextField quantityField = findComponentByClass(infoPanel, JTextField.class);
                if (quantityField != null) {
                    int quantity = Integer.parseInt(quantityField.getText());
                    quantity++;
                    quantityField.setText(Integer.toString(quantity));

                    JLabel totalPriceLabel = findComponentByClass(infoPanel, JLabel.class);
                    if (totalPriceLabel != null) {
                        double price = Double.parseDouble(findComponentByClass(infoPanel, JLabel.class).getText());
                        double discount = Double.parseDouble(findComponentByClass(infoPanel, JLabel.class).getText());
                        double total = price * quantity * (1 - discount / 100);
                        totalPriceLabel.setText(String.format("%.2f", total));
                    }
                    return;
                }
            }
        }
        
        // If the product is not found in the existing information panels, create a new panel for it
        createProductInformationPanel(buyProduct);
    }

    private static <T> T findComponentByClass(Container container, Class<T> cls) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (cls.isInstance(component)) {
                return cls.cast(component);
            }
        }
        return null;
    }

    private static void createProductInformationPanel(Products buyProduct) {
        JPanel panelBuyProductItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuyProductItem.setPreferredSize(new Dimension(400, 50));
        // ... Rest of the code to create and add components to the panel ...
        
        // Add the new panel to the list of information panels
        informationPanels.add(panelBuyProductItem);
        
        // Add the new panel to jpanelInformation
        jpanelInformation.add(panelBuyProductItem);
        
        // Revalidate and repaint jpanelInformation to update the UI
        jpanelInformation.revalidate();
        jpanelInformation.repaint();

        // Create a new panel for the selected product
        JPanel panelBuyProductItem1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuyProductItem1.setPreferredSize(new Dimension(400, 50));
        JLabel buyProductNameLabel = new JLabel("Name:");
        JTextField buyProductNameTextField = new JTextField(buyProduct.getName());
        buyProductNameTextField.setColumns(15);
        JLabel quantityLabel = new JLabel("Quantity");
        JTextField quantityTextField = new JTextField("1");
        quantityTextField.setColumns(5);
        JButton button1 = new JButton("+");
        JButton button2 = new JButton("-");
        JButton button3 = new JButton("x");
        JLabel buyProductId = new JLabel(buyProduct.getProduct_code());
        JLabel buyProductPrice = new JLabel(String.valueOf(buyProduct.getPrice()));
        JLabel buyProductDiscount = new JLabel(String.valueOf(buyProduct.getDiscount_percent()));
        JLabel totalPriceLabel = new JLabel(String.valueOf(buyProduct.getPrice()));

        buyProductId.setVisible(false);
        buyProductPrice.setVisible(false);
        buyProductDiscount.setVisible(false);
        totalPriceLabel.setVisible(false);

        panelBuyProductItem1.add(buyProductNameLabel);
        panelBuyProductItem1.add(buyProductNameTextField);
        panelBuyProductItem1.add(quantityLabel);
        panelBuyProductItem1.add(quantityTextField);
        panelBuyProductItem1.add(buyProductId);
        panelBuyProductItem1.add(buyProductPrice);
        panelBuyProductItem1.add(buyProductDiscount);
        panelBuyProductItem1.add(totalPriceLabel);
        panelBuyProductItem1.add(button1);
        panelBuyProductItem1.add(button2);
        panelBuyProductItem1.add(button3);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(quantityTextField.getText());
                value++;
                quantityTextField.setText(Integer.toString(value));
                double price = Double.parseDouble(buyProductPrice.getText());
                double discount = Double.parseDouble(buyProductDiscount.getText());
                double total = price * value * (1 - discount / 100);
                totalPriceLabel.setText(String.format("%.2f", total));
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(quantityTextField.getText());
                if (value > 1) {
                    value--;
                    quantityTextField.setText(Integer.toString(value));
                    double price = Double.parseDouble(buyProductPrice.getText());
                    double discount = Double.parseDouble(buyProductDiscount.getText());
                    double total = price * value * (1 - discount / 100);
                    totalPriceLabel.setText(String.format("%.2f", total));
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the panel from jpanelInformation
                jpanelInformation.remove(panelBuyProductItem1);
                informationPanels.remove(panelBuyProductItem1);
                // Revalidate and repaint jpanelInformation to update the UI
                jpanelInformation.revalidate();
                jpanelInformation.repaint();
            }
        });

        // Add the new panel to the list of information panels
        informationPanels.add(panelBuyProductItem1);
        // Add the new panel to the jpanelInformation
        jpanelInformation.add(panelBuyProductItem1);
        // Revalidate and repaint jpanelInformation to update the UI
        jpanelInformation.revalidate();
        jpanelInformation.repaint();
    }

    public static void clearInformationPanel() {
        for (JPanel infoPanel : informationPanels) {
            jpanelInformation.remove(infoPanel);
        }
        informationPanels.clear();
        jpanelInformation.revalidate();
        jpanelInformation.repaint();
    }

    private void initProductCard() {
        for (Products product : productModel.findAll()) {
            Product_Detail productDetail = new Product_Detail(product,"import");
            panelList.add(productDetail);
        }
    }
}
