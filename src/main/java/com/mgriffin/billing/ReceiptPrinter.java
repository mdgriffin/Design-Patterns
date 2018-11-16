package com.mgriffin.billing;

import com.mgriffin.order.CoffeeOrder;

public abstract class ReceiptPrinter {

    protected static String lineSeperator = System.getProperty("line.separator");

    private  CoffeeOrder coffeeOrder;

    private ReceiptPrinter() {}

    public ReceiptPrinter(CoffeeOrder coffeeOrder) {
        this.coffeeOrder = coffeeOrder;
    }

    public String print () {
        StringBuilder sb = new StringBuilder();
        sb.append(printLogo());
        sb.append(printOrder());
        sb.append(printPrice());
        sb.append(printFooter());

        return sb.toString();
    }

    protected abstract String printLogo ();


    protected String printOrder () {
        return lineSeperator + "=== Your Order === " + lineSeperator +
            coffeeOrder.getCoffeeSize() + " " + coffeeOrder.getCoffeeType() + " with " +
            coffeeOrder.getCondiments().stream().map(condiment -> condiment.getDisplayName() + ",").reduce("", String::concat);
    }

    protected String printPrice () {
        return lineSeperator +"Price: " + coffeeOrder.getPrice();
    }

    protected abstract String printFooter ();
}
