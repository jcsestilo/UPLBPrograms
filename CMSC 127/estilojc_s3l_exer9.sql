-- 1
CREATE USER director IDENTIFIED BY 'hospiDirect0r';
--2 
GRANT SELECT, INSERT, UPDATE ON hospital.* TO director;
-- 3
CREATE USER clerk IDENTIFIED BY 'hospiCl3rk';
-- 4
GRANT INSERT, DELETE ON hosptital.patient TO clerk;
-- 5
REVOKE INSERT ON hospital.* FROM director;
-- 6
START TRANSACTION;
SAVEPOINT newdocs;
INSERT INTO physician VALUES ('1234125', 'Christine', 'Palmer', 'Salvador', 'F', '1967-10-10', 'cpalmer@google.de', 4, 6);
-- 7
SAVEPOINT newhires;
-- 8
ROLLBACK TO newdocs;
-- 9
COMMIT;
-- 10
DROP USER clerk;