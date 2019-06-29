SELECT *
 FROM [USER] 
 WHERE [UID] IN 
 (/*
		找到张三关注的所有人
 */
		SELECT UIDFLED
		FROM  FOLLOW
		WHERE [UID] = (/*
											 张三的UID
										*/
										SELECT [UID] 
										FROM [USER] 
										WHERE [NAME] = '张三'
										)
)
ORDER BY [BYEAR]DESC,[UID] ASC