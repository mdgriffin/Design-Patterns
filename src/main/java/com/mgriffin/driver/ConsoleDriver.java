package com.mgriffin.driver;

import com.mgriffin.coffemat.*;

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

        orderService.addOrder(coffeeOrderBuilder.order());

        System.out.println(orderService.toString());
    }

    private static CoffeeType getCoffeeType () {
        System.out.println("Please select Coffee Type from the following options: ");
        Arrays.stream(CoffeeType.values()).forEach(type -> System.out.println((type.ordinal() + 1) + ": " + type.getDisplayName()));
        int option = -1;

        while (option <= 0 || option > CoffeeType.values().length) {
            try {
                option = scanner.nextInt();

                if (option <= 0 || option > CoffeeType.values().length) {
                    System.out.println("Invalid option please select again: ");
                }
            } catch(InputMismatchException exc) {
                scanner.nextLine();
                System.out.println("Invalid option, please enter a number: ");
            }
        }

        return CoffeeType.values()[option - 1];
    }

    private static CoffeeSize getSize () {
        System.out.println("Please select the size of the coffee: ");
        Arrays.stream(CoffeeSize.values()).forEach(size -> System.out.println((size.ordinal() + 1) + ": " + size.getDisplayName()));
        int option = -1;

        while (option <= 0 || option > CoffeeSize.values().length) {
            try {
                option = scanner.nextInt();

                if (option <= 0 || option > CoffeeSize.values().length) {
                    System.out.println("Invalid option please select again: ");
                }
            } catch(InputMismatchException exc) {
                scanner.nextLine();
                System.out.println("Invalid option, please enter a number: ");
            }
        }

        return CoffeeSize.values()[option - 1];
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
        int option = -1;
        System.out.println("Please select condiments: ");
        Arrays.stream(CoffeeCondiment.values()).forEach(size -> System.out.println((size.ordinal() + 1) + ": " + size.getDisplayName()));

        while (option <= 0 || option > CoffeeCondiment.values().length) {
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                if (option <= 0 || option > CoffeeCondiment.values().length) {
                    System.out.println("Invalid option please select again: ");
                }
            } catch(InputMismatchException exc) {
                scanner.nextLine();
                System.out.println("Invalid option, please enter a number: ");
            }
        }

        return CoffeeCondiment.values()[option - 1];
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
