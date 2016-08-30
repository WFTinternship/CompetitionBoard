package com.workfront.intern.cb.web.util;

import java.io.File;

/**
 *
 */
public class FileHelper {

	public static String generateFileName(File existingFile) {
		String filePath = existingFile.getAbsolutePath();
		String fileName = filePath.substring(0, filePath.lastIndexOf("."));
		String fileExt = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());

		File imageFile = existingFile;
		if (!existingFile.getName().endsWith(String.format(").%s", fileExt))) {
			imageFile = new File (fileName + " (1)." + fileExt);
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
}