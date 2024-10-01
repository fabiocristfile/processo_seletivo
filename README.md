# Projeto de Gestão de Diretórios e Arquivos

Este projeto é uma aplicação para gerenciar diretórios e arquivos, permitindo operações de CRUD (Criar, Ler, Atualizar e Deletar) em um sistema de diretórios. A aplicação utiliza Spring Boot para o backend e React.js para o frontend, com comunicação em tempo real utilizando WebSockets.

## Funcionalidades

- **CRUD de Diretórios**: Criar, ler, atualizar e deletar diretórios.
- **CRUD de Arquivos**: Criar, ler, atualizar e deletar arquivos dentro dos diretórios.
- **Hierarquia de Diretórios**: Visualização e navegação em subdiretórios.
- **Comunicação em Tempo Real**: Notificações sobre alterações em arquivos e diretórios utilizando WebSockets.
- **Documentação da API**: Documentação da API disponível através do Swagger.

## Tecnologias Utilizadas

- **Backend**: 
  - Backend: Spring Boot
  - Frontend: React.js
  - Banco de Dados: H2
  - Testes: JUnit 5, Mockito
  - Comunicação em Tempo Real: WebSockets, STOMP, SockJS
  - Documentação: Swagger
    
- **Frontend**: 
  - React.js
  - Axios (para chamadas HTTP)
  - CSS para estilização

## Estrutura do Projeto
```
├── backend
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   └── resources
│   │   └── test
├── frontend
│   ├── src
│   │   ├── components
│   │   ├── services
│   │   └── styles
├── README.md
```
## Como Executar o Projeto
- **Backend**:
  1. Navegue até a pasta do backend.
  2. Compile e execute a aplicação usando:
     ```
        ./mvnw spring-boot:run
     
     ```
  3. Acesse a API em: http://localhost:8080
- **Frontend**:
1. Navegue até a pasta do frontend.
2. Instale as dependências:
```
npm install
```
3. Execute a aplicação:
```
npm start
```
4. Acesse o frontend em: http://localhost:3000

## Documentação da API
A documentação da API está disponível no Swagger. Após iniciar o backend, acesse: http://localhost:8080/swagger-ui.html

## Configuração do Ambiente
Pré-requisitos
Java 17 ou superior
Maven
Node.js e npm (para o frontend)

## Instalação
1. Clone o repositório: git clone
```
https://github.com/seu_usuario/seu_repositorio.git
cd seu_repositorio
```
- **Backend**:
Navegue até a pasta do backend e execute:
```
cd backend
mvn spring-boot:run
```
2. A API estará disponível em http://localhost:8080.

- **Frontend**:
Navegue até a pasta do frontend e execute:
```
cd frontend
npm install
npm start
```
2. O frontend estará disponível em http://localhost:3000.

## Documentação da API
A documentação da API está disponível através do Swagger UI em:
http://localhost:8080/swagger-ui/index.html

## Contribuições
Contribuições são bem-vindas! Se você deseja contribuir para o projeto, siga estas etapas:

1. Faça um fork do projeto.
2. Crie uma nova branch (git checkout -b minha-feature).
3. Faça suas alterações e commit (git commit -m 'Adicionei uma nova feature').
4. Envie sua branch (git push origin minha-feature).
5. Crie um novo Pull Request.
## Licença
Somente para estudo e visualizar.
