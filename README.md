# Design Patterns Project

This application allows users to order coffee from the comfort of their desks
and then be notified when the coffee is ready for collection from one of the coffee machines.

## Patterns

- State: Coffee making stages
- Decorator
    - Create a message of the day class that welcomes the user to the system and displays a welcome message
    - The greeter will have to two decorator, stranger message and user message that could decorate the message with either the user's name or "guest" 
- Observer
- Singleton: Single Coffee Ordering endpoint
- Strategy: Coffee bill calculator (normal strategy, discount strategy)
- Factory Method 
- Command 
- Adapter 
- Template Method 
- Composite 


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