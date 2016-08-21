package com.workfront.intern.cb.web.util;

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
}