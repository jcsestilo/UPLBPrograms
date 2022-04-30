DROP DATABASE IF EXISTS `studentcourse`;
CREATE DATABASE IF NOT EXISTS `studentcourse` /*!40100 DEFAULT CHARACTER SET latin1 */;
GRANT ALL ON studentcourse.* TO 'scott'@'localhost';
USE `studentcourse`;



CREATE TABLE IF NOT EXISTS `course` (
  `courseid` int(5) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL,
  `title` varchar(200) NOT NULL,
  `units` decimal(2, 1),
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `course` (`code`, `title`, `units`) VALUES
  ('CMSC 11', 'Introduction to Computer Science', 3.0),
  ('CMSC 21', 'Fundamentals of Programming', 3.0),
	('CMSC 127', 'File Processing and Database Systems', 3.0),
  ('CMSC 150', 'Numerical and Symbolic Computations', 3.0),
	('MATH 17', 'College Algebra and Trigonometry', 5.0);
  


CREATE TABLE IF NOT EXISTS `student` (
  `studno` varchar(10) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `mname` varchar(30) NOT NULL,
  `suffix` varchar(5) DEFAULT NULL,
  `bday` date NOT NULL,
  `degree` varchar(10) NOT NULL,
  PRIMARY KEY (`studno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `student` (`studno`, `lname`, `fname`, `mname`, `suffix`, `bday`, `degree`) VALUES
	('2016-50840', 'San Jose', 'Gerald Albert', 'Ronato', 'II', '1998-06-07', 'BSCS'),
	('2017-12345', 'Abantao', 'John Robert', 'Tetangco', NULL, '1999-03-18', 'BSCS'),
	('2017-62912', 'Yllana', 'Marissa', 'Benedicto', NULL, '1998-01-22', 'BSCS'),
  ('2020-30013', 'Tomas', 'Sam', 'Castro', NULL, '2001-03-19', 'BSCE'),
	('2016-18511', 'Bondillo', 'Agnes Mila', 'Lim', NULL, '1997-05-10', 'BSCS'),
	('2015-14962', 'Kronwada', 'Maria Shae', 'San Juan', NULL, '1996-08-27', 'BSCS'),
	('2017-76692', 'Hermano', 'Prince Miggy', 'Callente', 'Jr', '1999-12-19', 'BSEE'),
  ('2019-90981', 'Dizon', 'Charlene', 'Tantiongco', NULL, '2000-12-19', 'BSCE'),
	('2014-64979', 'Canete', 'Allysa Mhay', 'Ramos', NULL, '1998-10-27', 'BSAP'),
  ('2020-12543', 'Mendoza', 'Marie', 'Salvador', NULL, '2001-10-03', 'BSAM');


CREATE TABLE IF NOT EXISTS `enrolls` (
  `courseid` int(5) NOT NULL,
  `studno` varchar(10) NOT NULL,
  `sem` varchar(1) NOT NULL,
  `ay` varchar(5) NOT NULL,
  `grade` decimal(3,2) NOT NULL,
  PRIMARY KEY (`courseid`, `studno`, `sem`, `ay`),
  CONSTRAINT `fk_courseid` FOREIGN KEY (courseid) REFERENCES course (courseid),
  CONSTRAINT `fk_studno` FOREIGN KEY (studno) REFERENCES student (studno)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `enrolls` (`courseid`, `studno`, `sem`, `ay`, `grade`) VALUES
  (5, '2015-14962', '2', '15-16', 5),
  (5, '2015-14962', 'M', '15-16', 5),
  (5, '2014-64979', '2', '15-16', 5),
  (5, '2014-64979', '1', '16-17', 1.25),
  (5, '2015-14962', '1', '16-17', 3),
  (5, '2016-50840', '1', '16-17', 2),
  (5, '2016-18511', '1', '16-17', 2.25),
  (5, '2017-12345', '1', '17-18', 1.75),
  (5, '2017-62912', '1', '17-18', 2.75),
  (5, '2017-76692', '1', '17-18', 1),

  (1, '2017-76692', '2', '17-18', 1.5),
  (1, '2014-64979', '2', '16-17', 1.75),

  (1, '2016-50840', '2', '16-17', 3),
  (1, '2017-12345', '2', '17-18', 2.75),
  (1, '2017-62912', '2', '17-18', 1.5),
  (1, '2016-18511', '2', '16-17', 2),
  (1, '2015-14962', '2', '16-17', 1),

  (2, '2016-50840', '2', '17-18', 5),
  (2, '2014-64979', '2', '17-18', 1.75),
  (2, '2016-18511', '2', '17-18', 2),
  (2, '2015-14962', '2', '17-18', 3),

  (2, '2016-50840', '1', '18-19', 1),
  (2, '2017-12345', '1', '18-19', 2.25),
  (2, '2017-62912', '1', '18-19', 1.25),

  (3, '2016-50840', '2', '18-19', 1.25),
  (3, '2017-12345', '2', '18-19', 1.75),
  (3, '2017-62912', '2', '18-19', 2.25),
  (3, '2016-18511', '2', '18-19', 3),
  (3, '2015-14962', '2', '18-19', 2),

  (4, '2016-50840', '1', '19-20', 2.25),
  (4, '2017-12345', '1', '19-20', 1.5),
  (4, '2017-62912', '1', '19-20', 1),
  (4, '2016-18511', '1', '19-20', 2),
  (4, '2015-14962', '1', '19-20', 3);