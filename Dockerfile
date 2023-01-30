FROM --platform=linux/amd64 openjdk:17
ARG JAR_FILE=build/libs/*jar
COPY ${JAR_FILE} restful-jpa-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/restful-jpa-0.0.1-SNAPSHOT.jar"]
