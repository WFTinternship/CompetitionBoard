package com.workfront.intern.cb.web.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
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

    public static void main(String[] args) throws IOException {
        String path = "/home/artbabayan/Desktop/Projects/dev/Learning/CompetitionBoard/CompetitionBoard/src/main/webapp/resources/img/test/avatar.jpg";
        imageResizeAndWriteToSpecificFolder(path, 40, 40);
    }
}