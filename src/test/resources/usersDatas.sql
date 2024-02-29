-- Suppression des données de la table users
DELETE FROM Users;

-- Insertion de données dans la table users
			(id	,name						,lastname							,email							,password											,token			,expiration_time					,role	)
	VAlUES
			(1	,'Custname1'				,'Custlastname1'					,'Custemail1'					,'Custpassword!1'									,null			,null								,'cust'	),
			(2	,'Adminame1'				,'Admilastname1'					,'Admiemail1'					,'Admipassword!1'									,null			,null								,'admi'	),
			(3	,'Stafname1'				,'Staflastname1'					,'Stafemail1'					,'Stafpassword!1'									,null			,null								,'staf'	),
			(4	,null						,null								,null							,null												,null			,null								,null	),
			(5	,'TestNoEmailName'			,'TestNoEmailLastname'				,null							,'TestNoEmailPassword'								,null			,null								,'cust'	),
			(6	,'name1'				,'lastname1'							,'name1lastname1'				,'password!1'										,null			,null								,'cust'	),
			(7	,'name1'				,'lastname1'							,'name1lastname1'				,'password!1'										,null			,null								,'cust'	),
			(8	,'name1'				,'lastname1'							,'name1lastname1'				,'password!1'										,null			,null								,'cust'	),
			(9	,'name1'				,'lastname1'							,'name1lastname1'				,'password!1'										,null			,null								,'cust'	),