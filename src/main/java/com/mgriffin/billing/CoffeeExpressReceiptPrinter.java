package com.mgriffin.billing;

import com.mgriffin.order.CoffeeOrder;

public class CoffeeExpressReceiptPrinter extends ReceiptPrinter {

    public CoffeeExpressReceiptPrinter (CoffeeOrder coffeeOrder) {
        super(coffeeOrder);
    }

    @Override
    protected String printLogo() {
        return lineSeperator +
                " __   __   ___  ___  ___  ___     ___      __   __   ___  __   __     " + lineSeperator +
                "/  ` /  \\ |__  |__  |__  |__     |__  \\_/ |__) |__) |__  /__` /__`    " + lineSeperator +
                "\\__, \\__/ |    |    |___ |___    |___ / \\ |    |  \\ |___ .__/ .__/    " + lineSeperator+
                "                                                                      " + lineSeperator;
    }

    @Override
    protected String printFooter() {
        return lineSeperator + "Thank You For Your Custom";
    }

}
