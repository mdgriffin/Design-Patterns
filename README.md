# Design Patterns Project

This application allows users to order coffee from the comfort of their desks
and then be notified when the coffee is ready for collection from one of the coffee machines.

## Patterns

- Tier 1 (14 marks each) - implement 2
    - [ ] **Abstract Factory**
    - [x] **Decorator**
        - Create a message of the day class that welcomes the user to the system and displays a welcome message
        - The greeter will have to two decorator, stranger message and user message that could decorate the message with either the user's name or "guest"
    - [x] **Command**: Registering a callback method when the order client finishing processing the order 
- Tier 2 (12 marks each) - implement 6
    - [x] **State**: Coffee making stages, implemented using the state engine
    - [x] **Observer**: Used extensively in the state engine, coffee machine and order service.
    - [ ] **Singleton**: OrderLogger keeps track of orders globally
    - [x] **Strategy**: 
        - Coffee bill calculator (normal strategy, discount strategy)
        - Could add a display strategy that changed the way the order is displayed, e.g. as a table
    - [x] **Factory Method**: 
        - Order Service cannot be instantiated outside order package, access is though the `SingleMachineOrderService` and `MultiMachineOrderService` classes which implement the OrderFactory interface. In this way it matches the intent **Define an interface for creating an object, but let subclasses decide which class to instantiate.**
        - DiscountFactory implemented by `TimeBasedDiscount` gets the current discount depending on the time of day.
    - [x] **Adapter**: The adapting of the events from coffee machine to order service and then on to the clients
    - [ ] **Template Method**
    - [ ] **Proxy**
- Other Patterns
    - [x] **Facade**: The Order Service is a facade into the coffee machines
    - [x] **Builder**: Builder pattern used by the state machine and by CoffeeOrder

## ToDo

- [x] Implement sockets to allow multiple clients to connect to the application
- Occasional discount are applied to the purchase price, for instance there is a 20% discount run during August
    - [x] The system should handle various discounts to be applied
- Customers have credits that are used after each order
- [x] Display price of order when ordering coffee through service
- [x] Display Message of the day