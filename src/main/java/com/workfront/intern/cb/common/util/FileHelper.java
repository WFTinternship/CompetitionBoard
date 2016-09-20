package com.workfront.intern.cb.common.util;

import java.io.*;
import java.util.List;

/**
 * Created by Inmelet on 9/20/2016
 */
public class FileHelper {

    public static String readFileContent(File file) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));

        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = in.readLine()) != null) {
            sb.append(s).append(" ");
        }

        in.close();
        return sb.toString().replaceAll("\t", "");
    }

    public static void readFileToList(File file, List<String> list) throws IOException {
        InputStream in = new FileInputStream(file);
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }
    }

}
