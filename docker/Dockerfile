FROM openjdk:8 

RUN mkdir -p /app/logs
ADD app-1.0.war /app
EXPOSE 8080
WORKDIR /app
CMD ["java", "-jar", "app-1.0.war"]