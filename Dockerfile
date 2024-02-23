FROM ghcr.io/graalvm/graalvm-community:21 AS build

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

EXPOSE 8080

CMD ["./backend", "-Xmx200M", "-Xms50M", "-Xss156k"]