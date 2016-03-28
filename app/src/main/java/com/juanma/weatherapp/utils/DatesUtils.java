package com.juanma.weatherapp.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by JuanMa on 27/3/16.
 */
public class DatesUtils {
    public final static String SHORT_DATE_FORMAT = "EE dd";

    public static String toDate(int add) {
        DateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, add);
        return dateFormat.format(cal.getTime()).toString();
    }

}
