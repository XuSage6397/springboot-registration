create database xusage_finnplay;

use xusage_finnplay;

CREATE TABLE IF NOT EXISTS `user`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `first_name` varchar(100) NOT NULL,
    `last_name`  varchar(100) NOT NULL,
    `email`      varchar(50)  NOT NULL,
    `password`   varchar(200) NOT NULL,
    `created_at` TIMESTAMP    NOT NULL,
    `created_by` varchar(50)  NOT NULL,
    `updated_at` TIMESTAMP   DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX IDX_email (email)
);