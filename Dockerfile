# Usar a imagem do JDK 8
FROM openjdk:8-jdk-alpine

# Definir o diretório de trabalho no container
WORKDIR /app

# Copiar o arquivo JAR gerado para dentro do container
COPY target/processo_seletivo.jar /app/processo_seletivo.jar

# Informar ao Docker que a aplicação será exposta na porta 8080
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "processo_seletivo.jar"]
