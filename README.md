# Budget API Project
Java RESTful API created for practicing.

## Classes Diagram

```mermaid
classDiagram
  class User {
     -Long id
     -String name
     -String email
     -String password
     -String passwordHash
  }

  class Transaction {
     -Long id
     -String description
     -Double amount
     -Date dueDate
     -Date expiration
     -TransactionEnum type
     -StatusEnum status
     -FrequencyEnum frequency
     -Integer quantity
     -User user
     -Transaction original
     -Transaction[] repetitions
  }
  
  class TransactionEnum {
      <<enumeration>>
      INCOME
      EXPENSE
  }
  
  class StatusEnum {
      <<enumeration>>
      PENDING
      CANCELED
      COMPLETED
  }
  
  class RepeatEnum {
      <<enumeration>>
      NONE
      DAILY
      WEEKLY
      MONTHLY
      YEARLY
  }

  User "1" *-- "0..*" Transaction
  Transaction "1" *-- "0..1" Transaction
  Transaction "1" *-- "1..*" Transaction
  Transaction *-- TransactionEnum
  Transaction *-- StatusEnum
  Transaction *-- RepeatEnum
```

## Docker Deployment Instructions

### Prerequisites

- Docker installed on your machine
- Docker Compose installed on your machine

### Build and Run Docker Containers

1. Clone the repository:
   ```sh
   git clone https://github.com/aldair-gc/budget-dev.git
   cd budget-dev
   ```

2. Build and run the Docker containers:
   ```sh
   docker-compose up --build
   ```

### Configure Environment Variables for PostgreSQL

The `docker-compose.yml` file sets the following environment variables for the PostgreSQL service:

- `POSTGRES_DB=budget_dev`
- `POSTGRES_USER=budget_dev_user`
- `POSTGRES_PASSWORD=budget_dev_password`

The Java application service uses the following environment variables to connect to the PostgreSQL database:

- `PGHOST=database`
- `PGPORT=5432`
- `PGDATABASE=budget_dev`
- `PGUSER=budget_dev_user`
- `PGPASSWORD=budget_dev_password`

### Access the Application

Once the Docker containers are up and running, you can access the application at:
```
http://localhost:8080
```

## CI/CD Setup Instructions

### Prerequisites

- GitHub account
- Repository on GitHub

### GitHub Actions Configuration

1. Create a directory for GitHub Actions workflows:
   ```sh
   mkdir -p .github/workflows
   ```

2. Create a CI configuration file:
   ```sh
   touch .github/workflows/ci.yml
   ```

3. Add the following content to the `ci.yml` file:
   ```yaml
   name: CI

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

         - name: Set up JDK 21
           uses: actions/setup-java@v2
           with:
             java-version: '21'

         - name: Build with Gradle
           run: ./gradlew build

         - name: Run tests
           run: ./gradlew test

         - name: Build Docker image
           run: docker-compose build

         - name: Deploy Docker container
           run: docker-compose up -d
   ```
