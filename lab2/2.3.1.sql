SELECT *
 FROM [USER] 
 WHERE [UID] IN 
 (/*
		�ҵ�������ע��������
 */
		SELECT UIDFLED
		FROM  FOLLOW
		WHERE [UID] = (/*
											 ������UID
										*/
										SELECT [UID] 
										FROM [USER] 
										WHERE [NAME] = '����'
										)
)
ORDER BY [BYEAR]DESC,[UID] ASC