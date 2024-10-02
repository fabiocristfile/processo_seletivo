# Use uma imagem do Maven 
FROM maven:3.8.5-openjdk-17 AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o pom.xml
COPY pom.xml . 

# Copie o restante dos arquivos do projeto
COPY src ./src

# Copie o pom.xml e os arquivos de wrapper do Maven
COPY mvnw ./
COPY mvnw.cmd ./
COPY .mvn ./.mvn 

# Copie o restante dos arquivos do projeto
COPY src ./src

## Dê permissão ao script mvnw
RUN chmod +x mvnw

# Limpe a variável de ambiente MAVEN_CONFIG
ENV MAVEN_CONFIG=""

# Execute o Maven para compilar o projeto
RUN ./mvnw clean package -DskipTests -X

# Use uma imagem leve para o aplicativo em execução
FROM openjdk:17-jdk-slim

# Copie o arquivo JAR gerado
COPY --from=build /app/target/processo-seletivo-2.0.0-FINAL.jar app.jar

# Comando para iniciar o aplicativo
ENTRYPOINT ["java", "-jar", "/app.jar"]