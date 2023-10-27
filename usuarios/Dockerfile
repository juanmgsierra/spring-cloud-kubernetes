FROM maven:3.8.7 AS maven

WORKDIR /app/usuarios

COPY ./pom.xml /app
COPY ./usuarios/.mvn ./.mvn
COPY ./usuarios/mvnw .
COPY ./usuarios/pom.xml .

RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./usuarios/src ./src


RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=maven /app/usuarios/target/usuarios-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT [ "java", "-jar", "usuarios-0.0.1-SNAPSHOT.jar" ]