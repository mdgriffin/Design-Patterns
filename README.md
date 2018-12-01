# Design Patterns Assignment

This application allows users to order coffee from the comfort of their desks and then be notified when the coffee is ready for collection from one of the coffee machines.

## Pattern Used

- [x] **Decorator**: Words of wisdom, implemented by `RandomWordsOfWisom` is then decorated by `QuotedWordsOfWisdom` and `MessageOfTheDay`
- [x] **Command**: Registering a callback method when the order client finishing processing the order 
- [x] **Observer**: Used extensively in the state engine, coffee machine and order service.
- [x] **Singleton**: OrderLogger keeps track of orders globally
- [x] **Strategy**: 
        - Coffee bill calculator (normal strategy, discount strategy)
        - Could add a display strategy that changed the way the order is displayed, e.g. as a table
- [x] **Factory Method**: 
        - Order Service cannot be instantiated outside order package, access is though the `SingleMachineOrderService` and `MultiMachineOrderService` classes which implement the OrderFactory interface. In this way it matches the intent **Define an interface for creating an object, but let subclasses decide which class to instantiate.**
        - DiscountFactory implemented by `TimeBasedDiscount` gets the current discount depending on the time of day.
- [x] **Template Method**: Receipt printer prints the details of the order.
- [x] **Facade**: The Order Service is a facade into the coffee machines
- [x] **Builder**: Builder pattern used by the state machine and by CoffeeOrder
- [x] **State**: Coffee making stages, implemented using the state engine