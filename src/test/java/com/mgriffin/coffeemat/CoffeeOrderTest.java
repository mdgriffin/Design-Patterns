package com.mgriffin.coffeemat;

import com.mgriffin.coffemat.CoffeeOrder;
import com.mgriffin.coffemat.Customer;
import com.mgriffin.coffemat.OrderStates;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CoffeeOrderTest {

    @Test
    public void whenCreatingCoffeeOrder_orderInstantiated () {
        CoffeeOrder coffeeOrder = new CoffeeOrder(new Customer("John Doe"));
    }

    @Test
    public void whenCreatingCoffeeOrder_initialStateIsWaiting () {
        CoffeeOrder coffeeOrder = new CoffeeOrder(new Customer("John Doe"));
        assertEquals(OrderStates.WAITING, coffeeOrder.getOrderState());
    }
}
