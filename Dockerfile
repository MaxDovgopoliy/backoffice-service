FROM openjdk:11
ADD /target/backoffice-0.0.1-SNAPSHOT.jar backoffice-service.jar
ENTRYPOINT ["java", "-jar", "backoffice-service.jar"]

