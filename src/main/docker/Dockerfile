FROM openjdk:11-jre-slim

LABEL application=io.karthik.cepengine \
      maintainer=KarthikReddivari<karthik2r@gmail.com>

ENV RUN_PORT=8080

COPY ./app/cep-engine-*.jar /app/app.jar

RUN groupadd -r iamnotroot && useradd -r -g iamnotroot iamnotroot && \
    chown -R iamnotroot:iamnotroot /app && \
    chmod -R 755 /app

WORKDIR /app

EXPOSE $RUN_PORT

VOLUME [ "/app/secrets", "/app/config" ]

USER iamnotroot

CMD java -jar app.jar
