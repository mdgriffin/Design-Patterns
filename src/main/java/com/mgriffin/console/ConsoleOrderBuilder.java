package com.mgriffin.console;

import com.mgriffin.order.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ConsoleOrderBuilder {

    private BufferedReader in;
    private PrintWriter out;

    public ConsoleOrderBuilder (BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public CoffeeOrder getCoffeeOrder () throws IOException {
        CoffeeOrder.CoffeeOrderBuilder coffeeOrderBuilder = new CoffeeOrder.CoffeeOrderBuilder();

        coffeeOrderBuilder.setCustomer(getCustomer());
        coffeeOrderBuilder.setType(getCoffeeType());
        coffeeOrderBuilder.setSize(getSize());
        getCondiments().stream().forEach(coffeeCondiment -> coffeeOrderBuilder.addCondiment(coffeeCondiment));

        return coffeeOrderBuilder.order();
    }

    private CoffeeType getCoffeeType () {
        out.println("Please select Coffee Type from the following options: ");
        Arrays.stream(CoffeeType.values()).forEach(type -> out.println((type.ordinal() + 1) + ": " + type.getDisplayName()));

        return CoffeeType.values()[getValidInt(CoffeeType.values().length) - 1];
    }

    private CoffeeSize getSize () {
        out.println("Please select the size of the coffee: ");
        Arrays.stream(CoffeeSize.values()).forEach(size -> out.println((size.ordinal() + 1) + ": " + size.getDisplayName()));

        return CoffeeSize.values()[getValidInt(CoffeeSize.values().length) - 1];
    }

    private List<CoffeeCondiment> getCondiments () throws IOException {
        List<CoffeeCondiment> condiments = new ArrayList<>();

        condiments.add(getCondiment());

        out.println("Would you like add another condiment, (Y)es, (N)o?:");
        String choice = in.readLine();

        while (choice.length() > 0 && choice.toLowerCase().charAt(0) == 'y') {
            condiments.add(getCondiment());
            out.println("Would you like add another condiment, (Y)es, (N)o?:");
            choice = in.readLine();
        }

        return condiments;
    }

    private CoffeeCondiment getCondiment () {
        out.println("Please select condiments: ");
        Arrays.stream(CoffeeCondiment.values()).forEach(size -> out.println((size.ordinal() + 1) + ": " + size.getDisplayName()));

        return CoffeeCondiment.values()[getValidInt(CoffeeCondiment.values().length) - 1];
    }

    private Customer getCustomer () throws IOException {
        String customerName = "";

        out.println("Please enter customer name: ");

        customerName = in.readLine();

        while (customerName.length() == 0) {
            out.println("Please enter a valid customer name: ");
            customerName = in.readLine();
        }

        return new Customer(customerName);
    }

    private int getValidInt (int maxVal) {
        String strOption = "";
        int option = -1;

        while (option <= 0 || option > maxVal) {
            try {
                strOption = in.readLine();

                try {
                    option = Integer.parseInt(strOption);
                } catch (NumberFormatException exc) {
                    out.println("Invalid option, please enter a number: ");
                }

                if (option <= 0 || option > maxVal) {
                    out.println("Invalid option please select again (1 - " + maxVal + "): ");
                }
            } catch (IOException exc) {
                out.println(exc);
            }
        }

        return option;
    }
}
