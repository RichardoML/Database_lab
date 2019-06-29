SELECT DISTINCT [UID]
FROM FRIENDS A
WHERE NOT EXISTS 
(
	SELECT*
	FROM FRIENDS B 
	WHERE
	B.[UID] = 5 AND 
	  NOT EXISTS
	(
			SELECT * 
			FROM FRIENDS C 
			WHERE C.[UID] = A.[UID]
			AND C.[FUID] = B.[FUID]
	)
)
AND [UID] <> 5
