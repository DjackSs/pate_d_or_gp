From mysql:latest

ENV MYSQL_ROOT_PASSWORD=hello
ENV MYSQL_USER=nat
ENV MYSQL_PASSWORD=sqlJeTaime
ENV MYSQL_DATABASE=pate_d_or

# copy database create script in container
COPY ./creat_bdd_mysql.sql /docker-entrypoint-initdb.d/

EXPOSE 3307

CMD ["mysqld"]