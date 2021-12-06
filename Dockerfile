FROM openjdk:11
EXPOSE 1400
ADD target/systemadapter.jar systemadapter.jar
ENTRYPOINT ["java", "-jar", "systemadapter.jar"]