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
        Date startDate = null;
        long time = 0;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(input);
            time = startDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return new Timestamp(time);
    }
}
