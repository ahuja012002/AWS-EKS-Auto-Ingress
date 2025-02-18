FROM openjdk:17
WORKDIR /app
COPY ./target/department-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "department-0.0.1-SNAPSHOT.jar"]