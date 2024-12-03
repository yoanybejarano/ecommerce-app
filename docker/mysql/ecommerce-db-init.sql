CREATE DATABASE  IF NOT EXISTS `ecommerce-db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecommerce-db`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecommerce-db
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `total_amount` decimal(38,2) DEFAULT NULL,
                        `user_id` bigint DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UK9emlp6m95v5er2bcqkjsw48he` (`user_id`),
                        CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `quantity` int NOT NULL,
                             `total_price` decimal(38,2) DEFAULT NULL,
                             `unit_price` decimal(38,2) DEFAULT NULL,
                             `cart_id` bigint DEFAULT NULL,
                             `product_id` bigint DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
                             KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`),
                             CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
                             CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Electronics'),(2,'Clothing & Fashion'),(3,'Home & Kitchen'),(4,'Health & Beauty'),(5,'Sports & Outdoors'),(6,'Books & Media'),(7,'Toys & Baby Products');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `download_url` varchar(255) DEFAULT NULL,
                         `file_name` varchar(255) DEFAULT NULL,
                         `file_type` varchar(255) DEFAULT NULL,
                         `image` longblob,
                         `product_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FKgpextbyee3uk9u6o2381m7ft1` (`product_id`),
                         CONSTRAINT `FKgpextbyee3uk9u6o2381m7ft1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `price` decimal(38,2) DEFAULT NULL,
                              `quantity` int NOT NULL,
                              `order_id` bigint DEFAULT NULL,
                              `product_id` bigint DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
                              KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
                              CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
                              CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
                          `order_id` bigint NOT NULL AUTO_INCREMENT,
                          `order_date` date DEFAULT NULL,
                          `order_status` enum('CANCELLED','DELIVERED','PENDING','PROCESSING','SHIPPED') DEFAULT NULL,
                          `total_amount` decimal(38,2) DEFAULT NULL,
                          `user_id` bigint DEFAULT NULL,
                          PRIMARY KEY (`order_id`),
                          KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
                          CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `brand` varchar(255) DEFAULT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           `inventory` int NOT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `price` decimal(38,2) DEFAULT NULL,
                           `category_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
                           CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Apple','iPhone 14 Pro Max with 128GB storage',50,'iPhone 14 Pro Max',1199.99,1),(2,'Samsung','Galaxy S22 Ultra with 256GB storage',40,'Galaxy S22 Ultra',999.99,1),(3,'Sony','Noise-cancelling over-ear headphones',30,'Sony WH-1000XM5',349.99,1),(4,'Dell','15-inch laptop with Intel Core i7',20,'XPS 15 Laptop',1299.99,1),(5,'DJI','Drone with 4K camera and GPS',15,'DJI Mavic Air 2',799.99,1),(6,'Bose','Portable Bluetooth speaker',100,'SoundLink Revolve+',199.99,1),(7,'Canon','Digital camera with 24MP sensor',25,'EOS Rebel T7',549.99,1),(8,'Apple','Smartwatch with fitness tracking',60,'Apple Watch Series 8',399.99,1),(9,'Logitech','Wireless ergonomic mouse',200,'MX Master 3S',99.99,1),(10,'HP','27-inch 4K monitor',35,'HP U27 4K Monitor',349.99,1),(11,'Nike','Running shoes for men',75,'Nike Air Zoom Pegasus',129.99,2),(12,'Adidas','Women’s casual sneakers',60,'Adidas Stan Smith',99.99,2),(13,'Levi’s','Classic fit denim jeans',100,'Levi’s 501 Jeans',59.99,2),(14,'Gucci','Luxury leather belt',15,'Gucci Leather Belt',499.99,2),(15,'North Face','Insulated winter jacket',20,'Thermoball Eco Jacket',229.99,2),(16,'Ray-Ban','Polarized aviator sunglasses',50,'Ray-Ban Aviator Classic',199.99,2),(17,'H&M','Summer dress for women',80,'Floral Print Maxi Dress',39.99,2),(18,'Puma','Sportswear hoodie',120,'Puma Essentials Hoodie',49.99,2),(19,'Zara','Slim fit blazer for men',30,'Zara Men’s Blazer',129.99,2),(20,'Coach','Designer handbag',25,'Coach Shoulder Bag',399.99,2),(21,'Instant Pot','Multi-function pressure cooker',100,'Instant Pot Duo',89.99,3),(22,'Dyson','Cordless vacuum cleaner',40,'Dyson V15 Detect',749.99,3),(23,'KitchenAid','Stand mixer with 10 speeds',20,'KitchenAid Artisan',299.99,3),(24,'Ikea','Compact dining table',50,'Ikea Melltorp',129.99,3),(25,'Philips','Air fryer with 3.5L capacity',30,'Philips Air Fryer XL',199.99,3),(26,'Cuisinart','Stainless steel cookware set',25,'Cuisinart 12-Piece Set',249.99,3),(27,'Tefal','Non-stick frying pan',80,'Tefal Comfort Pan',29.99,3),(28,'Serta','Memory foam mattress',10,'Serta Perfect Sleeper',899.99,3),(29,'Brita','Water filter pitcher',150,'Brita Standard Pitcher',29.99,3),(30,'Black+Decker','Power drill with 20V battery',50,'BD 20V Drill',89.99,3),(31,'Neutrogena','Moisturizer with SPF 15',200,'Hydro Boost Gel Cream',24.99,4),(32,'Maybelline','Volumizing mascara',150,'Lash Sensational Mascara',9.99,4),(33,'Oral-B','Electric toothbrush with timer',50,'Oral-B Pro 1000',49.99,4),(34,'Philips','Cordless electric shaver',40,'Philips Norelco 3000',79.99,4),(35,'Fitbit','Fitness tracker with heart rate',30,'Fitbit Inspire 3',99.99,4),(36,'Dove','Hair care set with shampoo & conditioner',120,'Dove Daily Moisture',14.99,4),(37,'L’Oreal','Anti-aging face serum',75,'Revitalift Hyaluronic Acid Serum',29.99,4),(38,'Braun','Thermometer with instant read',60,'Braun Ear Thermometer',39.99,4),(39,'Nature Made','Vitamin D3 supplements',150,'Vitamin D3 5000 IU',12.99,4),(40,'Gillette','Men’s shaving razor kit',100,'Fusion5 ProGlide',19.99,4),(41,'Nike','Men’s basketball shoes',50,'Air Jordan 1',149.99,5),(42,'Adidas','Women’s running shoes',75,'Ultraboost 22',179.99,5),(43,'Columbia','Waterproof hiking boots',30,'Newton Ridge Plus',129.99,5),(44,'Yeti','Portable cooler with 26L capacity',40,'Yeti Roadie 24',249.99,5),(45,'Under Armour','Performance workout shirt',120,'UA Tech Shirt',29.99,5),(46,'Wilson','Official size basketball',100,'Wilson Evolution',64.99,5),(47,'Callaway','Golf club set for beginners',20,'Strata Complete Set',499.99,5),(48,'Garmin','GPS smartwatch for outdoor activities',25,'Garmin Instinct 2',299.99,5),(49,'Therm-a-Rest','Self-inflating camping mattress',50,'Trail Pro Sleeping Pad',99.99,5),(50,'Osprey','Hiking backpack with 30L capacity',35,'Osprey Daylite Plus',79.99,5),(51,'Penguin','Classic literature novel',200,'1984 by George Orwell',9.99,6),(52,'HarperCollins','Bestselling fantasy novel',150,'The Hobbit by J.R.R. Tolkien',14.99,6),(53,'Scholastic','Children’s fantasy book',100,'Harry Potter and the Sorcerer’s Stone',12.99,6),(54,'Simon & Schuster','Self-help bestseller',80,'Atomic Habits by James Clear',16.99,6),(55,'Random House','Historical fiction novel',90,'The Nightingale by Kristin Hannah',13.99,6),(56,'Warner Bros.','Blu-ray action movie',50,'The Dark Knight',19.99,6),(57,'Sony','Music album CD',40,'Adele - 30',11.99,6),(58,'EA Sports','Sports video game',60,'FIFA 24',69.99,6),(59,'Hasbro','Strategy board game',75,'Catan',49.99,6),(60,'Kindle','E-book edition of popular novel',500,'The Alchemist by Paulo Coelho',5.99,6),(61,'Lego','Creative building block set',150,'Lego City Police Station',99.99,7),(62,'Mattel','Fashion doll for kids',120,'Barbie Dreamtopia',19.99,7),(63,'Hot Wheels','Set of collectible toy cars',200,'Hot Wheels 20-Car Pack',29.99,7),(64,'Hasbro','Classic action figure',100,'Marvel Spider-Man Figure',14.99,7),(65,'Fisher-Price','Educational baby walker',50,'Laugh & Learn Walker',44.99,7),(66,'Nerf','Foam dart blaster',90,'Nerf Elite 2.0',24.99,7),(67,'Graco','Convertible baby car seat',40,'Graco 4Ever DLX',299.99,7),(68,'Melissa & Doug','Wooden puzzle for toddlers',80,'Farm Animals Puzzle',12.99,7),(69,'Play-Doh','Modeling compound set',150,'Play-Doh Mega Pack',19.99,7),(70,'Chicco','Compact baby stroller',25,'Chicco Bravo Trio',399.99,7);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) DEFAULT NULL,
                        `first_name` varchar(255) DEFAULT NULL,
                        `last_name` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UKcudaqifebe8cfswgy556uuv3x` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'yoany@email.com','Yoany','Bejarano','$2a$12$xcnpyWLOh1uvaaJaxQPJGu2R9lR6ulQsAmydOblAG.jt6jARSL.22');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
                              `user_id` bigint NOT NULL,
                              `role_id` bigint NOT NULL,
                              KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
                              KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`),
                              CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                              CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-26 17:47:50
