# Etapa 1: Build
FROM gradle:jdk21-jammy AS build

WORKDIR /app

# Copia os arquivos de configuração
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle ./gradle

# Copia o código fonte
COPY src ./src

# --- CORREÇÃO DO ERRO ---
# 1. Garante que o script tenha permissão de execução
# 2. Converte quebras de linha do Windows (CRLF) para Linux (LF) usando sed
RUN chmod +x gradlew && \
    sed -i 's/\r$//' gradlew

# Executa o build
RUN ./gradlew bootJar -x test

# Etapa 2: Run (Essa parte continua igual)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]