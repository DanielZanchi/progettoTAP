FROM openjdk:8

ENV DISPLAY :0
CMD xeyes

ARG fatjar

COPY /maven/$fatjar /app/app.jar

WORKDIR /app

CMD ["java", "-jar", "app.jar", "mongo-host"]