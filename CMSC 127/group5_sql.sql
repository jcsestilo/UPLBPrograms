-- Based this on hospital.sql
-- Drop database if it exists
DROP DATABASE IF EXISTS `todolist`;
-- Create database
CREATE DATABASE IF NOT EXISTS `todolist`;
USE `todolist`;

-- Create USER table
CREATE TABLE IF NOT EXISTS `user`(
	userid INT NOT NULL AUTO_INCREMENT,
	fname VARCHAR(30) NOT NULL,
	mname VARCHAR(30) NOT NULL,
	lname VARCHAR(30) NOT NULL,
	emailid VARCHAR(50) NOT NULL,
	pass VARCHAR(20) NOT NULL,
	CONSTRAINT user_userid_pk PRIMARY KEY (`userid`)
);

-- Create CATEGORY table
CREATE TABLE IF NOT EXISTS `category`(
	categoryid INT NOT NULL AUTO_INCREMENT,
	categoryname VARCHAR(50) NOT NULL,
	CONSTRAINT category_categoryid_pk PRIMARY KEY (`categoryid`)
);

-- Create TASK table
CREATE TABLE IF NOT EXISTS `task`(
	taskid INT NOT NULL AUTO_INCREMENT,
	dateinputted DATE NOT NULL,
	title VARCHAR(50) NOT NULL,
-- deadline attribute for a task is optional
	deadline DATE, 
	taskstatus VARCHAR(10) NOT NULL,
	userid INT NOT NULL,
	categoryid INT NOT NULL,
	CONSTRAINT task_taskid_pk PRIMARY KEY (`taskid`),
	CONSTRAINT task_userid_fk FOREIGN KEY (`userid`) REFERENCES user(`userid`),
	CONSTRAINT task_categoryid_fk FOREIGN KEY (`categoryid`) REFERENCES category(`categoryid`)
);

-- Insert values into user
INSERT INTO user (fname, mname, lname, emailid, pass) VALUES
('Jan Coleen', 'S', 'Estilo', 'jsestilo@up.edu.ph', 'HelloWorld'),
('Gwyneth', 'B', 'Balucio', 'gbbalucio@up.edu.ph', 'GoodbyeWorld'),
('Alyssa', 'D', 'Pinote', 'adpinote@up.edu.ph', 'ImBackWorld'),
('Terri', 'M', 'Piell', 'tpiell3@businessinsider.com', 'ThisIsMyPassword');

-- Insert values into category; change if erd has aggregation
INSERT INTO category (categoryname) VALUES
("Academics"),
("Personal"),
("Org Works"),
("Lovelife");


-- Insert values into task
INSERT INTO task (dateinputted, title, deadline, taskstatus, userid, categoryid) VALUES
('2020-04-15', 'Project SQL', '2022-05-09', "Pending", 3, 2),
('2021-04-05', 'Requirement 08', '2022-05-10', "Completed", 2, 4),
('2020-04-15', 'Essay', '2023-05-10', "Pending", 2, 1),
('2021-04-05', 'Programming Assignment', '2022-04-14', "Pending", 1, 2),
('2019-09-20', 'Analysis', '2023-11-28', "Pending", 1, 4),
('2021-07-04', 'SP', '2022-09-03', "Completed", 4, 2),
('2019-01-31', 'Research Paper Chapter 1', NULL, "Completed", 4, 3);

-- Sample query for add to user table
INSERT INTO user (fname, mname, lname, emailid, pass) VALUES
('Brynna', 'F', 'Presland', 'bpresland4@china.com.cn', 'BrynnaGandaWalangTitibag');

-- Sample query for add to category table
INSERT INTO category (categoryname) VALUES
("To Watch");

-- Sample query for add to task table
INSERT INTO task (dateinputted, title, deadline, taskstatus, userid, categoryid) VALUES
('2018-05-19', 'Review for Exam', NULL, "Pending", 3, 4);

-- Sample query for edit row in user table
UPDATE user SET fname = "John Coleen", lname = "Astilla" 
WHERE userid = 1;

-- Sample query for edit row in category table
UPDATE category SET categoryname = "Academic Works" 
WHERE categoryid = 1;

-- Sample query for edit row in task table
UPDATE task SET title = "SQL DBMS Project" 
WHERE taskid = 1;

-- Sample query for marking task as done
UPDATE task SET taskstatus = "Completed" 
WHERE taskid = 1;

-- Sample query for delete row from user table
DELETE FROM user 
WHERE userid = 4;

-- Sample query for delete row from category table
DELETE FROM category 
WHERE categoryid = 4;

-- Sample query for delete row from task table
DELETE FROM task 
WHERE taskstatus = 2;

-- Sample select query statement after user log in successful (show tasks and grouped by category)
SELECT * FROM task
ORDER BY categoryid;

-- Sample select query statement for viewing tasks in one category only
SELECT * FROM task
WHERE categoryid = 2;

-- Sample select query for viewing tasks per day/per month (optional)

-- Per Month
SELECT * FROM task
WHERE MONTH(dateinputted) = "1";

--Per Day
SELECT * FROM task
WHERE DAY(dateinputted) = "15";