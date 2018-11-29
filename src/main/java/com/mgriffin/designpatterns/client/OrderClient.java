package com.mgriffin.designpatterns.client;

import com.mgriffin.designpatterns.billing.ReceiptPrinter;
import com.mgriffin.designpatterns.billing.CoffeeExpressReceiptPrinter;
import com.mgriffin.designpatterns.command.Command;
import com.mgriffin.designpatterns.command.DoNothingCommand;
import com.mgriffin.designpatterns.console.*;
import com.mgriffin.designpatterns.order.CoffeeOrder;
import com.mgriffin.designpatterns.order.OrderService;
import com.mgriffin.designpatterns.order.OrderObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static com.mgriffin.designpatterns.utility.StringUtils.join;
import static com.mgriffin.designpatterns.utility.StringUtils.lineBreak;
import static com.mgriffin.designpatterns.utility.StringUtils.lineBreakAfter;

public class OrderClient implements Runnable {
    private OrderService orderService;
    private BufferedReader in;
    private PrintWriter out;
    private Command processCompletedCommand;

    private OrderClient () {}

    public OrderClient (OrderService orderService, BufferedReader in, PrintWriter out) {
        this.orderService = orderService;
        this.in = in;
        this.out = out;
        this.processCompletedCommand = new DoNothingCommand();
    }

    private void onProcessCompleted () {
            processCompletedCommand.execute();
    }

    public void registerCompletedCommand (Command processCompletedCommand) {
        this.processCompletedCommand = processCompletedCommand;
    }

    @Override
    public void run() {
        try {
            out.print(getWelcomeMessage());

            WordsOfWisdom messageOfTheDay = new MessageOfTheDay(new QuotedWordsOfWisdom(new RandomWordsOfWisdom()));
            out.println(messageOfTheDay.getMessage());

            ConsoleOrderBuilder orderBuilder = new ConsoleOrderBuilder(in, out);
            CoffeeOrder coffeeOrder = orderBuilder.getCoffeeOrder();

            ReceiptPrinter receiptPrinter = new CoffeeExpressReceiptPrinter(coffeeOrder);
            out.println(receiptPrinter.print());

            orderService.addObserver(coffeeOrder, new OrderObserver() {
                @Override
                public void coffeeAdded() {
                    out.println(lineBreak("Coffee Added to Order"));
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
                    out.println("Order Completed" + lineBreak( "Please collect order from ") + machineName + " in the " + machineLocation + lineBreak("Goodbye!"));
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
