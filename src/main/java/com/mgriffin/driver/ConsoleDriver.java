package com.mgriffin.driver;

import com.mgriffin.coffemat.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleDriver {

    private static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args) {
        List<CoffeeMachine> coffeeMachines = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            coffeeMachines.add(new CoffeeMachineImpl());
        }

        OrderService orderService = new OrderServiceImpl(coffeeMachines);

        Customer customer = getCustomer();

        CoffeeOrder order = new CoffeeOrder.CoffeeOrderBuilder()
            .setCustomer(customer)
            .setType(CoffeeType.LATTE)
            .setSize(CoffeeSize.LARGE)
            .addCondiment(CoffeeCondiment.CREAM)
            .order();

        orderService.addOrder(order);

        System.out.println(orderService.toString());
    }

    private static Customer getCustomer () {
        String customerName = "";

        System.out.print("Please enter customer name: ");

        customerName = scanner.nextLine();

        while (customerName.length() == 0) {
            System.out.print("Please enter a valid customer name: ");
            customerName = scanner.nextLine();
        }

        return new Customer(customerName);
    }

}
