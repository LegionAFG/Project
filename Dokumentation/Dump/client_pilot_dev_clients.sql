CREATE DATABASE  IF NOT EXISTS `client_pilot_dev` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `client_pilot_dev`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: client_pilot_dev
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birthdate` date DEFAULT NULL,
  `firstname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lastname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nationality` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `relationship` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (17,'1989-07-01','Max','Divers','Müller','Frankreich','Geschieden'),(18,'1972-01-01','Anna','Divers','Schmidt','Österreich','Geschieden'),(19,'1976-01-08','Paul','Divers','Schneider','Frankreich','Verwitwet'),(20,'1975-10-08','Laura','Weiblich','Fischer','Afghanistan','Verheiratet'),(21,'2002-11-04','Lukas','Männlich','Weber','Türkei','Ledig'),(22,'2002-05-16','Sophie','Männlich','Meyer','Türkei','Ledig'),(23,'2000-03-28','Felix','Weiblich','Wagner','Kanada','Verwitwet'),(24,'1982-01-09','Mia','Divers','Becker','Österreich','Verwitwet'),(25,'1970-11-21','Leon','Weiblich','Hoffmann','Frankreich','Verwitwet'),(26,'2002-03-02','Emma','Männlich','Schulz','Afghanistan','Geschieden'),(27,'1979-11-12','Jonas','Weiblich','Koch','USA','Verwitwet'),(28,'1980-10-03','Lena','Männlich','Bauer','Deutschland','Verwitwet'),(29,'1978-05-02','Tim','Divers','Richter','Deutschland','Ledig'),(30,'1988-07-22','Lea','Divers','Klein','Türkei','Ledig'),(31,'1973-12-30','Moritz','Divers','Wolf','Spanien','Geschieden'),(32,'1987-11-15','Hannah','Männlich','Schröder','Kanada','Verheiratet'),(33,'1981-12-07','Anton','Männlich','Neumann','Kanada','Geschieden'),(34,'1975-06-19','Marie','Divers','Schwarz','Österreich','Verheiratet'),(35,'1986-02-12','Luis','Divers','Böhm','Frankreich','Verheiratet'),(36,'1974-10-10','Emily','Divers','Zimmermann','Schweiz','Verwitwet'),(37,'1982-08-21','Tom','Weiblich','Hofmann','Spanien','Verwitwet'),(38,'1984-11-16','Lara','Männlich','Schmitt','Kanada','Ledig'),(39,'1971-06-25','Ben','Divers','Winkler','Österreich','Verheiratet'),(40,'1998-04-15','Nina','Männlich','Krüger','USA','Ledig'),(41,'1979-09-26','Niklas','Divers','Krause','Schweiz','Verheiratet'),(42,'1989-05-17','Clara','Männlich','Stein','Deutschland','Verheiratet'),(43,'1976-02-12','Julian','Männlich','Meier','Deutschland','Verheiratet'),(44,'1990-06-30','Sarah','Divers','Lange','Österreich','Verwitwet'),(45,'1983-12-08','Philipp','Weiblich','Ortmann','Frankreich','Verwitwet'),(46,'1972-03-22','Julia','Divers','Fuchs','Schweiz','Ledig'),(47,'1978-07-05','Eric','Weiblich','Böhm','Spanien','Geschieden'),(48,'1992-04-28','Lisa','Männlich','Schumacher','Österreich','Verheiratet'),(49,'1977-08-20','Chris','Weiblich','Heinz','Kanada','Verwitwet'),(50,'1988-09-30','Mara','Männlich','Horn','Deutschland','Verwitwet'),(51,'1986-02-10','David','Männlich','Mayer','USA','Verheiratet'),(52,'1974-05-01','Nora','Männlich','Graf','USA','Geschieden'),(53,'2002-05-01','Oliver','Männlich','Engel','Schweiz','Verheiratet'),(54,'1985-02-10','Paula','Männlich','Pohl','Spanien','Verheiratet'),(55,'1977-08-01','Marco','Weiblich','Walter','Österreich','Ledig'),(56,'1972-10-28','Kim','Divers','Beck','Frankreich','Verheiratet'),(57,'1999-04-28','Daniel','Männlich','Albrecht','Kanada','Ledig'),(58,'1995-02-18','Livia','Divers','Bender','Türkei','Ledig'),(59,'1979-07-04','Simon','Weiblich','Bachmann','Österreich','Verheiratet'),(60,'1977-09-26','Theresa','Weiblich','Brandt','Spanien','Geschieden'),(61,'1976-02-12','Tobias','Männlich','Roth','Schweiz','Ledig'),(62,'1975-01-04','Franziska','Männlich','Jansen','Deutschland','Verheiratet'),(63,'1992-04-13','Kevin','Männlich','Schuster','Spanien','Verheiratet'),(64,'1987-02-14','Melina','Weiblich','Schilling','Schweiz','Verheiratet'),(65,'1994-01-25','Jan','Divers','Simon','USA','Ledig'),(66,'1994-01-11','Amelie','Männlich','Peters','Türkei','Verheiratet'),(67,'1989-07-01','Max','Divers','Müller','Frankreich','Geschieden'),(68,'1972-01-01','Anna','Divers','Schmidt','Österreich','Geschieden'),(69,'1976-01-08','Paul','Divers','Schneider','Frankreich','Verwitwet'),(70,'1975-10-08','Laura','Weiblich','Fischer','Afghanistan','Verheiratet'),(71,'2002-11-04','Lukas','Männlich','Weber','Türkei','Ledig'),(72,'2002-05-16','Sophie','Männlich','Meyer','Türkei','Ledig'),(73,'2000-03-28','Felix','Weiblich','Wagner','Kanada','Verwitwet'),(74,'1982-01-09','Mia','Divers','Becker','Österreich','Verwitwet'),(75,'1970-11-21','Leon','Weiblich','Hoffmann','Frankreich','Verwitwet'),(76,'2002-03-02','Emma','Männlich','Schulz','Afghanistan','Geschieden'),(77,'1979-11-12','Jonas','Weiblich','Koch','USA','Verwitwet'),(78,'1980-10-03','Lena','Männlich','Bauer','Deutschland','Verwitwet'),(79,'1978-05-02','Tim','Divers','Richter','Deutschland','Ledig'),(80,'1988-07-22','Lea','Divers','Klein','Türkei','Ledig'),(81,'1973-12-30','Moritz','Divers','Wolf','Spanien','Geschieden'),(82,'1987-11-15','Hannah','Männlich','Schröder','Kanada','Verheiratet'),(83,'1981-12-07','Anton','Männlich','Neumann','Kanada','Geschieden'),(84,'1975-06-19','Marie','Divers','Schwarz','Österreich','Verheiratet'),(85,'1986-02-12','Luis','Divers','Böhm','Frankreich','Verheiratet'),(86,'1974-10-10','Emily','Divers','Zimmermann','Schweiz','Verwitwet'),(87,'1982-08-21','Tom','Weiblich','Hofmann','Spanien','Verwitwet'),(88,'1984-11-16','Lara','Männlich','Schmitt','Kanada','Ledig'),(89,'1971-06-25','Ben','Divers','Winkler','Österreich','Verheiratet'),(90,'1998-04-15','Nina','Männlich','Krüger','USA','Ledig'),(91,'1979-09-26','Niklas','Divers','Krause','Schweiz','Verheiratet'),(92,'1989-05-10','Clara','Divers','Stein','Deutschland','Geschieden'),(93,'1976-02-12','Julian','Männlich','Meier','Deutschland','Verheiratet'),(94,'1990-06-30','Sarah','Divers','Lange','Österreich','Verwitwet'),(95,'1983-12-08','Philipp','Weiblich','Ortmann','Frankreich','Verwitwet'),(96,'1972-03-22','Julia','Divers','Fuchs','Schweiz','Ledig'),(97,'1978-07-05','Eric','Weiblich','Böhm','Spanien','Geschieden'),(98,'1992-04-28','Lisa','Männlich','Schumacher','Österreich','Verheiratet'),(99,'1977-08-20','Chris','Weiblich','Heinz','Kanada','Verwitwet'),(100,'1988-09-30','Mara','Männlich','Horn','Deutschland','Verwitwet'),(101,'1986-02-10','David','Männlich','Mayer','USA','Verheiratet'),(102,'1974-05-01','Nora','Männlich','Graf','USA','Geschieden'),(103,'2002-05-01','Oliver','Männlich','Engel','Schweiz','Verheiratet'),(104,'1985-02-10','Paula','Männlich','Pohl','Spanien','Verheiratet'),(105,'1977-08-01','Marco','Weiblich','Walter','Österreich','Ledig'),(106,'1972-10-28','Kim','Divers','Beck','Frankreich','Verheiratet'),(107,'1999-04-28','Daniel','Männlich','Albrecht','Kanada','Ledig'),(108,'1995-02-18','Livia','Divers','Bender','Türkei','Ledig'),(109,'1979-07-04','Simon','Weiblich','Bachmann','Österreich','Verheiratet'),(110,'1977-09-26','Theresa','Weiblich','Brandt','Spanien','Geschieden'),(111,'1976-02-12','Tobias','Männlich','Roth','Schweiz','Ledig'),(112,'1975-01-04','Franziska','Männlich','Jansen','Deutschland','Verheiratet'),(113,'1992-04-13','Kevin','Männlich','Schuster','Spanien','Verheiratet'),(114,'1987-02-14','Melina','Weiblich','Schilling','Schweiz','Verheiratet'),(115,'1994-01-25','Jan','Divers','Simon','USA','Ledig'),(116,'1994-01-11','Amelie','Männlich','Peters','Türkei','Verheiratet');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-11 23:37:16
