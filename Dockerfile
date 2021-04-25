FROM openjdk:11-jdk-slim as BUILD-BASE
COPY . /repo
RUN cd /repo && mvn package

FROM openjdk:11-jdk-slim
RUN mkdir -p /data/heap-dumps
COPY --from=BUILD-BASE /repo/target/ForDINS-*.jar fordins.jar

CMD ["java", \
    "-XX:+HeapDumpOnOutOfMemoryError", \
    "-XX:HeapDumpPath=/data/heap-dumps", \
    "-Djava.security.egd=file:/dev/urandom", \
    "-jar", \
    "/fordins.jar"]
EXPOSE 5697