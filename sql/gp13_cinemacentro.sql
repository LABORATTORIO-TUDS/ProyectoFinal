-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: localhost    Database: gp13_cinemacentro
-- ------------------------------------------------------
-- Server version	8.0.43-0ubuntu0.24.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asiento`
--

DROP TABLE IF EXISTS `asiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiento` (
  `codAsiento` int NOT NULL AUTO_INCREMENT,
  `fila` varchar(5) COLLATE utf8mb4_general_ci NOT NULL,
  `numero` int NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT '0',
  `codProyeccion` int NOT NULL,
  PRIMARY KEY (`codAsiento`),
  KEY `fk_asiento_proyeccion` (`codProyeccion`),
  CONSTRAINT `fk_asiento_proyeccion` FOREIGN KEY (`codProyeccion`) REFERENCES `proyeccion` (`codProyeccion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asiento`
--

LOCK TABLES `asiento` WRITE;
/*!40000 ALTER TABLE `asiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `asiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comprador`
--

DROP TABLE IF EXISTS `comprador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comprador` (
  `dni` int NOT NULL,
  `nombre` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `fechaNac` date NOT NULL,
  `password` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprador`
--

LOCK TABLES `comprador` WRITE;
/*!40000 ALTER TABLE `comprador` DISABLE KEYS */;
/*!40000 ALTER TABLE `comprador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleticket`
--

DROP TABLE IF EXISTS `detalleticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleticket` (
  `codAsiento` int NOT NULL,
  `codTicket` int NOT NULL,
  `subtotal` double NOT NULL,
  PRIMARY KEY (`codAsiento`,`codTicket`),
  KEY `detalle_ticket_fk` (`codTicket`),
  CONSTRAINT `detalle_asiento_fk` FOREIGN KEY (`codAsiento`) REFERENCES `asiento` (`codAsiento`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detalle_ticket_fk` FOREIGN KEY (`codTicket`) REFERENCES `ticketcompra` (`codTicket`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleticket`
--

LOCK TABLES `detalleticket` WRITE;
/*!40000 ALTER TABLE `detalleticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalleticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelicula` (
  `idPelicula` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `director` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `origen` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `genero` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `estreno` date NOT NULL,
  `enCartelera` tinyint(1) NOT NULL,
  PRIMARY KEY (`idPelicula`),
  KEY `idx_pelicula_td` (`titulo`,`director`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula`
--

LOCK TABLES `pelicula` WRITE;
/*!40000 ALTER TABLE `pelicula` DISABLE KEYS */;
/*!40000 ALTER TABLE `pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyeccion`
--

DROP TABLE IF EXISTS `proyeccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyeccion` (
  `codProyeccion` int NOT NULL AUTO_INCREMENT,
  `idioma` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `es3D` tinyint(1) NOT NULL,
  `subtitulada` tinyint(1) NOT NULL,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL,
  `precio` double NOT NULL,
  `titulo` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `director` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nroSala` int unsigned NOT NULL,
  `fechaProyeccion` date DEFAULT NULL,
  PRIMARY KEY (`codProyeccion`),
  KEY `tituloydirector_proyeccion_fk` (`titulo`,`director`),
  KEY `nroSala_proyeccion_fk` (`nroSala`),
  CONSTRAINT `nroSala_proyeccion_fk` FOREIGN KEY (`nroSala`) REFERENCES `salas` (`nroSala`),
  CONSTRAINT `tituloydirector_proyeccion_fk` FOREIGN KEY (`titulo`, `director`) REFERENCES `pelicula` (`titulo`, `director`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyeccion`
--

LOCK TABLES `proyeccion` WRITE;
/*!40000 ALTER TABLE `proyeccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `proyeccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salas`
--

DROP TABLE IF EXISTS `salas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salas` (
  `nroSala` int unsigned NOT NULL AUTO_INCREMENT,
  `apta3D` tinyint(1) NOT NULL,
  `capacidad` int unsigned NOT NULL,
  `estado` enum('LIBRE','OCUPADO') NOT NULL,
  PRIMARY KEY (`nroSala`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salas`
--

LOCK TABLES `salas` WRITE;
/*!40000 ALTER TABLE `salas` DISABLE KEYS */;
INSERT INTO `salas` VALUES (1,0,500,'LIBRE');
/*!40000 ALTER TABLE `salas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticketcompra`
--

DROP TABLE IF EXISTS `ticketcompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticketcompra` (
  `codTicket` int NOT NULL AUTO_INCREMENT,
  `fechaCompra` date NOT NULL,
  `fechaFuncion` date NOT NULL,
  `montoTotal` double NOT NULL,
  `dni` int NOT NULL,
  PRIMARY KEY (`codTicket`),
  KEY `ticket_dni_fk` (`dni`),
  CONSTRAINT `ticket_dni_fk` FOREIGN KEY (`dni`) REFERENCES `comprador` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticketcompra`
--

LOCK TABLES `ticketcompra` WRITE;
/*!40000 ALTER TABLE `ticketcompra` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticketcompra` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-18 23:31:20
