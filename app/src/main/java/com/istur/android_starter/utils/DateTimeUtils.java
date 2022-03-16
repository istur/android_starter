package com.istur.android_starter.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Locale;

public class DateTimeUtils implements DateFormat {

    private final static String DEFAULT_DATE_TIME_PATTERN = DateFormat.STANDARD_DATETIME;
    private final static String DEFAULT_DATE_PATTERN = DateFormat.STANDARD_DATE;
    private final static String DEFAULT_TIME_PATTERN = DateFormat.STANDARD_TIME;

    private static DateTimeFormatter formatter = null;


    public static String printStandardDateTime(DateTime date) {
        return printWithPattern(date, STANDARD_DATETIME);
    }

    public static String printStandardDate(DateTime dateTime) {
        return printWithPattern(dateTime, STANDARD_DATE);
    }

    public static String printStandardTime(DateTime dateTime) {
        return printWithPattern(dateTime, STANDARD_TIME);
    }

    public static String printWithPattern(DateTime dateTime, String pattern) {
        return printWithPattern(dateTime.toInstant().getMillis(), pattern);
    }

    public static String printWithPattern(long instant, String pattern) {
        return DateTimeFormat
                .forPattern(pattern)
                .withLocale(Locale.ENGLISH)
                .print(instant);
    }

    public static PeriodFormatter getHmPeriodFormatter() {
        return new PeriodFormatterBuilder()
                .appendHours().appendSuffix("h").appendSeparator(" ")
                .appendMinutes().appendSuffix("min").toFormatter();
    }

    public static DateTime parseString(String stringDate, String pattern) {
        if (stringDate == null || stringDate.isEmpty()) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
        DateTime date = dateTimeFormatter.parseDateTime(stringDate);
        return date;
    }

    public static String getSuffixDay(DateTime date) {
        int day = date.getDayOfMonth();

        if (!((day > 10) && (day < 19)))
            switch (day % 10) {
                case 1:
                    return DateTimeFormat.forPattern("dd'st' MMMM yyyy HH:mm").withLocale(Locale.ENGLISH).print(date);
                case 2:
                    return DateTimeFormat.forPattern("dd'nd' MMMM yyyy HH:mm").withLocale(Locale.ENGLISH).print(date);
                case 3:
                    return DateTimeFormat.forPattern("dd'rd' MMMM yyyy HH:mm").withLocale(Locale.ENGLISH).print(date);
                default:
                    return DateTimeFormat.forPattern("dd'th' MMMM yyyy HH:mm").withLocale(Locale.ENGLISH).print(date);
            }
        return DateTimeFormat.forPattern("dd'th' MMMM yyyy HH:mm").withLocale(Locale.ENGLISH).print(date);
    }

    public static DateTime getUKTimeZone(DateTime dateTime) {
        return dateTime.withZone(DateTimeZone.forID("Europe/London"));
    }


}
