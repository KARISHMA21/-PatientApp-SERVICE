#
FROM openjdk:17
EXPOSE 8081
COPY ./target/patient_service.jar ./
WORKDIR ./
ENTRYPOINT ["java","-jar","/patient_service.jar"]
#EXPOSE 8082
#ADD target/admin_service-jar-with-dependencies.jar admin_service-jar-with-dependencies.jar
#