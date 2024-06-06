# E-Commerce-Website #

## Overview ##
This project is a comprehensive e-commerce website built using React js and Spring Boot. It offers a full-featured online shopping experience, including user authentication, product browsing, shopping cart functionality, order management, and payment processing. The project aims to provide a scalable and robust platform for online businesses.

## Features ##
<li><b>User Authentication and Authorization : </b> Secure login and registration, role-based access control.</li>
<li><b>Product Management : </b>Add, update, delete, and display products.</li>
<li><b>Shopping Cart : </b>Add to cart, update quantity, remove from cart.</li>
<li><b>Order Management : </b>Place orders, view order history, manage orders.</li>
<li><b>Payment Integration : </b>Integrate with payment gateways for processing payments.</li>
<li><b>Search and Filter : </b>Search products by name, category, and other attributes.</li>
<li><b>Responsive Design : </b>Mobile-friendly design for a seamless experience on all devices.</li>

## Technologies Used ##
<li><b>Backend : </b>  Spring Boot</li>
<li><b>Database : </b> MySQL</li>
<li><b>Frontend : </b> React JS</li>
<li><b>Build Tool : </b> Maven</li>
<li><b>Version Control : </b> Git</li>

## Prerequisites ##
<li>JDK 17</li>
<li>Maven 4.0.0</li>
<li>MySQL 8.0</li>

## Getting Started ##
### Frontend Repository ###
The frontend code for this project is available in a separate repository. You can find it here.
()
### Clone the Repository ###


[git clone](https://github.com/vasu-choudhary/E-Commerce-backend)
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
The application should now be running on http://localhost:5173

## Project Structure ##
![image](https://github.com/vasu-choudhary/E-Commerce-backend/assets/170304449/6a407c59-0d13-442a-886a-bf47fdbbd04a)

<li><b>Config : </b> Contains configuration classes for the application.</li>
<li><b>controller : </b>Contains the web controllers.</li>
<li><b>dto : </b>Contains Data Transfer Object classes.</li>
<li><b>entity : </b> Contains the entity classes.</li>
<li><b>exception : </b>Contains custom exception classes and handlers.</li>
<li><b>repository : </b> Contains the Spring Data JPA repositories.</li>
<li><b>security : </b> Contains security configuration classes.</li>
<li><b>service : </b>Contains the service layer classes.</li>
<li><b>application.properties : </b>Configuration file for the application.</li>

## Contributing ##
1. Fork the repository.
2. Create a new branch (<b>git checkout -b feature/your-feature</b>).
3. Commit your changes (<b>git commit -am 'Add your feature'</b>).
4. Push to the branch (<b>git push origin feature/your-feature</b>).
5. Create a new Pull Request.


## Documentation ##
Maven Documentation is available online at ()
Spring Boot ()
MySQL ()
