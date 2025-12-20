# ========================================
# STAGE 1: Build da aplicação
# ========================================
FROM maven:3.9.11-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copia arquivos do Maven
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Baixa dependências (cache)
RUN ./mvnw dependency:go-offline -B

# Copia código fonte
COPY src ./src

# Compila (pula testes)
RUN ./mvnw clean package -DskipTests -B

# ========================================
# STAGE 2: Imagem final (Runtime)
# ========================================
FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="diego@example.com"
LABEL description="Clean Architecture Spring Boot API"
LABEL version="1.0"

# ========================================
# Instala pacotes necessários (root)
# ========================================
RUN apk add --no-cache netcat-openbsd

# Cria usuário não-root (segurança)
RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

# Copia JAR da stage anterior
COPY --from=builder /app/target/*.jar app.jar

# Ajusta permissões
RUN chown -R spring:spring /app

# Troca para usuário não-root
USER spring

# Exposição da porta da API
EXPOSE 8080

# Variáveis de ambiente padrão
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENV SPRING_PROFILES_ACTIVE=prod

# Healthcheck da API
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Entrypoint
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
