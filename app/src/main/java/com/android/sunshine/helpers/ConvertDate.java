package com.android.sunshine.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Nazila on 1/1/2018.
 */

public class ConvertDate {
    private static String DATE_PATTERN = "\"EEE, MMM d\"";
    private static long THOUSAND = 1000l;
    private static String TIME_ZONE = "GMT+3.5";

    public static String convertDate(long longDate) {
        Date date = new Date(longDate * THOUSAND);
        SimpleDateFormat daytime = new SimpleDateFormat(DATE_PATTERN);
        daytime.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        String formattedDate = daytime.format(date);
        return formattedDate;
    }
}
