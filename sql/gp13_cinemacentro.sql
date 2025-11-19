-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-10-2025 a las 01:09:41
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gp13_cinemacentro`
--
CREATE DATABASE IF NOT EXISTS `gp13_cinemacentro` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gp13_cinemacentro`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asiento`
--

DROP TABLE IF EXISTS `asiento`;
CREATE TABLE `asiento` (
  `codAsiento` int(11) NOT NULL,
  `fila` varchar(1) NOT NULL,
  `numero` int(11) NOT NULL,
  `estado` enum('LIBRE','OCUPADO') NOT NULL,
  `codProyeccion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprador`
--

DROP TABLE IF EXISTS `comprador`;
CREATE TABLE `comprador` (
  `dni` int(8) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `fechaNac` date NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleticket`
--

DROP TABLE IF EXISTS `detalleticket`;
CREATE TABLE `detalleticket` (
  `codAsiento` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  `codTicket` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
CREATE TABLE `pelicula` (
  `titulo` varchar(25) NOT NULL,
  `director` varchar(25) NOT NULL,
  `origen` varchar(25) NOT NULL,
  `genero` varchar(25) NOT NULL,
  `estreno` date NOT NULL,
  `enCartelera` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyeccion`
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


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salas`
--

DROP TABLE IF EXISTS `salas`;
CREATE TABLE `salas` (
  `nroSala` int(10) UNSIGNED NOT NULL,
  `apta3D` tinyint(1) NOT NULL,
  `capacidad` int(10) UNSIGNED NOT NULL,
  `estado` enum('LIBRE','OCUPADO') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `salas`
--

INSERT INTO `salas` (`nroSala`, `apta3D`, `capacidad`, `estado`) VALUES
(1, 1, 30, 'LIBRE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticketcompra`
--

DROP TABLE IF EXISTS `ticketcompra`;
CREATE TABLE `ticketcompra` (
  `codTicket` int(11) NOT NULL,
  `fechaCompra` date NOT NULL,
  `fechaFuncion` date NOT NULL,
  `montoTotal` int(11) NOT NULL,
  `dni` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asiento`
--
ALTER TABLE `asiento`
  ADD PRIMARY KEY (`codAsiento`);

--
-- Indices de la tabla `comprador`
--
ALTER TABLE `comprador`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  ADD PRIMARY KEY (`codAsiento`,`codTicket`),
  ADD KEY `detalle_ticket_fk` (`codTicket`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`titulo`,`director`);

--
-- Indices de la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  ADD PRIMARY KEY (`codProyeccion`),
  ADD KEY `tituloydirector_proyeccion_fk` (`titulo`,`director`),
  ADD KEY `nroSala_proyeccion_fk` (`nroSala`);

--
-- Indices de la tabla `salas`
--
ALTER TABLE `salas`
  ADD PRIMARY KEY (`nroSala`);

--
-- Indices de la tabla `ticketcompra`
--
ALTER TABLE `ticketcompra`
  ADD PRIMARY KEY (`codTicket`),
  ADD KEY `ticket_dni_fk` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asiento`
--
ALTER TABLE `asiento`
  MODIFY `codAsiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  MODIFY `codProyeccion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `salas`
--
ALTER TABLE `salas`
  MODIFY `nroSala` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `ticketcompra`
--
ALTER TABLE `ticketcompra`
  MODIFY `codTicket` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  ADD CONSTRAINT `detalle_asiento_fk` FOREIGN KEY (`codAsiento`) REFERENCES `asiento` (`codAsiento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_ticket_fk` FOREIGN KEY (`codTicket`) REFERENCES `ticketcompra` (`codTicket`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `proyeccion`
--
ALTER TABLE `proyeccion`
  ADD CONSTRAINT `nroSala_proyeccion_fk` FOREIGN KEY (`nroSala`) REFERENCES `salas` (`nroSala`),
  ADD CONSTRAINT `tituloydirector_proyeccion_fk` FOREIGN KEY (`titulo`,`director`) REFERENCES `pelicula` (`titulo`, `director`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ticketcompra`
--
ALTER TABLE `ticketcompra`
  ADD CONSTRAINT `ticket_dni_fk` FOREIGN KEY (`dni`) REFERENCES `comprador` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
