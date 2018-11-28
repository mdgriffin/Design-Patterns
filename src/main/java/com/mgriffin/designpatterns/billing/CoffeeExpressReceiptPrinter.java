package com.mgriffin.designpatterns.billing;

import com.mgriffin.designpatterns.order.CoffeeOrder;

import static com.mgriffin.designpatterns.utility.StringUtils.lineBreak;

public class CoffeeExpressReceiptPrinter extends ReceiptPrinter {

    private CoffeeOrder coffeeOrder;

    private CoffeeExpressReceiptPrinter () {}

    public CoffeeExpressReceiptPrinter (CoffeeOrder coffeeOrder) {
        this.coffeeOrder = coffeeOrder;
    }

    @Override
    protected String printOrder () {
        return lineBreak(coffeeOrder.getCoffeeSize() + " " + coffeeOrder.getCoffeeType() + " with " +
                coffeeOrder.getCondiments().stream().map(condiment -> condiment.getDisplayName() + ",").reduce("", String::concat));
    }

    protected String printPrice () {
        return lineBreak("Price: " + coffeeOrder.getPrice());
    }

}
