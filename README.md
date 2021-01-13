# Sample Demo Application (SDA)
***
> Demonstrative **Java** application to support ***multiple implementations***.

## Table of contents
***
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)

## General info
***
The purpose of the project is to illustrate *multiple implementations* on the same *single feature* called **Sample** as a ***unique entity***.

## Technologies
***
* JDBC
* JPA
* Hibernate
* Spring
* Thymeleaf
* Java (Sim)

## Setup
***
For **JDBC**, **JPA**, **Hibernate** and **Simulator**, please run the special dedicated console menu
from the console package **src/main/java/com.asi.sda/console**
* JdbcConsoleMenu
* JpaConsoleMenu
* HibernateConsoleMenu
* SimConsoleMenu

For **Spring** and **Thymeleaf**, please run **SampleDemoApplication**. Please consider you may need to use **Postman** for **Spring** requests and a browser on localhost server port 8080 for **Thymeleaf**. A database called **macromedia** is also needed to be created in **MySQL** before running any application.

## Features
Every technology has its own fully operational feature based on the single entity called **Sample**, including a custom **Mapper**, **RequestDto** and **ResponseDto**.
* **JDBC** => dao / service + console + main + testing
* **JPA** => dao / service + console + main + testing
* **Hibernate** => dao / service + console + main + testing
* **Spring** => repository / service / rest-controller + testing
* **Thymeleaf** => repository / service / controller
* **Simulator** (Java) => dao / service / controller + main + testing

To-do list:
* Replace the **Sample** entity by any desired entity.
* Replicate the **Sample** entity as many as needed.
* Establish all the required relations between entities.

## Status
***
Project is finished.
