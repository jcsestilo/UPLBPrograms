-- 1
SELECT caseid AS "Case ID",
CASE WHEN status="Resolved" OR status="Charged" THEN CONCAT(casename, " (Done)")
ELSE CONCAT(casename, " (In Progress)") END AS "Case Information",
casedate AS "Date Filed",
location AS "Court Location"
FROM casefile WHERE casename LIKE '%Driving%';
-- 2
SELECT offenderid, lastname, firstname, middlename, suffix, alias, birthday,
CASE WHEN middlename IS NULL THEN CONCAT(LOWER(SUBSTRING(firstname, 1, 1)), LOWER(lastname), "@gmail.com")
ELSE CONCAT(LOWER(SUBSTRING(firstname, 1, 1)), LOWER(SUBSTRING(middlename, 1, 1)), LOWER(lastname), "@gmail.com") END 
AS "Google Email"
FROM offender;
-- 3
SELECT caseid, casename, status, casedate, 
CASE status WHEN "On-going" THEN ADDDATE(CURDATE(), 90)
WHEN "Resolving" THEN ADDDATE(CURDATE(), 30)
ELSE CURDATE() END AS "Resolution Date"
FROM casefile WHERE status IN ("On-going", "Resolving", "Resolved")
ORDER BY casedate ASC;
-- 4
SELECT offenderid,
CASE WHEN middlename IS NULL THEN CONCAT(lastname, ' ', firstname)
ELSE CONCAT(lastname, ' ', firstname, ' ', middlename) END AS 'Full Name'
FROM offender WHERE (YEAR(birthday)%4=0 AND YEAR(birthday)%100<>0) OR YEAR(birthday)%400=0;
-- 5
SELECT caseid AS "Case ID", 
casename AS "Case Name", 
ROUND(DATEDIFF(CURDATE(), casedate)/30, 0) AS "No. of Months", 
description AS "Case Description",
location AS "Case Location", 
offenderid AS "Case Offender" 
FROM casefile WHERE status="On-going" ORDER BY casename;
-- 6
SELECT 
CASE WHEN o.alias IS NULL THEN CONCAT(o.firstname, ' ', o.lastname)
ELSE CONCAT(o.firstname, ' "', o.alias, '" ', o.lastname) END AS 'Full Name',
c.casename AS "Case Name",
ch.chargename AS "Charge Name",
c.status AS "Case Status",
ch.status AS "Charge Status"
FROM offender AS o 
INNER JOIN casefile AS c ON o.offenderid=c.offenderid
INNER JOIN charge AS ch ON ch.caseid=c.caseid 
WHERE c.casename=ch.chargename AND ((c.status="Resolved" OR c.status="Charged") AND (ch.status="Resolved" OR ch.status="Charged"));
-- 7
SELECT o.lastname AS 'Last name',
o.firstname AS 'First name',
c.caseid AS 'Case id'
FROM casefile c JOIN offender o ON c.offenderid=o.offenderid 
WHERE c.caseid NOT IN ((SELECT caseid FROM accessoryperson))
ORDER BY c.caseid;
-- 8
SELECT c.caseid AS 'Case ID',
c.casename AS 'Case name',
c.offenderid AS 'Offender ID',
o.lastname AS 'Last name',
o.firstname AS 'First name'
FROM casefile c JOIN offender o ON c.offenderid=o.offenderid
WHERE c.caseid NOT IN ((SELECT caseid FROM charge WHERE status NOT IN ("Resolved", "Charged")));
-- 9
SELECT c.caseid,
c.location,
c.status,
o.alias AS "Offender",
a.lastname AS "Accessory"
FROM casefile c JOIN offender o ON c.offenderid=o.offenderid
JOIN accessoryperson a ON c.caseid=a.caseid
WHERE c.status<>"Resolved"
ORDER BY c.casename DESC;
-- 10
SELECT o.lastname,
o.firstname,
o.alias,
c.casename,
(SELECT COUNT(*) FROM accessoryperson a WHERE a.caseid=c.caseid) AS 'countac'
FROM offender o JOIN casefile c ON o.offenderid=c.offenderid
WHERE (SELECT COUNT(*) FROM accessoryperson a WHERE a.caseid=c.caseid)>2;
-- 11
SELECT c.caseid,
(SELECT COUNT(*) FROM accessoryperson a WHERE a.caseid=c.caseid) AS `Number of Accessory Persons`
FROM casefile c WHERE (SELECT COUNT(*) FROM accessoryperson a WHERE a.caseid=c.caseid)>3
ORDER BY `Number of Accessory Persons` DESC;
-- 12
SELECT chargename,
isbailable,
MIN(bailsetamountinusd),
MAX(bailsetamountinusd),
AVG(bailsetamountinusd)
FROM charge GROUP BY chargename
ORDER BY isbailable, chargename;
-- 13
SELECT o.lastname,
o.firstname,
c.caseid,
ch.bailsetamountinusd,
(ch.bailsetamountinusd/((SELECT COUNT(*) FROM accessoryperson a WHERE a.caseid=c.caseid)+1)) AS 'Bail Contribution'
FROM offender o JOIN casefile c ON o.offenderid=c.caseid
JOIN accessoryperson a ON a.caseid=c.caseid
JOIN charge ch ON ch.caseid=c.caseid;
-- 14
SELECT o.offenderid,
o.lastname,
o.firstname,
CASE ch.status WHEN "Resolved" THEN ch.bailsetamountinusd
ELSE NULL END AS 'Total Bail Amount in USD'
FROM offender o LEFT JOIN casefile c ON o.offenderid=c.offenderid
JOIN charge ch ON c.caseid=ch.caseid
WHERE ch.bailsetamountinusd>50000;
-- 15
SELECT ch.chargename,
(SELECT COUNT(*) FROM casefile c WHERE c.casename=ch.chargename) AS 'Cases under this Charge',
ch.status,
AVG(ch.bailsetamountinusd) AS 'Average Bail Amount'
FROM charge ch GROUP BY ch.chargename
ORDER BY ch.chargename, ch.status;
-- 16
UPDATE charge SET bailsetamountinusd=
	CASE WHEN bailsetamountinusd<10000 THEN bailsetamountinusd+(bailsetamountinusd*.03)
	WHEN bailsetamountinusd<90000 THEN bailsetamountinusd+(bailsetamountinusd*.13)
	ELSE bailsetamountinusd+(bailsetamountinusd*.17) END
