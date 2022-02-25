# Microservices Study

Based on "[Master Microservices with Spring Docker and Kubernetes](https://www.udemy.com/course/master-microservices-with-spring-docker-kubernetes)" Udemy course.

### Notes

This project is being developed using IntelliJ Ultimate Edition under the WSL2 environment.

## The Monolithic Architecture

TBD

## The SOA Architecture

TBD

## The Microservices Architecture

TBD

## Creating a simple service

### 1. GitHub Configuration

TBD

### 2. Spring Initializer

From [Spring Initializer](https://start.spring.io/):

- Project: Gradle 
- Project Language: Java
- Spring Boot: 2.6.3
- Project Metadata
  - Group: microservice.study
  - Artifact: microservice-study
  - Name: Microservice Study
  - Description: Based on https://www.udemy.com/course/master-microservices-with-spring-docker-kubernetes course
  - Package name: microservice.study
  - Packaging: Jar
  - Java: 11
- Dependencies:
  - Spring Web
  - Spring Boot Actuator

### 3. Development Environment

TBD

### 4. Using Actuator

Run the Spring boot application.

Check `http://localhost.wsl:8080/actuator`:
```javascript
{
  _links: {
    self: {
      href: "http://localhost.wsl:8080/actuator",
      templated: false
    },
    health: {
      href: "http://localhost.wsl:8080/actuator/health",
      templated: false
    },
    health-path: {
      href: "http://localhost.wsl:8080/actuator/health/{*path}",
      templated: true
    }
  }
}
```
According to [Microservices Udemy course](https://www.udemy.com/course/master-microservices-with-spring-docker-kubernetes), 
the url `http://localhost.wsl:8080/actuator/info` should also be exposed.

To expose it, you must edit `application.properties` file and add:
```properties
management.endpoints.web.exposure.include=info
```
The default info retrieved can be set as `info.env` properties. Just add:
```properties
management.info.env.enabled=true
info.application.name=Microservices Study
info.application.description=A Java / Spring Microservices Study Case
info.application.version=1.0
```
Other information can be retrieved...

#### Java  info:
```properties
management.info.java.enabled=true
```
#### Build info:
```properties
management.info.build.enabled=true
```
Note: needs build info to be generated. Add the following to `build.gradle`:
```groovy
springBoot {
    buildInfo()
}
```
#### Git Info:
```properties
management.info.git.enabled=true
```
Note: needs git info to be generated. Add the following to `build.gradle`, in the `plugins` section:
```groovy
plugins {
    .
    .
    .
    id "com.gorylenko.gradle-git-properties" version "2.3.2"
}
```
#### Reference
  - https://medium.com/@TimvanBaarsen/help-my-spring-boot-info-actuator-endpoint-is-enabled-but-i-dont-see-any-environment-details-c2d41a7b24d7

#### Exposing all Actuator's endpoint

To expose all actuator's endpoints, you must edit `application.properties` file and set:
```properties
management.endpoints.web.exposure.include=*
```
Check `http://localhost.wsl:8080/actuator`, again.
