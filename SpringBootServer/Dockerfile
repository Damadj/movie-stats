FROM adoptopenjdk/openjdk8:alpine-slim
EXPOSE 8383
WORKDIR /app/server/
COPY target/server_lib /app/server/server_lib
COPY target/server.jar /app/server/
#COPY globalclient.properties .

CMD java -jar server.jar