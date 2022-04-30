-- 1
SELECT CONCAT(collegefullname, ' (', collegename, ')') AS 'College Name', yearestablished AS 'Year Established', (YEAR(CURDATE())-yearestablished) AS 'Years Since Established' FROM college;
-- 2
SELECT semester, academicyear, coursenumber, sectionname, numberofstudents, CEILING(numberofstudents/15) AS 'Recommended Labs' FROM offering WHERE coursenumber LIKE 'MATH%' AND islecture='Y';
-- 3
SELECT CONCAT(fname, ' ',lname) AS 'Full Name', degree, FLOOR(DATEDIFF(CURDATE(), bday)/365) AS 'Age' FROM student WHERE yrlevel='SR';
-- 4
SELECT degree AS 'Acronym', fulldegreename AS 'Name', ROUND(noofunitsrequired-(noofunitsrequired*0.05), 0) AS 'Adjusted Num of Required Units', offeringcollege, offeringunit FROM degreeprog;
-- 5
SELECT CASE WHEN prerequisite IS NULL THEN CONCAT('NONE -> ', coursenumber, ' (', coursetitle, ')') ELSE CONCAT(prerequisite, ' -> ', coursenumber, ' (', coursetitle, ')') END AS 'Course Flow' FROM course;
-- 6
SELECT CASE WHEN semester='mid' THEN CONCAT('Midyear ', academicyear) ELSE CONCAT(semester, " Semester AY ",academicyear) END AS 'Semester & AY', coursenumber, sectionname, numberofstudents FROM offering;
-- 7
SELECT studno, lname,fname, mname, unitsearned, CASE WHEN YEAR(bday) BETWEEN 1991 AND 1995 THEN 'Batch 1' WHEN YEAR(bday) BETWEEN 1996 AND 2000 THEN 'Batch 2' ELSE 'Batch 3' END AS 'Batch Order' FROM student WHERE degree = 'BSCS';
-- 8
SELECT *, CASE WHEN numberofstudents<100 AND islecture='Y' THEN 'Low' WHEN numberofstudents>=100 AND islecture='Y' THEN 'Regular' ELSE '-' END AS 'Class Size' FROM offering;
-- 9
SELECT coursenumber, coursetitle, prerequisite FROM course WHERE unitearned=5 AND coursenumber IN (SELECT coursenumber FROM offering WHERE islecture='Y');
-- 10
SELECT CONCAT(fname, ' ', lname) AS 'Full Name', degree, unitsearned FROM student WHERE degree IN (SELECT degree FROM degreeprog WHERE noofunitsrequired=144);