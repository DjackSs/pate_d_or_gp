# tomcat image
FROM tomcat:10

# info about the container
LABEL name="Pate D'Or gp" 

WORKDIR /java-app

# creat the .war file
# add the war file in the tomcat webapps directory
ADD ./pate_d_or_gp.war /usr/local/tomcat/webapps/

# acces port of the app within the container
EXPOSE 8080

# run the tomcat
CMD [ "catalina.sh", "run" ]


#-------------------------------------
# 1)Create a .war file


#-------------------------------------
# 2)Creat and run the container

# 1 - create and tag image :
# docker build -t java-app:0.1 .

# 2-  create container
# docker container run --name java-app01 -p 8083:8080 -d java-app:0.1 

# 3-  app is runnig on http://localhost:8083/pate_d_or_gp/home

