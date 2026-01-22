# Build
FROM gradle:jdk21-jammy AS build

WORKDIR /app

# Copia os arquivos de configuração
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle ./gradle

# Copia o código fonte
COPY src ./src

# Converte quebras de linha do Windows para Linux usando sed
RUN chmod +x gradlew && \
    sed -i 's/\r$//' gradlew

# Executa o build
RUN ./gradlew bootJar -x test

#Run
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]