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
        Date date = null;
        long time = 0;

        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(input);

            time = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return new Timestamp(time);
    }

    public static void main(String[] args) {

        String str = "1985-04-12T23:20";
        Timestamp timestamp = stringParseToTimeStamp(str);
        System.out.println(timestamp);
    }

}
