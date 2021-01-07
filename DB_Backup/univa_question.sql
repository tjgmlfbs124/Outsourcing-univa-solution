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
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `visible` int unsigned NOT NULL DEFAULT '1',
  `title` varchar(255) NOT NULL,
  `content` text,
  `upload_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `limit_date` datetime DEFAULT NULL,
  `manager_id` int unsigned DEFAULT NULL,
  `state` int unsigned NOT NULL DEFAULT '1',
  `score` int unsigned NOT NULL DEFAULT '0',
  `review` text,
  `user_id` int unsigned DEFAULT '0',
  `isNew` int DEFAULT '0',
  `isDetail` int DEFAULT '0',
  `language` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_manager1_idx` (`manager_id`),
  KEY `fk_question_question_state1_idx` (`state`),
  KEY `fk_question_user1` (`user_id`),
  CONSTRAINT `fk_question_manager2` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_question_question_state2` FOREIGN KEY (`state`) REFERENCES `question_state` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_question_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET DEFAULT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (174,1,'title3333333333','','2020-11-06 13:03:22','2020-01-01 23:59:59',NULL,4,0,NULL,1,0,0,'default'),(177,1,'ggg','ggg','2020-11-06 13:24:57','2020-01-01 23:59:59',NULL,4,0,NULL,1,0,0,'default'),(187,1,'title','content','2020-11-06 18:22:55',NULL,1,4,0,NULL,1,0,0,'default'),(188,1,'title233333','content2','2020-11-06 18:26:41',NULL,NULL,5,4,'와 이건 짱이야 짱짱\r\n짱짱',1,0,0,'default'),(190,1,'제목33333','이런 내용','2020-11-10 10:11:29',NULL,NULL,5,5,'요호',1,0,0,'default'),(201,1,'이것은 룡삼이 제목','질문질문','2020-11-19 14:23:45',NULL,NULL,5,4,'이것은 리뷰입니다',4,0,0,'default'),(202,1,'푸른폭풍','푸른\r\n폭풍','2020-11-20 13:54:12',NULL,NULL,2,0,NULL,6,0,0,'default'),(203,1,'메인제목','메인질문','2020-11-20 14:03:47',NULL,NULL,2,0,NULL,1,0,0,'default'),(204,1,'메인제목2','메인\r\n질문2','2020-11-20 14:04:33',NULL,NULL,2,0,NULL,1,0,0,'default'),(205,1,'제목제목','내용내용\r\n내용내용\r\n내용ㅇ','2020-11-20 14:36:39',NULL,NULL,4,0,NULL,7,0,0,'default'),(206,1,'테스트 타이틀','테스트 컨텐츠','2020-11-20 16:57:03',NULL,NULL,2,0,NULL,1,0,0,'default'),(224,1,'마지막 테스트 질문','ㅁㄴㅇㄻㄴㅇㄹ','2020-11-24 16:41:45',NULL,NULL,2,0,NULL,4,1,1,'default'),(225,1,'제목 입니다','대표 질문 입니다','2020-11-27 14:30:21',NULL,NULL,2,0,NULL,2,0,1,'default'),(226,1,'대표 제목 설정','대표 질문 설정','2020-11-27 14:32:30',NULL,NULL,2,0,NULL,2,1,0,'default'),(227,1,'질문 제목 1','질문 내용1','2020-11-27 14:35:09',NULL,NULL,2,0,NULL,2,1,1,'default'),(228,1,'eee','','2020-12-16 14:31:00',NULL,NULL,2,0,NULL,19,0,0,'default'),(231,1,'제모옥~','','2020-12-16 15:29:13',NULL,NULL,2,0,NULL,20,0,0,'default'),(232,1,'제목22','','2020-12-16 15:30:50',NULL,NULL,2,0,NULL,21,0,0,'default'),(233,1,'','','2020-12-16 15:31:20',NULL,NULL,2,0,NULL,22,0,0,'default'),(236,1,'제목123','내용','2020-12-17 10:33:25',NULL,NULL,2,0,NULL,23,1,1,'default'),(237,1,'제목333','내용333','2020-12-17 10:37:20',NULL,NULL,2,0,NULL,24,1,1,'default'),(239,1,'제목444','내용444','2020-12-17 10:43:21',NULL,NULL,2,0,NULL,25,0,0,'default'),(240,1,'제목555','내용555','2020-12-17 10:43:44',NULL,NULL,2,0,NULL,26,0,0,'default'),(241,1,'제목666','내용666','2020-12-17 10:44:12',NULL,NULL,5,5,'',27,0,0,'default'),(242,1,'제목777','내용777','2020-12-17 10:45:28',NULL,NULL,4,0,NULL,28,1,1,'default'),(248,1,'','','2020-12-17 13:56:39',NULL,NULL,2,0,NULL,29,0,0,'default'),(249,1,'이것은 무엇인가요','간다간다뿅간다\r\n아싸ㅓ리아싸리','2020-12-17 16:48:57','2020-12-30 23:59:59',NULL,5,5,'최고야',4,1,1,'KO'),(250,1,'제목999','내용999','2020-12-17 17:23:54',NULL,NULL,5,5,'good',30,1,1,'default'),(251,1,'안녕하세요','test_20210105','2021-01-05 15:58:46','2021-08-18 23:59:59',NULL,2,0,NULL,31,0,1,'KO'),(252,1,'20210106_test','20210106_test','2021-01-06 14:09:19','2020-01-01 23:59:59',NULL,4,0,NULL,32,0,1,'Default');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-07 16:40:14
