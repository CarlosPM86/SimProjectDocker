FROM openjdk:8
VOLUME /tmp
ADD ./target/zuul-0.0.1-SNAPSHOT.jar zuul-service.client.jar
ENTRYPOINT ["java", "-jar","/zuul-service.client.jar" ]