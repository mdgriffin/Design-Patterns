package com.mgriffin.billing;

import com.mgriffin.order.CoffeeOrder;

public abstract class ReceiptPrinter {

    protected static String lineSeperator = System.getProperty("line.separator");

    public String print () {
        StringBuilder sb = new StringBuilder();
        sb.append(printHeader());
        sb.append(printOrder());
        sb.append(printPrice());
        sb.append(printFooter());

        return sb.toString();
    }

    protected String printHeader () {
        return lineSeperator + "=== Your Order ===";
    }

    abstract String printOrder();

    abstract String printPrice();

    protected String printFooter() {
        return lineSeperator + lineSeperator + "Thanks For Your Custom";
    }
}
