FROM alpine
RUN apk add --update nodejs npm
WORKDIR /app
COPY package*.json ./
RUN npm install --save bootstrap@^5.0.0-alpha.6 react-bootstrap@^0.32.1
COPY . .
EXPOSE 3000
CMD ["npm","run", "start"]
