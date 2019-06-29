SELECT BID 
FROM MBLOG
WHERE UID IN
/*
	2000年后出生的武汉用户
*/
(SELECT [UID]
FROM [USER]
WHERE BYEAR >= 2000 AND CITY ='武汉'
)
AND
BID IN
/*
进入过头条的所有博文的ID
*/
(SELECT BID 
FROM TOPDAY
)