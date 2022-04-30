USE eight-twelve; -- 1
DESC tblorderitems; -- 2
SELECT DISTINCT orderdate FROM tblorder ORDER BY orderdate ASC; -- 3
SELECT * FROM tblitem WHERE itemdesc IS NULL AND itemqtyprice<100; -- 4
SELECT * FROM tblorderitems WHERE orderno BETWEEN 2 AND 5 AND itemtotalprice%2=0; -- 5
SELECT itemno AS "Item Number", itemname AS "Menu Item", itemdesc AS "Description", itemqtyprice AS "Item Price" FROM tblitem WHERE itemname LIKE '%Fries%' OR itemname LIKE '%Milkshake%' ORDER BY itemqtyprice DESC; -- 6
SELECT orderno AS "Order Number", ordertotalprice+(ordertotalprice*0.05) AS "Total Price with Tax", orderdate AS "Date Ordered" FROM tblorder WHERE orderdate LIKE '2016%' AND ordertotalprice>=60; -- 7
SELECT itemname, itemqtyprice, itemqtyprice-(itemqtyprice*0.20) AS "itemsaleprice" FROM tblitem WHERE itemname LIKE '%Regular%' AND itemqtyprice>80 ORDER BY itemname DESC; -- 8
SELECT * FROM tblorder WHERE orderdate NOT LIKE '2020%' AND orderdate LIKE '____-05-__' OR orderdate LIKE '____-08-__' ORDER BY ordertotalprice DESC; -- 9
SELECT * FROM tblitem WHERE LENGTH(itemdesc)-LENGTH(REPLACE(itemdesc, ' ', '')) >= 3; -- 10