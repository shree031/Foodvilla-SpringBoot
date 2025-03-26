<p align="center">
  <img src="https://github.com/user-attachments/assets/e65b9750-d3ba-418d-a356-8c2add637659" width="200">
  <img src="https://github.com/user-attachments/assets/399f0300-77d8-40e3-8309-aa87730b26f7" width="200">
  <img src="https://github.com/user-attachments/assets/57c727e7-4179-4e43-b122-6832e6ac6e5b" width="200">
  <img src="https://github.com/user-attachments/assets/d4534406-8695-4573-9355-ad30d87c377b" width="200">
</p>

# FoodVilla Backend

Welcome to the FoodVilla backend project! This repository contains the code for the backend part of the FoodVilla application, which powers the frontend features for browsing recipes and ordering ingredients.

## 🚀 Project Overview

FoodVilla Backend is built with Spring Boot and provides a robust API for managing recipes, user authentication, and ingredient orders. It is designed to work seamlessly with the FoodVilla frontend to deliver a complete web application experience.

## 🛠️ Features

- **Recipe Management**: API endpoints for creating, updating, deleting, and retrieving recipes.
- **User Authentication**: Secure endpoints for user registration, login, and role-based access control, with password encryption.
- **Ingredient Ordering**: Endpoints for managing ingredient availability and handling user orders.
- **Database Integration**: Utilizes PostgreSQL with Spring Data JPA for ORM.
- **RESTful API**: Fully RESTful API for easy integration with frontend applications.

## 🧰 Technologies Used

- **Backend Framework**: Spring Boot (specify the exact version if necessary)
- **Database**: PostgreSQL with Spring Data JPA
- **Security**: Spring Security for authentication and authorization with encrypted passwords
- **Build Tool**: Maven/Gradle (specify which one you are using)

## 🔧 Installation & Setup

To get a local copy of the project up and running, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/shree031/foodvilla-springboot.git
   ```
2. **Navigate to the project directory**
   ```bash
   cd foodvilla-springboot
   ```
3. **Install dependencies and build the project:**
   ```bash
   mvn install
   ```
4. **Set up the database:**
   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```
5. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
6. **Open your browser and go to http://localhost:8080 to view the running application.**

## 🔗 Integration with Frontend

This backend project is designed to work with the FoodVilla-Angular which is available at [FoodVilla Backend Repository](https://github.com/shree031/foodvilla-angular). Ensure that the frontend is configured to interact with the correct API endpoints provided by this backend.
