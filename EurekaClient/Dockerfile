FROM openjdk:8
VOLUME /tmp
EXPOSE 9000
ADD ./target/EurekaClient-0.0.1-SNAPSHOT.jar eureka.client.jar
ENTRYPOINT ["java", "-jar","/eureka.client.jar" ]