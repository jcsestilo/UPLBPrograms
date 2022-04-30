-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.32 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for eight-twelve
DROP DATABASE IF EXISTS `eight-twelve`;
CREATE DATABASE IF NOT EXISTS `eight-twelve` /*!40100 DEFAULT CHARACTER SET latin1 */;
GRANT ALL ON `eight-twelve`.* TO 'scott'@'localhost';
USE `eight-twelve`;


-- Dumping structure for table eight-twelve.tblitem
CREATE TABLE IF NOT EXISTS `tblitem` (
  `itemno` int(5) NOT NULL AUTO_INCREMENT,
  `itemname` varchar(30) NOT NULL,
  `itemdesc` varchar(50) DEFAULT NULL,
  `itemqtyprice` decimal(8,2) NOT NULL,
  PRIMARY KEY (`itemno`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Dumping data for table eight-twelve.tblitem: ~11 rows (approximately)
DELETE FROM `tblitem`;
/*!40000 ALTER TABLE `tblitem` DISABLE KEYS */;
INSERT INTO `tblitem` (`itemno`, `itemname`, `itemdesc`, `itemqtyprice`) VALUES
	(1, 'WcDough Burger', 'Regular Burger with No TLC', 35.00),
	(2, 'WcDough Fries Regular', 'Regular Salted Fries', 29.00),
	(3, 'WcDough Fries Large', 'Large Salted Fries', 55.00),
	(4, 'WcDough Cheese Burger', 'Regular Burger with Cheese', 55.00),
	(5, 'WcDough Quarter Pounder', 'Burger with a Quarter of Beef Patty and TLC', 106.00),
	(6, 'WcDough Big Mhak', 'Burger with 2 Beef Patties and Additional Layer of Bread and TLC', 98.00),
	(7, 'WcDough Coffee Regular', NULL, 30.00),
	(8, 'WcDough Coffee Large', NULL, 50.00),
	(9, 'WcDough Banana Pie', NULL, 55.00),
	(10, 'WcDough Apple Milkshake', NULL, 60.00),
	(11, 'WcDough Fries Medium', NULL, 40.00);
/*!40000 ALTER TABLE `tblitem` ENABLE KEYS */;


-- Dumping structure for table eight-twelve.tblorder
CREATE TABLE IF NOT EXISTS `tblorder` (
  `orderno` int(5) NOT NULL AUTO_INCREMENT,
  `ordertotalprice` decimal(10,2) NOT NULL,
  `orderdate` date NOT NULL,
  PRIMARY KEY (`orderno`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table eight-twelve.tblorder: ~3 rows (approximately)
DELETE FROM `tblorder`;
/*!40000 ALTER TABLE `tblorder` DISABLE KEYS */;
INSERT INTO `tblorder` (`orderno`, `ordertotalprice`, `orderdate`) VALUES
	(1, 64.00, '2016-01-01'),
	(2, 58.00, '2016-08-23'),
	(3, 174.00, '2016-08-23'),
	(4, 205.00, '2020-05-01'),
	(5, 408.00, '2020-05-20'),
	(6, 94.00, '2021-08-06');
/*!40000 ALTER TABLE `tblorder` ENABLE KEYS */;


-- Dumping structure for table eight-twelve.tblorderitems
CREATE TABLE IF NOT EXISTS `tblorderitems` (
  `orderno` int(5) NOT NULL,
  `itemno` int(5) NOT NULL,
  `itemqty` int(5) NOT NULL DEFAULT '1',
  `itemtotalprice` decimal(8,2) NOT NULL,
  KEY `tblorderitems_orderno_fk` (`orderno`),
  KEY `tblorderitems_itemno_fk` (`itemno`),
  CONSTRAINT `tblorderitems_orderno_fk` FOREIGN KEY (`orderno`) REFERENCES `tblorder` (`orderno`),
  CONSTRAINT `tblorderitems_itemno_fk` FOREIGN KEY (`itemno`) REFERENCES `tblitem` (`itemno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table eight-twelve.tblorderitems: ~7 rows (approximately)
DELETE FROM `tblorderitems`;
/*!40000 ALTER TABLE `tblorderitems` DISABLE KEYS */;
INSERT INTO `tblorderitems` (`orderno`, `itemno`, `itemqty`, `itemtotalprice`) VALUES
	(1, 1, 1, 35.00),
	(1, 2, 1, 29.00),
	(2, 2, 2, 58.00),
	(3, 1, 1, 35.00),
	(3, 2, 1, 40.00),
	(3, 3, 1, 80.00),
	(3, 4, 1, 35.00),
	(4, 8, 1, 50.00),
	(4, 9, 1, 55.00),
	(4, 10, 1, 60.00),
	(4, 11, 1, 40.00),
	(5, 5, 2, 212.00),
	(5, 6, 2, 196.00),
	(6, 1, 1, 35.00),
	(6, 2, 2, 58.00),
	(6, 7, 2, 60.00);
/*!40000 ALTER TABLE `tblorderitems` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
