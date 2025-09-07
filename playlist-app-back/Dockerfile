# 1️⃣ Imagen base con Java 17
FROM eclipse-temurin:17-jdk-jammy

# 2️⃣ Copiar todo el proyecto al contenedor
COPY . /app
WORKDIR /app

# 3️⃣ Construir la aplicación usando Maven
RUN mvn clean package -DskipTests

# 4️⃣ Exponer el puerto que usará Spring Boot
EXPOSE 8080

# 5️⃣ Comando para ejecutar la API
CMD ["java", "-jar", "target/playlist-app-back-1.0-SNAPSHOT.jar"]
