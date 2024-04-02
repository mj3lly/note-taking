# Note Taking Project (Spring Boot)
This is a Note-Taking Java / Maven / Spring Boot (version 3.2.4) application.

# How to Run
This application is packaged as a jar which has Tomcat embedded. No Tomcat or JBoss installation is necessary. You can 
run it using the ```java -jar``` command.

* Clone this repository
* Make sure you are using JDK 17 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
    java -jar target/note-taking-0.0.1-SNAPSHOT.jar
    or
    mvn spring-boot:run
```

Once the application runs you should see something like this

```
    2024-04-02T17:08:58.217+08:00  INFO 4152 --- [note-taking] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
    2024-04-02T17:08:58.223+08:00  INFO 4152 --- [note-taking] [           main] c.ota.notetaking.NoteTakingApplication   : Started NoteTakingApplication in 1.282 seconds (process running for 1.491)
```

This application also has a swagger UI for easy access of REST endpoints.
```
    http://localhost:8080/swagger-ui/index.html
```


## About the Service

The service is just a simple note-taking REST service. It uses a file based solution to store the notes in the background. 
Allows user to create, retrieve, update and delete notes. Each note consists of a title and body.

### Create a new note

```
    curl -X 'POST' \
      'http://localhost:8080/notes' \
      -H 'accept: application/json' \
      -H 'Content-Type: application/json' \
      -d '  {
        "title": "Java",
        "body": "Coding is cool!"
      }'
```

### Retrieve all notes

```
    curl -X 'GET' \
      'http://localhost:8080/notes' \
      -H 'accept: application/json'
```

### Retrieve a specific note by id

```
    curl -X 'GET' \
      'http://localhost:8080/notes/1' \
      -H 'accept: application/json'
```

### Update a specific note

```
    curl -X 'POST' \
      'http://localhost:8080/notes' \
      -H 'accept: application/json' \
      -H 'Content-Type: application/json' \
      -d '  {
        "id": 2,
        "title": "Junit",
        "body": "Testing is part of development!"
      }'
```

### Delete a specific note

```
    curl -X 'DELETE' \
      'http://localhost:8080/notes/2' \
      -H 'accept: application/json'
```
