FROM openjdk:11
ADD target/prospectron-0.0.1-SNAPSHOT.jar prospectron-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "prospectron-0.0.1-SNAPSHOT.jar"]