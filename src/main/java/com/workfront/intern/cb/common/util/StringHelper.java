package com.workfront.intern.cb.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringHelper {

    // Gets provided password encrypted with SHA-256.
    public static String passToEncrypt(String password) {
        StringBuffer sb = null;
        String passToEncrypt = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();

            //convert the byte to hex format
            sb = new StringBuffer();
            for (byte aByteData : byteData) {
                sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (sb != null) {
            passToEncrypt = sb.toString();
        }
        return passToEncrypt;
    }

    public static String deCapitalize(String text) {
        if (text.length() == 1) {
            return text.toLowerCase();
        } else {
            return text.substring(0, 1).toLowerCase() + text.substring(1);
        }
    }

    public static boolean contains(String[] values, String token) {
        return contains(values, token, false);
    }

    public static boolean contains(String[] values, String token, boolean ignoreCase) {
        if (token == null)
            return false;
        if (values == null || values.length == 0)
            return false;

        for (String val : values) {
            if (ignoreCase) {
                if (val != null && token.toLowerCase().equals(val.toLowerCase())) {
                    return true;
                }
            } else {
                if (token.equals(val)) {
                    return true;
                }
            }
        }
        return false;
    }
}