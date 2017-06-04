FROM frolvlad/alpine-oraclejdk8:slim

ADD demo.jar app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]