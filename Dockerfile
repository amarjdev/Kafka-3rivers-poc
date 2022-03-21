FROM openjdk:8-jdk-alpine
EXPOSE 7071
ADD target/Kafka-3rivers-poc-1.0.jar Kafka-3rivers-poc-1.0.jar
ENTRYPOINT ["java","-jar","Kafka-3rivers-poc-1.0.jar"]