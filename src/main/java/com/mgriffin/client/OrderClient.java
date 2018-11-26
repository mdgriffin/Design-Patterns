package com.mgriffin.client;

import com.mgriffin.billing.ReceiptPrinter;
import com.mgriffin.billing.CoffeeExpressReceiptPrinter;
import com.mgriffin.command.Command;
import com.mgriffin.command.DoNothingCommand;
import com.mgriffin.console.CustomerMessageOfTheDay;
import com.mgriffin.console.MessageOfTheDay;
import com.mgriffin.console.SimpleMessageOfTheDay;
import com.mgriffin.order.CoffeeOrder;
import com.mgriffin.order.OrderService;
import com.mgriffin.console.ConsoleOrderBuilder;
import com.mgriffin.order.OrderObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static com.mgriffin.utility.StringUtils.join;
import static com.mgriffin.utility.StringUtils.lineBreak;
import static com.mgriffin.utility.StringUtils.lineBreakAfter;

public class OrderClient implements Runnable {
    private OrderService orderService;
    private BufferedReader in;
    private PrintWriter out;
    private Command processCompletedCommand;
    protected static String lineSeperator = System.getProperty("line.separator");


    private OrderClient () {}

    public OrderClient (OrderService orderService, BufferedReader in, PrintWriter out) {
        this.orderService = orderService;
        this.in = in;
        this.out = out;
        this.processCompletedCommand = new DoNothingCommand();
    }

    public void onProcessCompleted () {
            processCompletedCommand.execute();
    }

    public void registerCompletedCommand (Command processCompletedCommand) {
        this.processCompletedCommand = processCompletedCommand;
    }

    @Override
    public void run() {
        try {
            out.print(getWelcomeMessage());

            ConsoleOrderBuilder orderBuilder = new ConsoleOrderBuilder(in, out);
            CoffeeOrder coffeeOrder = orderBuilder.getCoffeeOrder();

            MessageOfTheDay messageOfTheDay = new CustomerMessageOfTheDay(new SimpleMessageOfTheDay(), coffeeOrder.getCustomer());
            out.println(messageOfTheDay.getMessage());

            ReceiptPrinter receiptPrinter = new CoffeeExpressReceiptPrinter(coffeeOrder);
            out.println(receiptPrinter.print());

            orderService.addObserver(coffeeOrder, new OrderObserver() {
                @Override
                public void coffeeAdded() {
                    out.println(lineSeperator +"Coffee Added to Order");
                }

                @Override
                public void milkAdded() {
                    out.println("Milk Added to Order");
                }

                @Override
                public void condimentsAdded() {
                    out.println("Condiments Added to Order");
                }

                @Override
                public void orderCompleted(String machineName, String machineLocation) {
                    out.println("Order Completed" + lineSeperator + "Please collect order from " + machineName + " in the " + machineLocation + lineSeperator + "Goodbye!");
                    onProcessCompleted();
                }

                @Override
                public void queuePositionChanged(int currentPosition) {
                    out.println("Current Position in Queue: " + currentPosition);
                }
            });

            orderService.addOrder(coffeeOrder);
        } catch (IOException exc) {
            System.out.println(exc);
        }
    }

    private static String getWelcomeMessage () {
        return lineBreakAfter(join(
        "Welcome To",
            lineBreak(" __   __   ___  ___  ___  ___     ___      __   __   ___  __   __     "),
            lineBreak("/  ` /  \\ |__  |__  |__  |__     |__  \\_/ |__) |__) |__  /__` /__`    "),
            lineBreak("\\__, \\__/ |    |    |___ |___    |___ / \\ |    |  \\ |___ .__/ .__/    "),
            lineBreak("                                                                      ")
        ));
    }
}
