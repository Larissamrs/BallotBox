FROM maven:3.9.4-eclipse-temurin-21 AS build
  WORKDIR /app
  COPY pom.xml .
  RUN mvn dependency:go-offline
  COPY src ./src
  RUN mvn clean package -DskipTests
  FROM tomcat:10.1-jdk21
  WORKDIR /usr/local/tomcat
  COPY --from=build /app/target/provaweb3.war ./webapps/ 
  EXPOSE 8080
  CMD ["catalina.sh", "run"]