package com.workfront.intern.cb.web.util;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Helpers {
    /**
     * Parse input string to timestamp format
     */
    public static Timestamp stringParseToTimeStamp(String input) {
        Date date;
        long time = 0;

        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(input);

            time = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Timestamp(time);
    }

    /**
     * Image resize
     */
    public static void imageResizeAndWriteToSpecificFolder(String imagePath, int scaledWidth, int scaledHeight) throws IOException {
        imageResizeAndWriteToSpecificFolder(imagePath, imagePath, scaledWidth, scaledHeight);
    }

    /**
     * Image resize
     */
    private static void imageResizeAndWriteToSpecificFolder(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight) throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = null;
        inputImage = ImageIO.read(inputFile);
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

    public static void sendEmail(String inputMsg) {
        try {
            // Load SMTP properties
            ClassLoader classLoader = Helpers.class.getClassLoader();
            InputStream in = classLoader.getResourceAsStream("smtp.properties");
            final Properties props = new Properties();
            props.load(in);
            in.close();

            // Initialize email authenticator
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.pass"));
                }
            });

            // Compose message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty("mail.from")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(props.getProperty("mail.to")));
            message.setSubject("From form");
            message.setText(inputMsg);

            // Send email
            Transport.send(message);
            System.out.println("Done");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}