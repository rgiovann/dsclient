# **_SPRING BOOT CRUD INTERFACE - CLIENT ENTITY_**

[![Foo](https://rgiovann.github.io/image-repo/devsuperior.jpg)](https://devsuperior.com.br/)

This project was implemented as requirement of _"Bootcamp Spring 3.0"_ course (module 1) from DEVSUPERIOR school.  
The code in this repo builds a **CRUD** User interface for a hipothetical Client entity using **Spring Boot** framework with **Java** language
and **Maven** dependency manager.

The entity Client has the following attributes:

![](https://rgiovann.github.io/image-repo/client_astah.png)

The logical layers are arranged according to the picture below<sup>(1)</sup> :

![N|Solid](https://miro.medium.com/max/1100/1*xA9ZqDzT3_yfZfur3GW61A.webp)

The code handles 3 exceptions :

-   _ResourceNotFoundException_ [throwable] : When web request does not find the proper resource (HTTP 404)
-   _DatabaseException_ [throwable] : When constraint violation is found, in this example we have no assocition with other entity, so there is none. (HTTP 400)
-   _HttpMessageNotReadableException_ [not throwable] : When web request body contains a bad format JSON. (HTTP 400)

Furthermore, the code has audit attributes that keep track of database row creation/update dates. The database is embedded (H2 database) with a sql seed script for test purposes.

<sub>
(1) https://medium.com/learnwithnk/best-practices-in-spring-boot-project-structure-layers-of-microservice-versioning-in-api-cadf62bd3459_
</sub>
