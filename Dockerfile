FROM docker.khb.hu/stock/openjdk:8-jdk-alpine

ARG APP_VERSION=1.0-SNAPSHOT

#ADD src/main/resources/application.properties                /opt/app/application.properties
ADD target/spring-camel-cluster-singleton-${APP_VERSION}.jar /opt/app/app.jar

ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]