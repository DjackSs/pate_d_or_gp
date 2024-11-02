From mysql:latest

# copy database create script in container
COPY ./creat_bdd_mysql.sql /docker-entrypoint-initdb.d/

CMD ["mysqld"]

# -------------------
# Insert data in new database inside container :

# 1) copy insert_bdd inside the container :
#		docker cp file.sql <container_id>:/insert.sql
		
# 2) enter the container :
#		docker exec -ti <container_id> bah
#		mysql -u<user> -p
#		use pate_d_or;

# 3) run the script :
#		source insert.sql
