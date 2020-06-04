FROM openjdk:8 

RUN echo "Asia/Seoul" > /etc/timezone
RUN mkdir -p /app/logs
ADD app-1.0.war /app
EXPOSE 8080
WORKDIR /app
CMD ["java", "-jar", "app-1.0.war"]