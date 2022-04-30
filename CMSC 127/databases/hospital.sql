DROP DATABASE IF EXISTS `hospital`;
CREATE DATABASE IF NOT EXISTS `hospital`;
USE `hospital`;

CREATE TABLE IF NOT EXISTS `physician` (
	prcid VARCHAR(7) NOT NULL,
	fname VARCHAR(20) NOT NULL,
	lname VARCHAR(20) NOT NULL,
	mname VARCHAR(20) NOT NULL,
	gender VARCHAR(1) NOT NULL,
	bday DATE NOT NULL,
	email VARCHAR(30) NOT NULL,
	deptno INT(5)  NOT NULL,
	spno INT(5),
	PRIMARY KEY (`prcid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into physician (prcid, fname, lname, mname, gender, bday, email, deptno, spno) values
	('9572756', 'Hoebart', 'Sagrott', 'Ribeiro', 'M', '1968-11-30', 'hribeiro0@merriam-webster.com', 1, NULL),
	('1365403', 'Saul', 'Mackison', 'Pitceathly', 'M', '1969-03-07', 'spitceathly1@princeton.edu', 1, NULL),
	('3584564', 'Viv', 'Clineck', 'Begwell', 'F', '1969-02-10', 'vbegwell2@google.de', 1, NULL),
	('4139815', 'Kilian', 'Bamforth', 'Lythgoe', 'M', '1968-10-21', 'klythgoe3@rakuten.co.jp', 1, NULL),
	('1605301', 'Ingram', 'Jossum', 'Francillo', 'M', '1966-07-31', 'ifrancillo4@purevolume.com', 1, NULL),
	('2719806', 'Vincents', 'Wickrath', 'Gascoyen', 'M', '1967-04-22', 'vgascoyen0@yellowbook.com', 2, 3),
	('4938332', 'Casper', 'Passman', 'Minear', 'F', '1965-07-23', 'cminear1@goo.gl', 2, 3),
	('6911750', 'Maisey', 'Rook', 'Bride', 'F', '1968-08-31', 'mbride2@cyberchimps.com', 2, 2),
	('2384225', 'Barbey', 'Grimme', 'Kubecka', 'F', '1966-06-10', 'bkubecka3@privacy.gov.au', 2, 4),
	('5339023', 'Hertha', 'Beamont', 'Grene', 'F', '1966-10-30', 'hgrene4@google.nl', 2, 2),
	('9110774', 'Dori', 'Broady', 'Manvelle', 'F', '1965-11-13', 'dmanvelle0@hp.com', 3, NULL),
	('7668537', 'Matilde', 'Garci', 'Kroon', 'F', '1968-09-04', 'mkroon1@bloomberg.com', 3, NULL),
	('7475151', 'Rollins', 'Louch', 'Balhatchet', 'F', '1969-11-05', 'rbalhatchet2@spiegel.de', 3, NULL),
	('6790566', 'Zach', 'Lloyd', 'Elrick', 'M', '1969-12-18', 'zelrick3@latimes.com', 3, NULL),
	('2063467', 'Laure', 'Durgan', 'Rivallant', 'F', '1967-11-05', 'lrivallant4@slashdot.org', 3, NULL),
	('1219620', 'Gabbie', 'Plampeyn', 'Tod', 'F', '1968-10-06', 'gtod0@blogger.com', 4, 7),
	('6618291', 'Lilias', 'Jutson', 'Goult', 'F', '1967-04-27', 'lgoult1@miibeian.gov.cn', 4, 7),
	('5209794', 'Wanda', 'Alfonso', 'Baverstock', 'F', '1968-03-31', 'wbaverstock2@macromedia.com', 4, 7),
	('4179403', 'Imogene', 'Fritter', 'Ben-Aharon', 'M', '1966-03-23', 'ibenaharon3@harvard.edu', 4, 5),
	('1452300', 'Kristoffer', 'Graeber', 'Nurdin', 'M', '1965-03-11', 'knurdin4@dell.com', 4, 9);	

CREATE TABLE IF NOT EXISTS `department` (
	deptno INT(5) NOT NULL,
	deptname VARCHAR(50) NOT NULL,
	deptdateestablished DATE NOT NULL,
	PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into department (deptno, deptname, deptdateestablished) values
	(1, 'Department of Ophthalmology', '1983-08-01'),
	(2, 'Department of Obstetrics and Gynecology', '1987-06-27'),
	(3, 'Department of Internal Medicine', '1988-05-21'),
	(4, 'Department of Ophthalmology', '1980-09-29'),
	(5, 'Department of Surgery', '1987-05-23');

CREATE TABLE IF NOT EXISTS `specialization` (
	spno INT(5) NOT NULL,
	spname VARCHAR(35) NOT NULL,
	spdateestablished DATE NOT NULL,
	deptno INT(5) NOT NULL,
	PRIMARY KEY (`spno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into specialization (spno, spname, spdateestablished, deptno) values
	(1, 'Gastroenterology', '1988-03-31', 2),
	(2, 'Rheumatology', '1984-12-27', 2),
	(3, 'Pulmonology', '1983-08-01', 2),
	(4, 'Neurology', '1989-11-15', 2),
	(5, 'General Surgery', '1987-05-23', 4),
	(6, 'Neurosurgery', '1990-07-09', 4),
	(7, 'Pediatrics', '1988-07-12', 4),
	(8, 'Plastic Surgery', '1987-06-05', 4),
	(9, 'Thoracic and Cardiovascular Surgery', '1988-10-14', 4);

CREATE TABLE IF NOT EXISTS `patient` (
	patientid INT(5) NOT NULL,
	lname VARCHAR(20) NOT NULL,
	fname VARCHAR(20) NOT NULL,
	mname VARCHAR(20) NOT NULL,
	gender VARCHAR(1) NOT NULL,
	bday DATE NOT NULL,
	email VARCHAR(30),
	contact VARCHAR(20) NOT NULL,
  PRIMARY KEY (`patientid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into patient (patientid, lname, fname, mname, gender, bday, email, contact) values
	(1, 'Durand', 'Cockrem', 'Driutti', 'M', '1992-12-24', 'ddriutti0@hostgator.com', '684-59-7309'),
	(2, 'Gizela', 'Chazelle', 'Accum', 'F', '2020-12-03', 'gaccum1@pcworld.com', '243-82-3294'),
	(3, 'Schuyler', 'Dunkley', 'Sisneros', 'M', '2002-06-11', 'ssisneros2@themeforest.net', '409-49-1706'),
	(4, 'Terri', 'Millthorpe', 'Piell', 'M', '1996-02-01', 'tpiell3@businessinsider.com', '657-08-0678'),
	(5, 'Brynna', 'Fleeming', 'Presland', 'F', '2012-10-15', 'bpresland4@china.com.cn', '860-08-3451'),
	(6, 'Sharline', 'Hudson', 'Matton', 'F', '2014-07-01', 'smatton5@salon.com', '248-48-0928'),
	(7, 'Mallory', 'Sargent', 'Cunnah', 'M', '2008-07-01', 'mcunnah6@youtube.com', '549-36-0737'),
	(8, 'Hughie', 'Cham', 'Twine', 'M', '1992-05-22', NULL, '684-97-4711'),
	(9, 'Antonia', 'Smallwood', 'Klimsch', 'F', '2017-08-14', 'aklimsch8@home.pl', '806-64-1533'),
	(10, 'Ashby', 'Bulloch', 'Fetters', 'M', '1996-09-09', 'afetters9@cbsnews.com', '506-06-2003'),
	(11, 'Constantin', 'Dieton', 'Duguid', 'M', '2006-11-08', 'cduguida@infoseek.co.jp', '451-08-8162'),
	(12, 'Lanni', 'Scotchbourouge', 'Colliss', 'F', '2017-06-29', 'lcollissb@lycos.com', '811-47-0535'),
	(13, 'Willdon', 'Elmes', 'Lyons', 'M', '2005-11-02', 'wlyonsc@jimdo.com', '756-33-7609'),
	(14, 'Shannan', 'Flageul', 'Brendel', 'M', '2000-07-07', 'sbrendeld@pagesperso-orange.fr', '764-02-0630'),
	(15, 'Sauveur', 'Jobbing', 'Crofts', 'M', '2010-06-19', 'scroftse@webs.com', '689-71-2549'),
	(16, 'Jedd', 'Cayette', 'Dancy', 'M', '2008-06-11', 'jdancyf@miitbeian.gov.cn', '135-11-5547'),
	(17, 'Deeyn', 'Good', 'Cordle', 'F', '2019-02-18', 'dcordleg@foxnews.com', '712-83-9092'),
	(18, 'Faustine', 'Willshaw', 'Hammersley', 'F', '2003-06-14', 'fhammersleyh@woothemes.com', '233-94-7039'),
	(19, 'Henrie', 'Jope', 'McCarney', 'F', '1991-11-21', 'hmccarneyi@jiathis.com', '854-78-8703'),
	(20, 'Henry', 'Threadgill', 'Hollingby', 'M', '2005-01-24', 'hhollingbyj@amazon.de', '210-79-0857'),
	(21, 'Nerta', 'Revans', 'Raspison', 'F', '2022-02-17', NULL, '245-54-3178'),
	(22, 'Goldi', 'Talmadge', 'Rosenkrantz', 'F', '2002-08-29', 'grosenkrantzl@angelfire.com', '562-62-3906'),
	(23, 'Randell', 'Furneaux', 'Shellsheere', 'M', '2000-12-17', 'rshellsheerem@sohu.com', '453-13-2667'),
	(24, 'Iolanthe', 'Jozefowicz', 'Birwhistle', 'F', '1993-07-26', 'ibirwhistlen@ovh.net', '795-34-2417'),
	(25, 'Remy', 'Risbie', 'O'' Kelleher', 'F', '1997-10-13', 'rokellehero@tinypic.com', '871-52-2739'),
	(26, 'Corinna', 'Shinefield', 'McFeate', 'F', '1994-03-03', 'cmcfeatep@netscape.com', '745-56-0890'),
	(27, 'Vaughn', 'Yellowlee', 'Doctor', 'M', '2007-01-05', 'vdoctorq@wikipedia.org', '233-24-9492'),
	(28, 'Ilaire', 'Bartoloma', 'Trigg', 'M', '2011-04-15', 'itriggr@163.com', '523-73-0014'),
	(29, 'Kip', 'Allcott', 'Bullas', 'M', '2013-11-01', 'kbullass@admin.ch', '518-41-6432'),
	(30, 'Dougie', 'Overill', 'O''Driscoll', 'M', '1990-05-30', 'dodriscollt@macromedia.com', '431-34-5131'),
	(31, 'Corney', 'Whenman', 'Engelmann', 'M', '1994-08-27', 'cengelmannu@goodreads.com', '229-92-5385'),
	(32, 'Ernestine', 'Woolmore', 'Lippett', 'F', '2012-08-16', 'elippettv@wunderground.com', '511-60-0798'),
	(33, 'Amble', 'Otterwell', 'Sawell', 'M', '2008-10-14', 'asawellw@list-manage.com', '472-36-4721'),
	(34, 'Greta', 'Gregh', 'Balcock', 'F', '2021-09-22', 'gbalcockx@ucsd.edu', '452-62-0416'),
	(35, 'Karlotta', 'Sinclar', 'Joddins', 'F', '2007-01-21', 'kjoddinsy@printfriendly.com', '744-68-6325'),
	(36, 'Mead', 'Shilton', 'Greetland', 'F', '2000-03-28', 'mgreetlandz@hc360.com', '337-19-3809'),
	(37, 'Powell', 'Ivanenkov', 'Cordel', 'M', '2009-12-09', 'pcordel10@canalblog.com', '415-75-7533'),
	(38, 'Bourke', 'Heinert', 'Gornall', 'M', '2000-07-28', 'bgornall11@virginia.edu', '373-21-4186'),
	(39, 'Jarret', 'Trew', 'Speck', 'M', '1994-07-04', 'jspeck12@last.fm', '796-76-6885'),
	(40, 'Fanni', 'Deaton', 'Zebedee', 'F', '2002-07-19', 'fzebedee13@samsung.com', '752-91-4982'),
	(41, 'Beatrisa', 'Marians', 'Annett', 'F', '2017-05-06', 'bannett14@tumblr.com', '311-16-6487'),
	(42, 'Quinlan', 'Bauser', 'Gilardi', 'M', '1996-01-30', 'qgilardi15@163.com', '513-08-1690'),
	(43, 'Gordie', 'Grzegorek', 'Moloney', 'M', '2010-12-29', 'gmoloney16@reddit.com', '899-88-2581'),
	(44, 'Niel', 'Dimitresco', 'Wissbey', 'M', '2010-11-05', 'nwissbey17@aol.com', '514-62-9061'),
	(45, 'Ulick', 'Belcham', 'Comsty', 'M', '1999-07-27', 'ucomsty18@senate.gov', '581-19-1222'),
	(46, 'Jethro', 'Hutton', 'Alflatt', 'M', '2010-08-20', 'jalflatt19@is.gd', '726-54-3824'),
	(47, 'Dagmar', 'Ferres', 'Braundt', 'F', '1998-11-24', 'dbraundt1a@slashdot.org', '507-08-5765'),
	(48, 'Hailee', 'Krebs', 'Mahaddy', 'F', '2007-01-07', 'hmahaddy1b@qq.com', '819-65-0618'),
	(49, 'Eward', 'Saunter', 'Blakey', 'M', '2005-09-27', 'eblakey1c@ft.com', '579-14-2876'),
	(50, 'Stephine', 'Shanahan', 'Fernandes', 'F', '2002-11-18', 'sfernandes1d@whitehouse.gov', '410-32-6667');

CREATE TABLE IF NOT EXISTS `appointment` (
	appointmentid INT(5) NOT NULL,
	patientid INT(5) NOT NULL,
	prcid VARCHAR(7) NOT NULL,
	admitdate DATE NOT NULL,
	dischargedate DATE,
	philhealthid VARCHAR(50),
  PRIMARY KEY (`appointmentid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into appointment (appointmentid, patientid, prcid, admitdate, dischargedate, philhealthid) values
	(1, 1, '4938332', '2016-05-03', '2016-05-03', '0649511158'),
	(2, 2, '7475151', '2016-05-03', '2016-07-04', '7495106751'),
	(3, 6, '7475151', '2010-01-01', '2010-01-29', '9184617020'),
	(4, 4, '2063467', '2021-11-19', NULL, '1047964236'),
	(5, 8, '4938332', '2021-06-24', '2021-06-25', '0591758849'),
	(6, 3, '4938332', '2016-01-04', '2016-01-07', NULL),
	(7, 2, '2063467', '2019-12-12', '2019-12-28', NULL),
	(8, 7, '5209794', '2017-08-12', '2017-08-14', NULL),
	(9, 9, '4179403', '2019-12-12', '2020-01-08', '4115057203'),
	(10, 10, '1219620', '2017-08-12', '2017-08-12', '6040536578'),
	(11, 11, '1452300', '2018-12-20', '2018-12-21', '3493674465'),
	(12, 13, '9572756', '2017-08-21', '2017-09-18', '8832163152'),
	(13, 14, '1452300', '2014-09-25', '2015-08-29', '6482929206'),
	(14, 21, '1452300', '2016-05-23', '2016-06-30', '1401977669'),
	(15, 15, '1365403', '2021-06-07', '2021-06-19', '8669995972'),
	(16, 19, '9572756', '2020-02-05', NULL, NULL),
	(17, 20, '1365403', '2017-06-30', '2017-07-24', '8947970921'),
	(18, 29, '1365403', '2017-06-30', '2017-07-09', '8310949057'),
	(19, 35, '5209794', '2010-04-16', '2010-04-17', '9872471924'),
	(20, 50, '1219620', '2018-03-29', '2019-01-06', '6152909953'),
	(21, 13, '4938332', '2022-02-01', '2022-02-14', NULL);
