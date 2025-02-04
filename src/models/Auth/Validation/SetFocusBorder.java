package Models.Auth.Validation;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SetFocusBorder {
	public static void addFocusBorder(JTextField textField) {

		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setBorder(focusBorder());
			}

			@Override
			public void focusLost(FocusEvent e) {
				String text = textField.getText().trim();

				if (text.isEmpty()) {
					textField.setBorder(BorderFactory.createLineBorder(Color.RED));
				} else {
					if (textField.isEnabled()) {

						textField.setBorder(outcusBorder());
					} else {
						textField.setBorder(null);
					}

				}
			}
		});

	}

	public static void addFocusBorderPassword(JPasswordField passwordField) {
		passwordField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordField.setBorder(focusBorder());
				passwordField.setOpaque(false);
			}

			@Override
			public void focusLost(FocusEvent e) {
				char[] password = passwordField.getPassword();
				if (password.length == 0) {
					passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
				} else {
					passwordField.setBorder(outcusBorder());
				}
			}
		});
	}

	private static CompoundBorder focusBorder() {
		LineBorder outerBorder = new LineBorder(Color.decode("#f6b93b"), 2);
		LineBorder innerBorder = new LineBorder(Color.WHITE, 3);
		CompoundBorder compoundBorder = new CompoundBorder(outerBorder, innerBorder);
		return compoundBorder;
	}

	private static CompoundBorder outcusBorder() {
		LineBorder outerBorder = new LineBorder(Color.decode("#ffffff"), 2);
		LineBorder innerBorder = new LineBorder(Color.WHITE, 3);
		CompoundBorder compoundBorder = new CompoundBorder(outerBorder, innerBorder);
		return compoundBorder;
	}
}
