FROM openjdk:17
ENV JAR_FILE_NAME=kc-spring-boot-0.0.1-SNAPSHOT.jar
COPY target/*.jar $JAR_FILE_NAME
ENTRYPOINT java -jar $JAR_FILE_NAME
