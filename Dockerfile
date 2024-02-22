FROM ghcr.io/graalvm/graalvm-community:17 AS build

USER root
WORKDIR /code

COPY src src
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN ./gradlew nativeCompile -x test

FROM debian:12.4-slim

WORKDIR /work
COPY --from=build /code/build/native/nativeCompile/* /work/

RUN chmod 775 /work

RUN mkdir -p /usr/local/newrelic
ADD ./newrelic/newrelic.jar /usr/local/newrelic/newrelic.jar
ADD ./newrelic/newrelic.yml /usr/local/newrelic/newrelic.yml

EXPOSE 8080

CMD ["./backend", "-Xmx170M", "-Xms50M", "-Xss156k", "-javaagent:/usr/local/newrelic/newrelic.jar"]