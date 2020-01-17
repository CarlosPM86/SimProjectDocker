FROM openjdk:8
VOLUME /tmp
ADD ./target/sim-service-0.0.1-SNAPSHOT.jar sim-service.client.jar
ENTRYPOINT ["java", "-jar","/sim-service.client.jar" ]