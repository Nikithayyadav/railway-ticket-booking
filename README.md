# 🚆 Railway Ticket Booking System

A robust backend application for managing railway ticket bookings, built with **Java 17, Spring Boot, Spring Data JPA, and MySQL**. The system implements real-world booking workflows with seat management, train status validation, waiting-list handling, ticket management, pagination, validation, and centralized exception handling.

## ✨ Key Features

- 🚆 **Train Management** — Register and search trains by source and destination.
- 👤 **Passenger Management** — Register and manage passenger details with validation.
- 🎫 **Ticket Booking** — Book tickets with automatic seat allocation.
- 🪑 **Seat Management** — Tracks total and available seats dynamically.
- ⏱️ **Train Status Control** — Prevents booking when a train is inactive or has already started.
- 🕐 **IST Timestamps** — Stores passenger registration and booking date/time using Indian Standard Time.
- ⏳ **Waiting List** — Automatically places passengers on the waiting list when seats are unavailable.
- 🔄 **Automatic Seat Promotion** — Promotes the earliest waiting passenger when a confirmed ticket is cancelled.
- ❌ **Ticket Cancellation** — Cancels tickets and releases seats.
- 🔎 **Ticket Search** — View complete ticket details using the ticket number.
- 📜 **Booking History** — View passenger booking history with pagination.
- 📄 **Pagination** — Implemented for train search and booking history.
- 🛡️ **Exception Handling** — Custom exceptions with centralized global error handling.
- 📚 **Swagger/OpenAPI** — Interactive API documentation and testing.

## 🏗️ Architecture

```text
Client / Swagger
       ↓
REST Controllers
       ↓
Service Layer
       ↓
Repository Layer
       ↓
MySQL Database
The application follows a clean layered architecture with separate Controller, Service, Repository, Model, DTO, Response, and Exception layers.
🛠️ Tech Stack
Technology	Purpose
Java 17	Core programming
Spring Boot	Backend framework
Spring Web	REST APIs
Spring Data JPA	Data access
Hibernate	ORM
MySQL	Database
Lombok	Boilerplate reduction
Jakarta Validation	Request validation
Swagger / OpenAPI	API documentation
Maven	Build & dependency management
Git & GitHub	Version control


🔗 Core APIs
Method	Endpoint	Purpose
POST	/api/bookings	Book a ticket / join waiting list
GET	/api/bookings/{ticketNumber}	View ticket
PUT	/api/bookings/{ticketNumber}/cancel	Cancel ticket
GET	/api/bookings/history/{passengerId}	Booking history with pagination
POST	/api/trains	Register train
GET	/api/trains/search	Search trains with pagination


Additional passenger APIs are available through Swagger.
🔄 Booking Workflow
Search Train
     ↓
Select Passenger
     ↓
Check Train Status
     ↓
Check Seat Availability
     ↓
 ┌───────────────┐
 │               │
Seats Available  Full
 │               │
 ↓               ↓
CONFIRMED      WAITING
 │               │
 ↓               │
Seat Assigned    │
                 │
      Cancellation
           ↓
     Seat Released
           ↓
 Waiting Passenger
           ↓
       CONFIRMED
⚙️ Business Rules
- Booking is allowed only when the train is active.
- Booking is not allowed once the train has started.
- Available seats are reduced after successful confirmation.
- When seats reach zero, new passengers are added to the waiting list.
- Cancelling a confirmed ticket releases the seat.
- The earliest waiting passenger is automatically promoted when a seat becomes available.
- A cancelled ticket cannot be cancelled again.
🛡️ Exception Handling
The system includes custom exceptions such as:
- BookingClosedException
- InvalidPassengerDetailsException
- InvalidTicketNumberException
- SeatNotAvailableException
- TicketAlreadyCancelledException
- TrainNotFoundException
All exceptions are handled through a centralized GlobalExceptionHandler to provide consistent API responses.
📄 Pagination
Pagination is supported for:
- Train search
- Passenger booking history
Example:
GET /api/trains/search?source=Nampally&destination=Chennai&page=0&size=10
🚀 Getting Started
Prerequisites
- Java 17+
- MySQL
- Maven
- IntelliJ IDEA / any Java IDE
Database
Create the database:
CREATE DATABASE railway_booking_db;
Configure your MySQL credentials in:
src/main/resources/application.properties
Run the Application
mvn spring-boot:run
Application:
http://localhost:8080
Swagger UI:
http://localhost:8080/swagger-ui/index.html
📁 Project Structure
src/main/java/com/railway/booking
│
├── controller
├── dto
├── exception
├── model
├── repository
├── response
└── service
🎯 Project Highlights
This project focuses on implementing practical backend concepts including:
REST API Design • Layered Architecture • Spring Boot • JPA/Hibernate • MySQL • Business Logic • Pagination • Validation • Exception Handling • Seat Allocation • Waiting Lists • Git/GitHub • Swagger
👨‍💻 Author
Chandavena Nikitha
B.Tech – Computer Science & Engineering (Artificial Intelligence)
