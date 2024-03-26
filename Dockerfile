FROM openjdk:11-slim
WORKDIR /app
COPY build/libs/top10-0.0.1-SNAPSHOT.jar top10-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "top10-0.0.1-SNAPSHOT.jar"]