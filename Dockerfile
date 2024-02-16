FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /builder

COPY src src
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN ./gradlew bootJar -x test

FROM eclipse-temurin:17-alpine
WORKDIR /worker
COPY --from=builder /builder/build/libs/*.jar /worker/app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/worker/app.jar"]