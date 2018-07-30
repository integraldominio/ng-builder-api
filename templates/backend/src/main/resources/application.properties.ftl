spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.open-in-view=true
spring.h2.console.enabled=true
server.port=${projeto.serverPort}

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true

jwt.header=Authorization
jwt.secret=mySecret
jwt.expiration=604800
jwt.route.authentication.path=/auth
jwt.route.authentication.refresh=/refresh
 