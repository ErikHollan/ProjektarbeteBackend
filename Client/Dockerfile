# syntax=docker/dockerfile:1

FROM openjdk:19-alpine3.15

WORKDIR /root/projekt

COPY target/FrontendProjektarbeteBackend-0.0.1-SNAPSHOT.jar /root/projekt

ENTRYPOINT java -jar FrontendProjektarbeteBackend-0.0.1-SNAPSHOT.jar