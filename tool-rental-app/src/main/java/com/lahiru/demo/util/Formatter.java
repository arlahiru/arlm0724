package com.lahiru.demo.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formatter {

    public static final String DATE_FORMAT = "MM/dd/yy";

    public static String currency(double value){
        return DecimalFormat.getCurrencyInstance(Locale.US).format(value);
    }

    public static String percentage(int value){
        return value+"%";
    }

    public static String date(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern(Formatter.DATE_FORMAT));
    }
}
