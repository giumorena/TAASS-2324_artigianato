FROM maven:3.9.6-eclipse-temurin-17 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -DskipTests=true -f pom.xml clean package

FROM eclipse-temurin:17-jre
COPY --from=build /workspace/target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

# docker build --tag api-gateway .
# docker tag api-gateway gmorena/api-gateway
# docker push gmorena/api-gateway