# Task

# Payment API with JWT Authentication and Airtime Payment

This project is a sample implementation of a payment API with JWT authentication and airtime payment functionality.

## Getting Started

Follow these instructions to set up and run the project locally.

### Prerequisites

- Java JDK 8 or higher
- Maven or Gradle

### Installation

1. Clone the repository.
2. Open the project in your preferred IDE.

### Configuration

1. Configure the application properties (`src/main/resources/application.properties`) to connect to your database.

### Running the Application

1. Build and run the project using Maven or Gradle.
2. The application will start on `http://localhost:8080`.

## API Endpoints

### Authentication

#### POST /authenticate

Authenticate a user and receive a JWT token.

Example Request Body:
```json
{
  "username": "your_username",
  "password": "your_password"
}

Airtime Payment
POST /airtime-payment
Make an airtime payment using the authenticated user's JWT token.

Example Request Body:

json
Copy code
{
  "phoneNumber": "08123456789",
  "amount": 500
}
Testing
Run unit tests to ensure the functionality of the API.

Open a terminal window.
Navigate to the project root.
Run mvn test or gradle test.
Contributing
Contributions are welcome! Please open a new issue or submit a pull request for improvements.
