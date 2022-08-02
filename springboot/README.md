Table of contents
* [Introduction](#Introduction)
* [Quick Start](#Quick-Start)
* [Implementation](#Implementation)
* [Test](#Test)
* [Deployment](#Deployment)
* [Improvements](#Improvements)

# Introduction
This SpringBoot project is a new trading system which aims to replace the legacy monolithic application that is hard to scale and manage. The trading platform is a REST API that allows users to manage client profiles and accounts, execute security orders, etc. It uses Java 8 and SpringBoot to implement the REST API and a PostgresQL database to store all data including traders, accounts, quotes and security orders. Also, it fetches free market data (stock price) from a IEX Cloud service via the external REST API. Integration tests set up by JUnit are used to test major components. Swagger and postman are also used to test the REST endpoints. The application is built and managed by Maven and deployed using Docker. One PostgresQL and one SpringBoot application containers are created.



# Quick Start
- Prequiresites: Docker, CentOS 7
  - Docker scripts with description
      - build images
    
    PSQL image:
    ```
    FROM postgres:9.6-alpine
    ENV POSTGRES_DB jrvstrading
    COPY ./init_db_docker.sql /docker-entrypoint-initdb.d/
    ```
    ```
    cd ./springboot/psql
    docker build -t trading-psl
    ```
      
    APP image:
    ```
    #
    # Build stage
    #
    FROM maven:3.6-jdk-8-slim AS build
    COPY src /build/src
    COPY pom.xml /build/
    RUN mvn -f /build/pom.xml clean package -DskipTests
  
    #
    # Package stage
    #
    FROM openjdk:8-alpine
    COPY --from=build /build/target/trading-1.0-SNAPSHOT.jar /usr/local/app/trading/lib/trading_app.jar
    ENTRYPOINT ["java","-jar","/usr/local/app/trading/lib/trading_app.jar"]
    ```
    
    ```
    cd ./springboot
    docker build -t trading-app
    ```

    create a docker network

    ```
    docker network create trading-net
    ```

      - start containers
    
- Try trading-app with SwaggerUI (screenshot)

# Implemenation
## Architecture
- Draw a component diagram that contains controllers, services, DAOs, SQL, IEX Cloud, WebServlet/Tomcat, HTTP client, and SpringBoot. (you must create your own diagram)
- briefly explain the following components and services (3-5 sentences for each)
    - Controller layer (e.g. handles user requests....)
    - Service layer
    - DAO layer
    - SpringBoot: webservlet/TomCat and IoC
    - PSQL and IEX

## REST API Usage
### Swagger
What's swagger (1-2 sentences, you can copy from swagger docs). Why are we using it or who will benefit from it?
### Quote Controller
- High-level description for this controller. Where is market data coming from (IEX) and how did you cache the quote data (PSQL). Briefly talk about data from within your app
- briefly explain each endpoint
  e.g.
    - GET `/quote/dailyList`: list all securities that are available to trading in this trading system blah..blah..
### Trader Controller
- High-level description for trader controller (e.g. it can manage trader and account information. it can deposit and withdraw fund from a given account)
- briefly explain each endpoint
### Order Controller
- High-level description for this controller.
- briefly explain each endpoint
### App controller
- briefly explain each endpoint
### Optional(Dashboard controller)
- High-level description for this controller.
- briefly explain each endpoint

# Test
How did you test your application? Did you use any testing libraries? What's the code coverage?

# Deployment
- docker diagram including images, containers, network, and docker hub
  e.g. https://www.notion.so/jarviscanada/Dockerize-Trading-App-fc8c8f4167ad46089099fd0d31e3855d#6f8912f9438e4e61b91fe57f8ef896e0
- describe each image in details (e.g. how psql initialize tables)

# Improvements
If you have more time, what would you improve?
- at least 3 improvements