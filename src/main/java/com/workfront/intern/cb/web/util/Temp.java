package com.workfront.intern.cb.web.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Temp {
    public static void main(String[] args) {

        final Properties props = new Properties();
        props.getProperty("mail.smtp.host");
        props.getProperty("mail.smtp.socketFactory.port");
        props.getProperty("mail.smtp.socketFactory.class");
        props.getProperty("mail.smtp.auth");
        props.getProperty("mail.smtp.port");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info-traffic-fines@mail.ru"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("art-babayan@yandex.ru"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler," +
                    "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}