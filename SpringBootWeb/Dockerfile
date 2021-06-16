FROM adoptopenjdk/openjdk8:alpine-slim
EXPOSE 8080
WORKDIR /app/web/
COPY target/web_lib /app/web/web_lib
COPY target/web.jar /app/web/
COPY moviestats.properties .

CMD java -jar web.jar
