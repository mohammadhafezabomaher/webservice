FROM openjdk:17
EXPOSE 8090
ADD target/api-gateway-0.0.1-SNAPSHOT.jar Gateway.jar
ENTRYPOINT ["java","-jar","Gateway.jar"]