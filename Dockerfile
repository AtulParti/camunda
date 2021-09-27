FROM openjdk:8 
ADD target/camunda-0.0.1-SNAPSHOT.jar camunda-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "camunda-0.0.1-SNAPSHOT.jar"]
