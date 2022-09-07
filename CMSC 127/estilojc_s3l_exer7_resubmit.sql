-- 1
INSERT INTO enrolls(courseid, studno, sem, ay, grade) SELECT courseid, studno, 1, '22-23', CASE WHEN YEAR(CURDATE())-YEAR(s.bday)<20 THEN 1.00 ELSE 3.00 END FROM student AS s JOIN course AS c ON s.degree='BSCE' AND c.code='CMSC 150';
-- 2
INSERT INTO student(studno, lname, fname, mname, bday, degree) VALUES ('2015-10089', 'Pegg', 'Jean', 'Gunnhildr', STR_TO_DATE(CONCAT(YEAR(CURDATE())-21, '-03-14'), '%Y-%m-%d'), 'BSABM');
-- 3
DELETE FROM enrolls WHERE studno IN ((SELECT studno FROM student WHERE degree='BSCS'));
-- 4
UPDATE enrolls SET grade=(SELECT COUNT(*) FROM student WHERE degree='BSAP') WHERE ay='17-18';
-- 5
UPDATE enrolls SET grade=(SELECT AVG(e.grade) FROM enrolls e WHERE e.courseid=(SELECT courseid FROM course WHERE code="CMSC 150")) WHERE courseid=(SELECT courseid FROM course WHERE code="CMSC 21") AND sem=2;
-- 6
INSERT INTO orders SELECT MAX(orderno)+1, (SELECT customerid FROM customer WHERE customerid=13), (SELECT SUM(itemqtyprice) FROM item WHERE YEAR(itemlaunchdate)<2021 AND itemcompany="Samsung"), MIN(orderdate), ADDDATE(MAX(orderdate), 1) FROM orders;
-- 7
DELETE FROM item WHERE itemno IN ((SELECT o.itemno FROM orderdetails o JOIN orders ord ON o.orderno=ord.orderno WHERE YEAR(ord.orderdate)=2018));
-- 8
UPDATE item SET itemqtystock=(SELECT AVG(YEAR(CURDATE())-YEAR(bday)) FROM customer WHERE country IN ('Philippines', 'Japan')) WHERE itemqtystock>500 AND itemcompany IN ('Samsung', 'Apple', 'Google');
-- 9
INSERT INTO orders (customerid, ordertotal, orderdate, shippeddate) 
SELECT 13,
ordertotal,
NOW(),
ADDDATE(NOW(), 17)
FROM orders WHERE customerid=13;
-- 10, Ref: https://community.spiceworks.com/topic/1880611-delete-rows-from-a-sql-table-based-on-another-table
DELETE c FROM customer AS c JOIN orders AS o 
ON c.customerid=o.customerid AND 
(SELECT SUM(o.ordertotal) FROM orders o WHERE o.customerid=c.customerid)<17000;
-- 11
DELETE FROM customer WHERE customerid NOT IN ((SELECT customerid FROM orders)) AND YEAR(CURDATE())-YEAR(bday)<18;
