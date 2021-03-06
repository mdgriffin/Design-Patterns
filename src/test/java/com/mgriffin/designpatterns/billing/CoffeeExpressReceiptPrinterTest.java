package com.mgriffin.designpatterns.billing;

import com.mgriffin.designpatterns.order.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static com.mgriffin.designpatterns.utility.StringUtils.lineBreak;
import static com.mgriffin.designpatterns.utility.StringUtils.lineBreakAfter;
import static com.mgriffin.designpatterns.utility.StringUtils.join;

public class CoffeeExpressReceiptPrinterTest {

    @Test
    public void whenPrintingReceipt_itemsPrintedInCorrectOrder () {
        CoffeeOrder coffeeOrder = new CoffeeOrder.CoffeeOrderBuilder()
            .setDiscount((amount) -> { return amount * 1d;})
            .setCustomer(new Customer("John Doe"))
            .setType(CoffeeType.LATTE)
            .setSize(CoffeeSize.LARGE)
            .addCondiment(CoffeeCondiment.CREAM)
            .order();
        ReceiptPrinter receiptPrinter = new CoffeeExpressReceiptPrinter(coffeeOrder);
        String expectedResult = lineBreak(join(
                lineBreakAfter("=== Your Order ==="),
                lineBreakAfter("LARGE LATTE with Cream,"),
                lineBreakAfter("Price: 3.7"),
                lineBreakAfter(""),
                "Thanks For Your Custom"));

        String actualResult = receiptPrinter.print();
        assertEquals(expectedResult, actualResult);
    }

}
