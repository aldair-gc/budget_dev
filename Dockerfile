FROM bellsoft/liberica-openjdk-alpine:21.0.5-11
LABEL authors="Aldair Garros"

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]