#
FROM openjdk:17
EXPOSE 8081
COPY ./target/patient_service.jar ./
WORKDIR ./
ENTRYPOINT ["java","-jar","/patient_service.jar"]
