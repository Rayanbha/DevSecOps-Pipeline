FROM node:20.5-alpine AS builder

WORKDIR /app

RUN npm install -g @angular/cli@16.2.16

COPY package.json ./
RUN npm install

COPY . .

RUN ng build

FROM nginx:alpine

RUN rm -rf /usr/share/nginx/html/*

COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=builder /app/dist/crudtuto-Front /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]