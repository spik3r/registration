## Registration APP

#Requirements
- Java 8
- Docker
- Maven
- Docker Compose

#Info:
####DB: 
    user: postgres
    password: changeme
    port: 5432
####Kafka: 
    topic name: registration
    port: 9092
#Running the project
- start docker 
- right click docker-compose.yml within IntelliJ or other IDE and click run. Alternatively run `docker-compose up -d` from terminal

#HTTP Requests:
All below requests can be found under the `resources/requests` directory 

##Creating a user:
- call the endpoint using postman, curl or right click & run `register_user.http` from within IntelliJ or your IDE

##Getting all users:
- call the endpoint using postman, curl or right click & run `get_all_users.http` from within IntelliJ or your IDE

##Getting user by ID:
>Note this will need you to update the ID in the below mentioned http request with the id you want to get
- call the endpoint using postman, curl or right click & run `get_user_by_id.http` from within IntelliJ or your IDE
