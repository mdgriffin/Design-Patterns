package com.mgriffin.designpatterns.utility;

public class StringUtils {

    private static String lineSeperator = System.getProperty("line.separator");

    public static String lineBreak (String text) {
        return lineSeperator + text;
    }

    public static String lineBreakAfter (String text) {
        return text + lineSeperator;
    }

    public static String join(String ...arr) {
        return String.join("", arr);
    }
}
