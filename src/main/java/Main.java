import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {

  public static void main(String[] args) {

    // Change recipient email address here //
    String recipient = "to@gmail.com";

    // Change sender email address here //
    String sender = "from@gmail.com";

    // Change sender email password here //
    String password = "password";

    // Gmail smtp host //
    String host = "smtp.gmail.com";

    Properties properties = System.getProperties();

    properties.put("mail.smtp.host", host);

    // Use port 465 for Gmail //
    properties.put("mail.smtp.port", "465");

    // Enable SSL socket //
    properties.put("mail.smtp.ssl.enable", "true");

    // Enable authentication //
    properties.put("mail.smtp.auth", "true");

    // Used to verify the authenticity of the email address the email is being sent from //
    Session session =
        Session.getInstance(
            properties,
            new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
              }
            });

    session.setDebug(true);

    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(sender));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
      message.setSubject("Party");
      message.setText("Please see attached file");

      // Prints when the email is attempting to send //
      System.out.println("Sending message...");

      // Sending the message //
      Transport.send(message);

      // Prints if the email was sent successfully //
      System.out.println("Sent message successfully...");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
