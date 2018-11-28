package com.mgriffin.designpatterns.driver;

import java.util.Collections;

public class ProgressDriver {


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0;i <= 100; i++) {
            System.out.print(getProgress(i));
            Thread.sleep(25);
        }
    }


    private static String getProgress (int percentage) {
        StringBuilder sb = new StringBuilder();
        sb
        .append("\r")
        .append(String.format("%3d", percentage))
        .append("%")
        .append("[")
        .append(String.join("", Collections.nCopies(percentage, "=")))
        .append('>')
        .append(String.join("", Collections.nCopies(100 - percentage, " ")))
        .append("]");

        return sb.toString();
    }
}
