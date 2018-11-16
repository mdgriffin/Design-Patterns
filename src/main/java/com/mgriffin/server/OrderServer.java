package com.mgriffin.server;

import com.mgriffin.client.OrderClient;
import com.mgriffin.order.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class OrderServer {

    private static int PORT_NUMBER = 9090;

    public static void main(String[] args)  throws IOException {
        ServerSocket listener = new ServerSocket(PORT_NUMBER);

        OrderServiceFactory factory = new SingleMachineOrderServiceFactory();
        OrderService orderService = factory.createOrderService();

        try {
            while (true) {
                ClientConnection connection = new ClientConnectionImpl(listener, orderService);
                connection.open();
            }
        }
        finally {
            listener.close();
        }
    }

}