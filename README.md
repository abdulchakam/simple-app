# coding-interview-v2
I made this application myself

# feature 
  - Login
  - CRUD user
  - get all job and get job by code
  - web security authentication with jwt token
    
# spesification
  - Java 17
  - Maven 3.8.4
  - Swagger2
  - Postgresql
  
# intructions
   - Clone the project
   - Execute query DDL, DDL files are in the directory **src/main/resources/DDL.sql**
   - Open the project with the IntelliJ IDEA text editor and wait for the build process
   - Run the application in the main SimpleApplication method Or type command in terminal mvn spring-boot:run, wait until the process is complete
   - After that open browser copy and paste this url **http://localhost:8080/swagger-ui/index.html#/**
   - You can create a user first in the **/user/create** endpoint and for the request id set to null
   - After that you can log in with the user you created to get an access token
