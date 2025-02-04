package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.Employees;
import Models.Auth.LoginModel;
import Models.Auth.PasswordShowHide;
import Views.Auth.JPanelLogin;
import Views.Dashboard.JFrameDashBoard;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Index extends JFrame {

	private JPanel jpanelMain;
	private JPanel Center_Content_Panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.put("Button.focusPainted", false);
			UIManager.put("Button.borderPainted", false);

		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Index() {
		setTitle("SIMS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 590);
		jpanelMain = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon backgroundImage = new ImageIcon("src/Resources/Images/Background-Blue.png");
				g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
			}
		};
		jpanelMain.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(jpanelMain);
		jpanelMain.setLayout(new BoxLayout(jpanelMain, BoxLayout.PAGE_AXIS));

		JPanel Top_Panel = new JPanel();
		Top_Panel.setOpaque(false);
		jpanelMain.add(Top_Panel);
		Top_Panel.setLayout(new BorderLayout(0, 0));

		JPanel Middle_Panel = new JPanel();
		Middle_Panel.setOpaque(false);
		jpanelMain.add(Middle_Panel);
		Middle_Panel.setLayout(new BoxLayout(Middle_Panel, BoxLayout.X_AXIS));

		JPanel Left_Panel = new JPanel();
		Left_Panel.setOpaque(false);
		Middle_Panel.add(Left_Panel);
		Left_Panel.setLayout(new BorderLayout(0, 0));

		Center_Content_Panel = new JPanel();
		Center_Content_Panel.setOpaque(false);
		Middle_Panel.add(Center_Content_Panel);
		Center_Content_Panel.setLayout(new BoxLayout(Center_Content_Panel, BoxLayout.PAGE_AXIS));

		JPanel Right_Panel = new JPanel();
		Right_Panel.setOpaque(false);
		Middle_Panel.add(Right_Panel);
		Right_Panel.setLayout(new BorderLayout(0, 0));

		JPanel Bottom_Panel = new JPanel();
		Bottom_Panel.setOpaque(false);
		jpanelMain.add(Bottom_Panel);
		Bottom_Panel.setLayout(new BorderLayout(0, 0));
		initJFrame();
	}

	private void initJFrame() {
		ClearScreenCenter();
		JPanelLogin jLogin = new JPanelLogin(this);
		Center_Content_Panel.add(jLogin);
		jLogin.setVisible(true);
	}

	private void ClearScreenCenter() {
		Center_Content_Panel.removeAll();
		Center_Content_Panel.revalidate();
		Center_Content_Panel.repaint();
	}

}
