# How to run the application

./gradlew build
docker build -t spring/spring-tutorial .
docker run -p 8080:8080 spring/spring-tutorial