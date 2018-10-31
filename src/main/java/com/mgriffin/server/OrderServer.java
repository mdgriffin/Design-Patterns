package com.mgriffin.server;

import com.mgriffin.coffemat.*;
import com.mgriffin.console.ConsoleOrderBuilder;
import com.mgriffin.events.OrderObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class OrderServer {

    public static void main(String[] args)  throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        List<CoffeeMachine> coffeeMachines = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            coffeeMachines.add(new CoffeeMachineImpl());
        }

        OrderService orderService = new OrderServiceImpl(coffeeMachines);

        try {
            while (true) {
                Socket socket = listener.accept();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                ConsoleOrderBuilder orderBuilder = new ConsoleOrderBuilder(in, out);
                CoffeeOrder coffeeOrder = orderBuilder.getCoffeeOrder();

                out.println("Your order: \n" + coffeeOrder.toString());

                // TODO: Refactor so casting is not necessary
                ((OrderServiceImpl) orderService).addObserver(coffeeOrder, new OrderObserver() {
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
                        try {
                            socket.close();
                        } catch (IOException exc) {
                            out.println(exc);
                        }
                    }

                    @Override
                    public void queuePositionChanged(int currentPosition) {
                        out.println("Current Position in Queue: " + currentPosition);
                    }
                });

                orderService.addOrder(coffeeOrder);
            }
        }
        finally {
            listener.close();
        }
    }

}
