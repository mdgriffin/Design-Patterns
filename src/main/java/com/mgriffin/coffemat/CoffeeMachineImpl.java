package com.mgriffin.coffemat;

public class CoffeeMachineImpl implements CoffeeMachine {

    OrderServiceImpl orderService;

    CoffeeOrder coffeeOrder;

    public CoffeeMachineImpl(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    public boolean available () {
        return coffeeOrder == null || coffeeOrder.getOrderState() == OrderStates.COMPLETED;
    }

    public void start (CoffeeOrder coffeeOrder) {
        if (available()) {
            this.coffeeOrder = coffeeOrder;
        }
    }

    private void completed (CoffeeOrder coffeeOrder) {
        this.orderService.orderCompleted(this, coffeeOrder);
    }

}