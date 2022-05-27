-- 1
CREATE TABLE unit(
    unitname VARCHAR(10),
    unitfullname VARCHAR(30),
    collegename VARCHAR(5) NOT NULL,
    yearestablished YEAR(4),
    numoffacultymembers INT DEFAULT(0),
    CONSTRAINT unit_collegename_fk FOREIGN KEY(collegename) REFERENCES college(collegename),
    CONSTRAINT unit_unitname_pk PRIMARY KEY (unitname),
    CONSTRAINT unit_unitname_uk UNIQUE (unitname)
);
-- 2
CREATE TABLE teacher AS (
    SELECT 
    DATE_FORMAT(bday, '%Y%d%m') AS employeenum,
    fname,
    lname, 
    bday, 
    degree
    FROM student);
-- 3
ALTER TABLE teacher CHANGE COLUMN `degree` `unit` VARCHAR(10);
-- 4
TRUNCATE TABLE teacher;
-- 5
ALTER TABLE teacher ADD CONSTRAINT teacher_unit_fk FOREIGN KEY (unit) REFERENCES unit(unitname);
-- 6
CREATE OR REPLACE VIEW degreeprog_avg_view (degree, average) AS 
SELECT dp.degree, (SELECT AVG(st.unitsearned) FROM student st WHERE st.degree=dp.degree) FROM degreeprog dp;
-- 7
ALTER TABLE teacher DROP bday;
-- 8
CREATE OR REPLACE VIEW degreeprog_avg_view (college, degree, average) AS 
SELECT dp.offeringcollege, dp.degree, (SELECT AVG(st.unitsearned) FROM student st WHERE st.degree=dp.degree) FROM degreeprog dp;
-- 9
ALTER TABLE unit DROP FOREIGN KEY unit_collegename_fk;
-- 10
DROP TABLE unit;