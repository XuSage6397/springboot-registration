# Stringboot Registration

[TOC]

## Summary
This is an example web-based user registration project.

## Quick Start
### Basic environment
1. Java 17
2. Mysql 5.7

### Building
1. Clone the repository
    ```ssh
    git clone https://github.com/XuSage6397/springboot-registration.git
    ```
1. Go to the project directory
    ```shell
    cd springboot-registration
    ```
1. Maven build
    ```shell
    mvn clean package spring-boot:repackage -Dmaven.test.skip=true
    ```
1. Docker Build
   ```shell
   docker-compose up --build
   ```
   During the execution of this command, there is a relatively long waiting time, which is booting mysql and initialize the database.
1. Visit the web UI
   
   http://server:8080

## Demo Site
   http://107.148.210.139:8080/

## Features
1. Add `@UniqueField`, a general database uniqueness check annotation.
2. Custom spring security form login field and checking mechanism.
3. Save encrypted password into database.
4. Add a common `FieldRelate` annotation for checking multi-fields relation.
5. Add `FieldsMatch` annotation for checking two fields equality
6. Add unit test for testing `FieldRelate`
7. Extends default UserDetails add UserId
8. Extends SpringFramework BeanUtils, add `copyNonNullProperties`, which provide quickly copy properties for update.
9. Add PasswordEncoder for password storage.
10. Custom ValidateGroups for different validate

## Todos
- [ ] Change the frontend to freemarker
- [ ] Add server side message I18N support
- [ ] Validate extends

## Mysql 
### Docker
   During the first time docker-compose up --build, the database will be initialized.
   The initialize script in `docker/initdb.d/init.sql`

### Common script
```sql
CREATE DATABASE IF NOT EXISTS xusage_finnplay;
USE xusage_finnplay;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
   `id`         int       NOT NULL AUTO_INCREMENT,
   `first_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
   `last_name`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
   `email`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL,
   `password`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
   `created_at` timestamp NULL                                                DEFAULT NULL,
   `created_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL,
   `birthday`   date                                                          DEFAULT NULL,
   `updated_at` timestamp NULL                                                DEFAULT NULL,
   `updated_by` varchar(50)                                                   DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `IDX_email` (`email`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `last_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
```

## Special Thanks
1. https://github.com/eazybytes/spring/tree/3.1.2/example_37
   
   The pom.xml and frontend reference from https://github.com/eazybytes/spring/tree/3.1.2/example_37

3. ChatGPT
   
   Provide technical support

3. Google

   Provide technical support

# Assignment requirements

## Functionality requirements:
1. The system shall provide a home page to the user
2. On the home page, user can login with existing credentials, or regsiter a new account
3. User account information shall include: first name, last name, email, password, and birthday
4. After logged in, user shall be presented with his/her account information, and user can edit the account information (including password)
5. Logged in user shall be able to logout

## Technical requirements:
1. Springboot
2. Hibernate
3. MySQL
4. Classic three layers design - Controller, Service, and Dao
5. Spring MVC
6. If possible, build and delivery the application via Docker image
