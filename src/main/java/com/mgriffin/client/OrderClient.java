package com.mgriffin.client;

import com.mgriffin.command.Command;
import com.mgriffin.console.CustomerMessageOfTheDay;
import com.mgriffin.console.MessageOfTheDay;
import com.mgriffin.console.SimpleMessageOfTheDay;
import com.mgriffin.order.CoffeeOrder;
import com.mgriffin.order.OrderService;
import com.mgriffin.console.ConsoleOrderBuilder;
import com.mgriffin.order.OrderObserver;
import com.mgriffin.server.ClientConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderClient implements Runnable {
    private OrderService orderService;
    private BufferedReader in;
    private PrintWriter out;
    private Command processCompletedCommand;

    public OrderClient (OrderService orderService, BufferedReader in, PrintWriter out) {
        this.orderService = orderService;
        this.in = in;
        this.out = out;
    }

    public void onProcessCompleted () {
        if (this.processCompletedCommand != null) {
            processCompletedCommand.execute();
        }
    }

    public void registerCompletedCommand (Command processCompletedCommand) {
        this.processCompletedCommand = processCompletedCommand;
    }

    @Override
    public void run() {
        try {
            ConsoleOrderBuilder orderBuilder = new ConsoleOrderBuilder(in, out);

            CoffeeOrder coffeeOrder = orderBuilder.getCoffeeOrder();

            MessageOfTheDay messageOfTheDay = new CustomerMessageOfTheDay(new SimpleMessageOfTheDay(), coffeeOrder.getCustomer());
            out.println(messageOfTheDay.getMessage());

            out.println(coffeeOrder.toString());

            orderService.addObserver(coffeeOrder, new OrderObserver() {
                @Override
                public void coffeeAdded() {
                    out.println("Coffee Added");
                }

                @Override
                public void milkAdded() {
                    out.println("Milk Added");
                }

                @Override
                public void condimentsAdded() {
                    out.println("Condiments Added");
                }

                @Override
                public void orderCompleted() {
                    out.println("Order Completed");
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
}
