FROM openjdk:8

unset DISPLAY
CMD xeyes

ARG fatjar

COPY /maven/$fatjar /app/app.jar

WORKDIR /app

CMD ["java", "-jar", "app.jar", "mongo-host"]