From mysql:latest

# copy database create script in container
COPY ./creat_bdd_mysql.sql /docker-entrypoint-initdb.d/

CMD ["mysqld"]

