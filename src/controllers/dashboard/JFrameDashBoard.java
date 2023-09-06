package controllers.dashboard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class JFrameDashBoard extends JFrame {

	private JPanel contentPane;
	private JTextField jtextFieldSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameDashBoard frame = new JFrameDashBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrameDashBoard() {
		setTitle("DASHBOARD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 685);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Tai khoan");
		menuBar.add(mnNewMenu);
		
		JMenuItem jMenuItemChangePass = new JMenuItem("Doi mat khau");
		mnNewMenu.add(jMenuItemChangePass);
		
		JMenuItem jMenuItemAddUser = new JMenuItem("Tao tai khoan - 4");
		mnNewMenu.add(jMenuItemAddUser);
		
		JMenuItem jMenuItemLogout = new JMenuItem("Dang xuat");
		mnNewMenu.add(jMenuItemLogout);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("New menu");
		menuBar.add(mnNewMenu_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel jpanelLeft = new JPanel();
		contentPane.add(jpanelLeft, BorderLayout.WEST);
		jpanelLeft.setLayout(new BoxLayout(jpanelLeft, BoxLayout.PAGE_AXIS));
		
		JButton jbtnInventory = new JButton("Kho Hang -1,2");
		jpanelLeft.add(jbtnInventory);
		
		JButton jbtnInvoices = new JButton("hoa don ban hang-1");
		jpanelLeft.add(jbtnInvoices);
		
		JButton jbtnReturned = new JButton("Tra Hang-1");
		jpanelLeft.add(jbtnReturned);
		
		JButton jbtnInformManager = new JButton("Thong bao qly -1");
		jpanelLeft.add(jbtnInformManager);
		
		JButton jbtnHelp = new JButton("tro giup -1");
		jpanelLeft.add(jbtnHelp);
		
		JButton jbtnSaleRequest = new JButton("yeu cau - 2,3");
		jpanelLeft.add(jbtnSaleRequest);
		
		JButton jbtnValidateInventory = new JButton("Xac thuc ton kho - 2");
		jpanelLeft.add(jbtnValidateInventory);
		
		JButton jbtnReportInventory = new JButton("Bao cao -2 ");
		jpanelLeft.add(jbtnReportInventory);
		
		JButton jbtnInventoryTrends = new JButton("Thong ke - 2");
		jpanelLeft.add(jbtnInventoryTrends);
		
		JButton jbtnInvoiceToday = new JButton("hoa don  -3");
		jpanelLeft.add(jbtnInvoiceToday);
		
		JButton jbtnTransaction = new JButton("giao dich - 3");
		jpanelLeft.add(jbtnTransaction);
		
		JButton jbtnReturnedToday = new JButton("Hang tra ve - 3");
		jpanelLeft.add(jbtnReturnedToday);
		
		JButton jbtnSalesTrend = new JButton("bieu do - 3");
		jpanelLeft.add(jbtnSalesTrend);
		
		JButton jbtnAddProductCategory = new JButton("them moi - 4");
		jpanelLeft.add(jbtnAddProductCategory);
		
		JPanel panel = new JPanel();
		jpanelLeft.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel jpanelContent = new JPanel();
		contentPane.add(jpanelContent, BorderLayout.CENTER);
		jpanelContent.setLayout(new BoxLayout(jpanelContent, BoxLayout.X_AXIS));
		
		JPanel jpanelTop = new JPanel();
		contentPane.add(jpanelTop, BorderLayout.NORTH);
		jpanelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel jlblSearch = new JLabel("Search");
		jpanelTop.add(jlblSearch);
		
		jtextFieldSearch = new JTextField();
		jpanelTop.add(jtextFieldSearch);
		jtextFieldSearch.setColumns(10);
		
		JButton jbtnSearch = new JButton("Search");
		jpanelTop.add(jbtnSearch);
		
		JComboBox jcomboBoxCategory = new JComboBox();
		jpanelTop.add(jcomboBoxCategory);
		
	}

	private void init() {
		
	}
	
}
