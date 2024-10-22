# PTO-Backend with Spring

This project is developed using Java and Spring MVC for the backend, integrating MySQL as the relational database and Hibernate for ORM (Object-Relational Mapping). It employs AJAX (or Fetch API) for asynchronous communication between the client and server, ensuring dynamic interactions without the need for page reloads. The application uses JPQL (Java Persistence Query Language) within Hibernate for efficient database queries and implements structured logging throughout the system to maintain traceability and facilitate debugging.

## Tech Stack

**Java-Spring MVC:** A powerful framework for building Java-based enterprise web applications, providing a well-structured backend.

**Hibernate:** A widely used ORM framework for simplifying database operations, mapping Java objects to database entities.

**MySQL:** A reliable relational database management system used for handling the application’s data storage and retrieval.

**AJAX/Fetch API:** Enables dynamic client-server communication, allowing the application to update data asynchronously without full page refreshes.
Features

Backend powered by Spring MVC.
Database operations managed by Hibernate using JPQL.
AJAX/Fetch API for dynamic content updates and seamless user experience.
Comprehensive logging strategy with appropriate logging levels for tracing:

**INFO    :** General flow of the application.

**DEBUG   :** Detailed debugging information for development.

**ERROR   :** Critical errors that could affect the system's performance.

**WARN    :** Potential issues that might need attention.Setup & Installation

## Clone the Repository:

    git clone https://github.com/DasunNirmal/PTO-Backend-v2.git

### Configure the Database:

Set up your MySQL database and ensure the correct JDBC URL, username, and password are configured in the application properties.

**Update Hibernate Configuration  :** Configure Hibernate for MySQL, ensuring proper mappings and connection settings.

**Set Up Logging  :** Adjust the logging levels in the configuration file (e.g., logback.xml).

**Build & Deploy  :** Use Maven to build the project.

Run the Spring application.

**Access the Frontend :** Once the backend is running, link it to your frontend project for seamless interaction. 
you can find the frontend here : https://github.com/Madushanka24/PTO-Backend-V2