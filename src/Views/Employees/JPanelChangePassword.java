package Views.Employees;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Models.Auth.Validation.SetFocusBorder;
import Models.CSS.StyleColor;
import Models.Employees.EmployeeModel;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.mindrot.jbcrypt.BCrypt;

import Entities.Employees;

import java.awt.ComponentOrientation;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelChangePassword extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtextFieldOldPW;
	private JTextField jtextFieldNewPW;
	private JTextField jtextFieldCofirmPW;
	private int employeeId;

	/**
	 * Create the panel.
	 */
	public JPanelChangePassword() {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JPanel MainContent = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon backgroundImage = new ImageIcon("src/Resources/Images/Background-Blue.png");
				g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
			}
		};
		add(MainContent);
		MainContent.setLayout(new BoxLayout(MainContent, BoxLayout.LINE_AXIS));

		JPanel Form_Change_PW = new JPanel();
		Form_Change_PW.setOpaque(false);
		MainContent.add(Form_Change_PW);
		Form_Change_PW.setLayout(new BoxLayout(Form_Change_PW, BoxLayout.PAGE_AXIS));

		JPanel jpanelLabelTitle = new JPanel();
		jpanelLabelTitle.setOpaque(false);
		Form_Change_PW.add(jpanelLabelTitle);

		JLabel jLabelTitle = new JLabel("CHANGE PASSWORD");
		jLabelTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelTitle.setPreferredSize(new Dimension(300, 100));
		jLabelTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		jpanelLabelTitle.add(jLabelTitle);

		JPanel JpanelFormPassword = new JPanel();
		JpanelFormPassword.setOpaque(false);
		Form_Change_PW.add(JpanelFormPassword);
		JpanelFormPassword.setLayout(new BoxLayout(JpanelFormPassword, BoxLayout.PAGE_AXIS));

		JPanel jpanelTextOldPW = new JPanel();
		jpanelTextOldPW.setPreferredSize(new Dimension(500, 40));
		jpanelTextOldPW.setOpaque(false);
		JpanelFormPassword.add(jpanelTextOldPW);

		JPanel jpanelLabelOld = new JPanel();
		jpanelLabelOld.setOpaque(false);
		jpanelLabelOld.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FlowLayout flowLayout_2 = (FlowLayout) jpanelLabelOld.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEADING);
		jpanelLabelOld.setPreferredSize(new Dimension(140, 25));
		jpanelTextOldPW.add(jpanelLabelOld);

		JLabel jLabelOldPW = new JLabel("Old Password:");
		jLabelOldPW.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jpanelLabelOld.add(jLabelOldPW);

		JLabel lblNewLabel_2 = new JLabel("*");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		jpanelLabelOld.add(lblNewLabel_2);

		JPanel jpanelNewPW = new JPanel();
		jpanelNewPW.setPreferredSize(new Dimension(300, 40));
		jpanelNewPW.setOpaque(false);
		JpanelFormPassword.add(jpanelNewPW);

		JPanel jpanelLabelOld_1 = new JPanel();
		jpanelLabelOld_1.setOpaque(false);
		jpanelLabelOld_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FlowLayout flowLayout_1 = (FlowLayout) jpanelLabelOld_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		jpanelLabelOld_1.setPreferredSize(new Dimension(140, 25));
		jpanelNewPW.add(jpanelLabelOld_1);

		JLabel jLabelNewPW = new JLabel("New Password:");
		jLabelNewPW.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jpanelLabelOld_1.add(jLabelNewPW);

		JLabel lblNewLabel_2_1 = new JLabel("*");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setForeground(new Color(255, 0, 0));
		jpanelLabelOld_1.add(lblNewLabel_2_1);

		JPanel jpanelCofirmPW = new JPanel();
		jpanelCofirmPW.setPreferredSize(new Dimension(300, 40));
		jpanelCofirmPW.setOpaque(false);
		JpanelFormPassword.add(jpanelCofirmPW);

		JPanel jpanelLabelOld_2 = new JPanel();
		jpanelLabelOld_2.setOpaque(false);
		jpanelLabelOld_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FlowLayout flowLayout = (FlowLayout) jpanelLabelOld_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		jpanelLabelOld_2.setPreferredSize(new Dimension(140, 25));
		jpanelCofirmPW.add(jpanelLabelOld_2);

		JLabel jLabelComfirmPW = new JLabel("Comfirm Password:");
		jLabelComfirmPW.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jpanelLabelOld_2.add(jLabelComfirmPW);

		JLabel lblNewLabel_2_2 = new JLabel("*");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_2.setForeground(new Color(255, 0, 0));
		jpanelLabelOld_2.add(lblNewLabel_2_2);

		JPanel panelMargin = new JPanel();
		panelMargin.setOpaque(false);
		panelMargin.setPreferredSize(new Dimension(10, 20));
		Form_Change_PW.add(panelMargin);

		JPanel jpanelBtnFormPassword = new JPanel();
		jpanelBtnFormPassword.setMinimumSize(new Dimension(10, 60));
		FlowLayout flowLayout_3 = (FlowLayout) jpanelBtnFormPassword.getLayout();
		jpanelBtnFormPassword.setPreferredSize(new Dimension(10, 60));
		jpanelBtnFormPassword.setOpaque(false);
		Form_Change_PW.add(jpanelBtnFormPassword);

		JButton jbtnSave = new JButton("Save");
		jbtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validation()) {
					jbtnSave_actionPerformed(e);
				}
			}
		});
		jpanelBtnFormPassword.add(jbtnSave);

		JButton jbtnCancel = new JButton("Cancel");
		jbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnCancel_actionPerformed(e);
			}
		});
		jpanelBtnFormPassword.add(jbtnCancel);

		jtextFieldOldPW = new JTextField();
		jpanelTextOldPW.add(jtextFieldOldPW);
		jtextFieldOldPW.setColumns(20);

		jtextFieldNewPW = new JTextField();
		jtextFieldNewPW.setColumns(20);
		jpanelNewPW.add(jtextFieldNewPW);

		jtextFieldCofirmPW = new JTextField();
		jtextFieldCofirmPW.setColumns(20);
		jpanelCofirmPW.add(jtextFieldCofirmPW);
		// CSS Label
		jLabelTitle.setForeground(StyleColor.TitleColor());
		jLabelOldPW.setForeground(StyleColor.TitleColor());
		jLabelNewPW.setForeground(StyleColor.TitleColor());
		jLabelComfirmPW.setForeground(StyleColor.TitleColor());
		// css btn

		jbtnSave.setBackground(StyleColor.BtnBackground());
		jbtnCancel.setBackground(StyleColor.BtnBackground());
		// css textfield
		SetFocusBorder.addFocusBorder(jtextFieldOldPW);
		SetFocusBorder.addFocusBorder(jtextFieldNewPW);
		SetFocusBorder.addFocusBorder(jtextFieldCofirmPW);

	}

	public JPanelChangePassword(int employeeId) {
		this();
		this.employeeId = employeeId;
	}

	private boolean validation() {
		String oldPW = jtextFieldOldPW.getText();
		String newPW = jtextFieldNewPW.getText();
		String confirmPW = jtextFieldCofirmPW.getText();

		if (oldPW.isEmpty() || newPW.isEmpty() || confirmPW.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter complete information !!", "Failed",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!newPW.equals(confirmPW)) {
			JOptionPane.showMessageDialog(this, "New Password and confirm password do not match !!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public void jbtnSave_actionPerformed(ActionEvent e) {
		EmployeeModel employeeModel = new EmployeeModel();
		Employees employees = employeeModel.FindByEmployeeID(employeeId);
		String newPW = jtextFieldNewPW.getText();
		if (BCrypt.checkpw(jtextFieldOldPW.getText(), employees.getPassword())) {
			if(employeeModel.ChangePassword(employeeId, newPW)) {
				JOptionPane.showMessageDialog(this, "Change Password Success !!", "Success", JOptionPane.INFORMATION_MESSAGE);
				closeDialog();
			}else {
				JOptionPane.showMessageDialog(this, "Change Password Failed !!", "Failed", JOptionPane.ERROR_MESSAGE);
			};
			
		} else {
			JOptionPane.showMessageDialog(this, "Old password does not match !!", "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void jbtnCancel_actionPerformed(ActionEvent e) {
		ClearScreen();
		closeDialog();
	}
	private void closeDialog() {
		Window window = SwingUtilities.getWindowAncestor(JPanelChangePassword.this);
        if (window instanceof JDialog) {
            JDialog dialog = (JDialog) window;
            dialog.dispose();
        }
	}
	private void ClearScreen() {
		removeAll();
		revalidate();
		this.setOpaque(false);
		repaint();
	}

}
