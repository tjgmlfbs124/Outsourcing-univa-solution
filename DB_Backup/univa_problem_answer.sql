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
-- Table structure for table `problem_answer`
--

DROP TABLE IF EXISTS `problem_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problem_answer` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `answer_id` int unsigned NOT NULL,
  `number` int NOT NULL,
  `text` text,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_problem_answer_answer1_idx` (`answer_id`),
  CONSTRAINT `fk_problem_answer_answer2` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem_answer`
--

LOCK TABLES `problem_answer` WRITE;
/*!40000 ALTER TABLE `problem_answer` DISABLE KEYS */;
INSERT INTO `problem_answer` VALUES (1,1,0,'1번 문제 해결방법','K3XG4sy3xli2UM84395l/img.jpg'),(2,1,1,'2번 문제 해결방법','K3XG4sy3xli2UM84395l/img.jpg'),(3,2,0,'1번문제 해결방법입니다.','K3XG4sy3xli2UM84395l/img.jpg'),(4,2,1,'2번문제 해결방법입니다.','K3XG4sy3xli2UM84395l/img.jpg'),(7,7,0,'ㅎㅎㅎ','3AxKd1sTtfmsr9v2br81/img.png'),(10,9,0,'asdfasdf','2504qnUdzf5Ci83kk958/img.jpg'),(11,10,0,'간다ㅏㄹ마바사아\r\n','9Cirl6V058Q50699PWzr/img.jpg'),(12,11,0,'받아라\r\n이것은 나의 답변!','IVzLCv7ZJ1W12KEwq947/img.jpg'),(13,11,1,'받아라\r\n이것은 나의 두번째 답변!','dNC21813Gx0cAZ0k966t/img.jpg'),(14,12,0,'',NULL),(15,13,0,'',NULL),(16,14,0,'',NULL),(17,15,0,'test','ygpW25Eg858fQfLfRN2v/img.gif'),(18,16,0,'답변입니다',NULL),(19,16,1,'',NULL);
/*!40000 ALTER TABLE `problem_answer` ENABLE KEYS */;
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
