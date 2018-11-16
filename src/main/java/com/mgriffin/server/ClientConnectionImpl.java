package com.mgriffin.server;

import com.mgriffin.client.OrderClient;
import com.mgriffin.order.OrderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnectionImpl implements  ClientConnection {
    private ServerSocket listener;
    private OrderService orderService;
    private Socket socket;

    public ClientConnectionImpl (ServerSocket listener, OrderService orderService) throws IOException {
        this.listener = listener;
        this.orderService = orderService;
    }

    @Override
    public void open() {
        if (socket == null && listener != null && !listener.isClosed()) {
            try {
                this.socket = listener.accept();
                System.out.println("New Client Connected");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                OrderClient orderClient = new OrderClient(orderService, in, out);

                orderClient.registerCompletedCommand(() -> {
                    ClientConnectionImpl.this.close();
                });

                Thread clientThread = new Thread(orderClient);
                clientThread.start();
            } catch (IOException exc) {
                System.out.println(exc.getMessage());
            }
        }
    }

    private void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
