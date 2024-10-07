# Event Management Fullstack Application

[![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white)](#)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff)](#)
[![React](https://img.shields.io/badge/React-%2320232a.svg?logo=react&logoColor=%2361DAFB)](#)
[![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?logo=typescript&logoColor=fff)](#)
[![Postgres](https://img.shields.io/badge/Postgres-%23316192.svg?logo=postgresql&logoColor=white)](#)
[![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff)](#)

This repository contains a fullstack application for managing events, developed as part of a technical challenge. The application features a **Java** backend using **Spring Boot**, a **React** with **Typescript** frontend and a **PostgreSQL** database, all containerized using **Docker Compose**.

## Technology Stack

- Backend: Java 17, Spring Boot, Maven, Lombok, Spring Data JPA, PostgreSQL
- Frontend: React, TypeScript, Vite, AntDesign, Axios
- Testing: JUnit, Spring Boot Starter Test
- Package Management: pnpm, ESLint

## Setup Instructions
### Prerequisites

Ensure you have the following installed:
- Docker
- Docker Compose
- Java Development Kit (JDK) 17
- Maven

### Running the application with Docker

1. Clone the repository
2. Enter the backend folder and build the **JAR** file
```
mvn clean package
```
3. Navigate back to the project root and start the containers
```
docker-compose up --build
```

### Port Configuration
- Backend: `8080`
- Frontend: `3500`
- Database: `5432`

## Backend details

### Architecture
- **RESTful API**: Built with Spring Boot, adhering to REST principles.
- **Repository Pattern**: Implemented to separate data access logic from business logic.
- **Error Handling**: Custom exception classes ensure proper error validation.

### Error Handling and Validation

The API implements comprehensive error handling with appropriate **HTTP status codes**. Custom exceptions ensure that users receive **clear feedback** on any issues encountered.

### Testing
- All services include **unit tests** to verify functionality and ensure code quality.

## Frontend Details

### Framework
- Developed using React and hooks for state management.
- Utilizes AntDesign for UI components.
- Axios is used for API calls, with Day.js for date handling.

### Features

- Created a **EventContext** to manage application state and have separation of concerns.
- **Responsive Design**: The application is fully responsive, providing an optimal user experience on both mobile and desktop devices.
- API Integration: Axios is used for seamless API calls, while Day.js facilitates date handling.

## Database

All **SQL scripts** for database and table creation are included in the project. These scripts will be **automatically executed** when the Docker container is built, ensuring that the **database is set up** with the necessary structure and mock data for immediate use.

### PostgreSQL was selected for the following reasons:
- **Lightweight and efficient**, making it suitable for this application.
- I had previous experience with PostgreSQL, making it easy to utilize and implement.

## Functionality Overview

The application supports a CRUD system for managing events with the following attributes:

- ID: Automatically generated sequential identifier.
- Title: String field for the event name.
- Start Date: Date and time when the event starts.
- End Date: Date and time when the event ends.
- Price: Cost to participate in the event.
- Status: Can be "Started", "Completed", or "Paused".

Business Rules:

- Events cannot have an end date that precedes the start date.
- The status can be modified during updates.

## Conclusion

This project showcases the integration of a Java backend with a React frontend, leveraging Docker for containerization. It meets the requirements of a fullstack application while maintaining code quality and usability.

## Images
![](https://imgur.com/1OP6aeF.png)
##
![](https://imgur.com/I0e9wm3.png)
##
![](https://imgur.com/Yp4qrBw.png)
##
![](https://imgur.com/VhDP6TK.png)
##
![](https://imgur.com/YBrDnTQ.png)
##
![](https://imgur.com/14IUiRF.png) ![](https://imgur.com/FYoQvGw.png)
