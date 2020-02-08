-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: localhost    Database: cadastro
-- ------------------------------------------------------
-- Server version	5.7.24

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
-- Table structure for table `contatos`
--

DROP TABLE IF EXISTS `contatos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contatos` (
  `id` varchar(65) NOT NULL,
  `nome` varchar(65) NOT NULL,
  `email` varchar(65) DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `telefone` varchar(65) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contatos`
--

LOCK TABLES `contatos` WRITE;
/*!40000 ALTER TABLE `contatos` DISABLE KEYS */;
INSERT INTO `contatos` VALUES ('82366214-8ca8-4612-a64f-79d1374f0681','Pedro Teste','pedro@email.teste','999.888.777-66','(00) 0001-0002','1993-11-11'),('957d8020-a27a-4c04-85ee-2e18698c63b1','Maria da Silva','maria.silva@email.teste','123.456.789-01','(00) 0011-0022','1987-03-12'),('c2ff67be-d973-409e-894b-8b15278ea629','Sebastião da Silva','sebastiao.silva@email.com','000.111.222-33','(00) 0011-0022 / (00) 1100-0022','2001-04-13'),('b3bd0450-e90f-4368-a30f-1cb7b9e2dba7','Ana Maria','ana.maria@email.com','999.777.555-33','(99) 9988-7766','2000-11-23');
/*!40000 ALTER TABLE `contatos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enderecos`
--

DROP TABLE IF EXISTS `enderecos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enderecos` (
  `id_contato` varchar(65) NOT NULL,
  `estado` varchar(65) DEFAULT NULL,
  `cidade` varchar(65) DEFAULT NULL,
  `bairro` varchar(65) DEFAULT NULL,
  `logradouro` varchar(65) DEFAULT NULL,
  KEY `id_contato` (`id_contato`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enderecos`
--

LOCK TABLES `enderecos` WRITE;
/*!40000 ALTER TABLE `enderecos` DISABLE KEYS */;
INSERT INTO `enderecos` VALUES ('82366214-8ca8-4612-a64f-79d1374f0681','PA','Santarém','Boa Vista','Rua Teste IV, 104'),('957d8020-a27a-4c04-85ee-2e18698c63b1','SP','Marília','Bom Jardim','Rua Teste II, 101'),('c2ff67be-d973-409e-894b-8b15278ea629','GO','Testolândia','Serafim Cruz','Rua Teste III, 102'),('b3bd0450-e90f-4368-a30f-1cb7b9e2dba7','MS','Santa Cruz','Ermelinda','Rua Teste V, 105');
/*!40000 ALTER TABLE `enderecos` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-08 16:14:18
