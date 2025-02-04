package Views.Auth;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Entities.Employees;
import Models.Auth.LoginModel;
import Models.Auth.PasswordShowHide;
import Models.Auth.Validation.InputValidator;
import Models.Auth.Validation.SetFocusBorder;
import Models.CSS.StyleColor;
import Views.Index;
import Views.Dashboard.JFrameDashBoard;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPanelLogin extends JPanel {

	private PasswordShowHide passwordShowHide = new PasswordShowHide();
	private JTextField jtextFieldUsername;

	private JFrame parentFrame;

	private String BtnNoBG = "#f7d794";

	/**
	 * Create the panel.
	 */
	public JPanelLogin() {
		setOpaque(false);

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel Top_Fix = new JPanel();
		Top_Fix.setOpaque(false);
		add(Top_Fix);
		Top_Fix.setLayout(new BorderLayout(0, 0));

		JPanel jpanelForm = new JPanel();
		jpanelForm.setOpaque(false);
		add(jpanelForm);
		jpanelForm.setLayout(new BoxLayout(jpanelForm, BoxLayout.PAGE_AXIS));

		JLabel jlabelLogin = new JLabel("Login");

		jlabelLogin.setMinimumSize(new Dimension(300, 60));
		jlabelLogin.setMaximumSize(new Dimension(300, 60));
		jlabelLogin.setPreferredSize(new Dimension(300, 80));
		jlabelLogin.setFont(new Font("Tahoma", Font.BOLD, 26));
		jlabelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		jlabelLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpanelForm.add(jlabelLogin);

		JPanel jpanelFormLogin = new JPanel();
		jpanelFormLogin.setOpaque(false);
		jpanelForm.add(jpanelFormLogin);
		jpanelFormLogin.setLayout(new BoxLayout(jpanelFormLogin, BoxLayout.PAGE_AXIS));

		JPanel jpanelUsername = new JPanel();
		jpanelUsername.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) jpanelUsername.getLayout();
		jpanelFormLogin.add(jpanelUsername);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setPreferredSize(new Dimension(80, 30));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jpanelUsername.add(lblNewLabel);

		JPanel jpanelTextField = new JPanel();
		jpanelTextField.setOpaque(false);
		jpanelTextField.setPreferredSize(new Dimension(260, 25));
		jpanelUsername.add(jpanelTextField);
		jpanelTextField.setLayout(new BoxLayout(jpanelTextField, BoxLayout.LINE_AXIS));

		jtextFieldUsername = new JTextField();
		jtextFieldUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jbtnLogin_actionPerformed(null); 
                }
			}
		});
		jpanelTextField.add(jtextFieldUsername);
		jtextFieldUsername.setColumns(16);

		JLabel fix = new JLabel("");
		fix.setPreferredSize(new Dimension(30, 0));
		jpanelTextField.add(fix);

		JPanel jpanelPassword = new JPanel();
		jpanelPassword.setOpaque(false);
		jpanelFormLogin.add(jpanelPassword);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setPreferredSize(new Dimension(80, 30));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jpanelPassword.add(lblNewLabel_1);
		passwordShowHide.setOpaque(false);

