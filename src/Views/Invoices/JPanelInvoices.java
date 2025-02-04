package Views.Invoices;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import Models.Invoice.InvoiceModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class JPanelInvoices extends JPanel {
	private InvoiceModel invoiceModel = new InvoiceModel();
	private JPanel jpanelContent;

	/**
	 * Create the panel.
	 */
	public JPanelInvoices() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 15, 5);
		panel.setLayout(fl_panel);

		JButton jbtnSales = new JButton("ADD");
		jbtnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnSales_actionPerformed(e);
			}
		});
		jbtnSales.setPreferredSize(new Dimension(80, 25));
		panel.add(jbtnSales);
		
		JButton jbtnList = new JButton("LIST");
		jbtnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnList_actionPerformed(e);
			}
		});
		jbtnList.setPreferredSize(new Dimension(80, 25));
		panel.add(jbtnList);
		
		jpanelContent = new JPanel();
		add(jpanelContent, BorderLayout.CENTER);
		jpanelContent.setLayout(new BorderLayout(0, 0));

	}
	
	public void jbtnList_actionPerformed(ActionEvent e) {
		clearScreen();
		JPanelListInvoice panelList = new JPanelListInvoice();
		panelList.setVisible(true);
		jpanelContent.add(panelList);
	}
	
	public void jbtnSales_actionPerformed(ActionEvent e) {
		clearScreen();
		JPanelSalesInvoice panelSale = new JPanelSalesInvoice();
		panelSale.setVisible(true);
		jpanelContent.add(panelSale);
	}	
	

	private void clearScreen() {
		jpanelContent.removeAll();
		jpanelContent.revalidate();
		jpanelContent.repaint();
	}
}