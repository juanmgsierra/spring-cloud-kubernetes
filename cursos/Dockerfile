FROM maven:3.8.7 AS maven

WORKDIR /app/cursos

COPY ./pom.xml /app
COPY ./cursos/.mvn ./.mvn
COPY ./cursos/mvnw .
COPY ./cursos/pom.xml .

RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./cursos/src ./src


RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=maven /app/cursos/target/cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

ENTRYPOINT [ "java", "-jar", "cursos-0.0.1-SNAPSHOT.jar" ]