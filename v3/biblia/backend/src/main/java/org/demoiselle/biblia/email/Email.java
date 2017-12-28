package org.demoiselle.biblia.email;

import java.io.File;
import java.util.Properties;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import net.markenwerk.utils.mail.dkim.Canonicalization;
import net.markenwerk.utils.mail.dkim.DkimMessage;
import net.markenwerk.utils.mail.dkim.DkimSigner;
import net.markenwerk.utils.mail.dkim.SigningAlgorithm;
import org.jgroups.util.UUID;

/**
 *
 * @author SERPRO
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
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
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

            MimeMessage dkimSignedMessage = dkimSignMessage(message, username, "suaapp.com.br", "mail");
            Transport.send(dkimSignedMessage);

        } catch (MessagingException e) {
            LOG.severe(e.getMessage());
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
        }
    }

    private MimeMessage dkimSignMessage(MimeMessage message, String from, String signingDomain, String selector) throws Exception {
        // http://www.antispam.br/admin/dkim/
        File file = new File("/opt/key/dkim.der");
        DkimSigner dkimSigner = new DkimSigner(signingDomain, selector, file);
        dkimSigner.setIdentity(from);
        dkimSigner.setHeaderCanonicalization(Canonicalization.SIMPLE);
        dkimSigner.setBodyCanonicalization(Canonicalization.RELAXED);
        dkimSigner.setSigningAlgorithm(SigningAlgorithm.SHA256_WITH_RSA);
        dkimSigner.setLengthParam(true);
        dkimSigner.setZParam(false);
        return new DkimMessage(message, dkimSigner);
    }

}
