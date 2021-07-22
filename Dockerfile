
FROM openjdk:11-jdk-slim

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} convert-currency.jar

CMD exec java -jar /convert-currency.jar

