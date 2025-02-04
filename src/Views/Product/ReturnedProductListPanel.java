package Views.Product;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Entities.Invoice_detail;
import Entities.Products;
import Entities.Return_product;
import Models.Invoice.InvoiceDetailModel;
import Models.Invoice.ReturnProductModel;
import Models.Product.ProductModel;
import javax.swing.JLabel;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import com.toedter.calendar.JDayChooser;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JCalendar;

public class ReturnedProductListPanel extends JPanel {

	private JTable tableReturnedProductList;
	private InvoiceDetailModel invoiceDetailModel = new InvoiceDetailModel();
	private ProductModel productModel = new ProductModel();

	/**
	 * Create the panel.
	 */
	public ReturnedProductListPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panelList = new JPanel();
		add(panelList, BorderLayout.CENTER);
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panelList.add(scrollPane);

		tableReturnedProductList = new JTable();

		scrollPane.setViewportView(tableReturnedProductList);
		
		JPanel panelSearch = new JPanel();
		add(panelSearch, BorderLayout.NORTH);
		
		JMonthChooser monthChooser = new JMonthChooser();
	
		panelSearch.add(monthChooser);
		
		JYearChooser yearChooser = new JYearChooser();
		panelSearch.add(yearChooser);
		
		JButton btnSearch = new JButton("Search");
		panelSearch.add(btnSearch);

		init();
	}

	private void init() {
		ReturnProductModel returnModel = new ReturnProductModel();
		fillDataToTable(returnModel.returnedListAll());
	}

	private void fillDataToTable(List<Return_product> list) {
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}

		};
		model.addColumn("Name");
		model.addColumn("Quantity");
		model.addColumn("Reason");
		model.addColumn("Returned Date");

		for (Return_product return_product : list) {
			Invoice_detail invoiceDetail = invoiceDetailModel.findById(return_product.getInvoice_detail_id());
			Products product = productModel.findById(invoiceDetail.getProduct_code());
			model.addRow(new Object[] { product.getName(), invoiceDetail.getProduct_quantity(),
					return_product.getReason(), return_product.getReturned_at() });
		}

		tableReturnedProductList.setModel(model);
		tableReturnedProductList.getTableHeader().setReorderingAllowed(false);
	}
	
	public void monthChooser_componentHidden(ComponentEvent e) {
	}

}
