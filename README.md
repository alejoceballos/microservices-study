# Microservices Study

My notes and code from https://www.udemy.com/course/master-microservices-with-spring-docker-kubernetes
- Course Reference: https://github.com/eazybytes/microservices

![Microservices Architecture](README.files/Microservices-Architecture.png "Microservices Architecture")

**Table of Contents**

<!-- TOC -->
* [Microservices Study](#microservices-study)
  * [Microservices](#microservices)
    * [Important](#important)
      * [Identifying Boundaries](#identifying-boundaries)
  * [Communication](#communication-)
    * [Notes regarding REST](#notes-regarding-rest)
  * [Cloud Native](#cloud-native)
    * [Cloud Native vs. Traditional Enterprise](#cloud-native-vs-traditional-enterprise)
    * [Development Principles](#development-principles)
      * [12/15-Factor methodology](#1215-factor-methodology)
        * [Characteristics:](#characteristics)
        * [Principles:](#principles)
          * [1) 1 Code base, 1 Application](#1-1-code-base-1-application)
          * [2) API first](#2-api-first)
          * [3) Dependency management](#3-dependency-management)
          * [4) Design, build, release, run](#4-design-build-release-run)
          * [5) Configuration, credentials & code](#5-configuration-credentials--code)
          * [6) Logs](#6-logs)
          * [7) Disposability](#7-disposability)
          * [8) Backing services](#8-backing-services)
          * [9) Environment parity](#9-environment-parity)
          * [10) Administrative processes](#10-administrative-processes)
          * [11) Port binding](#11-port-binding-)
          * [12) Stateless processes](#12-stateless-processes)
          * [13) Concurrency](#13-concurrency)
          * [14) Telemetry](#14-telemetry)
          * [15) Authentication & Authorization](#15-authentication--authorization)
  * [A Basic Spring Web Application](#a-basic-spring-web-application)
    * [Layered Monolithic Architecture](#layered-monolithic-architecture)
    * [H2 Database:](#h2-database)
    * [Spring Rest Controller](#spring-rest-controller)
    * [Using JPA](#using-jpa)
    * [Using Lombok](#using-lombok)
    * [Validators](#validators)
    * [Auditing](#auditing)
    * [API Documentation](#api-documentation)
  * [Docker](#docker)
    * [Containers vs. Virtual Machines](#containers-vs-virtual-machines)
      * [NOTE! Using two OS simultaneously](#note-using-two-os-simultaneously)
    * [Images & Container](#images--container)
    * [Generating Docker Images:](#generating-docker-images)
      * [Dockerfile](#dockerfile)
      * [Buildpacks](#buildpacks)
      * [Google Jib](#google-jib)
      * [Comparison](#comparison)
    * [Push images to Docker hub](#push-images-to-docker-hub)
    * [Docker Compose](#docker-compose)
    * [Docker Desktop extensions](#docker-desktop-extensions)
<!-- TOC -->

## Microservices

An architecture style that enables building enterprise web applications. It separates business domain logic into 
different services ideally each one with its own database. It allows not only parallel development, but horizontal 
scaling. On the other side, it has some management complexity and infrastructure overhead, but it allows the deployment
of a single service without the need of deploying the whole application.

Other architecture styles are:
- **Monolith:** Deployed as a single structure. Smaller application for smaller teams. Fewer cross-cutting concerns. 
Better performance between concerns and domains. The whole application must follow the same set of technologies, as it
grows larger becomes harder to maintain and small changes demand a full deployment;
- **SOA (Service Oriented Architecture):** Separates concerns into services that can be worked in parallel. These 
services can be reused and better balanced. Its management, however is more complex than the monolith architecture and
the communication between services demands a complex  and overloaded protocol (e.g. SOAP).

### Important

When developing microservices, bear in mind:
- Defining boundaries (DDD);
- Containerization (Docker);
- Management/Configuration;
- Service Discovery & registration (Eureka);
- Building an Edge Server;
- Resiliency (Resiliency4J);
- Observability & Monitoring (Grafana, Prometheus, etc.);
- Security (OAuth2/OpenID);
- Event Driven (RabbitMQ, Kafka);
- Orchestration (Kubernetes);
- Cloud Deployment;

#### Identifying Boundaries

* DDD - Domain Driven design
  * Talk to Domain experts, Business Analysts, users, etc.
  * Keep continuous analysis to prevent growing or shrinking too much, in order to split in other servers or to be  
absorbed by another one. 
  * This process takes  along amount of time
* Event Storming Sizing
  * https://www.lucidchart.com/blog/ddd-event-storming
  * Identify events in the system: "completed payment", "list of products"
  * Identify commands from the events
  * Identify reactions from the commands

## Communication 

- Synchronous messaging: Representational State Transfer (REST)
- Asynchronous messaging:
  - Queue managers (e.g. RabbitMQ, IBM MQ)
  - Message broadcast managers (Kafka)

### Notes regarding REST

- Use verbs for CRUD operations (GET, POST, PUT, PATCH, DELETE, etc.)
- Validate inputs and return proper HTTP error codes and messages
- Document the API (Open API, Swagger, etc.)

## Cloud Native

Create applications designed for cloud computing. Get the full CNCF definition [here](https://github.com/cncf/toc/blob/main/DEFINITION.md).
Basically it is a set of practices that allow your application to run in any cloud environment.

1. Microservices (Loosely coupled services developed, deployed and scaled independently)
2. Containers (Lightweight, portable environments)
3. Scalable & Elastic (Scale horizontally)
4. DevOps practices (Collaboration Devs + Ops, CI/CD, ...)
5. Resilient & Fault tolerance (distributes, load balancing, failure recovery, high availability, ...)
6. Cloud-Native services (cloud platform: AWS, GCP, Azure, ...)

### Cloud Native vs. Traditional Enterprise

_Note: Not sure if I agree with all of this. Those are the course instructor's opinion_

1. Predictability (Error per service vs. Error per whole application)
2. OS Abstraction (due to containerization)
3. Oversize & Dependency (Low vs. High)
4. Delivery (Continuous vs. Waterfall)
5. Recovery & Scalability (Fast & High vs. Slow & Low)

### Development Principles

#### 12/15-Factor methodology

Based on [The Twelve-Factor App](https://12factor.net/)
and [Beyond the Twelve-Factor App](https://raw.githubusercontent.com/ffisk/books/master/beyond-the-twelve-factor-app.pdf).

##### Characteristics:

- Cloud Platform Deployment: platform independent
- Scalability as a Core Attribute: Horizontal scalability must be supported
- System Portability: Cloud technology-agnostic
- Continuous Deployment & Agility: Facilitate agile development cycles

##### Principles:

|   |                                   |    |                          |    |                                |
|---|-----------------------------------|----|--------------------------|----|--------------------------------|
| 1 | 1 Code base, 1 Application        | 6  | Logs                     | 11 | Port binding                   |
| 2 | API first                         | 7  | Disposability            | 12 | Stateless processes            |
| 3 | Dependency management             | 8  | Backing services         | 13 | Concurrency                    |
| 4 | Design, build, release, run       | 9  | Environment parity       | 14 | Telemetry                      |
| 5 | Configuration, credentials & code | 10 | Administrative processes | 15 | Authentication & Authorization |

###### 1) 1 Code base, 1 Application

- In real life, each microservice must have its own repository.
- Configurations must be injected externally during deployment (or on'-the-fly?)
- The same codebase must be used in development, testing and production environments

###### 2) API first

- Prioritize upfront API design
- Encourage division of work
- Allow asynchronous development

###### 3) Dependency management

- Declare all dependencies in a manifest
- Use dependence managers like Maven or Gradle

###### 4) Design, build, release, run

- Design stage: determine techs, dependencies and tools
- Build stage: compile and package to a unique identifiable immutable artifact
  - Use semantic versioning or timestamp versioning
- Release stage: Combine the build to a deployment configuration
  - Facilitate rollback through a central repository storage
- Run stage: Execute the application using a release

###### 5) Configuration, credentials & code

- Do not embed configuration in the code
- Enable change configurations independently of environment
- Resources for backing services (i.e. databases, messaging, credentials etc.)
- Do not expose sensitive configurations in the public
- Store configuration in a distinct, private repository

###### 6) Logs

- Log storage is an external tool responsibility
- Redirect logs to the standard output
- Treat logs sequentially according to timestamp
- Aggregate all logs in an external tool

###### 7) Disposability

- Application in the clouds are considered ephemeral
- It must be possible to terminate a service and replace it with a new instance
- Applications can be started and stopped as needed
- Design applications for fast start up and graceful shutdown

###### 8) Backing services

- Databases, message brokers, caching systems, etc.
- Different backing service instances per environment
- Manage each instance using resource binding per environment

###### 9) Environment parity

- Minimize difference between environments
- Use containers
- Address:
  - Time gap: The time between development and deployment. Use Continuous Integration and development
  - People gap: address the difference between roles. Use DevOps culture
  - Tools gap: Use the same tools regardless environment (e.g. same database tech in dev, test and production)

###### 10) Administrative processes

- Non-functional tasks, like migrations and maintenance should be treated as an isolated process 
- These processes are equally important and must be properly tracked, tested and deployed
- But separate the administrative tasks from the business logic of the service

###### 11) Port binding 

**NOTE: To be reviewed**

- Translate external public endpoints to internal endpoints
- Can be an application, externally visible, that requests to internal microservices application
- GraphQL, for example?

###### 12) Stateless processes

- Do not store anything in the service container instance because it will be lost if it is destroyed
- Important to achieve scalability
- Share-nothing architecture
- Use database backing services to store the state

###### 13) Concurrency

- Serve large number of users simultaneously
- Must be horizontally scalable
- Java use thread pools

###### 14) Telemetry

- Be able to monitor logs, metrics, traces, health status, events, etc. (observability)
- Monitor a large number of services and servers
- Remote monitoring

###### 15) Authentication & Authorization

- Security is a critical aspect
- Zero-trust approach
- External and internal communication must follow security standards
- Authentication & authorization, Code injection prevention: developer responsibility
  - Authentication: track who is using the system
  - Authorization: who has access to what
- HTTPS, SSL certificates, Firewall: platform team responsibility 

## A Basic Spring Web Application

- Using Spring Initializer
![A basic Spring Web Application using Spring Initializer](README.files/Basic-Spring-Web-Application.png "Spring Initializer")

- Adding it as a module in IntelliJ
![Import Module from an existing Spring Boot application](./README.files/Import-Spring-Boot-App-As-IntelliJ-Module.png "Import Module")

- Using YAML files instead of properties files

### Layered Monolithic Architecture

```
Client (HTTP) -> Controller -> Service -> Repository -> Database
                     |                        |
                   Mapper                JPA/Hibernate
```

### H2 Database:

Creating a "schema.sql" file under "main/resources", H2 will create the tables when starting up.

To be able to access the H2 memory database using IntelliJ, besides the web h2-console.
Check:
- [Querying the embedded H2 database of a Spring Boot application](http://web.archive.org/web/20160513065923/http://blog.techdev.de/querying-the-embedded-h2-database-of-a-spring-boot-application/).
- H2ServerConfiguration.class

### Spring Rest Controller

Go deeper on:
```
(application.properties) server.servlet.context-path=/api

@RestController = @Controller + @ResponseBody

@RequestMapping
  path
  MediaType.APPLICATION_JSON_VALUE

@GetMapping / @PostMapping / @PutMapping / @DeleteMapping / @PatchMapping
@RequestBody / @RequestParam / @PathVariable
@RequestHeader

RequestEntity<T>
ResponseEntity<T>

@ResponseStatus vs. @ControllerAdvice / @ExceptionHandler
```

### Using JPA

Go deeper on:
```
@MappedSuperclass vs. other mapping methods
@Column specifics (e.g. updatable = false, insertable = false)

@EntityListeners / @PrePersist / @PreUpdate

... extends JpaRepository
Optional<T> findByXXX(T xxx)

@Transactional

How to use Spring returning Optional and Hibernate Proxy? No persistence context? Always a new select?

jpa.show-sql=true
jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.orm.jdbc.bind=trace
```

### Using Lombok

Go deeper on:
```
@Builder / @SuperBuilder

@Data
JPA entities shouldn't have @Data
```

### Validators

Go deeper on:
```
Validation constraints (e.g. @NotBlank, @Size, @Email, @NotNull, @Pattern, etc.)
@Validation / @Valid

extends ResponseEntityExceptionHandler
override handleMethodArgumentNotValid
```

### Auditing

Go deeper on:
```
XXX implements AuditorAware<?>
@EnableJpaAuditing(auditorAwareRef = "XXX") + @Component("XXX")
@EntityListeners(AuditingEntityListener.class)

Spring's "AuditingEntityListener" vs. My own "BaseEntityListener"
 
[@PrePersist / @PreUpdate] vs. [@CreatedDate / @CreatedBy / @LastModifiedDate / @LastModifiedBy]
```

### API Documentation

Check: https://springdoc.org/

Just add the dependency (no security at this time).

Note that context-path is "api", so should go to: http://localhost:8080/api/swagger-ui/index.html

Validations are used to improve the documentation

Go deeper on: 
```
@OpenApiDefinition
  @Info
    @Contact
    @License
  @ExternalDocumentation(

@Tag

@Operation
  @ApiResponse vs. @ApiResponses
    @Content
  
@Schema
```

## Docker

Simply put, **Docker** is the software that allows creating containers.  

### Containers vs. Virtual Machines

Virtualization is a way to use a single machine to run many environments simulating several machines working at the same 
time, but while "Virtual Machines" are capable to run diverse operating systems, allowing to simulate deploying a system
to a Mac and a Linux OS, containers share the same OS kernel, meaning that all of them will simulate running in the same 
OS. However, having multiple Virtual Machines in the same machine wil demand more processing and memory than having 
multiple containers.

![Containers vs. Virtual Machines](README.files/VM-Vs-Container.png "Containers vs. Virtual Machines")

#### NOTE! Using two OS simultaneously

Docker allows using Windows and Linux containers simultaneously in a Windows machine. Check 
[Running Docker Windows and Linux Containers Simultaneously](https://devblogs.microsoft.com/premier-developer/running-docker-windows-and-linux-containers-simultaneously/)

### Images & Container
- The **image** is a blueprint of an environment that will hold the service we are building; 
- The **container** is an instance of an image running the service in an isolated environment. 

### Generating Docker Images:

- Dockerfile
- Buildpacks
- Google Jib

#### Dockerfile

Needs more manual steps and must know more of Docker internals.

1. Create your artifact (`mvn clean install`)
2. Create your Dockerfile (`FROM`/`COPY`/`ENTRYPOINT`)
3. Create your image (`docker build`)
4. Run your container (`docker run`)

#### Buildpacks

Does not need to know Docker internals, just add a configuration to the existing plugin. For professional deployment, it
seems to be a more complete tool than the next one, Google Jib.

1. Edit `pom.xml` (spring-boot-maven-plugin), add: `configuration` / `image` / `name`
2. Create your image (`mvn spring-boot:build-image`)
3. Run your container (`docker run`)

#### Google Jib

Faster than Buildpacks. Need to add a plugin to pom.xml.

1. Edit `pom.xml`, add `jib-maven-plugin`
2. Create your image (`mvn compile jib:dockerBuild`)
3. Run your container (`docker run`)

#### Comparison

- [Buildpacks vs Jib vs Dockerfile: Comparing containerization methods](https://cloud.google.com/blog/topics/developers-practitioners/comparing-containerization-methods-buildpacks-jib-and-dockerfile)
- [Buildpacks.io comparison page](https://buildpacks.io/features/#comparison)

### Push images to Docker hub

```shell
docker image push docker.io/username/imagename:tag
```

### Docker Compose

- Create a `docker-compose-yml` file
- Run `docker compose up` command

### Docker Desktop extensions

- Logs Explorer

