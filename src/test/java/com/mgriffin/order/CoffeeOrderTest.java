package com.mgriffin.order;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CoffeeOrderTest {

    @Test
    public void whenCreatingCoffeeOrder_orderInstantiated () {
        CoffeeOrder coffeeOrder = new CoffeeOrder.CoffeeOrderBuilder()
            .setDiscount((amount) -> { return amount * 1d;})
            .setCustomer(new Customer("John Doe"))
            .setType(CoffeeType.LATTE)
            .setSize(CoffeeSize.LARGE)
            .addCondiment(CoffeeCondiment.CREAM)
            .order();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatingCoffeeOrder_withoutCustomer_exceptionThrown() {
        CoffeeOrder coffeeOrder = new CoffeeOrder.CoffeeOrderBuilder()
            .setDiscount((amount) -> { return amount * 1d;})
            .setType(CoffeeType.LATTE)
            .setSize(CoffeeSize.LARGE)
            .addCondiment(CoffeeCondiment.CREAM)
            .order();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatingCoffeeOrder_withoutType_exceptionThrown() {
        CoffeeOrder coffeeOrder =new CoffeeOrder.CoffeeOrderBuilder()
            .setDiscount((amount) -> { return amount * 1d;})
            .setCustomer(new Customer("John Doe"))
            .setSize(CoffeeSize.LARGE)
            .addCondiment(CoffeeCondiment.CREAM)
            .order();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreatingCoffeeOrder_withoutSize_exceptionThrown() {
        CoffeeOrder coffeeOrder =new CoffeeOrder.CoffeeOrderBuilder()
                .setDiscount((amount) -> { return amount * 1d;})
                .setCustomer(new Customer("John Doe"))
                .setType(CoffeeType.LATTE)
                .addCondiment(CoffeeCondiment.CREAM)
                .order();
    }

    @Test
    public void whenCreatingCoffeeOrder_initialStateIsWaiting () {
        CoffeeOrder coffeeOrder =new CoffeeOrder.CoffeeOrderBuilder()
            .setDiscount((amount) -> { return amount * 1d;})
            .setCustomer(new Customer("John Doe"))
            .setType(CoffeeType.LATTE)
            .setSize(CoffeeSize.LARGE)
            .addCondiment(CoffeeCondiment.CREAM)
            .order();
        assertEquals(OrderStates.WAITING, coffeeOrder.getOrderState());
    }

    @Test
    public void whenCreatingCoffeeOrder_priceCalculatedCorrectly () {
        CoffeeOrder coffeeOrder = new CoffeeOrder.CoffeeOrderBuilder()
                .setDiscount((amount) -> { return amount * 1d;})
                .setCustomer(new Customer("John Doe"))
                .setType(CoffeeType.LATTE)
                .setSize(CoffeeSize.LARGE)
                .addCondiment(CoffeeCondiment.CREAM)
                .order();
        assertEquals(3.7d, coffeeOrder.getPrice());
    }

    @Test
    public void whenCreatingCoffeeOrder_discountApplied () {
        CoffeeOrder coffeeOrder = new CoffeeOrder.CoffeeOrderBuilder()
            .setDiscount((amount) -> { return amount * 0.5d;})
            .setCustomer(new Customer("John Doe"))
            .setType(CoffeeType.LATTE)
            .setSize(CoffeeSize.LARGE)
            .addCondiment(CoffeeCondiment.CREAM)
            .order();

        assertEquals(1.85d, coffeeOrder.getPrice(), 0.01);
    }
}
