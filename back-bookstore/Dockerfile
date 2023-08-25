FROM amazoncorretto:18-alpine
WORKDIR /app
COPY ./target/back-bookstore-0.0.1-SNAPSHOT.jar .
COPY ./src/main/resources/images/java_schildt.jpg .
EXPOSE 8080
RUN ["mkdir","/app/images"]
CMD ["java","-jar","back-bookstore-0.0.1-SNAPSHOT.jar"]