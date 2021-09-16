FROM openjdk:11
EXPOSE 8080
ADD target/systemadapter.jar systemadapter.jar
ENTRYPOINT ["java", "-jar", "systemadapter.jar"]