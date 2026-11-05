# Fraud Engine
Is a Spring Boot back end **demo** application designed to detect potentially fraudulent financial transactions using 
configurable fraud detection rules.
The application evaluates transactions against predefined rules, generates fraud alerts, assigns risk scores and 
provides endpoints for managing and monitoring fraud alerts.

## Features
- Transaction processing and validation
- Automated fraud detection using configurable rules
- High-amount transaction detection
- High-risk location detection
- Rapid transaction detection
- Fraud alert generation and risk scoring
- Fraud alert status management
- Fraud alert filtering by customer, status, severity, and date range
- Fraud alert statistics
- Customer transaction history
- Pagination and sorting of fraud alerts
- RESTful API endpoints
- API documentation using Swagger UI
- Basic Authentication for API security

## Technologies Used

- Java 21 – Programming language
- Spring Boot 4.1.2 – Backend application framework
- Spring Data JPA – Database interaction
- MySQL 8 – Relational database
- Maven – Dependency management and build automation
- Flyway – Database migration management
- JUnit 6 – Unit testing
- Mockito – Mocking dependencies during testing
- Lombok – Reducing boilerplate code
- Spring Security – API authentication
- Springdoc OpenAPI / Swagger UI – API documentation
- Postman – API testing
- Git & GitHub – Version control

## Requirements

Before running the Fraud Engine, ensure the following are installed:
- Java 21 or later
- Maven
- MySQL 8
- Git
- An IDE such as IntelliJ IDEA
- Postman (optional, for API testing)

## Database Setup
1. Install and configure MySQL.
2. Create a database named `fraud_engine`.
3. Configure your database connection in `application.properties`.
4. Flyway automatically manages database migrations when the application starts.

## Installation and Setup
1. Clone Repository: https://github.com/Lethari/fraud-engine.git
2. Navigate to Project Directory: `cd fraud_engine`
3. Configure the Database _ Create a MySQL database: `CREATE DATABASE fraud_engine`;
   Configure your database credentials in `src/main/resources/application.properties`.

**Example Configuration**:
```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/fraud_engine
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD

   spring.jpa.hibernate.ddl-auto=validate
   spring.flyway.enabled=true
   ```
Replace `YOUR_USERNAME` and `YOUR_PASSWORD` with your own MySQL credentials.

4. Build the Application: `mvn clean install`
5. Run the Application: `mvn spring-boot:run`
6. Application will start on: http://localhost:8080

- Flyway manages database migrations automatically during application startup.

### API Endpoints
- The Fraud Engine exposes RESTful endpoints for transaction processing and fraud alert management.

Fraud Alert Endpoints

**Method        Endpoint                                 Description**
GET        `/api/fraud_alerts`                           Retrieve all fraud alerts
GET        `/api/fraud_alerts/{id}`                      Retrieve a fraud alert by ID
GET        `/api/fraud_alerts/customer/{customerId}`     Retrieve alerts for a customer
PUT        `/api/fraud_alerts/{alertId}/status`          Update fraud alert status
GET        `/api/fraud_alerts/status/{status}`           Filter alerts by status
GET        `/api/fraud_alerts/severity/{severity}`       Filter alerts by severity
GET        `/api/fraud_alerts/page`                      Retrieve paginated fraud alerts
GET        `/api/fraud_alerts/date-range`                Retrieve alerts within a date range
GET        `/api/fraud_alerts/statistics`                Retrieve fraud alert statistics

**Transaction Endpoints**

**Method         Endpoint                                Description**
POST     `/api/transactions`                             Submit a transaction for fraud evaluation
GET      `/api/transactions/customer/
          {customerId}/history`                          Retrieve customer transaction history

**API Documentation**

### Swagger UI is available at:
[Swagger UI]`http://localhost:8080/swagger-ui/index.html`

## Testing
- The Fraud Engine uses JUnit and Mockito for unit testing and application testing.

### Run Tests
- Execute the following command from the project root directory:
`mvn clean test`

### API Testing
API endpoints can be tested using:
- Postman
- Swagger UI

### Test Coverage
- Tests cover fraud rule evaluation, transaction processing and fraud detection logic.

# Authentication and Security
- The Fraud Engine uses Spring Security with HTTP Basic Authentication to protect its REST API endpoints.

## Authentication
- API requests require valid authentication credentials.
- Postman and Swagger UI can be used to authenticate requests.
- Unauthorized requests are rejected.

## Security Configuration
- Database credentials and other sensitive configuration values are kept private.
- Never commit database passwords and sensitive credentials on GitHub.
- Use environment variables or secure configuration management for deployment.
- Securely store credentials in your local `application.properties` file.

# Project Structure
- The project follows a layered architecture to separate responsibilities and ensure maintainability is efficient.

```fraud-engine/
├── src/
│   ├── main/
│   │   ├── java/org/app/fraudengine/
│   │   │   ├── config/                   - Security
│   │   │   ├── controller/               - REST API
│   │   │   ├── service/                  - Logic
│   │   │   ├── rule/                     - Fraud
│   │   │   ├── entity/                   - JPA
│   │   │   ├── repository/               - Database
│   │   │   ├── dto/                      - Data
│   │   │   └── exception/
│   │   └── resources/
│   │       ├── db/migration/             - Flyway
│   │       └── application.properties
│   └── test/                             -Junit
├── pom.xml
└── README.md
```

