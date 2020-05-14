FROM docker.khb.hu/stock/openjdk:8-jdk-alpine

ADD target/${APP_BIN} /opt/app/app.war

ENTRYPOINT ["java", "--spring.config.location=classpath:/opt/app/application.properties", "-jar", "/opt/app/app.war"]