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

Consume Apis from a client app or test your apis with postman. 
Here the list of valid url and payload
# API Documentation

## Summary of Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Register a new user account |
| POST | `/login` | Authenticate an existing user |
| POST | `/messages` | Create a new message |
| PATCH | `/messages/{message_id}` | Update an existing message |
| DELETE | `/messages/{message_id}` | Remove a message |
| GET | `/messages` | Retrieve all messages |
| GET | `/messages/{message_id}` | Retrieve a specific message |
| GET | `/accounts/{account_id}/messages` | Retrieve all messages from a specific user |

## Detailed Documentation

### Account Endpoints

<details>
  <summary><code>POST</code> <code>/register</code> - Register a new account</summary>

#### Request Body
```json
{
  "username": "string",
  "password": "string"
}
```

#### Responses
- `200 OK` - Account created successfully
- `400 Bad Request` - Username taken or invalid input

#### Response Example (200 OK)
```json
{
  "account_id": 1,
  "username": "johndoe",
  "password": "password123"
}
```
</details>

<details>
  <summary><code>POST</code> <code>/login</code> - Login to existing account</summary>

#### Request Body
```json
{
  "username": "string",
  "password": "string"
}
```

#### Responses
- `200 OK` - Login successful
- `401 Unauthorized` - Invalid credentials

#### Response Example (200 OK)
```json
{
  "account_id": 1,
  "username": "johndoe",
  "password": "password123"
}
```
</details>

### Message Endpoints

<details>
  <summary><code>POST</code> <code>/messages</code> - Create a new message</summary>

#### Request Body
```json
{
  "posted_by": 1,
  "message_text": "Hello world!",
  "time_posted_epoch": 1609459200000
}
```

#### Responses
- `200 OK` - Message created successfully
- `400 Bad Request` - Invalid input or constraints violated

#### Response Example (200 OK)
```json
{
  "message_id": 1,
  "posted_by": 1,
  "message_text": "Hello world!",
  "time_posted_epoch": 1609459200000
}
```
</details>

<details>
  <summary><code>PATCH</code> <code>/messages/{message_id}</code> - Update a message</summary>

#### Path Parameters
- `message_id` - ID of the message to update

#### Request Body
```json
{
  "message_text": "Updated message text"
}
```

#### Responses
- `200 OK` - Message updated successfully
- `400 Bad Request` - Message not found or invalid input

#### Response Example (200 OK)
```json
{
  "message_id": 1,
  "posted_by": 1,
  "message_text": "Updated message text",
  "time_posted_epoch": 1609459200000
}
```
</details>

<details>
  <summary><code>DELETE</code> <code>/messages/{message_id}</code> - Delete a message</summary>

#### Path Parameters
- `message_id` - ID of the message to delete

#### Responses
- `200 OK` - Message deleted successfully
- `400 Bad Request` - Message not found

#### Response Example (200 OK)
```json
{
  "message_id": 1,
  "posted_by": 1,
  "message_text": "Hello world!",
  "time_posted_epoch": 1609459200000
}
```
</details>

<details>
  <summary><code>GET</code> <code>/messages</code> - Get all messages</summary>

#### Responses
- `200 OK` - Returns all messages

#### Response Example (200 OK)
```json
[
  {
    "message_id": 1,
    "posted_by": 1,
    "message_text": "Hello world!",
    "time_posted_epoch": 1609459200000
  },
  {
    "message_id": 2,
    "posted_by": 2,
    "message_text": "Another message",
    "time_posted_epoch": 1609459300000
  }
]
```
</details>

<details>
  <summary><code>GET</code> <code>/messages/{message_id}</code> - Get a specific message</summary>

#### Path Parameters
- `message_id` - ID of the message to retrieve

#### Responses
- `200 OK` - Returns the requested message
- Empty response if message not found

#### Response Example (200 OK)
```json
{
  "message_id": 1,
  "posted_by": 1,
  "message_text": "Hello world!",
  "time_posted_epoch": 1609459200000
}
```
</details>

<details>
  <summary><code>GET</code> <code>/accounts/{account_id}/messages</code> - Get all messages by a user</summary>

#### Path Parameters
- `account_id` - ID of the user account

#### Responses
- `200 OK` - Returns all messages by the specified user

#### Response Example (200 OK)
```json
[
  {
    "message_id": 1,
    "posted_by": 1,
    "message_text": "Hello world!",
    "time_posted_epoch": 1609459200000
  },
  {
    "message_id": 3,
    "posted_by": 1,
    "message_text": "My second message",
    "time_posted_epoch": 1609459400000
  }
]
```
</details>


## License

This project uses the following license: MIT License.

