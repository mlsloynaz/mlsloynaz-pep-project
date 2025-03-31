# PROJECT NAME 
Social Media Blog

## Project Description

Build Restfull Apis to manage the Social Media Blog messages and user accounts.

## Technologies Used

* Java - version 11
* Javalin - version 5.0.1
* JDBC - version 3.0
* JUnit - version 4.13.2

## Features

* User Account Management

User registration functionality
User authentication/login system


* Message Operations

Create new messages
Update existing messages by ID
Delete messages by ID
Retrieve all messages in the system


* Message Retrieval

Get a specific message by its ID
Get all messages from a specific user account


* Error Handling

Appropriate HTTP status codes for various scenarios
Input validation for message operations
Exception handling for JSON processing and other operations


* RESTful API Design

RESTful endpoint structure following resource-based URL patterns
Proper HTTP methods (POST, GET, PATCH, DELETE) for CRUD operations
JSON request/response formatting for data exchange

To-do list:
* Add unit tests for the DAO Layer. 
* Add swagger for API documentation

## Getting Started

# Getting Started

## Prerequisites
- Java 11 or higher
- Maven
- Git

## Clone the Repository
```bash
git clone https://github.com/mlsloynaz/mlsloynaz-pep-project.git
cd mlsloynaz-pep-project
```

## Setup Database (H2)
This project uses H2 in-memory database which is automatically configured when you run the application.

## Build the Project

**Windows:**
```cmd
mvn clean package
```

**Unix/Linux/macOS:**
```bash
./mvnw clean package
```

## Run the Application

**Windows:**
```cmd
java -jar target/Challenges-1.1.jar
```

**Unix/Linux/macOS:**
```bash
java -jar target/Challenges-1.1.jar
```

## API Testing
Once the application is running, you can test the API endpoints using tools like Postman or cURL.

The API will be available at:
```
http://localhost:8080
```

Example request to get all messages:

**Windows (PowerShell):**
```powershell
Invoke-RestMethod -Uri http://localhost:8080/messages -Method Get
```

**Unix/Linux/macOS:**
```bash
curl -X GET http://localhost:8080/messages
```

## Expected Output

When the application starts successfully, you should see output similar to:
```
[main] INFO io.javalin.Javalin - Javalin started in 264ms \o/
[main] INFO io.javalin.Javalin - Listening on http://localhost:8080
```

If you're using the H2 console (which may be enabled in the application), you can access it at:
```
http://localhost:8080/h2-console
```

JDBC URL: `jdbc:h2:mem:test`  
Username: `sa`  
Password: (leave empty)

## Usage

> Here, you instruct other people on how to use your project after they’ve installed it. This would also be a good place to include screenshots of your project in action.


## License

This project uses the following license: [<license_name>](<link>).

