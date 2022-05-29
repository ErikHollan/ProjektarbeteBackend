# syntax=docker/dockerfile:1

FROM openjdk:19-alpine3.15

WORKDIR /root/projekt

COPY target/ProjektarbeteBackend-0.0.1-SNAPSHOT.jar /root/projekt

ENTRYPOINT java -jar ProjektarbeteBackend-0.0.1-SNAPSHOT.jar