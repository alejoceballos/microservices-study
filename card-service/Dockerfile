FROM openjdk:17-slim as build

MAINTAINER https://github.com/alejoceballos

COPY build/libs/card-service-0.0.1-SNAPSHOT.jar card-service.jar

ENTRYPOINT ["java", "-jar", "/card-service.jar"]
