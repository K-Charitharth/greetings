package Assignment.greetings;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendMail {

	public boolean send(String to, String subject, String text) {
		String from = "charitharthkothakota1@gmail.com";
		String host = "smtp.gmail.com";
		String port = "465";

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("charitharthkothakota1@gmail.com", "########");
			}
		});

		session.setDebug(true);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);

			return true;
		} catch (MessagingException m) {
			m.printStackTrace();
			return false;
		}
	}
}