CREATE DATABASE IF NOT EXISTS `uaap`;
USE `uaap`;

CREATE TABLE IF NOT EXISTS `member` (
  `membername` varchar(5) NOT NULL,
  `memberfullname` varchar(50) NOT NULL,
  `yearfounded` int(4) NOT NULL COMMENT 'cannot use year since it allows only years from upward  1990s',
  `yearmembership` year(4) NOT NULL,
  PRIMARY KEY (`membername`)
);


INSERT INTO `member` (`membername`, `memberfullname`, `yearfounded`, `yearmembership`) VALUES
	('ADMU', 'Ateneo De Manila University', 1859, '1978'),
	('AdU', 'Adamson University', 1932, '1953'),
	('DLSU', 'De La Salle University', 1911, '1986'),
	('FEU', 'Far Eastern University', 1928, '1938'),
	('NU', 'National University', 1900, '1938'),
	('UE', 'University of the East', 1946, '1952'),
	('UP', 'University of the Philippines', 1908, '1938'),
	('UST', 'University of Santo Tomas', 1611, '1938');

CREATE TABLE IF NOT EXISTS `cheerdance` (
  `uaapyear` year(4) NOT NULL,
  `membername` varchar(5) NOT NULL,
  `rank` varchar(10) NOT NULL
);


INSERT INTO `cheerdance` (`uaapyear`, `membername`, `rank`) VALUES
	('2015', 'NU', 'Champion'),
	('2015', 'UP', '2nd place'),
	('2015', 'UST', '1st place'),
	('2014', 'NU', 'Champion'),
	('2014', 'UP', '1st place'),
	('2014', 'UST', '2nd place'),
	('2013', 'NU', 'Champion'),
	('2013', 'UP', '1st place'),
	('2013', 'DLSU', '2nd place');


CREATE TABLE IF NOT EXISTS `cheerdancehost` (
  `uaapyear` year(4) NOT NULL,
  `membername` varchar(5) NOT NULL,
  UNIQUE KEY `cheerdancehost_uk` (`uaapyear`,`membername`)
);


INSERT INTO `cheerdancehost` (`uaapyear`, `membername`) VALUES
	('2011', 'ADMU'),
	('2013', 'AdU'),
	('2010', 'DLSU'),
	('2012', 'NU'),
	('2014', 'UE'),
	('2015', 'UP');