//		add jpassword and show/hide logic
		passwordShowHide.setPreferredSize(new Dimension(260, 25));
		passwordShowHide.getPasswordField().setEchoChar('*');
	     passwordShowHide.getPasswordField().addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    jbtnLogin_actionPerformed(null); 
	                }
	            }
	        });
		jpanelPassword.add(passwordShowHide);

		JPanel jpanelLabelVerify = new JPanel();
		jpanelLabelVerify.setOpaque(false);
		jpanelFormLogin.add(jpanelLabelVerify);
		jpanelLabelVerify.setLayout(new BoxLayout(jpanelLabelVerify, BoxLayout.X_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		jpanelLabelVerify.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JButton jbtnVerifyAccount = new JButton("Verify Account ?");
		jbtnVerifyAccount.setOpaque(false);
		jbtnVerifyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnVerifyAccount_actionPerformed(e);
			}
		});
		jbtnVerifyAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnVerifyAccount.setAlignmentY(Component.TOP_ALIGNMENT);
		jbtnVerifyAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		jbtnVerifyAccount.setPreferredSize(new Dimension(180, 23));
		jbtnVerifyAccount.setContentAreaFilled(false);
		jbtnVerifyAccount.setBorderPainted(false);
		jbtnVerifyAccount.setFocusPainted(false);
		jbtnVerifyAccount.setForeground(new Color(30, 144, 255));
		jbtnVerifyAccount.setFont(new Font("Tahoma", Font.ITALIC, 14));
		jpanelLabelVerify.add(jbtnVerifyAccount);

		JButton jbtnForgotPassword = new JButton("Forgot Password ?");
		jbtnForgotPassword.setOpaque(false);
		jbtnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnForgotPassword_actionPerformed(e);
			}
		});
		jbtnForgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnForgotPassword.setAlignmentY(Component.TOP_ALIGNMENT);
		jbtnForgotPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		jbtnForgotPassword.setPreferredSize(new Dimension(180, 23));
		jbtnForgotPassword.setForeground(new Color(30, 144, 255));
		jbtnForgotPassword.setFont(new Font("Tahoma", Font.ITALIC, 14));
		jbtnForgotPassword.setFocusPainted(false);
		jbtnForgotPassword.setContentAreaFilled(false);
		jbtnForgotPassword.setBorderPainted(false);
		jpanelLabelVerify.add(jbtnForgotPassword);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		jpanelLabelVerify.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel jpanelButton = new JPanel();
		jpanelButton.setOpaque(false);
		jpanelButton.setBorder(new EmptyBorder(1, 0, 0, 0));
		FlowLayout flowLayout_2 = (FlowLayout) jpanelButton.getLayout();
		flowLayout_2.setVgap(10);
		flowLayout_2.setHgap(10);
		jpanelButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jpanelButton.setPreferredSize(new Dimension(10, 50));
		jpanelFormLogin.add(jpanelButton);

		JButton jbtnLogin = new JButton("Login");
		jbtnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnLogin_actionPerformed(e);
			}
		});
		jbtnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbtnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jpanelButton.add(jbtnLogin);

		JButton jbtnRegister = new JButton("Register");
		jbtnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnRegister_actionPerformed(e);
			}
		});
		jbtnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jpanelButton.add(jbtnRegister);

		JPanel Bottom_Fix = new JPanel();
		Bottom_Fix.setOpaque(false);
		add(Bottom_Fix);
		Bottom_Fix.setLayout(new BorderLayout(0, 0));

		// focus model.auth.validation

		SetFocusBorder.addFocusBorder(jtextFieldUsername);
		SetFocusBorder.addFocusBorder(passwordShowHide.getPasswordField());

		// set Style color

		jlabelLogin.setForeground(StyleColor.TitleColor());
		lblNewLabel.setForeground(StyleColor.TitleColor());
		lblNewLabel_1.setForeground(StyleColor.TitleColor());

		jbtnVerifyAccount.setForeground(StyleColor.btnNoBacground());
		jbtnVerifyAccount.setRolloverEnabled(false);
		jbtnForgotPassword.setForeground(StyleColor.btnNoBacground());
		jbtnForgotPassword.setRolloverEnabled(false);

		jbtnLogin.setBackground(StyleColor.BtnBackground());
		jbtnLogin.setRolloverEnabled(false);
		jbtnRegister.setBackground(StyleColor.BtnBackground());
		jbtnRegister.setRolloverEnabled(false);
	}

	public JPanelLogin(Index parentFrame) {
		this();
		this.parentFrame = parentFrame;
	}

	public void jbtnVerifyAccount_actionPerformed(ActionEvent e) {
		ClearScreen();
		JPanelVerifyEmail jVerifyEmail = new JPanelVerifyEmail();
		add(jVerifyEmail);
		jVerifyEmail.setVisible(true);
	}

	public void jbtnForgotPassword_actionPerformed(ActionEvent e) {
		ClearScreen();
		JPanelForgotPassword jPanelForgotPassword = new JPanelForgotPassword();

		add(jPanelForgotPassword);
		jPanelForgotPassword.setVisible(true);
	}

	public void jbtnLogin_actionPerformed(ActionEvent e) {
		LoginModel loginModel = new LoginModel();
		String usernameOrEmail = jtextFieldUsername.getText();
		String password = passwordShowHide.getPasswordAsString();
		// validate
		if (InputValidator.isUsernameValid(usernameOrEmail)) {
			InputValidator.showErrorMessage(this, "Please Enter Username/Email");
			return;
		}
		if (InputValidator.isUsernameOrEmailValid(usernameOrEmail)) {
			InputValidator.showErrorMessage(this, "Incorrect Username/Email format");
			return;
		}
		if (InputValidator.isPasswordValid(password)) {
			InputValidator.showErrorMessage(this, "Please Enter Password");
			return;
		}

		Employees employee = loginModel.Login(usernameOrEmail, password);
		if (employee != null) {
			java.awt.Window parentWindow = SwingUtilities.getWindowAncestor(this);
			if (parentWindow instanceof javax.swing.JFrame) {
				((javax.swing.JFrame) parentWindow).dispose();
			}
			JFrameDashBoard jFrameDashBoard = new JFrameDashBoard(employee.getEmployee_id());
			this.setVisible(false);
			jFrameDashBoard.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Login Failed, Please Try Again !!", "Login Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void jbtnRegister_actionPerformed(ActionEvent e) {
		ClearScreen();
		JPanelRegister jRegister = new JPanelRegister();
		add(jRegister);
		jRegister.setVisible(true);
	}

	private void ClearScreen() {
		removeAll();
		revalidate();
		repaint();

	}
}
