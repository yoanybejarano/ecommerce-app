CREATE DATABASE IF NOT EXISTS `payment-db` /*!40100
DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016
DEFAULT ENCRYPTION = 'N' */;

USE `payment-db`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: payment-db
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `audit_logs`
--

DROP TABLE IF EXISTS `audit_logs`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit_logs`
(
    `LogID`     int          NOT NULL AUTO_INCREMENT,
    `Action`    varchar(100) NOT NULL,
    `UserID`    int          NOT NULL,
    `Details`   text,
    `Timestamp` timestamp    NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`LogID`),
    KEY `UserID` (`UserID`),
    CONSTRAINT `audit_logs_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_logs`
--

LOCK TABLES `audit_logs` WRITE;
/*!40000 ALTER TABLE `audit_logs`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `audit_logs`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoices`
--

DROP TABLE IF EXISTS `invoices`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoices`
(
    `InvoiceID`     int            NOT NULL AUTO_INCREMENT,
    `UserID`        int            NOT NULL,
    `TotalAmount`   decimal(10, 2) NOT NULL,
    `InvoiceDate`   timestamp      NULL              DEFAULT CURRENT_TIMESTAMP,
    `DueDate`       timestamp      NOT NULL,
    `InvoiceStatus` enum ('Paid','Unpaid','Overdue') DEFAULT 'Unpaid',
    `CreatedAt`     timestamp      NULL              DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt`     timestamp      NULL              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`InvoiceID`),
    KEY `UserID` (`UserID`),
    CONSTRAINT `invoices_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoices`
--

LOCK TABLES `invoices` WRITE;
/*!40000 ALTER TABLE `invoices`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `invoices`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_methods`
--

DROP TABLE IF EXISTS `payment_methods`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_methods`
(
    `PaymentMethodID` int         NOT NULL AUTO_INCREMENT,
    `MethodName`      varchar(50) NOT NULL,
    `Description`     varchar(255)     DEFAULT NULL,
    `CreatedAt`       timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt`       timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`PaymentMethodID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_methods`
--

LOCK TABLES `payment_methods` WRITE;
/*!40000 ALTER TABLE `payment_methods`
    DISABLE KEYS */;
