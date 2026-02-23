# code-with-quarkus

This project implements the LabSeq sequence using Quarkus, exposing a REST endpoint and a simple web interface for testing.

## About the LabSeq Sequence

The LabSeq sequence is defined as:

- l(0) = 0
- l(1) = 1
- l(2) = 0
- l(3) = 1
- For n ≥ 4:
  l(n) = l(n − 4) + l(n − 3)

This project includes an efficient implementation with caching to support large values.

## Running the Application in Dev Mode

Start the application with live reload:

```bash
./mvnw quarkus:dev


The application will be available at:
- Web UI: http://localhost:8080/index.html
- API Endpoint: http://localhost:8080/labseq/{n}
- Swagger UI: http://localhost:8080/q/swagger-ui
- Dev UI: http://localhost:8080/q/dev/
REST Endpoint
GET /labseq/{n}
Returns the LabSeq value for the given index n.
Example request:
GET http://localhost:8080/labseq/25


Example response:
138


Rules:
- n must be an integer greater than or equal to 0
- The service uses caching for performance
- Large values are supported
Web Interface
A simple HTML/JavaScript page is included for testing:
http://localhost:8080/index.html


You can enter a number and retrieve the LabSeq value without using external tools.
Packaging the Application
Build the application:
./mvnw package


The output will be located in:
target/quarkus-app/


Run the packaged application:
java -jar target/quarkus-app/quarkus-run.jar


Running Tests
Execute the test suite:
./mvnw test


Technologies Used
- Java 17
- Quarkus
- Maven
- JAX-RS
- JUnit 5
- HTML and JavaScript


Quarkus Documentation
For more information about Quarkus, visit:
https://quarkus.io/