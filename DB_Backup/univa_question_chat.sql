-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 192.168.0.8    Database: univa
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `question_chat`
--

DROP TABLE IF EXISTS `question_chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_chat` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `question_id` int unsigned NOT NULL,
  `writer` int NOT NULL,
  `content` varchar(255) NOT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `image_url` varchar(255) DEFAULT NULL,
  `is_read` int NOT NULL DEFAULT '0',
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_chat_question1_idx` (`question_id`),
  CONSTRAINT `fk_question_chat_question1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_chat`
--

LOCK TABLES `question_chat` WRITE;
/*!40000 ALTER TABLE `question_chat` DISABLE KEYS */;
INSERT INTO `question_chat` VALUES (42,187,2,'ddd\n','2020-11-20 11:15:47',NULL,1,'CHAT'),(43,187,1,'gggg','2020-11-20 11:15:52',NULL,1,'CHAT'),(44,187,1,'08YxwMiEneY8oGh7nZCi/img.png','2020-11-20 11:15:56',NULL,1,'IMG'),(45,187,0,'ddd\n','2020-11-20 11:17:00',NULL,1,'CHAT'),(46,201,0,'첫번째 질문','2020-11-20 11:44:17',NULL,1,'CHAT'),(47,201,2,'123123','2020-11-20 11:52:25',NULL,1,'CHAT'),(48,201,2,'13','2020-11-20 11:53:39',NULL,1,'CHAT'),(49,201,2,'123123','2020-11-20 12:33:39',NULL,1,'CHAT'),(50,201,2,'123123','2020-11-20 12:35:09',NULL,1,'CHAT'),(51,201,2,'Te6xkplE1g2BP8Lax4E4/img.jpg','2020-11-20 12:41:00',NULL,1,'IMG'),(52,201,2,'123123','2020-11-20 12:45:09',NULL,1,'CHAT'),(53,201,2,'123123','2020-11-20 12:45:13',NULL,1,'CHAT'),(54,201,1,'123123','2020-11-20 12:45:29',NULL,1,'CHAT'),(55,201,2,'5fe7FgE9g2g12c49NnrD/img.jpg','2020-11-20 12:50:44',NULL,1,'IMG'),(56,201,2,'123123','2020-11-20 12:52:17',NULL,1,'CHAT'),(57,201,2,'124124','2020-11-20 12:52:21',NULL,1,'CHAT'),(58,201,1,'123123','2020-11-20 12:52:58',NULL,1,'CHAT'),(59,201,1,'123123','2020-11-20 12:53:00',NULL,1,'CHAT'),(60,201,1,'123123','2020-11-20 12:53:01',NULL,1,'CHAT'),(61,201,1,'123123','2020-11-20 12:53:44',NULL,1,'CHAT'),(62,201,1,'123123','2020-11-20 12:54:17',NULL,1,'CHAT'),(63,201,1,'123123','2020-11-20 12:54:19',NULL,1,'CHAT'),(64,201,2,'123123','2020-11-20 12:55:23',NULL,1,'CHAT'),(65,201,1,'123123','2020-11-20 12:55:27',NULL,1,'CHAT'),(66,201,2,'나는 운영자','2020-11-20 12:57:46',NULL,1,'CHAT'),(67,201,1,'나는 고객','2020-11-20 12:57:52',NULL,1,'CHAT'),(68,190,0,'dd','2020-11-20 13:07:47',NULL,1,'CHAT'),(69,205,0,'asdf\nasdfasdf','2020-11-20 14:40:48',NULL,1,'CHAT'),(70,205,2,'가나다라\n마바사아','2020-11-20 14:57:38',NULL,1,'CHAT'),(71,206,1,'뭘바','2020-11-20 16:57:29',NULL,1,'CHAT'),(72,227,2,'gg','2020-11-27 14:47:04',NULL,1,'CHAT'),(73,227,1,'hi~','2020-11-27 14:47:08',NULL,1,'CHAT'),(74,227,2,'항상~','2020-12-11 16:06:23',NULL,1,'CHAT'),(75,227,0,'기다린다고 말할까~','2020-12-11 16:06:30',NULL,1,'CHAT'),(76,226,0,'rkskekfk\n','2020-12-16 17:15:25',NULL,1,'CHAT'),(77,224,1,'asdf','2020-12-16 17:20:45',NULL,1,'CHAT'),(78,224,1,'avyKjroeGgGYTpDJ07RF/img.png','2020-12-16 17:42:13',NULL,1,'IMG'),(79,224,1,'SD','2020-12-16 17:42:24',NULL,1,'CHAT'),(80,233,2,'M4kJG5b6t4gqFK5BZa3N/img.png','2020-12-17 09:53:54',NULL,1,'IMG'),(81,232,2,'3621QcTFgEO00J4MT7f0/img.png','2020-12-17 09:54:06',NULL,1,'IMG'),(82,232,0,'STN55kHRiy3d73rR1mIc/img.jpg','2020-12-17 09:57:43',NULL,1,'IMG'),(83,232,0,'lLSZ232nUo0Ai2s9l9sp/img.jpg','2020-12-17 09:57:49',NULL,1,'IMG'),(84,232,2,'안녕하세요\n','2020-12-17 09:58:37',NULL,1,'CHAT'),(85,232,0,'반갑습니다','2020-12-17 09:58:50',NULL,1,'CHAT'),(86,242,0,'hello\n','2020-12-17 13:28:04',NULL,1,'CHAT'),(87,242,0,'8N5NuxxZ71aUBxc3ptAO/img.png','2020-12-17 14:08:09',NULL,1,'IMG'),(88,242,0,'6ze7onjN713828k8B23N/img.jpg','2020-12-17 15:09:11',NULL,1,'IMG'),(89,242,0,'H87Ue30VKwSfJyV5D8N6/img.jpg','2020-12-17 15:09:16',NULL,1,'IMG'),(90,249,1,'안녕하세요\n','2020-12-17 16:49:24',NULL,1,'CHAT'),(91,249,2,'안녕하세요','2020-12-17 16:50:05',NULL,1,'CHAT'),(92,249,2,'오류가있습니다','2020-12-17 16:50:42',NULL,1,'CHAT'),(93,249,2,'안녕하세요\n','2020-12-17 16:54:04',NULL,1,'CHAT'),(94,249,1,'네 안녕하세요','2020-12-17 16:54:08',NULL,1,'CHAT'),(95,250,0,'hhh\n','2020-12-30 16:31:55',NULL,1,'CHAT'),(96,188,1,'test','2021-01-05 13:24:04',NULL,1,'CHAT'),(97,251,0,'test','2021-01-06 11:56:54',NULL,1,'CHAT'),(98,190,1,'test','2021-01-06 11:58:05',NULL,1,'CHAT'),(99,190,2,'답장\n','2021-01-06 11:59:36',NULL,1,'CHAT'),(100,190,2,'답장_2','2021-01-06 13:06:18',NULL,1,'CHAT'),(101,252,1,'질문입니다\n','2021-01-06 17:12:36',NULL,1,'CHAT'),(102,252,2,'답변입니다','2021-01-06 17:12:41',NULL,1,'CHAT');
/*!40000 ALTER TABLE `question_chat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-07 16:40:13
