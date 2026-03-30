package org.demoiselle.forum.email;

import java.util.Properties;
import java.util.logging.Logger;
import jakarta.ejb.Stateless;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/**
 * Email service migrated to Jakarta Mail.
 * Note: DKIM signing removed due to utils-mail-dkim incompatibility with Jakarta Mail.
 * To re-enable DKIM, use a Jakarta Mail compatible DKIM library.
 */
@Stateless
public class Email {

    private static final Logger LOG = Logger.getLogger(Email.class.getName());

    public void send(String addresses, String topic, String textMessage, String ical) {

        final String username = "webmaster@suaapp.com.br";
        final String password = "123456789";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.suaapp.com.br");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("contato@suaapp.com.br"));
            message.setHeader("X-Priority", "1");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);

            BodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(textMessage, "text/html; charset=UTF-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textBodyPart);
            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            LOG.severe(e.getMessage());
        }
    }
}
