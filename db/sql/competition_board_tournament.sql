-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: competition_board
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tournament`
--

DROP TABLE IF EXISTS `tournament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament` (
  `tournament_id` int(11) NOT NULL AUTO_INCREMENT,
  `tournament_name` varchar(45) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `tournament_description` text,
  `tournament_format_id` int(11) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`tournament_id`),
  UNIQUE KEY `tournament_name_UNIQUE` (`tournament_name`),
  KEY `manager_id_idx` (`manager_id`),
  KEY `fk_tournament_1_idx` (`tournament_format_id`),
  CONSTRAINT `FK_manager_id` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`manager_id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_tournament_1` FOREIGN KEY (`tournament_format_id`) REFERENCES `tournament_format` (`format_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament`
--

LOCK TABLES `tournament` WRITE;
/*!40000 ALTER TABLE `tournament` DISABLE KEYS */;
INSERT INTO `tournament` VALUES (207,'TestTournament','2016-09-13 01:26:00','2016-09-13 01:26:00','Yerevan, Armenia','bla bla bla',1,104),(208,'user_3 tournament','2016-09-13 06:59:00','2016-09-13 06:59:00','Yerevan, Armenia','bla bla blabla bla bla',2,105),(209,'ches 2016','2016-09-14 03:12:00','2016-09-14 03:12:00','Yerevan, Armenia','bla bla bla',1,104),(211,'chess 2016','2016-09-16 01:47:00','2016-09-16 01:47:00','Yerevan','bla bla bla',1,104),(212,'football 2017','2016-09-16 01:47:00','2016-09-16 01:47:00','Yerevan, Armenia','bla bla bla',NULL,104),(213,'fifa 2020','2016-09-16 01:47:00','2016-09-16 01:47:00','Yerevan, Armenia','bla bla bla',NULL,104),(214,'fifa2020','2016-09-16 01:47:00','2016-09-16 01:47:00','Yerevan, Armenia','bla bla bla',NULL,104),(215,'fifa','2016-09-16 01:47:00','2016-09-16 01:47:00','Yerevan, Armenia','bla bla bla',NULL,104),(216,'batmiton','2016-09-16 01:47:00','2016-09-16 01:47:00','Yerevan, Armenia','bla bla bla',NULL,104),(217,'valeybol','2016-09-16 01:47:00','2016-09-16 01:47:00','Yerevan, Armenia','bla bla bla',NULL,104);
/*!40000 ALTER TABLE `tournament` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-16 18:47:36
