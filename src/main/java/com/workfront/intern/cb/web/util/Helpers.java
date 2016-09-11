package com.workfront.intern.cb.web.util;

import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.AddressException;
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
    public static Timestamp parseStringToTimeStamp(String input) {
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
     * Parse input timestamp to String format
     */
    public static String parseTimeStampToString(Timestamp timestamp) {
        Date date = new Date((timestamp).getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");

        return simpleDateFormat.format(date);
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
        BufferedImage inputImage = ImageIO.read(inputFile);
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

    /**
     * Sending email
     */
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
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * File helpers
     * Generates specific file name
     */
    public static String generateFileName(File existingFile) {
        String filePath = existingFile.getAbsolutePath();
        String fileName = filePath.substring(0, filePath.lastIndexOf("."));
        String fileExt = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());

        File imageFile = existingFile;
        if (!existingFile.getName().endsWith(String.format(").%s", fileExt))) {
            imageFile = new File(fileName + " (1)." + fileExt);
        }

        int index = 2;
        while (imageFile.exists()) {
            fileName = imageFile.getAbsolutePath();
            String next = fileName.substring(0, fileName.lastIndexOf("(") + 1);
            String name = next + index + ")." + fileExt;
            imageFile = new File(name);
            index++;
        }

        return imageFile.getAbsolutePath();
    }

    /**
     * Image helpers
     */
    private static final String[] IMG_EXTS = new String[]{".jpg", ".jpeg", ".png", ".bmp"};

    private static synchronized boolean isImage(String filePath) {
        if (!filePath.contains(".") || filePath.endsWith(".") || filePath.length() < 5) return false;
        int index = filePath.lastIndexOf(".");
        String ext = filePath.substring(index, filePath.length());
        for (String imgExt : IMG_EXTS) {
            if (ext.equalsIgnoreCase(imgExt)) return true;
        }
        return false;
    }

    public static synchronized String getFileExtension(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        if (!isImage(filePath))
            throw new IllegalArgumentException("unknown image format");
        return filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
    }

}