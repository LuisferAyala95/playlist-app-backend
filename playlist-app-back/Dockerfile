FROM eclipse-temurin:17-jdk-jammy

COPY . /app
WORKDIR /app

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/playlist-app-back-1.0-SNAPSHOT.jar"]

