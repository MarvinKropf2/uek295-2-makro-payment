# Backend Application Documentation

## Overview
This README provides instructions for setting up and running the backend application for managing payment.
## To Download
Ensure you have the following installed on your system:
- [VSCode](https://code.visualstudio.com/download)
- [Java Development Kit (JDK)](https://jdk.java.net/archive/)
- [Gradle](https://spring.io/guides/gs/gradle)
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Postman](https://www.postman.com/downloads/)

## Installation Steps
Follow these steps to install and run the application:

1. **Clone the Repository**: Use GitBash, GitHub Desktop, or any Git client to clone the repository:
   
    ```bash
    git clone ...
    ```

2. **Create Docker Container**: Set up a Docker container named `uek295db` with PostgreSQL running on port 5432 and password `postgres`:

    ```bash
    docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=postgres --name uek295db postgres
    ```

3. **Start the Application**: Navigate to the project directory and run the Spring Boot application with Gradle:

    ```bash
    gradle bootRun
    ```

## Testing
- Use Postman or any web browser to access `http://localhost:8080/payment/` and test the endpoints.
- Available endpoints include `/payment` to retrieve all paymentes and `/payment/1` to retrieve a specific payment.
- Perform CRUD operations to manage paymentes. A test collection is provided in Postman.

## Initial Data
Upon starting the backend, initial data is populated into the database. You can view this data in your database management tool or in the `data.sql` file located at `src/main/resources`.

Two users with different roles and authorities are available for testing:
- **Admin**:
  - Username: admin
  - Password: 1234
- **User**:
  - Username: nichtAdmin
  - Password: 1234

## Conclusion
By following these installation steps, you can set up and run the backend application successfully.
