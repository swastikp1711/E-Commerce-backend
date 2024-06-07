# E-Commerce-Website #

## Overview ##
This project is a comprehensive e-commerce website built using React js and Spring Boot. It offers a full-featured online shopping experience, including user authentication, product browsing, shopping cart functionality, order management, invoice generation and payment processing. The project aims to provide a scalable and robust platform for online businesses.

## Features ##
* <b>User Authentication and Authorization : </b> Secure login and registration, role-based access control.
* <b>Product Management : </b>Add, update, delete, and display products.
* <b>Shopping Cart : </b>Add to cart, update quantity, remove from cart.
* <b>Order Management : </b>Place orders, view order history, manage orders.
* <b>Payment Integration : </b>Integrate with payment gateways for processing payments.
* <b>Search and Filter : </b>Search products by name, category, and other attributes.
* <b>Product Management : </b>Add, update, delete, and display products.
* <b>Invoice Generation : </b>Automatically generate invoices and can be downloaded.
* <b>Security:</b>
     * Secure user authentication with encrypted passwords.
     * Protect API endpoints with authentication tokens.
     * Implement input validation to prevent XSS and SQL injection.
* <b>Responsive Design : </b>Mobile-friendly design for a seamless experience on all devices.

## Technologies Used ##
* <b>Backend : </b>  Spring Boot
* <b>Database : </b> MySQL
* <b>Frontend : </b> React JS
* <b>Build Tool : </b> Maven
* <b>Version Control : </b> Git

## Prerequisites ##
* JDK 17
* Maven 4.0.0
* MySQL 8.0

## Getting Started ##
### Frontend Repository ###
The frontend code for this project is available in a separate repository. You can find it [here](https://github.com/vasu-choudhary/E-Commerce-frontend)
### Clone the Repository ###


```
git clone https://github.com/yourusername/e-commerce-website.git
```
```
cd e-commerce-app
```
## Set Up the Database ##
1. Create a new database in MySQL:
 ```
CREATE DATABASE ecommerce_db;
```
2. Update the database configuration in src/main/resources/application.properties:
```
spring.datasource.url=jdbc:mysql://localhost:3306/ecommercedb
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

```
## Build and Run the Application ##
```
mvn clean install
mvn spring-boot:run
```
The application should now be running on http://localhost:8080

## Project Structure ##
![image](https://github.com/vasu-choudhary/E-Commerce-backend/assets/170304449/6a407c59-0d13-442a-886a-bf47fdbbd04a)

* <b>Config : </b> Contains configuration classes for the application.
* <b>controller : </b>Contains the web controllers.
* <b>dto : </b>Contains Data Transfer Object classes.
* <b>entity : </b> Contains the entity classes.
* <b>exception : </b>Contains custom exception classes and handlers.
* <b>repository : </b> Contains the Spring Data JPA repositories.
* <b>security : </b> Contains security configuration classes.
* <b>service : </b>Contains the service layer interfaces and their implementation classes.
* <b>application.properties : </b>Configuration file for the application.


## Documentation ##
*  [Maven](https://maven.apache.org/scm.html)
*  [Spring Boot](https://spring.io/projects/spring-boot)
*  [MySQL Community server](https://dev.mysql.com/downloads/file/?id=526927)
