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