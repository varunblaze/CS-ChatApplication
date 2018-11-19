FROM openjdk:8-jre-slim
COPY ./target/CSChatApplication-0.0.1-SNAPSHOT.jar /usr/src/oldmadras/
WORKDIR /usr/src/oldmadras
EXPOSE 8080
CMD ["java", "-jar", "CSChatApplication-0.0.1-SNAPSHOT.jar"]
