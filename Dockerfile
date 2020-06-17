FROM maven:3.6.3-jdk-14 AS build
WORKDIR /opt/src
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package -Dmaven.test.skip=true spring-boot:repackage

FROM openjdk:14
COPY --from=build /opt/src/target/authentication_microservice-0.0.1-SNAPSHOT.jar /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]