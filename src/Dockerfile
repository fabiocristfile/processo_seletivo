# Usando uma imagem base do Node.js para rodar o React
FROM node:16-alpine

# Definir o diretório de trabalho
WORKDIR /app

# Copiar os arquivos de dependências
COPY package*.json ./

# Instalar as dependências
RUN npm install

# Copiar o código para o diretório de trabalho
COPY . .

# Definir a porta onde a aplicação vai rodar
EXPOSE 3000

# Comando para rodar a aplicação
CMD ["npm", "start"]
