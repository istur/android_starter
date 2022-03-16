package com.istur.android_starter.utils;

public interface DateFormat {

    String STANDARD_TIME = "HH:mm";
    String STANDARD_DATE = "d MMM YYYY";
    String DATE_MM = "d MM YYYY";
    String STANDARD_DATETIME = STANDARD_DATE + " " + STANDARD_TIME;
    String STANDARD_DATETIME_MM = DATE_MM + " " + STANDARD_TIME;
    String DAY_OF_YEAR_DAY_OF_MONTH_MONTH = "E d MMM";
    String DAY_OF_WEEK_DAY_OF_MONTH_MONTH = "EEEE, MMM d";
    String DAY_OF_WEEK_DAY_OF_MONTH_MONTH_YEAR = "EEE, d MMM yyyy";
    String DAY_OF_WEEK_MONTH_DAY_OF_MONTH_YEAR = "EEE, MMM d yyyy";
    String DAY_OF_MONTH_MONTH_YEAR_HH_MM = "dd MMM yyyy 'at' HH:mm";
    String DAY_OF_WEEK_MONTH_MONTH_YEAR_HH_MM = "EEE, dd MMM yyyy 'at' HH:mm";
    String EXTENDED_DATE_TIME = "EEEE dd MMM yy";
    String E_D_MMM_WITH_COMMA = "E, d MMM";
    String E_D_MMMM_WITH_COMMA = "E, d MMMM";
    String STANDARD_DATE_WITH_SEPARATOR = "dd/MM/YYYY";
    String DAY_TIME_WITHOUT_YEAR = "d MMMM HH:mm a";
    String SMARTCARD_PRODUCT_FORMAT = "yyyyMMdd";
    String SMARTCARD_PRODUCT_FORMAT_FULL = "yyyyMMddhhmm";
    String SMARTCARD_PURCHASE_DATE = "YYYY-MM-dd HH:mm:ss.S";

}