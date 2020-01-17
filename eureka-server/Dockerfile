FROM openjdk:8
VOLUME /tmp
EXPOSE 8761
ADD ./target/EUREKA_SERVER-0.0.1-SNAPSHOT.jar eureka.server.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=madrid","/eureka.server.jar" ]