FROM openjdk:17-slim as build

MAINTAINER https://github.com/alejoceballos

COPY build/libs/account-service-0.0.1-SNAPSHOT.jar account-service.jar

ENTRYPOINT ["java", "-jar", "/account-service.jar"]

