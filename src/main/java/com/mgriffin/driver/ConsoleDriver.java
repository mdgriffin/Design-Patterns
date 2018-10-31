package com.mgriffin.driver;

import com.mgriffin.coffemat.*;
import com.mgriffin.events.OrderObserver;

import java.util.*;

public class ConsoleDriver {

    private static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args) {
        List<CoffeeMachine> coffeeMachines = new ArrayList<>();
        CoffeeOrder.CoffeeOrderBuilder coffeeOrderBuilder = new CoffeeOrder.CoffeeOrderBuilder();

        for (int i = 0; i < 4; i++) {
            coffeeMachines.add(new CoffeeMachineImpl());
        }

        OrderService orderService = new OrderServiceImpl(coffeeMachines);

        coffeeOrderBuilder.setCustomer(getCustomer());
        coffeeOrderBuilder.setType(getCoffeeType());
        coffeeOrderBuilder.setSize(getSize());
        getCondiments().stream().forEach(coffeeCondiment -> coffeeOrderBuilder.addCondiment(coffeeCondiment));
        CoffeeOrder coffeeOrder = coffeeOrderBuilder.order();

        System.out.println("Your order: \n" + coffeeOrder.toString());

        CoffeeOrder order = coffeeOrderBuilder.order();

        ((OrderServiceImpl) orderService).addObserver(order, new OrderObserver() {
            @Override
            public void coffeeAdded() {
                System.out.println("Coffee Added");
            }

            @Override
            public void milkAdded() {
                System.out.println("Milk Added");
            }

            @Override
            public void condimentsAdded() {
                System.out.println("Condiments Added");
            }

            @Override
            public void orderCompleted() {
                System.out.println("Order Completed");
                System.exit(0);
            }

            @Override
            public void queuePositionChanged(int currentPosition) {
                System.out.println("Current Position in Queue: " + currentPosition);
            }
        });

        orderService.addOrder(order);
    }

    private static CoffeeType getCoffeeType () {
        System.out.println("Please select Coffee Type from the following options: ");
        Arrays.stream(CoffeeType.values()).forEach(type -> System.out.println((type.ordinal() + 1) + ": " + type.getDisplayName()));

        return CoffeeType.values()[getValidInt(CoffeeType.values().length) - 1];
    }

    private static CoffeeSize getSize () {
        System.out.println("Please select the size of the coffee: ");
        Arrays.stream(CoffeeSize.values()).forEach(size -> System.out.println((size.ordinal() + 1) + ": " + size.getDisplayName()));

        return CoffeeSize.values()[getValidInt(CoffeeSize.values().length) - 1];
    }

    private static List<CoffeeCondiment> getCondiments () {
        List<CoffeeCondiment> condiments = new ArrayList<>();

        condiments.add(getCondiment());

        System.out.println("Would you like add another condiment, (Y)es, (N)o?:");
        String choice = scanner.nextLine();

        while (choice.length() > 0 && choice.toLowerCase().charAt(0) == 'y') {
            condiments.add(getCondiment());
            System.out.println("Would you like add another condiment, (Y)es, (N)o?:");
            choice = scanner.nextLine();
        }

        return condiments;
    }

    private static CoffeeCondiment getCondiment () {
        System.out.println("Please select condiments: ");
        Arrays.stream(CoffeeCondiment.values()).forEach(size -> System.out.println((size.ordinal() + 1) + ": " + size.getDisplayName()));

        return CoffeeCondiment.values()[getValidInt(CoffeeCondiment.values().length) - 1];
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

private static int getValidInt (int maxVal) {
    int option = -1;

    while (option <= 0 || option > maxVal) {
        try {
            option = scanner.nextInt();
            scanner.nextLine();

            if (option <= 0 || option > maxVal) {
                System.out.println("Invalid option please select again (1 - " + maxVal + "): ");
            }
        } catch(InputMismatchException exc) {
            scanner.nextLine();
            System.out.println("Invalid option, please enter a number: ");
        }
    }

    return option;
}

}
