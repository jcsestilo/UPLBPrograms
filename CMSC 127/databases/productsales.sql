DROP DATABASE IF EXISTS `productsales`;
CREATE DATABASE IF NOT EXISTS `productsales` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `productsales`;

CREATE TABLE IF NOT EXISTS `customer` (
  `customerid` int(5) NOT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `bday` date DEFAULT NULL,
  `country` varchar(50) NOT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DELETE FROM `customer`;
INSERT INTO `customer` (`customerid`, `fname`, `lname`, `email`, `bday`, `country`) VALUES
	(1, 'Holli', 'Kimm', 'hkimm0@discovery.com', '1967-04-19', 'Germany'),
	(2, 'Zitella', 'Deluca', 'zdeluca1@cocolog-nifty.com', '1989-02-28', 'Japan'),
	(3, 'Arlie', 'Sole', 'asole2@chicagotribune.com', '1978-11-02', 'Germany'),
	(4, 'Josy', 'Rustadge', 'jrustadge3@addthis.com', '1988-06-04', 'Germany'),
	(5, 'Sianna', 'Judge', 'sjudge4@myspace.com', '1990-01-01', 'Germany'),
	(6, 'Preston', 'Cornu', 'pcornu5@harvard.edu', '1987-09-02', 'USA'),
	(7, 'Emmie', 'Havik', 'ehavik6@dropbox.com', '2000-12-05', 'Thailand'),
	(8, 'Chadwick', 'Caret', 'ccaret7@comcast.net', '1984-10-13', 'Thailand'),
	(9, 'Gilberto', 'Forstall', 'gforstall8@yelp.com', '1995-03-17', 'Philippines'),
	(10, 'Barbra', 'Prestedge', 'bprestedge9@about.me', '1981-11-10', 'Philippines'),
	(11, 'Caspar', 'Soppit', 'csoppita@earthlink.net', '1970-08-21', 'Philippines'),
	(12, 'Cherey', 'Yorkston', 'cyorkstonb@ucoz.com', '1978-01-06', 'Italy'),
	(13, 'Niki', 'Oleszkiewicz', 'noleszkiewiczc@seesaa.net', '2003-04-16', 'Japan'),
	(14, 'Feliks', 'Jaquemar', 'fjaquemard@oracle.com', '1991-01-21', 'Japan'),
	(15, 'Cordula', 'Adnet', 'cadnete@si.edu', '1984-11-09', 'Germany'),
	(16, 'Moe', 'Hyder', 'mhyderf@google.com.au', '1998-09-03', 'Germany'),
	(17, 'Beverlie', 'Skuce', 'bskuceg@dagondesign.com', '1989-08-01', 'Canada'),
	(18, 'Richy', 'Bergstrand', 'rbergstrandh@mysql.com', '1990-11-04', 'Canada'),
	(19, 'Kass', 'Duggary', 'kduggaryi@blogger.com', '2001-11-13', 'Thailand'),
	(20, 'Salaidh', 'Giabuzzi', 'sgiabuzzij@php.net', '1989-12-12', 'Italy'),
	(21, 'Vaclav', 'deKnevet', 'vdeknevetk@umich.edu', '1990-11-01', 'Japan'),
	(22, 'Roz', 'Pybworth', 'rpybworthl@ftc.gov', '1968-12-24', 'Germany'),
	(23, 'Yuri', 'Leicester', 'yleicesterm@forbes.com', '1968-03-30', 'Italy'),
	(24, 'Missie', 'Eam', 'meamn@arizona.edu', '1979-01-27', 'Germany'),
	(25, 'Charita', 'Aingel', 'caingelo@i2i.jp', '1989-07-15', 'Germany'),
	(26, 'Jehanna', 'Hause', 'jhausep@time.com', '1997-04-30', 'Germany'),
	(27, 'Pembroke', 'Poulsum', 'ppoulsumq@addtoany.com', '1954-11-11', 'Italy'),
	(28, 'Cooper', 'Bredes', 'cbredesr@edublogs.org', '1959-10-30', 'Germany'),
	(29, 'Benji', 'Shiel', 'bshiels@latimes.com', '2001-04-28', 'Germany'),
	(30, 'Elden', 'Blyth', 'eblytht@ibm.com', '1963-01-19', 'Germany'),
	(31, 'Juieta', 'Lemary', 'jlemaryu@guardian.co.uk', '1995-08-23', 'Germany'),
	(32, 'Rivkah', 'Selburn', 'rselburnv@t.co', '1967-05-10', 'Germany'),
	(33, 'Nealon', 'Larrett', 'nlarrettw@shutterfly.com', '1955-10-09', 'Philippines'),
	(34, 'Halli', 'Wickling', 'hwicklingx@tripadvisor.com', '1984-07-21', 'Italy'),
	(35, 'Chance', 'Merrin', 'cmerriny@wiley.com', '1992-12-01', 'Germany'),
	(36, 'Eziechiele', 'Minghetti', 'eminghettiz@cdbaby.com', '1965-09-19', 'Philippines'),
	(37, 'Launce', 'Donnell', 'ldonnell10@ameblo.jp', '1980-11-02', 'Germany'),
	(38, 'Prudy', 'Pemberton', 'ppemberton11@jimdo.com', '1974-06-22', 'USA'),
	(39, 'Filberto', 'Stow', 'fstow12@cornell.edu', '1978-10-27', 'Germany'),
	(40, 'Jarad', 'Tacey', 'jtacey13@goodreads.com', '1967-03-19', 'USA'),
	(41, 'Malcolm', 'Drennan', 'mdrennan14@harvard.edu', '1988-03-09', 'Philippines'),
	(42, 'Drud', 'Janc', 'djanc15@ed.gov', '1998-04-01', 'Germany'),
	(43, 'Sheba', 'Caslane', 'scaslane16@bloglovin.com', '1959-12-21', 'Italy'),
	(44, 'Maximilien', 'Wantling', 'mwantling17@bing.com', '1960-12-08', 'Italy'),
	(45, 'Kiel', 'Hauger', 'khauger18@ning.com', '2004-03-19', 'Thailand'),
	(46, 'Becki', 'Jirasek', 'bjirasek19@nhs.uk', '1981-05-13', 'Canada'),
	(47, 'Peterus', 'Kerfoot', 'pkerfoot1a@geocities.jp', '2005-01-09', 'Germany'),
	(48, 'Cullan', 'Tuison', 'ctuison1b@reuters.com', '2003-06-27', 'Canada'),
	(49, 'Mitzi', 'Coper', 'mcoper1c@ted.com', '1984-11-30', 'Japan'),
	(50, 'Mair', 'Piffe', 'mpiffe1d@washingtonpost.com', '1958-01-04', 'Japan');

CREATE TABLE IF NOT EXISTS `item` (
  `itemno` int(5) NOT NULL AUTO_INCREMENT,
  `itemcompany` varchar(30) NOT NULL,
  `itemmodel` varchar(30) NOT NULL,
  `itemqtystock` int(5) NOT NULL,
  `itemqtyprice` decimal(8,2) NOT NULL,
  `itemlaunchdate` date NOT NULL,
  PRIMARY KEY (`itemno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DELETE FROM `item`;
INSERT INTO `item` (`itemcompany`, `itemmodel`, `itemqtystock`,`itemqtyprice`, `itemlaunchdate`) VALUES
	('LG','V40 ThinQ',15,45990,'2018-11-08'),
	('Google','Pixel',102,11990,'2018-05-31'),
	('Samsung','Galaxy Note 9',245,19200,'2018-08-07'),
	('Huawei','Y9s', 534,13990,	'2020-01-15'),
	('Samsung','Galaxy Z Flip',1500,79990, '2020-02-21'),
	('Samsung','Galaxy S20 (4G)',2000,45990,'2020-02-11'),
	('OPPO','Find X2 Pro',452,65990,'2020-03-21'),
	('Huawei','P40', 1200,36990, '2020-03-17'),
	('Huawei','Y5p',1215,4490,'2020-05-01'),
	('Xiaomi','Redmi Note 9',1500,9990,'2020-05-07'),
	('Xiaomi','Mi 10',200,36990,'2020-06-09'),
	('POCO','X3 NFC',823,12990,'2020-09-01'),
	('Samsung','Galaxy Z Fold 2',671,109990,'2020-09-28'),
	('OnePlus','8T',3430,29990,'2020-10-30'),
	('Xiaomi','Mi 10T 5G',346,20990,'2020-10-22'),
	('Apple','iPhone 12 mini', 90, 52990,'2020-11-15'),
	('Apple','iPhone 12 Pro',823,80990,'2020-11-04'),
	('Apple','iPhone 12 Pro Max',341,86990,'2020-11-18'),
	('OPPO','Reno 5 Pro',3400,26499,'2021-03-27'),
	('Samsung','Galaxy A52',15000,13990,'2021-02-26'),
	('Xiaomi','Redmi Note 9 5G',240,9990,'2021-02-18'),
	('OnePlus','9',12000,31990,'2021-03-21'),
	('Realmi','8 Pro',1033,12990,'2021-02-22');

CREATE TABLE IF NOT EXISTS `orderdetails` (
  `orderno` int(5) NOT NULL,
  `itemno` int(5) NOT NULL,
  `orderqty` int(5) NOT NULL,
  `orderprice` decimal(10,2) NOT NULL,
  PRIMARY KEY (`orderno`, `itemno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DELETE FROM `orderdetails`;
INSERT INTO `orderdetails` (`orderno`, `itemno`, `orderqty`, `orderprice`) VALUES
	(1, 3, 1, 19200),
	(2, 2, 1, 11990),
	(3, 2, 1, 11990),
	(4, 2, 1, 11990),
	(5, 1, 1, 45990),
	(6, 1, 1, 45990),
	(6, 3, 1, 19200),
	(7, 10, 2, 19980),
	(8, 7, 1, 65990),
	(8, 15, 1, 20990),
	(9, 13, 1, 109990),
	(10, 16, 1, 52990),
	(10, 18, 1, 86990),
	(11, 21, 1, 9990),
	(12, 22, 2, 63980),
	(12, 23, 1, 12990);

CREATE TABLE IF NOT EXISTS `orders` (
  `orderno` int(5) NOT NULL AUTO_INCREMENT,
  `customerid` int(5) NOT NULL,
  `ordertotal` decimal(10,2) NOT NULL,
  `orderdate` datetime NOT NULL,
  `shippeddate` datetime NOT NULL,
  PRIMARY KEY (`orderno`, `customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DELETE FROM `orders`;
INSERT INTO `orders` (`customerid`, `ordertotal`, `orderdate`, `shippeddate`) VALUES
	(23,19200,'2018-12-02 12:15:25','2018-12-07 11:15:21'),
	(12,11990,'2018-06-11 20:22:09','2018-06-19 08:45:55'),
	(29,11990,'2018-12-24 11:13:07','2019-01-05 08:15:22'),
	(8,11990,'2019-01-18 19:43:11','2019-01-19 13:30:10'),
	(1,45990,'2019-03-27 14:23:12','2019-04-01 15:15:01'),
	(45,65190,'2019-04-13 22:11:01','2019-04-18 14:10:11'),
	(15,19980,'2020-06-19 04:55:38','2020-06-20 09:01:21'),
	(13,86980,'2020-10-23 09:21:23','2020-10-30 16:01:12'),
	(28,109990,'2020-11-04 10:28:55','2020-12-01 15:18:10'),
	(45,139980,'2021-01-27 16:19:11','2021-02-01 11:21:32'),
	(31,9990,'2021-02-13 21:23:15','2021-02-15 08:00:15'),
	(29,76970,'2021-02-14 01:43:00','2021-02-15 11:15:00');