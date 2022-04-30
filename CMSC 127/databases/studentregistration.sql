-- Dumping database structure for studentregistration
DROP DATABASE IF EXISTS `studentregistration`;
CREATE DATABASE IF NOT EXISTS `studentregistration` /*!40100 DEFAULT CHARACTER SET latin1 */;
GRANT ALL ON studentregistration.* TO 'scott'@'localhost';
USE `studentregistration`;


-- Dumping structure for table studentregistration.college
CREATE TABLE IF NOT EXISTS `college` (
  `collegename` varchar(5) NOT NULL,
  `collegefullname` varchar(40) NOT NULL,
  `yearestablished` year(4) NOT NULL,
  PRIMARY KEY (`collegename`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table studentregistration.college: ~3 rows (approximately)
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` (`collegename`, `collegefullname`, `yearestablished`) VALUES
	('CAS', 'College of Arts and Sciences', '1972'),
	('CDC', 'College of Development Communication', '1998'),
	('CHE', 'College of Human Ecology', '1983');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;


-- Dumping structure for table studentregistration.course
CREATE TABLE IF NOT EXISTS `course` (
  `coursenumber` varchar(10) NOT NULL,
  `coursetitle` varchar(40) NOT NULL,
  `prerequisite` varchar(10) DEFAULT NULL,
  `unitearned` decimal(2,1) NOT NULL DEFAULT '3.0',
  UNIQUE KEY `course_coursenumber_uk` (`coursenumber`,`prerequisite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table studentregistration.course: ~3 rows (approximately)
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` (`coursenumber`, `coursetitle`, `prerequisite`, `unitearned`) VALUES
	('CMSC 127', 'File Processing and Database Systems', 'CMSC 123', 3.0),
	('CMSC 128', 'Introduction to Software Engineering', 'CMSC 123', 3.0),
	('MATH 17', 'College Algebra and Trigonometry', NULL, 5.0),
	('CMSC 131', 'Introduction to Computer Organization', 'CMSC 21', 3.0),
	('HUME 1', 'Introduction to Human Ecology', NULL, 3.0);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;


-- Dumping structure for table studentregistration.degreeprog
CREATE TABLE IF NOT EXISTS `degreeprog` (
  `degree` varchar(7) NOT NULL,
  `fulldegreename` varchar(50) NOT NULL,
  `noofunitsrequired` int(3) NOT NULL DEFAULT '0',
  `offeringcollege` varchar(5) NOT NULL,
  `offeringunit` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`degree`),
  KEY `degreeprog_offeringcollege_fk` (`offeringcollege`),
  CONSTRAINT `degreeprog_offeringcollege_fk` FOREIGN KEY (`offeringcollege`) REFERENCES `college` (`collegename`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table studentregistration.degreeprog: ~6 rows (approximately)
/*!40000 ALTER TABLE `degreeprog` DISABLE KEYS */;
INSERT INTO `degreeprog` (`degree`, `fulldegreename`, `noofunitsrequired`, `offeringcollege`, `offeringunit`) VALUES
	('BACA', 'BA Communication Arts', 148, 'CAS', 'DHUM'),
	('BSAP', 'BS Applied Physics', 144, 'CAS', 'IMSP'),
	('BSCS', 'BS Computer Science', 144, 'CAS', 'ICS'),
	('BSDC', 'BS Development Communication', 147, 'CDC', NULL),
	('BSM', 'BS Mathematics', 144, 'CAS', 'IMSP'),
	('BSN', 'BS Nutrition', 147, 'CHE', 'IHN');
/*!40000 ALTER TABLE `degreeprog` ENABLE KEYS */;


-- Dumping structure for table studentregistration.offering
CREATE TABLE IF NOT EXISTS `offering` (
  `semester` varchar(20) NOT NULL,
  `academicyear` varchar(9) NOT NULL,
  `coursenumber` varchar(15) NOT NULL,
  `sectionname` varchar(5) NOT NULL,
  `islecture` varchar(1) NOT NULL DEFAULT 'Y',
  `numberofstudents` int(3) NOT NULL DEFAULT '0',
  UNIQUE KEY `offering_saycs_uk` (`semester`,`academicyear`,`coursenumber`,`sectionname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table studentregistration.offering: ~4 rows (approximately)
/*!40000 ALTER TABLE `offering` DISABLE KEYS */;
INSERT INTO `offering` (`semester`, `academicyear`, `coursenumber`, `sectionname`, `islecture`, `numberofstudents`) VALUES
	('1st', '2012-2013', 'CMSC 127', 'S', 'Y', 150),
	('2nd', '2013-2014', 'MATH 17', 'B', 'Y', 179),
	('mid', '2014', 'MATH 17', 'C', 'Y', 80),
	('mid', '2014', 'MATH 17', 'C-1L', 'N', 20),
	('2nd', '2013-2014', 'HUME 1', 'AB-1L', 'N', 40);
/*!40000 ALTER TABLE `offering` ENABLE KEYS */;


-- Dumping structure for table studentregistration.student
CREATE TABLE IF NOT EXISTS `student` (
  `studno` varchar(10) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `mname` varchar(30) NOT NULL,
  `suffix` varchar(5) DEFAULT NULL,
  `bday` date NOT NULL,
  `degree` varchar(10) NOT NULL,
  `unitsearned` int(3) DEFAULT '0',
  `yrlevel` varchar(2) NOT NULL DEFAULT 'NF',
  PRIMARY KEY (`studno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table studentregistration.student: ~7 rows (approximately)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`studno`, `lname`, `fname`, `mname`, `suffix`, `bday`, `degree`, `unitsearned`, `yrlevel`) VALUES
	('2005-50840', 'San Jose', 'Gerald Albert', 'Ronato', 'II', '1988-06-07', 'BSDC', 135, 'SR'),
	('2007-12345', 'Abantao', 'John Robert', 'Tetangco', NULL, '1991-03-18', 'BSCS', 137, 'SR'),
	('2008-62912', 'Yllana', 'Marissa', 'Benedicto', NULL, '1994-01-22', 'BACA', 138, 'SR'),
	('2010-18511', 'Bondillo', 'Agnes Mila', 'Lim', NULL, '1993-05-10', 'BSCS', 58, 'SO'),
	('2011-14962', 'Kronwada', 'Maria Shae', 'San Juan', NULL, '1994-08-27', 'BSM', 19, 'OF'),
	('2012-76692', 'Hermano', 'Prince Miggy', 'Callente', 'Jr', '1995-12-19', 'BSN', 60, 'JR'),
	('2014-64979', 'Canete', 'Allysa Mhay', 'Ramos', NULL, '1998-10-27', 'BSAP', 0, 'NF'),
	('2014-76632', 'Sabater', 'Yuma Mae', 'Juan', NULL, '1998-07-07', 'BSCS', 19, 'OF'),
	('2012-55432', 'Shelf', 'Rowen', 'Pele', 'Jr', '1995-11-04', 'BSCS', 45, 'JR'),
	('2010-10233', 'Canete', 'Marline', 'Badwick', NULL, '1993-02-27', 'BSDC', 0, 'SO'),
	('2014-12007', 'Leonidas', 'Shane Amy', 'De Ramos', NULL, '1998-07-15', 'BACA', 0, 'NF');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
