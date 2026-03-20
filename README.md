# Product Evaluation System

A strictly structured, 4-layer architectural Java application for managing users, products, and product evaluations. Designed according to best practices and standard object-oriented programming (OOP) principles.

## Architecture Highlights
- **Model Layer**: Contains core entities representing the domain data (`BaseEntity`, `User`, `Product`, `Review`). Entities demonstrate inheritance by extending `BaseEntity` and encapsulation via private variables with getters and setters.
- **Repository Layer**: Defines standard data-access contracts (`IRepository`, `UserRepository`, etc.) and achieves polymorphism by mapping these to actual in-memory implementations (`InMemory...Repository`). Adapting the app to persist to a real database merely involves passing a SQL implementation of the interface repository into the services.
- **Service Layer**: Manages distinct core logic. Notably, `EvaluationService` is strictly responsible for guaranteeing that a user can evaluate a specific product only once.
- **Interface / View Layer**: Hosts the main entry point (`MainApp`) assembling all dependency injection visually, along with `ConsoleInterface` which manages a command line user interface.
- **Exception Layer**: Custom exceptions explicitly flag and communicate runtime logic errors (`DuplicateReviewException`, `UserNotFoundException`, `ProductNotFoundException`).

## Requirements
- Java Development Kit (JDK) 8 or higher.

## How to Compile and Run
1. Navigate to the `src` directory of the project in your terminal:
   ```shell
   cd src
   ```

2. Compile all Java application files safely.
   ```shell
   javac com/system/model/*.java com/system/exception/*.java com/system/repository/*.java com/system/service/*.java com/system/view/*.java
   ```

3. Launch the application `MainApp` class.
   ```shell
   java com.system.view.MainApp
   ```

## Usage
When the application first boots, some dummy initial data is automatically injected into the in-memory repositories (Users: Alice, Bob | Products: Laptop, Smartphone).

Follow the on-screen CLI commands to interact fully with the system! Try reviewing the same product with the same user ID twice to witness the custom exception and business-logic enforcement in action.

## Additional Files
- `schema.sql`: Provided for mapping over to an eventual relational persistence strategy. Includes tables and explicit `UNIQUE` constraint checks mapped to the Java logic.
