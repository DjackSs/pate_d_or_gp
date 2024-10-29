-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: pate_d_or
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cards` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` VALUES (1,'Collection Hiver'),(2,'Saveurs de Printemps'),(3,'Délices d\'Automne'),(4,'Spécialités d\'été');
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dishes`
--

DROP TABLE IF EXISTS `dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `description` varchar(250) NOT NULL,
  `category` varchar(30) NOT NULL,
  `id_card` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_card` (`id_card`),
  CONSTRAINT `dishes_ibfk_1` FOREIGN KEY (`id_card`) REFERENCES `cards` (`id`) ON DELETE CASCADE,
  CONSTRAINT `dishes_chk_1` CHECK ((`price` > 0)),
  CONSTRAINT `dishes_chk_2` CHECK ((`category` in (_utf8mb4'entry',_utf8mb4'dish',_utf8mb4'desert',_utf8mb4'beverage')))
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes`
--

LOCK TABLES `dishes` WRITE;
/*!40000 ALTER TABLE `dishes` DISABLE KEYS */;
INSERT INTO `dishes` VALUES (1,'Spaghetti Bolognese',18.00,'Pâtes italiennes classiques avec une sauce à la viande riche','dish',1),(2,'Filet Mignon',28.00,'Steak de boeuf tendre, parfaitement cuit selon vos préférences','dish',2),(3,'Martini Royale',12.00,'Un mélange sophistiqué de vodka et de vermouth','beverage',3),(4,'Mousse au Chocolat',10.00,'Délice au chocolat fondant, une fin sucrée pour votre repas','desert',4),(5,'Panna Cotta à la Vanille',9.00,'Dessert à la vanille crémeux avec compote de baies','desert',1),(6,'Ratatouille Proven�ale',22.00,'Un mélange de légumes frais dans une sauce tomate savoureuse','dish',2),(7,'Salade César',14.00,'Laitue romaine croquante, fromage parmesan et vinaigrette César','entry',3),(8,'Gratin Dauphinois',18.00,'Gratin de pommes de terre crémeux avec fromage fondu, un classique français','dish',4),(9,'Old Fashioned',15.00,'Un cocktail intemporel avec bourbon, sucre et bitter','beverage',1),(10,'Limonade Pétillante',8.00,'Limonade rafraichissante avec une touche d\'effervescence','beverage',2),(11,'Salade de Chêvre',12.00,'Salade mixte garnie de fromage de chêvre chaud et vinaigrette balsamique','entry',3),(12,'Confit de Canard',24.00,'Cuisse de canard cuite lentement avec une peau croustillante et une viande tendre','dish',4),(13,'Kebab d\'Agneau',20.00,'Brochettes d\'agneau marinées grillées, un délice savoureux','dish',1),(14,'Tarte aux Pommes',10.00,'Tarte aux pommes maison avec une croute feuilletée, servie chaude','desert',2),(15,'Porc Rôti aux Carottes',22.00,'Porc rôti tendre avec des carottes glacées, un plat copieux','dish',3);
/*!40000 ALTER TABLE `dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `object` varchar(100) NOT NULL,
  `content` varchar(250) NOT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'Feedback Qualité','Merci pour l\'expérience culinaire exceptionnelle! La nourriture était exceptionnelle.',NULL),(2,'Demande de Menu','Pourriez-vous fournir des informations sur les options végétariennes dans le menu?',NULL),(3,'27/04/2024 | Expérience Gastronomique','hello',2);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `state` char(4) DEFAULT NULL,
  `id_table` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_table` (`id_table`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`id_table`) REFERENCES `tables` (`id`) ON DELETE CASCADE,
  CONSTRAINT `orders_chk_1` CHECK ((`state` in (NULL,_utf8mb4'take',_utf8mb4'read',_utf8mb4'serv',_utf8mb4'sold')))
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (187,'read',3),(188,'sold',4),(189,'sold',5);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_dishes`
--

DROP TABLE IF EXISTS `orders_dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_dishes` (
  `id_order` int NOT NULL,
  `id_dish` int NOT NULL,
  KEY `id_order` (`id_order`),
  KEY `id_dish` (`id_dish`),
  CONSTRAINT `orders_dishes_ibfk_1` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  CONSTRAINT `orders_dishes_ibfk_2` FOREIGN KEY (`id_dish`) REFERENCES `dishes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_dishes`
--

LOCK TABLES `orders_dishes` WRITE;
/*!40000 ALTER TABLE `orders_dishes` DISABLE KEYS */;
INSERT INTO `orders_dishes` VALUES (187,6),(188,15),(189,1),(189,13),(189,13),(189,13);
/*!40000 ALTER TABLE `orders_dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reservation_time` datetime NOT NULL,
  `state` char(4) NOT NULL DEFAULT 'hold',
  `id_table` int DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_table` (`id_table`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`id_table`) REFERENCES `tables` (`id`),
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  CONSTRAINT `reservations_chk_1` CHECK ((`state` in (_utf8mb4'hold',_utf8mb4'gran',_utf8mb4'deni',_utf8mb4'here')))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,'2024-04-29 13:00:00','gran',5,2);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurants`
--

DROP TABLE IF EXISTS `restaurants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(60) NOT NULL,
  `postal_code` char(5) NOT NULL,
  `town` varchar(40) NOT NULL,
  `id_card` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_card` (`id_card`),
  CONSTRAINT `restaurants_ibfk_1` FOREIGN KEY (`id_card`) REFERENCES `cards` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurants`
--

LOCK TABLES `restaurants` WRITE;
/*!40000 ALTER TABLE `restaurants` DISABLE KEYS */;
INSERT INTO `restaurants` VALUES (1,'Expérience Gastronomique','123 Rue élégante','44000','NANTES',1),(2,'Fusion Gastronomique','456 Avenue Culinaire','44000','NANTES',2),(3,'Bistrot Méditerranéen','789 Chemin Savoureux','44000','NANTES',3),(4,'Cuisine Contemporaine','101 Place Moderne','44000','NANTES',4);
/*!40000 ALTER TABLE `restaurants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedules`
--

DROP TABLE IF EXISTS `schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedules` (
  `id` int NOT NULL AUTO_INCREMENT,
  `open_hour` time NOT NULL,
  `close_hour` time NOT NULL,
  `id_restaurant` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_restaurant` (`id_restaurant`),
  CONSTRAINT `schedules_ibfk_1` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `schedules_chk_1` CHECK ((`close_hour` > `open_hour`))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedules`
--

LOCK TABLES `schedules` WRITE;
/*!40000 ALTER TABLE `schedules` DISABLE KEYS */;
INSERT INTO `schedules` VALUES (1,'12:00:00','14:30:00',1),(2,'18:00:00','22:30:00',1),(3,'11:30:00','15:00:00',2),(4,'12:15:00','15:00:00',3),(5,'12:00:00','14:30:00',4);
/*!40000 ALTER TABLE `schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tables`
--

DROP TABLE IF EXISTS `tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tables` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number_place` int NOT NULL,
  `state` char(4) DEFAULT NULL,
  `id_restaurant` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_restaurant` (`id_restaurant`),
  CONSTRAINT `tables_ibfk_1` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tables_chk_1` CHECK ((`state` in (NULL,_utf8mb4'pres')))
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tables`
--

LOCK TABLES `tables` WRITE;
/*!40000 ALTER TABLE `tables` DISABLE KEYS */;
INSERT INTO `tables` VALUES (3,6,'pres',2),(4,8,'pres',3),(5,6,'pres',1),(6,6,NULL,1),(7,4,NULL,1),(8,4,NULL,1),(9,4,NULL,1),(10,4,NULL,1),(11,2,NULL,1),(12,2,NULL,1);
/*!40000 ALTER TABLE `tables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `lastname` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `expiration_time` datetime DEFAULT NULL,
  `role` char(4) NOT NULL DEFAULT 'cust',
  PRIMARY KEY (`id`),
  CONSTRAINT `users_chk_1` CHECK ((`role` in (_utf8mb4'cust',_utf8mb4'admi',_utf8mb4'staf')))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Johnny','Johnson','john@mail.fr','$2a$10$yOBVvVbzRnEBNfpzoCo0KuJlufhoTpIUnoNGi4c9DjXMsDL..p9uK','PyxDg-Vsr5RAL9CX-wdfzGhwDmUaMV-awwOncnydVIcehE6DdPhUQCXYS-7yLuyZ','2024-06-17 22:24:39','staf'),(2,'Johnny','Johnson-john','John@Johnson.fr','dd724a1092b7ba6fd5e1cfa1163ae98d91c744510681dd698925cb04f6056914b2478517e8d4bb1b60f075651f76fd68d811abb7bab5ed1eb91f928963efd1d9',NULL,NULL,'cust'),(4,'John','Johnson','john@mail.fr','$2a$10$SGjCwpQtqkgpMy6acz0e1uiBPnUQRvGl3HgxSWzDG3QRXwJIjuzOO','Tw7GlftzHWnm8LKB-eblWurXgcvf9Oi6_Ck2EMy8nmz1uETvC81eGZ7bdrx9h9M8','2024-06-17 22:45:54','staf');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-27 11:48:50
