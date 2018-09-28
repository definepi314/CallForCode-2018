package com.capgemini.callforcode.reusable.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Utils {

    private Utils() {

    }

    public static Date getCurrentDate() {

        return new Date(System.currentTimeMillis());

    }

    public static String dateToString(Date date) {

        String output = "";
        if (null != date) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            output = formatter.format(date);
        }
        return output;
    }

    public static java.util.Date incrementDaysToDate(java.util.Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static java.util.Date incrementMinutesToDate(java.util.Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        // minus number would decrement the
        // days
        return cal.getTime();
    }

    public static java.util.Date convertStringToDate(String inputDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.parse(inputDate);
    }

}
