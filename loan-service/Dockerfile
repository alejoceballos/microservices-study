FROM openjdk:17-slim as build

MAINTAINER https://github.com/alejoceballos

COPY build/libs/loan-service-0.0.1-SNAPSHOT.jar loan-service.jar

ENTRYPOINT ["java", "-jar", "/loan-service.jar"]

