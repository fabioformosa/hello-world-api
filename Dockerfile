FROM gradle:8.5.0-jdk21 AS build
WORKDIR /home/gradle/project
COPY gradle/ gradle/
COPY build.gradle settings.gradle gradlew ./
COPY src/ src/
RUN chmod +x ./gradlew
RUN ./gradlew build --no-daemon

FROM eclipse-temurin:21.0.2_13-jdk-alpine
EXPOSE 8080
VOLUME /tmp
RUN mkdir /logs
VOLUME /logs
ARG JAR_FILE=/home/gradle/project/build/libs/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
