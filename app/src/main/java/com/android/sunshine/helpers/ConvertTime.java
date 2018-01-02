package com.android.sunshine.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nazila on 12/25/2017.
 */

public final class ConvertTime {
    private static String TIME_PATTERN = "h:mm a";
    public static long THOUSAND = 1000;
    public static  String convertTime(long unixTime, String pattern) {
        String time = new SimpleDateFormat(TIME_PATTERN)
                .format(new Date(unixTime * THOUSAND));
        return time;
    }
    //    public final static String convertUnixSecondsToDateOrTime(Context context, long unixSeconds, String pattern) {
//        final long THOUSAND = 1000l;
//        final String TIME_ZONE = "GMT+3.5";
//        Date date = new Date(unixSeconds * THOUSAND);
//        SimpleDateFormat daytime = new SimpleDateFormat(pattern);
//        daytime.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
//        String formattedDate = daytime.format(date);
//        daytime.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
//        return String.valueOf(formattedDate);
//    }
}
