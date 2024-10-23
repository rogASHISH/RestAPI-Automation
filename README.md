# API Automation Project with REST Assured

## Overview
This project automates API testing using **REST Assured** in Java. The goal is to test basic CRUD operations on the [ReqRes](https://reqres.in/) API endpoints. The project includes automated tests for the following:

- **GET** `/api/users` - Retrieve a list of users.
- **GET** `/api/users/{id}` - Fetch user details by ID.
- **POST** `/api/users` - Create a new user.
- **PUT** `/api/users/{id}` - Update user information.
- **DELETE** `/api/users/{id}` - Delete a user.

The tests include **JSON schema validation**, **data-driven testing** using external JSON files, and are integrated with **GitHub Actions** for Continuous Integration (CI).

## Prerequisites
To run this project locally, ensure you have the following installed:

- **Java Development Kit (JDK)** 11 or higher
- **Maven** (for dependency management)
- **Git** (for cloning the repository)
- An IDE like **IntelliJ IDEA** or **Eclipse** (recommended)

## Project Setup

### 1. Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/RestAssured-API-Automation.git
cd RestAssured-API-Automation
```
2. Install Dependencies
Ensure you have Maven installed and set up in your system. Then run the following command to install all required dependencies:
```bash
mvn clean install
```
3. Run the Tests
Run all the tests using Maven:
```bash
mvn test
```

Project Structure

```bash
src/
├── main/
│   ├── java/
│   │   ├── com/automation/utils/
│   │   │   └── DataProviderUtils.java
│   └── resources/
│       ├── createUserData.json
│       ├── updateUserData.json
│       ├── jsonSchema.json
├── test/
│   ├── java/
│   │   ├── com/automation/tests/
│   │   │   ├── BaseApiTest.java
│   │   │   ├── UserApiTest.java
│   │   └── .github/
│       └── workflows/
│           └── api-test-pipeline.yml

```

Configuration and Customization
1. JSON Files for Data-Driven Testing
You can modify or add data to the following JSON files to change the test cases:

createUserData.json: Used for creating a new user.
updateUserData.json: Used for updating an existing user.
jsonSchema.json: Used to validate the response structure for the GET endpoints.
2. GitHub Actions CI/CD Pipeline
The project includes a GitHub Actions pipeline defined in the .github/workflows/api-test-pipeline.yml file. This workflow is triggered on every push or pull request to the main branch and automatically runs all tests.

GitHub Actions Workflow
The workflow configuration (api-test-pipeline.yml) includes:
```bash
name: API Test Pipeline

on:
  push:
    branches: 
      - main
  pull_request:
    branches:
      - main

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - name: Checkout code
      uses: actions/checkout@v2

    - name: Set up JDK 11
      uses: actions/setup-java@v2
      with:
        java-version: '11'

    - name: Build with Maven
      run: mvn clean install

    - name: Run API Tests
      run: mvn test

```
