-- 1
INSERT INTO enrolls(courseid, studno, sem, ay, grade) SELECT courseid, studno, 1, '22-23', CASE WHEN YEAR(CURDATE())-YEAR(s.bday)<20 THEN 1.00 ELSE 3.00 END FROM student AS s JOIN course AS c ON s.degree='BSCE' AND c.code='CMSC 150';
-- 2
INSERT INTO student(studno, lname, fname, mname, bday, degree) VALUES ('2015-10089', 'Pegg', 'Jean', 'Gunnhildr', STR_TO_DATE(CONCAT(YEAR(CURDATE())-21, '-03-14'), '%Y-%m-%d'), 'BSABM');
-- 3
DELETE FROM enrolls WHERE studno IN ((SELECT studno FROM student WHERE degree='BSCE'));
-- 4
UPDATE enrolls SET grade=(SELECT COUNT(*) FROM student WHERE degree='BSAP') WHERE ay='17-18';
-- 5
UPDATE enrolls SET grade=(SELECT AVG(e.grade) FROM enrolls e WHERE e.courseid=5) WHERE courseid=2 AND sem=2;
-- 6
INSERT INTO orders SELECT MAX(orderno)+1, (SELECT customerid FROM customer WHERE customerid=13), (SELECT SUM(itemqtyprice) FROM item WHERE YEAR(itemlaunchdate)<2021), MAX(orderdate), ADDDATE(MAX(orderdate), 1) FROM orders;
-- 7
DELETE FROM item WHERE itemno IN ((SELECT itemno FROM orderdetails));
-- 8
UPDATE item SET itemqtystock=(SELECT AVG(YEAR(CURDATE())-YEAR(bday)) FROM customer WHERE country IN ('Philippines', 'Japan')) WHERE itemqtystock>500 AND itemcompany IN ('Samsung', 'Apple', 'OnePlus');
-- 9
UPDATE orders SET orderdate=NOW(), shippeddate=ADDDATE(NOW(), 17) WHERE customerid=13;
-- 10, Ref: https://community.spiceworks.com/topic/1880611-delete-rows-from-a-sql-table-based-on-another-table
DELETE c FROM customer AS c JOIN orders AS o ON c.customerid=o.customerid AND o.ordertotal<17000;
-- 11
DELETE FROM customer WHERE customerid NOT IN ((SELECT customerid FROM orders)) AND YEAR(CURDATE())-YEAR(bday)<18;
