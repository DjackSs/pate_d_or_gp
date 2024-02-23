# Projet Pate D'Or: Application grand public

![Pate D'Or front](/assets/capture_front.png)

## Description

This is a Java Persistent Application (JPA) using the Hibernate framework to perform CRUD opperations on a database.

This project is the second step in developing an application to manage a fictional restaurant chain called "Pate D'Or".

Information about the context can be found in the ./assets folder.

## Technologies

* Java
* Java Server page Standar Tag Library (JSTL)
* Hibernate framwork

### Features

* Use the Project Object Model (POM) of Maven
* Implement a Data Access Object (DAO) pattern
* Implement a Model View Controller (MVC) pattern

## Setup

### installation:

This project was developed using the Eclipse IDE.

- `git clone https://github.com/DjackSs/pate_d_or_gp.git`
- Import existing project into workspace

### JDBC

This project uses a Relational DataBase Management System (RDBMS).

#### SSMS

By default, the project is run using SQL Server.
The ./BDD/ssms folder contains what is needed to connect to the database.

- The create_bdd.sql file
- The hibernate.cfg.xml file

Run the create_bdd script and add the hibernate.cfg.xml to the ./src/main/java folder (Java Ressources).

#### mysql

An alternative configuration is provided to make the project work with MySQL.
The ./BDD/mysql folder contains what is needed to connect to the database:

- The create_bdd_mysql.sql file
- The pom.xml file
- The hibernate.cfg.xml file

Run the create_bdd script and add the hibernate.cfg.xml to the ./src/main/java folder (Java Ressources). In addition, it is necessary to replace the pom.xml file in the root folder.

### ENV variable

* Configure the hibernate.cfg.xml file

Replace "user" and "password" by your credentials.

### Run the application

#### Tomcat server

This application run on a Tomcat server.
The ./BDD/apach-tomcat folder contain what it is need to creat a server.

- Eclipse IDE
 
1) Preferences/Server/Runtime Environements -> Add the ./BDD/apach-tomcat folder
2) Add the library in the project Build Path as Server Runtime

Run ./src/main/java/controller/ServletHome on the server.

