CREATE DATABASE  IF NOT EXISTS `gp13_cinemacentro` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gp13_cinemacentro`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gp13_cinemacentro
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `asiento`
--

DROP TABLE IF EXISTS `asiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiento` (
  `codAsiento` int NOT NULL AUTO_INCREMENT,
  `fila` varchar(1) COLLATE utf8mb4_general_ci NOT NULL,
  `numero` int NOT NULL,
  `estado` enum('LIBRE','OCUPADO') COLLATE utf8mb4_general_ci NOT NULL,
  `codProyeccion` int NOT NULL,
  PRIMARY KEY (`codAsiento`)
) ENGINE=InnoDB AUTO_INCREMENT=586 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `detalleticket`
--

DROP TABLE IF EXISTS `detalleticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleticket` (
  `codAsiento` int NOT NULL,
  `subtotal` double NOT NULL,
  `codTicket` int NOT NULL,
  PRIMARY KEY (`codAsiento`,`codTicket`),
  KEY `detalle_ticket_fk` (`codTicket`),
  CONSTRAINT `detalle_asiento_fk` FOREIGN KEY (`codAsiento`) REFERENCES `asiento` (`codAsiento`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detalle_ticket_fk` FOREIGN KEY (`codTicket`) REFERENCES `ticketcompra` (`codTicket`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelicula` (
  `titulo` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `director` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `origen` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `genero` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `estreno` date NOT NULL,
  `enCartelera` tinyint(1) NOT NULL,
  PRIMARY KEY (`titulo`,`director`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proyeccion`
--

DROP TABLE IF EXISTS `proyeccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyeccion` (
  `codProyeccion` int NOT NULL AUTO_INCREMENT,
  `idioma` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `es3D` tinyint(1) NOT NULL,
  `subtitulada` tinyint(1) NOT NULL,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL,
  `precio` double NOT NULL,
  `titulo` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `director` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `nroSala` int unsigned NOT NULL,
  `fechaProyeccion` date DEFAULT NULL,
  PRIMARY KEY (`codProyeccion`),
  KEY `tituloydirector_proyeccion_fk` (`titulo`,`director`),
  KEY `nroSala_proyeccion_fk` (`nroSala`),
  CONSTRAINT `nroSala_proyeccion_fk` FOREIGN KEY (`nroSala`) REFERENCES `salas` (`nroSala`),
  CONSTRAINT `tituloydirector_proyeccion_fk` FOREIGN KEY (`titulo`, `director`) REFERENCES `pelicula` (`titulo`, `director`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `estado` enum('LIBRE','OCUPADO') COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`nroSala`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ticketcompra`
--

DROP TABLE IF EXISTS `ticketcompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticketcompra` (
  `codTicket` int NOT NULL AUTO_INCREMENT,
  `fechaCompra` date NOT NULL,
  `codProyeccion` int NOT NULL,
  `montoTotal` int NOT NULL,
  `dni` int NOT NULL,
  PRIMARY KEY (`codTicket`),
  KEY `ticket_dni_fk` (`dni`),
  KEY `ticket_proyeccion_fk_idx` (`codProyeccion`),
  CONSTRAINT `ticket_dni_fk` FOREIGN KEY (`dni`) REFERENCES `comprador` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticket_proyeccion_fk` FOREIGN KEY (`codProyeccion`) REFERENCES `proyeccion` (`codProyeccion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2012 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-19 16:10:32
