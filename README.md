# 🚆 Railway Ticket Booking System   
  
A robust backend application for managing railway ticket bookings, built with **Java 17, Spring Boot, Spring Data JPA, and MySQL**. The system implements real-world booking workflows including seat management, train status validation, waiting-list handling, ticket management, pagination, validation, and centralized exception handling. 

---

## ✨ Key Features

- 🚆 **Train Management** — Register and search trains by source and destination. 
- 👤 **Passenger Management** — Register and manage passenger details with validation.
- 🎫 **Ticket Booking** — Book tickets with automatic seat allocation.
- 🪑 **Seat Management** — Tracks total and available seats dynamically.
- ⏱️ **Train Status Control** — Prevents booking when a train is inactive or has already started.
- 🕐 **IST Timestamps** — Stores passenger registration and booking date/time using Indian Standard Time.
- ⏳ **Waiting List** — Adds passengers to the waiting list when seats are unavailable.
- 🔄 **Automatic Seat Promotion** — Promotes the earliest waiting passenger when a confirmed ticket is cancelled.
- ❌ **Ticket Cancellation** — Cancels tickets and releases seats.
- 🔎 **Ticket Search** — View complete ticket details using the ticket number.
- 📜 **Booking History** — View passenger booking history with pagination.
- 📄 **Pagination** — Implemented for train search and booking history.
- 🛡️ **Exception Handling** — Custom exceptions with centralized global error handling.
- 📚 **Swagger/OpenAPI** — Interactive API documentation and testing.

---

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
```

The application follows a clean layered architecture with separate:

**Controller • Service • Repository • Model • DTO • Response • Exception**

layers.

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Core programming |
| Spring Boot | Backend framework |
| Spring Web | REST APIs |
| Spring Data JPA | Data access |
| Hibernate | ORM |
| MySQL | Database |
| Lombok | Boilerplate reduction |
| Jakarta Validation | Request validation |
| Swagger / OpenAPI | API documentation |
| Maven | Build & dependency management |
| Git & GitHub | Version control |

---

## 🔗 Core APIs

| Method | Endpoint | Purpose |
|--------|----------|---------|
| `POST` | `/api/bookings` | Book a ticket / join waiting list |
| `GET` | `/api/bookings/{ticketNumber}` | View ticket |
| `PUT` | `/api/bookings/{ticketNumber}/cancel` | Cancel ticket |
| `GET` | `/api/bookings/history/{passengerId}` | Booking history with pagination |
| `POST` | `/api/trains` | Register train |
| `GET` | `/api/trains/search` | Search trains with pagination |

Additional passenger APIs are available through Swagger.

---

## 🔄 Booking Workflow

```text
Search Train
     ↓
Select Passenger
     ↓
Check Train Status
     ↓
Check Seat Availability
     ↓
┌─────────────────┐
│                 │
Seats Available   Full
│                 │
↓                 ↓
CONFIRMED       WAITING
│                 │
↓                 │
Seat Assigned     │
                  │
           Ticket Cancellation
                  ↓
           Seat Released
                  ↓
         Waiting Passenger
                  ↓
             CONFIRMED
```

---

## ⚙️ Business Rules

- Booking is allowed only when the train is **active**.
- Booking is not allowed once the train has **started**.
- Available seats are reduced after successful confirmation.
- When available seats reach zero, new passengers are added to the **waiting list**.
- Cancelling a confirmed ticket releases the seat.
- The earliest waiting passenger is automatically promoted when a seat becomes available.
- A cancelled ticket cannot be cancelled again.
- Ticket status is maintained using `CONFIRMED`, `CANCELLED`, and `WAITING`.

---

## 🛡️ Exception Handling

The system includes custom exceptions such as:

- `BookingClosedException`
- `InvalidPassengerDetailsException`
- `InvalidTicketNumberException`
- `SeatNotAvailableException`
- `TicketAlreadyCancelledException`
- `TrainNotFoundException`

All exceptions are handled through a centralized `GlobalExceptionHandler` to provide consistent API responses.

---

## 📄 Pagination

Pagination is supported for:

- Train search
- Passenger booking history

Example:

```http
GET /api/trains/search?source=Nampally&destination=Chennai&page=0&size=10
```

Pagination metadata includes information such as:

- Current page
- Page size
- Total elements
- Total pages
- First page
- Last page

---

## 🕐 Date & Time

Passenger registration and ticket booking timestamps are maintained using:

```text
Asia/Kolkata
```

This ensures that application-generated timestamps are stored according to **Indian Standard Time (IST)**.

---

## 🚀 Getting Started

### Prerequisites

Make sure the following are installed:

- Java 17+
- MySQL
- Maven
- IntelliJ IDEA or any Java IDE
- Git

### 1. Clone the Repository

```bash
git clone https://github.com/Nikithayyadav/railway-ticket-booking.git
```

Navigate to the project:

```bash
cd railway-ticket-booking
```

### 2. Create the Database

Create the MySQL database:

```sql
CREATE DATABASE railway_booking_db;
```

### 3. Configure Database

Update the following file with your local MySQL credentials:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.application.name=Railway Ticket Booking System

spring.datasource.url=jdbc:mysql://localhost:3306/railway_booking_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```



