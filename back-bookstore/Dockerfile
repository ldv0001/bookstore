FROM amazoncorretto:18-alpine
WORKDIR /app
COPY ./target/back-bookstore-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
RUN ["mkdir","/app/images"]
CMD ["java","-jar","back-bookstore-0.0.1-SNAPSHOT.jar"]