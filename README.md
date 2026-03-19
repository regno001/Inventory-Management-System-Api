# Inventory Management System API

## Project Description
This project is a backend REST API for an Inventory Management System built using **Java and Spring Boot**.  
The system allows administrators and users to manage products, track inventory, and create orders.

The API supports:
- User registration and login
- Product management
- Order creation and inventory tracking

---

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- ModelMapper
- Lombok

---

## Project Architecture

The project follows a layered architecture:

Controller → Service → Repository → Database

Packages:

- controller
- service
- repository
- entity
- dto
- config
- exception
- security
  

---

## Database

Database used: **PostgreSQL**

# Port configuration
server.port=8081

# DB configuration
-//resource->appilcation.properties
-//Create a DB with Name inventoryDB
-spring.datasource.url=jdbc:postgresql://localhost:5432/inventoryDB
-spring.datasource.username=//postgresDB name
-spring.datasource.password=//password of your PostgresDB admin Access

# Working URL will be 
- POST http://localhost:8081/users/register
- POST http://localhost:8081/users/login
- POST http://localhost:8081/orders
- GET http://localhost:8081/products
- GET http://localhost:8081/products/id->id=productid ex 1,2,3,4..
- DELETE http://localhost:8081/products/id->id=productid ex 1,2,3,4..
- PATCH http://localhost:8081/products/id->id=productid ex 1,2,3,4..
