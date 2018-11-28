package com.mgriffin.designpatterns.billing;

import static com.mgriffin.designpatterns.utility.StringUtils.lineBreak;

public abstract class ReceiptPrinter {

    public String print () {
        StringBuilder sb = new StringBuilder();
        sb.append(printHeader());
        sb.append(printOrder());
        sb.append(printPrice());
        sb.append(printFooter());

        return sb.toString();
    }

    protected String printHeader () {
        return lineBreak("=== Your Order ===");
    }

    abstract String printOrder();

    abstract String printPrice();

    protected String printFooter() {
        return lineBreak(lineBreak("Thanks For Your Custom"));
    }
}
