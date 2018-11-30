package com.mgriffin.designpatterns.driver;

import com.mgriffin.designpatterns.client.OrderClient;
import com.mgriffin.designpatterns.order.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ConsoleDriver {

    public static void main (String[] args) {
        OrderServiceFactory factory = new SingleMachineOrderServiceFactory();
        OrderService orderService = factory.createOrderService();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out, true);

        OrderClient orderClient = new OrderClient(orderService, in, out);
        Thread clientThread = new Thread(orderClient);
        clientThread.start();
    }

}