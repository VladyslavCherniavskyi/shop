package com.cherniavskyi.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class TimeUtils {

    private TimeUtils() {
    }

    private static final String FORMAT = "DD.MM.YYYY HH:mm:ss";
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    public static String formatter(Date date) {
        var dateFormat = new SimpleDateFormat(FORMAT);
        dateFormat.setTimeZone(UTC);
        return dateFormat.format(date);
    }
}
