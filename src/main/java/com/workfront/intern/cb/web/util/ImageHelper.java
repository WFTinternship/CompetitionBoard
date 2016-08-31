package com.workfront.intern.cb.web.util;

import java.io.File;

/**
 *
 */
public class ImageHelper {
	public static final String[] IMG_EXTS = new String[] {".jpg", ".jpeg", ".png", ".bmp"};

	public static synchronized boolean isImage(String filePath) {
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
