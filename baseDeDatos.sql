CREATE DATABASE  IF NOT EXISTS `aplicacion_prueba_tecnica_vtv` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `aplicacion_prueba_tecnica_vtv`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: aplicacion_prueba_tecnica_vtv
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `auto`
--

DROP TABLE IF EXISTS `auto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auto` (
  `auto_id` int NOT NULL,
  `auto_vtv_id` int NOT NULL,
  KEY `auto_id` (`auto_id`),
  KEY `auto_vtv_id` (`auto_vtv_id`),
  CONSTRAINT `auto_ibfk_1` FOREIGN KEY (`auto_id`) REFERENCES `vehiculo` (`vehiculo_id`),
  CONSTRAINT `auto_ibfk_2` FOREIGN KEY (`auto_vtv_id`) REFERENCES `vtv` (`vtv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto`
--

LOCK TABLES `auto` WRITE;
/*!40000 ALTER TABLE `auto` DISABLE KEYS */;
INSERT INTO `auto` VALUES (14,8),(5,12),(19,13),(18,14),(17,15),(7,16),(5,21);
/*!40000 ALTER TABLE `auto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int NOT NULL,
  `email` varchar(50) NOT NULL,
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `persona` (`persona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (3,'vale_1930@hotmail.com'),(8,'paola@hotmail.com'),(9,'marcela@hotmail.com'),(21,'pablo@gmail.com'),(23,'marcelo@hotmail.com'),(24,'roberto@gmail.com'),(27,'juanito@hotmail.com'),(28,'graciela@hotmail.com'),(29,'roko@gmail.com'),(30,'pepe@hotmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspeccion`
--

DROP TABLE IF EXISTS `inspeccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inspeccion` (
  `inspeccion_numero` int NOT NULL AUTO_INCREMENT,
  `fecha` varchar(50) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `exento` tinyint(1) NOT NULL,
  `inspeccion_vehiculo_id` int NOT NULL,
  `inspeccion_inspector_id` int NOT NULL,
  `inspeccion_vtv_id` int NOT NULL,
  PRIMARY KEY (`inspeccion_numero`),
  KEY `inspeccion_vehiculo_id` (`inspeccion_vehiculo_id`),
  KEY `inspeccion_inspector_id` (`inspeccion_inspector_id`),
  KEY `inspeccion_vtv_id` (`inspeccion_vtv_id`),
  CONSTRAINT `inspeccion_ibfk_1` FOREIGN KEY (`inspeccion_vehiculo_id`) REFERENCES `vehiculo` (`vehiculo_id`),
  CONSTRAINT `inspeccion_ibfk_2` FOREIGN KEY (`inspeccion_inspector_id`) REFERENCES `inspector` (`id_inspector`),
  CONSTRAINT `inspeccion_ibfk_3` FOREIGN KEY (`inspeccion_vtv_id`) REFERENCES `vtv` (`vtv_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspeccion`
--

LOCK TABLES `inspeccion` WRITE;
/*!40000 ALTER TABLE `inspeccion` DISABLE KEYS */;
INSERT INTO `inspeccion` VALUES (8,'Thu Apr 13 16:23:20 ART 2023','Apto',0,12,1,6),(9,'Thu Apr 13 16:25:32 ART 2023','Rechazado',0,13,4,7),(10,'Thu Apr 13 16:30:55 ART 2023','Rechazado',0,14,4,8),(11,'Thu Apr 13 18:26:37 ART 2023','Apto',0,15,2,9),(12,'Thu Apr 13 15:58:53 ART 2023','Rechazado',0,5,1,3),(18,'Thu Apr 13 21:00:32 ART 2023','Condicional',1,5,6,12),(19,'Thu Apr 13 21:17:42 ART 2023','Condicional',1,19,26,13),(20,'Thu Apr 13 21:18:11 ART 2023','Condicional',1,18,26,14),(21,'Thu Apr 13 21:18:39 ART 2023','Rechazado',0,17,26,15),(22,'Thu Apr 13 21:19:24 ART 2023','Condicional',1,7,2,16),(27,'Thu Apr 13 22:08:41 ART 2023','Condicional',1,5,1,21);
/*!40000 ALTER TABLE `inspeccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspector`
--

DROP TABLE IF EXISTS `inspector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inspector` (
  `id_inspector` int NOT NULL,
  `celular` double NOT NULL,
  `inspecciones_realizadas` int NOT NULL,
  KEY `id_inspector` (`id_inspector`),
  CONSTRAINT `inspector_ibfk_1` FOREIGN KEY (`id_inspector`) REFERENCES `persona` (`persona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspector`
--

LOCK TABLES `inspector` WRITE;
/*!40000 ALTER TABLE `inspector` DISABLE KEYS */;
INSERT INTO `inspector` VALUES (1,1134153038,0),(2,1166552299,0),(4,1144558245,0),(6,116292022,0),(26,11445522,0);
/*!40000 ALTER TABLE `inspector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca` (
  `marca_id` int NOT NULL AUTO_INCREMENT,
  `marca` varchar(50) NOT NULL,
  `id_tipo_modelo` int DEFAULT NULL,
  PRIMARY KEY (`marca_id`),
  KEY `id_tipo_modelo` (`id_tipo_modelo`),
  CONSTRAINT `marca_ibfk_1` FOREIGN KEY (`id_tipo_modelo`) REFERENCES `modelo` (`modelo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` VALUES (3,'BMW',1),(4,'BMW',2),(5,'BMW',3),(6,'BMW',4),(7,'Mercedes Benz Clase G',5),(8,'Mercedes Benz Clase G',6),(9,'Mercedes Benz Clase G',7),(10,'Audi',8),(11,'Audi',9),(12,'Audi',10),(13,'Audi',11),(14,'Lexus',12),(15,'Lexus',13),(16,'Renault',14),(17,'Renault',15),(18,'Renault',16),(19,'Ford',17),(20,'Ford',18),(21,'Honda',19),(22,'Honda',20),(23,'BMW',21);
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelo`
--

DROP TABLE IF EXISTS `modelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modelo` (
  `modelo_id` int NOT NULL AUTO_INCREMENT,
  `modelo` varchar(50) NOT NULL,
  PRIMARY KEY (`modelo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelo`
--

LOCK TABLES `modelo` WRITE;
/*!40000 ALTER TABLE `modelo` DISABLE KEYS */;
INSERT INTO `modelo` VALUES (1,'BMW X5'),(2,'BMW X3'),(3,'BMW Serie 3'),(4,'BMW Serie 5'),(5,'Mercedes Benz Clase G'),(6,'Mercedes Benz Clase GLC'),(7,'Mercedes Benz Clase C'),(8,'Audi A3'),(9,'Audi A4'),(10,'Audi A6'),(11,'Audi Q5'),(12,'Lexus LC'),(13,'Lexus UX'),(14,'Renault Duster'),(15,'Renault Logan'),(16,'Renault Kangoo II'),(17,'Ford Mustang'),(18,'Ford Kuga'),(19,'Honda Civic'),(20,'Honda CR-V'),(21,'BMW X5');
/*!40000 ALTER TABLE `modelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `observacion`
--

DROP TABLE IF EXISTS `observacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `observacion` (
  `observacion_id` int NOT NULL AUTO_INCREMENT,
  `detalle` varchar(50) DEFAULT NULL,
  `aprobacion` varchar(20) DEFAULT NULL,
  `id_inspector` int NOT NULL,
  PRIMARY KEY (`observacion_id`),
  KEY `id_inspector` (`id_inspector`),
  CONSTRAINT `observacion_ibfk_1` FOREIGN KEY (`id_inspector`) REFERENCES `inspector` (`id_inspector`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `observacion`
--

LOCK TABLES `observacion` WRITE;
/*!40000 ALTER TABLE `observacion` DISABLE KEYS */;
INSERT INTO `observacion` VALUES (1,'Bien','apto',1),(2,'!Todo Correcto','Apto',1),(3,'Varias Fallas','Rechazado',6),(4,'Faltan cosas','Condicional',6),(5,'Bien','Apto',1),(6,'Faltan cosas','Condicional',4),(7,'Muy mal','Rechazado',4),(8,'Todo Bien','Apto',2),(9,'Bien..','Apto',6),(10,'','Apto',6),(11,'','Apto',6),(12,'En condiciones','Apto',26),(13,'Arreglar cosas','Condicional',26),(14,'Faltan bastante','Rechazado',26),(15,'Bien','Apto',2),(16,'Bien!','Apto',1),(17,'a','Apto',1),(18,'','Apto',1),(19,'','Apto',1),(20,'Bien','Apto',1);
/*!40000 ALTER TABLE `observacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `persona_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `edad` int NOT NULL,
  `dni` int NOT NULL,
  PRIMARY KEY (`persona_id`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Gonzalo','Leminia',21,43593947),(2,'Martina','Leminia',18,42593333),(3,'Valeria','Vazquez',44,33593222),(4,'Santino','Gilardi',30,30555321),(6,'Leonardo','Gilardi',44,33222142),(8,'Paola','Vazquez',44,33593111),(9,'Marcela','Gonzalez',50,29333222),(21,'Pablo','Perez',33,33453123),(23,'Marcelo','Gomez',36,56777888),(24,'Roberto','Galiati',50,12333789),(26,'Juanito','Lopez',24,43561111),(27,'Juanito','Lopez',24,43566777),(28,'Graciela','Dalto',60,45890000),(29,'Roko','Gonzalez',18,46345999),(30,'Pepe','Argento',44,21333218);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
  `vehiculo_id` int NOT NULL AUTO_INCREMENT,
  `dominio` varchar(50) NOT NULL,
  `marca_id` int NOT NULL,
  `nombre_propietario` varchar(50) NOT NULL,
  `vehiculo_id_persona` int NOT NULL,
  PRIMARY KEY (`vehiculo_id`),
  KEY `marca_id` (`marca_id`),
  KEY `vehiculo_id_persona` (`vehiculo_id_persona`),
  CONSTRAINT `vehiculo_ibfk_1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`marca_id`),
  CONSTRAINT `vehiculo_ibfk_3` FOREIGN KEY (`vehiculo_id_persona`) REFERENCES `persona` (`persona_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (5,'XX 999 FF',4,'Valeria',3),(7,'RR 42 GGG',22,'Valeria',3),(10,'HH 232 DD',12,'Marcelo',23),(12,'SS 456 GGG',16,'Paola',8),(13,'MM 50 MM',12,'Marcela',9),(14,'BW 11 MMM',23,'Pablo',21),(15,'RB 45 KLA',21,'Roberto',24),(17,'GG 123 JN',8,'Juanito',27),(18,'PE 178 AS',19,'Pepe',30),(19,'RK 99 GZ',22,'Roko',29);
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vtv`
--

DROP TABLE IF EXISTS `vtv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vtv` (
  `vtv_id` int NOT NULL AUTO_INCREMENT,
  `vtv_observaciones_id` int NOT NULL,
  `mediciones` varchar(50) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `vtv_id_auto` int NOT NULL,
  PRIMARY KEY (`vtv_id`),
  KEY `vtv_observaciones_id` (`vtv_observaciones_id`),
  CONSTRAINT `vtv_ibfk_1` FOREIGN KEY (`vtv_observaciones_id`) REFERENCES `observacion` (`observacion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vtv`
--

LOCK TABLES `vtv` WRITE;
/*!40000 ALTER TABLE `vtv` DISABLE KEYS */;
INSERT INTO `vtv` VALUES (2,1,'apto','apto',1),(3,2,'Condicional','Apto',5),(4,3,'Apto','Rechazado',8),(5,4,'Condicional','Condicional',10),(6,5,'Apto','Apto',12),(7,6,'Rechazado','Rechazado',13),(8,7,'Condicional','Rechazado',14),(9,8,'Apto','Apto',15),(10,9,'Rechazado','Rechazado',15),(11,10,'Rechazado','Rechazado',5),(12,11,'Condicional','Condicional',5),(13,12,'Condicional','Condicional',19),(14,13,'Condicional','Condicional',18),(15,14,'Rechazado','Rechazado',17),(16,15,'Condicional','Condicional',7),(17,16,'Condicional','Condicional',12),(18,17,'Apto','Apto',12),(19,18,'Condicional','Condicional',12),(20,19,'Rechazado','Rechazado',12),(21,20,'Condicional','Condicional',5);
/*!40000 ALTER TABLE `vtv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'aplicacion_prueba_tecnica_vtv'
--

--
-- Dumping routines for database 'aplicacion_prueba_tecnica_vtv'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-13 22:30:31
