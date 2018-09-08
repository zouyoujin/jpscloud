FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD jpscloud-admin-web.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]