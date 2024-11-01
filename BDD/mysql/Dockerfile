From mysql:latest

ADD ./creat_bdd_mysql.sql /docker-entrypoint-initdb.d

ENV MYSQL_ROOT_PASSWORD=sqlJeTaime
ENV MYSQL_USER=nat
ENV MYSQL_PASSWORD=sqlJeTaime

EXPOSE 3307

CMD ["mysqld"]