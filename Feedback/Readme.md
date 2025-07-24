📋 Simple Feedback API
This is a basic Spring Boot REST API to submit and view user feedback. It connects to PostgreSQL and supports both single and batch feedback submissions.

**✅ Features**
---->Submit single or multiple feedback entries

---->View all submitted feedback

---->Delete all feedback (for dev cleanup)

---->Server-side validation

---->Optional frontend viewer with download button

🏁 How to Run the Project
Step 1: Prerequisites
---->Make sure you have:
---->Java 17 or higher
---->Maven installed

PostgreSQL setup (running locally)

**Step 2:** Set Up the Database
---->Create a PostgreSQL database named feedbackdb
---->Or change the name as per your preference.

---->Update this section in src/main/resources/application.properties:
text
spring.datasource.url=jdbc:postgresql://localhost:5432/feedbackdb
spring.datasource.username=your_postgres_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

Step 3: Run the Project
---->In the project root folder, open terminal and run this command:
---->bash
---->mvn spring-boot:run

You should see the app running at:
text
http://localhost:8080
📮 Sample API Requests
---->You can use Postman / Insomnia / cURL to test the API.

🚀 Submit Single Feedback (POST /feedback)
Request:
json
{
"name": "Alice",
"email": "alice@example.com",
"message": "This is a feedback message."
}

cURL:
bash
curl -X POST http://localhost:8080/feedback \
-H "Content-Type: application/json" \
-d "{\"name\":\"Alice\",\"email\":\"alice@example.com\",\"message\":\"This is a feedback message.\"}"

🚀 Submit Multiple Feedbacks (POST /feedback/batch)
Request:
json
[
{
"name": "Sumit",
"email": "sumit@example.com",
"message": "First batch message"
},
{
"name": "Bob",
"email": "bob@example.com",
"message": "Second batch feedback from Bob"
}
]

✅ View All Feedback (GET /feedback)
URL:
text
http://localhost:8080/feedback
cURL:

bash
curl http://localhost:8080/feedback
🗑️ Delete All Feedback (DELETE /feedback)
cURL:

bash
curl -X DELETE http://localhost:8080/feedback
🧪 How to Run Tests
To run all unit tests:

bash
mvn test
🌐 Optional Web Viewer
To show feedback on an HTML page (and allow JSON download):

Copy feedback.html into:
src/main/resources/static/

Then open this URL:

text
http://localhost:8080/feedback.html
This page will:

Display feedback entries in a table

Allow you to download all feedback as a .json file

📌 Notes
IDs will auto-increment (PostgreSQL SERIAL) and won’t reset automatically, even if you delete rows

Server validates:

Name: at least 3 characters

Email: must be valid

Message: at least 10 characters