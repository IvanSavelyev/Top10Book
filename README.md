For start up the app use:

1. Build a sources by gradle build command from root (command: gradle clean build)
2. Then build a docker image (command: docker build -t top10app .)
3. Run app (command: docker run -it -p 8080:8080 top10app)

While app running, for addition info check swagger-ui link:
http://localhost:8080/api/top10/swagger-ui/index.html