FROM registry.access.redhat.com/ubi9/openjdk-17:1.15-1
LABEL name="cft2j02-task05-app"
COPY /target/su05microservice-1.0-SNAPSHOT.jar /app/su05microservice.jar
ENTRYPOINT ["java","-jar","/app/su05microservice.jar"]