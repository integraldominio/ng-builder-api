# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update
spring.datasource.url=jdbc:mysql://localhost:3306/ngxbuilder
spring.datasource.username=root
spring.datasource.password=1234

spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
spring.jpa.properties.hibernate.use_sql_comments=false

# OPCAO CRIAR USER 
# CREATE USER 'ngxbuilder'@'localhost' IDENTIFIED WITH MYSQL_NATIVE_PASSWORD BY 'ngxbuilder';
# GRANT ALL ON ngxbuilder.* TO 'ngxbuilder'@'localhost';
# DROP USER 'ngxbuilder'@'localhost';

server.port=3000

jwt.header=Authorization
jwt.secret=mySecret
jwt.expiration=604800
jwt.route.authentication.path=/auth
jwt.route.authentication.refresh=/refresh
 

