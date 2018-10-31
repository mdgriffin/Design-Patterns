package com.mgriffin.driver;

import com.mgriffin.client.OrderClient;
import com.mgriffin.coffemat.*;
import com.mgriffin.console.ConsoleOrderBuilder;
import com.mgriffin.events.OrderObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class ConsoleDriver {

    public static void main (String[] args) {
        List<CoffeeMachine> coffeeMachines = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            coffeeMachines.add(new CoffeeMachineImpl());
        }

        OrderService orderService = new OrderServiceImpl(coffeeMachines);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out, true);

        OrderClient orderClient = new OrderClient(orderService, in, out);
        Thread clientThread = new Thread(orderClient);
        clientThread.start();
    }

}
