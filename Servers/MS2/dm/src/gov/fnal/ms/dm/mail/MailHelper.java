package gov.fnal.ms.dm.mail;

//import java.text.DateFormat;

import java.util.Date;

import java.util.logging.Logger;

import javax.annotation.Resource;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHelper {
    
    private Session session; 
    static final Logger logger = Logger.getLogger("MailHelper");
    
    public MailHelper(Session session) {
        this.session = session;
    }
    public void sendMessage(String recipient, String subject, String body) {
        try {
            if(session != null) {
                Message message = new MimeMessage(session);
                message.setFrom();
                message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(recipient, false));
                message.setSubject(subject);
                //DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT);
                message.setText(body);
                //message.setHeader("X-Mailer", mailer);
                message.setSentDate(new Date());
                Transport.send(message);
                logger.info("Mail sent to " + recipient + ".");
            }
        } catch (MessagingException ex) {
            ex.printStackTrace();
            logger.info("Error in MailHelper for " + recipient +  " Detail is " + ex.getMessage());
        }
    }
}
