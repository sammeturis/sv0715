# sv0715
This repository is a host to tool-rental demo API application.

To demonstrate simple Tool Rental API Application

Pre-requisites:

Database: h2 in-memory DB

Schema Sqls:

    Refer /src/main/resources/database/schema.sql

SQL to insert sample data:

    Refer /src/main/resources/database/data.sql

URLs:
    get tool checkout info: using postman or equivalent
    
        - localhost:8080/rental/checkout

    Sample Request: [curl]
        - curl -X GET \
            http://localhost:8080/rental/checkout \
            -H 'Content-Type: application/json' \
            -H 'Postman-Token: 575fe0b1-00ad-419f-8364-5f0b12870fdb' \
            -H 'cache-control: no-cache' \
            -d '{
                "toolCode": "LADW",
                "rentalDays":3,
                "discountPercent":10,
                "checkoutDate":"2020-07-02"
            }'


    Technology Stack:
        -Java 17
        -Spring Boot
        -Restful
        -JPA
        -H2 In-Memory DB


    Build and Run:
        Non-Containerized: 
            Build: mvn clean install
            Run: mvn spring-boot:run

    ToDos:
        -Swagger integration
        -Actuate Health metrics
        -Further product enhancements to maintain current inventory and persist checkout details etc








