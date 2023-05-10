FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} seoul-bomo.jar
ENTRYPOINT ["java","-jar","/seoul-bomo.jar"]