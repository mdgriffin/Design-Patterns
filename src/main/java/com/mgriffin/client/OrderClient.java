package com.mgriffin.client;

import com.mgriffin.command.ProcessCompletedCallback;
import com.mgriffin.order.CoffeeOrder;
import com.mgriffin.order.OrderService;
import com.mgriffin.console.ConsoleOrderBuilder;
import com.mgriffin.order.OrderObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderClient implements Runnable {

    private OrderService orderService;
    private BufferedReader in;
    private PrintWriter out;
    private ProcessCompletedCallback processCompletedCallback;

    public OrderClient (OrderService orderService, BufferedReader in, PrintWriter out) {
        this.orderService = orderService;
        this.in = in;
        this.out = out;
    }

    public void onProcessCompleted () {
        if (processCompletedCallback != null) {
            processCompletedCallback.onProcessCompleted();
        }
    }

    public void registerCompletedCallback (ProcessCompletedCallback processCompletedCallback) {
        this.processCompletedCallback = processCompletedCallback;
    }

    @Override
    public void run() {
        try {
            ConsoleOrderBuilder orderBuilder = new ConsoleOrderBuilder(in, out);
            CoffeeOrder coffeeOrder = orderBuilder.getCoffeeOrder();

            out.println("Your order: \n" + coffeeOrder.toString());

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