### 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

---

## 📚 Swagger API Documentation

Swagger/OpenAPI is integrated for API documentation and testing.

Once the application is running, open:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger can be used to:

- View available APIs
- Enter request parameters
- Test API endpoints
- Verify successful responses
- Test validation and exception scenarios
- Test pagination
- Verify booking and cancellation workflows

---

## 📁 Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/
    │       └── railway/
    │           └── booking/
    │               │
    │               ├── controller/
    │               │   ├── BookingRestController.java
    │               │   ├── PassengerRestController.java
    │               │   └── TrainRestController.java
    │               │
    │               ├── dto/
    │               │   ├── BookingRequest.java
    │               │   └── TicketResponse.java
    │               │
    │               ├── exception/
    │               │   ├── BookingClosedException.java
    │               │   ├── GlobalExceptionHandler.java
    │               │   ├── InvalidPassengerDetailsException.java
    │               │   ├── InvalidTicketNumberException.java
    │               │   ├── SeatNotAvailableException.java
    │               │   ├── TicketAlreadyCancelledException.java
    │               │   └── TrainNotFoundException.java
    │               │
    │               ├── model/
    │               │   ├── Booking.java
    │               │   ├── BookingStatus.java
    │               │   ├── Passenger.java
    │               │   └── Train.java
    │               │
    │               ├── repository/
    │               │   ├── BookingRepository.java
    │               │   ├── PassengerRepository.java
    │               │   └── TrainRepository.java
    │               │
    │               ├── response/
    │               │   ├── ApiResponse.java
    │               │   ├── ErrorResponse.java
    │               │   └── PageMeta.java
    │               │
    │               └── service/
    │                   ├── BookingService.java
    │                   ├── PassengerService.java
    │                   └── TrainService.java
    │
    └── resources/
        └── application.properties
```

---

## 🎯 Project Highlights

This project demonstrates practical backend development concepts including:

**REST API Design • Layered Architecture • Spring Boot • JPA/Hibernate • MySQL • Business Logic • Pagination • Validation • Exception Handling • Seat Allocation • Waiting Lists • Train Status Management • Swagger/OpenAPI • Git/GitHub**

---

## 🔄 Key Booking Scenarios

### Successful Booking

```text
Passenger Exists
       ↓
Train Exists
       ↓
Train Active
       ↓
Train Not Started
       ↓
Seats Available
       ↓
Ticket CONFIRMED
       ↓
Seat Assigned
```

### Waiting List

```text
Passenger Exists
       ↓
Train Active
       ↓
Train Not Started
       ↓
No Seats Available
       ↓
Ticket WAITING
       ↓
Seat Number = null
```

### Cancellation & Promotion

```text
Confirmed Ticket
       ↓
Cancellation
       ↓
Seat Released
       ↓
Earliest Waiting Passenger
       ↓
Ticket CONFIRMED
       ↓
Seat Assigned
```

---

## 🧪 Testing

The application was tested using:

- **Swagger UI** for API testing
- **MySQL** for database verification
- Successful and failure scenarios
- Booking and cancellation workflows
- Seat availability
- Waiting-list promotion
- Train active/started validation
- Ticket search
- Booking history
- Pagination
- Custom exception handling

---

## 🎓 Concepts Demonstrated

- Object-Oriented Programming
- RESTful API Development
- Dependency Injection
- Layered Architecture
- JPA Entity Mapping
- Repository Pattern
- DTO Pattern
- Service Layer Business Logic
- Request Validation
- Custom Exception Handling
- Pagination
- Database Persistence
- Transactional Business Operations
- API Documentation
- Git Version Control

---

## 👨‍💻 Author

**Chandavena Nikitha**

B.Tech – Computer Science & Engineering (Artificial Intelligence)

---

## ⭐ Project

Built as a backend project to demonstrate practical **Java + Spring Boot development, REST API design, database integration, and real-world railway booking business logic**.
