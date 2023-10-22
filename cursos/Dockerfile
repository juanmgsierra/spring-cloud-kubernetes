FROM openjdk:17.0.2

WORKDIR /app

COPY ./target/cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

ENTRYPOINT [ "java", "-jar", "cursos-0.0.1-SNAPSHOT.jar" ]