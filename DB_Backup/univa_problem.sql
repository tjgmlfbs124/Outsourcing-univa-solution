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
-- Table structure for table `problem`
--

DROP TABLE IF EXISTS `problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problem` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `question_id` int unsigned NOT NULL,
  `number` int unsigned NOT NULL,
  `text` text,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_problem_question1_idx` (`question_id`),
  CONSTRAINT `fk_problem_question1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem`
--

LOCK TABLES `problem` WRITE;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` VALUES (18,174,0,NULL,NULL),(21,177,0,NULL,NULL),(31,187,0,'txt1',''),(32,187,1,'txt2',''),(33,188,0,'txt1','I209H2Y95lQIXFv0BY2c/img.png'),(34,188,1,'text123123','I209H2Y95lQIXFv0BY2c/img.png'),(37,190,0,'저런문제','I209H2Y95lQIXFv0BY2c/img.png'),(38,190,1,'이런 문제!','K3XG4sy3xli2UM84395l/img.jpg'),(40,201,0,'ㅁㄴㅇㄻㄴㅇㄹㄴㄹ','7x1Jx9ehZf6k9vnzot66/img.jpg'),(41,202,0,'폭풍\r\n푸른','S3j5X83q8s8Qv2q7ORHN/img.png'),(42,203,0,'파라드롭','2DF1Ie10Y54n7h7cUWaQ/img.png'),(43,204,0,'메인\r\n문제2','69H75CDSF82G45bQ6B81/img.png'),(44,205,0,'간다간다\r\n뿅뿅간다\r\n알라리알라리','kTk3o5wUEFJ68T87qEc8/img.jpg'),(45,206,0,'gif?','9i53lE9V3UiPM1Yu8vkx/img.gif'),(63,224,0,'','6e7YG5mJW7fLz47m8I4P/img.jpg'),(64,225,0,'질문 입니다','L88tfRc8GOIH32A8eAu3/img.png'),(65,226,0,'질문 내용 설정','2JIHDKYDiB3451fZQr70/img.png'),(66,227,0,'질문 내용 1-1','XtO9669VmFe6IDkM3YUK/img.png'),(67,228,0,'','v1pPN0ck3jg5TZF7Zeoc/img.png'),(68,231,0,'',NULL),(69,232,0,'',NULL),(70,233,0,'',NULL),(71,236,0,'질문1','Okhve1BM3MAtMC6NB3ol/img.png'),(72,236,1,'질문2','ZfpJyIU9g3uvCxx8aCA1/img.png'),(73,237,0,'질문~','13PxZ3L5v648BvE9BWO7/img.png'),(74,239,0,'',NULL),(75,240,0,'',NULL),(76,241,0,'ㅇㅇ',NULL),(77,242,0,'777',NULL),(78,248,0,'',NULL),(79,249,0,'',NULL),(80,250,0,'999',NULL),(81,251,0,'',NULL),(82,251,1,'',NULL),(83,252,0,'',NULL);
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-07 16:40:15
