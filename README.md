# Bank System RESTful API

This is a Spring Boot application that implements a RESTful API for a bank system. It provides endpoints for managing accounts, performing transactions, and accessing bank-related information.

## Running the Application

To run the application locally, follow these steps:

1. Make sure you have Java JDK 17 or higher installed on your system.

2. Clone this repository to your local machine.

3. Navigate to the root directory of the project in your terminal.

4. Run the following command to build and start the application:


5. Once the application is running, you can access the API endpoints using a tool like Postman or cURL.

## API Endpoints

https://www.postman.com/payload-geoscientist-60983349/workspace/bank-system/collection/19329241-22f4db58-82a5-4966-9bdd-39b92286e00e

## Models

The following models are used in the application:

- Account: Represents a user account in the bank system.
- Transaction: Represents a financial transaction between accounts.
- Bank: Represents the bank entity, containing accounts and transaction-related information.

## Dependencies

- **Spring Boot Starter Web**: For building RESTful APIs.
- **Spring Boot Starter Test**: For testing.
- **Spring Boot Starter Validation**: For validation.
- **Spring Boot Starter Actuator**: For monitoring and managing the application.
- **Spring Boot Starter Data JPA**: For data access.
- **H2 Database (Runtime)**: In-memory database.
- **Hibernate Core and Entity Manager**: ORM and entity management.
- **Spring Data JPA**: Additional JPA support.
- **Javax Persistence API**: Java Persistence API.
- **Lombok**: Simplifies Java development.

## Exception Handling

- **DuplicateEntryException**: Handles cases where duplicate entries are encountered, returning a conflict status (HTTP 409) with a message explaining the issue.
- **DataIntegrityViolationException**: Handles cases where data integrity violations occur, typically due to constraints or database issues. It returns a conflict status (HTTP 409) with a generic message indicating a data integrity violation.
- **Generic Exception**: Catches any other unexpected exceptions that may occur. It returns an internal server error status (HTTP 500) with a generic error message.

## Database Information

The application uses the H2 in-memory database for development and testing purposes.

### Configuration

For development and testing, the application is configured to use the H2 database with the following properties:

- **URL**: `jdbc:h2:mem:testdb`
- **Driver Class Name**: `org.h2.Driver`
- **Username**: `sa`
- **Password**: `password`
- **Dialect**: `org.hibernate.dialect.H2Dialect`

### Accessing H2 Console

During development, you can access the H2 console to interact with the database. The console is enabled and can be accessed at `/h2-console`. Please note that access is restricted to local connections only.

### Profile

The application is set to use the `h2` profile for H2 database configuration.



## Author

Hamza Jashari
