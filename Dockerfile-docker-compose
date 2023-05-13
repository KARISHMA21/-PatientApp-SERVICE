FROM openjdk:18
ADD target/patient_service-0.0.1-SNAPSHOT.jar patient_service-docker.jar
ENTRYPOINT ["java","-jar","/patient_service-docker.jar"]