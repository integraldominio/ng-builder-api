#spring.datasource.name=ngxbuilder
spring.datasource.driverClassName=org.h2.Driver
#spring.datasource.url=jdbc:h2:file:~/ngxbuilder;DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;
spring.jpa.hibernate.ddl-auto = none

spring.jpa.open-in-view=true
spring.h2.console.enabled=true
server.port=${projeto.serverPort}

spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
spring.jpa.properties.hibernate.use_sql_comments=false

logging.level.org.springframework.web=ERROR
logging.level.org.hibernate=ERROR
logging.level.ERROR
 
jwt.header=Authorization
jwt.secret=mySecret
jwt.expiration=604800
jwt.route.authentication.path=/auth
jwt.route.authentication.refresh=/refresh
