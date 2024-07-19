Without workdir
    docker run -it --rm -v "${PWD}:/app" dnyanyog.org/maven-jdk17:latest mvn install -f hello-service/pom.xml

With workdir
    docker run -it --rm -v "${PWD}:/app" dnyanyog.org/maven-jdk17:latest mvn install -f /app/hello-service/pom.xml

With maven volume (two volumes)
    docker run -it --rm -v "${PWD}:/app" -v "C:\Users\ganuk\.m2:/root/.m2" dnyanyog.org/maven-jdk17:latest mvn install -f /app/hello-service/pom.xml
