FROM openjdk:17-jdk-alpine
ENV SPRING_PROFILES_ACTIVE=pro
WORKDIR /app
COPY target/EcommerceBackendSystem-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar