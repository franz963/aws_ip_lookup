FROM maven:3.6.3-adoptopenjdk-14 AS MAVEN_ENV
WORKDIR /build/
COPY pom.xml /build
COPY src /build/src
RUN mvn clean package -DskipTests=true

FROM openjdk:14-jdk-alpine
COPY  --from=MAVEN_ENV /build/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]