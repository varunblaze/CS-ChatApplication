FROM openjdk:8-jre-slim
RUN unzip ./depends/ChatScriptNLP.zip
RUN sh ./depends/ChatScriptNLP/ChatScriptNLP/BINARIES/LinuxChatScript64
COPY ./depends/camel-chatscript-1.0-SNAPSHOT.jar /usr/src/oldmadras/
COPY ./target/CSChatApplication-0.0.1-SNAPSHOT.jar /usr/src/oldmadras/
WORKDIR /usr/src/oldmadras
EXPOSE 8080
CMD ["java", "-jar", "CSChatApplication-0.0.1-SNAPSHOT.jar"]
