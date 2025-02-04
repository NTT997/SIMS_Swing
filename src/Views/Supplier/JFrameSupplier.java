package Views.Supplier;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Entities.Suppliers;
import Models.Supplier.SupplierModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class JFrameSupplier extends JFrame {

    private JPanel contentPane;
    private JTextField jtextFieldName;
    private JTextField jtextfieldmail;
    private JTextField JtextfieldPhonenumber;
    private JTextField jtextfieldWeb;
    private JDateChooser dateChooserdile;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrameSupplier frame = new JFrameSupplier();
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
    public JFrameSupplier() {
    	setTitle("ADD SUPPLIER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 581, 310);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel = new JLabel("New Supplier");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JLabel lblNewLabel_1 = new JLabel("Name:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 0;
        panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

        jtextFieldName = new JTextField();
        GridBagConstraints gbc_jtextFieldName = new GridBagConstraints();
        gbc_jtextFieldName.gridwidth = 4;
        gbc_jtextFieldName.insets = new Insets(0, 0, 5, 5);
        gbc_jtextFieldName.fill = GridBagConstraints.HORIZONTAL;
        gbc_jtextFieldName.gridx = 1;
        gbc_jtextFieldName.gridy = 0;
        panel.add(jtextFieldName, gbc_jtextFieldName);
        jtextFieldName.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("Email:");
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_5.gridx = 0;
        gbc_lblNewLabel_5.gridy = 1;
        panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

        jtextfieldmail = new JTextField();
        GridBagConstraints gbc_jtextfieldmail = new GridBagConstraints();
        gbc_jtextfieldmail.gridwidth = 4;
        gbc_jtextfieldmail.insets = new Insets(0, 0, 5, 5);
        gbc_jtextfieldmail.fill = GridBagConstraints.HORIZONTAL;
        gbc_jtextfieldmail.gridx = 1;
        gbc_jtextfieldmail.gridy = 1;
        panel.add(jtextfieldmail, gbc_jtextfieldmail);
        jtextfieldmail.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Phone Number:");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 2;
        panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

        JtextfieldPhonenumber = new JTextField();
        GridBagConstraints gbc_JtextfieldPhonenumber = new GridBagConstraints();
        gbc_JtextfieldPhonenumber.gridwidth = 4;
        gbc_JtextfieldPhonenumber.insets = new Insets(0, 0, 5, 5);
        gbc_JtextfieldPhonenumber.fill = GridBagConstraints.HORIZONTAL;
        gbc_JtextfieldPhonenumber.gridx = 1;
        gbc_JtextfieldPhonenumber.gridy = 2;
        panel.add(JtextfieldPhonenumber, gbc_JtextfieldPhonenumber);
        JtextfieldPhonenumber.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Website:");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 3;
        panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

        jtextfieldWeb = new JTextField();
        GridBagConstraints gbc_jtextfieldWeb = new GridBagConstraints();
        gbc_jtextfieldWeb.gridwidth = 4;
        gbc_jtextfieldWeb.insets = new Insets(0, 0, 5, 5);
        gbc_jtextfieldWeb.fill = GridBagConstraints.HORIZONTAL;
        gbc_jtextfieldWeb.gridx = 1;
        gbc_jtextfieldWeb.gridy = 3;
        panel.add(jtextfieldWeb, gbc_jtextfieldWeb);
        jtextfieldWeb.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Date:");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 4;
        panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

        dateChooserdile = new JDateChooser();
        GridBagConstraints gbc_dateChooserdile = new GridBagConstraints();
        gbc_dateChooserdile.gridwidth = 4;
        gbc_dateChooserdile.insets = new Insets(0, 0, 5, 5);
        gbc_dateChooserdile.fill = GridBagConstraints.BOTH;
        gbc_dateChooserdile.gridx = 1;
        gbc_dateChooserdile.gridy = 4;
        panel.add(dateChooserdile, gbc_dateChooserdile);

        JButton jbtnSave = new JButton("Save");
        jbtnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jbtnSave_actionPerformed(e);
            }
        });
        GridBagConstraints gbc_jbtnSave = new GridBagConstraints();
        gbc_jbtnSave.insets = new Insets(0, 0, 5, 5);
        gbc_jbtnSave.gridx = 2;
        gbc_jbtnSave.gridy = 5;
        panel.add(jbtnSave, gbc_jbtnSave);

        JButton jbtnCancel = new JButton("Cancel");
        jbtnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jbtnCancel_actionPerformed(e);
            }
        });
        GridBagConstraints gbc_jbtnCancel = new GridBagConstraints();
        gbc_jbtnCancel.insets = new Insets(0, 0, 5, 5);
        gbc_jbtnCancel.gridx = 3;
        gbc_jbtnCancel.gridy = 5;
        panel.add(jbtnCancel, gbc_jbtnCancel);
    }

    public void jbtnSave_actionPerformed(ActionEvent e) {
        SupplierModel supplierModel = new SupplierModel();
        Suppliers suppliers = new Suppliers();

        String name = jtextFieldName.getText();
        String mail = jtextfieldmail.getText();
        String phoneNumberText = JtextfieldPhonenumber.getText();
        String website = jtextfieldWeb.getText();
        Date createdAt = dateChooserdile.getDate();

        // Check each individual data field to see if they are empty
        if (name.isEmpty() || mail.isEmpty() || phoneNumberText.isEmpty() || website.isEmpty() || createdAt == null) {
            JOptionPane.showMessageDialog(this, "Data cannot be left blank", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            // Check mail format
            if (!isValidEmail(mail)) {
                JOptionPane.showMessageDialog(this, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if the email is invalid
            }

            suppliers.setName(name);
            suppliers.setMail(mail);

            // Convert phone number text to integer
            try {
                int phoneNumber = Integer.parseInt(phoneNumberText);
                suppliers.setTel(phoneNumber);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Phone number invalid", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if the phone number is invalid
            }

            suppliers.setWebsite(website);
            suppliers.setCreated_at(createdAt);

            supplierModel.creatSupplier(suppliers);
            JOptionPane.showMessageDialog(this, "Supplier is Added", "Successful", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    // Function to check email format using regular expression
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }

    public void jbtnCancel_actionPerformed(ActionEvent e) {
        dispose();
    }

}
