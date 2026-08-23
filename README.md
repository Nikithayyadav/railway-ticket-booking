# Railway Ticket Booking System                    

A backend REST API-based Railway Ticket Booking System developed using Java and Spring Boot. The system manages passengers, trains, ticket bookings, cancellations, seat availability, exception handling, and paginated train search.  
  
## Problem Statement   

The Railway Ticket Booking System is designed to provide a backend solution for managing railway passengers, trains, ticket bookings, cancellations, and seat availability through REST APIs.

The system allows passengers to register, search trains based on source and destination, book tickets based on seat availability, and cancel tickets. It also provides centralized exception handling and pagination for efficient API responses.

## Objectives

- Manage passenger registration and details.
- Manage train information.
- Search trains based on source and destination.
- Book tickets based on seat availability.
- Automatically allocate seats during booking.
- Cancel confirmed tickets.
- Restore seat availability after cancellation.
- Provide centralized exception handling.
- Implement pagination for train search.
- Store and manage data using MySQL.

## Features

### Passenger Management
- Passenger registration
- Passenger details management
- Unique passenger ID generation

### Train Management
- Train registration
- Train search by source and destination
- Total and available seat management
- Train active/inactive status

### Ticket Booking
- Book tickets using passenger ID and train number
- Validate passenger and train
- Check seat availability
- Allocate seats
- Generate unique ticket number
- Update available seats

### Ticket Cancellation
- Cancel ticket using ticket number
- Validate ticket number
- Prevent cancellation of already cancelled tickets
- Restore available seat count

### Exception Handling
Centralized exception handling is implemented using custom exceptions and a global exception handler.

Handled scenarios include:

- Invalid passenger details
- Train not found
- Seat not available
- Booking closed
- Invalid ticket number
- Ticket already cancelled

### Pagination 

Train search supports pagination using Spring Data JPA.

Example:

GET /api/trains/search?source=Nampally&destination=Chennai&page=0&size=10

Pagination information is returned through the `meta` section of the common API response.

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Swagger / OpenAPI
- Lombok
- Maven
- Git & GitHub

## Project Architecture

The project follows a layered architecture:

Controller → Service → Repository → Database

### Controller Layer

Handles HTTP requests and responses.

### Service Layer

Contains the business logic such as:

- Seat availability validation
- Seat allocation
- Ticket generation
- Ticket cancellation
- Train search

### Repository Layer

Uses Spring Data JPA to communicate with the database.

### Model Layer

Contains JPA entities representing database tables.

### DTO Layer

Used to transfer request and response data between the client and application.

### Response Layer

Provides a common API response structure.

### Exception Layer

Contains custom exceptions and centralized exception handling.

## Project Structure

src/main/java/com/railway/booking

├── controller
├── service
├── repository
├── model
├── dto
├── response
└── exception

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/passengers` | Register passenger |
| POST | `/api/trains` | Register train |
| GET | `/api/trains/search` | Search trains |
| POST | `/api/bookings` | Book ticket |
| PUT | `/api/bookings/{ticketNumber}/cancel` | Cancel ticket |

## Pagination

Train search supports pagination.

Example:

GET /api/trains/search?source=Nampally&destination=Chennai&page=0&size=10

Where:

- `page` represents the page number.
- `size` represents the number of records per page.

Pagination metadata is returned in the `meta` field.

## API Response Format

The application follows a common response structure:

{
  "success": true,
  "data": {},
  "error": null,
  "meta": {}
}

### Success Response

- `success` indicates whether the operation was successful.
- `data` contains the response data.
- `error` contains error details when an operation fails.
- `meta` contains additional information such as pagination details.

## Database

The application uses MySQL as the database.

Main tables include:

- `passengers`
- `trains`
- `bookings`

Spring Data JPA and Hibernate are used for database interaction and ORM.

## Exception Handling Flow

Business Exception

↓

Custom Exception

↓

GlobalExceptionHandler

↓

ErrorResponse

↓

ApiResponse

↓

HTTP Response

## How to Run

### Prerequisites

- Java 17 or higher
- Maven
- MySQL
- Git

### Database Setup

Create the database:

CREATE DATABASE railway_booking_db;

Configure the database connection in:

src/main/resources/application.properties

Example:

spring.datasource.url=jdbc:mysql://localhost:3306/railway_booking_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update

### Run the Application

Using Maven:

./mvnw spring-boot:run

On Windows:

mvnw.cmd spring-boot:run

The application runs on:

http://localhost:8080

## Swagger

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html

Swagger is used to test and demonstrate the REST APIs.

## Testing

The APIs have been tested using Swagger UI and MySQL.

The booking flow verifies:

1. Passenger validation
2. Train validation
3. Seat availability
4. Seat allocation
5. Ticket generation
6. Database update

The cancellation flow verifies:

1. Ticket validation
2. Cancellation status
3. Seat restoration
4. Database update

## Future Enhancements

- View ticket
- Booking history
- View passenger details
- Train status such as Started, Running, and Completed
- Waiting list management
- Print/download ticket
- Authentication and authorization
- Admin-specific operations
