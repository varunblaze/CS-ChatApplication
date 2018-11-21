FROM openjdk:8-jre-slim
ADD start_app.sh /root/start_app.sh
EXPOSE 8080
CMD ["./start_app.sh"]
