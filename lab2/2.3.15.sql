SELECT MBLOG.BID,TITLE,LID
FROM MBLOG LEFT OUTER JOIN B_L
ON  MBLOG.BID = B_L.BID
WHERE MBLOG.BID IN
(
/*
20190420 �����в���ID
*/
SELECT BID
FROM TOPDAY 
WHERE TYEAR = 2019
AND TMONTH = 4
AND TDAY = 20
)