INSERT INTO `payment_methods`
VALUES (1, 'Credit Card', 'Payments made using a credit card, such as Visa, Mastercard, or Amex', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39'),
       (2, 'Debit Card', 'Payments made using a debit card linked to a bank account', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39'),
       (3, 'PayPal', 'Payments made using a PayPal account', '2024-11-15 23:39:39', '2024-11-15 23:39:39'),
       (4, 'Bank Transfer', 'Direct bank-to-bank transfers of funds', '2024-11-15 23:39:39', '2024-11-15 23:39:39'),
       (5, 'Cash', 'Payments made with physical currency', '2024-11-15 23:39:39', '2024-11-15 23:39:39'),
       (6, 'Check', 'Payments made via a written check', '2024-11-15 23:39:39', '2024-11-15 23:39:39'),
       (7, 'Mobile Payment', 'Payments made using mobile apps like Apple Pay, Google Pay, or Samsung Pay',
        '2024-11-15 23:39:39', '2024-11-15 23:39:39'),
       (8, 'Cryptocurrency', 'Payments made using digital currencies like Bitcoin or Ethereum', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39'),
       (9, 'Gift Card', 'Payments using preloaded gift cards', '2024-11-15 23:39:39', '2024-11-15 23:39:39'),
       (10, 'Direct Debit', 'Recurring payments deducted directly from a bank account', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39'),
       (11, 'Buy Now, Pay Later', 'Deferred payment options such as Afterpay or Klarna', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39'),
       (12, 'Wire Transfer', 'Electronic transfer of funds through wire services', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39'),
       (13, 'ACH Transfer', 'Automated Clearing House (ACH) network payments', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39'),
       (14, 'Cash on Delivery', 'Payments made upon receiving goods or services', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39'),
       (15, 'Prepaid Card', 'Payments using preloaded prepaid cards', '2024-11-15 23:39:39', '2024-11-15 23:39:39'),
       (16, 'Money Order', 'Payments using a money order issued by a bank or post office', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39'),
       (17, 'Digital Wallet', 'Payments made through wallets like Venmo or Skrill', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39'),
       (18, 'POS Financing', 'Point-of-sale financing options for large purchases', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39'),
       (19, 'International Transfer', 'Payments sent across borders through services like Western Union',
        '2024-11-15 23:39:39', '2024-11-15 23:39:39'),
       (20, 'Virtual Card', 'One-time use virtual credit or debit cards for online payments', '2024-11-15 23:39:39',
        '2024-11-15 23:39:39');
/*!40000 ALTER TABLE `payment_methods`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments`
(
    `PaymentID`       int            NOT NULL AUTO_INCREMENT,
    `UserID`          int            NOT NULL,
    `Amount`          decimal(10, 2) NOT NULL,
    `Currency`        varchar(10)    NOT NULL,
    `PaymentStatus`   enum ('Pending','Completed','Failed') DEFAULT 'Pending',
    `PaymentDate`     timestamp      NULL                   DEFAULT CURRENT_TIMESTAMP,
    `PaymentMethodID` int            NOT NULL,
    `TransactionID`   varchar(100)   NOT NULL,
    `CreatedAt`       timestamp      NULL                   DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt`       timestamp      NULL                   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`PaymentID`),
    UNIQUE KEY `TransactionID` (`TransactionID`),
    KEY `UserID` (`UserID`),
    KEY `PaymentMethodID` (`PaymentMethodID`),
    CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`),
    CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`PaymentMethodID`) REFERENCES `payment_methods` (`PaymentMethodID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `payments`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refunds`
--

DROP TABLE IF EXISTS `refunds`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refunds`
(
    `RefundID`     int            NOT NULL AUTO_INCREMENT,
    `PaymentID`    int            NOT NULL,
    `RefundAmount` decimal(10, 2) NOT NULL,
    `RefundReason` text,
    `RefundStatus` enum ('Initiated','Processed') DEFAULT 'Initiated',
    `RefundDate`   timestamp      NULL            DEFAULT CURRENT_TIMESTAMP,
    `CreatedAt`    timestamp      NULL            DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt`    timestamp      NULL            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`RefundID`),
    KEY `PaymentID` (`PaymentID`),
    CONSTRAINT `refunds_ibfk_1` FOREIGN KEY (`PaymentID`) REFERENCES `payments` (`PaymentID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refunds`
--

LOCK TABLES `refunds` WRITE;
/*!40000 ALTER TABLE `refunds`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `refunds`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security`
--

DROP TABLE IF EXISTS `security`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security`
(
    `SecurityID`           int       NOT NULL AUTO_INCREMENT,
    `PaymentID`            int       NOT NULL,
    `EncryptedCardDetails` text,
    `Token`                varchar(255)   DEFAULT NULL,
    `CreatedAt`            timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`SecurityID`),
    KEY `PaymentID` (`PaymentID`),
    CONSTRAINT `security_ibfk_1` FOREIGN KEY (`PaymentID`) REFERENCES `payments` (`PaymentID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security`
--

LOCK TABLES `security` WRITE;
/*!40000 ALTER TABLE `security`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `security`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users`
(
    `UserID`      int          NOT NULL AUTO_INCREMENT,
    `FirstName`   varchar(50)  NOT NULL,
    `LastName`    varchar(50)  NOT NULL,
    `Email`       varchar(100) NOT NULL,
    `PhoneNumber` varchar(20)       DEFAULT NULL,
    `CreatedAt`   timestamp    NULL DEFAULT CURRENT_TIMESTAMP,
    `UpdatedAt`   timestamp    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`UserID`),
    UNIQUE KEY `Email` (`Email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `users`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2024-11-18 14:18:41
/*GRANT ALL PRIVILEGES ON `payment-db`.* TO 'orwell'@'localhost' IDENTIFIED BY 'orwell1984';*/
/*ALTER USER 'root'@'localhost' IDENTIFIED BY 'orwell1984';*/