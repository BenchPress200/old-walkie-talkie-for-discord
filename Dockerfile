FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY build/libs/walkie-talkie-0.0.1-SNAPSHOT.jar walkie-talkie.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "walkie-talkie.jar"]