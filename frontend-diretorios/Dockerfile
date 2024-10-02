# Dockerfile do frontend
FROM node:18 AS build

# Cria um diretório de trabalho
WORKDIR /app

# Copia os arquivos package.json e package-lock.json
COPY package*.json ./

# Instala as dependências
RUN npm install

# Copia o restante dos arquivos
COPY . .

# Compila o aplicativo
RUN npm run build

# Fase de produção
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html

# Serve o aplicativo
EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]
