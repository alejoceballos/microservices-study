FROM openjdk:21-slim

LABEL author="Alejo Ceballos"
LABEL version="1.0"
LABEL description="Account Service Image"

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]

# docker build . -t alejoceballos/ms_study_accounts:v1
# -t TAG

# docker run -d -p 8080:8080 alejoceballos/ms_study_accounts:v1
# -d DETACHED, -p PORT
