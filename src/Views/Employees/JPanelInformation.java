package Views.Employees;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Window;
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
import javax.swing.SwingUtilities;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import Models.Employees.EmployeeModel;
import Models.Positions.PositionModel;
import Views.Index;

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JPanelInformation extends JPanel {
	private JTextField jtextFieldUsername;
	private JTextField jtextFieldEmail;
	private JTextField jtextFieldTel;
	private JTextField jtextFieldAddress;
	private File selectedFile = null;
	private JLabel jlabelImage;
	private Index parentFrame;
	private JLabel ButtonBrowse;
	private JTextField jtextFieldPosition;
	private int employeeID;
	private EmployeeModel employeeModel = new EmployeeModel();
	private Employees employees = employeeModel.FindByEmployeeID(employeeID);

	/**
	 * Create the panel.
	 */
	public JPanelInformation() {
		setDoubleBuffered(false);
		setFocusTraversalKeysEnabled(false);
		setFocusable(false);
		setRequestFocusEnabled(false);
		setOpaque(false);

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel jpanelForm = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon backgroundImage = new ImageIcon("src/Resources/Images/Background-Blue.png");
				g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
			}
		};
		jpanelForm.setInheritsPopupMenu(true);
		jpanelForm.setIgnoreRepaint(true);
		jpanelForm.setRequestFocusEnabled(false);
		add(jpanelForm);
		jpanelForm.setLayout(new BoxLayout(jpanelForm, BoxLayout.PAGE_AXIS));

		JLabel jlabelInformation = new JLabel("    INFORMATION");
		jlabelInformation.setMinimumSize(new Dimension(300, 60));
		jlabelInformation.setMaximumSize(new Dimension(300, 60));
		jlabelInformation.setPreferredSize(new Dimension(300, 150));
		jlabelInformation.setFont(new Font("Tahoma", Font.BOLD, 26));
		jlabelInformation.setHorizontalAlignment(SwingConstants.CENTER);
		jlabelInformation.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpanelForm.add(jlabelInformation);

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
		jpanelLabel.setPreferredSize(new Dimension(100, 25));
		jpanelUsername.add(jpanelLabel);

		JLabel labelUsername = new JLabel("Username:");
		labelUsername.setHorizontalTextPosition(SwingConstants.CENTER);
		labelUsername.setHorizontalAlignment(SwingConstants.LEFT);
		labelUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelUsername.setAlignmentX(0.5f);
		jpanelLabel.add(labelUsername);

		JPanel jpanelTextUsername = new JPanel();
		jpanelTextUsername.setOpaque(false);
		jpanelTextUsername.setPreferredSize(new Dimension(200, 25));
		jpanelUsername.add(jpanelTextUsername);
		jpanelTextUsername.setLayout(new BoxLayout(jpanelTextUsername, BoxLayout.X_AXIS));

		jtextFieldUsername = new JTextField();
		jtextFieldUsername.setEnabled(false);
		jtextFieldUsername.setDisabledTextColor(Color.BLACK);
		jtextFieldUsername.setColumns(16);
		jpanelTextUsername.add(jtextFieldUsername);

		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setPreferredSize(new Dimension(40, 0));
		jpanelUsername.add(lblNewLabel_1_2);

		JPanel jpanelEmail = new JPanel();
		jpanelEmail.setOpaque(false);
		Left_Form.add(jpanelEmail);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setPreferredSize(new Dimension(40, 0));
		jpanelEmail.add(lblNewLabel_1_1);

		JPanel jpanelLabelEmail = new JPanel();
		jpanelLabelEmail.setOpaque(false);
		FlowLayout fl_jpanelLabelEmail = (FlowLayout) jpanelLabelEmail.getLayout();
		fl_jpanelLabelEmail.setAlignment(FlowLayout.LEFT);
		jpanelLabelEmail.setPreferredSize(new Dimension(100, 25));
		jpanelEmail.add(jpanelLabelEmail);

		JLabel labelEmail = new JLabel("Email:");
		labelEmail.setHorizontalTextPosition(SwingConstants.CENTER);
		labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelEmail.setAlignmentX(0.5f);
		jpanelLabelEmail.add(labelEmail);

		JPanel jpanelMail = new JPanel();
		jpanelMail.setOpaque(false);
		jpanelMail.setPreferredSize(new Dimension(200, 25));
		jpanelEmail.add(jpanelMail);
		jpanelMail.setLayout(new BoxLayout(jpanelMail, BoxLayout.X_AXIS));

		jtextFieldEmail = new JTextField();
		jtextFieldEmail.setDisabledTextColor(Color.BLACK);
		jtextFieldEmail.setEnabled(false);
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

		JPanel JPanelComboBox = new JPanel();
		JPanelComboBox.setOpaque(false);
		JPanelComboBox.setPreferredSize(new Dimension(220, 25));
		jpanelPosition.add(JPanelComboBox);
		JPanelComboBox.setLayout(new BoxLayout(JPanelComboBox, BoxLayout.X_AXIS));

		jtextFieldPosition = new JTextField();
		jtextFieldPosition.setDisabledTextColor(Color.BLACK);
		jtextFieldPosition.setEnabled(false);
		jtextFieldPosition.setColumns(10);
		JPanelComboBox.add(jtextFieldPosition);

		JPanel jpanelTelephone = new JPanel();
		jpanelTelephone.setOpaque(false);
		Left_Form.add(jpanelTelephone);
		jpanelTelephone.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

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

		JPanel jpanelTextPhone = new JPanel();
		jpanelTextPhone.setOpaque(false);
		jpanelTextPhone.setPreferredSize(new Dimension(200, 25));
		jpanelTelephone.add(jpanelTextPhone);
		jpanelTextPhone.setLayout(new BoxLayout(jpanelTextPhone, BoxLayout.X_AXIS));

		jtextFieldTel = new JTextField();
		jpanelTextPhone.add(jtextFieldTel);
		jtextFieldTel.setColumns(10);

		JPanel jpanelAddress = new JPanel();
		jpanelAddress.setOpaque(false);
		FlowLayout flowLayout_6 = (FlowLayout) jpanelAddress.getLayout();
		Left_Form.add(jpanelAddress);

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

		JPanel jpanelTextAddress = new JPanel();
		jpanelTextAddress.setOpaque(false);
		jpanelTextAddress.setPreferredSize(new Dimension(200, 25));
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
		jpanelButton.setPreferredSize(new Dimension(10, 80));
		jpanelFormRegister.add(jpanelButton, BorderLayout.SOUTH);

		JButton jbtnCancel = new JButton("Cancel");
		jbtnCancel.setPreferredSize(new Dimension(70, 30));
		jbtnCancel.setFocusPainted(false);
		jbtnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnCancel_actionPerformed(e);
			}
		});

		JButton jbtnSave = new JButton("Save");
		jbtnSave.setPreferredSize(new Dimension(70, 30));
		jbtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateInput()) {
					jbtnSave_actionPerformed(e);
				}
			}
		});

		JLabel lblNewLabel_1_1_2_1_1_1 = new JLabel("");
		lblNewLabel_1_1_2_1_1_1.setPreferredSize(new Dimension(47, 0));
		jpanelButton.add(lblNewLabel_1_1_2_1_1_1);
		jpanelButton.add(jbtnSave);
		jpanelButton.add(jbtnCancel);
		ButtonBrowse = new JLabel("Browse");
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
		JLabel lblNewLabel_1_1_2_1_1_3 = new JLabel("");
		lblNewLabel_1_1_2_1_1_3.setPreferredSize(new Dimension(40, 0));
		jpanelTelephone.add(lblNewLabel_1_1_2_1_1_3);
		JLabel lblNewLabel_1_1_2_1_1_2 = new JLabel("");
		lblNewLabel_1_1_2_1_1_2.setPreferredSize(new Dimension(40, 0));
		jpanelAddress.add(lblNewLabel_1_1_2_1_1_2);
		// focus model.auth.validation
		SetFocusBorder.addFocusBorder(jtextFieldUsername);
		SetFocusBorder.addFocusBorder(jtextFieldEmail);
		SetFocusBorder.addFocusBorder(jtextFieldTel);
		SetFocusBorder.addFocusBorder(jtextFieldAddress);
		SetFocusBorder.addFocusBorder(jtextFieldPosition);

		// set label color
		jlabelInformation.setForeground(StyleColor.TitleColor());
		labelUsername.setForeground(StyleColor.TitleColor());
		labelAddress.setForeground(StyleColor.TitleColor());
		labelEmail.setForeground(StyleColor.TitleColor());
		labelPositions.setForeground(StyleColor.TitleColor());
		labelImage.setForeground(StyleColor.TitleColor());
		labelTelephone.setForeground(StyleColor.TitleColor());

		// set btn color
		jbtnCancel.setBackground(StyleColor.BtnBackground());
		jbtnCancel.setRolloverEnabled(false);
		jbtnSave.setBackground(StyleColor.BtnBackground());
		jbtnSave.setRolloverEnabled(false);

	}

	public JPanelInformation(int employeeID) {
		this();
		this.employeeID = employeeID;
		this.employees = employeeModel.FindByEmployeeID(employeeID);
		initPanel();
	}

	private void initPanel() {

		PositionModel positionModel = new PositionModel();
		Positons positons = positionModel.FindByPositionID(this.employees.getPosition_id());
		jtextFieldUsername.setText(employees.getUsername());
		jtextFieldEmail.setText(employees.getEmail());
		jtextFieldTel.setText(employees.getTel());
		jtextFieldAddress.setText(employees.getAddress());
		jtextFieldPosition.setText(positons.getPosition_name());
		ShowImgBox(employees.getImage());

	}

	public void ButtonBrowse_mouseClicked(MouseEvent e) {
		try {
			JFileChooser jFileChooser = new JFileChooser("C:\\Users\\Krantz\\Desktop\\img");
			jFileChooser.setDialogTitle("Select a Photo");
			jFileChooser.setMultiSelectionEnabled(false);
			int result = jFileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				selectedFile = jFileChooser.getSelectedFile();
				byte[] img = Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
				ShowImgBox(img);
			}
			jFileChooser.setVisible(false);
			repaint();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
		
	}

	public void jbtnSave_actionPerformed(ActionEvent e) {
		try {
			employees.setUsername(jtextFieldUsername.getText());
			employees.setTel(jtextFieldTel.getText());
			employees.setAddress(jtextFieldAddress.getText());
			byte[] bytes = employees.getImage();
			if (selectedFile != null) {
				bytes = Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
			}
			employees.setImage(bytes);

			if (employeeModel.UpdateAccount(employees)) {
				JOptionPane.showMessageDialog(null, "Edit Account Success !!");
				ClearScreen();
				closeDialog();
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}

	public void jbtnCancel_actionPerformed(ActionEvent e) {
		ClearScreen();
		closeDialog();

	}

	private boolean validateInput() {
		String username = jtextFieldUsername.getText();
		String tel = jtextFieldTel.getText();
		String address = jtextFieldAddress.getText();
		if (username.isEmpty() || tel.isEmpty() || address.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter complete information !!", "Failed",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (InputValidator.isValidPhoneNumber(tel)) {
			InputValidator.showErrorMessage(this, "Incorrect Telephone number format !!");
			return false;
		}
		return true;
	}

	private void ShowImgBox(byte[] img) {
		int width = jlabelImage.getWidth();
		int height = jlabelImage.getHeight();
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon(img).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		jlabelImage.setIcon(imageIcon);
	}

	private void ClearScreen() {
		removeAll();
		revalidate();
		this.setOpaque(false);
		repaint();
	}

	private void closeDialog() {
		Window window = SwingUtilities.getWindowAncestor(JPanelInformation.this);
		if (window instanceof JDialog) {
			JDialog dialog = (JDialog) window;
			dialog.dispose();
		}
	}

}
