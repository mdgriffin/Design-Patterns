# Design Patterns Project

This application allows users to order coffee from the comfort of their desks
and then be notified when the coffee is ready for collection from of the coffee machines.

## Patterns

- State: Coffee making stages
- Decorator
- Observer
- Singleton: Single Coffee Ordering endpoint
- Strategy 
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
