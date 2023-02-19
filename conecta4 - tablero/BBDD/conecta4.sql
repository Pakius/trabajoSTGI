-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.14-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6363
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para conecta4game
CREATE DATABASE IF NOT EXISTS `conecta4game` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `conecta4game`;

-- Volcando estructura para tabla conecta4game.detallespartida
CREATE TABLE IF NOT EXISTS `detallespartida` (
  `idPartida` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `turno` bit(1) DEFAULT NULL,
  `casillas` binary(49) DEFAULT NULL,
  `terminada` bit(1) DEFAULT NULL,
  PRIMARY KEY (`idPartida`,`idUsuario`),
  KEY `FK_detallespartida_usuarios` (`idUsuario`),
  CONSTRAINT `FK_detallespartida_partidas` FOREIGN KEY (`idPartida`) REFERENCES `partidas` (`idPartida`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_detallespartida_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla conecta4game.detallespartida: ~0 rows (aproximadamente)

-- Volcando estructura para tabla conecta4game.partidas
CREATE TABLE IF NOT EXISTS `partidas` (
  `idPartida` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` text NOT NULL,
  `Tablero` binary(49) NOT NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  PRIMARY KEY (`idPartida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla conecta4game.partidas: ~0 rows (aproximadamente)

-- Volcando estructura para tabla conecta4game.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` text DEFAULT NULL,
  `Nick` text DEFAULT NULL,
  `Password` text DEFAULT NULL,
  `email` text DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla conecta4game.usuarios: ~0 rows (aproximadamente)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
