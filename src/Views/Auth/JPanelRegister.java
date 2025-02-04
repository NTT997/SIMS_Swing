package Views.Auth;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import org.apache.commons.lang3.RandomStringUtils;

import Entities.Employees;
import Entities.Positons;
import Models.Auth.RegisterModel;
import Models.Auth.SendMailModel;
import Models.Auth.Validation.InputValidator;
import Models.Auth.Validation.SetFocusBorder;
import Models.CSS.StyleColor;
import Models.Positions.PositionModel;
import Views.Index;

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JPanelRegister extends JPanel {
	private JTextField jtextFieldUsername;
	private JTextField jtextFieldPassword;
	private JTextField jtextFieldConfirm;
	private JTextField jtextFieldEmail;
	private JComboBox<Positons> jcomboBoxPositions;
	private JTextField jtextFieldTel;
	private JTextField jtextFieldAddress;
	private File selectedFile = null;
	private JLabel jlabelImage;
	private Index parentFrame;
	private JLabel ButtonBrowse;

	/**
	 * Create the panel.
	 */
	public JPanelRegister() {
		setDoubleBuffered(false);
		setFocusTraversalKeysEnabled(false);
		setFocusable(false);
		setRequestFocusEnabled(false);
		setOpaque(false);

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel Top_Fix = new JPanel();
		Top_Fix.setOpaque(false);
		add(Top_Fix);
		Top_Fix.setLayout(new BorderLayout(0, 0));

		JPanel jpanelForm = new JPanel();
		jpanelForm.setInheritsPopupMenu(true);
		jpanelForm.setIgnoreRepaint(true);
		jpanelForm.setRequestFocusEnabled(false);
		add(jpanelForm);
		jpanelForm.setLayout(new BoxLayout(jpanelForm, BoxLayout.PAGE_AXIS));

		JLabel jlabelRegister = new JLabel("Register");
		jlabelRegister.setMinimumSize(new Dimension(300, 60));
		jlabelRegister.setMaximumSize(new Dimension(300, 60));
		jlabelRegister.setPreferredSize(new Dimension(300, 150));
		jlabelRegister.setFont(new Font("Tahoma", Font.BOLD, 26));
		jlabelRegister.setHorizontalAlignment(SwingConstants.CENTER);
		jlabelRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpanelForm.add(jlabelRegister);

		JPanel jpanelFormRegister = new JPanel();
		jpanelFormRegister.setOpaque(false);
		jpanelForm.add(jpanelFormRegister);
		jpanelFormRegister.setLayout(new BorderLayout(20, 15));

		JPanel Left_Fix = new JPanel();
		Left_Fix.setOpaque(false);
		Left_Fix.setSize(new Dimension(10, 0));
		jpanelFormRegister.add(Left_Fix, BorderLayout.WEST);
		Left_Fix.setLayout(new BorderLayout(0, 0));

		JPanel Center_Content = new JPanel();
		Center_Content.setOpaque(false);
		jpanelFormRegister.add(Center_Content, BorderLayout.CENTER);
		Center_Content.setLayout(new BoxLayout(Center_Content, BoxLayout.LINE_AXIS));

		JPanel Left_Form = new JPanel();
		Left_Form.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 5),
				new MatteBorder(0, 0, 0, 3, (Color) new Color(204, 255, 255))));
		Left_Form.setOpaque(false);
		Center_Content.add(Left_Form);
		Left_Form.setLayout(new BoxLayout(Left_Form, BoxLayout.PAGE_AXIS));

		JPanel jpanelUsername = new JPanel();
		jpanelUsername.setOpaque(false);
		Left_Form.add(jpanelUsername);
		jpanelUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setPreferredSize(new Dimension(40, 0));
		jpanelUsername.add(lblNewLabel_1);

		JPanel jpanelLabel = new JPanel();
		jpanelLabel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) jpanelLabel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		jpanelLabel.setPreferredSize(new Dimension(140, 25));
		jpanelUsername.add(jpanelLabel);

		JLabel labelUsername = new JLabel("Username:");
		labelUsername.setHorizontalTextPosition(SwingConstants.CENTER);
		labelUsername.setHorizontalAlignment(SwingConstants.LEFT);
		labelUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelUsername.setAlignmentX(0.5f);
		jpanelLabel.add(labelUsername);

		JLabel lblNewLabel_2 = new JLabel("*");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		jpanelLabel.add(lblNewLabel_2);

		JPanel jpanelTextUsername = new JPanel();
		jpanelTextUsername.setOpaque(false);
		jpanelTextUsername.setPreferredSize(new Dimension(200, 25));
		jpanelUsername.add(jpanelTextUsername);
		jpanelTextUsername.setLayout(new BoxLayout(jpanelTextUsername, BoxLayout.X_AXIS));

		jtextFieldUsername = new JTextField();
		jtextFieldUsername.setColumns(16);
		jpanelTextUsername.add(jtextFieldUsername);

		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setPreferredSize(new Dimension(40, 0));
		jpanelUsername.add(lblNewLabel_1_2);

		JPanel jpanelPassword = new JPanel();
		jpanelPassword.setOpaque(false);
		Left_Form.add(jpanelPassword);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setPreferredSize(new Dimension(40, 0));
		jpanelPassword.add(lblNewLabel_3);

		JPanel jpanelLabel2 = new JPanel();
		jpanelLabel2.setOpaque(false);
		jpanelLabel2.setPreferredSize(new Dimension(140, 25));
		FlowLayout flowLayout_1 = (FlowLayout) jpanelLabel2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		jpanelPassword.add(jpanelLabel2);

		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPassword.setHorizontalAlignment(SwingConstants.LEFT);
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelPassword.setAlignmentX(0.5f);
		jpanelLabel2.add(labelPassword);

		JLabel lblNewLabel_2_1 = new JLabel("*");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setForeground(Color.RED);
		jpanelLabel2.add(lblNewLabel_2_1);

		JPanel jpanelPass = new JPanel();
		jpanelPass.setOpaque(false);
		jpanelPass.setPreferredSize(new Dimension(200, 25));
		jpanelPassword.add(jpanelPass);
		jpanelPass.setLayout(new BoxLayout(jpanelPass, BoxLayout.X_AXIS));

		jtextFieldPassword = new JTextField();
		jtextFieldPassword.setColumns(10);
		jpanelPass.add(jtextFieldPassword);

		JLabel lblNewLabel_1_3 = new JLabel("");
		lblNewLabel_1_3.setPreferredSize(new Dimension(40, 0));
		jpanelPassword.add(lblNewLabel_1_3);

		JPanel jpanelConfirmPass = new JPanel();
		jpanelConfirmPass.setOpaque(false);
		Left_Form.add(jpanelConfirmPass);

		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setPreferredSize(new Dimension(40, 0));
		jpanelConfirmPass.add(lblNewLabel_1_1_1);

		JPanel jpanelLabel2_1 = new JPanel();
		jpanelLabel2_1.setOpaque(false);
		FlowLayout flowLayout_3 = (FlowLayout) jpanelLabel2_1.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		jpanelLabel2_1.setPreferredSize(new Dimension(140, 25));
		jpanelConfirmPass.add(jpanelLabel2_1);

		JLabel labelConfirmPassword = new JLabel("Confirm Password:");
		labelConfirmPassword.setHorizontalTextPosition(SwingConstants.CENTER);
		labelConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
		labelConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelConfirmPassword.setAlignmentX(0.5f);
		jpanelLabel2_1.add(labelConfirmPassword);

		JLabel lblNewLabel_2_1_1 = new JLabel("*");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_1.setForeground(Color.RED);
		jpanelLabel2_1.add(lblNewLabel_2_1_1);

		JPanel jpanelConfirm = new JPanel();
		jpanelConfirm.setOpaque(false);
		jpanelConfirm.setPreferredSize(new Dimension(200, 25));
		jpanelConfirmPass.add(jpanelConfirm);
		jpanelConfirm.setLayout(new BoxLayout(jpanelConfirm, BoxLayout.X_AXIS));

		jtextFieldConfirm = new JTextField();
		jtextFieldConfirm.setColumns(10);
		jpanelConfirm.add(jtextFieldConfirm);

		JLabel lblNewLabel_1_4 = new JLabel("");
		lblNewLabel_1_4.setPreferredSize(new Dimension(40, 0));
		jpanelConfirmPass.add(lblNewLabel_1_4);

		JPanel jpanelEmail = new JPanel();
		jpanelEmail.setOpaque(false);
		Left_Form.add(jpanelEmail);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setPreferredSize(new Dimension(40, 0));
		jpanelEmail.add(lblNewLabel_1_1);

		JPanel jpanelLabel2_1_1 = new JPanel();
		jpanelLabel2_1_1.setOpaque(false);
		FlowLayout flowLayout_4 = (FlowLayout) jpanelLabel2_1_1.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		jpanelLabel2_1_1.setPreferredSize(new Dimension(140, 25));
		jpanelEmail.add(jpanelLabel2_1_1);

		JLabel labelEmail = new JLabel("Email:");
		labelEmail.setHorizontalTextPosition(SwingConstants.CENTER);
		labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelEmail.setAlignmentX(0.5f);
		jpanelLabel2_1_1.add(labelEmail);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("*");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_1_1.setForeground(Color.RED);
		jpanelLabel2_1_1.add(lblNewLabel_2_1_1_1);

		JPanel jpanelMail = new JPanel();
		jpanelMail.setOpaque(false);
		jpanelMail.setPreferredSize(new Dimension(200, 25));
		jpanelEmail.add(jpanelMail);
		jpanelMail.setLayout(new BoxLayout(jpanelMail, BoxLayout.X_AXIS));

		jtextFieldEmail = new JTextField();
		jpanelMail.add(jtextFieldEmail);
		jtextFieldEmail.setColumns(10);

		JLabel lblNewLabel_1_5 = new JLabel("");
		lblNewLabel_1_5.setPreferredSize(new Dimension(40, 0));
		jpanelEmail.add(lblNewLabel_1_5);

		JPanel Right_Form = new JPanel();
		Right_Form.setBorder(new EmptyBorder(0, 10, 0, 0));
		Right_Form.setOpaque(false);
		Center_Content.add(Right_Form);
		Right_Form.setLayout(new BoxLayout(Right_Form, BoxLayout.PAGE_AXIS));

		JPanel jpanelPosition = new JPanel();
		jpanelPosition.setOpaque(false);
		Right_Form.add(jpanelPosition);
		jpanelPosition.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_1_1_2 = new JLabel("");
		lblNewLabel_1_1_2.setPreferredSize(new Dimension(40, 0));
		jpanelPosition.add(lblNewLabel_1_1_2);

		JPanel jpanelLabel2_1_1_1 = new JPanel();
		jpanelLabel2_1_1_1.setOpaque(false);
		jpanelLabel2_1_1_1.setPreferredSize(new Dimension(100, 25));
		jpanelPosition.add(jpanelLabel2_1_1_1);
		jpanelLabel2_1_1_1.setLayout(new BoxLayout(jpanelLabel2_1_1_1, BoxLayout.X_AXIS));

		JLabel labelPositions = new JLabel("Position:");
		labelPositions.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPositions.setHorizontalAlignment(SwingConstants.LEFT);
		labelPositions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelPositions.setAlignmentX(0.5f);
		jpanelLabel2_1_1_1.add(labelPositions);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("*");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_1_1_1.setForeground(Color.RED);
		jpanelLabel2_1_1_1.add(lblNewLabel_2_1_1_1_1);

		JPanel JPanelComboBox = new JPanel();
		JPanelComboBox.setOpaque(false);
		JPanelComboBox.setPreferredSize(new Dimension(220, 25));
		jpanelPosition.add(JPanelComboBox);
		JPanelComboBox.setLayout(new BoxLayout(JPanelComboBox, BoxLayout.X_AXIS));

		jcomboBoxPositions = new JComboBox();
		JPanelComboBox.add(jcomboBoxPositions);

		JPanel jpanelTelephone = new JPanel();
		jpanelTelephone.setOpaque(false);
		Right_Form.add(jpanelTelephone);
		jpanelTelephone.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_1_1_2_1 = new JLabel("");
		lblNewLabel_1_1_2_1.setPreferredSize(new Dimension(40, 0));
		jpanelTelephone.add(lblNewLabel_1_1_2_1);

		JPanel jpanelLabel2_1_1_1_1 = new JPanel();
		jpanelLabel2_1_1_1_1.setOpaque(false);
		jpanelLabel2_1_1_1_1.setPreferredSize(new Dimension(100, 25));
		jpanelTelephone.add(jpanelLabel2_1_1_1_1);
		jpanelLabel2_1_1_1_1.setLayout(new BoxLayout(jpanelLabel2_1_1_1_1, BoxLayout.X_AXIS));

		JLabel labelTelephone = new JLabel("Telephone:");
		labelTelephone.setHorizontalTextPosition(SwingConstants.CENTER);
		labelTelephone.setHorizontalAlignment(SwingConstants.LEFT);
		labelTelephone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelTelephone.setAlignmentX(0.5f);
		jpanelLabel2_1_1_1_1.add(labelTelephone);

		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("*");
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_1_1_1_1.setForeground(Color.RED);
		jpanelLabel2_1_1_1_1.add(lblNewLabel_2_1_1_1_1_1);

		JPanel jpanelTextPhone = new JPanel();
		jpanelTextPhone.setOpaque(false);
		jpanelTextPhone.setPreferredSize(new Dimension(220, 25));
		jpanelTelephone.add(jpanelTextPhone);
		jpanelTextPhone.setLayout(new BoxLayout(jpanelTextPhone, BoxLayout.X_AXIS));

		jtextFieldTel = new JTextField();
		jpanelTextPhone.add(jtextFieldTel);
		jtextFieldTel.setColumns(10);

		JPanel jpanelAddress = new JPanel();
		jpanelAddress.setOpaque(false);
		FlowLayout flowLayout_6 = (FlowLayout) jpanelAddress.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		Right_Form.add(jpanelAddress);

		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("");
		lblNewLabel_1_1_2_1_1.setPreferredSize(new Dimension(40, 0));
		jpanelAddress.add(lblNewLabel_1_1_2_1_1);

		JPanel jpanellabel = new JPanel();
		jpanellabel.setOpaque(false);
		jpanellabel.setPreferredSize(new Dimension(100, 25));
		jpanelAddress.add(jpanellabel);
		jpanellabel.setLayout(new BoxLayout(jpanellabel, BoxLayout.X_AXIS));

		JLabel labelAddress = new JLabel("Address:");
		labelAddress.setHorizontalTextPosition(SwingConstants.CENTER);
		labelAddress.setHorizontalAlignment(SwingConstants.LEFT);
		labelAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelAddress.setAlignmentX(0.5f);
		jpanellabel.add(labelAddress);

		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("*");
		lblNewLabel_2_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1_1_1_1_1_1.setForeground(Color.RED);
		jpanellabel.add(lblNewLabel_2_1_1_1_1_1_1);

		JPanel jpanelTextAddress = new JPanel();
		jpanelTextAddress.setOpaque(false);
		jpanelTextAddress.setPreferredSize(new Dimension(220, 25));
		jpanelAddress.add(jpanelTextAddress);
		jpanelTextAddress.setLayout(new BoxLayout(jpanelTextAddress, BoxLayout.X_AXIS));

		jtextFieldAddress = new JTextField();
		jpanelTextAddress.add(jtextFieldAddress);
		jtextFieldAddress.setColumns(10);

		JPanel jpanelImage = new JPanel();
		jpanelImage.setOpaque(false);
		FlowLayout flowLayout_7 = (FlowLayout) jpanelImage.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		Right_Form.add(jpanelImage);

		JLabel lblNewLabel_1_1_2_1_1_1 = new JLabel("");
		lblNewLabel_1_1_2_1_1_1.setPreferredSize(new Dimension(40, 0));
		jpanelImage.add(lblNewLabel_1_1_2_1_1_1);

		JPanel jPanelLabel = new JPanel();
		jPanelLabel.setOpaque(false);
		jPanelLabel.setPreferredSize(new Dimension(100, 120));
		jpanelImage.add(jPanelLabel);
		jPanelLabel.setLayout(new BoxLayout(jPanelLabel, BoxLayout.PAGE_AXIS));

		JLabel labelImage = new JLabel("Image:");
		labelImage.setMaximumSize(new Dimension(120, 25));
		labelImage.setPreferredSize(new Dimension(100, 14));
		labelImage.setHorizontalTextPosition(SwingConstants.CENTER);
		labelImage.setHorizontalAlignment(SwingConstants.LEFT);
		labelImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelImage.setAlignmentX(0.5f);
		jPanelLabel.add(labelImage);

		jlabelImage = new JLabel("");
		jlabelImage.setBorder(new LineBorder(new Color(255, 255, 204)));
		jlabelImage.setSize(new Dimension(120, 120));
		jlabelImage.setPreferredSize(new Dimension(120, 120));
		jpanelImage.add(jlabelImage);

		JPanel Rigth_fix = new JPanel();
		Rigth_fix.setOpaque(false);
		jpanelFormRegister.add(Rigth_fix, BorderLayout.EAST);
		Rigth_fix.setLayout(new BorderLayout(0, 0));

		JPanel jpanelButton = new JPanel();
		jpanelButton.setOpaque(false);
		FlowLayout flowLayout_2 = (FlowLayout) jpanelButton.getLayout();
		flowLayout_2.setVgap(10);
		flowLayout_2.setHgap(10);
		jpanelButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jpanelButton.setPreferredSize(new Dimension(10, 50));
		jpanelFormRegister.add(jpanelButton, BorderLayout.SOUTH);

		JButton jbtnRegister = new JButton("Register");
		jbtnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateInput()) {
					jbtnRegister_actionPerformed(e);
				}
			}
		});
		jpanelButton.add(jbtnRegister);

		JButton jbtnCancel = new JButton("Cancel");
		jbtnCancel.setFocusPainted(false);
		jbtnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnCancel_actionPerformed(e);
			}
		});
		jpanelButton.add(jbtnCancel);

		JPanel Bottom_Fix = new JPanel();
		Bottom_Fix.setOpaque(false);
		add(Bottom_Fix);
		Bottom_Fix.setLayout(new BorderLayout(0, 0));
		ButtonBrowse = new JLabel("Browse");
		ButtonBrowse.setOpaque(true);
		ButtonBrowse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ButtonBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ButtonBrowse_mouseClicked(e);
			}
		});
		ButtonBrowse.setForeground(new Color(255, 255, 0));
		ButtonBrowse.setIcon(new ImageIcon("D:\\CODE\\JavaSwing_Project\\SIMS\\src\\Resources\\Icons\\add2.png"));
		jpanelImage.add(ButtonBrowse);
		initPanel();

		jpanelForm.setBackground(StyleColor.FormColor());
		// focus model.auth.validation
		SetFocusBorder.addFocusBorder(jtextFieldUsername);
		SetFocusBorder.addFocusBorder(jtextFieldPassword);
		SetFocusBorder.addFocusBorder(jtextFieldConfirm);
		SetFocusBorder.addFocusBorder(jtextFieldEmail);
		SetFocusBorder.addFocusBorder(jtextFieldTel);
		SetFocusBorder.addFocusBorder(jtextFieldAddress);

		// set label color
		jlabelRegister.setForeground(StyleColor.TitleColor());
		labelUsername.setForeground(StyleColor.TitleColor());
		labelPassword.setForeground(StyleColor.TitleColor());
		labelConfirmPassword.setForeground(StyleColor.TitleColor());
		labelAddress.setForeground(StyleColor.TitleColor());
		labelEmail.setForeground(StyleColor.TitleColor());
		labelPositions.setForeground(StyleColor.TitleColor());
		labelImage.setForeground(StyleColor.TitleColor());
		labelTelephone.setForeground(StyleColor.TitleColor());

		jbtnRegister.setBackground(StyleColor.BtnBackground());
		jbtnRegister.setRolloverEnabled(false);
		jbtnCancel.setBackground(StyleColor.BtnBackground());
		jbtnCancel.setRolloverEnabled(false);

	}

	private void initPanel() {
		PositionModel positionModel = new PositionModel();
		DefaultComboBoxModel<Positons> model = new DefaultComboBoxModel<Positons>();
		boolean isFirstItem = true;
		 for (Positons positons : positionModel.ShowPosition()) {
		        if (isFirstItem) {
		            isFirstItem = false;
		            continue;
		        }
		        model.addElement(positons);
		    }
		jcomboBoxPositions.setModel(model);
		jcomboBoxPositions.setRenderer(new PositionsBox());

	}

	public void ButtonBrowse_mouseClicked(MouseEvent e) {
		JFileChooser jFileChooser = new JFileChooser("C:\\Users\\Krantz\\Desktop\\img");
		jFileChooser.setDialogTitle("Select a Photo");
		jFileChooser.setMultiSelectionEnabled(false);
		int result = jFileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {

			selectedFile = jFileChooser.getSelectedFile();
			int width = jlabelImage.getWidth();
			int height = jlabelImage.getHeight();
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage()
					.getScaledInstance(width, height, Image.SCALE_DEFAULT));
			jlabelImage.setIcon(imageIcon);

		}
		jFileChooser.setVisible(false);
		repaint();
	}

	public void jbtnRegister_actionPerformed(ActionEvent e) {
		try {
			Employees employee = new Employees();
			employee.setUsername(jtextFieldUsername.getText());
			employee.setPassword(jtextFieldPassword.getText());
			employee.setEmail(jtextFieldEmail.getText());
			Positons positons = (Positons) jcomboBoxPositions.getSelectedItem();
			employee.setPosition_id(positons.getPosition_id());
			employee.setTel(jtextFieldTel.getText());
			employee.setAddress(jtextFieldAddress.getText());
			String verifyCodeString = RandomStringUtils.random(8, true, true);
			employee.setToken(verifyCodeString);
			byte[] bytes = null;
			if (selectedFile != null) {
				bytes = Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
			}
			employee.setImage(bytes);
			RegisterModel registerModel = new RegisterModel();
			if (registerModel.Register(employee)) {
				int result = JOptionPane.showOptionDialog(this,
						"Please go to email '" + jtextFieldEmail.getText() + "' to verify your account",
						"Register Success", JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION, null, null,
						null);
				SendMailModel.sendConfirmEmail(employee.getEmail(), verifyCodeString);
				if (result == JOptionPane.OK_OPTION) {
					ClearScreen();
					JPanelVerifyEmail jVerifyEmail = new JPanelVerifyEmail();
					add(jVerifyEmail);
					jVerifyEmail.setVisible(true);
				} else {
					ClearScreen();
					JPanelLogin jLogin = new JPanelLogin();

					add(jLogin);
					jLogin.setVisible(true);
				}

			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}

	}

	public void jbtnCancel_actionPerformed(ActionEvent e) {
		ClearScreen();
		JPanelLogin jLogin = new JPanelLogin();
		add(jLogin);
		jLogin.setVisible(true);

	}

	private class PositionsBox extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Positons positons = (Positons) value;
			return super.getListCellRendererComponent(list, positons.getPosition_name(), index, isSelected,
					cellHasFocus);
		}

	}

	private boolean validateInput() {
		String username = jtextFieldUsername.getText();
		String password = jtextFieldPassword.getText();
		String confirmPassword = jtextFieldConfirm.getText();
		String email = jtextFieldEmail.getText();
		String tel = jtextFieldTel.getText();
		String address = jtextFieldAddress.getText();

		if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || tel.isEmpty()
				|| address.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter complete information !!", "Failed",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!password.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "Password and confirm password do not match !!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (InputValidator.isValidEmail(email)) {
			InputValidator.showErrorMessage(this, "Incorrect email format !!");
			return false;
		}
		if (InputValidator.isValidPhoneNumber(tel)) {
			InputValidator.showErrorMessage(this, "Incorrect Telephone number format !!");
			return false;
		}
		return true;
	}

	private void ClearScreen() {
		removeAll();
		revalidate();
		this.setOpaque(false);
		repaint();
	}
}
