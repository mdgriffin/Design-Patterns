# Design Patterns Project

This application allows users to order coffee from the comfort of their desks
and then be notified when the coffee is ready for collection from one of the coffee machines.

## Patterns

- Tier 1 (14 marks each) - implement 2
    - [ ] **Abstract Factory**
    - [ ] **Decorator**
        - Create a message of the day class that welcomes the user to the system and displays a welcome message
        - The greeter will have to two decorator, stranger message and user message that could decorate the message with either the user's name or "guest"
    - [x] **Command**: Registering a callback method when the order client finishing processing the order 
    - [ ] **Iterator**:
    - [ ] **Composite**:
- Tier 2 (12 marks each) - implement 6
    - [x] **State**: Coffee making stages, implemented using the state engine
    - [x] **Observer**: Used extensively in the state engine, coffee machine and order service.
    - [ ] **Singleton**: Get single order service, so that all clients will be communicating with the one service
    - [x] **Strategy**: Coffee bill calculator (normal strategy, discount strategy)
    - [x] **Factory Method**: Order Service cannot be instantiated outside order package, access is though the 
    `SingleMachineOrderService` and `MultiMachineOrderService` classes which implement the OrderFactory interface.
    In this way it matches the intent **Define an interface for creating an object, but let subclasses decide which class to instantiate.**
    - [ ] **Adapter**
    - [ ] **Template Method** 
    - [ ] **Proxy**

## ToDo

- Customers need to order their coffee in a variety of ways like mobile application, web, in person
    - Allow for these various scenarios
    - Allow for multiple simultaneous connection to the coffee service
- Implement sockets to allow multiple clients to connect to the application
- Occasional discount are applied to the purchase price, for instance there is a 20% discount run during August
    - The system should handle various discounts to be applied
    - Discounts can be applied cumulatively
- Customers have credits that are used after each order
- The coffee machine have supplies of milk and various other condiments, each order will depleted these supplies
- Change state pattern implementation to avoid using the simple state engine
    - The coffee making state to provide messages, like adding milk, sugar etc