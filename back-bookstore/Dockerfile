FROM amazoncorretto:18-alpine
WORKDIR /app
COPY ./target/back-bookstore-0.0.1-SNAPSHOT.jar .
RUN ["mkdir","/app/images"]
COPY ./src/main/resources/images/java_schildt.jpg ./images
EXPOSE 8080
CMD ["java","-jar","back-bookstore-0.0.1-SNAPSHOT.jar"]