WHERE status NOT IN ("Resolved", "Charged");
-- 17
CREATE VIEW offender_case AS 
SELECT o.offenderid, o.lastname, o.firstname, 
c.caseid, c.casename, c.casedate
FROM offender o JOIN casefile c ON o.offenderid=c.offenderid;
-- 18
UPDATE casefile c SET status="Resolved"
WHERE YEAR(CURDATE())-(SELECT YEAR(birthday) FROM offender o WHERE o.offenderid=c.offenderid)>65;
-- 19
CREATE TABLE judges(
judgeid INT NOT NULL,
lastname VARCHAR(20) NOT NULL,
firstname VARCHAR(20) NOT NULL,
rank VARCHAR(10) NOT NULL,
maincourtlocation VARCHAR(30),
birthday DATE NOT NULL,
CONSTRAINT judges_judgeid_uk UNIQUE(judgeid)
);
-- 20
DELETE FROM accessoryperson 
WHERE caseid IN 
((SELECT c.caseid 
	FROM casefile c JOIN charge ch 
	ON c.caseid=ch.caseid
	WHERE c.status="Resolved" AND ch.isbailable="Yes"));
-- BONUS
SELECT 
CONCAT(SUBSTRING(firstname, 1, 2), SUBSTRING(lastname, 8, 8), SUBSTRING(firstname, 2, 1), RIGHT(firstname, 1), SUBSTRING(firstname, 2, 1)) AS '', 
CONCAT(SUBSTRING(lastname, 1, 1), RIGHT(middlename, 1), LOWER(SUBSTRING(lastname, 1, 1)), RIGHT(middlename, 1)) AS '',
CONCAT(SUBSTRING(firstname, 2, 1), RIGHT(firstname, 1), SUBSTRING(firstname, 2, 1), SUBSTRING(middlename, 3, 1)) AS '',
CONCAT(LEFT(middlename, 1), SUBSTRING(middlename, 3, 1)) AS '',
CONCAT(SUBSTRING(lastname, 7, 1), SUBSTRING(firstname, 2, 1)) AS '',
CONCAT(SUBSTRING(middlename, 5, 1), RIGHT(middlename, 1), LEFT(lastname, 1)) AS ''
FROM offender WHERE offenderid=28;