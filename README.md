# Processo Seletivo - Projeto de Gestão de Diretórios e Arquivos

Uma aplicação para gerenciar diretórios e arquivos de forma simples e eficaz, desenvolvida com **Spring Boot** no backend e **React** no frontend. Este projeto foi criado como parte de um processo seletivo, demonstrando habilidades em desenvolvimento de software.

## Tecnologias Utilizadas

- **Backend:**
  - Spring Boot
  - Java 17
  - H2 Database
  - Maven

- **Frontend:**
  - React.js
  - Axios

- **Docker:**
  - Docker
  - Docker Compose

## Funcionalidades

- Criação, leitura, atualização e exclusão (CRUD) de diretórios e arquivos.
- Interface de usuário amigável desenvolvida com React.
- Comunicação em tempo real usando WebSockets.
- Conexão com banco de dados H2 em memória.

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

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Instalação

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/fabiocristfile/processo_seletivo.git
   ```
2. **Navegue até o diretório do projeto:
```bash
    cd processo_seletivo
```
3. **Construa e inicie a aplicação usando Docker Compose:
 ```bash
 docker-compose up --build
```
4. **Acesse a aplicação:

##Frontend: http://localhost:3000
##Backend: http://localhost:8080

##Testes
A aplicação foi testada localmente, e todos os recursos estão funcionando conforme o esperado. Utilize ferramentas como Postman para realizar testes na API.

## Documentação da API
A documentação da API está disponível no Swagger. Após iniciar o backend, acesse: http://localhost:8080/swagger-ui.html

##Como Contribuir
Contribuições são bem-vindas! Sinta-se à vontade para abrir um problema ou enviar um pedido de pull.

##Licença
Este projeto está licenciado sob a MIT License.

##Contato
Se você tiver dúvidas ou sugestões, entre em contato:

*Nome: Fábio Cristiano
*Email: fabiocristfile@gmail.com
*GitHub: https://github.com/fabiocristfile
