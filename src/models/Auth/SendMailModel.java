package Models.Auth;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMailModel {
	public static void sendConfirmEmail(String recipientEmail, String CodeMessage) {
		final String username = "thq130692@gmail.com";
		final String password = "rubjrxrlaehdcazu";
		// Cài đặt các thuộc tính cho sesion
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("SIMS application verify code:");
			String htmlContent = "<hr/><br/>" + "<div>"
					+ "<div style='font-size: 18px;width: 100%;font-style: italic;color: #3776c9;'>Your Verification Code is:</div>"
					+ "<p style='font-size: 20px;display:inline-block;background-color: #ebeb43;padding:10px 80px;margin: 10px'><strong>"
					+ CodeMessage + "</strong></p>" + "</div>"
					+ "<hr/><div style='font-size:16px;color: #3776c9;'>From Administrator SIMS !!!</div>";

			message.setContent(htmlContent, "text/html; charset=utf-8");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
