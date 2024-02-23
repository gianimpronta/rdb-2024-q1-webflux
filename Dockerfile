FROM ghcr.io/graalvm/graalvm-community:21 AS build

USER root

RUN microdnf install findutils

WORKDIR /code

COPY src src
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN --mount=type=cache,target=/root/.gradle/caches,id=gradle-cache ./gradlew
RUN --mount=type=cache,target=/root/.gradle/caches,id=gradle-cache ./gradlew nativeCompile -x test

FROM debian:12.4-slim

WORKDIR /work

RUN apt update && apt install -y curl && apt clean

COPY --from=build /code/build/native/nativeCompile/* /work/

RUN chmod 775 /work

EXPOSE 8080

CMD ["./backend", "-Xmx200M", "-Xms50M", "-Xss156k"]