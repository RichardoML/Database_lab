SELECT BID
FROM THUMB
GROUP BY BID 
HAVING COUNT(*) > 1;