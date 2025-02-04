//package Models.Auth;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//
//public class PasswordShowHide extends JPanel {
//	private JPasswordField passwordField;
//	private JButton toggleButton;
//	private boolean passwordVisible;
//	private Icon showIcon;
//	private Icon hideIcon;
//
//	public PasswordShowHide() {
//		this.setLayout(new BorderLayout());
//
//		this.passwordField = new JPasswordField();
//		this.passwordVisible = false;
//		this.showIcon = resizeIcon(new ImageIcon("src/Resources/Icons/Show.png"), 24, 24);
//		this.hideIcon = resizeIcon(new ImageIcon("src/Resources/Icons/Hide.png"), 24, 24);
//		
//		this.toggleButton = new JButton(showIcon);
//		
//		toggleButton.setPreferredSize(new Dimension(30, 25));
//		toggleButton.setBorderPainted(false);
//		toggleButton.setFocusPainted(false);
//		toggleButton.setContentAreaFilled(false);
//		toggleButton.setFocusable(false);
//
//		toggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		toggleButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				togglePasswordVisibility();
//			}
//		});
//
//		this.add(passwordField, BorderLayout.CENTER);
//		this.add(toggleButton, BorderLayout.EAST);
//		
//	}
//
//	private void togglePasswordVisibility() {
//		passwordVisible = !passwordVisible;
//		if (passwordVisible) {
//			passwordField.setEchoChar((char) 0); 
//			toggleButton.setIcon(hideIcon);
//			toggleButton.setRolloverEnabled(false);
//			toggleButton.setBorder(null);
//			toggleButton.requestFocus(false);
//		} else {
//			passwordField.setEchoChar('*'); 
//			toggleButton.setIcon(showIcon);
//			toggleButton.setRolloverEnabled(false);
//			toggleButton.setBorder(null);
//			toggleButton.requestFocus(false);
//		}
//	}
//	public JButton getToggleButton() {
//		return toggleButton;
//	}
//	public JPasswordField getPasswordField() {
//		return passwordField;
//	}
//	public String getPasswordAsString() {
//	    return new String(passwordField.getPassword()) ;
//	}
//
//	private Icon resizeIcon(Icon icon, int width, int height) {
//		if (icon instanceof ImageIcon) {
//			
//			ImageIcon imageIcon = (ImageIcon) icon;
//			Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
//			
//			return new ImageIcon(image);
//		}
//		return icon;
//	}
//	
//}

package Models.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordShowHide extends JPanel {
	private JPasswordField passwordField;
	private JButton toggleButton;
	private boolean passwordVisible;
	private Icon showIcon;
	private Icon hideIcon;
	private char echoChar;

	public PasswordShowHide() {
		this.setLayout(new BorderLayout());

		this.passwordField = new JPasswordField();
		this.passwordVisible = false;
		this.echoChar = '*';
		this.showIcon = createResizedIcon("src/Resources/Icons/Show.png", 24, 24);
		this.hideIcon = createResizedIcon("src/Resources/Icons/Hide.png", 24, 24);

		this.toggleButton = new JButton(showIcon);

		toggleButton.setPreferredSize(new Dimension(30, 25));
		toggleButton.setBorderPainted(false);
		toggleButton.setFocusPainted(false);
		toggleButton.setContentAreaFilled(false);
		toggleButton.setFocusable(false);

		toggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				togglePasswordVisibility();
			}
		});

		this.add(passwordField, BorderLayout.CENTER);
		this.add(toggleButton, BorderLayout.EAST);
	}

	private void togglePasswordVisibility() {
		passwordVisible = !passwordVisible;
		echoChar = passwordVisible ? (char) 0 : '*';
		passwordField.setEchoChar(echoChar);
		toggleButton.setIcon(passwordVisible ? hideIcon : showIcon);
		toggleButton.setFocusPainted(false);
	}

	public JButton getToggleButton() {
		return toggleButton;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public String getPasswordAsString() {
		return new String(passwordField.getPassword());
	}

	private Icon createResizedIcon(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
}
