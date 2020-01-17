FROM openjdk:8
VOLUME /tmp
ADD ./target/consumerRabbit-0.0.1-SNAPSHOT.jar consumerRabbit.client.jar
ENTRYPOINT ["java", "-jar","/consumerRabbit.client.jar" ]