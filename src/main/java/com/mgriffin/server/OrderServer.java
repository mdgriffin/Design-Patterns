package com.mgriffin.server;

import com.mgriffin.client.OrderClient;
import com.mgriffin.coffemat.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class OrderServer {

    private static int PORT_NUMBER = 9090;

    public static void main(String[] args)  throws IOException {
        ServerSocket listener = new ServerSocket(PORT_NUMBER);
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

                OrderClient orderClient = new OrderClient(orderService, in, out);

                Thread clientThread = new Thread(orderClient);
                clientThread.start();

                orderClient.registerCompletedCallback(() -> {
                    try {
                        socket.close();
                    } catch (IOException exc) {
                        System.out.println(exc);
                    }
                });
            }
        }
        finally {
            listener.close();
        }
    }

}
