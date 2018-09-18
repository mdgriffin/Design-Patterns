package com.mgriffin.coffeemat;

import com.mgriffin.coffemat.*;
import org.junit.Test;

public class CoffeeMakerTest {

    @Test
    public void whenInstantiatedCoffeeMaker_instanceInstantiated () {
        CoffeeMaker coffeeMaker = new CoffeeMaker();
    }

    @Test
    public void whenAddingOrder_noExceptionThrown () {
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.addOrder(new CoffeeOrder.CoffeeOrderBuilder()
            .setCustomer(new Customer("John Doe"))
            .setType(CoffeeType.LATTE)
            .setSize(CoffeeSize.LARGE)
            .addCondiment(CoffeeCondiment.CREAM)
            .order()
        );
    }
}
