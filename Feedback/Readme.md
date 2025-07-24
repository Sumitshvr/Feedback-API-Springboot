# Simple Feedback API

Welcome to the **Simple Feedback API**, a clean and easy-to-use Spring Boot REST application for capturing and managing user feedback.

---

## 🌟 Features

- Submit single or multiple feedback entries with validation
- Retrieve all saved feedback entries
- Server-side validation ensuring:
  - Name is required and minimum 3 characters
  - Email is required and must be valid
  - Message is required and minimum 10 characters
- Optional browser-based frontend to view and download feedback JSON
- Unit tests covering service-layer logic and edge cases
- PostgreSQL backend with easy cloud deployment readiness

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.x
- PostgreSQL (local or cloud-hosted)
- (Optional) Insomnia, Postman, or curl for API testing

### Configuration

1. **Set up your PostgreSQL database**

   Create a database named `feedbackdb` (or update as needed).

2. **Configure database credentials**

   Edit `src/main/resources/application.properties`:
   spring.datasource.url=jdbc:postgresql://localhost:5432/feedbackdb
  spring.datasource.username=your_db_username
  spring.datasource.password=your_db_password
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  server.port=8080

### Run the Project

Open your terminal at the project root and execute:


Once running, the API will be accessible at [http://localhost:8080](http://localhost:8080).

---

## 📡 API Endpoints

| HTTP Method | Endpoint           | Description                          |
|-------------|--------------------|------------------------------------|
| POST        | `/feedback`        | Submit a single feedback entry     |
| POST        | `/feedback/batch`  | Submit multiple feedback entries   |
| GET         | `/feedback`        | Retrieve all feedback entries       |
| DELETE      | '/feedback'        | Deletes all the feedbacks for testing |


---

## 📋 Sample Requests

### Submit Single Feedback

curl -X POST http://localhost:8080/feedback
-H "Content-Type: application/json"
-d '{"name":"Alice","email":"alice@example.com","message":"This is a feedback message."}'


### Submit Multiple Feedbacks (Batch)

curl -X POST http://localhost:8080/feedback/batch
-H "Content-Type: application/json"
-d '[{"name":"Sumit","email":"sumit@example.com","message":"Great app!"},{"name":"Bob","email":"bob@example.com","message":"Loved the service!"}]'


### Retrieve All Feedback

curl http://localhost:8080/feedback


### Delete All Feedback

curl -X DELETE http://localhost:8080/feedback


## 🧪 Running Tests

To run unit tests covering all feedback service logic:

mvn test


## 🌐 Optional Web Frontend

You can use the provided `feedback.html` file to view and download feedback in a user-friendly web interface.

- Place `feedback.html` in `src/main/resources/static/`.
- Access via [http://localhost:8080/feedback.html](http://localhost:8080/feedback.html).

---

## 🔧 Notes

- IDs auto-increment and won't reset after deletions (PostgreSQL behavior).
- Validation errors return HTTP 400 with descriptive messages.
- The project makes it easy to connect to cloud PostgreSQL services like Aiven.
- Easily extendable to add authentication, Swagger docs, or React frontend.

---

## 👨‍💻 Contribution

Feel free to fork, raise issues, or submit pull requests!

---

## 📜 License

This project is open source under the MIT License — see the `LICENSE` file for details.

---

Thank you for checking out Simple Feedback API!  
Happy coding. 🚀
