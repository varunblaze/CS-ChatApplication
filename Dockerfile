FROM maven:3.5-jdk-8-alpine
WORKDIR usr/src/oldmadras/depends
RUN mvn install:install-file -Dfile=camel-chatscript-1.0-SNAPSHOT.jar

FROM openjdk:8-jre-slim
COPY ./target/CSChatApplication-0.0.1-SNAPSHOT.jar /usr/src/oldmadras/
WORKDIR /usr/src/oldmadras
EXPOSE 8080
CMD ["java", "-jar", "CSChatApplication-0.0.1-SNAPSHOT.jar"]
