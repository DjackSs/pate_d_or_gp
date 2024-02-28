# Projet Pate D'Or: Application grand public

![Pate D'Or front](/assets/Capture_front.png)

## Description

This is a Java Persistent Application (JPA) that uses the Hibernate framework to perform CRUD operations on a database.

This project is the second step in the development of an application to manage a fictional restaurant chain called "Pate D'Or".

Informations about the overall [context](/assets/Expression_du_besoin.pdf) and [feature of the program](/assets/US_reservation.pdf) are available.

## Technologies

* Java
* Java Server page Standar Tag Library (JSTL)
* Hibernate framework

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
The [ssms folder](/BDD/ssms) contains what is needed to connect to the database.

- [The create_bdd.sql file](/BDD/ssms/creat_bdd.sql)
- [The hibernate.cfg.xml file](/BDD/ssms/hibernate.cfg.xml)

Run the create_bdd script and add hibernate.cfg.xml [here](/src/main/java).

#### mysql

An alternative configuration is provided to make the project work with MySQL.
The [mysql folder](/BDD/mysql) contains what is needed to connect to the database:

- [The create_bdd_mysql.sql file](/BDD/mysql/creat_bdd_mysql.sql)
- [The pom.xml file](/BDD/mysql/pom.xml)
- [The hibernate.cfg.xml file](/BDD/mysql/hibernate.cfg.xml)

Run the create_bdd script and add hibernate.cfg.xml [here](/src/main/java). In addition, it is necessary to replace the pom.xml file in the root folder.

### ENV variable

* Configure the hibernate.cfg.xml file

Replace "user" and "password" with your credentials.

### Run the application

#### Tomcat server

This application runs on a Tomcat server.
The [apach-tomcat folder](/BDD/apache-tomcat-10.1.18) contains what is needed to create a server.

- Eclipse IDE

1) Preferences/Server/Runtime Environements -> Add the ./BDD/apach-tomcat folder
2) Add the library in the project Build Path as Server Runtime

Run [ServletHome](/src/main/java/controller/ServletHome.java) on the server.

