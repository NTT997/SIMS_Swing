package Models.Auth.Validation;

import java.awt.Component;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class InputValidator {
	public static boolean isUsernameValid(String username) {
		return username.isEmpty();
	}

	public static boolean isPasswordValid(String password) {
		return password.isEmpty();
	}

	public static boolean isUsernameOrEmailValid(String input) {
		if (input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
			return false;
		}
		return !input.matches("^[a-zA-Z0-9._]+$");

	}

	public static boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(emailRegex);
		return !pattern.matcher(email).matches();
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		String phoneRegex = "^[0-9]{10}$";
		Pattern pattern = Pattern.compile(phoneRegex);
		return !pattern.matcher(phoneNumber).matches();
	}

	public static void showErrorMessage(Component component, String message) {
		JOptionPane.showMessageDialog(component, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}
}
