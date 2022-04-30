-- 1
SELECT CONCAT(fname, ' ',lname) AS 'Patient Name', gender AS 'Gender', bday AS 'Birthday', contact AS 'Contact Number' FROM patient JOIN appointment ON patient.patientid=appointment.patientid ORDER BY lname ASC; -- 1
--2 (Ref: https://stackoverflow.com/questions/31274442/counting-number-of-rows-with-foreign-keys)
SELECT p.prcid AS 'ID', lname AS 'Last Name', fname AS 'First Name', (SELECT COUNT(*) FROM appointment WHERE prcid=p.prcid) AS 'Patient Count', (SELECT MAX(admitdate) FROM appointment WHERE prcid=p.prcid) AS 'Latest Appointment' FROM physician AS p HAVING `Patient Count` > 1;
-- 3
SELECT prcid as 'PRC ID', lname AS 'Last Name', fname AS 'First Name', (SELECT deptname FROM department WHERE deptno=p.deptno) AS 'Department Name', CASE WHEN spno IS NULL THEN '-' ELSE (SELECT spname FROM specialization WHERE spno=p.spno) END AS 'Specialization' FROM physician AS p;
-- 4
SELECT p.lname AS 'Last Name', fname AS 'First Name', CASE WHEN spno IS NULL THEN '-' ELSE (SELECT spname FROM specialization WHERE spno=p.spno) END AS 'Specialization', (SELECT deptname FROM department WHERE deptno=p.deptno) AS 'Department Name', (SELECT COUNT(*) FROM appointment WHERE prcid=p.prcid) AS 'Number of Appointments' FROM physician AS p ORDER BY lname ASC, fname ASC;
-- 5
SELECT p.patientid AS 'Patient 1', 
(SELECT p2.patientid FROM patient p2 WHERE p2.patientid!=p.patientid AND (SELECT MIN(admitdate) FROM appointment WHERE patientid=p2.patientid)=(SELECT MIN(admitdate) FROM appointment WHERE patientid=p.patientid)) AS 'Patient 2', 
(SELECT MIN(admitdate) FROM appointment WHERE patientid=p.patientid) AS 'Date Admitted' 
FROM patient p WHERE (SELECT p2.patientid FROM patient p2 WHERE p2.patientid!=p.patientid AND (SELECT MIN(admitdate) FROM appointment WHERE patientid=p2.patientid)=(SELECT MIN(admitdate) FROM appointment WHERE patientid=p.patientid)) IS NOT NULL;